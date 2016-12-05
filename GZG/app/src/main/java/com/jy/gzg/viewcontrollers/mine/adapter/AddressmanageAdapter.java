package com.jy.gzg.viewcontrollers.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.swipe.SwipeMenuAdapter;
import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.home.bean.ItemModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class AddressmanageAdapter extends SwipeMenuAdapter<AddressmanageAdapter.DefaultViewHolder> {
    protected List<ItemModel> mDataList = new ArrayList<>();

    public AddressmanageAdapter() {
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<ItemModel> getDataList() {
        return mDataList;
    }

    public void setDataList(Collection<ItemModel> list) {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(Collection<ItemModel> list) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addressmanage,
                parent, false);
        return view;
    }

    @Override
    public AddressmanageAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView,
                                                                           int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(AddressmanageAdapter.DefaultViewHolder holder, int position) {

        String item = mDataList.get(position).title;

        DefaultViewHolder viewHolder = holder;
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder {
        public DefaultViewHolder(View itemView) {
            super(itemView);
        }
    }

}
