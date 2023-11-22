package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.CommentDto;
import com.ssafy.enjoytrip.board.model.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDto> getAllBoardsCommentList() {
        return commentMapper.getAllBoardsCommentList();
    }

    @Override
    public CommentDto getComment(int commentId) {
        return commentMapper.getComment(commentId);
    }

    @Override
    public void deleteComment(int commentId) {
        commentMapper.deleteComment(commentId);
    }

    @Override
    public List<CommentDto> getBoardCommentsList(int boardId) {
        return commentMapper.getBoardCommentsList(boardId);
    }

    @Override
    public List<CommentDto> getArticleCommentsList(int boardId, int articleId) {
        return commentMapper.getArticleCommentsList(boardId, articleId);
    }

    @Override
    public void postComment(CommentDto commentDto) {
        int order = 0;
        int depth = 0;
        int rootId = commentDto.getArticleId();

        if(commentDto.getParentBoardId() == 6){
            CommentDto parentComment = commentMapper.getComment(commentDto.getArticleId());
            depth = parentComment.getDepth() + 1;
            rootId = parentComment.getRootId();
        }
        String orderStr = commentMapper.findMaxOrder(commentDto);
        System.out.println(orderStr);

        if(!(orderStr == null)){
            order = Integer.parseInt(orderStr) + 1;
        }

        commentDto.setRootId(rootId);
        commentDto.setDepth(depth);
        commentDto.setOrder(order);
        commentMapper.postComment(commentDto);
    }

    @Override
    public void modifyComment(CommentDto commentDto) {
        commentMapper.modifyComment(commentDto);
    }

    @Override
    public void deleteArticleComments(int boardId, int articleId) {
        commentMapper.deleteArticleComments(boardId,articleId);
    }
}
