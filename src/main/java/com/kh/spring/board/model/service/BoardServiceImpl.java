package com.kh.spring.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.common.util.FileDTO;
import com.kh.spring.common.util.FileUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private final BoardRepository boardRepository;
	
	public void insertBoard(List<MultipartFile> mfs, Board board) {
		FileUtil fileUtil = new FileUtil();
		logger.debug(board.getUserId());
		boardRepository.insertBoard(board);
		for (MultipartFile multipartFile : mfs) {
			if(!multipartFile.isEmpty()) {
				boardRepository.insertFileInfo(fileUtil.fileUpload(multipartFile));
			}
			
		}
	}

	
	public Map<String,Object> selectBoardByIdx(String bdIdx) {
		Map<String,Object> res = new HashMap<String, Object>();
		
		Board board = boardRepository.selectBoardByIdx(bdIdx);
		List<FileDTO> files = boardRepository.selectFilesByIdx(bdIdx);
		
		res.put("board", board);
		res.put("files", files);
		
		return Map.of("board",board,"files",files);
	}
	
	
	
	
}
