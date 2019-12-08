package com.px.finallywork.ui.main_user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.px.finallywork.MessageCenterActivity;
import com.px.finallywork.R;

public class UserFragment extends Fragment {

    private ImageButton ibUserMessage;
    private ScrollView scrollView;
    private ImageButton ibUserIconAvator;
    private TextView tvUsercenter;

    @SuppressLint("Range")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View  view= inflater.inflate(R.layout.fragment_main_user, container, false);


        findView(view);
        tvUsercenter.setAlpha(0);
        ibUserMessage.setOnClickListener(view2 -> {
            Intent intent = new Intent(getContext(), MessageCenterActivity.class);
            startActivity(intent);
        });

        scrollView.setOnTouchListener((v, event) -> {
            int[] location = new int[2];
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE://下滑是正，上滑是负
                    ibUserIconAvator.getLocationOnScreen(location);//初始状态为125,即最大值是125，全部显示不透明是（40？）
                    float i = (location[1] - 40) / 85f;
                    tvUsercenter.setAlpha(1 - i);
                    break;
            }
            return false;
        });
        return view;
    }

    private void findView(View view) {
        ibUserIconAvator = view.findViewById(R.id.ib_user_icon_avator);
        tvUsercenter = view.findViewById(R.id.tv_usercenter);
        ibUserMessage = view.findViewById(R.id.ib_user_message);
        scrollView = view.findViewById(R.id.scrollview);
    }
}