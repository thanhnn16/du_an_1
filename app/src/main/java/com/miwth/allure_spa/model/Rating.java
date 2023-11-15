package com.miwth.allure_spa.model;

import java.util.Date;

public class Rating {
    private int id;
    private int userId;
    private int numberStar;
    private String image;
    private Date createdAt;
    private Date lastUpdate;

    public Rating(int id, int userId, int numberStar, String image, Date createdAt, Date lastUpdate) {
        this.id = id;
        this.userId = userId;
        this.numberStar = numberStar;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumberStar() {
        return numberStar;
    }

    public void setNumberStar(int numberStar) {
        this.numberStar = numberStar;
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
