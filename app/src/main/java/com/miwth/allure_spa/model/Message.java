package com.miwth.allure_spa.model;

public class Message {
    private int id;
    private int sender_id;
    private int receiver_id;
    private String message;
    private String sent_at;
    private String status;
    private String created_at;
    private String updated_at;

    public Message() {
    }

    public Message(int sender_id, String message, String sent_at) {
        this.sender_id = sender_id;
        this.receiver_id = 1;
        this.message = message;
        this.sent_at = sent_at;
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

    public String getSent_at() {
        return sent_at;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender_id=" + sender_id +
                ", receiver_id=" + receiver_id +
                ", message='" + message + '\'' +
                ", sent_at='" + sent_at + '\'' +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}


