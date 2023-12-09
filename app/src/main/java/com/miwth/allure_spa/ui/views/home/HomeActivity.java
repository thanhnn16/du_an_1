package com.miwth.allure_spa.ui.views.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.ui.views.chat.ChatActivity;
import com.miwth.allure_spa.ui.views.home.fragment.HomeFragment;
import com.miwth.allure_spa.ui.views.home.fragment.NotificationFragment;
import com.miwth.allure_spa.ui.views.home.fragment.profile.ProfileFragment;
import com.miwth.allure_spa.ui.views.home.fragment.SearchFragment;
import com.miwth.allure_spa.ui.views.welcome.WelcomeActivity;
import com.miwth.allure_spa.util.callback.BackButtonCallBack;
import com.miwth.allure_spa.util.callback.SideMenuCallBack;

public class HomeActivity extends AppCompatActivity implements SideMenuCallBack, BackButtonCallBack {
    private static final String TAG = "HOME_ACTIVITY";
    Fragment homeFragment, searchFragment, notificationFragment, profileFragment;
    NavigationView navigationView;
    FirebaseAuth mAuth;
    DrawerLayout drawerLayout;
    private long pressedTime;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton fabChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));
        AndroidThreeTen.init(this);
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

        bottomNavigationView.setOnItemSelectedListener(item -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (homeFragment != null) transaction.hide(homeFragment);
            if (searchFragment != null) transaction.hide(searchFragment);
            if (notificationFragment != null) transaction.hide(notificationFragment);
            if (profileFragment != null) transaction.hide(profileFragment);

            int itemId = item.getItemId();
            if (itemId == R.id.homeFragment) {
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fragment_container, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
            } else if (itemId == R.id.searchFragment) {
                if (searchFragment == null) {
                    searchFragment = new SearchFragment();
                    transaction.add(R.id.fragment_container, searchFragment);
                } else {
                    transaction.show(searchFragment);
                }
            } else if (itemId == R.id.notificationsFragment) {
                if (notificationFragment == null) {
                    notificationFragment = new NotificationFragment();
                    transaction.add(R.id.fragment_container, notificationFragment);
                } else {
                    transaction.show(notificationFragment);
                }
            } else if (itemId == R.id.profileFragment) {
                TokenManager tokenManager = new TokenManager(this);
                if (tokenManager.getToken().isEmpty()) {
                    Toast.makeText(this, "Bạn cần đăng nhập để sử dụng chức năng này", Toast.LENGTH_SHORT).show();
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                        transaction.add(R.id.fragment_container, homeFragment);
                    } else {
                        transaction.show(homeFragment);
                    }
                    bottomNavigationView.setSelectedItemId(R.id.homeFragment);

                } else {
                    if (profileFragment == null) {
                        profileFragment = new ProfileFragment();
                        transaction.add(R.id.fragment_container, profileFragment);
                    } else {
                        transaction.show(profileFragment);
                    }
                }
            }
            transaction.commit();
            return true;
        });

    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();

        homeFragment = new HomeFragment();
//        searchFragment = new SearchFragment();
//        notificationFragment = new NotificationFragment();
//        profileFragment = new ProfileFragment();

        fabChat = findViewById(R.id.fab);
        fabChat.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ChatActivity.class)));

        drawerLayout = findViewById(R.id.drawerLayout);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            Log.d(TAG, "Navigation item selected: " + id);
            if (id == R.id.account_info) {
                Log.d(TAG, "onCreate: account info");
            } else if (id == R.id.logout) {
                Log.d(TAG, "onCreate: logout");
                SharedPreferences sharedPreferences = getSharedPreferences("api_tokens", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Log.d(TAG, "logout: " + sharedPreferences.getString("token", ""));
                mAuth.signOut();
                startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));
                finishAffinity();
            } else {
                Log.d(TAG, "navigation: " + id);
            }
            return true;
        });

    }

    @Override
    public void onMenuIconClick() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackButtonClick() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            transaction.add(R.id.fragment_container, homeFragment);
            transaction.show(homeFragment);
        } else {
            transaction.show(homeFragment);
        }
        transaction.commit();
        bottomNavigationView.setSelectedItemId(R.id.homeFragment);
    }
}