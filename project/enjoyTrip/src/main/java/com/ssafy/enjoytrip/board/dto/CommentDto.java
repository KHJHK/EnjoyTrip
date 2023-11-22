package com.ssafy.enjoytrip.board.dto;

import lombok.Data;

@Data
public class CommentDto {
    int commentId;
    int boardId;
    int parentBoardId;
    int userNo;
    int articleId;
    String comment;
    String createDate;
    char deleteYN;
    int order;
    int depth;
    int rootId;
}
