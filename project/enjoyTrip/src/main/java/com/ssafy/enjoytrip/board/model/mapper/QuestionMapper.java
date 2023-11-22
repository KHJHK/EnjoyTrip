package com.ssafy.enjoytrip.board.model.mapper;

import com.ssafy.enjoytrip.board.dto.AnswerDto;
import com.ssafy.enjoytrip.board.dto.QuestionDto;

import java.util.List;

public interface QuestionMapper {
    List<QuestionDto> getQuestionList();
    void postQuestion(QuestionDto questionDto);
    QuestionDto getQuestionById(int questionId);
    void modifyQuestion(QuestionDto questionDto);
    void deleteQuestion(int questionId);
    void increaseHit(int questionId);
    AnswerDto getAnswer(int questionId);
}
