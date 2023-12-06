package com.miwth.allure_spa.ui.views.home.fragment.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Switch;

import com.miwth.allure_spa.R;

public class Settings extends AppCompatActivity {

    ImageButton btnBack;

    SwitchCompat switch_notification, switch_update, switch_dark_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));


        switch_notification = findViewById(R.id.switch_notification);
        switch_update = findViewById(R.id.switch_update);
        switch_dark_mode = findViewById(R.id.switch_dark_mode);

        btnBack = findViewById(R.id.back);

        btnBack.setOnClickListener(v -> {
            finish();
        });

        setSwitch();
    }
    private void setSwitch() {
        //switch_notification, switch_update, switch_dark_mode
        switch_notification.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                switch_notification.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(Settings.this,R.color.primaryColor)));
                switch_notification.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(Settings.this, R.color.primaryColor)));
            }else {
                switch_notification.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(Settings.this,R.color.gray)));
                switch_notification.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(Settings.this, R.color.gray)));
            }
        });

        switch_update.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                switch_update.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(Settings.this,R.color.primaryColor)));
                switch_update.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(Settings.this, R.color.primaryColor)));
            }else {
                switch_update.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(Settings.this,R.color.gray)));
                switch_update.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(Settings.this, R.color.gray)));
            }
        });

        switch_dark_mode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                switch_dark_mode.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(Settings.this,R.color.primaryColor)));
                switch_dark_mode.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(Settings.this, R.color.primaryColor)));
            }else {
                switch_dark_mode.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(Settings.this,R.color.gray)));
                switch_dark_mode.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(Settings.this, R.color.gray)));
            }
        });

    }

}


