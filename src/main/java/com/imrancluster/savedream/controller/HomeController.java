package com.imrancluster.savedream.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(Model model) {
		
		model.addAttribute("title", "Home Page");
		
		return "home/index";
	}
	
	@RequestMapping("/home")
	public String homePage(Model model) {
		
		model.addAttribute("title", "Home Page");
		
		return "home/index";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		
		model.addAttribute("title", "About Page");
		
		return "home/about";
	}
	
	@GetMapping("/secret")
	public String secret(HttpServletRequest request, Model model) {
		
		if (!request.isUserInRole("ROLE_ADMIN")) {
			return "error/access-denied";
		}
		
		model.addAttribute("title", "Secret Page");
		
		return "home/about";
	}
}
