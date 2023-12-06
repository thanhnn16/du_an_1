package com.miwth.allure_spa.ui.views.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.ui.adapter.CartAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cart extends AppCompatActivity {
    private static final String TAG = "CART_ACTIVITY";

    Toolbar toolbar;
    ImageButton btnBack;
    TextView tvCartNull;
    RecyclerView rvCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        btnBack = findViewById(R.id.btnBack);
        tvCartNull = findViewById(R.id.tvCartNull);
        rvCart = findViewById(R.id.rvCart);

        // Get products from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("Cart", MODE_PRIVATE);
        String products = sharedPreferences.getString("products", "");
        List<String> productList = new ArrayList<>(Arrays.asList(products.split(";")));

        // Initialize CartAdapter and set it to the RecyclerView
        CartAdapter cartAdapter = new CartAdapter(productList);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(cartAdapter);
    }
}