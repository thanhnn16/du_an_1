package com.miwth.allure_spa.api.appointment;

import com.miwth.allure_spa.model.Appointment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AppointmentsApiService {

    @GET("appointments")
    Call<AppointmentsResponse> getAppointments();

    @GET("appointments/{id}")
    Call<AppointmentsResponse> getAppointment(@Path("id") int id);

    @POST("appointment-management")
    Call<Void> createAppointment(@Body Appointment appointment);

}
