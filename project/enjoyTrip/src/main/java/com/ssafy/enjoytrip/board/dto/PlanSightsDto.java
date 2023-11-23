package com.ssafy.enjoytrip.board.dto;

import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import lombok.Data;

@Data
public class PlanSightsDto {
    int planId;
    int order;
    int contentId;
    int contentTypeId;
    String title;
    String firstImage;
    String firstImage2;
    int sidoCode;
    int gugunCode;
    double latitude;
    double longitude;
}
