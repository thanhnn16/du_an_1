package com.miwth.allure_spa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.Cosmetics;
import com.miwth.allure_spa.ui.views.cosmetic.CosmeticDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.miwth.allure_spa.api.ApiConstants.WEB_BASE_URL;

public class CosmeticAdapter extends RecyclerView.Adapter<CosmeticAdapter.CosmeticViewHolder> {

    private final List<Cosmetics> cosmeticsList;
    Context context;

    public CosmeticAdapter(Context context, ArrayList<Cosmetics> cosmeticsList) {
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
        holder.cosmeticCardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CosmeticDetailActivity.class);
            intent.putExtra("cosmetic_id", cosmetic.getId());
            Log.d("getID", "getID 1: " + cosmetic.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cosmeticsList == null ? 0 : cosmeticsList.size();
    }

    static class CosmeticViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle, tvRating, tvSoldQuantity, tvPrice;
        ImageButton btnAddToCart;
        CardView cosmeticCardView;

        public CosmeticViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvSoldQuantity = itemView.findViewById(R.id.tvSoldQuantity);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            cosmeticCardView = itemView.findViewById(R.id.cardView);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }

        public void bind(Cosmetics cosmetic) {
            String imgUrl = WEB_BASE_URL + cosmetic.getImage();
            Log.d("img", "bind: " + imgUrl);
            tvTitle.setText(cosmetic.getCosmeticsName());
            tvPrice.setText(String.format("%,d", cosmetic.getPrice()));
            Picasso.get().load(imgUrl).into(imageView);

        }
//            tvPrice.setText(String.valueOf(cosmetic.getPrice()));
//            set price format add . e.g: 1000000 -> 1.000.000
        // Update other views based on the cosmetic data
        // For example, you might want to use an image loading library to load the image from the URL
    }
}
