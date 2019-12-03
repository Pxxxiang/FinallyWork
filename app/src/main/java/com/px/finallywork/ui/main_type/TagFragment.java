package com.px.finallywork.ui.main_type;


import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.px.finallywork.R;
import com.px.finallywork.adapter.TagGridViewAdapter;
import com.px.finallywork.entity.main_type.TagBean;
import com.px.finallywork.utils.Constants;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TagFragment extends BaseFragment {

    private GridView gv_tag;
    private TagGridViewAdapter adapter;
    private List<TagBean.ResultBean> tagResult;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_tag, null);
        gv_tag = view.findViewById(R.id.gv_tag);

        return view;
    }

    @Override
    public void initData() {
        getDataFromNet();

    }


    public void getDataFromNet() {

        String url = Constants.TAG_URL;
        RequestParams params = new RequestParams(url);

        x.http().get(params, new Callback.CacheCallback<String>() {

            private String re = null;

            @Override
            public void onSuccess(String result) {
                if (result == null) {
                    result = this.re;
                }

                processData(result);

                adapter = new TagGridViewAdapter(mContext, tagResult);
                gv_tag.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                this.re = result;
                return true;
            }
        });

    }

    private void processData(String json) {
        Gson gson = new Gson();
        TagBean tagBean = gson.fromJson(json, TagBean.class);
        tagResult = tagBean.getResult();
    }

}
