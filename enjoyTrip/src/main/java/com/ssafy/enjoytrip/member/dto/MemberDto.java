package com.ssafy.enjoytrip.member.dto;


import lombok.Data;

@Data
public class MemberDto {
    int userNo;
    String userId;
    String userPassword;
    String userName;
    String email;
    String nickname;
    String birthday;
    String role;
    int status;
    String registDate;
    String profileImage;
}
