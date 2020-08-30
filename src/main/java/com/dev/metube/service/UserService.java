package com.dev.metube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.metube.mapper.UserMapper;
import com.dev.metube.model.UserInfo;
import com.dev.metube.model.UserSearch;
import com.dev.metube.util.PasswordEncode;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	public int getUserCountById(String userId) {
		return userMapper.selectUserCount(userId);
	}
	
	public UserInfo getUserWithRolesById(String userId) {
		UserInfo user = userMapper.selectUserInfo(userId);
		user.setRoles(userMapper.selectRolesByUserId(userId));
		return user;
	}
	
	public int setUser(UserSearch search) {
		PasswordEncode encoder = new PasswordEncode();
		String password = search.getPassword();
		String encoded = encoder.passwordEncoder("SHA-256").encode(password);
		search.setPassword(encoded);
		return userMapper.insertUser(search);
	}
}
