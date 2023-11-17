package com.miwth.allure_spa.ui.views.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.FunctionItem;
import com.miwth.allure_spa.ui.adapter.ProfileListAdapter;

import java.util.Arrays;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ListView listView;
    private ProfileListAdapter adapter;
    private ImageButton imgBack;

    private List<FunctionItem> functionList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views and data
        listView = view.findViewById(R.id.lvProfile);
        imgBack = view.findViewById(R.id.imgBack);

        functionList = Arrays.asList(
                new FunctionItem(R.drawable.ic_heart_profile, "Yêu Thích"),
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
                Toast.makeText(requireContext(), functionItem.getFunctionName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
