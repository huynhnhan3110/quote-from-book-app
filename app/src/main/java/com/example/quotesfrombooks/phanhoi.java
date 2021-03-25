package com.example.quotesfrombooks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class phanhoi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phanhoi);
        WebView w = (WebView)findViewById(R.id.link_webview);
        WebSettings webSettings = w.getSettings();
        webSettings.setJavaScriptEnabled(true);
        w.loadUrl("file:///android_asset/phanhoi.html");

    }
}