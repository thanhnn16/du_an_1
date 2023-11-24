package com.miwth.allure_spa.api.treatment;

import com.miwth.allure_spa.model.Treatments;

import java.util.List;

public class TreatmentsResponse {
    private boolean success;
    private String message;
    private List<Treatments> treatments;
    private Treatments treatment;


    public TreatmentsResponse(boolean success, String message, List<Treatments> treatments, Treatments treatment) {
        this.success = success;
        this.message = message;
        this.treatments = treatments;
        this.treatment = treatment;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Treatments> getData() {
        return treatments;
    }

    public Treatments getTreatment() {
        return treatment;

    }
}
