package com.miwth.allure_spa.ui.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.cosmetic.CosmeticsRepository;
import com.miwth.allure_spa.api.cosmetic.CosmeticsResponse;
import com.miwth.allure_spa.model.Cosmetics;
import com.miwth.allure_spa.ui.adapter.CosmeticAdapter;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeMoreActivity extends AppCompatActivity {
    private static final String TAG = "SEE_MORE_ACTIVITY";
    ImageButton btnBack;
    TextView tvTitle;
    RecyclerView rvSeeMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more);
        getWindow().setStatusBarColor(getResources().getColor(R.color.transparent, null));

        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        rvSeeMore = findViewById(R.id.rvSeeAll);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tvTitle.setText(title);
        btnBack.setOnClickListener(v -> finish());

        if (Objects.equals(title, getResources().getString(R.string.cosmetic_cap))) {
            CosmeticsRepository cosmeticsRepository = new CosmeticsRepository();
            cosmeticsRepository.getCosmetics().enqueue(new Callback<CosmeticsResponse>() {
                @Override
                public void onResponse(Call<CosmeticsResponse> call, Response<CosmeticsResponse> response) {
                    if (response.isSuccessful()) {
                        ArrayList<Cosmetics> cosmeticArrayList = (ArrayList<Cosmetics>) response.body().getData();
                        CosmeticAdapter cosmeticAdapter = new CosmeticAdapter(SeeMoreActivity.this, cosmeticArrayList);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(SeeMoreActivity.this, 2);
                        rvSeeMore.setLayoutManager(gridLayoutManager);
                        rvSeeMore.setAdapter(cosmeticAdapter);
                    } else {
                        Toast.makeText(SeeMoreActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CosmeticsResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        } else if (Objects.equals(title, getResources().getString(R.string.treatment_cap))) {
            CosmeticsRepository cosmeticsRepository = new CosmeticsRepository();
            cosmeticsRepository.getCosmetics().enqueue(new Callback<CosmeticsResponse>() {
                @Override
                public void onResponse(Call<CosmeticsResponse> call, Response<CosmeticsResponse> response) {
                    if (response.isSuccessful()) {
                        ArrayList<Cosmetics> cosmeticArrayList = (ArrayList<Cosmetics>) response.body().getData();
                        CosmeticAdapter cosmeticAdapter = new CosmeticAdapter(SeeMoreActivity.this, cosmeticArrayList);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(SeeMoreActivity.this, 2);
                        rvSeeMore.setLayoutManager(gridLayoutManager);
                        rvSeeMore.setAdapter(cosmeticAdapter);
                    } else {
                        Toast.makeText(SeeMoreActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CosmeticsResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        } else if (Objects.equals(title, getResources().getString(R.string.news_cap))) {
            CosmeticsRepository cosmeticsRepository = new CosmeticsRepository();
            cosmeticsRepository.getCosmetics().enqueue(new Callback<CosmeticsResponse>() {
                @Override
                public void onResponse(Call<CosmeticsResponse> call, Response<CosmeticsResponse> response) {
                    if (response.isSuccessful()) {
                        ArrayList<Cosmetics> cosmeticArrayList = (ArrayList<Cosmetics>) response.body().getData();
                        CosmeticAdapter cosmeticAdapter = new CosmeticAdapter(SeeMoreActivity.this, cosmeticArrayList);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(SeeMoreActivity.this, 2);
                        rvSeeMore.setLayoutManager(gridLayoutManager);
                        rvSeeMore.setAdapter(cosmeticAdapter);
                    } else {
                        Toast.makeText(SeeMoreActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CosmeticsResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());

                }
            });

        }
    }
}
