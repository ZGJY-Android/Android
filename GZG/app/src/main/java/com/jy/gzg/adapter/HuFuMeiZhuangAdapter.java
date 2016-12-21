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
import com.jy.gzg.bean.ProductBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class HuFuMeiZhuangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener {
    private Context context;
    private ArrayList<ProductBean> hfmzProductBean;
    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public HuFuMeiZhuangAdapter(ArrayList<ProductBean> hfmzProductBean, Context context) {
        this.hfmzProductBean = hfmzProductBean;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 加载数据item的布局，生成ViewHolder返回
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hufumeizhuang,
                parent, false);
        v.setOnClickListener(this);
        return new DemoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductBean listBean;
        listBean = hfmzProductBean.get(position);
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(listBean);
        ImageLoader.getInstance().displayImage(listBean.getImage(), ((DemoViewHolder) holder)
                .iv_img);
        ((DemoViewHolder) holder).tv_name.setText(listBean.getName());
        ((DemoViewHolder) holder).tv_name.setText(listBean.getName());
        ((DemoViewHolder) holder).tv_price.setText(listBean.getPrice() + "");
        ((DemoViewHolder) holder).tv_marketprice.setText("￥" + listBean.getMarket_price());
        ((DemoViewHolder) holder).tv_marketprice.getPaint().setFlags(Paint
                .STRIKE_THRU_TEXT_FLAG);// 添加中划线
    }

    @Override
    public int getItemCount() {
        // 返回数据有多少条
        if (null == hfmzProductBean) {
            return 0;
        }
        // 此处加1是应为item作为header会消耗掉1个长度，导致数据少一个显示
        return hfmzProductBean.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (ProductBean) v.getTag());
        }
    }

    // 可复用的ViewHolder
    public class DemoViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_img;// 商品图片
        private TextView tv_name,// 名称
                tv_price,// 销售价
                tv_marketprice;// 市场价

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
        void onItemClick(View view, ProductBean data);
    }
}
