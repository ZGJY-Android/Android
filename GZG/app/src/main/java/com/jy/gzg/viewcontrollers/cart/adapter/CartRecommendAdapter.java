package com.jy.gzg.viewcontrollers.cart.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.gzg.R;

import java.util.List;

/**
 * Created by YX on 2016/12/6 0006.
 */

public class CartRecommendAdapter extends RecyclerView.Adapter<CartRecommendAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Integer> mData;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private CartRecommendAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(CartRecommendAdapter.OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public CartRecommendAdapter(Context context, List<Integer> mData) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @Override
    public CartRecommendAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_cart_recommend, viewGroup, false);
        CartRecommendAdapter.ViewHolder viewHolder = new CartRecommendAdapter.ViewHolder(view);
        viewHolder.img = (ImageView) view.findViewById(R.id.iv_cart_recommend);
        viewHolder.originalPrice = (TextView) view.findViewById(R.id.iv_cart_recommend_original);
        //给原价上加横线
        viewHolder.originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.name = (TextView) view.findViewById(R.id.tv_cart_recommend_name);
        viewHolder.currentPrice = (TextView) view.findViewById(R.id.iv_cart_recommend_current);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CartRecommendAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.img.setImageResource(mData.get(i));

        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        ImageView img;                 //商品图片
        TextView name,                 //商品名称
                originalPrice,        //原价
                currentPrice;         //现价

    }
}
