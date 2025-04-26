package com.example.farmermarket.controller;

import com.example.farmermarket.model.Product;
import com.example.farmermarket.service.MarketplaceService;

import jakarta.validation.Valid;

import com.example.farmermarket.dto.ProductRequest;
import com.example.farmermarket.model.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class MarketplaceController {

	private final MarketplaceService marketplaceService;

	public MarketplaceController(MarketplaceService marketplaceService) {
		this.marketplaceService = marketplaceService;
	}

	@PostMapping("/farmers")
	public ResponseEntity<Farmer> createFarmer(@RequestParam String name) {
		Farmer farmer = marketplaceService.createFarmer(name);
		return ResponseEntity.ok(farmer);
	}

	@PostMapping("/clients")
	public ResponseEntity<Client> createClient(@RequestParam String name) {
		Client client = marketplaceService.createClient(name);
		return ResponseEntity.ok(client);
	}

	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {
		Product product = marketplaceService.createProduct(
						request.getFarmerId(),
						request.getName(),
						request.getCity(),
						request.getQuality(),
						request.getQuantity(),
						request.getDescription()
						);
		if (product == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
	    List<Product> products = marketplaceService.getAllProducts();
	    return ResponseEntity.ok(products);
	}

	@PostMapping("/products/{productId}/buy")
	public ResponseEntity<Product> buyProduct(@PathVariable Long productId, @RequestParam int amount) {
		Product updateProduct = marketplaceService.buyProduct(productId, amount);
		return ResponseEntity.ok(updateProduct);
	}

	@PostMapping("/register/client")
	public ResponseEntity<Client> registerClient(@Valid @RequestBody Client client) {
		Client registered = marketplaceService.registerClient(client);
		return ResponseEntity.ok(registered);
	}

	@PostMapping("/register/farmer")
	public ResponseEntity<Farmer> registerFarmer(@Valid @RequestBody Farmer farmer) {
		Farmer registered = marketplaceService.registerFarmer(farmer);
		return ResponseEntity.ok(registered);
	}

	@PostMapping("/login/client")
	public ResponseEntity<Client> loginClient(@RequestParam String email, @RequestParam String password) {
		Client client = marketplaceService.loginClient(email, password);
		return ResponseEntity.ok(client);
	}

	@PostMapping("/login/farmer")
	public ResponseEntity<Farmer> loginFarmer(@RequestParam String email, @RequestParam String password) {
		Farmer farmer = marketplaceService.loginFarmer(email, password);
		return ResponseEntity.ok(farmer);
	}

}