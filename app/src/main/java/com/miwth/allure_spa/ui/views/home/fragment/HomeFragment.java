package com.miwth.allure_spa.ui.views.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.cosmetic.CosmeticRepository;
import com.miwth.allure_spa.api.cosmetic.CosmeticsResponse;
import com.miwth.allure_spa.model.Cosmetics;
import com.miwth.allure_spa.model.Treatment;
import com.miwth.allure_spa.ui.adapter.CosmeticAdapter;
import com.miwth.allure_spa.ui.adapter.TreatmentAdapter;
import com.miwth.allure_spa.ui.views.WebviewActivity;
import com.miwth.allure_spa.util.callback.SideMenuCallBack;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static final String TAG = "HOME_FRAGMENT";
    LinearLayout llIntroduction, llVoucher, llService, llSanPhamDocQuyen, llSanPhamMayMoc, llCourse, llNews, llContact;
    RecyclerView rvCosmetic, rvTreatment, rvNews, rvBestSeller;
    ArrayList<Cosmetics> cosmeticsArrayList;
    ArrayList<Treatment> treatmentArrayList;
    Context context;
    CosmeticAdapter cosmeticAdapter;
    TreatmentAdapter treatmentAdapter;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();
        sharedPreferences = context.getSharedPreferences("api_tokens", Context.MODE_PRIVATE);

        rvCosmetic = homeFragmentView.findViewById(R.id.rvCosmetic);
        rvTreatment = homeFragmentView.findViewById(R.id.rvService);
        rvNews = homeFragmentView.findViewById(R.id.rvNews);
        rvBestSeller = homeFragmentView.findViewById(R.id.rvBestSeller);

        getData();

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

    private void getData() {
        CosmeticRepository cosmeticRepository = new CosmeticRepository(sharedPreferences.getString("token", null));
        cosmeticRepository.getCosmetics().enqueue(new Callback<CosmeticsResponse>() {
            @Override
            public void onResponse(Call<CosmeticsResponse> call, Response<CosmeticsResponse> response) {
                if (response.isSuccessful()) {
                    cosmeticsArrayList = (ArrayList<Cosmetics>) response.body().getData();
                    Log.d(TAG, "onResponse: " + cosmeticsArrayList.get(0).toString());
                    Log.d(TAG, "onResponse: " + response.body());
                    setUpRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<CosmeticsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setUpRecyclerView() {
        rvCosmetic.setHasFixedSize(true);
        rvTreatment.setHasFixedSize(true);
        rvNews.setHasFixedSize(true);
        rvBestSeller.setHasFixedSize(true);

        cosmeticAdapter = new CosmeticAdapter(context, cosmeticsArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvCosmetic.setLayoutManager(linearLayoutManager);
        rvCosmetic.setAdapter(cosmeticAdapter);

    }
}