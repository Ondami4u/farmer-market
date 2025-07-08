package com.inetex.farmermarket.farmer;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inetex.farmermarket.client.ClientRepository;
import com.inetex.farmermarket.dto.FarmerDto;
import com.inetex.farmermarket.dto.FarmerRegisterRequest;
import com.inetex.farmermarket.exceptions.UnauthorizedException;
import com.inetex.farmermarket.security.Role;
import com.inetex.farmermarket.security.RoleRepository;

@Service
public class FarmerService {

	private final FarmerRepository farmerRepository;
	private final ClientRepository clientRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	public FarmerService(FarmerRepository farmerRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.farmerRepository = farmerRepository;
		this.clientRepository = clientRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	public Farmer createFarmer(String name) {
		return farmerRepository.save(new Farmer(name));
	}
	
	public FarmerDto registerFarmer(FarmerRegisterRequest request) {
		if(farmerRepository.findByEmail(request.getEmail()).isPresent() ||
				clientRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("This email already in use");
		}
		Farmer farmer = new Farmer();
		farmer.setName(request.getName());
		farmer.setEmail(request.getEmail());
		farmer.setPassword(passwordEncoder.encode(request.getPassword()));
		
		Role farmerRole = roleRepository.findByName("FARMER")
				.orElseThrow(() -> new RuntimeException("Role FARMER not found"));
		farmer.setRoles(Set.of(farmerRole));
		farmerRepository.save(farmer);
		return FarmerDto.fromEntity(farmer);
	}

	public Farmer loginFarmer(String email, String password) {
		Farmer farmer = farmerRepository.findByEmail(email)
				.orElseThrow(() -> new UnauthorizedException("Farmer not found"));
		if (!passwordEncoder.matches(password, farmer.getPassword())) {
			throw new UnauthorizedException("Password or login is not correct");
		}
		return farmer;
	}
	
	public FarmerDto getFarmerByEmail(String email) {
		Farmer farmer = farmerRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Farmer nor found"));		
		return FarmerDto.fromEntity(farmer) ;
	}

}
