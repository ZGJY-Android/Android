package com.jy.gzg.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.bean.ProductBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class HuolipintuanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener {
    private ArrayList<ProductBean> hlptBeanList;
    private Context context;
    private OnRecyclerViewItemClickListener mOnItemClickListener;
    //item类型
    public static final int ITEM_TYPE_HEADER = 0,// 头部item
            ITEM_TYPE_CONTENT = 1;// 内容item

    public HuolipintuanAdapter(ArrayList<ProductBean> xstmBeanList, Context context) {
        this.hlptBeanList = xstmBeanList;
        this.context = context;
    }

    public int getItemViewType(int position) {
        // 每次都会调用此方法，获得当前所需要的view样式
        if (position < 1) {
            //头部View
            return ITEM_TYPE_HEADER;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 加载数据item的布局，生成ViewHolder返回
        View v;
        if (viewType == ITEM_TYPE_HEADER) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huolipintuan_header,
                    parent, false);
            return new HeaderViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huolipintuan,
                    parent, false);
            v.setOnClickListener(this);
            return new DemoViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductBean listBean;
        if (holder instanceof DemoViewHolder) {
            listBean = hlptBeanList.get(position - 1);// 必须将position-1，否则会缺少一个商品
            //将数据保存在itemView的Tag中，以便点击时进行获取
            holder.itemView.setTag(listBean);
            //获取屏幕信息
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            int screenWidth = dm.widthPixels;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth / 2,
                    screenWidth / 2);// 设置图片宽高相等
            ((DemoViewHolder) holder).relat_left.setLayoutParams(params);
            ImageLoader.getInstance().displayImage(listBean.getImage(), ((DemoViewHolder) holder)
                    .iv_img);
            if (listBean.getMemo() == null || listBean.getMemo().equals("")) {
                ((DemoViewHolder) holder).tv_memo.setText("暂无备注");
            } else
                ((DemoViewHolder) holder).tv_memo.setText("[推荐理由]" + listBean.getMemo());
            ((DemoViewHolder) holder).tv_name.setText(listBean.getName());
            ((DemoViewHolder) holder).tv_point.setText(listBean.getPoint() + "");
            ((DemoViewHolder) holder).tv_monthsales.setText(listBean.getMonth_sales() + "");
            ((DemoViewHolder) holder).tv_name.setText(listBean.getName());
            ((DemoViewHolder) holder).tv_price.setText(listBean.getPrice() + "");
            ((DemoViewHolder) holder).tv_marketprice.setText("￥" + listBean.getMarket_price());
            ((DemoViewHolder) holder).tv_marketprice.getPaint().setFlags(Paint
                    .STRIKE_THRU_TEXT_FLAG);// 添加中划线
        }
    }

    @Override
    public int getItemCount() {
        // 返回数据有多少条
        if (null == hlptBeanList) {
            return 0;
        }
        // 此处加1是应为item作为header会消耗掉1个长度，导致数据少一个显示
        return hlptBeanList.size() + 1;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (ProductBean) v.getTag());
        }
    }

    //头部 ViewHolder
    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    // 可复用的ViewHolder
    public class DemoViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relat_left;
        private ImageView iv_img;// 商品图片
        private TextView tv_memo,// 备注
                tv_name,// 名称
                tv_point,// 赠送积分
                tv_monthsales,// 月销量
                tv_price,// 销售价
                tv_marketprice;// 市场价

        public DemoViewHolder(View itemView) {
            super(itemView);
            relat_left = (RelativeLayout) itemView.findViewById(R.id.relat_left);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_memo = (TextView) itemView.findViewById(R.id.tv_memo);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_point = (TextView) itemView.findViewById(R.id.tv_point);
            tv_monthsales = (TextView) itemView.findViewById(R.id.tv_monthsales);
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
