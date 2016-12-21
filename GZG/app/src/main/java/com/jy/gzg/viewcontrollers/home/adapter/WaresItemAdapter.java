package com.jy.gzg.viewcontrollers.home.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.bean.HomeProductBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by YX on 2016/10/17 0017.
 */
public class WaresItemAdapter extends RecyclerView.Adapter<WaresItemAdapter.ViewHolder> {
    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private LayoutInflater mLayoutInflater;
    private List<HomeProductBean> mDatas;
    private ImageLoader imageLoader;

    public WaresItemAdapter(Context context, List<HomeProductBean> datats) {
        mLayoutInflater = LayoutInflater.from(context);
        mDatas = datats;
        imageLoader = ImageLoader.getInstance();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
        TextView mTxt, mPrice, tv_marketprice;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.producttype_wares_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImg = (ImageView) view.findViewById(R.id.iv_img);
        viewHolder.mTxt = (TextView) view.findViewById(R.id.tv_wares);
        viewHolder.mPrice = (TextView) view.findViewById(R.id.tv_price);
        viewHolder.tv_marketprice = (TextView) view.findViewById(R.id.tv_marketprice);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        imageLoader.displayImage(mDatas.get(i).getImage() + "", viewHolder.mImg);
        viewHolder.mTxt.setText(mDatas.get(i).getName());
        viewHolder.mPrice.setText("￥" + mDatas.get(i).getPrice() + "");
        viewHolder.tv_marketprice.setText(mDatas.get(i).getMarket_price() + "");
        viewHolder.tv_marketprice.getPaint().setFlags(Paint
                .STRIKE_THRU_TEXT_FLAG);// 添加中划线

        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
