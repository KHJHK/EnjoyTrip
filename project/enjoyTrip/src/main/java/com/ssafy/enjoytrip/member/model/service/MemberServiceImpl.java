package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.member.dto.MemberDto;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import com.ssafy.enjoytrip.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public Map<String, Object> login(MemberDto member) {
        MemberDto loginUser = memberMapper.login(member);

        //해당 유저 정보 없음
        if(loginUser==null) return null;

        //토큰 생성
        String token = JWTUtil.generateToken(loginUser);

        //응답 데이터에 토큰 정보 구성
        Map<String, Object> tokenResponse = new HashMap<>();
        tokenResponse.put("token", token);

        return tokenResponse;
    }

    @Override
    public List<MemberDto> getUserList() {
        return memberMapper.getUserList();
    }

    @Override
    public void regist(MemberDto memberDto) {
        memberMapper.regist(memberDto);
    }

    @Override
    public MemberDto getUserByNo(int userNo) {
        return memberMapper.getUserByNo(userNo);
    }

    @Override
    public void modifyUserInfo(MemberDto memberDto) {
        memberMapper.modifyUserInfo(memberDto);
    }

    @Override
    public void changeUserStatus(MemberDto memberDto) {
        memberMapper.changeUserStatus(memberDto);
    }

    @Override
    public void deleteUser(int userNo) {
        memberMapper.deleteUser(userNo);
    }

    @Override
    public String findId(String userName, String email) {
        return memberMapper.findId(userName, email);
    }

    @Override
    public String findPassword(String userName, String email, String userId) {
        return memberMapper.findPassword(userName, email, userId);
    }

}
