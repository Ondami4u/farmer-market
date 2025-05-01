package com.example.farmermarket.client;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

	Optional<Client> findByEmail(String email);

	Optional<Client> findByEmailAndPassword(String email, String password);
}
