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
 * Created by Administrator on 2016/10/19 0019.
 */
public class XSTMAdapter extends RecyclerView.Adapter<XSTMAdapter.DemoViewHolder> implements View
        .OnClickListener {
    private List<HomeProductBean> xstmBeanList;
    private Context context;
    private ImageLoader imageLoader;
    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public XSTMAdapter(List<HomeProductBean> xstmBeanList, Context context) {
        this.xstmBeanList = xstmBeanList;
        this.context = context;
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 加载数据item的布局，生成ViewHolder返回
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_xianshitemai,
                parent, false);
        v.setOnClickListener(this);
        return new DemoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DemoViewHolder demoViewHolder, final int position) {
        HomeProductBean listBean = xstmBeanList.get(position);
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
        if (null == xstmBeanList) {
            return 0;
        }
        return xstmBeanList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (HomeProductBean) v.getTag());
        }
    }

    // 可复用的ViewHolder
    public static class DemoViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_img;
        private TextView tv_name, tv_price, tv_marketprice;

        public DemoViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_marketprice = (TextView) itemView.findViewById(R.id.tv_marketprice);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, HomeProductBean data);
    }
}
