package com.inetex.farmermarket.client;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inetex.farmermarket.dto.ClientDto;
import com.inetex.farmermarket.dto.ClientRegisterRequest;

@RestController
@RequestMapping("/api")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping("/clients/register")
	public ResponseEntity<ClientDto> registerClient(@RequestBody @Valid ClientRegisterRequest request) {
		ClientDto responseDto = clientService.registerClient(request);
		return ResponseEntity.ok(responseDto);
	}
	
	@GetMapping("/clients/me")
	public ResponseEntity<ClientDto> getCurrentCLient(Authentication authentication) {
		String email = authentication.getName(); //subj from token
		ClientDto responseDto = clientService.getClientByEmail(email);
		return ResponseEntity.ok(responseDto);
	}

}
