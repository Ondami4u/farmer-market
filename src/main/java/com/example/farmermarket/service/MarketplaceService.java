package com.example.farmermarket.service;

import com.example.farmermarket.model.Client;
import com.example.farmermarket.model.Farmer;
import com.example.farmermarket.model.Product;
import com.example.farmermarket.repository.ClientRepository;
import com.example.farmermarket.repository.FarmerRepository;
import com.example.farmermarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

	public Client createClient(String name) {
		return clientRepository.save(new Client(name));
	}

	public Farmer createFarmer(String name) {
		return farmerRepository.save(new Farmer(name));
	}

	public Product createProduct(Long farmerId, String name, String city, String quality, int quantity, String description) {
		Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
		if (farmer == null) {
			return null;
		}
		Product product = new Product(name, city, quality, quantity, description);
		product.setFarmer(farmer);
		return productRepository.save(product);
	}

	public boolean buyProduct(Long productId, int amount) {
		Product product = productRepository.findById(productId).orElse(null);
		if (product == null || product.getQuantity() < amount) {
			return false;
		}
		product.setQuantity(product.getQuantity() - amount);
		productRepository.save(product);
		return true;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
}