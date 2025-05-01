package com.example.farmermarket.client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

	Optional<Client> findByEmail(String email);

	Optional<Client> findByEmailAndPassword(String email, String password);
}
