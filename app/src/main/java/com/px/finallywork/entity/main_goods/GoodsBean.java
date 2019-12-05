package com.px.finallywork.entity.main_goods;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import com.px.finallywork.ui.main_cart.CartFragment;
import com.px.finallywork.utils.Constants;

import org.byteam.superadapter.SuperViewHolder;
import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.Objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsBean extends BaseBean {

    public GoodsBean(String coverPrice, String figure, String name, String productId) {
        this.coverPrice = coverPrice;
        this.figure = figure;
        this.name = name;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GoodsBean goodsBean = (GoodsBean) o;
        return productId.equals(goodsBean.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productId);
    }

    public GoodsBean() {
    }

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
    private boolean isSelected = false;
    private String result;

    public boolean isSelected() {
        return isSelected;
    }

    private Handler handler;
    private TextView textView2;
    private float price;
    private Float singleprice;
    private CheckBox checkBox;

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public static int CHECK_ALL = 0x003;
    public static int UNCHECK_ALL = 0x004;
    public static int QUANJU_CHECK_ADD = 1;
    public static int QUANJU_CHECK_SUB = 2;
    public static int ITEM_CHECK = 0;
    private Bundle bundle1;
    private Bundle bundle2;
    private Message message1;
    private Message message2;
    private Message message3;
    private Message message4;

    public void setNumber(int number) {
        if (number >= 1) {
            this.number = number;
        } else {
            this.number = 1;
        }
    }

    @Override
    public void bindHolder(SuperViewHolder holder, final Context context) {

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

        singleprice = Float.parseFloat(getCoverPrice());
        textView2 = holder.findViewById(R.id.tv_price_gov);
        price = singleprice * getNumber();
        result = String.format("%.2f", price);
        textView2.setText("￥" + result);
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == ITEM_CHECK) {
//                    Log.i("priceChange", "价格变化了");
                    price = singleprice * getNumber();
                    result = String.format("%.2f", price);
                    textView2.setText("￥" + result);
                    handler.removeMessages(ITEM_CHECK);
                }
            }
        };

        NumberAddSubView numberAddSubView = holder.findViewById(R.id.numberAddSubView);
        numberAddSubView.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
            @Override
            public void addNumber(View view, int value) {
                setNumber(value);
//                Log.i("添加地数量: ", value + "");
                message1 = new Message();
                bundle1 = new Bundle();
                bundle1.putFloat("Singleprice", getSingleprice());
                bundle1.putInt("num", getNumber());
                message1.setData(bundle1);
                message1.what = QUANJU_CHECK_ADD;
//
                handler.sendEmptyMessage(ITEM_CHECK);
                CartFragment.getHandler().sendMessage(message1);
            }

            @Override
            public void subNumner(View view, int value) {
                setNumber(value);
                message2 = new Message();
                bundle2 = new Bundle();
                bundle2.putFloat("Singleprice", getSingleprice());
                bundle2.putInt("num", getNumber());
                message2.what = QUANJU_CHECK_SUB;
                message2.setData(bundle2);
//
                handler.sendEmptyMessage(ITEM_CHECK);
                CartFragment.getHandler().sendMessage(message2);
            }
        });
        numberAddSubView.setValue(getNumber());

        checkBox = holder.findViewById(R.id.cb_gov);
        checkBox.setChecked(isSelected);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkBox.isChecked()) {
                    setSelected(true);
                    checkBox.setChecked(isSelected);
                    message3 = new Message();
                    message3.what = CHECK_ALL;
                    CartFragment.getHandler().sendMessage(message3);

                    message1 = new Message();
                    bundle1 = new Bundle();
                    bundle1.putFloat("Singleprice", getSingleprice());
                    bundle1.putInt("num", getNumber());
                    message1.setData(bundle1);
                    message1.what = QUANJU_CHECK_ADD;

                    handler.sendEmptyMessage(ITEM_CHECK);
                    CartFragment.getHandler().sendMessage(message1);
                } else {
                    setSelected(false);
                    checkBox.setChecked(isSelected);
                    message3 = new Message();
                    message3.what = UNCHECK_ALL;
                    CartFragment.getHandler().sendMessage(message3);

                    message2 = new Message();
                    bundle2 = new Bundle();
                    bundle2.putFloat("Singleprice", getSingleprice());
                    bundle2.putInt("num", getNumber());
                    message2.what = QUANJU_CHECK_SUB;
                    message2.setData(bundle2);

                    handler.sendEmptyMessage(ITEM_CHECK);
                    CartFragment.getHandler().sendMessage(message2);
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
