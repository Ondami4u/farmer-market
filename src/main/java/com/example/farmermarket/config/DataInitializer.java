package com.example.farmermarket.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.farmermarket.security.Role;
import com.example.farmermarket.security.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        if (roleRepository.findByName("FARMER").isEmpty()) {
            Role farmerRole = new Role();
            farmerRole.setName("FARMER");
            roleRepository.save(farmerRole);
            System.out.println("Role FARMER created.");
        } else {
            System.out.println("Role FARMER already exists.");
        }
    }
}