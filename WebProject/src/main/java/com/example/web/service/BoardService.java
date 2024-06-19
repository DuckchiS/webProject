package com.example.web.service;

import org.springframework.ui.Model;

import com.example.web.dto.BoardDto;


public interface BoardService {
	public Model getList(Model m, int currentPage);
	public Model getSearchList(Model m, int currentPage, String word);
	public BoardDto read(long no);
	public void del(long no);
	public String write(BoardDto dto);
	public void modify(BoardDto dto);
}
