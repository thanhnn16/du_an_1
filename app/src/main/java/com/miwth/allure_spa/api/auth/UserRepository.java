package com.miwth.allure_spa.api.auth;

import com.miwth.allure_spa.api.cosmetic.CosmeticRepository;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class UserRepository {
    private UserApiService apiService;

    public UserRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CosmeticRepository.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        apiService = retrofit.create(UserApiService.class);
    }

    public Call<LoginResponse> login(String email, String password) {
        return apiService.login(new LoginRequest(email, password));
    }
}
