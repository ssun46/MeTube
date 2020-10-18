package com.dev.metube.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
	public @ResponseBody String uploadProfileImage(@RequestParam("profile_filepond") MultipartFile file) throws IOException {
		return fileService.uploadProfileImage(file);
	}
	
	@GetMapping("/profile/thumbnail/{id}")
	public @ResponseBody byte[] getProfileThumbnailImage(@PathVariable("id") Integer id) throws UnsupportedEncodingException {
		return fileService.getProfileThumbnail(id);
	}
}
