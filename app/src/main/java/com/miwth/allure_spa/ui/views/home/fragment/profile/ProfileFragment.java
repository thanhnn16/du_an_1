package com.miwth.allure_spa.ui.views.home.fragment.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.model.FunctionItem;
import com.miwth.allure_spa.ui.adapter.ProfileListAdapter;
import com.miwth.allure_spa.ui.views.home.fragment.profile.UserDetailActivity;
import com.miwth.allure_spa.ui.views.home.fragment.profile.SettingsActivity;
import com.miwth.allure_spa.ui.views.welcome.WelcomeActivity;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class ProfileFragment extends Fragment {
    private static final String TAG = "PROFILE_FRAGMENT";
    ImageButton imgAvatar;
    TokenManager tokenManager;
    TextView tvName, tv_phone_number;
    private ImageButton imgBack;
    private List<FunctionItem> functionList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ListView listView = view.findViewById(R.id.lvProfile);
        tvName = view.findViewById(R.id.tvName);
        tv_phone_number = view.findViewById(R.id.tv_phone_number);

        imgAvatar = view.findViewById(R.id.imgAvatar);

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), UserDetailActivity.class);
                startActivity(intent);
            }
        });

        tokenManager = new TokenManager(requireContext());

        if (!tokenManager.getImage().isEmpty()) {
            String imgUrl = tokenManager.getImage();
            Picasso.get().load(imgUrl).into(imgAvatar);
        }

        if (!tokenManager.getFullName().isEmpty()) {
            tvName.setText(tokenManager.getFullName());
        }

        tv_phone_number.setText(tokenManager.getPhoneNumber());

        functionList = Arrays.asList(new FunctionItem(R.drawable.ic_heart_profile, "Thông Tin Cá Nhân"), new FunctionItem(R.drawable.ic_payment_profile, "Thanh Toán"), new FunctionItem(R.drawable.ic_time_profile, "Lịch Sử"), new FunctionItem(R.drawable.ic_voucher_profile, "Voucher"), new FunctionItem(R.drawable.ic_settings_profile, "Cài Đặt"));

        ProfileListAdapter adapter = new ProfileListAdapter(requireContext(), functionList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            FunctionItem functionItem = functionList.get(position);
            if (functionItem.getFunctionName().equals("Thông Tin Cá Nhân")) {
                Intent intent = new Intent(requireContext(), UserDetailActivity.class);
                startActivity(intent);
            } else if (functionItem.getFunctionName().equals("Cài Đặt")) {
                Intent intent = new Intent(requireContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });


        LinearLayout llLogout = view.findViewById(R.id.llLogout);
        llLogout.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), WelcomeActivity.class);
            startActivity(intent);

        });
        return view;
    }
}
