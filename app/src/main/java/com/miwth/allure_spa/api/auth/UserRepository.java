package com.miwth.allure_spa.api.auth;

import static com.miwth.allure_spa.api.ApiConstants.API_BASE_URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class UserRepository {
    private UserApiService apiService;

    public UserRepository() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder().header("Accept", "application/json");
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        apiService = retrofit.create(UserApiService.class);
    }

    public Call<AuthResponse> login(String phone_number, String password) {
        return apiService.login(new AuthRequest(phone_number, password));
    }

    public Call<AuthResponse> register(String phone_number, String password) {
        return apiService.register(new AuthRequest(phone_number, password));
    }
}
