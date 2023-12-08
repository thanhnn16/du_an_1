package com.miwth.allure_spa.api.user;

import static com.miwth.allure_spa.api.ApiConstants.getRetrofit;
import static com.miwth.allure_spa.api.ApiConstants.getRetrofitWithAuth;

import com.miwth.allure_spa.model.User;

import retrofit2.Call;

public class UserDetailRepository {
    private final UserDetailApiService apiService;

    public UserDetailRepository(String token) {
        this.apiService = getRetrofitWithAuth(token).create(UserDetailApiService.class);
    }

    public Call<UserDetailResponse> getUserDetail(int id) {
        return apiService.getUserDetail(id);
    }

    public Call<UserDetailResponse> updateUserDetail(int id, User request) {
        return apiService.updateUserDetail(id, request);
    }
}
