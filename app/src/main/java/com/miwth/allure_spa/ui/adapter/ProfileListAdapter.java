package com.miwth.allure_spa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.model.FunctionItem;
import com.miwth.allure_spa.ui.views.home.fragment.Profile.DetailUser;
import com.miwth.allure_spa.ui.views.home.fragment.Profile.Settings;

import java.util.List;

public class ProfileListAdapter extends BaseAdapter {
    private List<FunctionItem> itemList;
    private Context context;

    public ProfileListAdapter(Context context, List<FunctionItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_profile, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.iconImageView = convertView.findViewById(R.id.imgIcon);
            viewHolder.textView = convertView.findViewById(R.id.tvFunction);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final FunctionItem functionItem = itemList.get(position);
        viewHolder.iconImageView.setImageResource(functionItem.getIconResId());
        viewHolder.textView.setText(functionItem.getFunctionName());

        if ("Thông Tin Cá Nhân".equals(functionItem.getFunctionName())) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailUser.class);
                    context.startActivity(intent);
                }
            });
        }


        if ("Cài Đặt".equals(functionItem.getFunctionName())) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Settings.class);
                    context.startActivity(intent);
                }
            });
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView iconImageView;
        TextView textView;
    }
}
