package com.miwth.allure_spa.model;

import java.util.Date;

public class ShoppingCart {
    private int id;
    private int cosmeticsId;
    private int treatmentId;
    private int userId;
    private int quantity;
    private Date createdAt;
    private Date lastUpdate;

    public ShoppingCart(int id, int cosmeticsId, int treatmentId, int userId,
                        int quantity, Date createdAt, Date lastUpdate) {
        this.id = id;
        this.cosmeticsId = cosmeticsId;
        this.treatmentId = treatmentId;
        this.userId = userId;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }
}

