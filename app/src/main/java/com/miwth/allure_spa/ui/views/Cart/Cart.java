package com.miwth.allure_spa.ui.views.Cart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.CartItem;
import com.miwth.allure_spa.ui.adapter.CartAdapter;
import com.miwth.allure_spa.ui.views.payment.PaymentActivity;
import com.miwth.allure_spa.util.callback.CartItemCheckCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart extends AppCompatActivity implements CartItemCheckCallBack {

    View bottomSheetView;
    RecyclerView rvCart;
    CartAdapter cartAdapter;

    TextView tvCartNull;
    List<CartItem> cartItems;

    ImageButton btnDelete,  btnBack;

    private int totalPrice = 0;

    private TextView tvTotalPrice;

    CardView btnAddToCart;



    private void loadCartItems() {
        SharedPreferences sharedPreferences = getSharedPreferences("Cart", MODE_PRIVATE);
        Gson gson = new Gson();
        Map<String, ?> keys = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            String json = sharedPreferences.getString(entry.getKey(), "");
            if (json.startsWith("{")) { // Check if the string is a valid JSON
                CartItem cartItem = gson.fromJson(json, CartItem.class);
                cartItems.add(cartItem);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));


        rvCart = findViewById(R.id.rvCart);
        tvCartNull = findViewById(R.id.tvCartNull);
//        btnDelete = findViewById(R.id.btnDelete);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            finish();
        });


        cartItems = new ArrayList<>();
        loadCartItems();

        if (!cartItems.isEmpty()) {
            tvCartNull.setVisibility(View.GONE);
        }

        cartAdapter = new CartAdapter(cartItems);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(cartAdapter);

    }


    @Override
    public void onCheck(int totalPrice) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_cart, null);

        tvTotalPrice = bottomSheetView.findViewById(R.id.tvTotalPrice);
        tvTotalPrice.setText(String.valueOf(totalPrice));

        btnAddToCart = bottomSheetView.findViewById(R.id.btnAddToCart);

        btnAddToCart.setOnClickListener(v -> {
            Intent intent = new Intent(Cart.this, PaymentActivity.class);
            startActivity(intent);
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }


}

