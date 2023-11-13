package com.miwth.allure_spa.views.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.callback.SideMenuCallBack;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton ivMenu = homeFragmentView.findViewById(R.id.ivMenu);
        ivMenu.setOnClickListener(v -> {
            SideMenuCallBack sideMenuCallBack = (SideMenuCallBack) getActivity();
            if (sideMenuCallBack != null) {
                sideMenuCallBack.onMenuIconClick();
            }
        });


        return homeFragmentView;
    }
}