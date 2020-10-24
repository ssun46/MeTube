package com.dev.metube.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dev.metube.model.Category;

@Mapper
public interface CategoryMapper {
	public List<Category> selectAll();
}
