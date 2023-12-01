package com.miwth.allure_spa.api.appointment;

import com.miwth.allure_spa.model.Appointment;

import java.util.List;

public class AppointmentsResponse {
    private boolean success;
    private String message;
    private List<Appointment> appointments;
    private Appointment appointment;

    public AppointmentsResponse(boolean success, String message, List<Appointment> appointments) {
        this.success = success;
        this.message = message;
        this.appointments = appointments;
    }

    public AppointmentsResponse(boolean success, String message, Appointment appointment) {
        this.success = success;
        this.message = message;
        this.appointment = appointment;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public Appointment getAppointment() {
        return appointment;
    }

}
