package com.px.finallywork.ui.main_user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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

    private ImageButton ibUserIconAvator;
    private ImageButton ibUserMessage;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View  view= inflater.inflate(R.layout.fragment_main_user, container, false);

        findView(view);

        ibUserIconAvator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ibUserMessage.setOnClickListener(view2 -> {
            Intent intent = new Intent(getContext(), MessageCenterActivity.class);
            startActivity(intent);
        });
        return view;
    }

    private void findView(View view) {
        ibUserIconAvator =  view.findViewById(R.id.ib_user_icon_avator);
        TextView tvUsername = view.findViewById(R.id.tv_username);
        TextView tvAllOrder = view.findViewById(R.id.tv_all_order);
        TextView tvUserPay = view.findViewById(R.id.tv_user_pay);
        TextView tvUserReceive = view.findViewById(R.id.tv_user_receive);
        TextView tvUserFinish = view.findViewById(R.id.tv_user_finish);
        TextView tvUserDrawback = view.findViewById(R.id.tv_user_drawback);
        TextView tvUserLocation = view.findViewById(R.id.tv_user_location);
        TextView tvUserCollect = view.findViewById(R.id.tv_user_collect);
        TextView tvUserCoupon = view.findViewById(R.id.tv_user_coupon);
        TextView tvUserScore = view.findViewById(R.id.tv_user_score);
        TextView tvUserPrize = view.findViewById(R.id.tv_user_prize);
        TextView tvUserTicket = view.findViewById(R.id.tv_user_ticket);
        TextView tvUserInvitation = view.findViewById(R.id.tv_user_invitation);
        TextView tvUserCallcenter = view.findViewById(R.id.tv_user_callcenter);
        TextView tvUserFeedback = view.findViewById(R.id.tv_user_feedback);
        TextView tvUsercenter = view.findViewById(R.id.tv_usercenter);
        ImageButton ibUserSetting = view.findViewById(R.id.ib_user_setting);
        ibUserMessage = view.findViewById(R.id.ib_user_message);
        ScrollView scrollView = view.findViewById(R.id.scrollview);
    }
}