// TreatmentDetails.java
package com.miwth.allure_spa.ui.views.treatment;

import static com.miwth.allure_spa.api.ApiConstants.WEB_BASE_URL;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.google.android.material.imageview.ShapeableImageView;
import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.api.treatment.TreatmentsRepository;
import com.miwth.allure_spa.api.treatment.TreatmentsResponse;
import com.miwth.allure_spa.model.Treatments;
import com.miwth.allure_spa.ui.views.rating.RatingActivity;
import com.miwth.allure_spa.ui.views.treatment.booking.BookingActivity;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TreatmentDetails extends AppCompatActivity {
    private static final String TAG = "TREATMENT_DETAILS_ACTIVITY";
    TreatmentsRepository treatmentsRepository;
    TextView tvTreatmentName, tvTreatmentPrice;
    LinearLayout llMoTaLT, llComment;

    CardView cvBookNow;

    ImageButton ibBack;

    ShapeableImageView ivTreatmentDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_details);

        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));

        tvTreatmentName = findViewById(R.id.tvTreatmentDetailName);
        tvTreatmentPrice = findViewById(R.id.tvTreatmentDetailPrice);
        llMoTaLT = findViewById(R.id.llMoTaLT);
        cvBookNow = findViewById(R.id.cvBookNow);
        llComment = findViewById(R.id.llComment);

        ivTreatmentDetail = findViewById(R.id.ivTreatmentDetail);

        ibBack = findViewById(R.id.ibBack);

        ibBack.setOnClickListener(v -> {
            finish();
        });

        cvBookNow.setOnClickListener(v -> {
            TokenManager tokenManager = new TokenManager(TreatmentDetails.this);
            String token = tokenManager.getToken();
            Log.d(TAG, "Token: " + token);
            if (token.isEmpty()) {
                Toast.makeText(TreatmentDetails.this, "Bạn cần đăng nhập để đặt lịch", Toast.LENGTH_SHORT).show();
            } else {
                // Get the service price
                String servicePrice = tvTreatmentPrice.getText().toString();

                startActivity(new Intent(TreatmentDetails.this, BookingActivity.class)
                        .putExtra("treatment_id", getIntent().getIntExtra("treatment_id", 0))
                        .putExtra("treatment_price", getIntent().getIntExtra("price", 0)));

            }
        });

        llComment.setOnClickListener(v -> {
            Intent intent = new Intent(TreatmentDetails.this, RatingActivity.class);
            startActivity(intent);
        });


        Intent intent = getIntent();
        int treatmentId = intent.getIntExtra("treatment_id", 0);
        Log.d(TAG, "getID: " + treatmentId);

        treatmentsRepository = new TreatmentsRepository();

        treatmentsRepository.getTreatment(treatmentId).enqueue(new Callback<TreatmentsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TreatmentsResponse> call, @NonNull Response<TreatmentsResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() == null) {
                        Log.d(TAG, "onResponse: " + response.message());
                        Toast.makeText(TreatmentDetails.this, "Body null", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Treatments treatment = response.body().getTreatment();
                    tvTreatmentName.setText(treatment.getTreatmentName());
                    String price = String.format("%,d VNĐ", treatment.getPrice());
                    tvTreatmentPrice.setText(price);
                    String[] moTa = treatment.getDescription().split("\\. ");
                    for (String s : moTa) {
                        TextView textView = new TextView(TreatmentDetails.this);
                        textView.setText("•\t\t" + s);
                        llMoTaLT.addView(textView);
                    }

                    if (!treatment.getImage().isEmpty()) {
                        String imageUrl = WEB_BASE_URL + treatment.getImage();
                        Picasso.get().load(imageUrl).into(ivTreatmentDetail);
                    }


                    Log.d(TAG, "onResponse: " + treatment.getTreatmentName());
                } else {
                    Log.d(TAG, "onResponse: " + response.message());
                    Toast.makeText(TreatmentDetails.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(@NonNull Call<TreatmentsResponse> call, @NonNull Throwable t) {
                Toast.makeText(TreatmentDetails.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
