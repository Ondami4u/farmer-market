package com.example.farmermarket.config;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.farmermarket.client.Client;
import com.example.farmermarket.client.ClientRepository;
import com.example.farmermarket.farmer.Farmer;
import com.example.farmermarket.farmer.FarmerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final FarmerRepository farmerRepository;
	private final ClientRepository clientRepository;
	
	public CustomUserDetailsService(FarmerRepository farmerRepository, ClientRepository clientRepository) {
		this.farmerRepository = farmerRepository;
		this.clientRepository = clientRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Farmer> farmerOpt = farmerRepository.findByEmail(username);
		if(farmerOpt.isPresent()) {
			Farmer farmer = farmerOpt.get();
			List<SimpleGrantedAuthority> authorities = farmer.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
					.toList();
			return new User(farmer.getEmail(), farmer.getPassword(), authorities);
		}
		
		Optional<Client> clientOpt = clientRepository.findByEmail(username);
		if(clientOpt.isPresent()) {
			Client client = clientOpt.get();
			List<SimpleGrantedAuthority> authorities = client.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
					.toList();
			return new User(client.getEmail(), client.getPassword(), authorities);
		}
		
		throw new UsernameNotFoundException("User not found " + username);
	}

}
