package com.miwth.allure_spa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.Cosmetics;

import java.util.List;

public class CosmeticAdapter extends RecyclerView.Adapter<CosmeticAdapter.CosmeticViewHolder> {

    private List<Cosmetics> cosmeticsList;
    Context context;

    public CosmeticAdapter(Context context, List<Cosmetics> cosmeticsList) {
        this.context = context;
        this.cosmeticsList = cosmeticsList;
    }

    @NonNull
    @Override
    public CosmeticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cosmetic_card, parent, false);
        return new CosmeticViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CosmeticViewHolder holder, int position) {
        Cosmetics cosmetic = cosmeticsList.get(position);
        holder.bind(cosmetic);
    }

    @Override
    public int getItemCount() {
        return cosmeticsList.size();
    }

    static class CosmeticViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle, tvRating, tvSoldQuantity, tvPrice;
        ImageButton btnAddToCart;

        public CosmeticViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvSoldQuantity = itemView.findViewById(R.id.tvSoldQuantity);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }

        public void bind(Cosmetics cosmetic) {
            tvTitle.setText(cosmetic.getCosmetics_name());
            tvPrice.setText(String.format("%,d", cosmetic.getPrice()));
//            tvPrice.setText(String.valueOf(cosmetic.getPrice()));
//            set price format add . e.g: 1000000 -> 1.000.000
            // Update other views based on the cosmetic data
            // For example, you might want to use an image loading library to load the image from the URL
        }
    }
}