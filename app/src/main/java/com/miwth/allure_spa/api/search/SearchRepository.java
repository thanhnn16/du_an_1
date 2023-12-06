package com.miwth.allure_spa.api.search;

import static com.miwth.allure_spa.api.ApiConstants.getRetrofit;

import com.miwth.allure_spa.api.appointment.AppointmentsApiService;
import com.miwth.allure_spa.api.appointment.AppointmentsResponse;

import retrofit2.Call;

public class SearchRepository {
    private final SearchApiService apiService;

    public SearchRepository() {
        apiService = getRetrofit().create(SearchApiService.class);
    }

    public Call<SearchResponse> getResults(String search) {
        return apiService.getResults(search);
    }
}
