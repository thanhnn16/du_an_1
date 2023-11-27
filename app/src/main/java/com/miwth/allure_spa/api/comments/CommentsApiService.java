package com.miwth.allure_spa.api.comments;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CommentsApiService {

    @GET("comments/{id}")
    Call<CommentsResponse> getComments(@Path("id") int id);

    @GET("comments")
    Call<CommentsResponse> getComments();
}