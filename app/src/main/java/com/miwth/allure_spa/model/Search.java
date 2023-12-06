package com.miwth.allure_spa.model;

import com.squareup.moshi.Json;

public class Search {
    @Json(name = "name")
    private String title;
    private String description;
    @Json(name = "image")
    private String imageUrl;

    public Search(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
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
}