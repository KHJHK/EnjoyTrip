package com.ssafy.enjoytrip.board.model.mapper;

import com.ssafy.enjoytrip.board.dto.ReviewDto;

import java.util.List;

public interface ReviewMapper {
    List<ReviewDto> getReviewList();
    ReviewDto getReviewById(int reviewId);
    void postReview(ReviewDto reviewDto);
    void modifyReview(ReviewDto reviewDto);
    void deleteReview(int reviewId);
    void increaseHits(int reviewId);
}
