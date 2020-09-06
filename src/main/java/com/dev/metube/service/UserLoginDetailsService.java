package com.dev.metube.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.metube.model.UserInfo;

@Service
public class UserLoginDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserInfo user = userService.getUserById(userId);
		return new User(user.getName(), user.getPassword(), getAuthorities(user));
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(UserInfo user) {
		String[] roleList = new String[1];
		roleList[0] = user.getUserRole();
		
		System.out.println("user role is " + roleList[0]);
		Collection<GrantedAuthority> authority = AuthorityUtils.createAuthorityList(roleList);
		return authority;
	}
}
