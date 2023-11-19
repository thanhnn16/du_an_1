package com.miwth.allure_spa.ui.views.cosmetic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.cosmetic.CosmeticsRepository;
import com.miwth.allure_spa.api.cosmetic.CosmeticsResponse;
import com.miwth.allure_spa.model.Cosmetics;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CosmeticDetailActivity extends AppCompatActivity {
    private static final String TAG = "COSMETIC_DETAIL_ACTIVITY";
    CosmeticsRepository cosmeticsRepository;
    TextView tvCosmeticName, tvCosmeticPrice;
    LinearLayout llCongDung, llMoTaSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetic_detail);
        getWindow().setStatusBarColor(getResources().getColor(R.color.transparent, null));

        tvCosmeticName = findViewById(R.id.tvCosmeticDetailName);
        tvCosmeticPrice = findViewById(R.id.tvCosmeticDetailPrice);
        llCongDung = findViewById(R.id.llCongDung);
        llMoTaSP = findViewById(R.id.llMoTaSP);


        Intent intent = getIntent();
        int cosmeticId = intent.getIntExtra("cosmetic_id", 0);
        Log.d(TAG, "getID 2: " + cosmeticId);

        cosmeticsRepository = new CosmeticsRepository();

        cosmeticsRepository.getCosmetic(cosmeticId).enqueue(new Callback<CosmeticsResponse>() {
            @Override
            public void onResponse(@NonNull Call<CosmeticsResponse> call, @NonNull Response<CosmeticsResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() == null) {
                        Log.d(TAG, "onResponse: " + response.message());
                        Toast.makeText(CosmeticDetailActivity.this, "Body null", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Cosmetics cosmetic = response.body().getCosmetic();
                    tvCosmeticName.setText(cosmetic.getCosmetics_name());
                    String price = String.format("%,d", cosmetic.getPrice() + " VNĐ");
                    tvCosmeticPrice.setText(price);
                    String[] congDung = cosmetic.getPurpose().split("\\. ");
                    String[] moTaSP = cosmetic.getDescriptions().split("\\. ");
                    for (String s : congDung) {
                        TextView textView = new TextView(CosmeticDetailActivity.this);
                        textView.setText("•\t\t" + s);
                        llCongDung.addView(textView);
                    }
                    for (String s : moTaSP) {
                        TextView textView = new TextView(CosmeticDetailActivity.this);
                        textView.setText("•\t\t" + s);
                        llMoTaSP.addView(textView);
                    }
                    Log.d(TAG, "onResponse: " + cosmetic.getCosmetics_name());
                } else {
                    Log.d(TAG, "onResponse: " + response.message());
                    Toast.makeText(CosmeticDetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CosmeticsResponse> call, @NonNull Throwable t) {
                Toast.makeText(CosmeticDetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}