package com.jy.gzg.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.bean.ProductBean2;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2016/10/22 0022.
 */
public class SearchResultAdapter extends ListBaseAdapter<ProductBean2> {
    private LayoutInflater mLayoutInflater;
    private ImageLoader imageLoader;

    public SearchResultAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        imageLoader = ImageLoader.getInstance();
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_searchresult_commodity,
                parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ProductBean2 productBean2 = mDataList.get(position);
        imageLoader.displayImage(productBean2.getImage(), ((ViewHolder) holder).iv_img);
        ((ViewHolder) holder).tv_name.setText(productBean2.getName());
        ((ViewHolder) holder).tv_memo.setText(productBean2.getMemo());
        ((ViewHolder) holder).tv_price.setText(productBean2.getPrice() + "");
        ((ViewHolder) holder).tv_marketprice.setText("￥" + productBean2.getMarket_price());
        ((ViewHolder) holder).tv_marketprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_img;// 图片展示
        private TextView tv_name,// 名称
                tv_memo,// 说明
                tv_price,// 销售价
                tv_marketprice;// 市场价

        public ViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_memo = (TextView) itemView.findViewById(R.id.tv_memo);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_marketprice = (TextView) itemView.findViewById(R.id.tv_marketprice);
        }
    }
}
