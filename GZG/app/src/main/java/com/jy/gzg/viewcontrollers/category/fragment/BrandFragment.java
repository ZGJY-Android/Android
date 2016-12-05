package com.jy.gzg.viewcontrollers.category.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.category.adapter.BrandAdapter;

import java.util.ArrayList;

/**
 * Created by YX on 2016/11/1 0001.
 */
public class BrandFragment extends Fragment {
    public static final String ARGS_BRAND = "args_brand";
    private int mBrand;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private BrandAdapter brandAdapter;
    private String [] strings = new String[]{"韩国","日本","欧洲","澳大利亚"};
    private ArrayList<String> data = new ArrayList<String>(){
        {
            for (int i = 0;i<strings.length;i++)add(strings[i]);
        }
    };

    private Integer[] integers = new Integer[]{R.color.hanguoBg,R.color.ribenBg,R.color.ouzhouBg,R.color.aozhouBg};
    private ArrayList<Integer> colors = new ArrayList<Integer>(){
        {
            for (int i = 0;i<integers.length;i++)add(integers[i]);
        }
    };

    public static BrandFragment newInstance(int brand){
        Bundle bundle = new Bundle();

        bundle.putInt(ARGS_BRAND,brand);
        BrandFragment brandFragment = new BrandFragment();
        brandFragment.setArguments(bundle);
        return brandFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBrand = getArguments().getInt(ARGS_BRAND);
        Log.i("mBrand---------------",mBrand+"");
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_brand,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_brand);
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        brandAdapter = new BrandAdapter(getActivity());
        mRecyclerView.setAdapter(brandAdapter);
        brandAdapter.addDatas(data,colors);
        return view;
    }
}
