
package com.px.finallywork.entity.main_home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

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
public class ChannelInfo extends BaseBean {

    private String channelName;
    private String image;
    private long option;
    private long type;
    private Value value;

    @Override
    public void bindHolder(SuperViewHolder holder, Context context) {

        TextView textView = holder.findViewById(R.id.channe_tx);
        textView.setText(getChannelName());

        ImageView imageView = holder.findViewById(R.id.channe_img);
        ImageOptions options = new ImageOptions.Builder().setFadeIn(true).build();
        x.image().bind(imageView, Constants.BASE_URl_IMAGE + getImage(), options, new Callback.CacheCallback<Drawable>() {
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
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_home_channe_info;
    }
}
