package com.ssafy.enjoytrip.attraction.controller;

import com.ssafy.enjoytrip.attraction.dto.AttractionDescriptionDto;
import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.dto.GugunDto;
import com.ssafy.enjoytrip.attraction.dto.SidoDto;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
@Slf4j
public class AttractionController {
    private final AttractionService attractionService;

    @GetMapping("/{contentId}")
    public ResponseEntity<?> getAttractionById(@PathVariable("contentId") int id){
        AttractionDto attraction = attractionService.getAttractionById(id);
        return new ResponseEntity<AttractionDto>(attraction, HttpStatus.OK);
    }

    @GetMapping("/{contentId}/detail")
    public ResponseEntity<?> getAttractionDetail(@PathVariable("contentId") int id){
        AttractionDescriptionDto attractionDetail = attractionService.getAttractionDetail(id);
        return new ResponseEntity<AttractionDescriptionDto>(attractionDetail, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getSidoList(){
        List<SidoDto> sidoList = attractionService.getSidoList();
        return new ResponseEntity<List<SidoDto>>(sidoList, HttpStatus.OK);
    }

    @GetMapping("/search/{sidoCode}")
    public ResponseEntity<?> getGugunListBySidoCode(@PathVariable int sidoCode){
        List<GugunDto> gugunList = attractionService.getGugunListBySidoCode(sidoCode);
        return new ResponseEntity<List<GugunDto>>(gugunList, HttpStatus.OK);
    }

    @GetMapping("/search/{sidoCode}/{gugunCode}")
    public ResponseEntity<?> getAttractionListBySidoGugunCode(@PathVariable("sidoCode") int sidoCode, @PathVariable("gugunCode") int gugunCode){
        List<AttractionDto> attractionList = attractionService.getAttractionListBySidoGugunCode(sidoCode, gugunCode);
        return new ResponseEntity<List<AttractionDto>>(attractionList, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<?> getAttractionListByContentTypeId(@RequestBody Map<String, String> params){
        List<AttractionDto> attractionList = null;
        String keyword = params.get("keyword");
        int type = Integer.parseInt(params.get("type"));
        try {
            if(type == 0) {
                attractionList = attractionService.getAttractionListByTitle(keyword);
            }
            if(type == 1){
                int input = Integer.parseInt(keyword);
                attractionList = attractionService.getAttractionListByContentTypeId(input);
            }

            return new ResponseEntity<List<AttractionDto>>(attractionList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<AttractionDto>>(attractionList, HttpStatus.OK);
        }

    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
