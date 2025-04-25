package com.example.farmermarket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	@Size(min = 6, message = "Password must contain at least 6 characters")
	private String password;

	public Client(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Client() {
	}

	public Client(String name) {
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

	public String getPassword() {
		return password;
	}
}
