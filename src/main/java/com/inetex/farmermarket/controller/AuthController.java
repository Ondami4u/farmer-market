package com.inetex.farmermarket.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import com.inetex.farmermarket.dto.LoginRequest;
import com.inetex.farmermarket.security.JwtUtils;


@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	
	@PostMapping
	public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		User user = (User) authentication.getPrincipal();
		String email = user.getUsername();
		List<String> roles = user.getAuthorities()
				.stream().map(grantedAuthority -> grantedAuthority.getAuthority()).toList();
		String token = jwtUtils.generateToken(email, roles);
		return ResponseEntity.ok(token);
	}
	
	@GetMapping("/whoami")
	public ResponseEntity<String> whoAmI(Authentication authentication) {
		String email = authentication.getName();
		String roles = authentication.getAuthorities().toString();
		return ResponseEntity.ok("Email: " + email + "Roles: " + roles);
	}

}
