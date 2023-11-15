package com.miwth.allure_spa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.miwth.allure_spa.ui.views.home.HomeActivity;
import com.miwth.allure_spa.ui.views.welcome.OnboardingActivity;
import com.miwth.allure_spa.ui.views.welcome.WelcomeActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_ACTIVITY";
    Boolean splashLoaded = false;

    SharedPreferences sharedPreferences;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                );


        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        loading();

        splashScreen.setKeepOnScreenCondition(() -> {
            return !splashLoaded;
        });

    }

    private void loading() {
        sharedPreferences = getSharedPreferences("onboarding", MODE_PRIVATE);
        Boolean firstTime = sharedPreferences.getBoolean("firstTime", true);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        Log.d(TAG, "loading: " + mUser);
        new Handler().postDelayed(() -> {
            splashLoaded = true;
            if (mUser != null) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            } else if (firstTime) {
                startActivity(new Intent(MainActivity.this, OnboardingActivity.class));
                finish();
            } else {
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                finish();
            }
        }, 1000);
    }
}