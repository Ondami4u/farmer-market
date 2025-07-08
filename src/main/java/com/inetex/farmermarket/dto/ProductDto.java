package com.inetex.farmermarket.dto;

import com.inetex.farmermarket.product.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

	private Long id;
	private String name;
	private String city;
	private String quality;
	private int quantity;
	private String description;
	private double price;

	public ProductDto() {
	}

	private ProductDto(Long id, String name, String city, String quality,
			int quantity, String description, double price) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.quality = quality;
		this.quantity = quantity;
		this.description = description;
		this.price = price;
	}

	public static ProductDto fromEntity(Product product) {
		return new ProductDto(product.getId(), product.getName(), product.getCity(), product.getQuality(), product.getQuantity(), product.getDescription(), product.getPrice());
	}

	public static Product toEntity(ProductDto dto) {
		Product product = new Product();
		product.setName(dto.getName());
		product.setCity(dto.getCity());
		product.setQuality(dto.getQuality());
		product.setQuantity(dto.getQuantity());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		return product;
	}

}
