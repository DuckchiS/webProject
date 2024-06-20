package com.example.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.dto.ReplyDto;
import com.example.web.mapper.ReplyMapper;

@Service
public class ReplyService {
	@Autowired
	private ReplyMapper replyMapper;
	
	public void createReply(ReplyDto reply) {
		replyMapper.insertReply(reply);
	}
	
	public List<ReplyDto> getRepliesByBoardId(long b_no) {
		return replyMapper.getReplys(b_no);
	}
}
