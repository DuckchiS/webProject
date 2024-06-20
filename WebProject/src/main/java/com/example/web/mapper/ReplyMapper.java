package com.example.web.mapper;

import java.util.List;

import com.example.web.dto.ReplyDto;

public interface ReplyMapper {
	void insertReply(ReplyDto reply);
	List<ReplyDto> getReplys(long b_no); 
}
