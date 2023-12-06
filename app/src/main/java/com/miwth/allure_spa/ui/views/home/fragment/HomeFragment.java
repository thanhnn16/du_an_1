package com.miwth.allure_spa.ui.views.home.fragment;

import android.content.Context;
import android.content.Intent;
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
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.api.cosmetic.CosmeticsRepository;
import com.miwth.allure_spa.api.cosmetic.CosmeticsResponse;
import com.miwth.allure_spa.api.treatment.TreatmentsRepository;
import com.miwth.allure_spa.api.treatment.TreatmentsResponse;
import com.miwth.allure_spa.model.Cosmetics;
import com.miwth.allure_spa.model.Treatments;
import com.miwth.allure_spa.ui.adapter.BestSellCosmeticAdapter;
import com.miwth.allure_spa.ui.adapter.CosmeticAdapter;
import com.miwth.allure_spa.ui.adapter.TreatmentAdapter;
import com.miwth.allure_spa.ui.views.Cart.Cart;
import com.miwth.allure_spa.ui.views.SeeMoreActivity;
import com.miwth.allure_spa.ui.views.WebviewActivity;
import com.miwth.allure_spa.util.callback.SideMenuCallBack;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static final String TAG = "HOME_FRAGMENT";
    LinearLayout llIntroduction, llVoucher, llService, llSanPhamDocQuyen, llSanPhamMayMoc, llCourse, llNews, llContact;
    RecyclerView rvCosmetic, rvTreatment, rvBestSeller;
    LinearLayout tvSeeMoreCosmetic, tvSeeMoreTreatment, tvSeeMoreBestSeller;
    ImageButton btnCart;
    ArrayList<Cosmetics> cosmeticsArrayList;
    ArrayList<Cosmetics> bestCosmeticsArrayList;
    ArrayList<Treatments> treatmentArrayList;
    Context context;
    CosmeticAdapter cosmeticAdapter;
    TreatmentAdapter treatmentAdapter;
    BestSellCosmeticAdapter bestSellCosmeticAdapter;
    String token;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();
        assert context != null;
        token = new TokenManager(context).getToken();

        rvCosmetic = homeFragmentView.findViewById(R.id.rvCosmetic);
        rvTreatment = homeFragmentView.findViewById(R.id.rvService);
        rvBestSeller = homeFragmentView.findViewById(R.id.rvBestSeller);

        tvSeeMoreBestSeller = homeFragmentView.findViewById(R.id.best_seller_see_more);
        tvSeeMoreCosmetic = homeFragmentView.findViewById(R.id.cosmetic_see_more);
        tvSeeMoreTreatment = homeFragmentView.findViewById(R.id.service_see_more);


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

        ImageButton btnCart = homeFragmentView.findViewById(R.id.btnCart);
        btnCart.setOnClickListener(v -> {
            Intent intent1 = new Intent(getActivity(), Cart.class);
            startActivity(intent1);
        });

        setUpSeeMore();
        getData();
        return homeFragmentView;
    }

    private void getData() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<ArrayList<Cosmetics>> getCosmeticsTask = () -> {
            CosmeticsRepository cosmeticRepository = new CosmeticsRepository();
            Response<CosmeticsResponse> response = cosmeticRepository.getCosmetics().execute();
            if (response.isSuccessful()) {
                ArrayList<Cosmetics> allCosmetics = (ArrayList<Cosmetics>) response.body().getData();
                return new ArrayList<>(allCosmetics.subList(0, Math.min(allCosmetics.size(), 4)));
            } else {
                return new ArrayList<>();
            }
        };

        Callable<ArrayList<Cosmetics>> getBestSellTask = () -> {
            CosmeticsRepository cosmeticRepository = new CosmeticsRepository();
            Response<CosmeticsResponse> response = cosmeticRepository.getCosmetics().execute();
            if (response.isSuccessful()) {
                ArrayList<Cosmetics> allCosmetics = (ArrayList<Cosmetics>) response.body().getData();
                return new ArrayList<>(allCosmetics.subList(0, Math.min(allCosmetics.size(), 4)));
            } else {
                return new ArrayList<>();
            }
        };


        Callable<ArrayList<Treatments>> getTreatmentsTask = () -> {
            TreatmentsRepository treatmentsRepository = new TreatmentsRepository();
            Response<TreatmentsResponse> response = treatmentsRepository.getTreatments().execute();
            if (response.isSuccessful()) {
                Log.d(TAG, "getData: " + response.body().getData().get(0).getTreatmentName());
                ArrayList<Treatments> allTreatments = (ArrayList<Treatments>) response.body().getData();
                return new ArrayList<>(allTreatments.subList(0, Math.min(allTreatments.size(), 4)));
            } else {
                return new ArrayList<>();
            }
        };

        Future<ArrayList<Cosmetics>> cosmeticsFuture = executorService.submit(getCosmeticsTask);
        Future<ArrayList<Cosmetics>> bestSellFuture = executorService.submit(getBestSellTask);
        Future<ArrayList<Treatments>> treatmentsFuture = executorService.submit(getTreatmentsTask);

        try {
            cosmeticsArrayList = cosmeticsFuture.get();
            treatmentArrayList = treatmentsFuture.get();
            bestCosmeticsArrayList = bestSellFuture.get();

            Log.d(TAG, "getData: " + treatmentArrayList.get(0).getTreatmentName());
            setUpRecyclerView();
        } catch (Exception e) {
            Log.d(TAG, "getData: " + e.getMessage());
        }
    }

    private void setUpRecyclerView() {
        rvCosmetic.setHasFixedSize(true);
        rvTreatment.setHasFixedSize(true);
        rvBestSeller.setHasFixedSize(true);

        Log.d(TAG, "setUpRecyclerView: " + cosmeticsArrayList.size());
        Log.d(TAG, "setUpRecyclerView: " + treatmentArrayList.size());
        cosmeticAdapter = new CosmeticAdapter(context, cosmeticsArrayList);
        treatmentAdapter = new TreatmentAdapter(context, treatmentArrayList);
        bestSellCosmeticAdapter = new BestSellCosmeticAdapter(context, bestCosmeticsArrayList);


        rvCosmetic.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rvCosmetic.setAdapter(cosmeticAdapter);

        rvTreatment.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rvTreatment.setAdapter(treatmentAdapter);

        rvBestSeller.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rvBestSeller.setAdapter(bestSellCosmeticAdapter);

    }

    private void setUpSeeMore() {
        intent = new Intent(context, SeeMoreActivity.class);
        tvSeeMoreCosmetic.setOnClickListener(v -> {
            intent.putExtra("title", getResources().getString(R.string.cosmetic_cap));
            startActivity(intent);
        });
        tvSeeMoreTreatment.setOnClickListener(v -> {
            intent.putExtra("title", getResources().getString(R.string.treatment_cap));
            startActivity(intent);
        });
        tvSeeMoreBestSeller.setOnClickListener(v -> {
            intent.putExtra("title", getResources().getString(R.string.best_seller_cap));
            startActivity(intent);
        });
    }
}