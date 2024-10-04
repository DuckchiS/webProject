package com.example.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.web.dto.ReplyDto;
import com.example.web.mapper.ReplyMapper;

@Service
public class ReplyService {
	@Autowired
	private ReplyMapper replyMapper;
	
	@PreAuthorize("isAuthenticated()")
	public void createReply(ReplyDto reply) {
		replyMapper.insertReply(reply);
	}
	
    // 페이징 처리된 댓글 목록을 가져오는 메서드
    public List<ReplyDto> getRepliesByBoardId(long b_no, int page, int repliesPerPage) {
        int offset = (page - 1) * repliesPerPage;
        return replyMapper.getReplys(b_no, offset, repliesPerPage);
    }

    // 게시물의 댓글 개수를 가져오는 메서드
    public int getReplyCountByBoardId(long b_no) {
        return replyMapper.getReplyCountByBoardId(b_no);
    }
}
