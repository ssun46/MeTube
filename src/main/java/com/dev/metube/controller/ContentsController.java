package com.dev.metube.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dev.metube.model.Video;
import com.dev.metube.service.CategoryService;
import com.dev.metube.service.ContentsService;
import com.dev.metube.service.FileService;

@Controller
@RequestMapping("/contents")
public class ContentsController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ContentsService contentsService;
		
	@Autowired
	FileService fileService;
	
	@GetMapping("/{id}/detail")
	public String contentsDetail(@PathVariable("id") Integer id, Model model){
		model.addAttribute("contents", contentsService.getContentsVideo(id));
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
	
	@PostMapping("/list")
	public @ResponseBody Map<String, Object> getContentsListByGoPublic(@RequestParam("keyword") String keyword, @RequestParam("currentIndex") Integer currentIndex) {
		return contentsService.getContentsListByGoPublic(keyword, currentIndex);
	}
	
	@GetMapping("/{id}/thumbnail")
	public @ResponseBody byte[] getContentsThumbnail(@PathVariable("id") Integer id) {
		return fileService.getContentsThumbnail(id);
	}
}
