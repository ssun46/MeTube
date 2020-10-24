package com.dev.metube.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev.metube.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	FileService fileService;
	
	@PostMapping(value = "/upload/profile/image", consumes = {"multipart/form-data"})
	public @ResponseBody String uploadProfileImage(@RequestParam("profile") MultipartFile file) throws IOException {
		return fileService.uploadProfileImage(file);
	}
	
	@PostMapping(value = "/upload/video", consumes = {"multipart/form-data"})
	public @ResponseBody String uploadVideo(@RequestParam("video") MultipartFile file) throws IOException {
		return fileService.uploadContent(file);
	}
	
	@PostMapping(value = "/upload/video/thumbnail", consumes = {"multipart/form-data"})
	public @ResponseBody String uploadVideoThumbnail(@RequestParam("id") Integer id, @RequestParam("thumbnail") MultipartFile file) throws IOException {
		return fileService.uploadThumbnail(id, file);
	}
	
	@GetMapping("/profile/thumbnail/{id}")
	public @ResponseBody byte[] getProfileThumbnailImage(@PathVariable("id") Integer id) throws UnsupportedEncodingException {
		return fileService.getProfileThumbnail(id);
	}
}
