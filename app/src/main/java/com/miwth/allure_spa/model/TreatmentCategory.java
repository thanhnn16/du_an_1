package com.miwth.allure_spa.model;

import java.util.Date;

public class TreatmentCategory {
    private int id;
    private String treatmentCategoryName;
    private Date createdAt;
    private Date lastUpdate;

    public TreatmentCategory(int id, String treatmentCategoryName, Date createdAt, Date lastUpdate) {
        this.id = id;
        this.treatmentCategoryName = treatmentCategoryName;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTreatmentCategoryName() {
        return treatmentCategoryName;
    }

    public void setTreatmentCategoryName(String treatmentCategoryName) {
        this.treatmentCategoryName = treatmentCategoryName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

