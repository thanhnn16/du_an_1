package com.miwth.allure_spa.model;

import com.squareup.moshi.Json;

public class Search {
    int id;
    @Json(name = "name")
    private String title;
    private String description;
    @Json(name = "image")
    private String imageUrl;

    private String type;
    private int price;


    public Search(int id, String title, String description, String imageUrl, String type, int price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.type = type;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }
}