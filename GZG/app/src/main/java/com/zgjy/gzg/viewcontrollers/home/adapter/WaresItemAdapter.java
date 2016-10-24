package com.zgjy.gzg.viewcontrollers.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zgjy.gzg.R;

import java.util.List;

/**
 * Created by YX on 2016/10/17 0017.
 */
public class WaresItemAdapter extends RecyclerView.Adapter<WaresItemAdapter.ViewHolder> {

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickLitener{
        void onItemClick(View view,int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    private LayoutInflater mLayoutInflater;
    private List<Integer> mDatas;

    public WaresItemAdapter(Context context, List<Integer> datats) {
        mLayoutInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }
        ImageView mImg;
        TextView mTxt;
        TextView mPrice;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.guojiaguan_wares_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImg = (ImageView) view.findViewById(R.id.iv_wares);
        viewHolder.mTxt = (TextView) view.findViewById(R.id.tv_wares);
        viewHolder.mPrice = (TextView) view.findViewById(R.id.price);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.mImg.setImageResource(mDatas.get(i));

        if (mOnItemClickLitener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(viewHolder.itemView,i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
