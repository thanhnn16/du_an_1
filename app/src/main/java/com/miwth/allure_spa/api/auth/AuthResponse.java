package com.miwth.allure_spa.api.auth;

public class AuthResponse {
    private String token;
    private String user_id;

    public AuthResponse(String token, String userId) {
        this.token = token;
        user_id = userId;
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return user_id;
    }

}
