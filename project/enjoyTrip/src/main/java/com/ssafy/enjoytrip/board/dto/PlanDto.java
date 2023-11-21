package com.ssafy.enjoytrip.board.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlanDto {
    int planId;
    int boardId;
    int userNo;
    String planTitle;
    String planContent;
    String planSubtitle;
    String planPostDate;
    int planHits;
    int planSecret;
    String userName;
    String nickname;
    List<PlanSightsDto> sights;
}
