package com.miwth.allure_spa.ui.views.auth;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.miwth.allure_spa.R;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {
    EditText edtPhoneNumber;
    Button btnOtp;
    TextView tvNotNow;
    private static final String TAG = "SEND_OTP_ACTIVITY";
    FirebaseAuth mAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;
    String phoneNumber;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otpactivity);

        getWindow().setStatusBarColor(getResources().getColor(R.color.transparent, null));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        mAuth = FirebaseAuth.getInstance();


        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);

        lottieAnimationView = findViewById(R.id.animationView);

        btnOtp = findViewById(R.id.btnSend);
        tvNotNow = findViewById(R.id.tvNotNow);
        tvNotNow.setPaintFlags(tvNotNow.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvNotNow.setOnClickListener(v -> finish());

        edtPhoneNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edtPhoneNumber, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        edtPhoneNumber.requestFocus();


        edtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnOtp.setEnabled(edtPhoneNumber.getText().toString().length() == 9);
                if (edtPhoneNumber.getText().toString().length() == 9) {
                    btnOtp.setBackgroundColor(getResources().getColor(R.color.primaryColor, null));
                } else {
                    btnOtp.setBackgroundColor(getResources().getColor(R.color.primaryColorDisabled, null));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                btnOtp.setEnabled(edtPhoneNumber.getText().toString().length() == 9);
            }
        });
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);
                String message = "";

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    message = "Số điện thoại không hợp lệ";
                    Log.e(TAG, "onVerificationFailed: " + phoneNumber);
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    message = "Quá số lần yêu cầu";
                } else if (e instanceof FirebaseAuthMissingActivityForRecaptchaException) {
                    // reCAPTCHA verification attempted with null Activity
                    message = "Lỗi xác thực";
                }
                // Show a message and update the UI
                Toast.makeText(SendOTPActivity.this, message, Toast.LENGTH_SHORT).show();
                stopAnimation();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);


                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                Toast.makeText(SendOTPActivity.this, "Mã OTP đã được gửi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SendOTPActivity.this, VerifyOTPActivity.class);
                stopAnimation();
                intent.putExtra("credential", verificationId);
                intent.putExtra("phoneNumber", phoneNumber);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        };


        btnOtp.setOnClickListener(v -> {
            playAnimation();
            phoneNumber = String.valueOf(edtPhoneNumber.getText());

            PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth).setPhoneNumber("+84" + phoneNumber)       // Phone number to verify
                    .setTimeout(30L, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(this)                 // (optional) Activity for callback binding
                    // If no activity is passed, reCAPTCHA verification can not be used.
                    .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                    .build();

            PhoneAuthProvider.verifyPhoneNumber(options);
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            // Đặt focus cho edtPhoneNumber
            edtPhoneNumber.requestFocus();

            // Hiển thị bàn phím
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtPhoneNumber, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void playAnimation() {
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
        btnOtp.setText("");
        btnOtp.setEnabled(false);
        edtPhoneNumber.setEnabled(false);
        tvNotNow.setEnabled(false);
        btnOtp.setBackgroundColor(getResources().getColor(R.color.transparent, null));
    }

    private void stopAnimation() {
        lottieAnimationView.setVisibility(View.GONE);
        lottieAnimationView.pauseAnimation();
        btnOtp.setEnabled(true);
        edtPhoneNumber.setEnabled(true);
        btnOtp.setText(getResources().getString(R.string.nhan_ma_otp));
        tvNotNow.setEnabled(true);
        btnOtp.setBackgroundColor(getResources().getColor(R.color.primaryColor, null));
    }
}