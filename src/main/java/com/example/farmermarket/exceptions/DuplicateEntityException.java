package com.example.farmermarket.exceptions;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String message) {
        super(message);
    }
}