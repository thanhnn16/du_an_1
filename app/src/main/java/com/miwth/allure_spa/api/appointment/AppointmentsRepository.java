package com.miwth.allure_spa.api.appointment;

import static com.miwth.allure_spa.api.ApiConstants.getRetrofit;

import retrofit2.Call;

public class AppointmentsRepository {
    private final AppointmentsApiService apiService;

    public AppointmentsRepository() {
         apiService = getRetrofit().create(AppointmentsApiService.class);
    }

    public Call<AppointmentsResponse> getAppointments() {
        return apiService.getAppointments();
    }

    public Call<AppointmentsResponse> getAppointment(int id) {
        return apiService.getAppointment(id);
    }
}
