package com.example.farmermarket.client;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection = "clients")
public class Client {

	@Id
	private String id;

	@NotBlank(message = "Name must not be blank")
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

	public String getId() {
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

	public void setPassword(String password) {
		this.password = password;
	}
}
