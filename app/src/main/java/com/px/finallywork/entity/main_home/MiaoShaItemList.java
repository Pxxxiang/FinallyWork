package com.px.finallywork.entity.main_home;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
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
public class MiaoShaItemList extends BaseBean {

    private List<MiaoShaItem> list;

    @Override
    public void bindHolder(SuperViewHolder holder, Context context) {

        List<ItemInterface> itemList = new ArrayList<>();
        itemList.addAll(list);
        RecyclerView recyclerView = holder.findViewById(R.id.sec_re2);

        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(context,itemList,null);
        recyclerView.setAdapter(homeViewPagerAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setNestedScrollingEnabled(false);
        homeViewPagerAdapter.notifyDataSetChanged();

    }

    @Override
    public int getLayoutId() {
        return R.layout.main_home_seckill_re;
    }
}
