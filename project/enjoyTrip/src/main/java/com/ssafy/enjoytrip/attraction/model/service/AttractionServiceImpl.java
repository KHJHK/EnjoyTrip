package com.ssafy.enjoytrip.attraction.model.service;

import com.ssafy.enjoytrip.attraction.dto.AttractionDescriptionDto;
import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.dto.GugunDto;
import com.ssafy.enjoytrip.attraction.dto.SidoDto;
import com.ssafy.enjoytrip.attraction.model.mapper.AttractionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService{
    private final AttractionMapper attractionMapper;
    @Override
    public AttractionDto getAttractionById(int contentId) {
        return attractionMapper.getAttractionById(contentId);
    }
    @Override
    public AttractionDescriptionDto getAttractionDetail(int contentId) {
        return attractionMapper.getAttractionDetail(contentId);
    }

    @Override
    public List<SidoDto> getSidoList() {
        return attractionMapper.getSidoList();
    }

    @Override
    public List<GugunDto> getGugunListBySidoCode(int sidoCode) {
        return attractionMapper.getGugunListBySidoCode(sidoCode);
    }

    @Override
    public List<AttractionDto> getAttractionListBySidoGugunCode(int sidoCode, int gugunCode) {
        return attractionMapper.getAttractionListBySidoGugunCode(sidoCode, gugunCode);
    }

    @Override
    public List<AttractionDto> getAttractionListByTitle(String keyword) {
        return attractionMapper.getAttractionListByTitle(keyword);
    }

    @Override
    public List<AttractionDto> getAttractionListByContentTypeId(int contentTypeId) {
        return attractionMapper.getAttractionListByContentTypeId(contentTypeId);
    }

}
