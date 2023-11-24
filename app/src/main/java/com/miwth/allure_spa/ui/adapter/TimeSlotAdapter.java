package com.miwth.allure_spa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.TimeSlot;

import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder> {

    private List<TimeSlot> timeSlots;
    private Context context;
    private int selectedPosition = -1; // Add this line

    public TimeSlotAdapter(Context context, List<TimeSlot> timeSlots) {
        this.context = context;
        this.timeSlots = timeSlots;
    }

    @NonNull
    @Override
    public TimeSlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_time_slot, parent, false);
        return new TimeSlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotViewHolder holder, int position) {
        TimeSlot timeSlot = timeSlots.get(position);
        holder.tvTime.setText(timeSlot.getTime());
        holder.tvTimeStatus.setText(timeSlot.getStatus());

        // Set the background of the item based on whether it's selected
        if (position == selectedPosition) {
            holder.itemView.setBackgroundResource(R.drawable.ic_launcher_background);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.border_book_service);
        }

        // Set an OnClickListener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the selected position
                selectedPosition = position;

                // Notify the adapter to redraw the items
                notifyDataSetChanged();

                // Display a Toast with the time slot's information
                Toast.makeText(context, "Clicked on time slot: " + timeSlot.getTime(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeSlots.size();
    }

    public static class TimeSlotViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime;
        TextView tvTimeStatus;

        public TimeSlotViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvTimeStatus = itemView.findViewById(R.id.tvTimeStatus);
        }
    }
}