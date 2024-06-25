package com.example.web.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.web.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public int getCount();
	public int getSearchCount(@Param("word") String word);
	public ArrayList<BoardDto> getList(int limitIndex);
	public ArrayList<BoardDto> getSearchList(@Param("limitIndex") int limitIndex, @Param("word") String word);
	public BoardDto read(long no);
	public void del(long no);
	public void replyDel(long no);
	public void write(BoardDto dto);
	public void modify(BoardDto dto);
}
