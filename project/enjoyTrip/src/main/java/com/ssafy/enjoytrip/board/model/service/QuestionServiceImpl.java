package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.QuestionDto;
import com.ssafy.enjoytrip.board.model.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionMapper questionMapper;
    @Override
    public List<QuestionDto> getQuestionList() {
        return questionMapper.getQuestionList();
    }

    @Override
    public void postQuestion(QuestionDto questionDto) {
        questionMapper.postQuestion(questionDto);
    }

    @Override
    public QuestionDto getQuestionById(int questionId) {
        return questionMapper.getQuestionById(questionId);
    }

    @Override
    public void modifyQuestion(QuestionDto questionDto) {
        questionMapper.modifyQuestion(questionDto);
    }

    @Override
    public void deleteQuestion(int questionId) {
        questionMapper.deleteQuestion(questionId);
    }

    @Override
    public void increaseHit(int questionId) {
        questionMapper.increaseHit(questionId);
    }
}
