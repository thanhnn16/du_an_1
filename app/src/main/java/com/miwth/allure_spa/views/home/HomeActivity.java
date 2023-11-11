package com.miwth.allure_spa.views.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.views.home.fragment.HomeFragment;
import com.miwth.allure_spa.views.home.fragment.NotificationFragment;
import com.miwth.allure_spa.views.home.fragment.ProfileFragment;
import com.miwth.allure_spa.views.home.fragment.SearchFragment;

public class HomeActivity extends AppCompatActivity {
    Fragment homeFragment, searchFragment, notificationFragment, profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));

        init();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();


    }

    private void init() {
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        notificationFragment = new NotificationFragment();
        profileFragment = new ProfileFragment();

    }
}