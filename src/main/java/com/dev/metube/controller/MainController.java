package com.dev.metube.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.metube.model.UserInfo;
import com.dev.metube.model.UserSearch;
import com.dev.metube.service.UserLoginDetailsService;
import com.dev.metube.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String contentList() {
		return "/contents/contentsList";
	}
	
	@RequestMapping("/login")
	public String signinByGet() {
		System.out.println("Login controller Invorked");
		return "login";
	}
	
	@RequestMapping("/login/processing")
	public String signinByPost(@ModelAttribute UserInfo userInfo) {
		System.out.println("Login Processing controller Invorked");
		UserLoginDetailsService userDetails = (UserLoginDetailsService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails);
		return "login";
	}
	
	@GetMapping("/signup")
	public String signupByGet() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signupByPost(UserSearch search) {
		userService.setUser(search);
		return "redirect:/";
	}
	
	@ResponseBody
	@GetMapping("/chkUserId")
	public boolean getUserCount(@RequestParam HashMap<String, Object> param) {
		return userService.getUserCountById((String) param.get("id")) == 0 ? false : true;
	}
}
