package com.ssafy.enjoytrip.member.controller;

import com.ssafy.enjoytrip.member.dto.MemberDto;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto member){
        log.debug("login() 요청 수신, {}", member);
        Map<String, Object> loginInfo = memberService.login(member);

        //로그인 실패 시 401응답
        if(loginInfo==null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok(loginInfo);
    }

    @GetMapping
    public ResponseEntity<?> getUserList(){
        List<MemberDto> results = memberService.getUserList();

        return new ResponseEntity<List<MemberDto>>(results,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> regist(@RequestBody MemberDto memberDto){

        try {
            System.out.println("aa " + memberDto.toString());
            memberService.regist(memberDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{userNo}")
    ResponseEntity<?> getUserByNo(@PathVariable("userNo") int no) {
        MemberDto member = memberService.getUserByNo(no);
        return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> modifyUserInfo(@RequestBody MemberDto memberDto) {
        try {
            memberService.modifyUserInfo(memberDto);
            return new ResponseEntity<List<MemberDto>>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PutMapping("/status")
    public ResponseEntity<?> changeUserStatus(@RequestBody MemberDto memberDto) {
        try {
            memberService.changeUserStatus(memberDto);
            return new ResponseEntity<List<MemberDto>>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/{userNo}")
    public ResponseEntity<?> deleteUser(@PathVariable("userNo") int userNo) {
        try {
            memberService.deleteUser(userNo);
            return new ResponseEntity<List<MemberDto>>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }







    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
