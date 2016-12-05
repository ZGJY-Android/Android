package com.jy.gzg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.gzg.R;
import com.jy.gzg.adapter.CountryVerticalAdapter;
import com.jy.gzg.util.AppToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YX on 2016/11/21 0021.
 */
public class CountryFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    private RecyclerView vRecyclerView;
    protected List mDatas;

    public static CountryFragment newInstance(int page, ArrayList data) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        args.putIntegerArrayList("data",data);
        CountryFragment fragment = new CountryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
        //获取商品数据
        mDatas = getArguments().getIntegerArrayList("data");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //初始化view
        View view = inflater.inflate(R.layout.viewpager_country, container, false);
        vRecyclerView = (RecyclerView) view.findViewById(R.id.rv_country_vertical);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        vRecyclerView.setLayoutManager(gridLayoutManager);
        //设置适配器
        CountryVerticalAdapter countryVerticalAdapter = new CountryVerticalAdapter(getActivity(), mDatas);
//        //设置数据
//        countryVerticalAdapter.initData(mDatas);
        countryVerticalAdapter.setOnItemClickListener(new CountryVerticalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                AppToast.getInstance().showShort("纵向商品" + position);
            }
        });
        vRecyclerView.setAdapter(countryVerticalAdapter);
        return view;
    }
}
