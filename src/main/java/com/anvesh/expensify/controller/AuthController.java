package com.anvesh.expensify.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	
	@GetMapping("/auth/verify")
	public ResponseEntity<?> verifyCredentials() {
		return ResponseEntity.ok("User authenticated successfully!");
	}
}
