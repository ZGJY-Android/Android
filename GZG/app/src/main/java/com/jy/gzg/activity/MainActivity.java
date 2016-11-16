package com.jy.gzg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jy.gzg.R;
import com.jy.gzg.fragment.CartFragment;
import com.jy.gzg.fragment.CategoryFragment;
import com.jy.gzg.fragment.HomeFragment;
import com.jy.gzg.fragment.MineFragment;
import com.jy.gzg.fragment.Tab;
import com.jy.gzg.widget.FragmentTabHost;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost tabHost;
    private LayoutInflater inflater;
    private List<Tab> mTabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        //WindowUtil.setStatusBarTint(this, Color.parseColor("#f00"));

        initTab();



//        test();

    }

    private void test() {
        String url = "http://192.168.0.110:8080/product/list/2";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        /*JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.e("111111111111111", jsonArray.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("2222222222222222", volleyError.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("type","2");
                return map;
            }
        };*/

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("111111111111111", jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("2222222222222222", volleyError.toString());
            }
        });

        /*StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("----------------------", s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("_____________________", volleyError.toString());
            }

        });*/

        requestQueue.add(jsonObjectRequest);


    }

    private void initTab() {
        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.contentLayout);
        tabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);

        tabHost.setCurrentTab(0);
        inflater = LayoutInflater.from(this);

        Tab tab_home = new Tab(R.string.home, R.drawable.selector_icon_home, HomeFragment.class);
        Tab tab_category = new Tab(R.string.category, R.drawable.selector_icon_category,
                CategoryFragment.class);
        Tab tab_cart = new Tab(R.string.cart, R.drawable.selector_icon_cart, CartFragment.class);
        Tab tab_mine = new Tab(R.string.mine, R.drawable.selector_icon_mine, MineFragment.class);

        mTabs.add(tab_home);
        mTabs.add(tab_category);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);

        for (Tab tab : mTabs) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            tabHost.addTab(tabSpec, tab.getFragment(), null);
        }

    }

    private View buildIndicator(Tab tab) {
        View view = inflater.inflate(R.layout.footer_tabs, null);
        ImageView icon = (ImageView) view.findViewById(R.id.tab_icon);
        TextView title = (TextView) view.findViewById(R.id.tab_title);
        icon.setBackgroundResource(tab.getIcon());
        title.setText(tab.getTitle());
        return view;
    }


}
