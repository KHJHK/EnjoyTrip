package com.ssafy.enjoytrip.board.dto;

import com.ssafy.enjoytrip.member.dto.MemberDto;
import lombok.Data;

import java.util.List;

@Data
public class NoticeDto {
    int noticeId;
    int boardId;
    int userNo;
    String noticeTitle;
    String noticeContent;
    String noticePostDate;
    int noticeHits;
    String userName;
    List<CommentDto> comments;
}
