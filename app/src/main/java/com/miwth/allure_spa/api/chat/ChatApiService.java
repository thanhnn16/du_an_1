package com.miwth.allure_spa.api.chat;

import com.miwth.allure_spa.api.comment.CommentsResponse;
import com.miwth.allure_spa.model.Message;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChatApiService {

    @GET("messages/{userId}")
    Call<MessagesResponse> getMessages(@Path("userId") int id);

    @POST("messages")
    Call<MessagesResponse> sendMessages(@Body Message message);
}