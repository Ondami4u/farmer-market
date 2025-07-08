package com.inetex.farmermarket.farmer;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inetex.farmermarket.dto.FarmerDto;
import com.inetex.farmermarket.dto.FarmerRegisterRequest;

@RestController
@RequestMapping("/api")
public class FarmerController {

	private final FarmerService farmerService;

	public FarmerController(FarmerService farmerService) {
		this.farmerService = farmerService;
	}


	@PostMapping("/farmers/register")
	public ResponseEntity<FarmerDto> registerFarmer(@RequestBody @Valid FarmerRegisterRequest request) {
		FarmerDto responseDto = farmerService.registerFarmer(request);
		return ResponseEntity.ok(responseDto);
	}
	
	@GetMapping("/farmers/me")
	public ResponseEntity<FarmerDto> getCurrentFarmer(Authentication authentication) {
		String email = authentication.getName();
		FarmerDto responseDto = farmerService.getFarmerByEmail(email);
		return ResponseEntity.ok(responseDto);
	}


}
