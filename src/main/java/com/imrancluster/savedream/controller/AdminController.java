package com.imrancluster.savedream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin")
	public String adminPage(Model model) {
		
		model.addAttribute("title", "Admin Page!");
		
		return "admin/index";
	}
}
