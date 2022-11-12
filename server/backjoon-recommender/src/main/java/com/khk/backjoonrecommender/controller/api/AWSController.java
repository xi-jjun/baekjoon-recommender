package com.khk.backjoonrecommender.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AWSController {
	@GetMapping("/health-check")
	public String healthCheck() {
		return "Good";
	}
}
