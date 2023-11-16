package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.NoticeDto;
import com.ssafy.enjoytrip.board.model.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
    private final NoticeMapper noticeMapper;

    @Override
    public void postNotice(NoticeDto noticeDto) {
        noticeMapper.postNotice(noticeDto);
    }

    @Override
    public List<NoticeDto> getNoticeList() {
        return noticeMapper.getNoticeList();
    }

    @Override
    public NoticeDto getNoticeById(int noticeId) {
        return noticeMapper.getNoticeById(noticeId);
    }

    @Override
    public void increaseHit(int noticeId) {
        noticeMapper.increaseHit(noticeId);
    }

    @Override
    public void modifyNotice(NoticeDto noticeDto) {
        noticeMapper.modifyNotice(noticeDto);
    }

    @Override
    public void deleteNotice(int noticeId) {
        noticeMapper.deleteNotice(noticeId);
    }
}
