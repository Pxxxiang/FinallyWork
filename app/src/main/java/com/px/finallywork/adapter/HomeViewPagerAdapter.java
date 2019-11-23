package com.px.finallywork.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.px.finallywork.R;
import com.px.finallywork.utils.ItemInterface;

import org.byteam.superadapter.IMulItemViewType;
import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HomeViewPagerAdapter extends SuperAdapter<ItemInterface> {
    private List<ItemInterface> list;

    public HomeViewPagerAdapter(Context context, List<ItemInterface> items, IMulItemViewType<ItemInterface> mulItemViewType) {
        super(context, items, mulItemViewType);
        this.list = items;
        System.out.println(items.size()+"===================");
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, ItemInterface item) {
        item.bindHolder(holder,getContext());
        Log.i("layoutPosition",layoutPosition+","+viewType);
    }

    @Override
    protected IMulItemViewType<ItemInterface> offerMultiItemViewType() {
        return new IMulItemViewType<ItemInterface>() {
            @Override
            public int getViewTypeCount() {
                System.out.println("7："+HomeViewPagerAdapter.super.getViewTypeCount());
                return HomeViewPagerAdapter.super.getViewTypeCount()+1;
            }

            @Override
            public int getItemViewType(int position, ItemInterface itemInterface) {
                return itemInterface.getLayoutId();
            }

            @Override
            public int getLayoutId(int viewType) {
                return viewType;
            }
        };
    }
//    @Override
//    public int getItemCount() {
//        return Integer.MAX_VALUE;
//    }
}
