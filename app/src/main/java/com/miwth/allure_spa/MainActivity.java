package com.miwth.allure_spa;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

public class MainActivity extends AppCompatActivity {
    Boolean splashLoaded = false;

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashLoaded = true;
                setContentView(R.layout.activity_main);
            }
        }, 3000);
    }
}