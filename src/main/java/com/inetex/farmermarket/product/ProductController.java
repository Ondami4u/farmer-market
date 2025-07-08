package com.inetex.farmermarket.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inetex.farmermarket.dto.ProductDto;
import com.inetex.farmermarket.dto.ProductRequest;

@RestController
@RequestMapping("/api")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/products/create")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductRequest request) {
		Product product = productService.createProduct(
				request.getFarmerId(),
				request.getName(),
				request.getCity(),
				request.getQuality(),
				request.getQuantity(),
				request.getDescription(),
				request.getPrice()
				);
		if (product == null) {
			return ResponseEntity.badRequest().build();
		}
		ProductDto productsDto = ProductDto.fromEntity(product);
		return ResponseEntity.ok(productsDto);

	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		List<ProductDto> productDtos = products.stream()
				.map(ProductDto::fromEntity).toList();
		return ResponseEntity.ok(productDtos);
	}

	@PostMapping("/products/{productId}/buy")
	public ResponseEntity<ProductDto> buyProduct(@PathVariable Long productId, @RequestParam int amount) {
		Product updateProduct = productService.buyProduct(productId, amount);
		ProductDto responseDto = ProductDto.fromEntity(updateProduct);
		return ResponseEntity.ok(responseDto);
	}
	
	@PostMapping("/products/{productId}/update-price")
	public ResponseEntity<ProductDto> updateProductPrice(@PathVariable Long productId, @RequestParam double newPrice) {
		Product updateProduct = productService.updatePrice(productId, newPrice);
		return ResponseEntity.ok(ProductDto.fromEntity(updateProduct));
	}
	
	@PutMapping("/products/{productId}/update-description")
	public ResponseEntity<ProductDto> updateProductDescription(@PathVariable Long productId, @RequestParam String newDescription) {
		Product updateProduct = productService.updateDescription(productId, newDescription);
		return ResponseEntity.ok(ProductDto.fromEntity(updateProduct));
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
		productService.deleteProduct(productId);
		return ResponseEntity.noContent().build();
	}
}
