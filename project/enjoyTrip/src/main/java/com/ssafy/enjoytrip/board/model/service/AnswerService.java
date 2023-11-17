package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.AnswerDto;

public interface AnswerService {
    AnswerDto getAnswerByQuestionId(int questionId);
    void postAnswer(AnswerDto answerDto);
    void modifyAnswer(AnswerDto answerDto);
    void deleteAnswer(int questionId);
    void increaseHit(int questionId);
}
