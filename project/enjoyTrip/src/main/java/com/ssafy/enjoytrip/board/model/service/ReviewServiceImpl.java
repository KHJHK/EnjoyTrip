package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.ReviewDto;
import com.ssafy.enjoytrip.board.model.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> getReviewList() {
        return reviewMapper.getReviewList();
    }

    @Override
    public ReviewDto getReviewById(int reviewId) {
        return reviewMapper.getReviewById(reviewId);
    }

    @Override
    public void postReview(ReviewDto reviewDto) {
        reviewMapper.postReview(reviewDto);
    }

    @Override
    public void modifyReview(ReviewDto reviewDto) {
        reviewMapper.modifyReview(reviewDto);
    }

    @Override
    public void deleteReview(int reviewId) {
        reviewMapper.deleteReview(reviewId);
    }

    @Override
    public void increaseHits(int reviewId) {
        reviewMapper.increaseHits(reviewId);
    }
}
