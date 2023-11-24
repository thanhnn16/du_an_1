package com.miwth.allure_spa.model;

import com.squareup.moshi.Json;

import java.util.Date;

public class Treatments {
    private int id;


    @Json(name = "treatment_name")
    private String treatmentName;
    @Json(name = "treatment_category_id")
    private int treatmentCategoryId;
    private String descriptions;
    private String purpose;

    @Json(name = "execution_time")
    private String executionTime;
    private int price;
    private String image;
    @Json(name = "created_at")
    private Date createdAt;
    @Json(name = "last_update")
    private Date lastUpdate;

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

    public String getDescriptions() {
        return descriptions;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getDescription() {
        return descriptions;

    }
}
