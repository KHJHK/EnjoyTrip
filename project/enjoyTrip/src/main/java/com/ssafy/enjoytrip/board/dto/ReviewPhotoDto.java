package com.ssafy.enjoytrip.board.dto;

import lombok.Data;

@Data
public class ReviewPhotoDto {
    int reviewId;
    int order;
    String originalName;
    String saveName;
    String imageLocation;
}
