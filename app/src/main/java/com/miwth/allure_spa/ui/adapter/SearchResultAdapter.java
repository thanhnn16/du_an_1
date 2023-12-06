package com.miwth.allure_spa.ui.adapter;

import static com.miwth.allure_spa.api.ApiConstants.WEB_BASE_URL;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.Search;
import com.squareup.picasso.Picasso;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private List<Search> searchResults;

    public SearchResultAdapter(List<Search> searchResults) {
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultAdapter.ViewHolder holder, int position) {
        Search searchResult = searchResults.get(position);
        holder.tvTitle.setText(searchResult.getTitle());
        holder.tvDescription.setText(searchResult.getDescription());
        Picasso.get().load(WEB_BASE_URL + searchResult.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}