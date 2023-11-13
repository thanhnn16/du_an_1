package com.miwth.allure_spa.views.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.miwth.allure_spa.R;
import com.miwth.allure_spa.callback.SideMenuCallBack;
import com.miwth.allure_spa.views.home.fragment.HomeFragment;
import com.miwth.allure_spa.views.home.fragment.NotificationFragment;
import com.miwth.allure_spa.views.home.fragment.ProfileFragment;
import com.miwth.allure_spa.views.home.fragment.SearchFragment;
import com.miwth.allure_spa.views.welcome.WelcomeActivity;

public class HomeActivity extends AppCompatActivity implements SideMenuCallBack {
    private static final String TAG = "HOME_ACTIVITY";
    Fragment homeFragment, searchFragment, notificationFragment, profileFragment;
    NavigationView navigationView;
    FirebaseAuth mAuth;
    DrawerLayout drawerLayout;
    private long pressedTime;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));

        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                if (pressedTime + 2000 > System.currentTimeMillis()) {
                    finishAffinity();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Nhấn trở lại lần nữa để thoát", Toast.LENGTH_SHORT).show();
                }
                pressedTime = System.currentTimeMillis();

            }
        });


        init();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.account_info) {
                Log.d(TAG, "onCreate: account info");
                return true;
            } else if (id == R.id.logout) {
                Log.d(TAG, "onCreate: logout");
                mAuth.signOut();
                startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));
                finishAffinity();
                return true;
            } else {
                Log.d(TAG, "navigation: " + id);
                return false;
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.homeFragment) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
            } else if (id == R.id.searchFragment) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, searchFragment).commit();
            } else if (id == R.id.notificationsFragment) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, notificationFragment).commit();
            } else if (id == R.id.profileFragment) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();
            }
            return true;
        });

    }

    private void init() {
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        notificationFragment = new NotificationFragment();
        profileFragment = new ProfileFragment();
        drawerLayout = findViewById(R.id.drawerLayout);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        navigationView = findViewById(R.id.navigationView);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onMenuIconClick() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}