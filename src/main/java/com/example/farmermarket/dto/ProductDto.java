package com.example.farmermarket.dto;

import com.example.farmermarket.product.Product;

public class ProductDto {

	private Long id;
	private String name;
	private String city;
	private String quality;
	private int quantity;
	private String description;

	public ProductDto() {
	}

	private ProductDto(Long id, String name, String city, String quality, int quantity, String description) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.quality = quality;
		this.quantity = quantity;
		this.description = description;
	}

	public static ProductDto fromEntity(Product product) {
		return new ProductDto(product.getId(), product.getName(), product.getCity(), product.getQuality(), product.getQuantity(), product.getDescription());
	}

	public static Product toEntity(ProductDto dto) {
		Product product = new Product();
		product.setName(dto.getName());
		product.setCity(dto.getCity());
		product.setQuality(dto.getQuality());
		product.setQuantity(dto.getQuantity());
		product.setDescription(dto.getDescription());
		return product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
