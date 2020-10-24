package com.dev.metube.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dev.metube.model.Video;

@Mapper
public interface VideoMapper {
	public int insertFile(Video video);
	public String selectPathById(@Param("id") Integer id);
	public int update(Video video);
}
