package com.jp.library;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class Common {
	public void setUser(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.getPrincipal().equals("anonymousUser")) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			String username = userDetails.getEmail();
			model.addAttribute("username", username);
		} else {
			model.addAttribute("username", "Anonymous User");
		}
	}
}
