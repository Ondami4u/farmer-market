package com.example.farmermarket.repository;

import com.example.farmermarket.model.Client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Optional<Client> findByEmail(String email);

	Optional<Client> findByEmailAndPassword(String email, String password);
}
