package com.miwth.allure_spa.api.cosmetic;

import com.miwth.allure_spa.model.Cosmetics;

import java.util.List;

public class CosmeticsResponse {
    private boolean success;
    private String message;
    private List<Cosmetics> data;

    public CosmeticsResponse(boolean success, String message, List<Cosmetics> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Cosmetics> getData() {
        return data;
    }
}
