package com.miwth.allure_spa.api.user;

import com.miwth.allure_spa.model.Appointment;
import com.miwth.allure_spa.model.Invoice;
import com.miwth.allure_spa.model.User;

import java.util.List;

public class UserDetailResponse {
    List<Appointment> appointments;
    User user;
    List<Invoice> invoices;

    String error;
    String success;

    public UserDetailResponse() {
    }

    public UserDetailResponse(List<Appointment> appointments, User user, List<Invoice> invoices, String error, String success) {
        this.appointments = appointments;
        this.user = user;
        this.invoices = invoices;
        this.error = error;
        this.success = success;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public User getUser() {
        return user;
    }

    public String getError() {
        return error;
    }

    public String getSuccess() {
        return success;
    }

}
