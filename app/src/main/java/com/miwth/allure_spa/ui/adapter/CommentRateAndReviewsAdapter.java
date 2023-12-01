package com.miwth.allure_spa.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.Comment;

import java.util.List;

public class CommentRateAndReviewsAdapter extends RecyclerView.Adapter<CommentRateAndReviewsAdapter.ViewHolder> {
    private List<Comment> commentList;

    public CommentRateAndReviewsAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate_and_reviews, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.txtNameUser.setText(comment.getUserName());
        holder.ratingBar.setRating(comment.getRating());
        holder.tvTime.setText(comment.getTime());
        holder.txtContent.setText(comment.getContent());
        holder.imgLike.setImageResource(comment.isUseful() ? R.drawable.ic_like : R.drawable.ic_like);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameUser, tvTime, txtContent;
        RatingBar ratingBar;
        ImageView imgLike;

        ShapeableImageView avtUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avtUser = itemView.findViewById(R.id.avtUser);
            txtNameUser = itemView.findViewById(R.id.txtNameUser);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            tvTime = itemView.findViewById(R.id.tvTime);
            txtContent = itemView.findViewById(R.id.txtContent);
            imgLike = itemView.findViewById(R.id.imgLike);
        }
    }
}