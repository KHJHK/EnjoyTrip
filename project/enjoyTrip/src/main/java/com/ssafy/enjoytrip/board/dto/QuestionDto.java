package com.ssafy.enjoytrip.board.dto;

import lombok.Data;

import java.util.List;

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
    String userName;
    String nickname;
    AnswerDto answer;
}
