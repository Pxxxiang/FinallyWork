package com.px.finallywork.entity.main_home;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class HotInfoList extends BaseBean {

    private List<HotInfo> hotInfo;
    @Override
    public void bindHolder(SuperViewHolder holder, Context context) {
        List<ItemInterface> itemList = new ArrayList<>();
        itemList.addAll(hotInfo);

        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(context,itemList,null);
        RecyclerView recyclerView = holder.findViewById(R.id.hot_re);
        GridLayoutManager layoutManager = new GridLayoutManager(context,2);

        recyclerView.setAdapter(homeViewPagerAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(layoutManager);

        homeViewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_home_hot;
    }
}
