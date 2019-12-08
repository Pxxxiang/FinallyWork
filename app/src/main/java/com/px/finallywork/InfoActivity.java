package com.px.finallywork;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class InfoActivity extends AppCompatActivity {


    private WebView wbGoodInfoMore;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ImageButton back = findViewById(R.id.ib_home_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        wbGoodInfoMore = findViewById(R.id.web);
        getData();
        setWebViewData(url);
    }

    private void getData() {
        Intent intent = this.getIntent();
        url = Objects.requireNonNull(intent.getExtras()).getString("url");
        Log.i("what_url", "getData: "+url);
    }

    private void setWebViewData(String url) {
        if (url != null) {

            //设置支持JavaScript
            WebSettings webSettings = wbGoodInfoMore.getSettings();
            webSettings.setUseWideViewPort(true);//支持双击页面变大变小
            webSettings.setJavaScriptEnabled(true);//设置支持JavaScript
            webSettings.setDomStorageEnabled(true);//设置对标签的支持
//webSettings.setAllowContentAccess();
//            webSettings.set
//            优先使用缓存
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            wbGoodInfoMore.loadUrl(url);
        }
    }
}
