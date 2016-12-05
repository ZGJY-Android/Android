package com.jy.gzg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jy.gzg.R;

import java.util.List;

/**
 * Created by YX on 2016/11/18 0018.
 */
public class CountryHorizontalAdapter extends RecyclerView.Adapter<CountryHorizontalAdapter.ViewHodler> {

    private LayoutInflater mLayoutInflater;
    public List<Integer> mData;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setmOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    public CountryHorizontalAdapter(Context context, List<Integer> mData) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_country_horizontal,viewGroup,false);
        ViewHodler viewHodler = new ViewHodler(view);
        viewHodler.mImg = (ImageView) view.findViewById(R.id.country_horizontal);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler viewHodler, final int i) {
        viewHodler.mImg.setImageResource(mData.get(i));
        if (mOnItemClickListener!= null){
            viewHodler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view,i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHodler extends RecyclerView.ViewHolder {

        public ViewHodler(View itemView) {
            super(itemView);
        }
        ImageView mImg;
    }

}

