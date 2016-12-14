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
public class HLPTAdapter extends RecyclerView.Adapter<HLPTAdapter.DemoViewHolder> implements View
        .OnClickListener {
    private List<HomeProductBean> hlptBeanList;
    private Context context;
    private ImageLoader imageLoader;
    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public HLPTAdapter(List<HomeProductBean> xstmBeanList, Context context) {
        this.hlptBeanList = xstmBeanList;
        this.context = context;
    }

    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 加载数据item的布局，生成ViewHolder返回
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_huolipintuan,
                parent, false);
        v.setOnClickListener(this);
        imageLoader = ImageLoader.getInstance();
        return new DemoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DemoViewHolder demoViewHolder, final int position) {
        HomeProductBean listBean = hlptBeanList.get(position);
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
        demoViewHolder.tv_marketprice.getPaint().setFlags(Paint
                .STRIKE_THRU_TEXT_FLAG);// 添加中划线
        if (position == (getItemCount() - 1)) {
            demoViewHolder.view_line.setVisibility(View.GONE);// 最后一个商品隐藏分割线
        }
    }

    @Override
    public int getItemCount() {
        // 返回数据有多少条
        if (null == hlptBeanList) {
            return 0;
        }
        if (hlptBeanList.size() > 3) {
            return 3;// 因为此处只需展示三条数据即可
        } else {
            return hlptBeanList.size();
        }
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
        private ImageView iv_img;// 商品图片
        private TextView tv_name,// 名称
                tv_price,// 销售价
                tv_marketprice;// 市场价
        private View view_line;// 分割线

        public DemoViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_marketprice = (TextView) itemView.findViewById(R.id.tv_marketprice);
            view_line = (View) itemView.findViewById(R.id.view_line);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, HomeProductBean data);
    }
}
