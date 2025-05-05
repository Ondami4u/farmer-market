package com.example.farmermarket.config;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import com.example.farmermarket.farmer.Farmer;
import com.example.farmermarket.farmer.FarmerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final FarmerRepository farmerRepository;
	
	public CustomUserDetailsService(FarmerRepository farmerRepository) {
		this.farmerRepository = farmerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Farmer farmer = farmerRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
		List<GrantedAuthority> authorities = farmer.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
				.map(granted -> (GrantedAuthority) granted).toList();
		return new User(farmer.getEmail(), farmer.getPassword(), authorities);
	}

}
