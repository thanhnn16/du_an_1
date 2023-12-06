package com.miwth.allure_spa.ui.views.home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.search.SearchRepository;
import com.miwth.allure_spa.api.search.SearchResponse;
import com.miwth.allure_spa.model.Search;
import com.miwth.allure_spa.ui.adapter.SearchResultAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";

    RecyclerView rvSearchResult;
    RecyclerView recentSearch;

    SearchView searchView;
    SearchResultAdapter searchResultAdapter;
    TextView tvRecentSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,
                container, false);

        tvRecentSearch = view.findViewById(R.id.tvRecentSearch);

        searchView = view.findViewById(R.id.searchView);
        recentSearch = view.findViewById(R.id.rvRecentSearch);
        rvSearchResult = view.findViewById(R.id.rvSearchResult);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvSearchResult.setLayoutManager(linearLayoutManager);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchRepository searchRepository = new SearchRepository();
                Call<SearchResponse> call = searchRepository.getResults(query);
                call.enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        if (response.isSuccessful()) {
                            ArrayList<Search> searchArrayList = new ArrayList<>();
                            if (response.body().getResults() != null) {
                                searchArrayList.addAll(response.body().getResults());
                                recentSearch.setVisibility(View.GONE);
                                rvSearchResult.setVisibility(View.VISIBLE);
                                searchResultAdapter = new SearchResultAdapter(searchArrayList);
                                rvSearchResult.setAdapter(searchResultAdapter);
                            } else {
                                rvSearchResult.setVisibility(View.GONE);
                                tvRecentSearch.setText("Không tìm thấy kết quả nào cho \"" + query + "\"");


                            }
                        } else {
                            Log.d(TAG, "onResponse: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    rvSearchResult.setVisibility(View.GONE);
                    tvRecentSearch.setText("");
                } else
                    rvSearchResult.setVisibility(View.VISIBLE);

                return false;
            }
        });


        return view;
    }
}