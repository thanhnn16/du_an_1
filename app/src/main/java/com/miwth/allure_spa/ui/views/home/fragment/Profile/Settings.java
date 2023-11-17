package com.miwth.allure_spa.ui.views.home.fragment.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.miwth.allure_spa.R;

public class Settings extends AppCompatActivity {

    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnBack = findViewById(R.id.back);

        btnBack.setOnClickListener(v -> {
            finish();
        });







    }
}