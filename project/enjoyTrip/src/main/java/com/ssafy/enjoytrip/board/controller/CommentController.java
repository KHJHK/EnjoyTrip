package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.dto.CommentDto;
import com.ssafy.enjoytrip.board.model.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> postComment(@RequestBody CommentDto comment){
        try {
            commentService.postComment(comment);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> getComment(@PathVariable int commentId){
        try{
            CommentDto comment = commentService.getComment(commentId);
            return new ResponseEntity<CommentDto>(comment, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBoardsCommentList(){
        try{
            List<CommentDto> commentList = commentService.getAllBoardsCommentList();
            return new ResponseEntity<List<CommentDto>>(commentList, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{boardId}/list")
    public ResponseEntity<?> getBoardCommentsList(@PathVariable int boardId){
        try{
            List<CommentDto> commentList = commentService.getBoardCommentsList(boardId);
            return new ResponseEntity<List<CommentDto>>(commentList, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{boardId}/{articleId}")
    public ResponseEntity<?> getArticleCommentsList(@PathVariable int boardId, @PathVariable int articleId){
        try{
            List<CommentDto> commentList = commentService.getArticleCommentsList(boardId, articleId);
            return new ResponseEntity<List<CommentDto>>(commentList, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> modifyComment(@RequestBody CommentDto commentDto){
        try{
            commentService.modifyComment(commentDto);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId){
        try{
            commentService.deleteComment(commentId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/{boardId}/{articleId}")
    public ResponseEntity<?> deleteArticleComments(@PathVariable int boardId, @PathVariable int articleId){
        try{
            commentService.deleteArticleComments(boardId, articleId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
