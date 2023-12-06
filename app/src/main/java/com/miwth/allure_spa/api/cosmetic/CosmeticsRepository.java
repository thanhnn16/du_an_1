package com.miwth.allure_spa.api.cosmetic;

import static com.miwth.allure_spa.api.ApiConstants.getRetrofit;

import retrofit2.Call;

public class CosmeticsRepository {
    private final CosmeticsApiService apiService;

    public CosmeticsRepository() {
        apiService = getRetrofit().create(CosmeticsApiService.class);
    }

    public Call<CosmeticsResponse> getCosmetic(int id) {
        return apiService.getCosmetic(id);
    }

    public Call<CosmeticsResponse> getCosmetics() {
        return apiService.getCosmetics();
    }
    public Call<CosmeticsResponse> getBestSells() {
        return apiService.getBestSells();
    }
}
