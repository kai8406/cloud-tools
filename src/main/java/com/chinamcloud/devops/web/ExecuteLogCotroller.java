package com.chinamcloud.devops.web;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chinamcloud.devops.service.db.ExecuteLogService;
import com.chinamcloud.devops.utils.Servlets;

@Controller
@RequestMapping("/executeLog")
public class ExecuteLogCotroller {

	/**
	 * 接收request的前缀.{@value}
	 */
	public static final String Request_Prefix = "search_";

	@Autowired
	private ExecuteLogService service;

	@PostMapping("/")
	String post(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "age");
		return "redirect:/executeLog";
	}

	@GetMapping("/")
	String list(Model model, @PageableDefault Pageable pageable, ServletRequest request) {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, Request_Prefix);

		model.addAttribute("pages", service.findAll(searchParams, pageable));

		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, Request_Prefix));

		return "executeLog/list";
	}

}