package com.miwth.allure_spa.api.chat;

import java.util.Date;
import java.util.List;

public class MessagesResponse {
    private int id;
    private int sender_id;
    private int receiver_id;
    private String message;
    private Date sent_at;
    private String status;
    private Date created_at;
    private Date updated_at;
    private Sender sender;


    List<MessagesResponse> messages;

    public MessagesResponse(int id, int sender_id, int receiver_id, String message, Date sent_at, String status, Date created_at, Date updated_at, Sender sender, List<MessagesResponse> messages) {
        this.id = id;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.message = message;
        this.sent_at = sent_at;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.sender = sender;
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public String getMessage() {
        return message;
    }

    public Date getSent_at() {
        return sent_at;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public Sender getSender() {
        return sender;
    }

    public List<MessagesResponse> getMessages() {
        return messages;
    }
}

class Sender {
    private int id;
    private String phone_number;
    private String email;
    private String role;
    private String full_name;
    private int gender;
    private String address;
    private String date_of_birth;
    private String skin_condition;
    private String image;
    private String note;
    private Date created_at;
    private Date     updated_at;

    public Sender() {
    }

    public Sender(int id, String phone_number, String email, String role, String full_name, int gender, String address, String date_of_birth, String skin_condition, String image, String note, Date created_at, Date updated_at) {
        this.id = id;
        this.phone_number = phone_number;
        this.email = email;
        this.role = role;
        this.full_name = full_name;
        this.gender = gender;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.skin_condition = skin_condition;
        this.image = image;
        this.note = note;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getFull_name() {
        return full_name;
    }

    public int getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getSkin_condition() {
        return skin_condition;
    }

    public String getImage() {
        return image;
    }

    public String getNote() {
        return note;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }
}