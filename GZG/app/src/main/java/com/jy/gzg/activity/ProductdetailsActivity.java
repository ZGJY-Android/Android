package com.jy.gzg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jy.gzg.R;
import com.jy.gzg.adapter.MyFragmentPagerAdapter;
import com.jy.gzg.bean.ProductBean;
import com.jy.gzg.bean.ProductListBean;
import com.jy.gzg.fragment.ProductCommentFragment;
import com.jy.gzg.fragment.ProductDetailsFragment;
import com.jy.gzg.fragment.ProductInfoFragment;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.widget.AppConstant;
import com.jy.gzg.widget.ProductInfoPopupWindow;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    private Button btn_addtoform;// 添加到进货单
    public static ProductBean productBean;// 当前商品对象
    // 自定义的弹出框类
    private ProductInfoPopupWindow productInfoPopupWindow;
    public static int productCount = 1;// 选中的商品的数量，默认为1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        initViews();
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
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ProductListBean productDetailsBean = GsonUtil.parseJsonWithGson(jsonObject
                                .toString(),
                        ProductListBean.class);
                List<ProductBean> productBeanList = productDetailsBean.getList();
                if (productBeanList.size() != 0) {
                    productBean = productBeanList.get(0);
                    productInfoFragment.setXstmBean(productBean);
                    productDetailsFragment.setXstmBean(productBean);
                    productCommentFragment.setXstmBean(productBean);
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
                AppToast.getInstance().showShort("网络请求失败");
            }
        });
        requestQueue.add(jsonObjectRequest);
        setViewsListen();
    }

    /**
     * 初始化控件
     */
    public void initViews() {
        iv_return = (ImageView) findViewById(R.id.iv_return);
        tab_tabLayout = (TabLayout) findViewById(R.id.tab_tabLayout);
        vp_viewPager = (ViewPager) findViewById(R.id.vp_viewPager);
        btn_addtoform = (Button) findViewById(R.id.btn_addtoform);
        productInfoPopupWindow = new ProductInfoPopupWindow();
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

        btn_addtoform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实例化ProductInfoPopupWindow
                productInfoPopupWindow = new ProductInfoPopupWindow(ProductdetailsActivity.this,
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                productInfoPopupWindow.dismiss();
                                ProductInfoFragment.tv_count.setText(productCount + "");
                                switch (v.getId()) {
                                    case R.id.iv_productimg:
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });

                productInfoPopupWindow.setOnClickListener(new ProductInfoPopupWindow.CallBack() {
                    @Override
                    public void isYes(String result) {
                        Log.i("zyw", result);
                        ProductInfoFragment.tv_count.setText(productCount + "");
                    }
                });

                //显示窗口
                productInfoPopupWindow.showAtLocation(findViewById(R.id
                        .mScollView), Gravity
                        .BOTTOM, 0, 0); //设置layout在PopupWindow中显示的位置
            }
        });
    }
}
