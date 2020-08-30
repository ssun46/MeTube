package com.dev.metube.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.metube.model.UserSearch;
import com.dev.metube.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String contentList() {
		return "/contents/contentsList";
	}
	
	@GetMapping("/login")
	public String signin() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(UserSearch search) {
		String rtnMsg = "OK";
		int insertCnt = 0;
		
		try {
			insertCnt = userService.setUser(search);
			
			if(insertCnt < 1) {
				rtnMsg = "FAIL";
			}
		} catch(Exception e) {
			rtnMsg = "ERROR";
		}
		
		return rtnMsg;
	}
	
	@ResponseBody
	@PostMapping("/chkUserId")
	public boolean getUserCount(@RequestBody UserSearch search) {
		String userId = search.getId();
		System.out.println("A : " + userId);
		boolean rtnBool = userService.getUserCountById(userId) == 0 ? false : true;
		return rtnBool;
	}
}
