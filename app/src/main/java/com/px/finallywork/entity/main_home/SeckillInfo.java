
package com.px.finallywork.entity.main_home;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.px.finallywork.R;
import com.px.finallywork.adapter.HomeViewPagerAdapter;
import com.px.finallywork.entity.BaseBean;
import com.px.finallywork.utils.ItemInterface;

import org.byteam.superadapter.SuperViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@SuppressWarnings("unused")
public class SeckillInfo extends BaseBean {

    private String endTime;
    private List<MiaoShaItem> list;
    private String startTime;
    private transient TextView textView;

    private Handler handler;
    private transient long dt;


    @Override
    public void bindHolder(SuperViewHolder holder, Context context) {

        List<ItemInterface> itemList = new ArrayList<>();
        textView = holder.findViewById(R.id.sec_time);
        //textView的倒计时设置
        if (handler==null) {
            dt = Integer.parseInt(getEndTime()) - Integer.parseInt(getStartTime());
            sendHandler();
        }
        handler.sendEmptyMessageDelayed(0, 1000);



        MiaoShaItemList miaoShaItemList = new MiaoShaItemList();
        miaoShaItemList.setList(getList());
        itemList.add(miaoShaItemList);

        RecyclerView recyclerView = holder.findViewById(R.id.sec_re);

        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(context, itemList, null);
        recyclerView.setAdapter(homeViewPagerAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        homeViewPagerAdapter.notifyDataSetChanged();
    }

    private void sendHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                dt = dt - 1000;
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                String time = format.format(new Date(dt));
                textView.setText(time);
//                Log.i("handle zhi x ing", "wo zhi xin g le "+ dt);
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);
                if (dt <= 0) {
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_home_seckill;
    }
}
