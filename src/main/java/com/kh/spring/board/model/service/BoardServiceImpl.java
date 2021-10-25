package com.kh.spring.board.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.common.util.FileUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private final BoardRepository boardRepository;
	
	@Transactional
	public void insertBoard(List<MultipartFile> mfs, Board board) {
		FileUtil fileUtil = new FileUtil();
		logger.debug(board.getUserId());
		boardRepository.insertBoard(board);
		for (MultipartFile multipartFile : mfs) {
			boardRepository.insertFileInfo(fileUtil.fileUpload(multipartFile));
		}
	}
	
	
	
	
}
