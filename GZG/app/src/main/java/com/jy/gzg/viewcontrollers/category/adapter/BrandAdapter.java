package com.jy.gzg.viewcontrollers.category.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.category.bean.BrandBean;
import com.jy.gzg.widget.AppConstant;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by YX on 2016/11/10 0010.
 */
public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<BrandBean.ListBean> listBeen = null;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public BrandAdapter(Context Context) {
        mLayoutInflater = LayoutInflater.from(Context);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
        ImageView mImg;
        TextView name;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_reclassify_brand,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImg = (ImageView) view.findViewById(R.id.iv_reclassify_brand);
        viewHolder.name = (TextView) view.findViewById(R.id.tv_reclassify_brand);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        BrandBean.ListBean currentItem = listBeen.get(i);
        viewHolder.name.setText(currentItem.getName());
        String logoUrl = AppConstant.IP+currentItem.getLogo();
        ImageLoader.getInstance().displayImage(logoUrl,viewHolder.mImg);
        if (onItemClickListener == null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view,i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listBeen.size();
    }

    public void addDatas(List<BrandBean.ListBean> listBeen) {
        this.listBeen = listBeen;
        notifyDataSetChanged();
    }
}
