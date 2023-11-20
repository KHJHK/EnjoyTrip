package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getReviewList();
    ReviewDto getReviewById(int reviewId);
    void postReview(ReviewDto reviewDto);
    void modifyReview(ReviewDto reviewDto);
    void deleteReview(int reviewId, String path);
    void increaseHits(int reviewId);
}
