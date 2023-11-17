package com.miwth.allure_spa.ui.views.auth;

import android.annotation.SuppressLint;
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

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.miwth.allure_spa.R;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class VerifyOTPActivity extends AppCompatActivity {
    private static final String TAG = "VERIFY_OTP_ACTIVITY";
    private FirebaseAuth mAuth;
    private String mVerificationId, phoneNumber, code;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private Button btnVerify;
    private EditText[] otpEditTexts = new EditText[6];
    private TextView tvPhoneNumber, tvResendOTP, tvCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otpactivity);
        getWindow().setStatusBarColor(getResources().getColor(R.color.transparent, null));

        initView();
        setPhoneNumber();
        setResendClickListener();
        startCountDown();
        addTextWatcher();
        setVerifyClickListener();
    }

    private void initView() {
        mAuth = FirebaseAuth.getInstance();
        btnVerify = findViewById(R.id.btnVerify);
        tvPhoneNumber = findViewById(R.id.tv_phone_number);
        tvResendOTP = findViewById(R.id.tvResendOTP);
        tvCountDown = findViewById(R.id.tvCountDown);
        otpEditTexts[0] = findViewById(R.id.edtOTP1);
        otpEditTexts[1] = findViewById(R.id.edtOTP2);
        otpEditTexts[2] = findViewById(R.id.edtOTP3);
        otpEditTexts[3] = findViewById(R.id.edtOTP4);
        otpEditTexts[4] = findViewById(R.id.edtOTP5);
        otpEditTexts[5] = findViewById(R.id.edtOTP6);
    }

    private void setPhoneNumber() {
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("phoneNumber");
        mVerificationId = intent.getStringExtra("credential");
        mResendToken = intent.getParcelableExtra("token");
        if (phoneNumber != null) {
            tvPhoneNumber.setText("+84" + phoneNumber);
        }
    }

    private void setResendClickListener() {
        tvResendOTP.setOnClickListener(v -> {
            PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                    .setPhoneNumber("+84" + phoneNumber)
                    .setTimeout(30L, TimeUnit.SECONDS)
                    .setActivity(VerifyOTPActivity.this)
                    .setCallbacks(getVerificationCallbacks())
                    .build();
            PhoneAuthProvider.verifyPhoneNumber(options);
        });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks getVerificationCallbacks() {
        return new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                handleVerificationFailed(e);
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                handleCodeSent(verificationId, token);
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted: " + phoneAuthCredential);
            }
        };
    }

    private void handleVerificationFailed(FirebaseException e) {
        Log.w(TAG, "onVerificationFailed", e);
        if (e instanceof FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(VerifyOTPActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(VerifyOTPActivity.this, "Quá số lần gửi OTP", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
        Log.d(TAG, "onCodeSent:" + verificationId);
        mVerificationId = verificationId;
        mResendToken = token;
        Toast.makeText(VerifyOTPActivity.this, "Đã gửi lại OTP", Toast.LENGTH_SHORT).show();
        tvResendOTP.setEnabled(false);
        tvResendOTP.setTextColor(getResources().getColor(R.color.primaryColor, null));
        startCountDown();
    }

    @SuppressLint("DefaultLocale")
    private void startCountDown() {
        new Thread(() -> {
            for (int i = 30; i >= 0; i--) {
                try {
                    Thread.sleep(1000);
                    final int finalI = i;
                    runOnUiThread(() -> {
                        tvCountDown.setText(String.format("00:%02d", finalI));
                        if (finalI == 0) {
                            tvResendOTP.setEnabled(true);
                            tvResendOTP.setTextColor(getResources().getColor(R.color.primaryColor, null));
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void addTextWatcher() {
        for (int i = 0; i < 6; i++) {
            final int index = i;
            otpEditTexts[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 1 && index < 5) {
                        otpEditTexts[index + 1].requestFocus();
                    } else if (s.length() == 0 && index > 0) {
                        otpEditTexts[index - 1].requestFocus();
                    }
                    updateVerifyButtonState();
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            otpEditTexts[i].setOnKeyListener((v, keyCode, event) -> {
                if (keyCode == 67 && event.getAction() == 0) {
                    if (otpEditTexts[index].getText().toString().isEmpty() && index > 0) {
                        otpEditTexts[index - 1].requestFocus();
                    }
                }
                return false;
            });
        }
    }

    private void updateVerifyButtonState() {
        StringBuilder codeBuilder = new StringBuilder();
        for (EditText editText : otpEditTexts) {
            codeBuilder.append(editText.getText().toString());
        }
        code = codeBuilder.toString();
        btnVerify.setEnabled(code.length() == 6);
        btnVerify.setBackgroundColor(getResources().getColor(
                btnVerify.isEnabled() ? R.color.primaryColor : R.color.primaryColorDisabled, null));
    }

    private void setVerifyClickListener() {
        btnVerify.setOnClickListener(v -> {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
            signInWithPhoneAuthCredential(credential);
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                handleSignInSuccess(Objects.requireNonNull(task.getResult().getUser()));
            } else {
                handleSignInFailure(task.getException());
            }
        });
    }

    private void handleSignInSuccess(FirebaseUser user) {
        Log.d(TAG, "signInWithCredential:success");
        Intent intent = new Intent(VerifyOTPActivity.this, RegisterActivity.class);
        if (user.getPhoneNumber() != null) {
            intent.putExtra("phoneNumber", phoneNumber);
        }
        startActivity(intent);
        finish();
    }

    private void handleSignInFailure(Exception exception) {
        Log.w(TAG, "signInWithCredential:failure", exception);
        if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(VerifyOTPActivity.this, "OTP không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}
