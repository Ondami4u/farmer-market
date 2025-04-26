package com.example.farmermarket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Farmer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name must not be blank")
	private String name;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	@Size(min = 6, message = "Password must contain at least 6 characters")
	private String password;

	public Farmer(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	@OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL)
	@JsonManagedReference //main connection side
	private List<Product> products;

	public Farmer() {
	}

	public Farmer(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}