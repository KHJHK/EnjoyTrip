package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllBoardsCommentList();
    CommentDto getComment(int commentId);
    void deleteComment (int commentId);
    List<CommentDto> getBoardCommentsList(int boardId);
    List<CommentDto> getArticleCommentsList(int boardId, int articleId);
    void postComment(CommentDto commentDto);
    void modifyComment(CommentDto commentDto);
    void deleteArticleComments(int boardId, int articleId);
}
