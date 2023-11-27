package com.tachung.server.DTO;

import lombok.Data;

// 로그인 이후 유저 정보 요청 DTO
@Data
public class UserRequest {
    private String email;
    private String password;
}
