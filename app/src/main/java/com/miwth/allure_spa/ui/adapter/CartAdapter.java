package com.miwth.allure_spa.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<String> products;

    public CartAdapter(List<String> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String productDetail = products.get(position);
        String[] details = productDetail.split(",");
        String productName = details[0];
        String productPrice = details[1];
        String productQty = details[2];
        String productImage = details[3];
        holder.tvName.setText(productName);
        holder.tvPrice.setText(productPrice);
        holder.tvQty.setText(productQty);
        holder.ivImage.setImageBitmap(convertStringToBitmap(productImage));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvQty;
        ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQty = itemView.findViewById(R.id.tvQty);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }

    private Bitmap convertStringToBitmap(String imageString) {
        byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}