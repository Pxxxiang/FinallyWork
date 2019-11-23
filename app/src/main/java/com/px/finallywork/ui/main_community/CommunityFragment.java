package com.px.finallywork.ui.main_community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.px.finallywork.utils.ItemInterface;
import com.px.finallywork.R;

import java.util.List;

public class CommunityFragment extends Fragment {

    private CommunityViewModel communityViewModel;
    private List<ItemInterface> itemInterfaceList ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        communityViewModel =
                ViewModelProviders.of(this).get(CommunityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_community, container, false);
//        new CommunityViewPagerAdapter(getContext(),itemInterfaceList,);
//        new LinearLayoutManager(getContext());
        return root;
    }
}