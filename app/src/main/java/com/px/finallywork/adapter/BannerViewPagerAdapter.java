package com.px.finallywork.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.px.finallywork.InfoActivity;
import com.px.finallywork.R;
import com.px.finallywork.utils.ItemInterface;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

public class BannerViewPagerAdapter extends SuperAdapter<ItemInterface> {

    private  List<String> url;
    private List<ItemInterface> list;

    private Context contexts;

    public BannerViewPagerAdapter(Context context, List<ItemInterface> items,List<String> obj, int layoutResId) {
        super(context, items, layoutResId);
        this.list = items;
        this.url = obj;
        this.contexts = context;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, ItemInterface item) {
        item.bindHolder(holder, getContext());
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, final int position) {

        ImageView imageView = holder.findViewById(R.id.banner_img);
        Glide.with(getContext()).load(list.get(position % list.size())).into(imageView);
        try{
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(contexts,"秒杀="+list2.get(position%list2.size()).getValue(),Toast.LENGTH_SHORT).show();
                    sendInfoToGoodActivity(contexts ,position);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    private void sendInfoToGoodActivity(Context context,int position) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra("url", url.get(position % list.size()));
        context.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

}
