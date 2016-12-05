package com.jy.gzg.fragment;

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
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.adapter.ProductDetailsAdapter;
import com.jy.gzg.bean.ProductBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31 0031.
 */
public class ProductDetailsFragment extends Fragment {
    private Context mContext;
    private ProductBean xstmBean;
    private ArrayList<String> imgList;
    private TextView tv_text;
    private RecyclerView mRecyclerView;
    private ProductDetailsAdapter productDetailsAdapter;

    public void setXstmBean(ProductBean xstmBean) {
        this.xstmBean = xstmBean;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productdetails, container, false);
        String introduction = xstmBean.getIntroduction();// 获取详情图片的地址字符串
        if (introduction == null || introduction.equals("")) {
            tv_text = (TextView) view.findViewById(R.id.tv_text);
            tv_text.setVisibility(View.VISIBLE);
        } else {
            imgList = new ArrayList();
            String[] images = introduction.trim().split("><");
            for (int i = 0; i < images.length; i++) {
                if (images[i].contains("br")) {
                    continue;
                }
                int beginIndex = images[i].indexOf("h");
                int endIndex = images[i].lastIndexOf("g");
                imgList.add(images[i].substring(beginIndex, endIndex + 1));
            }

            mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
            productDetailsAdapter = new ProductDetailsAdapter(imgList, mContext);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(productDetailsAdapter);
        }
        return view;
    }
}
