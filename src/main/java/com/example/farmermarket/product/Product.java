package com.example.farmermarket.product;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.example.farmermarket.farmer.Farmer;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

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
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    public Product() {
    }

    public Product(String name, String city,
    		String quality, int quantity,
    		String description, double price) {
        this.name = name;
        this.city = city;
        this.quality = quality;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
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

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
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
    
    public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
