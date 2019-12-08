package com.px.finallywork.entity.main_home;

import android.content.Context;
import android.os.Handler;
import android.os.Message;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.px.finallywork.R;
import com.px.finallywork.adapter.BannerViewPagerAdapter;
import com.px.finallywork.adapter.HomeViewPagerAdapter;
import com.px.finallywork.entity.BaseBean;

import com.px.finallywork.ui.home.BannerIndicator;
import com.px.finallywork.ui.home.SmoothLinearLayoutManager;
import com.px.finallywork.utils.Constants;
import com.px.finallywork.utils.ItemInterface;

import org.byteam.superadapter.SuperViewHolder;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BannerInfoL extends BaseBean {

    private List<BannerInfo> bannerInfo = new ArrayList<>();
    private SmoothLinearLayoutManager layoutManager;
    private Handler handler;
    private int i;
    private RecyclerView recyclerView;
    private ArrayList<String> urlList2 = new ArrayList<>();
    private BannerIndicator bannerIndicator;
    private List imageList;

    @Override
    public void bindHolder(SuperViewHolder holder, Context context) {
        List<ItemInterface> itemList = new ArrayList<>();
        imageList = new ArrayList();
        BannerViewPagerAdapter bannerViewPagerAdapter;

        for (BannerInfo b : bannerInfo) {
            imageList.add(Constants.BASE_URl_IMAGE + b.getImage());
            urlList2.add(Constants.BASE_URl_IMAGE + b.getValue().getUrl());
        }

        itemList.addAll(imageList);
        bannerViewPagerAdapter = new BannerViewPagerAdapter(context, itemList, urlList2, R.layout.banner_view);

        recyclerView = holder.findViewById(R.id.vp_pager);

        layoutManager = new SmoothLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.setScrollEnabled(false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(bannerViewPagerAdapter);

        recyclerView.setOnFlingListener(null);
        //自动切换
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        //底部红点
        bannerIndicator = holder.findViewById(R.id.indicator);
        bannerIndicator.setNumber(imageList.size());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    i = layoutManager.findFirstVisibleItemPosition() % imageList.size();
                    bannerIndicator.setPosition(i);
                }
            }
        });


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                recyclerView.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() + 1);
            }
        }, 2000, 2000, TimeUnit.MILLISECONDS);
        recyclerView.scrollToPosition(imageList.size() * 10);

        bannerViewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_view_page;
    }
}
