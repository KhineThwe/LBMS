//package com.jp.library.service;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.jp.library.entity.User;
//import com.jp.library.repository.UserRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//	
//	
//
//	@Autowired
//	UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
//		User user = userRepository.getUserByEmail(usernameOrEmail);
//		System.out.println(usernameOrEmail);
////		if (user == null) {
////			System.out.println(usernameOrEmail);
////			throw new UsernameNotFoundException("User not found");
////		}
//		
//		Set<GrantedAuthority> authorities = user.getRoles().stream()
//				.map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
//		return null;
////		return new org.springframework.security.core.userdetails.User(usernameOrEmail, user.getPassword(), authorities);
//	}
//}
