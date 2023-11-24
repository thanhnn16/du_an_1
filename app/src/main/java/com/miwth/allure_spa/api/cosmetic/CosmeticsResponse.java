package com.miwth.allure_spa.api.cosmetic;

import com.miwth.allure_spa.model.Cosmetics;

import java.util.List;

public class CosmeticsResponse {
    private boolean success;
    private String message;
    private List<Cosmetics> cosmetics;
    private Cosmetics cosmetic;

    public CosmeticsResponse(boolean success, String message, List<Cosmetics> cosmetics, Cosmetics cosmetic) {
        this.success = success;
        this.message = message;
        this.cosmetics = cosmetics;
        this.cosmetic = cosmetic;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Cosmetics> getData() {
        return cosmetics;
    }

    public Cosmetics getCosmetic() {
        return cosmetic;
    }
}
