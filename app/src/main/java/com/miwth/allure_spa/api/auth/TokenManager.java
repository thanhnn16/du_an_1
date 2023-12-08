package com.miwth.allure_spa.api.auth;

import static com.miwth.allure_spa.api.ApiConstants.WEB_BASE_URL;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private final SharedPreferences sharedPreferences;

    public TokenManager(Context context) {
        sharedPreferences = context.getSharedPreferences("api_tokens", Context.MODE_PRIVATE);
    }

    public String getToken() {
        return sharedPreferences.getString("token", "");
    }

    public int getUserId() {
        return sharedPreferences.getInt("user_id", 0);
    }

    public void saveToken(String token) {
        sharedPreferences.edit().putString("token", token).apply();
    }

    public void saveUserId(int user_id) {
        sharedPreferences.edit().putInt("user_id", user_id).apply();
    }

    public void saveImage(String image) {
        String substring = image.substring(1);
        sharedPreferences.edit().putString("image", WEB_BASE_URL + substring).apply();
    }

    public void savePhoneNumber(String phoneNumber) {
        sharedPreferences.edit().putString("phoneNumber", phoneNumber).apply();
    }

    public String getImage() {
        return sharedPreferences.getString("image", "");
    }

    public void saveFullName(String fullName) {
        sharedPreferences.edit().putString("fullName", fullName).apply();
    }

    public String getFullName() {
        return sharedPreferences.getString("fullName", "");
    }

    public String getPhoneNumber() {
        return sharedPreferences.getString("phoneNumber", "");
    }

    public void saveFCMToken(String token) {
        sharedPreferences.edit().putString("fcm_token", token).apply();
    }

    public String getFCMToken() {
        return sharedPreferences.getString("fcm_token", "");
    }

}
