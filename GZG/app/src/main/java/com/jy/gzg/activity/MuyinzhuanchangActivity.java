package com.jy.gzg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jy.gzg.R;
import com.jy.gzg.adapter.MuYingZhuanChangAdapter;
import com.jy.gzg.bean.HomeModelBean;
import com.jy.gzg.bean.MenuPopwindowBean;
import com.jy.gzg.bean.ProductBean;
import com.jy.gzg.ui.MenuPopwindow;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.widget.AppConstant;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MuyinzhuanchangActivity extends AppCompatActivity {
    private ImageView iv_return, iv_menu;
    private ArrayList<ProductBean> myzxList;
    private MuYingZhuanChangAdapter muYingZhuanChangAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muyinzhuanchang);
        initViews();
        myzxList = new ArrayList();
        // 获取该项的编号
        Intent intent = getIntent();
        final String headermodel_id = intent.getStringExtra("headermodel_id");
        String url = AppConstant.HEADERMODEL_DETAILS +
                headermodel_id;
        // ------------------------------进行网络数据请求-----------------------------------
        RequestQueue requestQueue = Volley.newRequestQueue(MuyinzhuanchangActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                HomeModelBean huolipintuanBean = GsonUtil.parseJsonWithGson(jsonObject
                                .toString(),
                        HomeModelBean.class);
                ArrayList<ProductBean> xstmBeanList = huolipintuanBean.getPage().getList();
                muYingZhuanChangAdapter = new MuYingZhuanChangAdapter(xstmBeanList,
                        MuyinzhuanchangActivity
                                .this);
                mRecyclerView.setAdapter(muYingZhuanChangAdapter);
                muYingZhuanChangAdapter.setOnItemClickListener(new MuYingZhuanChangAdapter
                        .OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, ProductBean data) {
                        Intent intent = new Intent(MuyinzhuanchangActivity.this,
                                ProductdetailsActivity
                                        .class);
                        intent.putExtra("product_id", data.getId() + "");
                        startActivity(intent);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("zyw", volleyError.toString());
                ArrayList<ProductBean> xstmBeanList = new ArrayList<>();
                muYingZhuanChangAdapter = new MuYingZhuanChangAdapter(xstmBeanList,
                        MuyinzhuanchangActivity.this);
                mRecyclerView.setAdapter(muYingZhuanChangAdapter);
            }
        });
        requestQueue.add(jsonObjectRequest);
        muYingZhuanChangAdapter = new MuYingZhuanChangAdapter(myzxList, MuyinzhuanchangActivity
                .this);
        // 网格布局管理器，且该recycleview纵向有2个item
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MuyinzhuanchangActivity.this,
                2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(muYingZhuanChangAdapter);
        setViewsListen();
    }

    /**
     * 初始化相关控件信息
     */
    private void initViews() {
        iv_return = (ImageView) findViewById(R.id.iv_return);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
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
