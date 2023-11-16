package com.ssafy.enjoytrip.attraction.model.service;

import com.ssafy.enjoytrip.attraction.dto.AttractionDescriptionDto;
import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.dto.GugunDto;
import com.ssafy.enjoytrip.attraction.dto.SidoDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AttractionService {
    AttractionDto getAttractionById(int contentId);
    AttractionDescriptionDto getAttractionDetail(int contentId);
    List<SidoDto> getSidoList();
    List<GugunDto> getGugunListBySidoCode(int sidoCode);
    List<AttractionDto> getAttractionListBySidoGugunCode(int sidoCode, int gugunCode);
    List<AttractionDto> getAttractionListByTitle(String keyword);
    List<AttractionDto> getAttractionListByContentTypeId(int contentTypeId);

}
