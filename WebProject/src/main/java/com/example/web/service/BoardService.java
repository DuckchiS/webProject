package com.example.web.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.web.dto.BoardDto;

@Service
public interface BoardService {
	public Model getList(Model m, int currentPage, String category);
	public Model getSearchList(Model m, int currentPage, String word, String category);
	public BoardDto read(long no);
	public void del(long no);
	public void write(BoardDto dto);
	public void modify(BoardDto dto);
}
