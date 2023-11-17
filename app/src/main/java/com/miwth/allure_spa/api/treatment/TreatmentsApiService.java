package com.miwth.allure_spa.api.treatment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TreatmentsApiService {

    @GET("treatments/{id}")
    Call<TreatmentsResponse> getTreatment(@Path("id") int id);

    @GET("treatments")
    Call<TreatmentsResponse> getTreatments();
}
