package com.miwth.allure_spa.model;

import java.util.Date;

public class Appointment {
    private int id;
    private int userId;
    private int treatmentId;
    private Date appointmentDate;
    private String status;
    private String note;
    private Date createdAt;
    private Date lastUpdate;

    public Appointment(int id, int userId, int treatmentId, Date appointmentDate,
                       String status, String note, Date createdAt, Date lastUpdate) {
        this.id = id;
        this.userId = userId;
        this.treatmentId = treatmentId;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.note = note;
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

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

