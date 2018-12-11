package com.imrancluster.savedream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

	@GetMapping("/access-denied")
	public String accessDeniedPage() {
		
		return "error/access-denied";
	}
}
