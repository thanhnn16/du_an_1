package com.miwth.allure_spa.model;

import com.squareup.moshi.Json;

import java.util.Date;

public class Cosmetics {
    private int id;
    @Json(name = "cosmetic_name")
    private String cosmeticsName;
    @Json(name = "cosmetic_category_id")
    private int cosmeticsCategoryId;
    private String description;
    private int price;
    private String purpose;
    private String ingredients;
    @Json(name = "how_to_use")
    private String howToUse;
    private String image;
    @Json(name = "created_at")
    private Date createdAt;
    @Json(name = "updated_at")
    private Date updatedAt;

    public Cosmetics(int id, String cosmeticsName, int cosmeticsCategoryId, String descriptions, int price, String purpose, String ingredients, String howToUse, String image, Date createdAt, Date lastUpdate) {
        this.id = id;
        this.cosmeticsName = cosmeticsName;
        this.cosmeticsCategoryId = cosmeticsCategoryId;
        this.description = descriptions;
        this.price = price;
        this.purpose = purpose;
        this.ingredients = ingredients;
        this.howToUse = howToUse;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = lastUpdate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Cosmetics{" + "id=" + id + ", cosmetics_name='" + getCosmeticsName() + '\'' + ", cosmetics_category_id=" + getCosmeticsCategoryId() + ", descriptions='" + getDescription() + '\'' + ", price=" + getPrice() + ", purpose='" + getPurpose() + '\'' + ", ingredients='" + getIngredients() + '\'' + ", how_to_use='" + getHowToUse() + '\'' + ", image='" + getImage() + '\'' + ", created_at=" + getCreatedAt() + ", last_update=" + getUpdatedAt() + '}';
    }
}

