package com.example.farmermarket.farmer;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.farmermarket.exceptions.UnauthorizedException;
import com.example.farmermarket.security.RoleRepository;
import com.example.farmermarket.utils.PasswordValidator;
import com.example.farmermarket.dto.FarmerDto;
import com.example.farmermarket.dto.FarmerRegisterRequest;
import com.example.farmermarket.exceptions.DuplicateEntityException;
import com.example.farmermarket.security.Role;

@Service
public class FarmerService {

	private final FarmerRepository farmerRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	public FarmerService(FarmerRepository farmerRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.farmerRepository = farmerRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	public Farmer createFarmer(String name) {
		return farmerRepository.save(new Farmer(name));
	}

//	public Farmer registerFarmer(Farmer farmer) {
//		Optional<Farmer> existingFarmer = farmerRepository.findByEmail(farmer.getEmail());
//		if (existingFarmer.isPresent())
//			throw new EntityExistsException("Farmer with this email already exists");
//		//PasswordValidator.validate(farmer.getPassword());
//		return farmerRepository.save(farmer);
//	}

	public Farmer loginFarmer(String email, String password) {
		Farmer farmer = farmerRepository.findByEmail(email)
				.orElseThrow(() -> new UnauthorizedException("Farmer not found"));
		if (!passwordEncoder.matches(password, farmer.getPassword())) {
			throw new UnauthorizedException("Password or login is not correct");
		}
		return farmer;
	}
	
	public FarmerDto registerFarmer(FarmerRegisterRequest request) {
		Farmer farmer = new Farmer();
		farmer.setName(request.getName());
		farmer.setEmail(request.getEmail());
		farmer.setPassword(passwordEncoder.encode(request.getPassword()));
		
		Optional<Farmer> existing = farmerRepository.findByEmail(request.getEmail());
		if(existing.isPresent())
			throw new RuntimeException("Farmer with this email already exists");
		Role farmerRole = roleRepository.findByName("FARMER")
				.orElseThrow(() -> new RuntimeException("Role FARMER not found"));
		farmer.setRoles(Set.of(farmerRole));
		farmerRepository.save(farmer);
		return FarmerDto.fromEntity(farmer);
	}

}
