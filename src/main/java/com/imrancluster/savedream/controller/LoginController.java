package com.imrancluster.savedream.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		
		model.addAttribute("title", "Login Page");
		
		if (request.getParameterMap().containsKey("error")) {
			model.addAttribute("loginError", true);
		}
		
		return "auth/login";
	}
}
