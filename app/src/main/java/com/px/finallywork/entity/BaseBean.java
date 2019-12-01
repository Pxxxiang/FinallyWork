package com.px.finallywork.entity;

import android.content.Context;

import com.px.finallywork.utils.ItemInterface;

import org.byteam.superadapter.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public abstract class BaseBean implements ItemInterface {
    public int viewType;
    public static List<ItemInterface> goodList = new ArrayList<>();
    public static List<String> pid = new ArrayList<>();
    public static List<Integer> num = new ArrayList<>();
    public static List<Float> singlePrice = new ArrayList<>();
    public static float ttPrice;


    @Override
    public abstract void bindHolder(SuperViewHolder holder, Context context);


    @Override
    public abstract int getLayoutId();


}
