package com.miwth.allure_spa.model;

public class Comment {


    private String userName;
    private float rating;
    private String time;
    private String content;
    private boolean useful;

    public Comment(String userName, float rating, String time, String content, boolean useful) {
        this.userName = userName;
        this.rating = rating;
        this.time = time;
        this.content = content;
        this.useful = useful;
    }

    public Comment() {
    }

    public String getUserName() {
        return userName;
    }

    public float getRating() {
        return rating;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public boolean isUseful() {
        return useful;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUseful(boolean useful) {
        this.useful = useful;
    }

}