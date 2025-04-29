package com.example.farmermarket.farmer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

	Optional<Farmer> findByEmail(String email);

	Optional<Farmer> findByEmailAndPassword(String email, String password);
}
