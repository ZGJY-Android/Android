package com.jy.gzg.viewcontrollers.category.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.category.adapter.MyAdapter;

import java.util.ArrayList;

/**
 * Created by YX on 2016/10/31 0031.
 */
public class WaresItemFragment extends Fragment {
    public static final String TAG = "WaresItemFragment";
    private String str;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter myAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_category, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_fragment_category);
        //得到数据
        str = getArguments().getString(TAG);
        textView.setText(str);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_category);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(getActivity());
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.addDatas(generateData());
        setHeader(mRecyclerView);


        return view;
    }

    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.recy_wares_header, view, false);
        myAdapter.setHeaderView(header);
    }

    private ArrayList<String> generateData() {
        ArrayList<String> data = new ArrayList<String>() {
            {
                for (int i = 0; i < 20; i++) add("数据" + i);
            }
        };
        return data;
    }
}
