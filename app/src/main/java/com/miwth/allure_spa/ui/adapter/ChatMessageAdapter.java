package com.miwth.allure_spa.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miwth.allure_spa.R;
import com.miwth.allure_spa.api.chat.MessagesResponse;
import com.miwth.allure_spa.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.MessageViewHolder> {

    private ArrayList<MessagesResponse> messageList;
    private int friendId;
    private Context context;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    public ChatMessageAdapter(ArrayList<MessagesResponse> messageList, int friendId, Context context){
        this.friendId = friendId;
        this.context = context;
        this.messageList = messageList;
    }

    @Override
    public int getItemViewType(int position) {
        MessagesResponse message = messageList.get(position);
        if(message.getSender_id() == friendId) {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
        return VIEW_TYPE_MESSAGE_SENT;
    }

    @NonNull
    @Override
    public ChatMessageAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == VIEW_TYPE_MESSAGE_SENT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_seen,
                    parent, false);
        }
        else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_received,
                    parent, false);
        }
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageAdapter.MessageViewHolder holder, int position) {
        MessagesResponse message = messageList.get(position);
//        if(message.get() == null){
//            holder.photo_image_view.setVisibility(View.GONE);
//            holder.setTexts(message);
//        }
//        else{
//            holder.photo_image_view.setImageURI(Uri.parse(message.getPhotoUrl()));
//            String dateTime = message.getTimestamp() + ", " + message.getDate();
//            holder.message_text_view.setVisibility(View.GONE);
//            holder.time_text_view.setText(dateTime);
//        }

        holder.photo_image_view.setImageResource(R.drawable.logo_with_text);
        String dateTime = String.valueOf(message.getSent_at());
        holder.message_text_view.setText(message.getMessage());
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        try{
            return messageList.size();
        }
        catch (NullPointerException e){
            Log.e("ChatAdapter", "getItemCount: ", e);
        }
        return 0;
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView message_text_view;
        private TextView time_text_view;
        private ImageView photo_image_view;


        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            message_text_view = itemView.findViewById(R.id.text_message_body);
            time_text_view = itemView.findViewById(R.id.text_message_time);
            photo_image_view = itemView.findViewById(R.id.photoImageView);
        }

        private void setTexts(Message message) {
            message_text_view.setText(message.getMessage());
            String dateTime = message.getSent_at();
            time_text_view.setText(dateTime);
        }

    }

}

