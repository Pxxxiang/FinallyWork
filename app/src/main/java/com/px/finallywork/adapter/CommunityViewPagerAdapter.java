package com.px.finallywork.adapter;

import android.content.Context;

import com.px.finallywork.utils.ItemInterface;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

public class CommunityViewPagerAdapter extends SuperAdapter<ItemInterface> {

    public CommunityViewPagerAdapter(Context context, List<ItemInterface> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, ItemInterface item) {
        item.bindHolder(holder,getContext());
    }
}
