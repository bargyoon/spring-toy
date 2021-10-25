package com.kh.spring.board.model.service;

import org.springframework.stereotype.Service;

import com.kh.spring.board.model.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private BoardRepository boardRepository;
}
