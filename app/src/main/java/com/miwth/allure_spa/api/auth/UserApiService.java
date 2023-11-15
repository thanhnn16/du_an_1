package com.miwth.allure_spa.api.auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApiService {
    @Headers({
            "Accept: application/json",
    })
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
