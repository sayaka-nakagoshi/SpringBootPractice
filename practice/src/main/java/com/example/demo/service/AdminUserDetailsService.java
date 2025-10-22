package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.AdminRepository;

//セキュリティ認証をするため、ユーザー情報と権限を設定する
@Service
public class AdminUserDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		return adminRepository.findByEmail(email)
				.map(user -> {
					return new org.springframework.security.core.userdetails.User(
							user.getEmail(),
							user.getPassword(),
							List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))

				);
				})
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
	}

}