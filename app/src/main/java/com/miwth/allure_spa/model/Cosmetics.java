package com.miwth.allure_spa.model;

import java.util.Date;

public class Cosmetics {
    private int id;
    private String cosmetics_name;
    private int cosmetics_category_id;
    private String descriptions;
    private int price;
    private String purpose;
    private String ingredients;
    private String how_to_use;
    private String image;
    private Date created_at;
    private Date last_update;

    public Cosmetics(int id, String cosmeticsName, int cosmeticsCategoryId, String descriptions,
                     int price, String purpose, String ingredients, String howToUse, String image,
                     Date createdAt, Date lastUpdate) {
        this.id = id;
        this.cosmetics_name = cosmeticsName;
        this.cosmetics_category_id = cosmeticsCategoryId;
        this.descriptions = descriptions;
        this.price = price;
        this.purpose = purpose;
        this.ingredients = ingredients;
        this.how_to_use = howToUse;
        this.image = image;
        this.created_at = createdAt;
        this.last_update = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCosmetics_name() {
        return cosmetics_name;
    }

    public void setCosmetics_name(String cosmetics_name) {
        this.cosmetics_name = cosmetics_name;
    }

    public int getCosmetics_category_id() {
        return cosmetics_category_id;
    }

    public void setCosmetics_category_id(int cosmetics_category_id) {
        this.cosmetics_category_id = cosmetics_category_id;
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

    public String getHow_to_use() {
        return how_to_use;
    }

    public void setHow_to_use(String how_to_use) {
        this.how_to_use = how_to_use;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "Cosmetics{" +
                "id=" + id +
                ", cosmetics_name='" + getCosmetics_name() + '\'' +
                ", cosmetics_category_id=" + getCosmetics_category_id() +
                ", descriptions='" + getDescriptions() + '\'' +
                ", price=" + getPrice() +
                ", purpose='" + getPurpose() + '\'' +
                ", ingredients='" + getIngredients() + '\'' +
                ", how_to_use='" + getHow_to_use() + '\'' +
                ", image='" + getImage() + '\'' +
                ", created_at=" + getCreated_at() +
                ", last_update=" + getLast_update() +
                '}';
    }
}

