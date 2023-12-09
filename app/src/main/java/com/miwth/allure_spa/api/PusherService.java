package com.miwth.allure_spa.api;


import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.gson.Gson;
import com.miwth.allure_spa.R;
import com.miwth.allure_spa.ui.views.chat.ChatActivity;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

public class PusherService extends NotificationCompat {
    private Pusher pusher;
    private Channel channel;
    private Context context;
    private static final String CHANNEL_ID = "allurespa";

    public PusherService(Context context) {
        this.context = context;
        PusherOptions options = new PusherOptions();
        options.setCluster("ap1");
        pusher = new Pusher("ba94d855ff15be911df8", options);
    }


    public void connect() {
        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                Log.i("Pusher", "State changed from " + change.getPreviousState() + " to " + change.getCurrentState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                Log.i("Pusher", "There was a problem connecting! " + "\ncode: " + code + "\nmessage: " + message + "\nException: " + e);
            }
        }, ConnectionState.ALL);

        channel = pusher.subscribe("allurespa");
        channel.bind("chat-event", event -> {
            Gson gson = new Gson();
            Log.d("Pusher", "connect: " + event.getData());
            EventMessage eventMessage = gson.fromJson(event.getData(), EventMessage.class);
            Message message = eventMessage.getMessage();
            String sender = eventMessage.getSender();

            sendNotification(sender, message.getMessage());
        });

    }

    private void sendNotification(String sender, String data) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.drawable.logo_without_text).setContentTitle("Tin nhắn từ: " + sender).setContentText(data).setAutoCancel(true).setContentIntent(pendingIntent).setStyle(new NotificationCompat.BigTextStyle().bigText(data));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Allure Spa", NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);

        notificationManager.notify(0, notificationBuilder.build());

    }

    public void disconnect() {
        pusher.disconnect();
    }

    public class EventMessage {
        private Message message;
        private String sender;

        public Message getMessage() {
            return message;
        }

        public String getSender() {
            return sender;
        }

        // getters and setters
    }

    class Message {
        private int sender_id;
        private String receiver_id;
        private String message;
        private String sent_at;
        private String updated_at;
        private String created_at;
        private int id;

        public int getSender_id() {
            return sender_id;
        }

        public String getReceiver_id() {
            return receiver_id;
        }

        public String getMessage() {
            return message;
        }

        public String getSent_at() {
            return sent_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public int getId() {
            return id;
        }
    }
}