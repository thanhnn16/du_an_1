package com.miwth.allure_spa.model;

import java.util.Date;

public class Treatment {
    private int id;
    private String treatmentName;
    private int treatmentCategoryId;
    private String descriptions;
    private String executionTime;
    private int price;
    private String image;
    private Date createdAt;
    private Date lastUpdate;

    public Treatment(int id, String treatmentName, int treatmentCategoryId, String descriptions,
                     String executionTime, int price, String image, Date createdAt, Date lastUpdate) {
        this.id = id;
        this.treatmentName = treatmentName;
        this.treatmentCategoryId = treatmentCategoryId;
        this.descriptions = descriptions;
        this.executionTime = executionTime;
        this.price = price;
        this.image = image;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public int getTreatmentCategoryId() {
        return treatmentCategoryId;
    }

    public void setTreatmentCategoryId(int treatmentCategoryId) {
        this.treatmentCategoryId = treatmentCategoryId;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

