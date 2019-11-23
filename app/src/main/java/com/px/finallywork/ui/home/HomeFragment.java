package com.px.finallywork.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.px.finallywork.R;
import com.px.finallywork.adapter.HomeViewPagerAdapter;
import com.px.finallywork.entity.main_home.ActInfoList;
import com.px.finallywork.entity.main_home.BannerInfoL;
import com.px.finallywork.entity.main_home.ChannelInfoList;
import com.px.finallywork.entity.main_home.Community;
import com.px.finallywork.entity.main_home.HotInfoList;
import com.px.finallywork.entity.main_home.RecommendInfoList;
import com.px.finallywork.entity.main_home.SeckillInfo;
import com.px.finallywork.utils.Constants;
import com.px.finallywork.utils.ItemInterface;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private List<ItemInterface> itemList;
    private HomeViewPagerAdapter homeViewPagerAdapter;
    @ViewInject(R.id.recycleView)
    private RecyclerView recyclerView;

    @ViewInject(R.id.home_top)
    private ImageView imageView;

    private int distance;

    private boolean visible = true;
    private LinearLayoutManager linearLayoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        itemList = new ArrayList<>();
        initData();
//        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        x.view().inject(this, view);
        homeViewPagerAdapter = new HomeViewPagerAdapter(getContext(), itemList, null);
//        Log.i("size", itemList.size() + "");
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setAdapter(homeViewPagerAdapter);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


//置顶显示
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView re, int dx, int dy) {
                super.onScrolled(re, dx, dy);
                //获取RecyclerView当前顶部显示的第一个条目对应的索引
                int position = linearLayoutManager.findFirstVisibleItemPosition();
                //根据索引来获取对应的itemView
                View firstVisiableChildView = linearLayoutManager.findViewByPosition(position);
                //获取当前显示条目的高度
                int itemHeight = firstVisiableChildView.getHeight();
                //获取当前Recyclerview 偏移量
                int flag = (position) * itemHeight - firstVisiableChildView.getTop();
                //注意事项：recyclerView不要设置padding
                if (flag == 0)
                    imageView.setVisibility(View.GONE);
                else {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            smoothMoveToPosition(recyclerView, 0);
                        }
                    });
                }
            }

        });
        return view;
    }

    private void initData() {


        String url = Constants.HOME_URL;

        RequestParams params = new RequestParams(url);
//params.setCacheMaxAge(60*1000);
        x.http().get(params, new Callback.CacheCallback<String>() {


            //            private boolean hasError = false;
            private String re = null;


            @Override
            public boolean onCache(String result) {
                this.re = result;
//                Log.i("reOnCash",result);
//                System.out.println("reOnCash"+result);
                //存入本地缓存
                return true;
            }

            @Override
            public void onSuccess(String result) {
                if (result == null) {
                    result = this.re;
                }

//                Community commodity = new Community();
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
                Gson gson = gsonBuilder.create();
//                Log.i("onSuccessresult",result);
//                System.out.println(result);

                Community commodity = gson.fromJson(result, Community.class);

                BannerInfoL bannerInfoL = new BannerInfoL();
                bannerInfoL.setBannerInfo(commodity.getResult().getBannerInfo());

                ActInfoList actInfoList = new ActInfoList();
                actInfoList.setActInfoList(commodity.getResult().getActInfo());

                ChannelInfoList channelInfoList = new ChannelInfoList();
                channelInfoList.setChannelInfo(commodity.getResult().getChannelInfo());

                HotInfoList hotInfoList = new HotInfoList();
                hotInfoList.setHotInfo(commodity.getResult().getHotInfo());

                RecommendInfoList recommendInfoList = new RecommendInfoList();
                recommendInfoList.setRecommendInfo(commodity.getResult().getRecommendInfo());

                SeckillInfo seckillInfo = commodity.getResult().getSeckillInfo();

                itemList.add(bannerInfoL);
                itemList.add(channelInfoList);
                itemList.add(actInfoList);
                itemList.add(seckillInfo);
                itemList.add(recommendInfoList);
                itemList.add(hotInfoList);


                homeViewPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                hasError = true;
                ex.printStackTrace();
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFinished() {
//                if (!hasError && re != null) {
//                    //成功获取数据
//                }
            }
        });
    }


    //目标项是否在最后一个可见项之后
//        private boolean mShouldScroll;
    //记录目标项位置
//        private int mToPosition;
    //目标项是否在最后一个可见项之后 private boolean mShouldScroll; //记录目标项位置 private int mToPosition;
    //滑动到指定位置
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后
            mRecyclerView.smoothScrollToPosition(position);
//                mToPosition = position;
//                mShouldScroll = true;
        }
    }
}