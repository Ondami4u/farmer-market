package com.example.farmermarket.farmer;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends MongoRepository<Farmer, String> {

	Optional<Farmer> findByEmail(String email);

	Optional<Farmer> findByEmailAndPassword(String email, String password);
}
