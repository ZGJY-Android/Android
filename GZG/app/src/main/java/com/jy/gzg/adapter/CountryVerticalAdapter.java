package com.jy.gzg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.bean.ProductBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by YX on 2016/11/21 0021.
 */
public class CountryVerticalAdapter extends RecyclerView.Adapter<CountryVerticalAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private ArrayList<ProductBean> mData;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public CountryVerticalAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);

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
        ProductBean currentWares = mData.get(i);
        viewHolder.name.setText(currentWares.getName());
        viewHolder.price.setText("￥" + currentWares.getPrice());
        ImageLoader.getInstance().displayImage(currentWares.getImage(), viewHolder.img);
        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, i);
                }
            });
        }
    }

    public void setmData(ArrayList<ProductBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if (mData == null) {
            mData = new ArrayList<ProductBean>();
        }
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
