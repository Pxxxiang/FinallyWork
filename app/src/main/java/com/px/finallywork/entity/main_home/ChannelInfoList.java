package com.px.finallywork.entity.main_home;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.px.finallywork.R;
import com.px.finallywork.adapter.HomeViewPagerAdapter;
import com.px.finallywork.entity.BaseBean;
import com.px.finallywork.utils.ItemInterface;

import org.byteam.superadapter.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChannelInfoList extends BaseBean {

    private List<ChannelInfo> channelInfo;

    @Override
    public void bindHolder(SuperViewHolder holder, Context context) {

        List<ItemInterface> itemList = new ArrayList<>();
        itemList.addAll(channelInfo);
        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(context, itemList, null);

        RecyclerView recyclerView = holder.findViewById(R.id.channe_re);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 5);
//        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        recyclerView.setAdapter(homeViewPagerAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(layoutManager);

        homeViewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_home_channe;
    }
}
