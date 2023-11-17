package com.miwth.allure_spa.api.auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApiService {

    @POST("login")
    Call<AuthResponse> login(@Body AuthRequest loginRequest);

    @POST("register")
    Call<AuthResponse> register(@Body AuthRequest registerRequest);
}
