package com.miwth.allure_spa.model;

import java.util.Date;

public class Invoice {
    private int id;
    private int userId;
    private Date createdAt;
    private Date lastUpdate;

    public Invoice(int id, int userId, Date createdAt, Date lastUpdate) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
