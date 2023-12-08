package com.miwth.allure_spa.ui.views.cosmetic;

import static com.miwth.allure_spa.api.ApiConstants.WEB_BASE_URL;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.imageview.ShapeableImageView;
import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.api.cosmetic.CosmeticsRepository;
import com.miwth.allure_spa.api.cosmetic.CosmeticsResponse;
import com.miwth.allure_spa.model.CartItem;
import com.miwth.allure_spa.model.Cosmetics;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.gson.Gson;
import com.miwth.allure_spa.ui.views.rating.RatingActivity;
import com.squareup.picasso.Picasso;


public class CosmeticDetailActivity extends AppCompatActivity {
    private static final String TAG = "COSMETIC_DETAIL_ACTIVITY";
    SharedPreferences sharedPreferences;

    CosmeticsRepository cosmeticsRepository;
    TextView tvCosmeticName, tvCosmeticPrice, tvAddToCartBtn, tvTreatmentDetailQty;

    ImageView ivTreatmentDetailMinus, ivTreatmentDetailPlus;
    LinearLayout llCongDung, llMoTaSP, llAddToCartBtn;

    CardView cvAddToCartBtn;

    ShapeableImageView ivCosmeticDetail;

    ImageButton ibBack;
    String imageUrl ="";
    int price = 0;




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

        ivCosmeticDetail = findViewById(R.id.ivCosmeticDetail);

        ivTreatmentDetailMinus = findViewById(R.id.ivTreatmentDetailMinus);
        ivTreatmentDetailPlus = findViewById(R.id.ivTreatmentDetailPlus);
        tvTreatmentDetailQty = findViewById(R.id.tvTreatmentDetailQty);
        llAddToCartBtn = findViewById(R.id.llAddToCartBtn);

        llAddToCartBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CosmeticDetailActivity.this, RatingActivity.class);
            startActivity(intent);
        });

        ibBack = findViewById(R.id.ibBack);

        ibBack.setOnClickListener(v -> {
            finish();
        });

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
                    price = cosmetic.getPrice();
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

                    if (!cosmetic.getImage().isEmpty()) {
                        imageUrl = WEB_BASE_URL + cosmetic.getImage();
                        Picasso.get().load(imageUrl).into(ivCosmeticDetail);
                    }

                    Log.d(TAG, "onResponse: " + cosmetic.getCosmeticsName());
                } else {
                    Log.d(TAG, "onResponse: " + response.message());
                    Toast.makeText(CosmeticDetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }


                cvAddToCartBtn.setOnClickListener(v -> {
                    TokenManager tokenManager = new TokenManager(CosmeticDetailActivity.this);
                    String token = tokenManager.getToken();
                    Log.d(TAG, "Token: " + token);
                    if (token.isEmpty()) {
                        Toast.makeText(CosmeticDetailActivity.this, "Bạn cần đăng nhập để thêm sản phẩm vào giỏ hàng",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        // Get the product image URL
                        CartItem cartItem = new CartItem(tvCosmeticName.getText().toString(), String.valueOf(price), tvTreatmentDetailQty.getText().toString(), imageUrl);

                        // Save the CartItem to the shared preferences
                        saveCartItem(cartItem);

                        Toast.makeText(CosmeticDetailActivity.this, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<CosmeticsResponse> call, @NonNull Throwable t) {
                Toast.makeText(CosmeticDetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


// ...

    private void saveCartItem(CartItem cartItem) {
        SharedPreferences sharedPreferences = getSharedPreferences("Cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartItem);
        editor.putString(cartItem.getName(), json);

        editor.apply();
    }
}
