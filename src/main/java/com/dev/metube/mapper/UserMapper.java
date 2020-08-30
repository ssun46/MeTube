package com.dev.metube.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dev.metube.model.UserInfo;
import com.dev.metube.model.UserRoles;
import com.dev.metube.model.UserSearch;

@Mapper
public interface UserMapper {
	public int selectUserCount(String userId);
	public UserInfo selectUserInfo(String userId);
	public UserRoles selectRolesByUserId(String userId);
	public int insertUser(UserSearch search);
}
