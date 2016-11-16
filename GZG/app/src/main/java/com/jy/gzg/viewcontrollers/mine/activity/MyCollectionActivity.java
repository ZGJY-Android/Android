package com.jy.gzg.viewcontrollers.mine.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.mine.adapter.MyCollectionAdapter;
import com.jy.gzg.viewcontrollers.mine.bean.MyCollectionBean;
import com.jy.gzg.viewcontrollers.mine.minterface.OnMSwipItemClickListener;
import com.jy.gzg.viewcontrollers.mine.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionActivity extends AppCompatActivity {
    private List<MyCollectionBean> collections;
    private MyCollectionAdapter adapter;
    private RecyclerView mRecyclerView;
    private ImageView iv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);
        initViews();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager
                .VERTICAL));
        initData();
        setViewsListen();
        mRecyclerView.setAdapter(adapter);

    }

    /**
     * 初始化各种控件
     */
    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        iv_return = (ImageView) findViewById(R.id.iv_return);
    }

    /**
     * 设置各种控件的监听事件
     */
    private void setViewsListen() {
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        collections = new ArrayList<>();
        MyCollectionBean myCollectionBean;
        for (int i = 0; i < 20; i++) {
            myCollectionBean = new MyCollectionBean();
            collections.add(myCollectionBean);
        }
        adapter = new MyCollectionAdapter(MyCollectionActivity.this, collections, new
                OnMSwipItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(MyCollectionActivity.this, "点击了第" + position + "个View", Toast
                                .LENGTH_SHORT).show();
                    }
                });
    }
}
