package com.example.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.web.dto.BoardDto;
import com.example.web.mapper.BoardMapper;

import jakarta.transaction.Transactional;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BoardServiceImpl implements BoardService{
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Override
	public Model getList(Model m, int currentPage) {
		return getListorSearch(m, currentPage, null);
	}
	
	@Override
	public Model getSearchList(Model m, int currentPage, String word) {
		return getListorSearch(m, currentPage, word);
	}
	
	private Model getListorSearch(Model m, int currentPage, String word) {
	    int listCountPerPage = 5;    // 페이지당 보여줄 글 수
	    int pagesPerBlock = 3;       // 블럭 당 페이지 수
	    int currentBlock = 1;        // 현재 페이지 블럭
	    int blockStartPage = 1;      // 블럭 시작 페이지
	    int blockEndPage = 1;        // 블럭 끝 페이지
	    int blockCount = 1;          // 블럭 총 수
	    int prevPage = 1;            // 이전 블럭 링크 클릭에 걸 페이지
	    int nextPage = 1;            // 다음 블럭 링크 클릭에 걸 페이지

	    int limitIndex = (currentPage - 1) * listCountPerPage;
	    List<BoardDto> list;
	    int count;
	    
	    if (word == null || word.isEmpty()) {
	        list = mapper.getList(limitIndex);
	        count = mapper.getCount();
	    } else {
	        list = mapper.getSearchList(limitIndex, word);
	        count = mapper.getSearchCount(word);
	    }
	    
	    m.addAttribute("list", list);
	    m.addAttribute("count", count);

	    // 총 페이지 수 구하기.
	    int totalPageCount = 0;
	    totalPageCount = (int) Math.ceil((double) count / listCountPerPage);
	    log.info("==== 방명록 ==== : 총 게시글 수는 " + count);
	    log.info("==== 방명록 ==== : 총 페이지 수는 " + totalPageCount);
	    m.addAttribute("totalPageCount", totalPageCount);
	    m.addAttribute("pagesPerBlock", pagesPerBlock);

	    blockCount = (int) Math.ceil((double) totalPageCount / pagesPerBlock);
	    m.addAttribute("blockCount", blockCount);

	    currentBlock = (int) Math.ceil((double) currentPage / pagesPerBlock);
	    m.addAttribute("currentBlock", currentBlock);

	    blockStartPage = (currentBlock - 1) * pagesPerBlock + 1;
	    blockEndPage = currentBlock * pagesPerBlock;
	    if (blockEndPage > totalPageCount) {
	        blockEndPage = totalPageCount;
	    }
	    m.addAttribute("blockStartPage", blockStartPage);
	    m.addAttribute("blockEndPage", blockEndPage);

	    if (currentBlock > 1) {
	        m.addAttribute("hasBlockPrev", true);
	        prevPage = (currentBlock - 1) * pagesPerBlock;
	        m.addAttribute("prevPage", prevPage);
	    }

	    if (currentBlock < blockCount) {
	        m.addAttribute("hasBlockNext", true);
	        nextPage = currentBlock * pagesPerBlock + 1;
	        m.addAttribute("nextPage", nextPage);
	    }
	    return m;
	}
	
	@Override
	public BoardDto read(long no) {
		return mapper.read(no);
	}
	
	
	@Override
	public void del(long no) {
		mapper.replyDel(no);
		mapper.del(no);
	}
	
	@Transactional
	@Override
	public void write(BoardDto dto) {
		mapper.write(dto);
	}
	
	@Override
	public void modify(BoardDto dto) {
		mapper.modify(dto);
	}
}
