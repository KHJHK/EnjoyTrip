package com.ssafy.enjoytrip.board.dto;

import lombok.Data;

@Data
public class NoticeDto {
    int noticeId;
    int boardId;
    int userNo;
    String noticeTitle;
    String noticeContent;
    String noticePostDate;
    int noticeHits;
}
