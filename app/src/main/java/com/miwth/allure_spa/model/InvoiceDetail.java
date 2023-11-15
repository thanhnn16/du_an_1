package com.miwth.allure_spa.model;

import java.util.Date;

public class InvoiceDetail {
    private int id;
    private int invoiceId;
    private int treatmentId;
    private int cosmeticsId;
    private int treatmentQuantity;
    private int cosmeticsQuantity;
    private int totalAmount;
    private String note;
    private Date createdAt;
    private Date lastUpdate;

    public InvoiceDetail(int id, int invoiceId, int treatmentId, int cosmeticsId,
                         int treatmentQuantity, int cosmeticsQuantity, int totalAmount,
                         String note, Date createdAt, Date lastUpdate) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.treatmentId = treatmentId;
        this.cosmeticsId = cosmeticsId;
        this.treatmentQuantity = treatmentQuantity;
        this.cosmeticsQuantity = cosmeticsQuantity;
        this.totalAmount = totalAmount;
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

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public int getCosmeticsId() {
        return cosmeticsId;
    }

    public void setCosmeticsId(int cosmeticsId) {
        this.cosmeticsId = cosmeticsId;
    }

    public int getTreatmentQuantity() {
        return treatmentQuantity;
    }

    public void setTreatmentQuantity(int treatmentQuantity) {
        this.treatmentQuantity = treatmentQuantity;
    }

    public int getCosmeticsQuantity() {
        return cosmeticsQuantity;
    }

    public void setCosmeticsQuantity(int cosmeticsQuantity) {
        this.cosmeticsQuantity = cosmeticsQuantity;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
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

