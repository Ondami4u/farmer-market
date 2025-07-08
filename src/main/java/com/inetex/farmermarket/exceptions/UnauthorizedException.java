package com.example.farmermarket.exceptions;

@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException() {}
	
	public UnauthorizedException(String message) {
		super(message);
	}
}
