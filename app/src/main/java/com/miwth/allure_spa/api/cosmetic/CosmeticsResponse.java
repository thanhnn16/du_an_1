package com.miwth.allure_spa.api.cosmetic;

import com.miwth.allure_spa.model.Cosmetics;

import java.util.List;

public class CosmeticsResponse {
    private boolean success;
    private String message;
    private List<Cosmetics> data;
    private Cosmetics cosmetic;

    public CosmeticsResponse(boolean success, String message, List<Cosmetics> data, Cosmetics cosmetic) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.cosmetic = cosmetic;
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

    public Cosmetics getCosmetic() {
        return cosmetic;
    }
}
