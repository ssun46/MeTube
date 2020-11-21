package com.dev.metube.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.metube.mapper.VideoMapper;
import com.dev.metube.model.LoginUserDetails;
import com.dev.metube.model.User;
import com.dev.metube.model.Video;

@Service
public class FileService {
	public static String profileUploadPath = "C:\\Dev";
	public static String contentsUploadPath = "C:\\Dev\\contents";

	@Autowired
	UserService userService;
	
	@Autowired
	ContentsService contentsService;
	
	@Autowired
	VideoMapper videoMapper;
	
	protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected Video video = new Video();
	
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
	
	public String uploadContent(MultipartFile file) {
		String resultId = "";
		Integer insertedId = 0;
		String tempName = "temp";
		
		if(file == null) {
			return null;
		}

		long timestamp = System.currentTimeMillis();
		LoginUserDetails userDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			Path uploadPath = Paths.get(contentsUploadPath + File.separator + userDetails.getNo() + File.separator + timestamp);
			Path contentPath = Paths.get(File.separator + userDetails.getNo() + File.separator + timestamp + File.separator + file.getOriginalFilename());
			if(Files.notExists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			Path filePath = Paths.get(uploadPath.toString() + File.separator + file.getOriginalFilename());
			Files.write(filePath, file.getBytes());
			tempName = format.format(new Date(timestamp));
			video.setName(tempName);
			video.setPath(contentPath.toString());
			insertedId = contentsService.insertFile(file, video);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		resultId = String.valueOf(insertedId) + "," + tempName;
		return resultId;
	}
	
	public String uploadThumbnail(Integer id, MultipartFile file) throws IOException {
		String videoPath = contentsService.getPath(id);
		if(videoPath == null) {
			return "fail";
		}
		
		String contentPath = videoPath.substring(0, videoPath.lastIndexOf(File.separator));
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		Path uploadPath = Paths.get(contentsUploadPath + contentPath + File.separator + "thumbnail" + extension);
		Files.write(uploadPath, file.getBytes());
		return contentPath + File.separator + "thumbnail" + extension;
	}
	
	public byte[] getContentsThumbnail(Integer id) {
		if(id == null) {
			return null;
		}
		
		try {
			String thumbnailPath = videoMapper.getThumbnailPath(id);
			Path thumbnailFullPath = Paths.get(contentsUploadPath + File.separator + thumbnailPath);
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
	
	public FileSystemResource getVideo(Integer id) {
		if(id == null) {
			return null;
		}
		
		try {
			String videoPath = videoMapper.selectPathById(id);
			Path videoFullPath = Paths.get(contentsUploadPath + File.separator + videoPath);
			return new FileSystemResource(videoFullPath.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
