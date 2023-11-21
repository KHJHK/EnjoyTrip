package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.PlanDto;
import com.ssafy.enjoytrip.board.dto.PlanSightsDto;
import com.ssafy.enjoytrip.board.model.mapper.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{
    private final PlanMapper planMapper;
    @Override
    public List<PlanDto> getPlanList() {
        return planMapper.getPlanList();
    }

    @Override
    public PlanDto getPlanById(int planId) {
        planMapper.increaseHits(planId);
        return planMapper.getPlanById(planId);
    }

    @Override
    public void postPlan(PlanDto planDto) {
        planMapper.postPlan(planDto);
        List<PlanSightsDto> sights = planDto.getSights();
        if (sights != null && !sights.isEmpty()) {
            planMapper.registerSight(planDto);
        }
    }

    @Override
    public void modifyPlan(PlanDto planDto) {
        planMapper.modifyPlan(planDto);
        planMapper.deleteAllSights(planDto.getPlanId());
        List<PlanSightsDto> sights = planDto.getSights();
        if (sights != null && !sights.isEmpty()) {
            planMapper.registerSight(planDto);
        }
    }

    @Override
    public void deletePlan(int planId) {
        planMapper.deleteAllSights(planId);
        planMapper.deletePlan(planId);
    }

    @Override
    public List<PlanDto> getMyPlans(int userNo) {
        return planMapper.getMyPlans(userNo);
    }
}
