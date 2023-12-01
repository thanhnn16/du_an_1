package com.miwth.allure_spa.api.comment;

import static com.miwth.allure_spa.api.ApiConstants.getRetrofit;

import retrofit2.Call;

public class CommentsRepository {
    private final CommentsApiService apiService;

    public CommentsRepository() {
        apiService = getRetrofit().create(CommentsApiService.class);
    }

    public Call<CommentsResponse> getComment(int id) {
        return apiService.getComments(id);
    }

    public Call<CommentsResponse> getComments() {
        return apiService.getComments();
    }
}