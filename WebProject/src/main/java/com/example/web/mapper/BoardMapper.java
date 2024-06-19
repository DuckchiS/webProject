package com.example.web.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.web.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public int getCount();
	public int getSearchCount(String word);
	public ArrayList<BoardDto> getList(int limitIndex);
	public ArrayList<BoardDto> getSearchList(int limitIndex, String word);
	public BoardDto read(long no);
	public void del(long no);
	public String write(BoardDto dto);
	public void modify(BoardDto dto);
}
