package com.miwth.allure_spa.api.auth;

public class AuthRequest {
    private String phone_number;
    private String password;

    public AuthRequest(String phone_number, String password) {
        this.phone_number = phone_number;
        this.password = password;
    }
}
