package com.miwth.allure_spa.ui.views.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.miwth.allure_spa.R;

public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton ibBack;
    private TextView tvName, tvSeen;
    private ImageView ivAvt, send_image_chat, send_message_image;
    private RecyclerView rcvChat;
    private EditText edtComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.toolbar);
        ibBack = findViewById(R.id.ibBack);
        tvName = findViewById(R.id.tvName);
        tvSeen = findViewById(R.id.tvSeen);
        ivAvt = findViewById(R.id.ivAvt);
        send_image_chat = findViewById(R.id.send_image_chat);
        send_message_image = findViewById(R.id.send_message_image);
        rcvChat = findViewById(R.id.rcvChat);
        edtComment = findViewById(R.id.edtComment);
    }
}