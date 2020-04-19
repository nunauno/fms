package com.ldi.fasttrack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthCheck {
	
	@GetMapping("/healthcheck")
	@ResponseBody
	public String HelloWorld() {
		return "home";
	}
}
