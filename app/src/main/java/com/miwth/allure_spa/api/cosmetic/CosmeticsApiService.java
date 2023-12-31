package com.miwth.allure_spa.api.cosmetic;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CosmeticsApiService {

    @GET("cosmetics/{id}")
    Call<CosmeticsResponse> getCosmetic(@Path("id") int id);

    @GET("cosmetics")
    Call<CosmeticsResponse> getCosmetics();

    @GET("cosmetics-best")
    Call<CosmeticsResponse> getBestSells();
}
