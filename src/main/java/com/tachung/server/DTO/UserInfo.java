package com.tachung.server.DTO;


import lombok.Data;


// 유저 정보 DTO
@Data
public class UserInfo {
    private String name;
    private String email;
    // 기본 생성자

    // 모든 필드를 초기화하는 생성자
    public UserInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }
}