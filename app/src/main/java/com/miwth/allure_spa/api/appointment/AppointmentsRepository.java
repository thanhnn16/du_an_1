package com.miwth.allure_spa.api.appointment;

import static com.miwth.allure_spa.api.ApiConstants.getRetrofit;
import static com.miwth.allure_spa.api.ApiConstants.getRetrofitWithAuth;

import com.miwth.allure_spa.model.Appointment;

import retrofit2.Call;

public class AppointmentsRepository {
    private final AppointmentsApiService apiService;

    public AppointmentsRepository(String token) {
         apiService = getRetrofitWithAuth(token).create(AppointmentsApiService.class);
    }

    public Call<AppointmentsResponse> getAppointments() {
        return apiService.getAppointments();
    }

    public Call<AppointmentsResponse> getAppointment(int id) {
        return apiService.getAppointment(id);
    }

    public Call<Void> createAppointment(Appointment appointment) {
        return apiService.createAppointment(appointment);
    }

}
