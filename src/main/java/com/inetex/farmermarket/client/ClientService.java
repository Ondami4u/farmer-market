package com.example.farmermarket.client;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.farmermarket.dto.ClientDto;
import com.example.farmermarket.dto.ClientRegisterRequest;
import com.example.farmermarket.exceptions.UnauthorizedException;
import com.example.farmermarket.security.Role;
import com.example.farmermarket.security.RoleRepository;

@Service
public class ClientService {

	private final ClientRepository clientRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.clientRepository = clientRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	public Client createClient(String name) {
		return clientRepository.save(new Client(name));
	}

	public ClientDto registerClient(ClientRegisterRequest request) {
		Client client = new Client();
		client.setName(request.getName());
		client.setEmail(request.getEmail());
		client.setPassword(passwordEncoder.encode(request.getPassword()));
		
		Optional<Client> existing = clientRepository.findByEmail(client.getEmail());
		if(existing.isPresent())
			throw new RuntimeException("Client with this email already exists");
		Role clientRole = roleRepository.findByName("CLIENT").orElseThrow(() -> new RuntimeException("Role CLIENT not found"));
		client.setRoles(Set.of(clientRole));
		clientRepository.save(client);
		return ClientDto.fromEntity(client);
	}

	public Client loginClient(String email, String password) {
		Client client = clientRepository.findByEmail(email).
				orElseThrow(() -> new UnauthorizedException("Client not found"));
		if (!passwordEncoder.matches(password, client.getPassword())) {
			throw new UnauthorizedException("Password or login is not correct");
		}
		return client;
	}

	public ClientDto getClientByEmail(String email) {
		Client client = clientRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Client not found"));
		return ClientDto.fromEntity(client);
	}

}
