package com.px.finallywork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class SearchActivity extends AppCompatActivity {

    @ViewInject(R.id.cancel)
    Button cancel;

    @ViewInject(R.id.et_search)
    EditText searchText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        x.view().inject(this);

        cancel.setOnClickListener((view) -> {finish();});
        //自动获取焦点k
        searchText.setFocusable(true);
        searchText.setFocusableInTouchMode(true);
        searchText.requestFocus();

    }
}
