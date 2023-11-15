package com.miwth.allure_spa.model;

import java.util.Date;

public class Cosmetics {
    private int id;
    private String cosmeticsName;
    private int cosmeticsCategoryId;
    private String descriptions;
    private int price;
    private String purpose;
    private String ingredients;
    private String howToUse;
    private String image;
    private Date createdAt;
    private Date lastUpdate;

    public Cosmetics(int id, String cosmeticsName, int cosmeticsCategoryId, String descriptions,
                     int price, String purpose, String ingredients, String howToUse, String image,
                     Date createdAt, Date lastUpdate) {
        this.id = id;
        this.cosmeticsName = cosmeticsName;
        this.cosmeticsCategoryId = cosmeticsCategoryId;
        this.descriptions = descriptions;
        this.price = price;
        this.purpose = purpose;
        this.ingredients = ingredients;
        this.howToUse = howToUse;
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

    public String getCosmeticsName() {
        return cosmeticsName;
    }

    public void setCosmeticsName(String cosmeticsName) {
        this.cosmeticsName = cosmeticsName;
    }

    public int getCosmeticsCategoryId() {
        return cosmeticsCategoryId;
    }

    public void setCosmeticsCategoryId(int cosmeticsCategoryId) {
        this.cosmeticsCategoryId = cosmeticsCategoryId;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getHowToUse() {
        return howToUse;
    }

    public void setHowToUse(String howToUse) {
        this.howToUse = howToUse;
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

