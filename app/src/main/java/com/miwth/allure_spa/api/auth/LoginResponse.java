package com.miwth.allure_spa.api.auth;

// LoginResponse.java
public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
