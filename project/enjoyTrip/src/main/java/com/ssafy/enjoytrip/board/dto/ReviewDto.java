package com.ssafy.enjoytrip.board.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReviewDto {
    int reviewId;
    int boardId;
    int userNo;
    String reviewTitle;
    String reviewContent;
    String reviewPostDate;
    int reviewHits;
    String userName;
    String nickname;
    List<ReviewPhotoDto> photos;
}
