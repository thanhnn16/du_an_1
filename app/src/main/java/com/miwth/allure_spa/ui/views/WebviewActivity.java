package com.miwth.allure_spa.ui.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.miwth.allure_spa.R;

public class WebviewActivity extends AppCompatActivity {

    WebView webView;
    TextView tvTitle;
    ImageButton ibBack;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white, null));

        tvTitle = findViewById(R.id.tvTitle);
        ibBack = findViewById(R.id.btnBack);
        ibBack.setOnClickListener(v -> {
            finish();
        });

        webView = findViewById(R.id.webview);

        WebViewClient webViewClient = new WebViewClient();
        webView.setWebViewClient(webViewClient);

        String baseUrl = "https://www.allurespa.com.vn/";

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");

        tvTitle.setText(title);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(baseUrl + url);

    }


}