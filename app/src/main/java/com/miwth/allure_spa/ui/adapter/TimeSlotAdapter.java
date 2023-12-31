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
import com.miwth.allure_spa.ui.views.treatment.booking.BookingActivity;

import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder> {

    private final List<TimeSlot> timeSlots;
    private final Context context;
    private final RecyclerView recyclerView;

    public TimeSlotAdapter(Context context, List<TimeSlot> timeSlots, RecyclerView recyclerView) {
        this.context = context;
        this.timeSlots = timeSlots;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public TimeSlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_time_slot, parent, false);
        return new TimeSlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        TimeSlot timeSlot = timeSlots.get(position);

        holder.tvTime.setText(timeSlot.getTime());
        holder.tvTimeStatus.setText(timeSlot.getStatus());

        if (position == ((BookingActivity) context).selectedPosition && recyclerView == ((BookingActivity) context).selectedRecyclerView) {
            holder.itemView.setBackgroundResource(R.drawable.border_book_service_selected);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.border_book_service);
        }

        int finalPosition = position;
        holder.itemView.setOnClickListener(v -> {
            ((BookingActivity) context).selectedPosition = finalPosition;
            ((BookingActivity) context).selectedRecyclerView = recyclerView;

            ((BookingActivity) context).adapter1.notifyDataSetChanged();
            ((BookingActivity) context).adapter2.notifyDataSetChanged();

            Toast.makeText(context, "Khung giờ bạn chọn: " + timeSlot.getTime(), Toast.LENGTH_SHORT).show();
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