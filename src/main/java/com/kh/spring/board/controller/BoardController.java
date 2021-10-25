package com.kh.spring.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.common.util.FileUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private BoardService boardService;
	
	@GetMapping("board-form")
	public void boardForm() {}
	
	@PostMapping("upload")
	public String uploadBoard(Board board
				, List<MultipartFile> files) {
		
		logger.debug(board.toString());
		FileUtil fileUtil = new FileUtil();
		for (MultipartFile multipartFile : files) {
			fileUtil.fileUpload(multipartFile);
		}
		
		return "redirect:/";
	}
	
	
}
