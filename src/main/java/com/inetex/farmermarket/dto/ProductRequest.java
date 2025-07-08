package com.inetex.farmermarket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
	private Long farmerId;
	private String name;
	private String city;
	private String quality;
	private int quantity;
	private String description;
	private double price;

}
