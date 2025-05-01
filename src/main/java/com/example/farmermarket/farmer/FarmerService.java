package com.example.farmermarket.farmer;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.farmermarket.exceptions.UnauthorizedException;
import com.example.farmermarket.utils.PasswordValidator;
import com.example.farmermarket.exceptions.DuplicateEntityException;


@Service
public class FarmerService {

	private final FarmerRepository farmerRepository;

	public FarmerService(FarmerRepository farmerRepository) {
		this.farmerRepository = farmerRepository;
	}

	public Farmer createFarmer(String name) {
		return farmerRepository.save(new Farmer(name));
	}

	public Farmer registerFarmer(Farmer farmer) {
		Optional<Farmer> existingFarmer = farmerRepository.findByEmail(farmer.getEmail());
		if (existingFarmer.isPresent())
			throw new DuplicateEntityException("Farmer with this email already exists");
		PasswordValidator.validate(farmer.getPassword());
		return farmerRepository.save(farmer);
	}

	public Farmer loginFarmer(String email, String password) {
		Optional<Farmer> loginFarmer = farmerRepository.findByEmailAndPassword(email, password);
		if (loginFarmer.isPresent()) {
			return loginFarmer.get();
		} else {
			throw new UnauthorizedException("Password or login is not correct");
		}
	}

}
