package com.dev.metube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.metube.model.LoginUserDetails;
import com.dev.metube.model.User;
import com.dev.metube.service.UserService;

@Controller
@RequestMapping("/channel")
public class ChannelController {
	
	@Autowired
	UserService userService;

	@RequestMapping
	public String chennel(Model model) {
		LoginUserDetails userDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		return "channel/channel_menu";
	}

}
