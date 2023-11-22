package com.ssafy.enjoytrip.board.model.mapper;

import com.ssafy.enjoytrip.board.dto.CommentDto;
import com.ssafy.enjoytrip.board.dto.NoticeDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
    void postNotice(NoticeDto notice);
    List<NoticeDto> getNoticeList();
    NoticeDto getNoticeById(int noticeId);
    void increaseHit(int noticeId);
    void modifyNotice(NoticeDto noticeDto);
    void deleteNotice(int noticeId);
    List<CommentDto> commentList(@Param("noticeId") int noticeId, @Param("boardId") int boardId);

}
