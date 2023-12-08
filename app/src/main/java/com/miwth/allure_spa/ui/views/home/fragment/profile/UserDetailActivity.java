package com.miwth.allure_spa.ui.views.home.fragment.profile;

import static com.miwth.allure_spa.api.ApiConstants.WEB_BASE_URL;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.api.user.UserDetailRepository;
import com.miwth.allure_spa.api.user.UserDetailResponse;
import com.miwth.allure_spa.model.User;
import com.miwth.allure_spa.ui.views.home.HomeActivity;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailActivity extends AppCompatActivity {

    private static final String TAG = "USER_DETAIL_ACTIVITY";
    TokenManager tokenManager;
    private ImageView ibAvatar;
    private EditText etName, etEmail, etPhone, etAddress, etSkinCondition, etDoB;
    RadioGroup rgGender;
    Button btnSave;
    UserDetailRepository userDetailRepository;
    String currentImage = "";

    ActivityResultLauncher<Intent> getImageActivityResultLauncher;
    int userId;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));

        tokenManager = new TokenManager(this);

        ImageButton ibBack = findViewById(R.id.ibBack);
        ibAvatar = findViewById(R.id.ibAvatar);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etSkinCondition = findViewById(R.id.etSkinConditional);
        etDoB = findViewById(R.id.etDoB);
        rgGender = findViewById(R.id.rgGender);
        btnSave = findViewById(R.id.btnSave);
        userId = tokenManager.getUserId();
        userDetailRepository = new UserDetailRepository(tokenManager.getToken());

        getImageActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                selectedImageUri = data.getData();
                ibAvatar.setImageURI(selectedImageUri);
            }
        });
        getUserDetail();

        ibBack.setOnClickListener(v -> finish());

        ibAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            getImageActivityResultLauncher.launch(intent);
        });

        btnSave.setOnClickListener(v -> updateUser());


        etName.setText(tokenManager.getFullName());
        ibAvatar.setImageURI(Uri.parse(tokenManager.getImage()));
        etPhone.setText(tokenManager.getPhoneNumber());

        if (!tokenManager.getImage().isEmpty()) {
            Picasso.get().load(tokenManager.getImage()).into(ibAvatar);
        }
    }

    private void updateUser() {
        String email = etEmail.getText().toString();
        String name = etName.getText().toString();
        String address = etAddress.getText().toString();
        String skinCondition = etSkinCondition.getText().toString();
        int gender = rgGender.getCheckedRadioButtonId() == R.id.rbMale ? 1 : 0;
        String dob = etDoB.getText().toString();

        User user = new User(
                email,
                name,
                address,
                dob,
                skinCondition,
                gender,
                selectedImageUri != null ? selectedImageUri.toString() : currentImage
        );

        Log.d(TAG, "updateUser: " + user);
        userDetailRepository.updateUserDetail(userId, user).enqueue(new Callback<UserDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserDetailResponse> call, @NonNull Response<UserDetailResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UserDetailActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserDetailActivity.this, HomeActivity.class));
                    finishAffinity();
                } else {
                    Toast.makeText(UserDetailActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: " + response.message());
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDetailResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "Network error: " + t.getMessage());
            }
        });
    }

    private void getUserDetail() {
        userDetailRepository.getUserDetail(userId).enqueue(new Callback<UserDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserDetailResponse> call, @NonNull Response<UserDetailResponse> response) {
                if (response.isSuccessful()) {
                    UserDetailResponse userDetailResponse = response.body();
                    if (userDetailResponse != null) {
                        User user = userDetailResponse.getUser();

                        String name = user.getFullName();
                        String email = user.getEmail();
                        String phone = user.getPhoneNumber();
                        String address = user.getAddress();
                        String skinCondition = user.getSkinCondition();
                        String image = user.getImage();
                        String dob = user.getDateOfBirth();
                        int gender = user.getGender();

                        if (name != null) {
                            etName.setText(name);
                        }
                        if (email != null) {
                            etEmail.setText(email);
                        }
                        if (phone != null) {
                            etPhone.setText(phone);
                        }
                        if (address != null) {
                            etAddress.setText(address);
                        }
                        if (skinCondition != null) {
                            etSkinCondition.setText(skinCondition);
                        }
                        if (dob != null) {
                            etDoB.setText(dob);
                        }

                        if (image != null) {
                            currentImage = image;
                            String url = WEB_BASE_URL.substring(0, WEB_BASE_URL.length() - 1) + image;
                            Picasso.get().load(url).into(ibAvatar);
                        }

                        if (gender == 1) {
                            rgGender.check(R.id.rbMale);
                        } else {
                            rgGender.check(R.id.rbFemale);
                        }

                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<UserDetailResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });

    }
}