package com.miwth.allure_spa.ui.views.chat;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.api.chat.ChatRepository;
import com.miwth.allure_spa.api.chat.MessagesResponse;
import com.miwth.allure_spa.model.Message;
import com.miwth.allure_spa.ui.adapter.ChatMessageAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";
    TokenManager tokenManager;

    private Toolbar toolbar;
    private ImageButton ibBack, btnSend;
    private TextView tvName, tvSeen;
    private ImageView ivAvt, send_image_chat, send_message_image;
    private RecyclerView rcvChat;
    private EditText edtComment;
    ChatRepository chatRepository;
    String token;
    ChatMessageAdapter chatMessageAdapter;

    ArrayList<MessagesResponse> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));
        setContentView(R.layout.activity_chat);

        tokenManager = new TokenManager(this);
        token = tokenManager.getToken();

        getMsgs();

        rcvChat = findViewById(R.id.rcvChat);


        toolbar = findViewById(R.id.toolbar);
        ibBack = findViewById(R.id.ibBack);
        tvName = findViewById(R.id.tvName);
        tvSeen = findViewById(R.id.tvSeen);
        ivAvt = findViewById(R.id.ivAvt);
//        send_image_chat = findViewById(R.id.send_image_chat);
        btnSend = findViewById(R.id.btnSend);
        rcvChat = findViewById(R.id.rcvChat);
        edtComment = findViewById(R.id.edtComment);

        ibBack.setOnClickListener(v -> finish());


        btnSend.setOnClickListener(v -> {
            String msg = edtComment.getText().toString();
            if (!msg.equals("")) {
                sendMessage(msg);
            } else {
                Toast.makeText(ChatActivity.this, "Vui lòng nhập tin nhắn cần gửi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMsgs() {
        chatRepository = new ChatRepository(token);

        Call<MessagesResponse> call = chatRepository.getMessages(tokenManager.getUserId());
        call.enqueue(new Callback<MessagesResponse>() {
            @Override
            public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                if (response.isSuccessful()) {
                    messages = new ArrayList<>(response.body().getMessages());
                    chatMessageAdapter = new ChatMessageAdapter(messages, 1, ChatActivity.this);
                    rcvChat.setAdapter(chatMessageAdapter);
                } else {
                    Toast.makeText(ChatActivity.this, "Lấy tin nhắn thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessagesResponse> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Lấy tin nhắn thất bại" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void sendMessage(String msg) {
        chatRepository = new ChatRepository(token);

        LocalDateTime now = LocalDateTime.now();

        String nowString = now.toString();

        Message message = new Message(tokenManager.getUserId(), nowString, msg);


        Call<MessagesResponse> call = chatRepository.sendMessages(message);
        call.enqueue(new Callback<MessagesResponse>() {
            @Override
            public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ChatActivity.this, "Gửi tin nhắn thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChatActivity.this, "Gửi tin nhắn thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessagesResponse> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Gửi tin nhắn thất bại", Toast.LENGTH_SHORT).show();
            }
        });


    }
}