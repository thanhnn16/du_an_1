package com.miwth.allure_spa.api.cosmetic;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class CosmeticRepository {

    public static final String BASE_URL = "http://192.168.1.10:8000/api/";
    private final CosmeticApiService apiService;

    public CosmeticRepository(String token) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder().header("Authorization", "Bearer " + token).addHeader("Accept", "application/json");
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }).build();

        Moshi moshi = new Moshi.Builder()
                .add(new DateAdapterZ())
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(MoshiConverterFactory.create(moshi)).build();

        apiService = retrofit.create(CosmeticApiService.class);
    }

    public Call<CosmeticsResponse> getCosmetic(int id) {
        return apiService.getCosmetic(id);
    }

    public Call<CosmeticsResponse> getCosmetics() {
        return apiService.getCosmetics();
    }

    public static class DateAdapterZ {
        @ToJson
        String toJson(Date date) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.US).format(date);
        }

        @FromJson
        Date fromJson(String date) throws ParseException {
            try {
                return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.US).parse(date);
            } catch (ParseException e) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(date);
            }
        }
    }
}