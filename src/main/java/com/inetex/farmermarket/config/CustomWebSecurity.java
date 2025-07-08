package com.example.farmermarket.config;

import org.springframework.stereotype.Component;

import com.example.farmermarket.product.ProductRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomWebSecurity {

    private final ProductRepository productRepository;

    public boolean checkProductAuthor(String productIdStr, String email) {
        Long productId = Long.parseLong(productIdStr);
        return productRepository.findById(productId)
            .map(product -> product.getFarmer().getEmail().equals(email))
            .orElse(false);
    }
}
