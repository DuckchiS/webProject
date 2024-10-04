package com.example.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.web.dto.ReplyDto;

public interface ReplyMapper {
	void insertReply(ReplyDto reply);
    // 페이징 처리를 위한 메서드
    List<ReplyDto> getReplys(@Param("b_no") long b_no, @Param("offset") int offset, @Param("limit") int limit);
    
    // 댓글 총 개수를 가져오는 메서드
    int getReplyCountByBoardId(long b_no);
}
