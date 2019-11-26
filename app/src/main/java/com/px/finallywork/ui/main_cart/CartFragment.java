package com.px.finallywork.ui.main_cart;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.px.finallywork.R;
import com.px.finallywork.adapter.HomeViewPagerAdapter;
import com.px.finallywork.entity.BaseBean;
import com.px.finallywork.entity.main_goods.GoodsBean;
import com.px.finallywork.utils.CacheUtils;
import com.px.finallywork.utils.ItemInterface;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private List<ItemInterface> itemList;
    private HomeViewPagerAdapter homeViewPagerAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TextView editText;
    private LinearLayout cleckAll;
    private LinearLayout editAll;

    private float totalPrice;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_cart, container, false);

        editText = view.findViewById(R.id.tv_shopcart_edit);
        cleckAll = view.findViewById(R.id.ll_check_all);
        editAll = view.findViewById(R.id.ll_delete);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().equals("编辑")) {
                    editText.setText("完成");
                    editAll.setVisibility(View.VISIBLE);
                    cleckAll.setVisibility(View.GONE);
                }else{
                    editText.setText("编辑");
                    editAll.setVisibility(View.GONE);
                    cleckAll.setVisibility(View.VISIBLE);
                }
            }
        });


        itemList = new ArrayList<>();
        itemList.addAll(BaseBean.goodList);
        recyclerView = view.findViewById(R.id.main_cart_re);

        TextView textView = view.findViewById(R.id.tv_shopcart_total);

        for (int i = 0; i < BaseBean.singlePrice.size(); i++) {
            totalPrice += BaseBean.singlePrice.get(i) * BaseBean.num.get(i);
        }
        textView.setText("￥"+totalPrice);

//        itemList.add(goodsBean);
//        Log.i("ssssss", BaseBean.goodList.size() + "");
        homeViewPagerAdapter = new HomeViewPagerAdapter(getContext(), itemList, null);
        linearLayoutManager = new LinearLayoutManager(getContext());

//        recyclerView.setItemViewCacheSize(BaseBean.goodList.size());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeViewPagerAdapter);
//        gList = BaseBean.goodList;
        homeViewPagerAdapter.notifyDataSetChanged();

        return view;
    }

}