package com.example.web.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.web.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public int getCount(@Param("category")String category);
	public int getSearchCount(@Param("word") String word, @Param("category")String category);
	public ArrayList<BoardDto> getList(@Param("limitIndex")int limitIndex, @Param("category")String category);
	public ArrayList<BoardDto> getSearchList(@Param("limitIndex") int limitIndex, @Param("word") String word, @Param("category")String category);
	public BoardDto read(long no);
	public void del(long no);
	public void replyDel(long no);
	public void write(BoardDto dto);
	public void modify(BoardDto dto);
	public void hit(long no);
}
