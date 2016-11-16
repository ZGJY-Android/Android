package com.jy.gzg.viewcontrollers.category.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jy.gzg.R;

import java.util.List;

/**
 * Created by YX on 2016/11/11 0011.
 */
public class BrandReclassifyAdapter extends RecyclerView.Adapter<BrandReclassifyAdapter.ViewHodler> {

    private LayoutInflater mLayoutInflater;
    public List<Integer> mDatas;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }


    public BrandReclassifyAdapter(Context context, List<Integer> mDatas) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.reclassify_brand_wares,viewGroup,false);
        ViewHodler viewHodler = new ViewHodler(view);
        viewHodler.mImg = (ImageView) view.findViewById(R.id.iv_reclassify_brand_wares);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler viewHodler, final int i) {
        viewHodler.mImg.setImageResource(mDatas.get(i));

        if (onItemClickListener!=null){
            viewHodler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view,i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHodler extends RecyclerView.ViewHolder{
        public ViewHodler(View itemView) {
            super(itemView);
        }
        ImageView mImg;
    }
}
