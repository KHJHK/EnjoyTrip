package com.ssafy.enjoytrip.member.controller;

import com.ssafy.enjoytrip.board.dto.ReviewPhotoDto;
import com.ssafy.enjoytrip.member.dto.MemberDto;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Member;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    @Value("${file.path.upload-member}")
    private String uploadPath;
    private String defaultImage = uploadPath + "default_profile";
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
            memberService.regist(memberDto);
            String saveFolder = uploadPath + File.separator + memberDto.getUserNo();
            File folder = new File(saveFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
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
    @PostMapping("/id")
    public ResponseEntity<?> findId(@RequestBody MemberDto memberDto){
        try {
            String id = memberService.findId(memberDto.getUserName(), memberDto.getEmail());
            return new ResponseEntity<String>(id, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @PostMapping("/password")
    public ResponseEntity<?> findPassword(@RequestBody MemberDto memberDto){
        try {
            String password = memberService.findPassword(memberDto.getUserName(), memberDto.getEmail(), memberDto.getUserId());
            return new ResponseEntity<String>(password, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PutMapping("/image")
    public ResponseEntity<?> changeUserImage(@RequestParam(required = false) MultipartFile image, @RequestParam int userNo){
        try {
            MemberDto memberDto = new MemberDto();
            memberDto.setUserNo(userNo);
            if(image != null) {
                String saveFolder = uploadPath + File.separator + memberDto.getUserNo();
                File folder = new File(saveFolder);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                String originalFileName = image.getOriginalFilename();
                if (!originalFileName.isEmpty()) {
                    String saveFileName = UUID.randomUUID().toString()
                            + originalFileName.substring(originalFileName.lastIndexOf('.'));
                    memberDto.setProfileLocation(userNo + "");
                    memberDto.setProfileOriginalName(originalFileName);
                    memberDto.setProfileSaveName(saveFileName);
                    image.transferTo(new File(folder, saveFileName));
                }
            }

            memberService.changeUserImage(memberDto, uploadPath);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
