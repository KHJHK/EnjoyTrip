package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.dto.NoticeDto;

import java.util.List;

public interface NoticeService {
    void postNotice(NoticeDto notice);
    List<NoticeDto> getNoticeList();
    NoticeDto getNoticeById(int noticeId);
    void increaseHit(int noticeId);
    void modifyNotice(NoticeDto noticeDto);
    void deleteNotice(int noticeId);
}
