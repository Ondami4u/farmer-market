package com.inetex.farmermarket.client;


import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inetex.farmermarket.dto.ClientDto;
import com.inetex.farmermarket.dto.ClientRegisterRequest;
import com.inetex.farmermarket.exceptions.UnauthorizedException;
import com.inetex.farmermarket.farmer.FarmerRepository;
import com.inetex.farmermarket.security.Role;
import com.inetex.farmermarket.security.RoleRepository;

@Service
public class ClientService {

	private final ClientRepository clientRepository;
	private final FarmerRepository farmerRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	public ClientService(ClientRepository clientRepository, FarmerRepository farmerRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.clientRepository = clientRepository;
		this.farmerRepository = farmerRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	public Client createClient(String name) {
		return clientRepository.save(new Client(name));
	}

	public ClientDto registerClient(ClientRegisterRequest request) {
		if(clientRepository.findByEmail(request.getEmail()).isPresent() 
				|| farmerRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already in use");
		}
		Client client = new Client();
		client.setName(request.getName());
		client.setEmail(request.getEmail());
		client.setPassword(passwordEncoder.encode(request.getPassword()));
	
		Role clientRole = roleRepository.findByName("CLIENT")
				.orElseThrow(() -> new RuntimeException("Role CLIENT not found"));
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
