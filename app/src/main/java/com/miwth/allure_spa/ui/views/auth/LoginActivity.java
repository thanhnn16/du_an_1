package com.miwth.allure_spa.ui.views.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));

        userRepository = new UserRepository();

        btnLogin = findViewById(R.id.btnLogin);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtPassword = findViewById(R.id.edtPassword);

        edtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInputs();
            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInputs();
            }
        });

        btnLogin.setOnClickListener(v -> {
            String phoneNumber = edtPhoneNumber.getText().toString();
            String password = edtPassword.getText().toString();
            userRepository.login(phoneNumber, password).enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
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
                            tokenManager.saveUserId(authResponse.getUserId());
                            if (authResponse.getFullName() != null) {
                                tokenManager.saveFullName(authResponse.getFullName());
                            }
                            if (authResponse.getImage() != null) {
                                tokenManager.saveImage(authResponse.getImage());
                            }
                            tokenManager.savePhoneNumber(authResponse.getPhoneNumber());

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

    private void checkInputs() {
        String phoneNumber = edtPhoneNumber.getText().toString();
        String password = edtPassword.getText().toString();

        if (phoneNumber.isEmpty() || password.isEmpty()) {
            if (phoneNumber.isEmpty()) {
                edtPhoneNumber.setError("Vui lòng nhập số điện thoại");
            }
            if (password.isEmpty()) {
                edtPassword.setError("Vui lòng nhập mật khẩu");
            }
            btnLogin.setBackgroundColor(getResources().getColor(R.color.primaryColorDisabled, null));
            btnLogin.setEnabled(false);
        } else if (phoneNumber.length() < 10) {
            edtPhoneNumber.setError("Số điện thoại phải có ít nhất 10 số");
            btnLogin.setBackgroundColor(getResources().getColor(R.color.primaryColorDisabled, null));
            btnLogin.setEnabled(false);
        } else if (password.length() < 6) {
            edtPassword.setError("Mật khẩu phải có ít nhất 6 kí tự");
            btnLogin.setBackgroundColor(getResources().getColor(R.color.primaryColorDisabled, null));
            btnLogin.setEnabled(false);
        } else {
            btnLogin.setBackgroundColor(getResources().getColor(R.color.primaryColor, null));
            btnLogin.setEnabled(true);
        }
    }
}