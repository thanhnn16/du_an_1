package com.miwth.allure_spa.ui.views.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.auth.AuthResponse;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.api.auth.UserRepository;
import com.miwth.allure_spa.ui.views.home.HomeActivity;
import com.miwth.allure_spa.ui.views.home.fragment.profile.UserDetailActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "REGISTER_ACTIVITY";
    String phoneNumber;
    TextView tvPhoneNumber;
    EditText edtPassword, edtConfirmPassword;
    Button btnRegister, btnLogin, btnFillInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));

        Intent intent = getIntent();
        phoneNumber = "0" + intent.getStringExtra("phoneNumber");
        Log.d(TAG, "onCreate: " + phoneNumber);

        tvPhoneNumber = findViewById(R.id.tv_phone_number);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtRePassword);
        btnRegister = findViewById(R.id.btnSignup);

        tvPhoneNumber.setText(phoneNumber);

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

        edtConfirmPassword.addTextChangedListener(new TextWatcher() {
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

                            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(RegisterActivity.this);
                            bottomSheetDialog.setContentView(R.layout.bottom_sheet_detail_user);

                             btnLogin = bottomSheetDialog.findViewById(R.id.btnLogin);
                             btnFillInfo = bottomSheetDialog.findViewById(R.id.btnFillInfo);

                            btnLogin.setOnClickListener(v -> {
                                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();

                            });

                            btnFillInfo.setOnClickListener(v -> {
                                Intent intent = new Intent(RegisterActivity.this, UserDetailActivity.class);
                                startActivity(intent);
                                finish();
                            });


                            bottomSheetDialog.show();



                        } else {
                            try {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                    String message = jsonObject.getString("message");
                                    Log.d(TAG, "Message: " + message);
                                    if (message.contains("phone number")) {
                                        tvPhoneNumber.setTextColor(getResources().getColor(R.color.red, null));
                                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.phone_number_already_exists), Toast.LENGTH_SHORT).show();
                                    } else {
                                        edtPassword.setError(response.errorBody().string());
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
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

    private void checkInputs() {
        String password = edtPassword.getText().toString();
        String confirmPassword = edtConfirmPassword.getText().toString();

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            if (password.isEmpty()) {
                edtPassword.setError("Vui lòng nhập mật khẩu");
            }
            if (confirmPassword.isEmpty()) {
                edtConfirmPassword.setError("Vui lòng nhập lại mật khẩu");
            }
            btnRegister.setBackgroundColor(getResources().getColor(R.color.primaryColorDisabled, null));
            btnRegister.setEnabled(false);
        } else if (password.length() < 6) {
            edtPassword.setError("Mật khẩu phải có ít nhất 6 kí tự");
            btnRegister.setBackgroundColor(getResources().getColor(R.color.primaryColorDisabled, null));
            btnRegister.setEnabled(false);
        } else if (!password.equals(confirmPassword)) {
            edtConfirmPassword.setError("Mật khẩu không khớp");
            btnRegister.setBackgroundColor(getResources().getColor(R.color.primaryColorDisabled, null));
            btnRegister.setEnabled(false);
        } else {
            btnRegister.setBackgroundColor(getResources().getColor(R.color.primaryColor, null));
            btnRegister.setEnabled(true);
        }
    }
}