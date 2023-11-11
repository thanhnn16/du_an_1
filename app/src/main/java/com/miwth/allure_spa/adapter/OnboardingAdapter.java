package com.miwth.allure_spa.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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


        ImageView[] indicators = new ImageView[getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(context);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    context,
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            holder.indicatorLayout.addView(indicators[i]);
        }

        int childCount = holder.indicatorLayout.getChildCount();
        Log.i("ChildCount", "Childcount: " + childCount);
        for (int i = 0; i < childCount; i++) {
            Log.i("ChildCount", "i: " + i);
            Log.i("ChildCount", "Index : " + position);
            ImageView imageView = (ImageView) holder.indicatorLayout.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(context, R.drawable.onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(context, R.drawable.onboarding_indicator_inactive)
                );
            }
        }

    }

    @Override
    public int getItemCount() {
        return onboardingModelArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView onboardingImage;
        TextView onboardingTitle, onboardingDescription;
        LinearLayout indicatorLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            onboardingDescription = itemView.findViewById(R.id.description);
            onboardingImage = itemView.findViewById(R.id.image);
            onboardingTitle = itemView.findViewById(R.id.title);

            indicatorLayout = itemView.findViewById(R.id.dots);

        }
    }
}
