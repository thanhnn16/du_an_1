package com.miwth.allure_spa.api.search;

import com.miwth.allure_spa.api.appointment.AppointmentsResponse;
import com.miwth.allure_spa.model.Appointment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchApiService {
    @GET("search")
    Call<SearchResponse> getResults(@Query("search") String search);
}
