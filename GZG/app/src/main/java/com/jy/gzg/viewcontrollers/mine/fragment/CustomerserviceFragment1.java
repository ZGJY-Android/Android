package com.jy.gzg.viewcontrollers.mine.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.mine.adapter.CustomerService1Adapter;

import java.util.ArrayList;

/**
 * create an instance of this fragment.
 */
public class CustomerserviceFragment1 extends Fragment {
    private Context mContext;
    private ArrayList<String> orderFormList;
    private RecyclerView mRecyclerView;
    private CustomerService1Adapter customerService1Adapter1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

       @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customerservice1, container, false);
        orderFormList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            orderFormList.add("str_" + i);
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        customerService1Adapter1 = new CustomerService1Adapter(orderFormList, mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(customerService1Adapter1);
        return view;
    }

}
