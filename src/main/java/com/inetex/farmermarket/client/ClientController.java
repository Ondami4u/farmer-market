package com.example.farmermarket.client;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmermarket.dto.ClientDto;
import com.example.farmermarket.dto.ClientRegisterRequest;

@RestController
@RequestMapping("/api")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

//	@PostMapping("/clients")
//	public ResponseEntity<ClientDto> createClient(@RequestParam String name) {
//		Client client = clientService.createClient(name);
//		ClientDto responseDto = ClientDto.fromEntity(client);
//		return ResponseEntity.ok(responseDto);
//	}

	@PostMapping("/clients/register")
	public ResponseEntity<ClientDto> registerClient(@RequestBody ClientRegisterRequest request) {
		ClientDto responseDto = clientService.registerClient(request);
		return ResponseEntity.ok(responseDto);
	}
	
	@GetMapping("/clients/me")
	public ResponseEntity<ClientDto> getCurrentCLient(Authentication authentication) {
		String email = authentication.getName(); //subj from token
		ClientDto responseDto = clientService.getClientByEmail(email);
		return ResponseEntity.ok(responseDto);
	}

//	@PostMapping("/login/client")
//	public ResponseEntity<ClientDto> loginClient(@RequestParam String email, @RequestParam String password) {
//		Client client = clientService.loginClient(email, password);
//		ClientDto responseDto = ClientDto.fromEntity(client);
//		return ResponseEntity.ok(responseDto);
//	}

}
