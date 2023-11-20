package com.ssafy.enjoytrip.board.model.mapper;

import com.ssafy.enjoytrip.board.dto.ReviewDto;
import com.ssafy.enjoytrip.board.dto.ReviewPhotoDto;

import java.util.List;

public interface ReviewMapper {
    List<ReviewDto> getReviewList();
    ReviewDto getReviewById(int reviewId);
    void postReview(ReviewDto reviewDto);
    void modifyReview(ReviewDto reviewDto);
    void deleteReview(int reviewId);
    void increaseHits(int reviewId);
    void registerFile (ReviewDto reviewDto);
    List<ReviewPhotoDto> photoInfoList(int reviewId);
//    void deleteFile(ReviewPhotoDto photoDto);
    void deleteAllFile(int reviewId);

}
