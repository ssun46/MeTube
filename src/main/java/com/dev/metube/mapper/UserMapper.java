package com.dev.metube.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dev.metube.model.User;

@Mapper
public interface UserMapper {
	public int insert(User user);
	public User selectLoginUserByUsername(@Param("username") String username);
	public User selectWithoutPasswordByUsername(@Param("username") String username);
	public boolean selectUserExist(@Param("username") String username);
	public String selectProfilePath(@Param("id") Integer id);
	public int profilePathUpdate(User user);
}
