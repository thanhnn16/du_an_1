package com.miwth.allure_spa.views.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.adapter.OnboardingAdapter;
import com.miwth.allure_spa.models.OnboardingModel;

import java.util.ArrayList;

public class OnboardingActivity extends AppCompatActivity {
    OnboardingAdapter onboardingAdapter;
    //    private static final int NUM_PAGES = 3;
    ArrayList<OnboardingModel> onboardingModels = new ArrayList<>();
    ViewPager2 viewPager2;
    ImageButton btnNext;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            getWindow().setStatusBarContrastEnforced(true);
        }
        getWindow().setStatusBarColor(getResources().getColor(R.color.onboarding_bg, null));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.onboarding_bg, null));

        initializeOnboardingItems();

        btnNext.setOnClickListener(v -> {
            startActivity(new Intent(OnboardingActivity.this, WelcomeActivity.class));
            editor.putBoolean("firstTime", false);
            editor.apply();
            finish();
        });

    }

    private void initializeOnboardingItems() {
        viewPager2 = findViewById(R.id.vp_onboarding);

        sharedPreferences = getSharedPreferences("onboarding", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        onboardingModels.add(new OnboardingModel(
                getString(R.string.spa_name),
                getString(R.string.onboarding_des_1),
                R.drawable.onboarding_1
        ));

        onboardingModels.add(new OnboardingModel(
                getString(R.string.spa_name),
                getString(R.string.onboarding_des_2),
                R.drawable.onboarding_2
        ));

        onboardingModels.add(new OnboardingModel(
                getString(R.string.spa_name),
                getString(R.string.onboarding_des_1),
                R.drawable.onboarding_3
        ));

        onboardingAdapter = new OnboardingAdapter(this, onboardingModels);
        viewPager2.setAdapter(onboardingAdapter);

        btnNext = findViewById(R.id.btnNext);
    }
}