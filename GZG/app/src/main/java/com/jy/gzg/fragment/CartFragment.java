package com.jy.gzg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.gzg.R;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.viewcontrollers.cart.adapter.CartRecommendAdapter;
import com.jy.gzg.widget.CustomGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Allen on 15/12/27.
 */
public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private List mDatas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        initData();
        //初始化view
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_cart, container,
                false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_cart_recommend);
        //设置布局管理器
        CustomGridLayoutManager gridLayoutManager = new CustomGridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        //设置适配器
        CartRecommendAdapter cartRecommendAdapter = new CartRecommendAdapter(getActivity(), mDatas);
        cartRecommendAdapter.setOnItemClickListener(new CartRecommendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                AppToast.getInstance().showShort("购物车推荐商品" + position);
            }
        });
        recyclerView.setAdapter(cartRecommendAdapter);
        return view;
    }

    private void initData() {
        mDatas = new ArrayList<>(Arrays.asList(R.mipmap.sy_mypic3, R.mipmap.sy_mypic3, R
                .mipmap.sy_mypic6, R.mipmap.sy_mypic3, R.mipmap.sy_mypic3, R.mipmap
                .sy_mypic6, R
                .mipmap.sy_mypic6, R.mipmap.sy_mypic3, R.mipmap.sy_mypic3, R.mipmap
                .sy_mypic6));
    }
}
