package com.imrancluster.savedream.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/systems")
public class SystemController {

	@GetMapping("/config")
	public String systemConfigPage(HttpServletRequest request) {
		
		if (request.isUserInRole("ROLE_ADMIN")) {
			System.out.println("ADMIN USER");
		}
		
		
		return "system/config";
	}
}
