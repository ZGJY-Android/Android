package com.jy.gzg.viewcontrollers.category.adapter;

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
 * Created by YX on 2016/11/7 0007.
 */
public class ReclassifyAdapter extends RecyclerView.Adapter<ReclassifyAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    public List<Integer> mDatas;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public ReclassifyAdapter(Context context, List<Integer> datas) {
        this.mDatas = datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }
        ImageView mImg;
        TextView mTxt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.ceclassify_wares_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImg = (ImageView) view.findViewById(R.id.iv_reclassify_wares);
        viewHolder.mTxt = (TextView) view.findViewById(R.id.tv_reclassify_wares);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.mImg.setImageResource(mDatas.get(i));

        if (onItemClickListener != null) {
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
        return mDatas.size();
    }



    public void addData(int pos,Integer datas) {
        mDatas.add(pos, datas);
        notifyItemInserted(pos);
    }

    public void removeData(int pos) {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }



}
