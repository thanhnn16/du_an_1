package com.miwth.allure_spa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.Treatments;

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
        holder.description.setText(treatment.getDescriptions());
        holder.price.setText(String.format("%d", treatment.getPrice()));
    }

    @Override
    public int getItemCount() {
        return treatmentsList == null ? 0 : treatmentsList.size();
    }

    public static class TreatmentViewHolder extends RecyclerView.ViewHolder {
        public TextView treatmentName, description, price;

        public TreatmentViewHolder(View view) {
            super(view);
            treatmentName = view.findViewById(R.id.tvServiceTitle);
            description = view.findViewById(R.id.tvServiceDescription);
            price = view.findViewById(R.id.tvServicePrice);

        }
    }
}