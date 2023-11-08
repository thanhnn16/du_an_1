package com.miwth.allure_spa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.models.OnboardingModel;

import java.util.ArrayList;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.ViewHolder> {
    Context context;
    ArrayList<OnboardingModel> onboardingModelArrayList;

    public OnboardingAdapter(Context context, ArrayList<OnboardingModel> onboardingModelArrayList) {
        this.context = context;
        this.onboardingModelArrayList = onboardingModelArrayList;
    }

    @NonNull
    @Override
    public OnboardingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.onboarding_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingAdapter.ViewHolder holder, int position) {
        holder.onboardingImage.setImageResource(onboardingModelArrayList.get(position).getImage());
        holder.onboardingTitle.setText(onboardingModelArrayList.get(position).getTitle());
        holder.onboardingDescription.setText(onboardingModelArrayList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return onboardingModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView onboardingImage;
        TextView onboardingTitle, onboardingDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            onboardingDescription = itemView.findViewById(R.id.description);
            onboardingImage = itemView.findViewById(R.id.image);
            onboardingTitle = itemView.findViewById(R.id.title);

        }
    }
}
