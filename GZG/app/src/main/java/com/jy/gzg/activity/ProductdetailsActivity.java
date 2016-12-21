package com.jy.gzg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jy.gzg.R;
import com.jy.gzg.adapter.MyFragmentPagerAdapter;
import com.jy.gzg.bean.ProductBean;
import com.jy.gzg.bean.ProductDetailsBean;
import com.jy.gzg.fragment.ProductCommentFragment;
import com.jy.gzg.fragment.ProductDetailsFragment;
import com.jy.gzg.fragment.ProductInfoFragment;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.widget.AppConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductdetailsActivity extends AppCompatActivity {
    private String[] titles = new String[]{"商品", "详情", "评价"};
    private ArrayList<Fragment> fragments;

    // 申明控件对象
    private ImageView iv_return;
    private TabLayout tab_tabLayout;
    private ViewPager vp_viewPager;
    private ProductInfoFragment productInfoFragment;// 商品信息
    private ProductDetailsFragment productDetailsFragment;// 商品详情
    private ProductCommentFragment productCommentFragment;// 商品评价

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        initViews();
        setViewsListen();
        // 获取商品的编号
        Intent intent = getIntent();
        final String productId = intent.getStringExtra("product_id");
        fragments = new ArrayList<>();
        productInfoFragment = new ProductInfoFragment();
        productDetailsFragment = new ProductDetailsFragment();
        productCommentFragment = new ProductCommentFragment();
        String url = AppConstant.PRODUCT_DETAILS +
                productId;
        // 进行网络请求
        RequestQueue requestQueue = Volley.newRequestQueue(ProductdetailsActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                ProductDetailsBean productDetailsBean = GsonUtil.parseJsonWithGson(s,
                        ProductDetailsBean.class);
                List<ProductBean> productBeanList = productDetailsBean.getList();
                if (productBeanList.size() != 0) {
                    ProductBean xstmBean = productBeanList.get(0);
                    productInfoFragment.setXstmBean(xstmBean);
                    productDetailsFragment.setXstmBean(xstmBean);
                    productCommentFragment.setXstmBean(xstmBean);
                }
                fragments.add(productInfoFragment);
                fragments.add(productDetailsFragment);
                fragments.add(productCommentFragment);
                // TabLayout中的Tab如果没有占满屏幕的宽度。
                tab_tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                tab_tabLayout.setTabMode(TabLayout.MODE_FIXED);
                MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter
                        (getSupportFragmentManager(),
                                titles, fragments);
                vp_viewPager.setAdapter(adapter);
                tab_tabLayout.setupWithViewPager(vp_viewPager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                AppToast.getInstance().showShort("连接服务器失败");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", productId);
                return map;
            }
        };
        requestQueue.add(stringRequest);
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
