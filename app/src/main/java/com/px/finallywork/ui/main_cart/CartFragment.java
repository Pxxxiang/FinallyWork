package com.px.finallywork.ui.main_cart;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.px.finallywork.R;
import com.px.finallywork.adapter.HomeViewPagerAdapter;
import com.px.finallywork.entity.BaseBean;
import com.px.finallywork.entity.main_goods.GoodsBean;
import com.px.finallywork.utils.ItemInterface;

import java.util.ListIterator;

import static com.px.finallywork.entity.main_goods.GoodsBean.CHECK_ALL;
import static com.px.finallywork.entity.main_goods.GoodsBean.QUANJU_CHECK_ADD;
import static com.px.finallywork.entity.main_goods.GoodsBean.QUANJU_CHECK_SUB;
import static com.px.finallywork.entity.main_goods.GoodsBean.UNCHECK_ALL;

public class CartFragment extends Fragment {

    private TextView editText;
    private LinearLayout cleckAll;
    private LinearLayout editAll;
    private CheckBox checkBox;
    private CheckBox cbAll;

    private boolean isSelected = false;
    private String result;
    private Button btn_delete;

    private void setSelected(boolean selected) {
        isSelected = selected;
    }

    private static float totalPrice;
    private static Handler handler;
    private TextView textView;
    private HomeViewPagerAdapter homeViewPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPrice();
    }

    @Override
    public void onResume() {
        super.onResume();
        Message message3 = new Message();
        message3.what = UNCHECK_ALL;
        CartFragment.getHandler().sendMessage(message3);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_cart, container, false);

        settingOrFinish(view);

        cbAll = view.findViewById(R.id.cb_all);
        checkBox = view.findViewById(R.id.checkbox_all);

        cbAll.setChecked(isSelected);
        checkBox.setChecked(isSelected);
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalPrice=0;
                if (cbAll.isChecked()) {
                    setSelected(true);
                    cbAll.setChecked(isSelected);
                    checkBox.setChecked(isSelected);
                    for (ItemInterface it : BaseBean.goodList) {
                        GoodsBean goodsBean;
                        goodsBean = (GoodsBean) it;
                        goodsBean.setSelected(true);
                        float price = Float.parseFloat(goodsBean.getCoverPrice());
                        float num = goodsBean.getNumber();
                        Log.i("totalpppp", "onClick: total"+totalPrice);
                        totalPrice += price * num;
                        result = String.format("%.2f", totalPrice);
                        textView.setText("￥" + result);
                    }
                    homeViewPagerAdapter.notifyDataSetChanged();
                } else {
                    setSelected(false);
                    cbAll.setChecked(isSelected);
                    checkBox.setChecked(isSelected);
                    for (ItemInterface it : BaseBean.goodList) {
                        GoodsBean goodsBean;
                        goodsBean = (GoodsBean) it;
                        goodsBean.setSelected(false);

                        totalPrice = 0;
                        result = String.format("%.2f", totalPrice);
                        textView.setText("￥" + result);
                    }
                    homeViewPagerAdapter.notifyDataSetChanged();
                }
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalPrice=0;
                if (checkBox.isChecked()) {
                    setSelected(true);
                    cbAll.setChecked(isSelected);
                    checkBox.setChecked(isSelected);
                    for (ItemInterface it : BaseBean.goodList) {
                        GoodsBean goodsBean;
                        goodsBean = (GoodsBean) it;
                        goodsBean.setSelected(true);
                        float price = Float.parseFloat(goodsBean.getCoverPrice());
                        float num = goodsBean.getNumber();
                        totalPrice += price * num;
                        result = String.format("%.2f", totalPrice);
                        textView.setText("￥" + result);
                    }
                    homeViewPagerAdapter.notifyDataSetChanged();
                } else {
                    setSelected(false);
                    cbAll.setChecked(isSelected);
                    checkBox.setChecked(isSelected);
                    for (ItemInterface it : BaseBean.goodList) {
                        GoodsBean goodsBean;
                        goodsBean = (GoodsBean) it;
                        goodsBean.setSelected(false);
                        totalPrice = 0;
                        result = String.format("%.2f", totalPrice);
                        textView.setText("￥" + result);
                    }
                    homeViewPagerAdapter.notifyDataSetChanged();
                }
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.main_cart_re);
        textView = view.findViewById(R.id.tv_shopcart_total);
        textView.setText("￥" + result);

        if (handler == null) {
            newHandler();
        }

        homeViewPagerAdapter = new HomeViewPagerAdapter(getContext(), BaseBean.goodList, null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeViewPagerAdapter);
        //删除事件
        deleteChose(view);

        homeViewPagerAdapter.notifyDataSetChanged();

        return view;
    }

    private void deleteChose(View view) {
        btn_delete = view.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListIterator<ItemInterface> itemInterfaceListIterator = BaseBean.goodList.listIterator();

                while (itemInterfaceListIterator.hasNext()) {
                    ItemInterface next = itemInterfaceListIterator.next();
                    Log.i("state123", "onClick: " + ((GoodsBean) next).isSelected());
                    if (((GoodsBean) next).isSelected()) {
                        itemInterfaceListIterator.remove();
                    }
                }
                setSelected(false);
                cbAll.setChecked(isSelected);
                checkBox.setChecked(isSelected);
                homeViewPagerAdapter.notifyDataSetChanged();
            }
        });
    }


    private void settingOrFinish(View view) {
        editText = view.findViewById(R.id.tv_shopcart_edit);
        cleckAll = view.findViewById(R.id.ll_check_all);
        editAll = view.findViewById(R.id.ll_delete);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().equals("编辑")) {
                    editText.setText("完成");
                    editAll.setVisibility(View.VISIBLE);
                    cleckAll.setVisibility(View.GONE);
                } else {
                    editText.setText("编辑");
                    editAll.setVisibility(View.GONE);
                    cleckAll.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initPrice() {
        totalPrice = 0;
        for (ItemInterface it : BaseBean.goodList) {
            GoodsBean goodsBean;
            goodsBean = (GoodsBean) it;
            if (goodsBean.isSelected()) {
                float price = Float.parseFloat(goodsBean.getCoverPrice());
                float num = goodsBean.getNumber();
                totalPrice += price * num;
            }
        }
        result = String.format("%.2f", totalPrice);
    }

    private void newHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.i("UNCHECK_ALL", "handleMessage: " + msg.what);
                if (msg.what == QUANJU_CHECK_ADD) {
                    float price = msg.getData().getFloat("Singleprice");
                    float num = msg.getData().getInt("num");
                    totalPrice += price * num;

                    handler.removeMessages(QUANJU_CHECK_ADD);
                    result = String.format("%.2f", totalPrice);
                    textView.setText("￥" + result);
                } else if (msg.what == QUANJU_CHECK_SUB) {
                    float price = msg.getData().getFloat("Singleprice");
                    float num = msg.getData().getInt("num");
                    totalPrice -= price * num;
                    if (totalPrice <= 0) {
                        totalPrice = 0;
                    }
                    Log.i("QUANJU_CHECK", "handleMessage:QUANJU_CHECK_SUB price" + totalPrice);

                    handler.removeMessages(QUANJU_CHECK_SUB);
                    result = String.format("%.2f", totalPrice);
                    textView.setText("￥" + result);
                } else if (msg.what == CHECK_ALL) {
                    setSelected(true);
                    if (BaseBean.goodList.isEmpty()) {
                        setSelected(false);
                    }
                    for (ItemInterface it : BaseBean.goodList) {
                        GoodsBean goodsBean;
                        goodsBean = (GoodsBean) it;
                        if (!goodsBean.isSelected()) {
                            setSelected(false);
                        }
                    }
                    cbAll.setChecked(isSelected);
                    checkBox.setChecked(isSelected);
                    homeViewPagerAdapter.notifyDataSetChanged();
                    handler.removeMessages(CHECK_ALL);
                } else {
                    setSelected(true);
                    if (BaseBean.goodList.isEmpty()) {
                        setSelected(false);
                    }
                    cbAll.setChecked(isSelected);
                    checkBox.setChecked(isSelected);
                    for (ItemInterface it : BaseBean.goodList) {
                        GoodsBean goodsBean;
                        goodsBean = (GoodsBean) it;
                        if (!goodsBean.isSelected()) {
                            setSelected(false);
                            cbAll.setChecked(isSelected);
                            checkBox.setChecked(isSelected);
                        }
                    }
                    homeViewPagerAdapter.notifyDataSetChanged();
                    handler.removeMessages(UNCHECK_ALL);
                }
            }
        };
    }

    public static Handler getHandler() {
        return handler;
    }
}