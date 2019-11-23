package com.px.finallywork.utils;

import android.content.Context;
import android.os.Handler;

import org.byteam.superadapter.SuperViewHolder;

public interface ItemInterface {

    void bindHolder(SuperViewHolder holder, Context context) ;
//    void bindHolder(SuperViewHolder holder, Context context, int position);
    int getLayoutId();
}
