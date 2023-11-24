package com.miwth.allure_spa.ui.views.treatment.BookService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.TimeSlot;
import com.miwth.allure_spa.ui.adapter.TimeSlotAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);

        // Step 1: Create a list of TimeSlots that you want to display
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("8:00", "Còn 3 chỗ"));
        timeSlots.add(new TimeSlot("9:00", "Còn 2 chỗ"));
        timeSlots.add(new TimeSlot("10:00", "Còn 1 chỗ"));
        timeSlots.add(new TimeSlot("11:00", "Còn 3 chỗ"));
        timeSlots.add(new TimeSlot("12:00", "Còn 2 chỗ"));
        // Add more time slots as needed

        // Step 2: Initialize the TimeSlotAdapter with the list of TimeSlots
        TimeSlotAdapter adapter = new TimeSlotAdapter(this, timeSlots);

        // Step 3: Set the adapter for the RecyclerView
        recyclerView.setAdapter(adapter);
    }
}