package com.chinamcloud.devops.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chinamcloud.devops.service.db.ExecuteLogService;
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
	public static final String URL = "http://10.10.16.217:8888";

	@Autowired
	private ExecuteLogService service;

	@GetMapping("/form/")
	String getForm() {
		return "executeLog/form";
	}

	@GetMapping("/")
	String list(Model model, @PageableDefault Pageable pageable, ServletRequest request) {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, Request_Prefix);

		model.addAttribute("pages", service.findAll(searchParams, pageable));

		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, Request_Prefix));

		return "executeLog/list";
	}

	@PostMapping("/form/")
	String postForm(RedirectAttributes redirectAttributes, @RequestParam("accessKey") String accessKey,
			@RequestParam("vpcCode") String vpcCode, @RequestParam("instanceCode") String instanceCode,
			@RequestParam("command") String command) {

		redirectAttributes.addFlashAttribute("accessKey", accessKey);
		redirectAttributes.addFlashAttribute("instanceCode", instanceCode);
		redirectAttributes.addFlashAttribute("vpcCode", vpcCode);
		redirectAttributes.addFlashAttribute("command", command);

		Map<String, String> params = new HashMap<>();
		params.put("accessKey", accessKey);
		params.put("vpc_code", vpcCode);
		params.put("tgt", instanceCode);
		params.put("command", command);
		params.put("is_async", "False");

		String jsonString = HttpClientUtils.post(URL + "/opts_util/run_command", params);

		Result result = binder.fromJson(jsonString, Result.class);

		redirectAttributes.addFlashAttribute("resultMap", resultMap(result));

		return "redirect:/executeLog/form/";
	}

	/**
	 * 对salt-api返回的json参数进行解析.
	 * 
	 * @param result
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private HashMap<String, Object> resultMap(Result result) {

		// 替换换行符.
		String s = StringUtils.replace(result.getResp_info(), "\\n", "<br>");

		// 截取字段.eg: {"return": [{"Ecs-L0dNQYd0": "root"}]} -> {"Ecs-OKlBXqyX": "root"}
		String json = StringUtils.substring(s, 12, s.length() - 2);

		return binder.fromJson(json, HashMap.class);
	}

}
