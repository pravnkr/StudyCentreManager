package com.ignoubadhega.studycentremanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppViewsBasedOnRoleController {

    // view for normal staff added by coordinator
	@GetMapping("/")
	public String showHome() {
		
		return "home";
	}
	

	@GetMapping("/coordinator")
	public String showCoordinatorView() {
		
		return "coordinator";
	}
	
}










