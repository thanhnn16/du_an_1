package com.miwth.allure_spa.model;

import com.squareup.moshi.Json;

import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String email;
    @Json(name = "phone_number")
    private String phoneNumber;
    @Json(name = "full_name")
    private String fullName;
    private String address;
    @Json(name = "date_of_birth")
    private String dateOfBirth;
    @Json(name = "skin_condition")
    private String skinCondition;
    private int gender;
    private String image;
    private String note;
    private List<Appointment> appointments; // add this field
    private List<Invoice> invoices; // add this field

    public User(String email, String fullName, String address, String dateOfBirth, String skinCondition, int gender, String image) {
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.skinCondition = skinCondition;
        this.gender = gender;
        this.image = image;
    }

    public User(int id, String email, String phoneNumber, String fullName, String address, String dateOfBirth, String skinCondition, int gender, String image, String note, List<Appointment> appointments, List<Invoice> invoices) {
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
        this.appointments = appointments;
        this.invoices = invoices;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSkinCondition() {
        return skinCondition;
    }

    public int getGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }

    public String getNote() {
        return note;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", skinCondition='" + skinCondition + '\'' +
                ", gender=" + gender +
                ", image='" + image + '\'' +
                ", note='" + note + '\'' +
                ", appointments=" + appointments +
                ", invoices=" + invoices +
                '}';
    }
}