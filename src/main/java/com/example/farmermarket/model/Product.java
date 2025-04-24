package com.example.farmermarket.model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String quality;
    private int quantity;
    private String description;

    @ManyToOne
    private Farmer farmer;

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

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public Farmer getFarmer() {
        return farmer;
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

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
