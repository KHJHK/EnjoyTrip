package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.AnswerDto;
import com.ssafy.enjoytrip.board.model.mapper.AnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService{
    private final AnswerMapper answerMapper;
    @Override
    public AnswerDto getAnswerByQuestionId(int questionId) {
        return answerMapper.getAnswerByQuestionId(questionId);
    }

    @Override
    public void postAnswer(AnswerDto answerDto) {
        answerMapper.postAnswer(answerDto);
    }

    @Override
    public void modifyAnswer(AnswerDto answerDto) {
        answerMapper.modifyAnswer(answerDto);
    }

    @Override
    public void deleteAnswer(int questionId) {
        answerMapper.deleteAnswer(questionId);
    }

    @Override
    public void increaseHit(int questionId) {
        answerMapper.increaseHit(questionId);
    }
}
