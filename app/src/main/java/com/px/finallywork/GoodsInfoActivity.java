package com.px.finallywork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.px.finallywork.entity.BaseBean;
import com.px.finallywork.entity.main_goods.GoodsBean;
import com.px.finallywork.entity.main_goods.NumberAddSubView;
import com.px.finallywork.utils.Constants;
import com.px.finallywork.utils.VirtualkeyboardHeight;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import static com.px.finallywork.R.id.iv_good_info_image;


public class GoodsInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private Button btn_more;
    private GoodsBean goodsBean;
    private LinearLayout ll_root;

    private int va = 1;


    private void findViews() {
        tv_more_share = findViewById(R.id.tv_more_share);
        tv_more_search = findViewById(R.id.tv_more_search);
        tv_more_home = findViewById(R.id.tv_more_home);
        btn_more = findViewById(R.id.btn_more);
        ibGoodInfoBack = findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = findViewById(iv_good_info_image);
        tvGoodInfoName = findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = findViewById(R.id.tv_good_info_store);
        tvGoodInfoStyle = findViewById(R.id.tv_good_info_style);
        wbGoodInfoMore = findViewById(R.id.wb_good_info_more);
        llGoodsRoot = findViewById(R.id.ll_goods_root);
        ll_root = findViewById(R.id.ll_root);


        tvGoodInfoCallcenter = findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = findViewById(R.id.btn_good_info_addcart);

        ibGoodInfoBack.setOnClickListener(this);
        ibGoodInfoMore.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);

        //设置点击事件
        tv_more_share.setOnClickListener(this);
        tv_more_search.setOnClickListener(this);
        tv_more_home.setOnClickListener(this);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);

        goodsBean = new GoodsBean();

        findViews();

        getData();

        setData();
    }

    /**
     * 获取Intent中的数据
     * private String coverPrice;
     * private String figure;
     * private String name;
     * private String productId;
     */
    private void getData() {
        Intent intent = this.getIntent();
        goodsBean.setCoverPrice(intent.getExtras().getString("coverPrice"));
        goodsBean.setFigure(intent.getExtras().getString("figure"));
        goodsBean.setName(intent.getExtras().getString("name"));
        goodsBean.setProductId(intent.getExtras().getString("productId"));

    }

    private void setData() {
        ImageOptions options = new ImageOptions.Builder().setFadeIn(true).build();
        x.image().bind(ivGoodInfoImage, Constants.BASE_URl_IMAGE + goodsBean.getFigure(), options);
//        ivGoodInfoImage
        tvGoodInfoName.setText(goodsBean.getName());
        tvGoodInfoPrice.setText("￥" + goodsBean.getCoverPrice());

        setWebViewData(goodsBean.getProductId());
    }

    @Override
    public void onClick(View v) {
        if (v == ibGoodInfoBack) {

            // Handle clicks for ibGoodInfoBack
            finish();
        } else if (v == ibGoodInfoMore) {
            // Handle clicks for ibGoodInfoMore
            if (ll_root.getVisibility() == View.VISIBLE) {
                ll_root.setVisibility(View.GONE);
            } else {
                ll_root.setVisibility(View.VISIBLE);
            }
            Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
        } else if (v == btnGoodInfoAddcart) {
            // Handle clicks for btnGoodInfoAddcart
//            GoodsBean goodsBean = new GoodsBean();
//            sendData();
            showPopwindow();

//            Toast.makeText(this, "添加到成功了"+BaseBean.goodList.size(), Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCallcenter) {
            Toast.makeText(this, "客户中心", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCollection) {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCart) {
            Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_share) {
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_search) {
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_home) {
            Toast.makeText(this, "主页面", Toast.LENGTH_SHORT).show();
        }
    }


    private void setWebViewData(String product_id) {
        if (product_id != null) {
            wbGoodInfoMore.loadUrl("http://www.atguigu.com");
            //设置支持JavaScript
            WebSettings webSettings = wbGoodInfoMore.getSettings();
            webSettings.setUseWideViewPort(true);//支持双击页面变大变小
            webSettings.setJavaScriptEnabled(true);//设置支持JavaScript
            //优先使用缓存
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            wbGoodInfoMore.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });
        }
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    /**
     * 显示popupWindow
     */
    private void showPopwindow() {

        // 1 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_add_product, null);

        // 2下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 3 参数设置
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
//        window.setBackgroundDrawable(dw);
        backgroundAlpha(100);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);


        // 4 控件处理
        ImageView iv_goodinfo_photo = view.findViewById(R.id.iv_goodinfo_photo);
        TextView tv_goodinfo_name = view.findViewById(R.id.tv_goodinfo_name);
        TextView tv_goodinfo_price = view.findViewById(R.id.tv_goodinfo_price);
        NumberAddSubView nas_goodinfo_num = view.findViewById(R.id.nas_goodinfo_num);
        Button bt_goodinfo_cancel = view.findViewById(R.id.bt_goodinfo_cancel);
        Button bt_goodinfo_confim = view.findViewById(R.id.bt_goodinfo_confim);

        // 加载图片
        Glide.with(GoodsInfoActivity.this).load(Constants.BASE_URl_IMAGE + goodsBean.getFigure()).into(iv_goodinfo_photo);

        // 名称
        tv_goodinfo_name.setText(goodsBean.getName());
        // 显示价格
        tv_goodinfo_price.setText(goodsBean.getCoverPrice());

        // 设置最大值和当前值
        nas_goodinfo_num.setMaxValue(8);
        nas_goodinfo_num.setValue(1);

        nas_goodinfo_num.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
            @Override
            public void addNumber(View view, int value) {
                va = value;
//                goodsBean.setNumber(value);
                Log.i("添加地数量: ", value + "");
            }

            @Override
            public void subNumner(View view, int value) {
//                goodsBean.setNumber(value);
                va = value;
            }
        });

        bt_goodinfo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        bt_goodinfo_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                //添加购物车

                String id = goodsBean.getProductId();

                if (BaseBean.goodList.isEmpty() && BaseBean.pid.isEmpty()) {
                    goodsBean.setNumber(va);
                    BaseBean.goodList.add(goodsBean);
                    BaseBean.pid.add(goodsBean.getProductId());
                    BaseBean.num.add(va);
//                    System.out.println("为空 我加了一个");
                    Log.i("123123123", "为空 我加了一个");
                } else {
                    if (BaseBean.pid.contains(id)) {//添加已经存在的
                        for (int i = 0; i < BaseBean.goodList.size(); i++) {
                            if (BaseBean.pid.get(i).equals(id)) {
                                GoodsBean g = (GoodsBean) BaseBean.goodList.get(i);
                                g.setNumber(va+g.getNumber());
                                Log.i("123123123", "有:" + g.getNumber());
                                BaseBean.goodList.set(i, g);
                                BaseBean.num.set(i, g.getNumber());
                                Log.i("123123123", "我修改了第:" + i);
                                Log.i("123123123", "一共有几个:" + BaseBean.num.get(i)+"===");
                            }
                        }
                    } else {//添加不存在的
                        goodsBean.setNumber(va);
                        BaseBean.goodList.add(goodsBean);
                        BaseBean.pid.add(goodsBean.getProductId());
                        BaseBean.num.add(va);
//                            System.out.println("不为空 我添加了"+j);
                        Log.i("123123123", "不为空 我添加了新的:");
                    }
                }
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                window.dismiss();
                backgroundAlpha(1f);
            }
        });

        // 5 在底部显示
        window.showAtLocation(GoodsInfoActivity.this.findViewById(R.id.ll_goods_root),
                Gravity.BOTTOM, 0, VirtualkeyboardHeight.getBottomStatusHeight(GoodsInfoActivity.this));

    }
}
