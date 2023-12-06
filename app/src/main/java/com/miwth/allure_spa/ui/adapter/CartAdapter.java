package com.miwth.allure_spa.ui.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.CartItem;
import com.miwth.allure_spa.util.callback.CartItemCheckCallBack;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<CartItem> cartItems;

    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        // bind data to the views in holder
        holder.tvName.setText(cartItem.getName());
        holder.tvPrice.setText(cartItem.getPrice());
        holder.tvQty.setText(cartItem.getQty());

        Picasso.get().load(cartItem.getImage()).into(holder.ivImage);

        holder.cbSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                int totalPrice = Integer.parseInt(cartItem.getPrice()) * Integer.parseInt(cartItem.getQty());
                CartItemCheckCallBack cartItemCheckCallBack = (CartItemCheckCallBack) buttonView.getContext();
                cartItemCheckCallBack.onCheck(totalPrice);
            }
        });


    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvQty;
        ImageView ivImage;

        CheckBox cbSelect;

        ImageButton btnDelete, btnMinus, btnPlus;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQty = itemView.findViewById(R.id.tvQty);
            ivImage = itemView.findViewById(R.id.ivImage);

            cbSelect = itemView.findViewById(R.id.cbSelect);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);


            btnMinus.setOnClickListener(v -> {
                CartItem cartItem = cartItems.get(getAdapterPosition());
                int qty = Integer.parseInt(cartItem.getQty());
                if (qty > 1) {
                    qty--;
                    cartItem.setQty(String.valueOf(qty));
                    tvQty.setText(String.valueOf(qty));

                    SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences("Cart", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(cartItem.getName(), cartItem.toString());
                    editor.apply();
                }
            });

            btnPlus.setOnClickListener(v -> {
                CartItem cartItem = cartItems.get(getAdapterPosition());
                int qty = Integer.parseInt(cartItem.getQty());
                qty++;
                cartItem.setQty(String.valueOf(qty));
                tvQty.setText(String.valueOf(qty));

                SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences("Cart", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(cartItem.getName(), cartItem.toString());
                editor.apply();
            });

            btnDelete.setOnClickListener(v -> {
                CartItem cartItem = cartItems.get(getAdapterPosition());

                SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences("Cart", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(cartItem.getName());
                editor.apply();

                cartItems.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
            });


        }

    }
}