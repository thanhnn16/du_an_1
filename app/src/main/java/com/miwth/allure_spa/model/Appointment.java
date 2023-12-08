package com.miwth.allure_spa.model;

import com.squareup.moshi.Json;

import java.time.LocalDateTime;
import java.util.Date;

public class Appointment {
    private int id;
    @Json(name = "user_id")
    private int userId;
    @Json(name = "treatment_id")
    private int treatmentId;
    @Json(name = "start_date")
    private LocalDateTime startDate;
    @Json(name = "end_date")
    private LocalDateTime endDate;
    @Json(name = "is_consultation")
    private int isConsultation;
    @Json(name = "is_all_day")
    private int isAllDay;
    private String status;
    private String note;
    @Json(name = "created_at")
    private LocalDateTime createdAt;
    @Json(name = "updated_at")
    private LocalDateTime updatedAt;

    public Appointment(int userId, int treatmentId, LocalDateTime startDate, LocalDateTime endDate, int isConsultation, int isAllDay, String status, String note) {
        this.userId = userId;
        this.treatmentId = treatmentId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isConsultation = isConsultation;
        this.isAllDay = isAllDay;
        this.status = status;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public int isConsultation() {
        return isConsultation;
    }

    public int isAllDay() {
        return isAllDay;
    }

    public String getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

