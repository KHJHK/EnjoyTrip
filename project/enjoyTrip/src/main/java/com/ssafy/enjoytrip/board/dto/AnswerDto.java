package com.ssafy.enjoytrip.board.dto;

import lombok.Data;

@Data
public class AnswerDto {
    int questionId;
    int boardId;
    int userNo;
    String answerTitle;
    String answerContent;
    String answerPostDate;
    String answerHits;
}
