package com.miwth.allure_spa.ui.views.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.util.callback.BackButtonCallBack;

public class SearchFragment extends Fragment {
    ImageButton btnBack;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,
                container, false);

        btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            BackButtonCallBack backButtonCallBack = (BackButtonCallBack) getActivity();
            if (backButtonCallBack != null) {
                backButtonCallBack.onBackButtonClick();
            }
        });


        return view;
    }
}