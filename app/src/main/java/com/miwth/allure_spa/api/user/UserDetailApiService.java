package com.miwth.allure_spa.api.user;

import com.miwth.allure_spa.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserDetailApiService {

    @GET("user/{id}")
    Call<UserDetailResponse> getUserDetail(@Path("id") int id);

    @PUT("user/{id}")
    Call<UserDetailResponse> updateUserDetail(@Path("id") int id, @Body User request);
}
