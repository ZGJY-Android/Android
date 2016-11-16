package com.jy.gzg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.gzg.R;
import com.jy.gzg.bean.ProductCommentBean;

/**
 * Created by Administrator on 2016/10/22 0022.
 */
public class ProductCommentAdapter extends ListBaseAdapter<ProductCommentBean> {
    private LayoutInflater mLayoutInflater;

    public ProductCommentAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_product_comment,
                parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
