package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.PlanDto;

import java.util.List;

public interface PlanService {
    List<PlanDto> getPlanList();
    PlanDto getPlanById(int planId);
    void postPlan(PlanDto planDto);
    void modifyPlan(PlanDto planDto);
    void deletePlan(int planId);
    List<PlanDto> getMyPlans(int userNo);
}
