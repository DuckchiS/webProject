package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.web.dto.UserDto;
import com.example.web.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member/*")
@AllArgsConstructor
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/loginProc")
	public String loginProcess() {
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String signUp(){
		return "member/signup";
	}
	
	@PostMapping("/signupProc")
	public String signUpProcess(UserDto user) {
		userService.signUpProcess(user);
		return "redirect:/member/login";
	}
	
	@GetMapping("/mypage")
	public void myPage() {
		
	}
	
	@GetMapping("/latterbox")
	public void latterBox() {
		
	}
	
	@GetMapping("/readlatter")
	public void readLatter() {
		
	}
}
