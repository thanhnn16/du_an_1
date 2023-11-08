package com.miwth.allure_spa.views.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(true);
        }
        getWindow().setStatusBarColor(getResources().getColor(R.color.onboarding_bg, null));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.onboarding_bg, null));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        initializeOnboardingItems();

        btnNext.setOnClickListener(v -> {
            startActivity(new Intent(OnboardingActivity.this, WelcomeActivity.class));
            finish();
        });

    }

    private void initializeOnboardingItems() {
        viewPager2 = findViewById(R.id.vp_onboarding);

        onboardingModels.add(new OnboardingModel(
                getString(R.string.spa_name),
                getString(R.string.onboarding_des_1),
                R.drawable.onboarding_1
                ));

        onboardingAdapter = new OnboardingAdapter(this, onboardingModels);
        viewPager2.setAdapter(onboardingAdapter);

        btnNext = findViewById(R.id.btnNext);
    }
}