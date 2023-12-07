package com.miwth.allure_spa.ui.views.treatment.BookService;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.appointment.AppointmentsRepository;
import com.miwth.allure_spa.api.appointment.AppointmentsResponse;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.model.Appointment;
import com.miwth.allure_spa.model.TimeSlot;
import com.miwth.allure_spa.ui.adapter.TimeSlotAdapter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;

public class BookInformation extends AppCompatActivity {

    private static final String TAG = "BOOK_INFORMATION_ACTIVITY";
    public TimeSlotAdapter adapter1;
    public TimeSlotAdapter adapter2;

    ImageButton ibBack;
    CardView btnBookNow;
    List<TimeSlot> timeSlots, timeSlots2;
    CalendarView calendarView;

    TextView tvTreatmentDetailTotalPrice;

    EditText etTreatmentDetailNote;

    public int selectedPosition = -1;
    public RecyclerView selectedRecyclerView = null;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));
        tokenManager = new TokenManager(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);

        calendarView = findViewById(R.id.cvTreatmentDetail);

        btnBookNow = findViewById(R.id.btnBookNow);

        ibBack = findViewById(R.id.ibBack);

        etTreatmentDetailNote = findViewById(R.id.etTreatmentDetailNote);

        tvTreatmentDetailTotalPrice = findViewById(R.id.tvTreatmentDetailTotalPrice);

        int servicePrice = getIntent().getIntExtra("treatment_price", 0);
        String formattedPrice = String.format("%,d", servicePrice) + " VNĐ";
        tvTreatmentDetailTotalPrice.setText(formattedPrice);







        ibBack.setOnClickListener(v -> {
            finish();
        });

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

        timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("8:00", "Còn chỗ"));
        timeSlots.add(new TimeSlot("9:00", "Còn chỗ"));
        timeSlots.add(new TimeSlot("10:00", "Còn chỗ"));
        timeSlots.add(new TimeSlot("11:00", "Còn chỗ"));
        timeSlots.add(new TimeSlot("12:00", "Còn chỗ"));

        adapter1 = new TimeSlotAdapter(this, timeSlots, recyclerView);
        recyclerView.setAdapter(adapter1);

        timeSlots2 = new ArrayList<>();
        timeSlots2.add(new TimeSlot("13:00", "Còn chỗ"));
        timeSlots2.add(new TimeSlot("14:00", "Còn chỗ"));
        timeSlots2.add(new TimeSlot("15:00", "Còn chỗ"));
        timeSlots2.add(new TimeSlot("16:00", "Còn chỗ"));
        timeSlots2.add(new TimeSlot("17:00", "Còn chỗ"));

        adapter2 = new TimeSlotAdapter(this, timeSlots2, recyclerView2);
        recyclerView2.setAdapter(adapter2);

        btnBookNow.setOnClickListener(v -> {
            if (selectedPosition == -1) {
                Toast.makeText(this, "Bạn chưa chọn giờ", Toast.LENGTH_SHORT).show();
            } else {
                long dateInMs = calendarView.getDate();
                String time = timeSlots.get(selectedPosition).getTime();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                LocalTime startTime = LocalTime.parse(time, timeFormatter);
                LocalTime endTime = startTime.plusHours(2);

                Date date = new Date(dateInMs);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                String strStartDate = formatter.format(date) + " " + startTime + ":00";
                String strEndDate = formatter.format(date) + " " + endTime + ":00";

                DateTimeFormatter fullFormarter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime start_date = LocalDateTime.parse(strStartDate, fullFormarter);
                LocalDateTime end_date = LocalDateTime.parse(strEndDate, fullFormarter);

                Intent intent = getIntent();

                int treatmentId = intent.getIntExtra("treatment_id", 0);

                int userId = Integer.parseInt(tokenManager.getUserId());

                String note = etTreatmentDetailNote.getText().toString();
                if (note.isEmpty()) {
                    note = "Book từ app";
                }

                Appointment newAppointment = new Appointment(userId, treatmentId, start_date, end_date, false, false, "pending", note);

                AppointmentsRepository appointmentsRepository = new AppointmentsRepository(new TokenManager(this).getToken());

                Call<Void> call = appointmentsRepository.createAppointment(newAppointment);

                call.enqueue(new retrofit2.Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(BookInformation.this, "Đặt lịch thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(BookInformation.this, "Đặt lịch thất bại", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onResponse: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(BookInformation.this, "Đặt lịch thất bại", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });


            }
        });

    }
}