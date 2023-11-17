package com.miwth.allure_spa.ui.views.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.ui.views.auth.LoginActivity;
import com.miwth.allure_spa.ui.views.auth.SendOTPActivity;
import com.miwth.allure_spa.ui.views.home.HomeActivity;

public class WelcomeActivity extends AppCompatActivity {
    CardView btnLogin;
    LinearLayout btnSignup;
    TextView tvSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getWindow().setStatusBarColor(getResources().getColor(R.color.primaryColor, null));

        btnLogin = findViewById(R.id.btnDangNhap);
        btnSignup = findViewById(R.id.btnDangKy);
        tvSkip = findViewById(R.id.tvDangKySau);

        tvSkip.setPaintFlags(tvSkip.getPaintFlags() | android.graphics.Paint.UNDERLINE_TEXT_FLAG);

        tvSkip.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this, HomeActivity.class)));
        btnLogin.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this, LoginActivity.class)));
        btnSignup.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this, SendOTPActivity.class)));

    }
}