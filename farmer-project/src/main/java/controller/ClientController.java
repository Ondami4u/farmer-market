package controller;

import dto.ClientRegisterRequest;
import model.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody @Valid ClientRegisterRequest request) {
        Client client = clientService.registerClient(request);
        return ResponseEntity.ok(client);
    }
}
