package com.miwth.allure_spa.ui.views.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.ui.views.WebviewActivity;
import com.miwth.allure_spa.util.callback.SideMenuCallBack;

public class HomeFragment extends Fragment {
    LinearLayout llIntroduction, llVoucher, llService, llSanPhamDocQuyen, llSanPhamMayMoc, llCourse, llNews, llContact;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        llIntroduction = homeFragmentView.findViewById(R.id.llIntroduction);
        llVoucher = homeFragmentView.findViewById(R.id.llVoucher);
        llService = homeFragmentView.findViewById(R.id.llService);
        llSanPhamDocQuyen = homeFragmentView.findViewById(R.id.llSanPhamDocQuyen);
        llSanPhamMayMoc = homeFragmentView.findViewById(R.id.llSanPhamMayMoc);
        llCourse = homeFragmentView.findViewById(R.id.llCourse);
        llNews = homeFragmentView.findViewById(R.id.llNews);
        llContact = homeFragmentView.findViewById(R.id.llContact);
        Intent intent = new Intent(getActivity(), WebviewActivity.class);
        llIntroduction.setOnClickListener(v -> {
            intent.putExtra("url", "gioi-thieu");
            intent.putExtra("title", "Giới thiệu");
            startActivity(intent);
        });
        llVoucher.setOnClickListener(v -> {
            intent.putExtra("url", "voucher");
            intent.putExtra("title", "Voucher");
            startActivity(intent);
        });
        llService.setOnClickListener(v -> {
            intent.putExtra("url", "dich-vu");
            intent.putExtra("title", "Dịch vụ");
            startActivity(intent);
        });
        llSanPhamDocQuyen.setOnClickListener(v -> {
            intent.putExtra("url", "san-pham");
            intent.putExtra("title", "Sản phẩm độc quyền");
            startActivity(intent);
        });
        llSanPhamMayMoc.setOnClickListener(v -> {
            intent.putExtra("url", "san-pham-may-moc");
            intent.putExtra("title", "Sản phẩm máy móc");
            startActivity(intent);
        });
        llCourse.setOnClickListener(v -> {
            intent.putExtra("url", "khoa-hoc");
            intent.putExtra("title", "Khóa học");
            startActivity(intent);
        });
        llNews.setOnClickListener(v -> {
            intent.putExtra("url", "/category/tin-tuc");
            intent.putExtra("title", "Tin tức");
            startActivity(intent);
        });
        llContact.setOnClickListener(v -> {
            intent.putExtra("url", "lien-he");
            intent.putExtra("title", "Liên hệ");
            startActivity(intent);
        });


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