package com.miwth.allure_spa.model;

import com.squareup.moshi.Json;

import java.util.Date;

public class Treatments {
    private int id;
    @Json(name = "treatment_name")
    private String treatmentName;
    @Json(name = "treatment_category_id")
    private int treatmentCategoryId;
    private String description;
    private String purpose;

    @Json(name = "execution_time")
    private String executionTime;
    private int price;
    private String image;
    @Json(name = "created_at")
    private Date createdAt;
    @Json(name = "updated_at")
    private Date updatedAt;

    public Treatments() {
    }

    public int getId() {
        return id;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public int getTreatmentCategoryId() {
        return treatmentCategoryId;
    }

    public String getDescription() {
        return description;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getPurpose() {
        return purpose;
    }
}
