package com.miwth.allure_spa.api.auth;

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

    public String getUserId() {
        return sharedPreferences.getString("user_id", "");
    }

    public void saveToken(String token) {
        sharedPreferences.edit().putString("token", token).apply();
    }

    public void saveUserId(String user_id) {
        sharedPreferences.edit().putString("user_id", user_id).apply();
    }
}
