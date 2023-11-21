package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.member.dto.MemberDto;

import java.util.List;
import java.util.Map;

public interface MemberService {

    Map<String, Object> login(MemberDto member);
    List<MemberDto> getUserList();
    void regist(MemberDto memberDto);
    MemberDto getUserByNo(int userNo);
    void modifyUserInfo(MemberDto memberDto);
    void changeUserStatus(MemberDto memberDto);
    void deleteUser(int userNo);
    String findId(String userName, String email);
    String findPassword(String userName, String email, String userId);

    void changeUserImage(MemberDto memberDto, String path);
}
