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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.px.finallywork.R;
import com.px.finallywork.adapter.HomeViewPagerAdapter;
import com.px.finallywork.entity.BaseBean;
import com.px.finallywork.entity.main_goods.GoodsBean;
import com.px.finallywork.utils.ItemInterface;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private float totalPrice;
    private static Handler handler;
    private TextView textView;
    private HomeViewPagerAdapter homeViewPagerAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_cart, container, false);

        editText = view.findViewById(R.id.tv_shopcart_edit);
        cleckAll = view.findViewById(R.id.ll_check_all);
        editAll = view.findViewById(R.id.ll_delete);
        Button btn_delete = view.findViewById(R.id.btn_delete);

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

        cbAll = view.findViewById(R.id.cb_all);
        checkBox = view.findViewById(R.id.checkbox_all);
        cbAll.setChecked(isSelected);
        checkBox.setChecked(isSelected);
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbAll.isChecked()) {
                    setSelected(true);
                    cbAll.setChecked(isSelected);
                    checkBox.setChecked(isSelected);
                    for (ItemInterface it : BaseBean.goodList) {
                        GoodsBean goodsBean;
                        goodsBean = (GoodsBean) it;
                        goodsBean.setSelected(true);
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
                    }
                    homeViewPagerAdapter.notifyDataSetChanged();
                }
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    setSelected(true);
                    cbAll.setChecked(isSelected);
                    checkBox.setChecked(isSelected);
                    for (ItemInterface it : BaseBean.goodList) {
                        GoodsBean goodsBean;
                        goodsBean = (GoodsBean) it;
                        goodsBean.setSelected(true);
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
                    }
                    homeViewPagerAdapter.notifyDataSetChanged();
                }
            }
        });

//        List<ItemInterface> itemList = new ArrayList<>();
//        itemList.addAll(BaseBean.goodList);
        RecyclerView recyclerView = view.findViewById(R.id.main_cart_re);
        textView = view.findViewById(R.id.tv_shopcart_total);

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                totalPrice = 0;
                Log.i("UNCHECK_ALL", "handleMessage: "+msg.what);
                if (msg.what == QUANJU_CHECK_ADD) {
                    for (ItemInterface it : BaseBean.goodList) {
                        GoodsBean goodsBean;
                        goodsBean = (GoodsBean) it;
                        float price = Float.parseFloat(goodsBean.getCoverPrice());
                        float num = goodsBean.getNumber();
                        totalPrice += price * num;
                    }
                    handler.removeMessages(QUANJU_CHECK_ADD);
                    result = String.format("%.2f",totalPrice);
                    textView.setText("￥" + result);
                } else if (msg.what == QUANJU_CHECK_SUB) {
                    for (ItemInterface it : BaseBean.goodList) {
                        GoodsBean goodsBean;
                        goodsBean = (GoodsBean) it;
                        float price = Float.parseFloat(goodsBean.getCoverPrice());
                        float num = goodsBean.getNumber();
                        totalPrice += price * num;
                    }
                    handler.removeMessages(QUANJU_CHECK_SUB);
                    result = String.format("%.2f",totalPrice);
                    textView.setText("￥" + result);
                } else if (msg.what == UNCHECK_ALL) {
                    setSelected(true);
                    cbAll.setChecked(isSelected);
                    checkBox.setChecked(isSelected);
                    for (ItemInterface it : BaseBean.goodList) {
                        GoodsBean goodsBean;
                        goodsBean = (GoodsBean) it;
                        if (goodsBean.isSelected()!=true) {
                            setSelected(false);
                            cbAll.setChecked(isSelected);
                            checkBox.setChecked(isSelected);
                        }
                    }
                    homeViewPagerAdapter.notifyDataSetChanged();
                    handler.removeMessages(UNCHECK_ALL);
                } else {
                    Log.i("UNCHECK_ALL", "handleMessage: nothing");
                }
            }
        };

        for (ItemInterface it : BaseBean.goodList) {
            GoodsBean goodsBean;
            goodsBean = (GoodsBean) it;
            float price = Float.parseFloat(goodsBean.getCoverPrice());
            float num = goodsBean.getNumber();
            totalPrice += price * num;
        }
        Log.i("price", "onCreateView: " + totalPrice);
        result = String.format("%.2f",totalPrice);
        textView.setText("￥" + result);


        homeViewPagerAdapter = new HomeViewPagerAdapter(getContext(), BaseBean.goodList, null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeViewPagerAdapter);
        //删除事件

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListIterator<ItemInterface> itemInterfaceListIterator = BaseBean.goodList.listIterator();

                while (itemInterfaceListIterator.hasNext()){
                    ItemInterface next = itemInterfaceListIterator.next();
                    Log.i("state123", "onClick: "+((GoodsBean)next).isSelected());
                    if (((GoodsBean)next).isSelected()==true) {
                        itemInterfaceListIterator.remove();
                    }
                }
                setSelected(false);
                cbAll.setChecked(isSelected);
                checkBox.setChecked(isSelected);
                homeViewPagerAdapter.notifyDataSetChanged();
            }
        });



        homeViewPagerAdapter.notifyDataSetChanged();

        return view;
    }

    public static Handler getHandler() {
        return handler;
    }
}