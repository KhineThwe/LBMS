package com.jp.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.jp.library.CustomUserDetails;
import com.jp.library.entity.User;
import com.jp.library.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.getUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new CustomUserDetails(user);
	}

}
