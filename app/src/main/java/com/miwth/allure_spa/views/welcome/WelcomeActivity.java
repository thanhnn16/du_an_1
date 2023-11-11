package com.miwth.allure_spa.views.welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.views.auth.SendOTPActivity;

public class WelcomeActivity extends AppCompatActivity {
    CardView btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getWindow().setStatusBarColor(getResources().getColor(R.color.primaryColor, null));

        btnLogin = findViewById(R.id.btnDangNhap);

        btnLogin.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this, SendOTPActivity.class)));

    }
}