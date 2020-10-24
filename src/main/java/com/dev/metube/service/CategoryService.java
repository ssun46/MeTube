package com.dev.metube.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.metube.mapper.CategoryMapper;

@Service
public class CategoryService {
	
	@Autowired
	CategoryMapper categoryMapper;
	
	public Map<String, Object> getCategoryList() {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("data", categoryMapper.selectAll());
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			map.put("result", false);
			map.put("msg", "콘텐츠 카데고리 조회중 오류가 발생했습니다.");
			return map;
		}
		map.put("result", true);
		return map;
	}
}
