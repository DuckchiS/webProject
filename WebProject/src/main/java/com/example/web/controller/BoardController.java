package com.example.web.controller;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import com.example.web.dto.BoardDto;
import com.example.web.dto.ReplyDto;
import com.example.web.model.CustomUserDetails;
import com.example.web.service.BoardService;
import com.example.web.service.ReplyService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	private BoardService service;
	
	private ReplyService replyService;
	
	@GetMapping("/list")
    public String list(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(value = "category", defaultValue = "free") String category,
                       Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model = service.getSearchList(model, currentPage, keyword, category);
        } else {
            model = service.getList(model, currentPage, category);
        }
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
    public RedirectView write(@RequestParam("b_title") String title, @RequestParam("b_content") String content, @RequestParam("b_category") String category, Principal principal) {
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
        dto.setB_category(category);

        // 게시글 작성 서비스 호출
        service.write(dto);

        // 리스트로 리다이렉트
        return new RedirectView("/board/list?category="+category);
    }
	
	@GetMapping({"/read", "/modify"})
	public void read(@RequestParam("b_no") long b_no, 
	                 @RequestParam(value = "category", required = false) String category,
	                 @RequestParam(value = "page", defaultValue = "1") int page, 
	                 Model model, 
	                 HttpSession session, 
	                 Principal principal) {
	    // 세션에서 조회 기록을 확인
	    String sessionKey = "viewed_" + b_no;
	    
	    // 조회수 증가 로직: 세션에 해당 게시글 조회 기록이 없을 때만 조회수 증가
	    if (session.getAttribute(sessionKey) == null) {
	        // 조회수 증가
	        service.read(b_no);  // 조회수를 증가시키면서 게시글을 가져옴
	        // 조회한 기록을 세션에 저장
	        session.setAttribute(sessionKey, true);
	    } else {
	        // 조회수를 증가시키지 않고 게시글을 가져옴
	        BoardDto board = service.read(b_no);  // 조회수는 증가하지 않음
	        model.addAttribute("read", board);
	    }
	    
	    int repliesPerPage = 5; // 페이지당 댓글 수
	    List<ReplyDto> replies = replyService.getRepliesByBoardId(b_no, page, repliesPerPage);  // 페이징 처리된 댓글 목록 가져오기
	    int totalReplyCount = replyService.getReplyCountByBoardId(b_no);  // 댓글의 총 개수
	    int totalPages = (int) Math.ceil((double) totalReplyCount / repliesPerPage);  // 총 페이지 수 계산
	    System.out.println("==========================");
	    System.out.println(totalReplyCount);
	    System.out.println(totalPages);
	    System.out.println("==========================");
	    
	    // 로그인 사용자 정보 설정
	    if (principal != null) {
	        String username = principal.getName();
	        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String nickname = userDetails.getNickname();
	        model.addAttribute("currentUsername", nickname);
	    } else {
	        model.addAttribute("currentUsername", "Guest"); // 로그인하지 않은 경우
	    }
	    
	    model.addAttribute("replies", replies);
	    model.addAttribute("category", category);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("currentPage", page);
	}

	
	@PostMapping("/modify")
	public String modifyProcess(BoardDto dto, @RequestParam(value = "category", required = false) String category) {
	    service.modify(dto);
	    if (category != null) {
	        return "redirect:/board/list?category=" + category;
	    } else {
	        return "redirect:/board/list";
	    }
	}
	@GetMapping("/del")
	public String del(@RequestParam("b_no") long bno, @RequestParam(value = "category", required = false) String category) {
	    service.del(bno);
	    if (category != null) {
	        return "redirect:/board/list?category=" + category;
	    } else {
	        return "redirect:/board/list";
	    }
	}
	
	@PostMapping("/reply")
	public RedirectView replyProcess(@RequestParam("r_content") String content, @RequestParam("b_no") int boardId, Principal principal) {
	    // Principal 객체를 통해 현재 로그인한 사용자의 정보를 가져옴
        String username = principal.getName();
        
        // 사용자 정보를 SecurityContext에서 가져옴
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nickname = userDetails.getNickname();

        // 댓글 DTO에 값 설정
        ReplyDto replyDto = new ReplyDto();
        replyDto.setR_content(content);
        replyDto.setM_nickname(nickname);
        replyDto.setB_no(boardId);
        
        replyService.createReply(replyDto);

        // 게시글 상세 페이지로 리다이렉트
        return new RedirectView("/board/read?b_no=" + boardId);
	}
	
	@GetMapping("/latter")
	public void latter() {
		
	}
	
	@PostMapping("/latter")
	public String Latter() {
		return "";
	}
}
