package com.tachung.server.DTO;

public class AuthenticationResponse {
    private String token;
    private UserInfo userInfo;

    public AuthenticationResponse(String token, UserInfo userInfo) {
        this.token = token;
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
