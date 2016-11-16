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
import com.jy.gzg.viewcontrollers.mine.adapter.EvaluateManager2Adapter;

import java.util.ArrayList;

/**
 * create an instance of this fragment.
 */
public class EvaluateManagerFragment2 extends Fragment {
    private Context mContext;
    private ArrayList<String> orderFormList;
    private RecyclerView mRecyclerView;
    private EvaluateManager2Adapter evaluateManager2Adapter;

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
        View view = inflater.inflate(R.layout.fragment_evaluatemanager2, container, false);
        orderFormList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            orderFormList.add("str_" + i);
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        evaluateManager2Adapter = new EvaluateManager2Adapter(orderFormList, mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(evaluateManager2Adapter);
        return view;
    }

}
