package com.chinamcloud.devops.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexCotroller {

	@GetMapping("/")
	String index() {
		return "index";
	}

	@PostMapping("/")
	String postIndex() {
		return "redirect:/";
	}

}
