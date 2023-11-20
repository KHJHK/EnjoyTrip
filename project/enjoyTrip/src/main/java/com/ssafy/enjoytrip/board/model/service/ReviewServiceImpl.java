package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.ReviewDto;
import com.ssafy.enjoytrip.board.dto.ReviewPhotoDto;
import com.ssafy.enjoytrip.board.model.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
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
        System.out.println(reviewDto.getReviewId());
        List<ReviewPhotoDto> photos = reviewDto.getPhotos();
        if (photos != null && !photos.isEmpty()) {
            reviewMapper.registerFile(reviewDto);
        }
    }

    @Override
    public void modifyReview(ReviewDto reviewDto) {
        reviewMapper.modifyReview(reviewDto);
    }

    @Override
    public void deleteReview(int reviewId, String path) {
        List<ReviewPhotoDto> fileList = reviewMapper.photoInfoList(reviewId);
        reviewMapper.deleteAllFile(reviewId);
        reviewMapper.deleteReview(reviewId);
        for(ReviewPhotoDto fileInfoDto : fileList) {
            File file = new File(path + File.separator + fileInfoDto.getImageLocation() + File.separator + fileInfoDto.getSaveName());
            file.delete();
        }
    }

    @Override
    public void increaseHits(int reviewId) {
        reviewMapper.increaseHits(reviewId);
    }
}
