package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.dto.PlanDto;
import com.ssafy.enjoytrip.board.model.service.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
@Slf4j
public class PlanController {
    private final PlanService planService;

    @PostMapping
    public ResponseEntity<?> postReview(@RequestBody PlanDto plan){
        try {
            planService.postPlan(plan);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getPlanList(){
        try{
            List<PlanDto> planList = planService.getPlanList();
            System.out.println(planList.get(0).getPlanPostDate());
            System.out.println(planList.get(0).getUserName());
            return new ResponseEntity<List<PlanDto>>(planList, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{planId}")
    public ResponseEntity<?> getPlanById(@PathVariable int planId){
        try{
            PlanDto plan = planService.getPlanById(planId);
            return new ResponseEntity<PlanDto>(plan, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PutMapping("/{planId}")
    public ResponseEntity<?> modifyPlan(@RequestBody PlanDto plan){
        try{
            planService.modifyPlan(plan);
            return new ResponseEntity<PlanDto>(plan, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping("/my-plan")
    public ResponseEntity<?> getMyPlans(@RequestParam int userNo){
        try{
            List<PlanDto> myPlan = planService.getMyPlans(userNo);
            return new ResponseEntity<List<PlanDto>>(myPlan, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable int planId){
        try{
            planService.deletePlan(planId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }
    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
