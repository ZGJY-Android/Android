package com.jy.gzg.viewcontrollers.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jy.gzg.R;

/**
 * Created by Administrator on 2016/10/25 0025.
 */
public class OrderformFragment5 extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orderform5, container, false);
        TextView textView = (TextView) view.findViewById(R.id.tv_str);
        textView.setText("第5页");
        return view;
    }

}
