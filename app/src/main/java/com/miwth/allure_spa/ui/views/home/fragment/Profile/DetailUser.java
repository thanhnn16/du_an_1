package com.miwth.allure_spa.ui.views.home.fragment.Profile;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.squareup.picasso.Picasso;

public class DetailUser extends AppCompatActivity {

    TokenManager tokenManager;
    private ImageButton ibBack;
    private ImageView ibAvatar;
    private EditText etName, etEmail, etPhone, etAddress, etGender;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        ibBack = findViewById(R.id.ibBack);
        ibAvatar = findViewById(R.id.ibAvatar);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etGender = findViewById(R.id.etGender);
        toolbarTitle = findViewById(R.id.toolbar).findViewById(R.id.title);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tokenManager = new TokenManager(this);

        etName.setText(tokenManager.getFullName());
        ibAvatar.setImageURI(Uri.parse(tokenManager.getImage()));
        etPhone.setText(tokenManager.getPhoneNumber());

        if (tokenManager.getImage() != null) {
            Picasso.get().load(tokenManager.getImage()).into(ibAvatar);
        }
    }
}