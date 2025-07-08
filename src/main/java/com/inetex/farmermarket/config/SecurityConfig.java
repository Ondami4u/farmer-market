package com.inetex.farmermarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.inetex.farmermarket.security.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

/**
 * Enable method-level authorization using @PreAuthorize via @EnableWebSeciroty
 */
//@EnableMethodSecurity
public class SecurityConfig {

    private final CustomWebSecurity webSecurity;
    private final JwtAuthFilter jwtAuthFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/*/register", "/api/login/**").permitAll()
                
                .requestMatchers("/api/products").permitAll()
                
                .requestMatchers(HttpMethod.POST, "/api/products/create").hasRole("FARMER")
                .requestMatchers(HttpMethod.POST, "/api/products/*/buy").hasRole("CLIENT")
                .requestMatchers(HttpMethod.POST, "/api/products/*/update-price").hasRole("FARMER")
                .requestMatchers(HttpMethod.PUT, "/api/products/*/update-description").hasRole("FARMER")
                  
                .requestMatchers(HttpMethod.DELETE, "/api/products/*").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    	return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}
