package com.miwth.allure_spa.model;

import com.squareup.moshi.Json;

import java.util.Date;

public class User {
    private int id;
    private String email;
    @Json(name = "phone_number")
    private String phoneNumber;
    @Json(name = "full_name")
    private String fullName;
    private String address;
    @Json(name = "date_of_birth")
    private Date dateOfBirth;
    @Json(name = "skin_condition")
    private String skinCondition;
    private boolean gender;
    private String image;
    private String note;

    public User(int id, String email, String phoneNumber, String fullName, String address, Date dateOfBirth, String skinCondition, boolean gender, String image, String note) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.skinCondition = skinCondition;
        this.gender = gender;
        this.image = image;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSkinCondition() {
        return skinCondition;
    }

    public boolean isGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }

    public String getNote() {
        return note;
    }

}
