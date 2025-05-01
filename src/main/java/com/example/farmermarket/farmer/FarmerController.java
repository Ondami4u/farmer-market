package com.example.farmermarket.farmer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmermarket.dto.FarmerDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class FarmerController {

	private final FarmerService farmerService;

	public FarmerController(FarmerService farmerService) {
		this.farmerService = farmerService;
	}

	@PostMapping("/farmers")
	public ResponseEntity<FarmerDto> createFarmer(@RequestParam String name) {
		Farmer farmer = farmerService.createFarmer(name);
		FarmerDto responseDto = FarmerDto.fromEntity(farmer);
		return ResponseEntity.ok(responseDto);
	}

	@PostMapping("register/farmer")
	public ResponseEntity<FarmerDto> registerFarmer(@Valid @RequestBody FarmerDto farmerDto) {
		Farmer farmer = FarmerDto.toEntity(farmerDto);
		Farmer registered = farmerService.registerFarmer(farmer);
		FarmerDto responseDto = FarmerDto.fromEntity(registered);
		return ResponseEntity.ofNullable(responseDto);
	}
	
	@PostMapping("login/farmer")
	public ResponseEntity<FarmerDto> loginFarmer(@RequestParam String email, @RequestParam String password) {
		Farmer farmer = farmerService.loginFarmer(email, password);
		FarmerDto responseDto = FarmerDto.fromEntity(farmer);
		return ResponseEntity.ok(responseDto);
	}

}
