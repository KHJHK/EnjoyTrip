package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.NoticeDto;
import com.ssafy.enjoytrip.board.model.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
    private final NoticeMapper noticeMapper;

    @Override
    public void postNotice(NoticeDto noticeDto) {
        noticeMapper.postNotice(noticeDto);
    }
}
