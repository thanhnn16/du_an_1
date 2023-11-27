package com.miwth.allure_spa.api.comment;

import com.miwth.allure_spa.model.Comment;

import java.util.List;

public class CommentsResponse {
    private boolean success;
    private String message;
    private List<Comment> comments;

    public CommentsResponse(boolean success, String message, List<Comment> comments) {
        this.success = success;
        this.message = message;
        this.comments = comments;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Comment> getComments() {
        return comments;
    }
}