package com.ssafy.enjoytrip.board.dto;

import lombok.Data;

@Data
public class QuestionDto {
    int questionId;
    int BoardId;
    int userNo;
    String questionTitle;
    String questionContent;
    String questionPostDate;
    int questionSecret;
    int questionHits;
}
