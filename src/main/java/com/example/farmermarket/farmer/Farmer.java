package com.example.farmermarket.farmer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
}