package com.jy.gzg.viewcontrollers.category.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.util.AppToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by YX on 2016/11/10 0010.
 */
public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<Integer> mDatas = new ArrayList<>();
    private ArrayList<String> mText = new ArrayList<>();

    public BrandAdapter(Context Context) {
        mLayoutInflater = LayoutInflater.from(Context);
        this.mContext = Context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView mRecyclerView;
        private List<Integer> mDatas;
        private BrandReclassifyAdapter mAdapter;

        public ViewHolder(View itemView) {
            super(itemView);

            mDatas = new ArrayList<>(Arrays.asList(R.mipmap.adis, R.mipmap.coves, R.mipmap.erocs, R.mipmap.nike, R.mipmap.sy_xspic3, R.mipmap.sy_xspic4));

            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.rv_reclassify_brand);
            //设置布局管理器
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
//            gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(gridLayoutManager);

            mAdapter = new BrandReclassifyAdapter(mContext,mDatas);
            mAdapter.setOnItemClickListener(new BrandReclassifyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    AppToast.getInstance().showShort("品牌商品" + position);
                }
            });

            mRecyclerView.setAdapter(mAdapter);

        }

        RecyclerView mRecycler;
        TextView mTxt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.reclassify_brand, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mRecycler = (RecyclerView) view.findViewById(R.id.rv_reclassify_brand);
        viewHolder.mTxt = (TextView) view.findViewById(R.id.tv_reclassify_brand);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mTxt.setText(mText.get(i));
        viewHolder.mRecycler.setBackgroundResource(mDatas.get(i));

    }

    @Override
    public int getItemCount() {
        return mText.size();
    }

    public void addDatas(ArrayList<String> datas,ArrayList<Integer> integers) {
        mText.addAll(datas);
        mDatas.addAll(integers);
        notifyDataSetChanged();
    }


}
