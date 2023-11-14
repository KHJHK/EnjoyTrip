package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.member.dto.MemberDto;

import java.util.List;

public interface MemberService {

    List<MemberDto> getUserList();
    int regist(MemberDto memberDto);
}
