package com.tachung.server.DTO;


public class UserInfo {
    private String name;
    private String email;

    // 기본 생성자

    // 모든 필드를 초기화하는 생성자
    public UserInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getter 메소드
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // setter 메소드
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}