package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.member.dto.MemberDto;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;
    @Override
    public List<MemberDto> getUserList() {
        return memberMapper.getUserList();
    }

    @Override
    public int regist(MemberDto memberDto) {
        return memberMapper.regist(memberDto);
    }
}
