package com.example.farmermarket.client;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.farmermarket.exceptions.UnauthorizedException;
import com.example.farmermarket.utils.PasswordValidator;

import jakarta.persistence.EntityExistsException;

@Service
public class ClientService {

	private final ClientRepository clientRepository;

	private ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public Client createClient(String name) {
		return clientRepository.save(new Client(name));
	}

	public Client registerClient(Client client) {
		Optional<Client> existingClient = clientRepository.findByEmail(client.getEmail());
		if (existingClient.isPresent())
			throw new EntityExistsException("Client with this email already exists");
		//PasswordValidator.validate(client.getPassword());
		return clientRepository.save(client);
	}

	public Client loginClient(String email, String password) {
		Optional<Client> loginClient = clientRepository.findByEmailAndPassword(email, password);
		if (loginClient.isPresent()) {
			return loginClient.get();
		} else {
			throw new UnauthorizedException("password or login is not correct");
		}
	}

}
