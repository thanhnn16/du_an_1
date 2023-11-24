package com.miwth.allure_spa.api.auth;

import static com.miwth.allure_spa.api.ApiConstants.LOGIN_URL;
import static com.miwth.allure_spa.api.ApiConstants.REGISTER_URL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApiService {

    @POST(LOGIN_URL)
    Call<AuthResponse> login(@Body AuthRequest loginRequest);

    @POST(REGISTER_URL)
    Call<AuthResponse> register(@Body AuthRequest registerRequest);
}
