package com.example.farmermarket.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.farmermarket.exceptions.FarmerNotFoundException;
import com.example.farmermarket.farmer.Farmer;
import com.example.farmermarket.farmer.FarmerRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final FarmerRepository farmerRepository;

	public ProductService(ProductRepository productRepository, FarmerRepository farmerRepository) {
		this.productRepository = productRepository;
		this.farmerRepository = farmerRepository;
	}

	public Product createProduct(Long farmerId, String name, String city, String quality, int quantity, String description, double price) {
		Farmer farmer = farmerRepository.findById(farmerId).orElseThrow(() -> new FarmerNotFoundException("Farmer with ID " + farmerId + " not found"));
		Product product = new Product(name, city, quality, quantity, description, price);
		product.setFarmer(farmer);
		return productRepository.save(product);
	}

	public Product buyProduct(Long productId, int amount) {
		Product product = productRepository.findById(productId).orElse(null);
		if (product == null || product.getQuantity() < amount) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough product available");
		}
		product.setQuantity(product.getQuantity() - amount);
		return productRepository.save(product); // moduleMapper
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product updatePrice(Long productId, double newPrice) {
		Product product = productRepository.findById(productId).orElse(null);
		if (product != null) {
		product.setPrice(newPrice);
		return productRepository.save(product);
		}
		return null;
	}

	public Product updateDescription(Long productId, String newDescription) {
		Product product = productRepository.findById(productId).orElse(null);
		if(product == null)
			return null;
		product.setDescription(newDescription);
		return productRepository.save(product);
	}

	public boolean deleteProduct(Long productId) {
		Product product = productRepository.findById(productId).orElse(null);
		if(product == null)
			return false;
		productRepository.deleteById(productId);
		return true;
	}

}
