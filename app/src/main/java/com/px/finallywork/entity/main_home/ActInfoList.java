package com.px.finallywork.entity.main_home;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.px.finallywork.R;
import com.px.finallywork.adapter.BannerViewPagerAdapter;
import com.px.finallywork.adapter.HomeViewPagerAdapter;
import com.px.finallywork.entity.BaseBean;
import com.px.finallywork.ui.home.SmoothLinearLayoutManager;
import com.px.finallywork.utils.Constants;
import com.px.finallywork.utils.ItemInterface;

import org.byteam.superadapter.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActInfoList extends BaseBean {

    private List<ActInfo> actInfoList;


    @Override
    public void bindHolder(SuperViewHolder holder, Context context) {

        BannerViewPagerAdapter bannerViewPagerAdapter;
        List<ItemInterface> itemList = new ArrayList<>();

        final List imageList = new ArrayList();
        for (ActInfo b : actInfoList) {
            imageList.add(Constants.BASE_URl_IMAGE + b.getIconUrl());
        }
        itemList.addAll(imageList);

        bannerViewPagerAdapter = new BannerViewPagerAdapter(context,itemList,R.layout.banner_view);

        final RecyclerView recyclerView = holder.findViewById(R.id.act_re);
        final SmoothLinearLayoutManager layoutManager = new SmoothLinearLayoutManager(context);

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(bannerViewPagerAdapter);
        recyclerView.setLayoutManager(layoutManager);
        //自动切换
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setOnFlingListener(null);
        snapHelper.attachToRecyclerView(recyclerView);

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
        return R.layout.main_home_act;
    }
}
