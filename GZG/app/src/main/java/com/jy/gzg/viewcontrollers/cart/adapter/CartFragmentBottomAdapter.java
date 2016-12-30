package com.jy.gzg.viewcontrollers.cart.adapter;

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
 * Created by Administrator on 2016/11/28 0028.
 */
public class CartFragmentBottomAdapter extends RecyclerView.Adapter<CartFragmentBottomAdapter
        .DefaultViewHolder> {
    private Context mContext;
    private List<HomeProductBean> mDatas;
    private ImageLoader imageLoader;

    public CartFragmentBottomAdapter(Context mContext, List<HomeProductBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        imageLoader = ImageLoader.getInstance();
    }


    @Override
    public DefaultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_cart_recommend, parent, false);
        return new CartFragmentBottomAdapter.DefaultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder demoViewHolder, int position) {
        HomeProductBean listBean = mDatas.get(position);
        //将数据保存在itemView的Tag中，以便点击时进行获取
        demoViewHolder.itemView.setTag(listBean);
        if (listBean.getImage() != null && !listBean.getImage().equals("")) {
            imageLoader.displayImage(listBean.getImage().toString(), demoViewHolder.iv_img);
        } else {
            imageLoader.displayImage("", demoViewHolder.iv_img);
        }
        demoViewHolder.tv_name.setText(listBean.getName());
        demoViewHolder.tv_price.setText("￥" + listBean.getPrice());
        demoViewHolder.tv_marketprice.setText("￥" + listBean.getMarket_price());
        demoViewHolder.tv_marketprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        // 返回数据有多少条
        if (null == mDatas) {
            return 0;
        }
        return mDatas.size();
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_img;
        private TextView tv_name, tv_price, tv_marketprice;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_marketprice = (TextView) itemView.findViewById(R.id.tv_marketprice);
        }
    }

}
