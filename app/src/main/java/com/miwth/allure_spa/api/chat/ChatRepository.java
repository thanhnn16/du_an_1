package com.miwth.allure_spa.api.chat;

import com.miwth.allure_spa.api.ApiConstants;
import com.miwth.allure_spa.model.Message;

import retrofit2.Call;

public class ChatRepository {
    private final ChatApiService chatApiService;

    public ChatRepository(String token) {
        chatApiService = ApiConstants.getRetrofitWithAuth(token).create(ChatApiService.class);
    }

    public Call<MessagesResponse> getMessages(int userId) {
        return chatApiService.getMessages(userId);
    }

    public Call<MessagesResponse> sendMessages(Message message) {
        return chatApiService.sendMessages(message);
    }

}
