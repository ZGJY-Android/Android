package com.jy.gzg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.gzg.R;

import java.util.List;

/**
 * Created by YX on 2016/11/21 0021.
 */
public class CountryVerticalAdapter extends RecyclerView.Adapter<CountryVerticalAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Integer> mData;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public CountryVerticalAdapter(Context context, List<Integer> mData) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_country_vertical, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.img = (ImageView) view.findViewById(R.id.iv_country_wares);
        viewHolder.intro = (TextView) view.findViewById(R.id.tv_country_intro);
        viewHolder.name = (TextView) view.findViewById(R.id.tv_country_wares);
        viewHolder.price = (TextView) view.findViewById(R.id.tv_country_price);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
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

        ImageView img;
        TextView intro;
        TextView name;
        TextView price;
    }
}
