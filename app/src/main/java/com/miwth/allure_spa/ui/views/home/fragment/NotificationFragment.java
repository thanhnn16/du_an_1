package com.miwth.allure_spa.ui.views.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.util.callback.BackButtonCallBack;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotificationFragment extends Fragment {
    ImageButton btnBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        btnBack = view.findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            BackButtonCallBack backButtonCallBack = (BackButtonCallBack) getActivity();
            if (backButtonCallBack != null) {
                backButtonCallBack.onBackButtonClick();
            }
        });

        // Lấy dữ liệu từ Intent
        Intent intent = getActivity().getIntent();
        String cosmeticName = intent.getStringExtra("cosmetic_name");
        String cosmeticImageUrl = intent.getStringExtra("cosmetic_image_url");

        // Hiển thị tên sản phẩm
        TextView tvNotification = view.findViewById(R.id.tvNotification);
        ImageView imgNotification = view.findViewById(R.id.imgNotification);
        TextView tvNotificationNull = view.findViewById(R.id.tvNotificationNull);
        LinearLayout lnNotification = view.findViewById(R.id.lnNotification);

        if (cosmeticName != null && cosmeticImageUrl != null) {
            tvNotification.setText(cosmeticName);
            Picasso.get().load(cosmeticImageUrl).into(imgNotification);
            tvNotificationNull.setVisibility(View.GONE);
            lnNotification.setVisibility(View.VISIBLE);
        } else {
            tvNotification.setVisibility(View.GONE);
            imgNotification.setVisibility(View.GONE);
            lnNotification.setVisibility(View.GONE);
            tvNotificationNull.setVisibility(View.VISIBLE);
        }

        // Hiển thị ngày hiện tại
        TextView dateText = view.findViewById(R.id.date_text);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, 'ngày' dd 'tháng' MM 'năm' yyyy", new Locale("vi", "VN"));
        String currentDate = sdf.format(new Date());
        dateText.setText(currentDate);

        return view;
    }
}