package com.metro.Metro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metro.Metro.model.User;
import com.metro.Metro.repository.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserService implements UserDetailsService{

	private final UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmailId(username);
		
		if (user == null) {
			
			throw new UsernameNotFoundException("Bad Credentials...");
			
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>(); 

		authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
		
		UserDetails userdetails = org.springframework.security.core.userdetails.User.builder().
				authorities(authorities)
				.password(user.getPassword())
				.accountExpired(false)
				.accountLocked(false)
				.username(username)
				.build();
		
		
		return userdetails;
	}

}
