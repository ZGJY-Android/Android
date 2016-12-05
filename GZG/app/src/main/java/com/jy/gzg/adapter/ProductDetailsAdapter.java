package com.jy.gzg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jy.gzg.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsAdapter
        .DemoViewHolder> {
    ArrayList<String> imgList;
    Context context;
    ImageLoader imageLoader;

    public ProductDetailsAdapter(ArrayList<String> imgList, Context context) {
        this.imgList = imgList;
        this.context = context;
    }

    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 加载数据item的布局，生成ViewHolder返回
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .item_product_details,
                parent, false);
        imageLoader = ImageLoader.getInstance();
        return new DemoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DemoViewHolder holder, int position) {
        imageLoader.displayImage(imgList.get(position), holder.iv_img);
    }

    @Override
    public int getItemCount() {
        // 返回数据有多少条
        if (null == imgList) {
            return 0;
        }
        return imgList.size();
    }

    // 可复用的ViewHolder
    public static class DemoViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_img;

        public DemoViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }
}
