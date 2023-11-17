package com.ssafy.enjoytrip.board.dto;

import lombok.Data;

@Data
public class ReviewDto {
    int reviewId;
    int boardId;
    int userNo;
    String reviewTitle;
    String reviewContent;
    String reviewPostDate;
    int reviewHits;
}
