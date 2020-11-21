package com.dev.metube.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dev.metube.model.Vote;

@Mapper
public interface VoteMapper {
	public int insert(Vote vote);
	public int delete(Vote vote);
	public boolean selectExist(Vote vote);
}
