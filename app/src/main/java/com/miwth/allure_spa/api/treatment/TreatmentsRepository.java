package com.miwth.allure_spa.api.treatment;

import static com.miwth.allure_spa.api.ApiConstants.getRetrofit;

import retrofit2.Call;

public class TreatmentsRepository {
    private final TreatmentsApiService apiService;

    public TreatmentsRepository() {
        apiService = getRetrofit().create(TreatmentsApiService.class);
    }

    public Call<TreatmentsResponse> getTreatment(int id) {
        return apiService.getTreatment(id);
    }

    public Call<TreatmentsResponse> getTreatments() {
        return apiService.getTreatments();
    }


}