package com.miwth.allure_spa.ui.views.cosmetic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.cosmetic.CosmeticsRepository;
import com.miwth.allure_spa.api.cosmetic.CosmeticsResponse;
import com.miwth.allure_spa.model.Cosmetics;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CosmeticDetailActivity extends AppCompatActivity {
    private static final String TAG = "COSMETIC_DETAIL_ACTIVITY";
    SharedPreferences sharedPreferences;

    CosmeticsRepository cosmeticsRepository;
    TextView tvCosmeticName, tvCosmeticPrice, tvAddToCartBtn, tvTreatmentDetailQty;

    ImageView ivTreatmentDetailMinus, ivTreatmentDetailPlus;
    LinearLayout llCongDung, llMoTaSP;

    CardView cvAddToCartBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetic_detail);
        getWindow().setStatusBarColor(getResources().getColor(R.color.transparent, null));

        tvCosmeticName = findViewById(R.id.tvCosmeticDetailName);
        tvCosmeticPrice = findViewById(R.id.tvCosmeticDetailPrice);
        llCongDung = findViewById(R.id.llCongDung);
        llMoTaSP = findViewById(R.id.llMoTaSP);
        cvAddToCartBtn = findViewById(R.id.cvAddToCartBtn);
        tvAddToCartBtn = findViewById(R.id.tvAddToCartBtn);

        ivTreatmentDetailMinus = findViewById(R.id.ivTreatmentDetailMinus);
        ivTreatmentDetailPlus = findViewById(R.id.ivTreatmentDetailPlus);
        tvTreatmentDetailQty = findViewById(R.id.tvTreatmentDetailQty);

        ivTreatmentDetailMinus.setOnClickListener(v -> {
            int currentQty = Integer.parseInt(tvTreatmentDetailQty.getText().toString());
            if (currentQty > 1) {
                tvTreatmentDetailQty.setText(String.valueOf(currentQty - 1));
            }
        });

        ivTreatmentDetailPlus.setOnClickListener(v -> {
            int currentQty = Integer.parseInt(tvTreatmentDetailQty.getText().toString());
            tvTreatmentDetailQty.setText(String.valueOf(currentQty + 1));
        });

        cvAddToCartBtn.setOnClickListener(v -> {
            sharedPreferences = getSharedPreferences("Cart", MODE_PRIVATE);
            String products = sharedPreferences.getString("products", "");
            String cosmeticName = tvCosmeticName.getText().toString();
            String cosmeticPrice = tvCosmeticPrice.getText().toString();
            String cosmeticQty = tvTreatmentDetailQty.getText().toString();
            String cosmeticImage = "https://i.imgur.com/2YJmYqI.png";
            String productDetail = cosmeticName + "," + cosmeticPrice + "," + cosmeticQty + "," + cosmeticImage;
            if (products.equals("")) {
                products = productDetail;
            } else {
                products += ";" + productDetail;
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("products", products);
            editor.apply();

            Toast.makeText(CosmeticDetailActivity.this, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        });


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
                    tvCosmeticName.setText(cosmetic.getCosmeticsName());
                    int price = cosmetic.getPrice();
                    String formattedPrice = String.format("%,d", price) + " VNĐ";
                    tvCosmeticPrice.setText(formattedPrice);
                    String[] congDung = cosmetic.getPurpose().split("\\. ");
                    String[] moTaSP = cosmetic.getDescription().split("\\. ");
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
                    Log.d(TAG, "onResponse: " + cosmetic.getCosmeticsName());
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