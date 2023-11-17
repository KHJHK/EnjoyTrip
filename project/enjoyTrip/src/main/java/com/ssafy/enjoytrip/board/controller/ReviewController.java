package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.dto.ReviewDto;
import com.ssafy.enjoytrip.board.model.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> postReview(@RequestBody ReviewDto review){
        try {
            reviewService.postReview(review);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getReviewList(){
        try{
            List<ReviewDto> reviewList = reviewService.getReviewList();
            return new ResponseEntity<List<ReviewDto>>(reviewList, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReviewById(@PathVariable int reviewId){
        try{
            reviewService.increaseHits(reviewId);
            ReviewDto review = reviewService.getReviewById(reviewId);
            return new ResponseEntity<ReviewDto>(review, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> modifyReview(@RequestBody ReviewDto review){
        try{
            reviewService.modifyReview(review);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable int reviewId){
        try{
            reviewService.deleteReview(reviewId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
