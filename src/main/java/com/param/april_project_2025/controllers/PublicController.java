package com.param.april_project_2025.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

	@GetMapping("/is-alive")
	public ResponseEntity<String> checkLiveliness() {
		return ResponseEntity.ok("Application is Running!");
	}
}