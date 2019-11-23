
package com.px.finallywork.entity.main_home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
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
public class BannerInfo extends BaseBean {

    private String image;
    private long option;
    private long type;
    private Value value;



    @Override
    public void bindHolder(SuperViewHolder holder, final Context context) {
        ImageView imageView = holder.findViewById(R.id.banner_img);
        ImageOptions options = new ImageOptions.Builder().setFadeIn(true).build();
        x.image().bind(imageView, Constants.BASE_URl_IMAGE+getImage(), options, new Callback.CacheCallback<Drawable>() {
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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"value"+getValue(),Toast.LENGTH_SHORT).show();
                sendGoodInfoToGoodActivity(context);
            }
        });

    }
    private void sendGoodInfoToGoodActivity(Context context) {
        Intent intent = new Intent(context, GoodsInfoActivity.class);

        context.startActivity(intent);

    }


    @Override
    public int getLayoutId() {
        return  R.layout.banner_view;
    }
}
