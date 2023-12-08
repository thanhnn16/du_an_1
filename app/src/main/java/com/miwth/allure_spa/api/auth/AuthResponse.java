package com.miwth.allure_spa.api.auth;

import com.squareup.moshi.Json;

public class AuthResponse {
    private String token;
    private int user_id;

    private String image;
    @Json(name = "full_name")
    private String fullName;

    @Json(name = "phone_number")
    private String phoneNumber;
    private String error;

    public AuthResponse(String token, int user_id, String image, String fullName, String phoneNumber, String error) {
        this.token = token;
        this.user_id = user_id;
        this.image = image;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return user_id;
    }

    public String getImage() {
        return image;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getError() {
        return error;
    }

}
