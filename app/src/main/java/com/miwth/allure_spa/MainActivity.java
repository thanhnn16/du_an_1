package com.miwth.allure_spa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.ui.views.RateAndReviews.Rating_Reviews;
import com.miwth.allure_spa.ui.views.cosmetic.CosmeticDetailActivity;
import com.miwth.allure_spa.ui.views.home.HomeActivity;
import com.miwth.allure_spa.ui.views.home.fragment.NotificationFragment;
import com.miwth.allure_spa.ui.views.treatment.BookService.BookInformation;
import com.miwth.allure_spa.ui.views.treatment.TreatmentDetails;
import com.miwth.allure_spa.ui.views.welcome.OnboardingActivity;
import com.miwth.allure_spa.ui.views.welcome.WelcomeActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_ACTIVITY";
    Boolean splashLoaded = false;

    SharedPreferences sharedPreferences;

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
        splashScreen.setKeepOnScreenCondition(() -> !splashLoaded);

    }

    private void loading() {
        sharedPreferences = getSharedPreferences("onboarding", MODE_PRIVATE);
        boolean firstTime = sharedPreferences.getBoolean("firstTime", true);
        TokenManager tokenManager = new TokenManager(this);
        Log.d(TAG, "logout: " + tokenManager.getToken());
        new Handler().postDelayed(() -> {
            splashLoaded = true;
            if (!tokenManager.getToken().isEmpty()) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            } else if (firstTime) {
                startActivity(new Intent(MainActivity.this, OnboardingActivity.class));
                finish();
            } else {

//                bàn tay của thần
//                startActivity(new Intent(MainActivity.this, BookInformation.class));
//                startActivity(new Intent(MainActivity.this, CosmeticDetailActivity.class));
//                startActivity(new Intent(MainActivity.this, Rating_Reviews.class));
//                startActivity(new Intent(MainActivity.this, TreatmentDetails.class));
                startActivity(new Intent(MainActivity.this, NotificationFragment.class));

//                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                finish();
            }
        }, 1000);
    }
}
