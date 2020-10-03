package com.dev.metube.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dev.metube.model.User;
import com.dev.metube.type.EnumUserStatus;
import com.dev.metube.type.EnumUserType;

@Mapper
public interface UserMapper {
	public int insert(User search);
	public User selectByUsername(String username);
	public boolean selectUserExist(String username);
}
