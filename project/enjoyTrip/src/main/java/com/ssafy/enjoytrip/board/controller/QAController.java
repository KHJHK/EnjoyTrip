package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.dto.AnswerDto;
import com.ssafy.enjoytrip.board.dto.NoticeDto;
import com.ssafy.enjoytrip.board.dto.QuestionDto;
import com.ssafy.enjoytrip.board.model.service.AnswerService;
import com.ssafy.enjoytrip.board.model.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Slf4j
public class QAController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @GetMapping
    public ResponseEntity<?> getQuestionList(){
        try{
            List<QuestionDto> questionList = questionService.getQuestionList();
            return new ResponseEntity<List<QuestionDto>>(questionList, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }
    @GetMapping("/{questionId}")
    public ResponseEntity<?> getQuestionById(@PathVariable int questionId){
        try{
            questionService.increaseHit(questionId);
            QuestionDto question = questionService.getQuestionById(questionId);
            return new ResponseEntity<QuestionDto>(question, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> postQuestion(@RequestBody QuestionDto question){
        try {
            questionService.postQuestion(question);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> modifyQuestion(@RequestBody QuestionDto question){
        try{
            questionService.modifyQuestion(question);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable int questionId){
        try{
            answerService.deleteAnswer(questionId);
            questionService.deleteQuestion(questionId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{questionId}/answers")
    public ResponseEntity<?> getAnswerByQuestionId(@PathVariable int questionId){
        try{
            AnswerDto answer = answerService.getAnswerByQuestionId(questionId);
            return new ResponseEntity<AnswerDto>(answer, HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PostMapping("/{questionId}/answers")
    public ResponseEntity<?> postAnswer(@RequestBody AnswerDto answerDto){
        try{
            answerService.postAnswer(answerDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PutMapping("/{questionId}/answers")
    public ResponseEntity<?> modifyAnswer(@RequestBody AnswerDto answerDto){
        try{
            answerService.modifyAnswer(answerDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/{questionId}/answers")
    public ResponseEntity<?> deleteAnswer(@PathVariable int questionId){
        try{
            answerService.deleteAnswer(questionId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
