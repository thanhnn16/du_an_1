package com.miwth.allure_spa.ui.views.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.miwth.allure_spa.R;

public class PaymentActivity extends AppCompatActivity {

    ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ibBack = findViewById(R.id.ibBack);

        ibBack.setOnClickListener(v -> {
            finish();
        });
    }
}