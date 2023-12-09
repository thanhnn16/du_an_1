package com.miwth.allure_spa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.splashscreen.SplashScreen;

import com.miwth.allure_spa.api.PusherService;
import com.miwth.allure_spa.api.auth.TokenManager;
import com.miwth.allure_spa.ui.views.home.HomeActivity;
import com.miwth.allure_spa.ui.views.welcome.OnboardingActivity;
import com.miwth.allure_spa.ui.views.welcome.WelcomeActivity;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_ACTIVITY";
    Boolean splashLoaded = false;

    SharedPreferences sharedPreferences;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        loading();
        splashScreen.setKeepOnScreenCondition(() -> !splashLoaded);

    }

    private void loading() {
        sharedPreferences = getSharedPreferences("onboarding", MODE_PRIVATE);
        boolean firstTime = sharedPreferences.getBoolean("firstTime", true);
//        getFCMToken();
//        setUpPusher();
        PusherService pusherService = new PusherService(this);
        pusherService.connect();

        tokenManager = new TokenManager(this);
        Log.d(TAG, "logout: " + tokenManager.getToken());
        new Handler().postDelayed(() -> {
            splashLoaded = true;
            if (!tokenManager.getToken().isEmpty()) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            } else if (firstTime) {
                startActivity(new Intent(MainActivity.this, OnboardingActivity.class));
                finish();
            } else {
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                finish();
            }
        }, 1000);
    }

//    private void getFCMToken() {
//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
//            if (!task.isSuccessful()) {
//                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
//            } else {
//                String token = task.getResult();
//                Log.d(TAG, "getFCMToken: " + token);
//                tokenManager.saveFCMToken(token);
//            }
//        });
//    }

//    private void setUpPusher() {
//        PusherOptions options = new PusherOptions();
//        options.setCluster("ap1");
//
//        Pusher pusher = new Pusher("ba94d855ff15be911df8", options);
//
//        pusher.connect(new ConnectionEventListener() {
//            @Override
//            public void onConnectionStateChange(ConnectionStateChange change) {
//                Log.i("Pusher", "State changed from " + change.getPreviousState() + " to " + change.getCurrentState());
//            }
//
//            @Override
//            public void onError(String message, String code, Exception e) {
//                Log.i("Pusher", "There was a problem connecting! " + "\ncode: " + code + "\nmessage: " + message + "\nException: " + e);
//            }
//        }, ConnectionState.ALL);
//
//        Channel channel = pusher.subscribe("allurespa");
//
//        channel.bind("chat-event", event -> {
//            Intent intent = new Intent(this, FcmNotificationService.class);
//
//
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                    Log.d(TAG, "Pusher: " + event.toString());
//                    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
//
//                            String title = "Allure Spa";
//                            PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
//
//                    NotificationCompat.Builder builder = new NotificationCompat.
//                    Builder(this, event.getChannelName()).
//                    setSmallIcon(R.drawable.logo_without_text)
//                    .setContentTitle(title)
//                    .setContentText(event.getData())
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                    .setAutoCancel(true)
//                    .setStyle(new NotificationCompat.BigTextStyle().bigText(event.getData()))
//                    .setContentIntent()
//            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//                onRequestPermissionsResult(1, new String[]{"android.permission.RECEIVE_NOTIFICATIONS"}, new int[]{PackageManager.PERMISSION_GRANTED});
//                return;
//            }
//            notificationManager.notify(1, builder.build());
//
//
//        });
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(MainActivity.this, "Từ bây giờ bạn có thể nhận được thông báo từ Allure Spa", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(MainActivity.this, "Bạn không thể nhận được thông báo từ Allure Spa", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}