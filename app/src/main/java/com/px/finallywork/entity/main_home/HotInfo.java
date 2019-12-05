
package com.px.finallywork.entity.main_home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.px.finallywork.GoodsInfoActivity;
import com.px.finallywork.R;
import com.px.finallywork.entity.BaseBean;
import com.px.finallywork.utils.Constants;

import org.byteam.superadapter.SuperViewHolder;
import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@SuppressWarnings("unused")
public class HotInfo extends BaseBean {

    private String coverPrice;
    private String figure;
    private String name;
    private String productId;

    private transient Context contexts;

    @Override
    public void bindHolder(SuperViewHolder holder, Context context) {
        TextView textView = holder.findViewById(R.id.hot_title);
        TextView textView2 = holder.findViewById(R.id.hot_price);

        this.contexts = context;


        textView.setText(getName());
        textView2.setText("￥" + getCoverPrice());

        ImageView imageView = holder.findViewById(R.id.hot_img);
        ImageOptions options = new ImageOptions.Builder().setFadeIn(true).build();
        x.image().bind(imageView, Constants.BASE_URl_IMAGE + getFigure(), options, new Callback.CacheCallback<Drawable>() {
            @Override
            public boolean onCache(Drawable result) {
                return true;
            }

            @Override
            public void onSuccess(Drawable result) {
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
        holder.setOnClickListener(R.id.hot_info_item, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(contexts, "秒杀=" + getProductId(), Toast.LENGTH_SHORT).show();
                sendGoodInfoToGoodActivity(contexts);
            }
        });
    }

    private void sendGoodInfoToGoodActivity(Context context) {
        Intent intent = new Intent(context, GoodsInfoActivity.class);
        intent.putExtra("coverPrice", getCoverPrice());
        intent.putExtra("figure", getFigure());
        intent.putExtra("name", getName());
        intent.putExtra("productId", getProductId());
        context.startActivity(intent);

    }

    @Override
    public int getLayoutId() {
        return R.layout.main_home_hot_info;
    }
}
