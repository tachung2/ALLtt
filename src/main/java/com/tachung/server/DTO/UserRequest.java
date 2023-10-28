package com.tachung.server.DTO;

import lombok.Data;

@Data
public class UserRequest {
    private String email;
    private String password;
}
