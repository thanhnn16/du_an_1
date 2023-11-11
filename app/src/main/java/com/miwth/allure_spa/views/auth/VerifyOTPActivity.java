package com.miwth.allure_spa.views.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.miwth.allure_spa.R;
import com.miwth.allure_spa.views.home.HomeActivity;

public class VerifyOTPActivity extends AppCompatActivity {
    private static final String TAG = "VERIFY_OTP_ACTIVITY";
    FirebaseAuth mAuth;
    PhoneAuthCredential credential;
    String mVerificationId;
    String phoneNumber;
    String code;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    Button btnVerify;
    EditText edtOTP1, edtOTP2, edtOTP3, edtOTP4, edtOTP5, edtOTP6;
    TextView tvPhoneNumber, tvResendOTP, tvCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otpactivity);

        getWindow().setStatusBarColor(getResources().getColor(R.color.transparent, null));

        btnVerify = findViewById(R.id.btnVerify);
        tvPhoneNumber = findViewById(R.id.tv_phone_number);
        tvResendOTP = findViewById(R.id.tvResendOTP);
        tvResendOTP.setEnabled(false);
        tvCountDown = findViewById(R.id.tvCountDown);

        startCountDown();

        edtOTP1 = findViewById(R.id.edtOTP1);
        edtOTP2 = findViewById(R.id.edtOTP2);
        edtOTP3 = findViewById(R.id.edtOTP3);
        edtOTP4 = findViewById(R.id.edtOTP4);
        edtOTP5 = findViewById(R.id.edtOTP5);
        edtOTP6 = findViewById(R.id.edtOTP6);

        edtOTP1.requestFocus();
        addTextWatcher();

        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("phoneNumber");
        if (phoneNumber != null) {
            tvPhoneNumber.setText("+84" + phoneNumber);
        }
        mAuth = FirebaseAuth.getInstance();


        btnVerify.setOnClickListener(v -> {
//            code = "123456";
            code = edtOTP1.getText().toString() + edtOTP2.getText().toString() + edtOTP3.getText().toString() + edtOTP4.getText().toString() + edtOTP5.getText().toString() + edtOTP6.getText().toString();
            credential = PhoneAuthProvider.getCredential(mVerificationId, code);
            signInWithPhoneAuthCredential(credential);
        });

    }

    private void addTextWatcher() {
        edtOTP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((edtOTP1.getText().toString().length() == 1)) {
                    edtOTP2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtOTP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((edtOTP2.getText().toString().length() == 1)) {
                    edtOTP3.requestFocus();
                }
                if ((edtOTP2.getText().toString().length() == 0)) {
                    edtOTP1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtOTP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((edtOTP3.getText().toString().length() == 1)) {
                    edtOTP4.requestFocus();
                }
                if ((edtOTP3.getText().toString().length() == 0)) {
                    edtOTP2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtOTP4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((edtOTP4.getText().toString().length() == 1)) {
                    edtOTP5.requestFocus();
                }
                if ((edtOTP4.getText().toString().length() == 0)) {
                    edtOTP3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtOTP5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((edtOTP5.getText().toString().length() == 1)) {
                    edtOTP6.requestFocus();
                }
                if ((edtOTP5.getText().toString().length() == 0)) {
                    edtOTP4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtOTP6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((edtOTP6.getText().toString().length() == 0)) {
                    edtOTP5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = task.getResult().getUser();
                        // Update UI
                        Intent intent = new Intent(VerifyOTPActivity.this, HomeActivity.class);
                        if (user.getPhoneNumber() != null) {
                            Toast.makeText(VerifyOTPActivity.this, "Chào " + user.getPhoneNumber(), Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            Toast.makeText(VerifyOTPActivity.this, "OTP không hợp lệ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void startCountDown() {
        new Thread(() -> {
            for (int i = 30; i >= 0; i--) {
                try {
                    Thread.sleep(1000);
                    final int finalI = i;
                    runOnUiThread(() -> tvCountDown.setText("Gửi lại mã OTP sau " + finalI + " giây"));
                    if (i == 0) {
                        runOnUiThread(() -> tvResendOTP.setEnabled(true));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}