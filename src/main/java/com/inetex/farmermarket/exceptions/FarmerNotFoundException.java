package com.example.farmermarket.exceptions;

@SuppressWarnings("serial")
public class FarmerNotFoundException extends RuntimeException {
    public FarmerNotFoundException() {}

    public FarmerNotFoundException(String message) {
        super(message);
    }
}
