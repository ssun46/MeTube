package com.dev.metube.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.metube.model.Video;
import com.dev.metube.service.CategoryService;
import com.dev.metube.service.ContentsService;

@Controller
@RequestMapping("/contents")
public class ContentsController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ContentsService contentsService;
	
	@GetMapping("/{id}/detail")
	public String contentsDetail(@PathVariable("id") Integer id){
		System.out.println(id);
		return "contents/contents_detail";	
	}

	@GetMapping("/categories")
	public @ResponseBody Map<String, Object> getCategories() {
		return categoryService.getCategoryList();
	}
	
	@PostMapping("/saveContent")
	public @ResponseBody Map<String, Object> saveContent(Video video) {
		return contentsService.updateContent(video);
	}
}
