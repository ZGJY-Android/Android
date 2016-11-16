package com.jy.gzg.viewcontrollers.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.gzg.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/28 0028.
 */
public class CustomerService2Adapter extends RecyclerView.Adapter<CustomerService2Adapter.DemoViewHolder> {
    ArrayList<?> orderFormList;
    Context context;

    public CustomerService2Adapter(ArrayList<?> orderFormList, Context context) {
        this.orderFormList = orderFormList;
        this.context = context;
    }

    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 加载数据item的布局，生成ViewHolder返回
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customerservice2,
                parent, false);
        return new DemoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DemoViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        // 返回数据有多少条
        if (null == orderFormList) {
            return 0;
        }
        return orderFormList.size();
    }

    // 可复用的ViewHolder
    public static class DemoViewHolder extends RecyclerView.ViewHolder {
        public DemoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
