package com.example.newsapp;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity{
    private TextView tv_author, tv_publishedAt, tv_title, tv_description;
    private ImageView imgv_image;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv_author = findViewById(R.id.author);
        tv_publishedAt = findViewById(R.id.date);
        tv_title = findViewById(R.id.title);
        imgv_image = findViewById(R.id.backdrop);


        tv_author.setText(getIntent().getStringExtra("authorIntent"));
        tv_publishedAt.setText(getIntent().getStringExtra("publishedAtIntent"));
        tv_title.setText(getIntent().getStringExtra("titleIntent"));
        Glide.with(this).load(getIntent().getStringExtra("imageIntent")).into(imgv_image);


        url = getIntent().getStringExtra("urlIntent");


        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}
