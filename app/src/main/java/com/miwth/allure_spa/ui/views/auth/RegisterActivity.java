package com.miwth.allure_spa.ui.views.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.auth.AuthResponse;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.api.auth.UserRepository;
import com.miwth.allure_spa.ui.views.home.HomeActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "REGISTER_ACTIVITY";
    String phoneNumber;
    TextView tvPhoneNumber;
    EditText edtPassword, edtConfirmPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();
        phoneNumber = "0" + intent.getStringExtra("phoneNumber");
        Log.d(TAG, "onCreate: " + phoneNumber);

        tvPhoneNumber = findViewById(R.id.tv_phone_number);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtRePassword);
        btnRegister = findViewById(R.id.btnSignup);

        tvPhoneNumber.setText(phoneNumber);

        btnRegister.setOnClickListener(v -> {
            String password = edtPassword.getText().toString();
            String confirmPassword = edtConfirmPassword.getText().toString();
            if (password.equals(confirmPassword)) {
                UserRepository userRepository = new UserRepository();
                userRepository.register(phoneNumber, password).enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() == null) {
                                return;
                            }
                            TokenManager tokenManager = new TokenManager(RegisterActivity.this);
                            tokenManager.saveToken(response.body().getToken());
                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            try {
                                String errorResponse = response.errorBody() != null ? response.errorBody().string() : "";
                                Log.d(TAG, "onResponse: " + errorResponse);
                                if (errorResponse.contains(phoneNumber)) {
                                    tvPhoneNumber.setTextColor(getResources().getColor(R.color.red, null));
                                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.phone_number_already_exists), Toast.LENGTH_SHORT).show();
                                } else {
                                    edtPassword.setError(getResources().getString(R.string.error));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });

            } else {
                edtConfirmPassword.setError(getResources().getString(R.string.password_not_match));
            }
        });

    }
}