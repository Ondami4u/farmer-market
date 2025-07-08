package com.example.farmermarket.dto;

import com.example.farmermarket.client.Client;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientDto {

	private Long id;
	private String name;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	public ClientDto() {}

	private ClientDto(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public static ClientDto fromEntity(Client client) {
		return new ClientDto(
				client.getId(),
				client.getName(),
				client.getEmail());
	}

	public static Client toEntity(ClientDto dto) {
		Client client = new Client();
		client.setName(dto.getName());
		client.setEmail(dto.getEmail());
		client.setPassword(dto.getPassword());
		return client;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
}
