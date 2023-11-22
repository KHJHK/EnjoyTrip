package com.ssafy.enjoytrip.board.model.mapper;

import com.ssafy.enjoytrip.board.dto.CommentDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    List<CommentDto> getAllBoardsCommentList();
    CommentDto getComment(int commentId);
    void deleteComment (int commentId);
    List<CommentDto> getBoardCommentsList(int boardId);
    List<CommentDto> getArticleCommentsList(@Param("boardId") int boardId, @Param("articleId") int articleId);
    void postComment(CommentDto commentDto);
    void modifyComment(CommentDto commentDto);
    void deleteArticleComments(int boardId, int articleId);
    String findMaxOrder(CommentDto commentDto);
}
