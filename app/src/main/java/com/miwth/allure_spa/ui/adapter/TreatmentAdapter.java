package com.miwth.allure_spa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.Treatments;
import com.miwth.allure_spa.ui.views.treatment.TreatmentDetails;

import java.util.List;

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

    @Override
    public void onBindViewHolder(@NonNull TreatmentViewHolder holder, int position) {
        Treatments treatment = treatmentsList.get(position);
        holder.treatmentName.setText(treatment.getTreatmentName());
        holder.description.setText(treatment.getDescription());
        int price = treatment.getPrice();
        String formattedPrice = String.format("%,d", price) + " VNĐ";
        holder.price.setText(formattedPrice);
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TreatmentDetails.class);
            intent.putExtra("treatment_id", treatment.getId());
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


        public TreatmentViewHolder(View view) {
            super(view);
            treatmentName = view.findViewById(R.id.tvServiceTitle);
            description = view.findViewById(R.id.tvServiceDescription);
            price = view.findViewById(R.id.tvServicePrice);

            cardView = view.findViewById(R.id.cardService);

        }
    }
}