package com.ssafy.enjoytrip.board.model.mapper;

import com.ssafy.enjoytrip.board.dto.PlanDto;
import com.ssafy.enjoytrip.board.dto.PlanSightsDto;
import com.ssafy.enjoytrip.board.dto.ReviewPhotoDto;

import java.util.List;

public interface PlanMapper {
    List<PlanDto> getPlanList();
    void postPlan(PlanDto planDto);
    void modifyPlan(PlanDto planDto);
    PlanDto getPlanById(int planId);
    void deletePlan(int planId);
    List<PlanDto> getMyPlans(int userNo);
    List<ReviewPhotoDto> sightInfoList(int planId);
    void increaseHits(int planId);
    void registerSight(PlanDto planDto);
    void deleteAllSights(int planId);
}
