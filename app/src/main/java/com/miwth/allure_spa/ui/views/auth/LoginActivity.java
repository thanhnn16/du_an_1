package com.miwth.allure_spa.ui.views.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.auth.AuthResponse;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.api.auth.UserRepository;
import com.miwth.allure_spa.ui.views.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LOGIN_ACTIVITY";
    Button btnLogin;
    EditText edtPhoneNumber, edtPassword;
    UserRepository userRepository;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userRepository = new UserRepository();

        btnLogin = findViewById(R.id.btnLogin);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin.setOnClickListener(v -> {
            String phoneNumber = edtPhoneNumber.getText().toString();
            String password = edtPassword.getText().toString();
            userRepository.login(phoneNumber, password).enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() == null) {
                            Log.d(TAG, "onResponse: " + response.errorBody());
                            return;
                        }
                        AuthResponse authResponse = response.body();
                        if (authResponse.getToken() != null) {
                            Log.d(TAG, "onResponse: " + authResponse.getToken());
                            tokenManager = new TokenManager(LoginActivity.this);
                            tokenManager.saveToken(authResponse.getToken());
                            Log.d(TAG, "onResponse: " + tokenManager.getToken());
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finishAffinity();
                        }
                    } else {
                        try {
                            String errorBody = response.errorBody().string();
                            Log.d(TAG, "onResponse: " + errorBody);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<AuthResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        });

    }
}