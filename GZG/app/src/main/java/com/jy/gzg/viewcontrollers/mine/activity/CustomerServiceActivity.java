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
import com.jy.gzg.viewcontrollers.mine.fragment.CustomerserviceFragment1;
import com.jy.gzg.viewcontrollers.mine.fragment.CustomerserviceFragment2;

import java.util.ArrayList;

public class CustomerServiceActivity extends AppCompatActivity {
    private String[] titles = new String[]{"售后申请", "进度查询"};
    private ArrayList<Fragment> fragments;

    // 申明控件对象
    private TabLayout tab_tabLayout;
    private ViewPager vp_viewPager;
    private ImageView iv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerservice);
        initView();
        setViewListen();

        fragments = new ArrayList<>();
        fragments.add(new CustomerserviceFragment1());// 全部
        fragments.add(new CustomerserviceFragment2());// 待付款
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
