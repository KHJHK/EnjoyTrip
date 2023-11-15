package com.ssafy.enjoytrip.member.model.mapper;

import com.ssafy.enjoytrip.member.dto.MemberDto;

import java.util.List;

public interface MemberMapper {

    MemberDto login(MemberDto member);
    List<MemberDto> getUserList();
    int regist(MemberDto memberDto);
    MemberDto getUserByNo(int userNo);
    int modifyUserInfo(MemberDto memberDto);
    int changeUserStatus(MemberDto memberDto);
    int deleteUser(int userNo);
}
