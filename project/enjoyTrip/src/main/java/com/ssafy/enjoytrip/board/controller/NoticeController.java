package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.dto.NoticeDto;
import com.ssafy.enjoytrip.board.model.service.NoticeService;
import com.ssafy.enjoytrip.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<?> postNotice(@RequestBody NoticeDto notice){
        try {
            notice.setUserNo(JWTUtil.userNo);
            noticeService.postNotice(notice);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getNoticeList(){
        try{
            List<NoticeDto> noticeList = noticeService.getNoticeList();
            return new ResponseEntity<List<NoticeDto>>(noticeList, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<?> getNoticeById(@PathVariable int noticeId){
        try{
            noticeService.increaseHit(noticeId);
            NoticeDto notice = noticeService.getNoticeById(noticeId);
            return new ResponseEntity<NoticeDto>(notice, HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> modifyNotice(@RequestBody NoticeDto notice){
        try{
            if(JWTUtil.userNo != notice.getUserNo()){
                return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
            }
            noticeService.modifyNotice(notice);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<?> deleteNotice(@PathVariable int noticeId){
        try{
            int userNo = noticeService.getNoticeById(noticeId).getUserNo();
            if(JWTUtil.userNo != userNo){
                return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
            }
            noticeService.deleteNotice(noticeId);
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
