package com.example.farmermarket.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmermarket.dto.ClientDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ClientController {

	private final ClientService clientService;

	private ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping("/clients")
	public ResponseEntity<ClientDto> createClient(@RequestParam String name) {
		Client client = clientService.createClient(name);
		ClientDto responseDto = ClientDto.fromEntity(client);
		return ResponseEntity.ok(responseDto);
	}

	@PostMapping("/register/client")
	public ResponseEntity<ClientDto> registerClient(@RequestBody ClientDto clientDto) {
		Client client = ClientDto.toEntity(clientDto);
		Client registered = clientService.registerClient(client);
		ClientDto responseDto = ClientDto.fromEntity(registered);
		return ResponseEntity.ok(responseDto);
	}

	@PostMapping("/login/client")
	public ResponseEntity<ClientDto> loginClient(@RequestParam String email, @RequestParam String password) {
		Client client = clientService.loginClient(email, password);
		ClientDto responseDto = ClientDto.fromEntity(client);
		return ResponseEntity.ok(responseDto);
	}

}
