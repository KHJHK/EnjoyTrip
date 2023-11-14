package com.ssafy.enjoytrip.member.model.mapper;

import com.ssafy.enjoytrip.member.dto.MemberDto;

import java.util.List;

public interface MemberMapper {

    List<MemberDto> getUserList();
    int regist(MemberDto memberDto);

}
