package com.miwth.allure_spa.ui.views.home.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.model.FunctionItem;
import com.miwth.allure_spa.ui.adapter.ProfileListAdapter;
import com.miwth.allure_spa.ui.views.home.fragment.Profile.DetailUser;
import com.miwth.allure_spa.ui.views.home.fragment.Profile.Settings;
import com.miwth.allure_spa.ui.views.welcome.WelcomeActivity;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class ProfileFragment extends Fragment {
private static final String TAG = "PROFILE_FRAGMENT";
    ImageButton imgAvatar;
    TokenManager tokenManager;
    TextView tvName, tv_phone_number;
    private ListView listView;
    private ProfileListAdapter adapter;
    private ImageButton imgBack;
    private List<FunctionItem> functionList;
    private LinearLayout llLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views and data
        listView = view.findViewById(R.id.lvProfile);
        tvName = view.findViewById(R.id.tvName);
        tv_phone_number = view.findViewById(R.id.tv_phone_number);

        imgAvatar = view.findViewById(R.id.imgAvatar);

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), DetailUser.class);
                startActivity(intent);
            }
        });

        tokenManager = new TokenManager(requireContext());
        Log.d(TAG, "onCreateView: " + tokenManager.getImage());
        Log.d(TAG, "onCreateView: " + tokenManager.getFullName());
        Log.d(TAG, "onCreateView: " + tokenManager.getPhoneNumber());

        String imgUrl = tokenManager.getImage();

        if (tokenManager.getImage() != null) {
            Picasso.get().load(imgUrl).into(imgAvatar);
        }

        if (tokenManager.getFullName() != null) {
            tvName.setText(tokenManager.getFullName());
        }

        if (tokenManager.getPhoneNumber() != null) {
            tv_phone_number.setText(tokenManager.getPhoneNumber());
        }

        functionList = Arrays.asList(
                new FunctionItem(R.drawable.ic_heart_profile, "Thông Tin Cá Nhân"),
                new FunctionItem(R.drawable.ic_payment_profile, "Thanh Toán"),
                new FunctionItem(R.drawable.ic_time_profile, "Lịch Sử"),
                new FunctionItem(R.drawable.ic_voucher_profile, "Voucher"),
                new FunctionItem(R.drawable.ic_settings_profile, "Cài Đặt")
        );

        adapter = new ProfileListAdapter(requireContext(), functionList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FunctionItem functionItem = functionList.get(position);
                if (functionItem.getFunctionName().equals("Thông Tin Cá Nhân")) {
                    Intent intent = new Intent(requireContext(), DetailUser.class);
                    startActivity(intent);
                } else if (functionItem.getFunctionName().equals("Cài Đặt")) {
                    Intent intent = new Intent(requireContext(), Settings.class);
                    startActivity(intent);
                }
            }
        });


        llLogout = view.findViewById(R.id.llLogout);
        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), WelcomeActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }
}
