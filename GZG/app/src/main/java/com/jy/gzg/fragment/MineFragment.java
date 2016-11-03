package com.jy.gzg.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.mine.activity.OrderformActivity;


/**
 * Created by Allen on 15/12/27.
 */
public class MineFragment extends Fragment {
    private Context mContext;

    // 申明控件对象
    private LinearLayout line_orderform;// 我的订单

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_mine, container,
                false);
        initView(view);
        setViewListen();
        return view;
    }

    /**
     * 初始化各种控件
     *
     * @param view
     */
    private void initView(View view) {
        line_orderform = (LinearLayout) view.findViewById(R.id.line_orderform);
    }


    /**
     * 设置各种监听事件
     */
    private void setViewListen() {
        line_orderform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderformActivity.class);
                startActivity(intent);
            }
        });
    }
}
