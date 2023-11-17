package com.miwth.allure_spa.api.treatment;

import com.miwth.allure_spa.model.Treatments;

import java.util.List;

public class TreatmentsResponse {
    private boolean success;
    private String message;
    private List<Treatments> data;

    public TreatmentsResponse(boolean success, String message, List<Treatments> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Treatments> getData() {
        return data;
    }
}
