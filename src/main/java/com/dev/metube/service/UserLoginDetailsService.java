package com.dev.metube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.metube.model.LoginUserDetails;
import com.dev.metube.model.User;

@Service
public class UserLoginDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public LoginUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserInfoByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("login failed");
		}
		System.out.println(user.toString());
		return new LoginUserDetails(user);
	}
}
