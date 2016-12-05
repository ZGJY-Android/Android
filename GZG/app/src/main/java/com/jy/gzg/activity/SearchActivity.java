package com.jy.gzg.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.gzg.R;
import com.jy.gzg.ui.XCFlowLayout;

public class SearchActivity extends AppCompatActivity {
    /**
     * 显示的文字
     */
    private String[] mDatas = new String[]{"QQ",
            "视频放开那三国",
            "电子书-酒店-单机",
            "小说-斗地主-优酷网",
            "网游",
            "WIFI万能钥匙",
            "播放器",
            "游戏捕鱼达人2",
            "机票",
            "刀塔传奇",
            "壁纸",
            "节奏大师",
            "锁屏",
            "装机必备",
            "天天动听",
            "备份",
            "网盘",
            "海淘网",
            "大众点评",
            "爱奇艺视频",
            "腾讯手机管家",
            "百度地图",
            "猎豹清理大师",
            "谷歌地图",
            "hao123上网导航",
            "京东",
            "有你",
            "万年历-农历黄历",
            "支付宝钱包"};


    private XCFlowLayout mFlowLayout;
    private ImageView iv_search;
    private TextView tv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initChildViews();
    }

    private void initChildViews() {
        mFlowLayout = (XCFlowLayout) findViewById(R.id.xcflow_layout);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        tv_return = (TextView) findViewById(R.id.tv_return);

        MarginLayoutParams lp = new MarginLayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 15;
        lp.rightMargin = 15;
        lp.topMargin = 15;
        lp.bottomMargin = 15;

        for (int i = 0; i < mDatas.length; i++) {
            final TextView view = new TextView(this);
            view.setText(mDatas[i]);
            view.setTextSize(12);
            view.setTextColor(Color.parseColor("#111111"));
            view.setBackgroundResource(R.drawable.shape_search_bck);
            mFlowLayout.addView(view, lp);

            // 设置点击事件
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SearchActivity.this, view.getText().toString(), Toast
                            .LENGTH_SHORT).show();
                }
            });
        }

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                startActivity(intent);
            }
        });

        tv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
