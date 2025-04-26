package com.example.farmermarket.service;

import com.example.farmermarket.exceptions.FarmerNotFoundException;
import com.example.farmermarket.exceptions.UnauthorizedException;
import com.example.farmermarket.model.Client;
import com.example.farmermarket.model.Farmer;
import com.example.farmermarket.model.Product;
import com.example.farmermarket.repository.ClientRepository;
import com.example.farmermarket.repository.FarmerRepository;
import com.example.farmermarket.repository.ProductRepository;

import jakarta.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketplaceService {

	private final ProductRepository productRepository;
	private final FarmerRepository farmerRepository;
	private final ClientRepository clientRepository;

	public MarketplaceService(ProductRepository productRepository, FarmerRepository farmerRepository, ClientRepository clientRepository) {
		this.productRepository = productRepository;
		this.farmerRepository = farmerRepository;
		this.clientRepository = clientRepository;
	}

	public Client registerClient(Client client) {
		Optional<Client> existingClient = clientRepository.findByEmail(client.getEmail());
		if (existingClient.isPresent())
			throw new EntityExistsException("Client with this email already exists");
		return clientRepository.save(client);
	}

	public Client createClient(String name) {
		return clientRepository.save(new Client(name));
	}

	public Client loginClient(String email, String password) {
		Optional<Client> loginClient = clientRepository.findByEmailAndPassword(email, password);
		if (loginClient.isPresent()) {
			return loginClient.get();
		} else {
			throw new UnauthorizedException("Password or login is not correct");
		}
	}

	public Farmer registerFarmer(Farmer farmer) {
		Optional<Farmer> existingFarmer = farmerRepository.findByEmail(farmer.getEmail());
		if (existingFarmer.isPresent())
			throw new EntityExistsException("Farmer with this email already exists");
		return farmerRepository.save(farmer);
	}

	public Farmer createFarmer(String name) {
		return farmerRepository.save(new Farmer(name));
	}

	public Farmer loginFarmer(String email, String password) {
		Optional<Farmer> loginFarmer = farmerRepository.findByEmailAndPassword(email, password);
		if (loginFarmer.isPresent()) {
			return loginFarmer.get();
		} else {
			throw new UnauthorizedException("Password or login is not correct");
		}
	}

	public Product createProduct(Long farmerId, String name, String city, String quality, int quantity, String description) {
		Farmer farmer = farmerRepository.findById(farmerId).orElseThrow(() -> new FarmerNotFoundException("Farmer with ID " + farmerId + " not found"));
		Product product = new Product(name, city, quality, quantity, description);
		product.setFarmer(farmer);
		return productRepository.save(product);
	}

	public Product buyProduct(Long productId, int amount) {
		Product product = productRepository.findById(productId).orElse(null);
		if (product == null || product.getQuantity() < amount) {
			throw new RuntimeException("Not enough product available");
		}
		product.setQuantity(product.getQuantity() - amount);
		return productRepository.save(product);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
}