package com.example.farmermarket.product;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Entity;

@Entity(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String quality;
    private int quantity;
    private String description;

    
    private String farmerId;

    public Product() {
    }

    public Product(String name, String city, String quality, int quantity, String description) {
        this.name = name;
        this.city = city;
        this.quality = quality;
        this.quantity = quantity;
        this.description = description;
    }

    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getQuality() {
        return quality;
    }

    public void setId(Long id) {
		this.id = id;
	}

	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}

	public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
