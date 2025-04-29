package com.example.farmermarket.dto;

import com.example.farmermarket.farmer.Farmer;

public class FarmerDto {

	private Long id;
	private String name;
	private String email;
	
	public FarmerDto() {}

	public FarmerDto(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public static FarmerDto fromEntity(Farmer farmer) {
		return new FarmerDto(
				farmer.getId(),
				farmer.getName(),
				farmer.getEmail()
				);
	}
	
	public static Farmer toEntity(FarmerDto dto) {
		Farmer farmer = new Farmer();
		farmer.setName(dto.getName());
		farmer.setEmail(dto.getEmail());
		return farmer;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
