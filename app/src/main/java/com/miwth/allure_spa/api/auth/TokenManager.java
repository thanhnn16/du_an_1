package com.miwth.allure_spa.api.auth;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private SharedPreferences sharedPreferences;

    public TokenManager(Context context) {
        sharedPreferences = context.getSharedPreferences("api_tokens", Context.MODE_PRIVATE);
    }

    public String getToken() {
        return sharedPreferences.getString("token", "");
    }

    public void saveToken(String token) {
        sharedPreferences.edit().putString("token", token).apply();
    }
}
