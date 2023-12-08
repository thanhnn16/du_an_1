package com.miwth.allure_spa.model;

import com.squareup.moshi.Json;

import java.util.Date;

public class Invoice {
    private int id;
    @Json(name = "user_id")
    private int userId;
    @Json(name = "voucher_id")
    private int voucherId;
    private String note;
    private String status;
    @Json(name = "created_at")
    private Date createdAt;
    @Json(name = "updated_at")
    private Date updatedAt;

    public Invoice() {
    }

    public Invoice(int id, int userId, int voucherId, String note, String status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.voucherId = voucherId;
        this.note = note;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public String getNote() {
        return note;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
