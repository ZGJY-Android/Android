package com.jy.gzg.viewcontrollers.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.swipe.SwipeMenuAdapter;
import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.mine.bean.MyCollectionBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class MyCollectionAdapter extends SwipeMenuAdapter<MyCollectionAdapter.DefaultViewHolder> {
    protected List<MyCollectionBean> mDataList = new ArrayList<>();

    public MyCollectionAdapter() {
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        DefaultViewHolder viewHolder = holder;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<MyCollectionBean> getDataList() {
        return mDataList;
    }

    public void setDataList(Collection<MyCollectionBean> list) {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(Collection<MyCollectionBean> list) {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    public void remove(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
        if (position != mDataList.size()) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, mDataList.size() - position);
        }

    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mycollection,
                parent, false);
        return view;
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder {
        public DefaultViewHolder(View itemView) {
            super(itemView);
        }
    }

}
