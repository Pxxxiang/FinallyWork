package com.px.finallywork.entity.main_goods;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.px.finallywork.R;
import com.px.finallywork.entity.BaseBean;
import com.px.finallywork.utils.CacheUtils;
import com.px.finallywork.utils.Constants;

import org.byteam.superadapter.SuperViewHolder;
import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsBean extends BaseBean {

    //价格
    private String coverPrice;
    //图片
    private String figure;
    //名称
    private String name;
    //产品id
    private String productId;

    private int number = 1;

    /**
     * 是否被选中
     */
    private boolean isSelected = true;

    private int va = 1;
    private Handler handler;
    private TextView textView2;
    private Float price;
    private Float pri;
    private CheckBox checkBox;


    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void bindHolder(SuperViewHolder holder, Context context) {

        final CheckBox checkBox = holder.findViewById(R.id.cb_gov);
        checkBox.setChecked(!isSelected);

        ImageView imageView = holder.findViewById(R.id.iv_gov);
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

        TextView textView = holder.findViewById(R.id.tv_desc_gov);
        textView.setText(getName());

        pri = Float.parseFloat(getCoverPrice());
        textView2 = holder.findViewById(R.id.tv_price_gov);
        price = pri *getNumber();
        textView2.setText(price +"");
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.i("priceChange","价格变化了");
                price = pri *getNumber();
                textView2.setText(price+"");
                handler.removeMessages(0);

            }
        };
        NumberAddSubView numberAddSubView = holder.findViewById(R.id.numberAddSubView);

        numberAddSubView.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
            @Override
            public void addNumber(View view, int value) {
                va = value;
                setNumber(value);
                Log.i("添加地数量: ", value + "");
                handler.sendEmptyMessage(0);
            }

            @Override
            public void subNumner(View view, int value) {
                setNumber(value);
                handler.sendEmptyMessage(0);
            }
        });
        numberAddSubView.setValue(getNumber());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    checkBox.setChecked(isSelected());
                }else {
                    checkBox.setChecked(false);
                }
            }
        });

//        goodsBean.setCoverPrice(CacheUtils.getString(getContext(),"coverPrice"));
//        goodsBean.setFigure(CacheUtils.getString(getContext(),"figure"));
//        goodsBean.setProductId(CacheUtils.getString(getContext(),"name"));
//        goodsBean.setName(CacheUtils.getString(getContext(),"productId"));
//        goodsBean.setNumber(Integer.valueOf(CacheUtils.getString(getContext(),"number")));

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shop_cart;
    }
}
