package com.miwth.allure_spa.model;

import java.util.Date;

public class CosmeticsCategory {
    private int id;
    private String cosmeticsCategoryName;
    private Date createdAt;
    private Date lastUpdate;

    public CosmeticsCategory(int id, String cosmeticsCategoryName, Date createdAt, Date lastUpdate) {
        this.id = id;
        this.cosmeticsCategoryName = cosmeticsCategoryName;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCosmeticsCategoryName() {
        return cosmeticsCategoryName;
    }

    public void setCosmeticsCategoryName(String cosmeticsCategoryName) {
        this.cosmeticsCategoryName = cosmeticsCategoryName;
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

