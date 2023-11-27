// TreatmentDetails.java
package com.miwth.allure_spa.ui.views.treatment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.treatment.TreatmentsRepository;
import com.miwth.allure_spa.api.treatment.TreatmentsResponse;
import com.miwth.allure_spa.model.Treatments;
import com.miwth.allure_spa.ui.adapter.TimeSlotAdapter;
import com.miwth.allure_spa.ui.views.treatment.BookService.BookInformation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TreatmentDetails extends AppCompatActivity {
    private static final String TAG = "TREATMENT_DETAILS_ACTIVITY";
    TreatmentsRepository treatmentsRepository;
    TextView tvTreatmentName, tvTreatmentPrice;
    LinearLayout llMoTaLT;

    CardView cvBookNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_details);

        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));

        tvTreatmentName = findViewById(R.id.tvTreatmentDetailName);
        tvTreatmentPrice = findViewById(R.id.tvTreatmentDetailPrice);
        llMoTaLT = findViewById(R.id.llMoTaLT);
        cvBookNow = findViewById(R.id.cvBookNow);

        cvBookNow.setOnClickListener(v -> {
            Intent intent = new Intent(TreatmentDetails.this, BookInformation.class);
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
