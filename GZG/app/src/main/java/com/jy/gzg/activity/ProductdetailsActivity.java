package com.jy.gzg.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jy.gzg.R;
import com.jy.gzg.adapter.MyFragmentPagerAdapter;
import com.jy.gzg.fragment.ProductCommentFragment;
import com.jy.gzg.fragment.ProductDetailsFragment;
import com.jy.gzg.fragment.ProductInfoFragment;

import java.util.ArrayList;

public class ProductdetailsActivity extends AppCompatActivity {
    private String[] titles = new String[]{"商品", "详情", "评价"};
    private ArrayList<Fragment> fragments;

    // 申明控件对象
    private ImageView iv_return;
    private TabLayout tab_tabLayout;
    private ViewPager vp_viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        initViews();
        setViewsListen();
        fragments = new ArrayList<>();
        fragments.add(new ProductInfoFragment());// 商品信息
        fragments.add(new ProductDetailsFragment());// 商品详情
        fragments.add(new ProductCommentFragment());// 商品评价

        // TabLayout中的Tab如果没有占满屏幕的宽度。
        tab_tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tab_tabLayout.setTabMode(TabLayout.MODE_FIXED);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                titles, fragments);
        vp_viewPager.setAdapter(adapter);
        tab_tabLayout.setupWithViewPager(vp_viewPager);
    }

    /**
     * 初始化控件
     */
    public void initViews() {
        iv_return = (ImageView) findViewById(R.id.iv_return);
        tab_tabLayout = (TabLayout) findViewById(R.id.tab_tabLayout);
        vp_viewPager = (ViewPager) findViewById(R.id.vp_viewPager);
    }

    /**
     * 设置控件的监听事件
     */
    public void setViewsListen() {
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
