package com.miwth.allure_spa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.Treatments;
import com.miwth.allure_spa.ui.views.treatment.TreatmentDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.miwth.allure_spa.api.ApiConstants.WEB_BASE_URL;


public class TreatmentAdapter extends RecyclerView.Adapter<TreatmentAdapter.TreatmentViewHolder> {

    Context context;
    private List<Treatments> treatmentsList;

    public TreatmentAdapter(Context context, List<Treatments> treatmentsList) {
        this.context = context;
        this.treatmentsList = treatmentsList;
    }

    @NonNull
    @Override
    public TreatmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_card, parent, false);
        return new TreatmentViewHolder(itemView);

    }

    public void onBindViewHolder(@NonNull TreatmentViewHolder holder, int position) {
        Treatments treatment = treatmentsList.get(position);
        holder.treatmentName.setText(treatment.getTreatmentName());
        holder.description.setText(treatment.getDescription());
        int price = treatment.getPrice();
        String formattedPrice = String.format("%,d", price) + " VNĐ";
        holder.price.setText(formattedPrice);

        // Construct the image URL and load the image
        String imgUrl = WEB_BASE_URL + treatment.getImage();
        Picasso.get().load(imgUrl).into(holder.imageView);

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TreatmentDetails.class);
            intent.putExtra("treatment_id", treatment.getId());
            intent.putExtra("price", treatment.getPrice());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return treatmentsList == null ? 0 : treatmentsList.size();
    }

    public static class TreatmentViewHolder extends RecyclerView.ViewHolder {
        public TextView treatmentName, description, price;
        CardView cardView;

        ImageView imageView;


        public TreatmentViewHolder(View view) {
            super(view);
            treatmentName = view.findViewById(R.id.tvServiceTitle);
            description = view.findViewById(R.id.tvServiceDescription);
            price = view.findViewById(R.id.tvServicePrice);
            imageView = view.findViewById(R.id.imageView);

            cardView = view.findViewById(R.id.cardService);

        }
    }
}