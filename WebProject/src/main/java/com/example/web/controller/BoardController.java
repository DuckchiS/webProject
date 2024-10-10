package com.example.web.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
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
	
	@PostMapping(value = "/write", consumes = "multipart/form-data")
	public RedirectView write(@RequestParam("b_title") String title,
	                           @RequestParam("b_content") String content,
	                           @RequestParam("b_category") String category,
	                           @RequestParam(value = "b_image", required = false) MultipartFile file,
	                           Principal principal) {
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

	    // 이미지 파일 처리
	    if (file != null && !file.isEmpty()) {
	        try {
	            dto.setB_image(file.getBytes());  // 파일이 있을 경우 바이트 배열로 변환하여 설정
	        } catch (IOException e) {
	            log.error("이미지 파일 변환 중 오류 발생: " + e.getMessage());
	            // 여기서 필요에 따라 예외 처리 추가 가능
	        }
	    } else {
	        dto.setB_image(null); // 이미지가 제공되지 않을 경우 null 설정
	    }

	    // 게시글 작성 서비스 호출
	    service.write(dto);

	    // 리스트로 리다이렉트
	    return new RedirectView("/board/list?category=" + category);
	}

	@GetMapping({"/read", "/modify"})
	public void read(@RequestParam("b_no") long b_no, 
	                 @RequestParam(value = "category", required = false) String category,
	                 @RequestParam(value = "page", defaultValue = "1") int page, 
	                 @RequestParam(value = "replySubmitted", required = false) Boolean replySubmitted,
	                 Model model, 
	                 HttpSession session, 
	                 Principal principal) {

	    // 게시글 조회 기록 관리: 게시글 번호로 세션 키 설정
	    String sessionKey = "viewed_" + b_no;
	    String pageKey = "page_" + b_no;

	    // 현재 시간을 가져옴
	    long currentTime = System.currentTimeMillis();
	    long viewTimeout = 1 * 1000; // 1초 타임아웃

	    // 세션에 저장된 게시글 조회 시간을 확인
	    Long lastViewTime = (Long) session.getAttribute(sessionKey);

	    // 게시글 조회
	    BoardDto board;

	    // 댓글 작성 후 조회하거나 댓글 페이지 이동 시 조회수를 증가시키지 않음
	    boolean isReplyOrPagingAction = (replySubmitted != null && replySubmitted) || (session.getAttribute(pageKey) != null && (int) session.getAttribute(pageKey) != page);

	    // 조회수를 증가시키는 조건: 조회 시간이 없거나, 마지막 조회 이후 일정 시간이 지난 경우 && 댓글 또는 페이징 동작이 아닌 경우
	    if ((lastViewTime == null || (currentTime - lastViewTime) > viewTimeout) && !isReplyOrPagingAction) {
	        // 조회수 증가
	        board = service.read(b_no, true);
	        session.setAttribute(sessionKey, currentTime);  // 현재 시간을 세션에 저장
	    } else {
	        // 조회수 증가 없음
	        board = service.read(b_no, false);
	    }

	    // 댓글 페이지 이동 처리
	    session.setAttribute(pageKey, page);  // 현재 댓글 페이지를 세션에 기록

	    // 댓글 처리 부분
	    int repliesPerPage = 5;  // 페이지당 댓글 수
	    List<ReplyDto> replies = replyService.getRepliesByBoardId(b_no, page, repliesPerPage);
	    int totalReplyCount = replyService.getReplyCountByBoardId(b_no);
	    int totalPages = (int) Math.ceil((double) totalReplyCount / repliesPerPage);

	    // 로그인 사용자 정보 설정
	    if (principal != null) {
	        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        model.addAttribute("currentUsername", userDetails.getNickname());
	    } else {
	        model.addAttribute("currentUsername", "Guest");
	    }

	    model.addAttribute("read", board);          // 게시글 데이터
	    model.addAttribute("replies", replies);     // 댓글 목록
	    model.addAttribute("category", category);   // 카테고리
	    model.addAttribute("totalPages", totalPages);  // 총 페이지 수
	    model.addAttribute("currentPage", page);    // 현재 페이지
	}
	
	@PostMapping("/modify")
	public String modifyProcess(
	        BoardDto dto, 
	        @RequestParam(value = "b_image", required = false) MultipartFile file,
	        @RequestParam(value = "category", required = false) String category,
	        RedirectAttributes redirectAttributes) { // RedirectAttributes 추가

	    // 게시글 번호와 내용을 필수로 체크
	    if (dto.getB_no() <= 0) {
	        redirectAttributes.addFlashAttribute("error", "게시글 번호가 유효하지 않습니다.");
	        return "redirect:/board/modify?category=" + category;
	    }
	    
	    if (dto.getB_content() == null || dto.getB_content().trim().isEmpty()) {
	        redirectAttributes.addFlashAttribute("error", "게시글 내용이 비어 있습니다.");
	        return "redirect:/board/modify?category=" + category;
	    }

	    if (file != null && !file.isEmpty()) {
	        log.info("File received: " + file.getOriginalFilename());
	        try {
	            dto.setB_image(file.getBytes());
	            log.info("File converted to bytes successfully.");
	        } catch (IOException e) {
	            log.error("Error converting file to bytes: " + e.getMessage());
	            redirectAttributes.addFlashAttribute("error", "이미지 업로드 중 오류가 발생했습니다. 다시 시도해 주세요.");
	            return "redirect:/board/modify?category=" + category;
	        }
	    } else {
	        log.warn("No file was uploaded.");
	        dto.setB_image(null); // 파일이 없는 경우 null 설정
	    }

	    // 게시글 수정 서비스 호출
	    service.modify(dto);

	    // 수정 후 리다이렉트
	    return category != null ? "redirect:/board/list?category=" + category : "redirect:/board/list";
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
	public RedirectView replyProcess(@RequestParam("r_content") String content, 
	                                 @RequestParam("b_no") int boardId, 
	                                 Principal principal) {
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
	    
	    // 댓글 생성 서비스 호출
	    replyService.createReply(replyDto);

	    // 게시글 상세 페이지로 리다이렉트할 때 replySubmitted 파라미터 추가
	    return new RedirectView("/board/read?b_no=" + boardId + "&replySubmitted=true");
	}
	
	@GetMapping("/latter")
	public void latter() {
		
	}
	
	@PostMapping("/latter")
	public String Latter() {
		return "";
	}
}
