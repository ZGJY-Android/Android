package com.jy.gzg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jy.gzg.R;
import com.jy.gzg.adapter.HuolipintuanAdapter;
import com.jy.gzg.bean.HomeModelBean;
import com.jy.gzg.bean.ProductBean;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.widget.AppConstant;

import org.json.JSONObject;

import java.util.ArrayList;

public class HuolipintuanActivity extends AppCompatActivity {
    private ArrayList<ProductBean> xstmList;
    private HuolipintuanAdapter huolipintuanAdapter;
    private RecyclerView mRecyclerView;
    private ImageView iv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_huolipintuan);
        initViews();
        xstmList = new ArrayList();
        // 获取该项的编号
        Intent intent = getIntent();
        final String headermodel_id = intent.getStringExtra("headermodel_id");
        String url = AppConstant.HEADERMODEL_DETAILS +
                headermodel_id;
        // ------------------------------进行网络数据请求-----------------------------------
        RequestQueue requestQueue = Volley.newRequestQueue(HuolipintuanActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                HomeModelBean huolipintuanBean = GsonUtil.parseJsonWithGson(jsonObject
                                .toString(),
                        HomeModelBean.class);
                ArrayList<ProductBean> xstmBeanList = huolipintuanBean.getPage().getList();
                huolipintuanAdapter = new HuolipintuanAdapter(xstmBeanList, HuolipintuanActivity
                        .this);
                mRecyclerView.setAdapter(huolipintuanAdapter);
                huolipintuanAdapter.setOnItemClickListener(new HuolipintuanAdapter
                        .OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, ProductBean data) {
                        Intent intent = new Intent(HuolipintuanActivity.this,
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
                huolipintuanAdapter = new HuolipintuanAdapter(xstmBeanList,
                        HuolipintuanActivity.this);
                mRecyclerView.setAdapter(huolipintuanAdapter);
            }
        });
        requestQueue.add(jsonObjectRequest);
        huolipintuanAdapter = new HuolipintuanAdapter(xstmList, HuolipintuanActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HuolipintuanActivity
                .this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(huolipintuanAdapter);

        setViewsListen();
    }

    /**
     * 初始化相关控件信息
     */
    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        iv_return = (ImageView) findViewById(R.id.iv_return);
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
