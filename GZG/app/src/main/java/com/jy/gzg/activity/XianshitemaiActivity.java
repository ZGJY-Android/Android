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
import com.jy.gzg.adapter.XianshitemaiAdapter;
import com.jy.gzg.bean.ProductBean;
import com.jy.gzg.bean.XianshitemaiBean;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.viewcontrollers.home.widget.HomeConstant;

import org.json.JSONObject;

import java.util.ArrayList;

public class XianshitemaiActivity extends AppCompatActivity {
    private ArrayList<ProductBean> xstmList;
    private XianshitemaiAdapter xianshitemaiAdapter;
    private RecyclerView mRecyclerView;
    private ImageView iv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_xianshitemai);
        initViews();
        xstmList = new ArrayList();
        // ------------------------------进行网络数据请求-----------------------------------
        RequestQueue requestQueue = Volley.newRequestQueue(XianshitemaiActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                HomeConstant.HOME_XSTM, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                XianshitemaiBean xianshitemaiBean = GsonUtil.parseJsonWithGson(jsonObject
                                .toString(),
                        XianshitemaiBean.class);
                ArrayList<ProductBean> xstmBeanList = xianshitemaiBean.getPage().getList();
                xianshitemaiAdapter = new XianshitemaiAdapter(xstmBeanList, XianshitemaiActivity
                        .this);
                mRecyclerView.setAdapter(xianshitemaiAdapter);
                xianshitemaiAdapter.setOnItemClickListener(new XianshitemaiAdapter
                        .OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, ProductBean data) {
                        Intent intent = new Intent(XianshitemaiActivity.this,
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
                xianshitemaiAdapter = new XianshitemaiAdapter(xstmBeanList,
                        XianshitemaiActivity.this);
                mRecyclerView.setAdapter(xianshitemaiAdapter);
            }
        });
        requestQueue.add(jsonObjectRequest);
        xianshitemaiAdapter = new XianshitemaiAdapter(xstmList, XianshitemaiActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(XianshitemaiActivity
                .this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(xianshitemaiAdapter);
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
