package com.example.farmermarket.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {

    @Id
    private String id;

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

    
    public String getId() {
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

    public void setId(String id) {
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
