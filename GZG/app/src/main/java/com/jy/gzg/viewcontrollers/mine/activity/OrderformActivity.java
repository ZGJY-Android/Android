package com.jy.gzg.viewcontrollers.mine.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jy.gzg.R;
import com.jy.gzg.adapter.MyFragmentPagerAdapter;
import com.jy.gzg.viewcontrollers.mine.fragment.OrderformFragment1;
import com.jy.gzg.viewcontrollers.mine.fragment.OrderformFragment2;
import com.jy.gzg.viewcontrollers.mine.fragment.OrderformFragment3;
import com.jy.gzg.viewcontrollers.mine.fragment.OrderformFragment4;
import com.jy.gzg.viewcontrollers.mine.fragment.OrderformFragment5;

import java.util.ArrayList;

public class OrderformActivity extends AppCompatActivity {
    private String[] titles = new String[]{"全部", "待付款", "待发货", "待收货", "待评价"};
    private ArrayList<Fragment> fragments;

    // 申明控件对象
    private TabLayout tab_tabLayout;
    private ViewPager vp_viewPager;
    private ImageView iv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderform);
        initView();
        setViewListen();

        fragments = new ArrayList<>();
        fragments.add(new OrderformFragment1());// 全部
        fragments.add(new OrderformFragment2());// 待付款
        fragments.add(new OrderformFragment3());// 待发货
        fragments.add(new OrderformFragment4());// 待收货
        fragments.add(new OrderformFragment5());// 待评价

        // TabLayout中的Tab如果没有占满屏幕的宽度。
        tab_tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tab_tabLayout.setTabMode(TabLayout.MODE_FIXED);

        //Fragment+ViewPager+FragmentViewPager组合的使用
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                titles, fragments);
        vp_viewPager.setAdapter(adapter);
        tab_tabLayout.setupWithViewPager(vp_viewPager);
    }

    /**
     * 初始化各种控件
     */
    private void initView() {
        vp_viewPager = (ViewPager) findViewById(R.id.vp_viewPager);
        tab_tabLayout = (TabLayout) findViewById(R.id.tab_tabLayout);
        iv_return = (ImageView) findViewById(R.id.iv_return);
    }

    /**
     * 设置各种监听事件
     */
    private void setViewListen() {
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
