package com.jy.gzg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jy.gzg.R;
import com.jy.gzg.bean.MenuPopwindowBean;
import com.jy.gzg.ui.MenuPopwindow;

import java.util.ArrayList;
import java.util.List;

public class MuyinzhuanchangActivity extends AppCompatActivity {
    private ImageView iv_return, iv_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muyinzhuanchang);
        initViews();
        setViewsListen();
    }

    /**
     * 初始化相关控件信息
     */
    private void initViews() {
        iv_return = (ImageView) findViewById(R.id.iv_return);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);
    }

    /**
     * 设置控件的事件
     */
    private void setViewsListen() {
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] icons = {R.mipmap.h_pinlun, R.mipmap.h_pinlun, R.mipmap.h_pinlun, R.mipmap
                        .h_pinlun};
                String[] texts = {"消息 ", "首页 ", "搜索 ", "购物车"};
                List<MenuPopwindowBean> list = new ArrayList<>();
                MenuPopwindowBean bean = null;
                for (int i = 0; i < icons.length; i++) {
                    bean = new MenuPopwindowBean();
                    bean.setIcon(icons[i]);
                    bean.setText(texts[i]);
                    list.add(bean);
                }
                MenuPopwindow pw = new MenuPopwindow(MuyinzhuanchangActivity.this, list);
                // pw.setOnItemClick(myOnItemClickListener);
                pw.showPopupWindow(findViewById(R.id.iv_menu));//点击右上角的那个button
            }
        });
    }
}
