package com.dev.metube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.metube.mapper.UserMapper;
import com.dev.metube.model.User;
import com.dev.metube.type.EnumUserStatus;
import com.dev.metube.type.EnumUserType;
import com.dev.metube.util.PasswordEncode;

@Service("UserService")
public class UserService {
	
	@Autowired
	UserMapper userMapper;

	private PasswordEncode encoder = new PasswordEncode();
	
	public int createUser(User user) {
		try {
			String encodedPassword = encoder.passwordEncoder("sha256").encode(user.getPassword());
			user.setPassword(encodedPassword);
		} catch (Exception e) {
			System.out.printf(e.getMessage(), e);
		}
		return userMapper.insert(user);
	}
	
	public User getLoginUserByUsername(String username) {
		return userMapper.selectLoginUserByUsername(username);
	}
	
	public User getUserByUsername(String username) {
		return userMapper.selectWithoutPasswordByUsername(username);
	}
	
	public boolean checkUserExist(String username) {
		return userMapper.selectUserExist(username);
	}
	
	public String getThumbnailPath(Integer id) {
		return userMapper.selectProfilePath(id);
	}
	
	public int updateProfileThumbnail(User user) {
		return userMapper.profilePathUpdate(user);
	}
}
