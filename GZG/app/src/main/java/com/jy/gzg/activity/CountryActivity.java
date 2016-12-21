package com.jy.gzg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.adapter.CountryHorizontalAdapter;
import com.jy.gzg.adapter.CountryPagerAdapter;
import com.jy.gzg.util.AppLog;
import com.jy.gzg.util.AppToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryActivity extends AppCompatActivity {

    private static final int COUNTRY_KOREA = 1;
    private static final int COUNTRY_JAPAN = 2;
    private static final int COUNTRY_AUSTRALIA = 3;
    private static final int COUNTRY_EUROPE = 4;
    //当前国家的id
    public static int country;
    private LinearLayout linearLayout;
    private FrameLayout imgBack;
    private TextView textView;
    private ImageView imgBg;
    private RecyclerView hRecyclerView;
    private List<Integer> hData;
    private TabLayout tabLayout;
    public static int tagId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_country);

        initData();
        initView();

        Intent intent = getIntent();
        country = intent.getIntExtra("country", -1);
        tagId = intent.getIntExtra("headermodel_id", -1);
        AppLog.e("--------country--------", country);

        switch (country) {
            case COUNTRY_KOREA:
                //设置当前国家馆的背景颜色
                linearLayout.setBackgroundResource(R.color.hanguoBg);
                textView.setText("韩国");
                //设置当前国家馆的banner
                imgBg.setBackgroundResource(R.mipmap.hgg);
                //设置当前国家馆横向商品的渐变背景色
                hRecyclerView.setBackgroundResource(R.drawable.shape_jianbian_hanguo);
                break;
            case COUNTRY_JAPAN:
                linearLayout.setBackgroundResource(R.color.ribenBg);
                textView.setText("日本");
                imgBg.setBackgroundResource(R.mipmap.rbg);
                hRecyclerView.setBackgroundResource(R.drawable.shape_jianbian_riben);
                break;
            case COUNTRY_AUSTRALIA:
                linearLayout.setBackgroundResource(R.color.aozhouBg);
                textView.setText("澳洲");
                imgBg.setBackgroundResource(R.mipmap.hgg);
                hRecyclerView.setBackgroundResource(R.drawable.shape_jianbian_aozhou);
                break;
            case COUNTRY_EUROPE:
                linearLayout.setBackgroundResource(R.color.ouzhouBg);
                textView.setText("欧洲");
                imgBg.setBackgroundResource(R.mipmap.rbg);
                hRecyclerView.setBackgroundResource(R.drawable.shape_jianbian__ouzhou);
                break;
        }

    }

    private void initData() {
        //横向商品数据
        hData = new ArrayList<>(Arrays.asList(R.mipmap.xhx, R.mipmap.xhx, R.mipmap.xhx, R.mipmap
                .xhx, R.mipmap.xhx, R.mipmap.xhx));
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.linear_guojia);
        textView = (TextView) findViewById(R.id.title_guojia);
        imgBg = (ImageView) findViewById(R.id.iv_guojia);
        //返回按钮
        imgBack = (FrameLayout) findViewById(R.id.fl_guojia_left);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//-----------------------------------------国家馆横向商品---------------------------------
        hRecyclerView = (RecyclerView) findViewById(R.id.rv_country_horizontal);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        CountryHorizontalAdapter countryHorizontalAdapter = new CountryHorizontalAdapter(this,
                hData);
        countryHorizontalAdapter.setmOnItemClickListener(new CountryHorizontalAdapter
                .OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                AppToast.getInstance().showShort("横向商品" + position);
            }
        });
        hRecyclerView.setAdapter(countryHorizontalAdapter);

//-----------------------------------------国家馆TabLayout和纵向商品---------------------------------
        //Fragment+ViewPager+FragmentViewPager组合的使用
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_wares_guojia);
        CountryPagerAdapter countryPagerAdapter = new CountryPagerAdapter
                (getSupportFragmentManager(), this);
        viewPager.setAdapter(countryPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_country);
        tabLayout.setupWithViewPager(viewPager);
    }
}
