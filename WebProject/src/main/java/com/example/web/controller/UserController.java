package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web.dto.UserDto;
import com.example.web.service.UserService;

import jakarta.servlet.http.HttpSession;
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
	public String loginProcess(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
	    if (username.equals("사용자 입력 아이디") && password.equals("사용자 입력 비밀번호")) {
	        // 로그인 성공
	        session.setAttribute("username", username);
	        return "redirect:/board/list"; 
	    } else {
	        // 로그인 실패
	        model.addAttribute("error", "아이디나 비밀번호가 잘못되었습니다.");
	        return "member/login"; 
	    }
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
