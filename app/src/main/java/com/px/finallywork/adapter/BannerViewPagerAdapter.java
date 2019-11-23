package com.px.finallywork.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.px.finallywork.GoodsInfoActivity;
import com.px.finallywork.R;
import com.px.finallywork.entity.main_home.BannerInfo;
import com.px.finallywork.utils.ItemInterface;

import org.byteam.superadapter.IMulItemViewType;
import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

public class BannerViewPagerAdapter extends SuperAdapter<ItemInterface> {

    private List<ItemInterface> list;
    private List<BannerInfo> list2;
    private Context contexts;

    public BannerViewPagerAdapter(Context context, List<ItemInterface> items,List<BannerInfo> obj, int layoutResId) {
        super(context, items, layoutResId);
        this.list = items;
        this.list2 = obj;
        this.contexts = context;
    }
    public BannerViewPagerAdapter(Context context, List<ItemInterface> items,int layoutResId) {
        super(context, items, layoutResId);
        this.list = items;
        this.contexts = context;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, ItemInterface item) {
        item.bindHolder(holder, getContext());
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, final int position) {

        ImageView imageView = holder.findViewById(R.id.banner_img);
        System.out.println("123456890-098765:" + position % list.size());
        Glide.with(getContext()).load(list.get(position % list.size())).into(imageView);
        try{
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(contexts,"秒杀="+list2.get(position%list2.size()).getValue(),Toast.LENGTH_SHORT).show();
                    sendGoodInfoToGoodActivity(contexts);
                }
            });
        }catch (Exception e){
            e.printStackTrace();

        }


    }
    private void sendGoodInfoToGoodActivity(Context context) {
        Intent intent = new Intent(context, GoodsInfoActivity.class);

        context.startActivity(intent);

    }


    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

}
