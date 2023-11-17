package com.ssafy.enjoytrip.board.model.mapper;

import com.ssafy.enjoytrip.board.dto.AnswerDto;

public interface AnswerMapper {
    AnswerDto getAnswerByQuestionId(int questionId);
    void postAnswer(AnswerDto answerDto);
    void modifyAnswer(AnswerDto answerDto);
    void deleteAnswer(int questionId);
    void increaseHit(int questionId);
}
