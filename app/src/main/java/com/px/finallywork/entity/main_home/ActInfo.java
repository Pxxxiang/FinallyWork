
package com.px.finallywork.entity.main_home;

import android.content.Context;

import com.px.finallywork.R;
import com.px.finallywork.entity.BaseBean;

import org.byteam.superadapter.SuperViewHolder;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@SuppressWarnings("unused")
public class ActInfo extends BaseBean{

    private String iconUrl;
    private String name;
    private String url;


    @Override
    public void bindHolder(SuperViewHolder holder, Context context) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.banner_view;
    }
}
