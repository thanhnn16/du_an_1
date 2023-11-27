package com.miwth.allure_spa.ui.views.treatment.BookService;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.TimeSlot;
import com.miwth.allure_spa.ui.adapter.TimeSlotAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookInformation extends AppCompatActivity {

    public TimeSlotAdapter adapter1;
    public TimeSlotAdapter adapter2;

    public int selectedPosition = -1; // The position of the currently selected TimeSlot
    public RecyclerView selectedRecyclerView = null; // The currently selected RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);

        CalendarView calendarView = findViewById(R.id.cvTreatmentDetail);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return true;
            }
        };

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return true;
            }
        };

        recyclerView.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("8:00", "Còn chỗ"));
        timeSlots.add(new TimeSlot("9:00", "Còn chỗ"));
        timeSlots.add(new TimeSlot("10:00", "Còn chỗ"));
        timeSlots.add(new TimeSlot("11:00", "Còn chỗ"));
        timeSlots.add(new TimeSlot("12:00", "Còn chỗ"));

        adapter1 = new TimeSlotAdapter(this, timeSlots, recyclerView);
        recyclerView.setAdapter(adapter1);

        List<TimeSlot> timeSlots2 = new ArrayList<>();
        timeSlots2.add(new TimeSlot("13:00", "Còn chỗ"));
        timeSlots2.add(new TimeSlot("14:00", "Còn chỗ"));
        timeSlots2.add(new TimeSlot("15:00", "Còn chỗ"));
        timeSlots2.add(new TimeSlot("16:00", "Còn chỗ"));
        timeSlots2.add(new TimeSlot("17:00", "Còn chỗ"));

        adapter2 = new TimeSlotAdapter(this, timeSlots2, recyclerView2);
        recyclerView2.setAdapter(adapter2);

        // Other code
    }
}