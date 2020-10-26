package com.dev.metube.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dev.metube.model.SearchBase;
import com.dev.metube.model.Video;

@Mapper
public interface VideoMapper {
	public int insertFile(Video video);
	public String selectPathById(@Param("id") Integer id);
	public Integer selectContentsCountByPublicYes(SearchBase search);
	public List<Video> selectCoutentsListByPublicYes(SearchBase search);
	public Video selectVideoById(@Param("id") Integer id);
	public int update(Video video);
	public String getThumbnailPath(@Param("id") Integer id);
}
