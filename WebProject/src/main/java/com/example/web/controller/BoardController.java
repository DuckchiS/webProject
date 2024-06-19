package com.example.web.controller;

import java.net.URI;
import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import com.example.web.dto.BoardDto;
import com.example.web.model.CustomUserDetails;
import com.example.web.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	private BoardService service;
	
	@GetMapping("/list")
	public String list(@RequestParam(value="currentPage", defaultValue = "1") int currentPage, Model model) {
		model = service.getList(model, currentPage);
		return "board/list";
	}
	
	@GetMapping("/write")
	public String write(Principal principal) {
		if (principal == null) {
			// 사용자 인증이 되어 있지 않은 경우
			return "redirect:/login";
		}
		return "board/write";
	}
	
	@PostMapping(value = "/write", consumes = "application/x-www-form-urlencoded")
    public RedirectView write(@RequestParam("b_title") String title, @RequestParam("b_content") String content, Principal principal) {
        // Principal 객체를 통해 현재 로그인한 사용자의 정보를 가져옴
        String username = principal.getName();
        
        // 사용자 정보를 SecurityContext에서 가져옴
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nickname = userDetails.getNickname();

        // DTO에 값 설정
        BoardDto dto = new BoardDto();
        dto.setB_title(title);
        dto.setB_content(content);
        dto.setM_nickname(nickname);

        // 게시글 작성 서비스 호출
        service.write(dto);


     // 리스트로 리다이렉트
        return new RedirectView("/board/list");
    }
	
	@GetMapping({"/read", "/modify"})
	public void read(@RequestParam("b_no") long b_no, Model model) {
		model.addAttribute("read",service.read(b_no));
	}
	
	@PostMapping("/modify")
	public String modifyProcess(BoardDto dto) {
		service.modify(dto);
		return "redirect:/board/list";
	}
	
	@GetMapping("/del")
	public String del(@RequestParam("b_no") long bno) {
		service.del(bno);
		return "redirect:/board/list";
	}
	
	@GetMapping("/reply")
	public void reply() {
		
	}
	
	@GetMapping("/latter")
	public void latter() {
		
	}
	
	@PostMapping("/latter")
	public String Latter() {
		return "";
	}
}
