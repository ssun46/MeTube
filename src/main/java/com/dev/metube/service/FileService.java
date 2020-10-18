package com.dev.metube.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.metube.model.LoginUserDetails;
import com.dev.metube.model.User;

@Service
public class FileService {
	public static String profileUploadPath = "C:\\Dev";

	@Autowired
	UserService userService;
	
	public String uploadProfileImage(MultipartFile file) {
		if(file == null) {
			return null;
		}
		
		LoginUserDetails userDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			Path uploadPath = Paths.get(profileUploadPath + File.separator + "profile" + File.separator + userDetails.getNo());
			Path thumbnailPath = Paths.get(File.separator + userDetails.getNo() + File.separator + file.getOriginalFilename());
			if(Files.notExists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			Path filePath = Paths.get(uploadPath.toString() + File.separator + file.getOriginalFilename());
			Files.write(filePath, file.getBytes());
			User user = new User();
			user.setId(userDetails.getNo());
			user.setThumbnail_path(thumbnailPath.toString());
			userService.updateProfileThumbnail(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return String.valueOf(userDetails.getNo());
	}
	
	public byte[] getProfileThumbnail(Integer id) {
		if(id == null) {
			return null;
		}
		
		try {
			String thumbnailPath = userService.getThumbnailPath(id);
			Path thumbnailFullPath = Paths.get(profileUploadPath + File.separator + "profile" + File.separator + thumbnailPath);
			File file = new File(thumbnailFullPath.toString());
			if(file != null && file.exists()) {
				byte[] imageBytes = Files.readAllBytes(file.toPath());
				return imageBytes;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
