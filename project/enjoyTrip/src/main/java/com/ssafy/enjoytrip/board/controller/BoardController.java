package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.dto.BoardDto;
import com.ssafy.enjoytrip.board.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<?> getBoardList() {
        List<BoardDto> boardList = boardService.getBoardList();
        return new ResponseEntity<List<BoardDto>>(boardList, HttpStatus.OK);
    }
}
