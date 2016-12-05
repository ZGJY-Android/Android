package com.jy.gzg.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.activity.LoginActivity;
import com.jy.gzg.viewcontrollers.mine.activity.AddressmanageActivity;
import com.jy.gzg.viewcontrollers.mine.activity.CustomerServiceActivity;
import com.jy.gzg.viewcontrollers.mine.activity.EvaluateManagerActivity;
import com.jy.gzg.viewcontrollers.mine.activity.HelpFeedbackActivity;
import com.jy.gzg.viewcontrollers.mine.activity.MyCollectionActivity;
import com.jy.gzg.viewcontrollers.mine.activity.MyCouponActivity;
import com.jy.gzg.viewcontrollers.mine.activity.OrderformActivity;
import com.jy.gzg.viewcontrollers.mine.activity.SettingActivity;


/**
 * Created by Allen on 15/12/27.
 */
public class MineFragment extends Fragment {
    private Context mContext;

    // 申明控件对象
    private LinearLayout line_orderform,// 我的订单
            line_customerservice,// 我的售后
            line_mycollection,// 我的收藏
            line_mycoupon,// 我的优惠券
            line_addressmanage,// 收获地址管理
            line_evaluatemanager,// 评价中心
            line_helpfeedback;// 帮助与反馈
    private TextView tv_nickname;// 昵称
    private ImageView iv_setting;// 设置

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
        line_customerservice = (LinearLayout) view.findViewById(R.id.line_customerservice);
        line_mycollection = (LinearLayout) view.findViewById(R.id.line_mycollection);
        line_mycoupon = (LinearLayout) view.findViewById(R.id.line_mycoupon);
        line_addressmanage = (LinearLayout) view.findViewById(R.id.line_addressmanage);
        line_evaluatemanager = (LinearLayout) view.findViewById(R.id.line_evaluatemanager);
        line_helpfeedback = (LinearLayout) view.findViewById(R.id.line_helpfeedback);

        tv_nickname = (TextView) view.findViewById(R.id.tv_nickname);
        iv_setting = (ImageView) view.findViewById(R.id.iv_setting);
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
        line_customerservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CustomerServiceActivity.class);
                startActivity(intent);
            }
        });
        line_mycollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyCollectionActivity.class);
                startActivity(intent);
            }
        });
        line_mycoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyCouponActivity.class);
                startActivity(intent);
            }
        });
        line_addressmanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddressmanageActivity.class);
                startActivity(intent);
            }
        });
        line_evaluatemanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EvaluateManagerActivity.class);
                startActivity(intent);
            }
        });
        line_helpfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HelpFeedbackActivity.class);
                startActivity(intent);
            }
        });

        tv_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
            }
        });
        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
