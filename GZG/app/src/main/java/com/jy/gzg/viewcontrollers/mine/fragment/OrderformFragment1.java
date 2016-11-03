package com.jy.gzg.viewcontrollers.mine.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.mine.adapter.OrderForm1Adapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/25 0025.
 */
public class OrderformFragment1 extends Fragment {
    private Context mContext;
    private ArrayList<String> orderFormList;
    private RecyclerView mRecyclerView;
    private OrderForm1Adapter orderForm1Adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orderform1, container, false);
        orderFormList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            orderFormList.add("str_" + i);
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        orderForm1Adapter = new OrderForm1Adapter(orderFormList, mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(orderForm1Adapter);
        return view;
    }

}
