package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getQuestionList();
    void postQuestion(QuestionDto questionDto);
    QuestionDto getQuestionById(int questionId);
    void modifyQuestion(QuestionDto questionDto);
    void deleteQuestion(int questionId);
    void increaseHit(int questionId);
}
