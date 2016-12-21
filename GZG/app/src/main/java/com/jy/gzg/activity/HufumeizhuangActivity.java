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
import com.jy.gzg.adapter.HuFuMeiZhuangAdapter;
import com.jy.gzg.bean.HuolipintuanBean;
import com.jy.gzg.bean.ProductBean;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.widget.AppConstant;

import org.json.JSONObject;

import java.util.ArrayList;

public class HufumeizhuangActivity extends AppCompatActivity {
    private ImageView iv_return;
    private ArrayList<ProductBean> hfmzList;
    private HuFuMeiZhuangAdapter huFuMeiZhuangAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hufumeizhuang);
        initViews();
// 获取该项的编号
        Intent intent = getIntent();
        final String headermodel_id = intent.getStringExtra("headermodel_id");
        String url = AppConstant.HEADERMODEL_DETAILS +
                headermodel_id;
        // ------------------------------进行网络数据请求-----------------------------------
        RequestQueue requestQueue = Volley.newRequestQueue(HufumeizhuangActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                HuolipintuanBean huolipintuanBean = GsonUtil.parseJsonWithGson(jsonObject
                                .toString(),
                        HuolipintuanBean.class);
                ArrayList<ProductBean> xstmBeanList = huolipintuanBean.getPage().getList();
                huFuMeiZhuangAdapter = new HuFuMeiZhuangAdapter(xstmBeanList,
                        HufumeizhuangActivity
                                .this);
                mRecyclerView.setAdapter(huFuMeiZhuangAdapter);
                huFuMeiZhuangAdapter.setOnItemClickListener(new HuFuMeiZhuangAdapter
                        .OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, ProductBean data) {
                        Intent intent = new Intent(HufumeizhuangActivity.this,
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
                huFuMeiZhuangAdapter = new HuFuMeiZhuangAdapter(xstmBeanList,
                        HufumeizhuangActivity.this);
                mRecyclerView.setAdapter(huFuMeiZhuangAdapter);
            }
        });
        requestQueue.add(jsonObjectRequest);
        huFuMeiZhuangAdapter = new HuFuMeiZhuangAdapter(hfmzList, HufumeizhuangActivity
                .this);
        // 网格布局管理器，且该recycleview纵向有2个item
        GridLayoutManager gridLayoutManager = new GridLayoutManager(HufumeizhuangActivity.this,
                2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(huFuMeiZhuangAdapter);
        setViewsListen();

        setViewsListen();
    }

    /**
     * 初始化相关控件信息
     */
    private void initViews() {
        iv_return = (ImageView) findViewById(R.id.iv_return);
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
    }
}
