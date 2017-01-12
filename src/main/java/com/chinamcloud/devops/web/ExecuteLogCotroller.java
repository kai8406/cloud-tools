package com.chinamcloud.devops.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chinamcloud.devops.entity.ExecuteLog;
import com.chinamcloud.devops.entity.Tenants;
import com.chinamcloud.devops.entity.Vpc;
import com.chinamcloud.devops.mq.CMDBService;
import com.chinamcloud.devops.service.db.ExecuteLogService;
import com.chinamcloud.devops.utils.Collections3;
import com.chinamcloud.devops.utils.HttpClientUtils;
import com.chinamcloud.devops.utils.Servlets;
import com.chinamcloud.devops.utils.mapper.JsonMapper;

@Controller
@RequestMapping("/executeLog")
public class ExecuteLogCotroller {

	public static JsonMapper binder = JsonMapper.nonEmptyMapper();
	/**
	 * 接收request的前缀.{@value}
	 */
	public static final String Request_Prefix = "search_";

	/**
	 * Salt API URL.{@value}
	 */
	public static final String Salt_API_URL = "http://10.10.16.217:8888";

	/**
	 * 页面传递进来的"*"号参数对应的Id.
	 */
	public static final Integer star_Index = 0;

	@Autowired
	private ExecuteLogService service;

	@Autowired
	private CMDBService cmdbService;

	@GetMapping("/form/")
	String getForm(Model model) {
		model.addAttribute("tenants", cmdbService.getTenants(new HashMap<>()));
		return "executeLog/form";
	}

	@GetMapping("/id/{id}")
	String list(Model model, @PathVariable("id") Integer id) {

		ExecuteLog executeLog = service.find(id);
		model.addAttribute("detail", executeLog);

		model.addAttribute("resultMap", resultMap(executeLog.getResult()));

		return "executeLog/detail";
	}

	@GetMapping("/")
	String list(Model model, @PageableDefault Pageable pageable, ServletRequest request) {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, Request_Prefix);

		model.addAttribute("pages", service.findAll(searchParams, pageable));

		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, Request_Prefix));

		return "executeLog/list";
	}

	@PostMapping("/form/")
	String postForm(RedirectAttributes redirectAttributes, @RequestParam("tenants") Integer tenantsId,
			@RequestParam("vpc") Integer vpcId, @RequestParam("ecs") List<Integer> ecsIds,
			@RequestParam("command") String command) {

		redirectAttributes.addFlashAttribute("tenants", tenantsId);
		redirectAttributes.addFlashAttribute("vpc", vpcId);
		redirectAttributes.addFlashAttribute("ecs", ecsIds);
		redirectAttributes.addFlashAttribute("command", command);

		Tenants tenants = cmdbService.findTenants(tenantsId);
		Vpc vpc = cmdbService.findVpc(vpcId);

		List<String> ecsCodes = new ArrayList<>();

		ecsIds.stream().filter(i -> i != star_Index).forEach(i -> ecsCodes.add(cmdbService.findEcs(i).getCode()));

		Map<String, String> params = new HashMap<>();
		params.put("accessKey", tenants.getAccessKey());
		params.put("vpc_code", vpc.getCode());
		params.put("tgt", ecsIds.contains(star_Index) ? "*" : Collections3.convertToString(ecsCodes, ","));
		params.put("command", command);
		params.put("is_async", "False");

		String jsonString = HttpClientUtils.post(Salt_API_URL + "/opts_util/run_command", params);

		Result result = binder.fromJson(jsonString, Result.class);

		ExecuteLog executeLog = new ExecuteLog();
		executeLog.setResult(result.getResp_info());
		executeLog.setInstanceCode(ecsIds.contains(star_Index) ? "*" : "");
		executeLog.setCreateTime(new Date());
		executeLog.setCommand(command);
		executeLog.setVpcCode(vpc.getCode());

		service.saveAndFlush(executeLog);

		redirectAttributes.addFlashAttribute("resultMap", resultMap(result.getResp_info()));

		return "redirect:/executeLog/form/";
	}

	/**
	 * 对salt-api返回的json参数进行解析.
	 * 
	 * @param result
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private HashMap<String, Object> resultMap(String jsonString) {

		// 替换换行符.
		String s = StringUtils.replace(jsonString, "\\n", "<br>");

		// 截取字段.eg: {"return": [{"Ecs-L0dNQYd0": "root"}]} -> {"Ecs-OKlBXqyX": "root"}
		String json = StringUtils.substring(s, 12, s.length() - 2);

		return binder.fromJson(json, HashMap.class);
	}

}
