package com.px.finallywork.ui.main_type;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.px.finallywork.R;

public class TypeFragment extends Fragment {

    private TypeViewModel typeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        typeViewModel =
                ViewModelProviders.of(this).get(TypeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_type, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        typeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}