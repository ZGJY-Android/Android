package com.jy.gzg.viewcontrollers.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jy.gzg.R;
import com.jy.gzg.activity.ProductdetailsActivity;
import com.jy.gzg.adapter.ListBaseAdapter;
import com.jy.gzg.bean.HomeProductBean;
import com.jy.gzg.ui.CustomGridLayoutManager;
import com.jy.gzg.ui.CustomLinearLayoutManager;
import com.jy.gzg.viewcontrollers.home.bean.ItemModelBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/12 0012.
 */
public class DataAdapter extends ListBaseAdapter<ItemModelBean> {
    private LayoutInflater mLayoutInflater;
    private XSTMAdapter xstmAdapter;
    private HLPTAdapter hlptAdapter;
    private WaresItemAdapter mWaresItemAdapter;

    private List<HomeProductBean> xstmBeanList, hlptBeanList, myzxBeanList, hfmzBeanList,
            jkbjBeanList, jjryBeanList;

    // 建立枚举5个item类型
    public enum ITEM_TYPE {
        TYPE_1,// 限时特卖
        TYPE_2,// 火力拼团
        TYPE_3// 产品分类
    }

    public DataAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setXstmBeanList(List<HomeProductBean> xstmBeanList) {
        this.xstmBeanList = xstmBeanList;
    }

    public void setHlptBeanList(List<HomeProductBean> hlptBeanList) {
        this.hlptBeanList = hlptBeanList;
    }

    public void setMyzcBeanList(List<HomeProductBean> myzxBeanList) {
        this.myzxBeanList = myzxBeanList;
    }

    public void setHfmzBeanList(List<HomeProductBean> hfmzBeanList) {
        this.hfmzBeanList = hfmzBeanList;
    }

    public void setJkbjBeanList(List<HomeProductBean> jkbjBeanList) {
        this.jkbjBeanList = jkbjBeanList;
    }

    public void setJjryBeanList(List<HomeProductBean> jjryBeanList) {
        this.jjryBeanList = jjryBeanList;
    }

    @Override
    public int getItemViewType(int position) {
        // 每次都会调用此方法，获得当前所需要的view样式
        // Log.i(Constant.TAG, "getItemViewType执行了" + position);
        if (position < 1) {
            return ITEM_TYPE.TYPE_1.ordinal();
        } else if (position < 2) {
            return ITEM_TYPE.TYPE_2.ordinal();
        } else {
            return ITEM_TYPE.TYPE_3.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 加载Item View的时候根据不同TYPE加载不同的布局
        // Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.TYPE_1.ordinal()代表0
        if (viewType == ITEM_TYPE.TYPE_1.ordinal())
            return new ViewHolder1(mLayoutInflater.inflate(R.layout.view_home_xianshitemai,
                    parent, false));
        else if (viewType == ITEM_TYPE.TYPE_2.ordinal())
            return new ViewHolder2(mLayoutInflater.inflate(R.layout.view_home_huolipintuan,
                    parent, false));
        else
            return new ViewHolder3(mLayoutInflater.inflate(R.layout.view_home_producttype,
                    parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder1) {
            // 网格布局管理器，且该recycleview纵向有2个item
            CustomGridLayoutManager gridLayoutManager = new CustomGridLayoutManager(mContext, 3);
            gridLayoutManager.setScrollEnabled(false);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((ViewHolder1) holder).mRecycleView.setLayoutManager(gridLayoutManager);
            xstmAdapter = new XSTMAdapter(xstmBeanList, mContext);
            ((ViewHolder1) holder).mRecycleView.setAdapter(xstmAdapter);
            xstmAdapter.setOnItemClickListener(new XSTMAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, HomeProductBean data) {
                    Intent intent = new Intent(mContext,
                            ProductdetailsActivity
                                    .class);
                    intent.putExtra("product_id", data.getId() + "");
                    mContext.startActivity(intent);
                }
            });
        } else if (holder instanceof ViewHolder2) {
            CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(mContext);
            linearLayoutManager.setScrollEnabled(false);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((ViewHolder2) holder).mRecycleView.setLayoutManager(linearLayoutManager);
            hlptAdapter = new HLPTAdapter(hlptBeanList, mContext);
            ((ViewHolder2) holder).mRecycleView.setAdapter(hlptAdapter);
            hlptAdapter.setOnItemClickListener(new HLPTAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, HomeProductBean data) {
                    Intent intent = new Intent(mContext,
                            ProductdetailsActivity
                                    .class);
                    intent.putExtra("product_id", data.getId() + "");
                    mContext.startActivity(intent);
                }
            });
        } else {
            // 网格布局管理器，且该recycleview纵向有2个item
            CustomGridLayoutManager gridLayoutManager = new CustomGridLayoutManager(mContext, 3);
            gridLayoutManager.setScrollEnabled(false);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((ViewHolder3) holder).mRecyclerView.setLayoutManager(gridLayoutManager);
            if (position == 2) {
                // 母婴专场
                ((ViewHolder3) holder).iv_producttype.setImageResource(R.mipmap.myzx);
                mWaresItemAdapter = new WaresItemAdapter(mContext, myzxBeanList);
                mWaresItemAdapter.setOnItemClickLitener(new WaresItemAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mContext,
                                ProductdetailsActivity
                                        .class);
                        intent.putExtra("product_id", myzxBeanList.get(position).getId() + "");
                        mContext.startActivity(intent);
                    }
                });
            } else if (position == 3) {
                // 护肤美妆
                ((ViewHolder3) holder).iv_producttype.setImageResource(R.mipmap.hfmz);
                mWaresItemAdapter = new WaresItemAdapter(mContext, hfmzBeanList);
                mWaresItemAdapter.setOnItemClickLitener(new WaresItemAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mContext,
                                ProductdetailsActivity
                                        .class);
                        intent.putExtra("product_id", hfmzBeanList.get(position).getId() + "");
                        mContext.startActivity(intent);
                    }
                });
            } else if (position == 4) {
                // 健康保健
                ((ViewHolder3) holder).iv_producttype.setImageResource(R.mipmap.jkbj);
                mWaresItemAdapter = new WaresItemAdapter(mContext, jkbjBeanList);
                mWaresItemAdapter.setOnItemClickLitener(new WaresItemAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mContext,
                                ProductdetailsActivity
                                        .class);
                        intent.putExtra("product_id", jkbjBeanList.get(position).getId() + "");
                        mContext.startActivity(intent);
                    }
                });
            } else if (position == 5) {
                // 居家日用
                ((ViewHolder3) holder).iv_producttype.setImageResource(R.mipmap.jjry);
                mWaresItemAdapter = new WaresItemAdapter(mContext, jjryBeanList);
                mWaresItemAdapter.setOnItemClickLitener(new WaresItemAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mContext,
                                ProductdetailsActivity
                                        .class);
                        intent.putExtra("product_id", jjryBeanList.get(position).getId() + "");
                        mContext.startActivity(intent);
                    }
                });
            }
            ((ViewHolder3) holder).mRecyclerView.setAdapter(mWaresItemAdapter);
        }
    }

    // 限时特卖
    private class ViewHolder1 extends RecyclerView.ViewHolder {
        private RecyclerView mRecycleView;

        public ViewHolder1(View itemView) {
            super(itemView);
            mRecycleView = (RecyclerView) itemView.findViewById(R.id.mRecycleView);
        }
    }

    // 火力拼团
    private class ViewHolder2 extends RecyclerView.ViewHolder {
        private RecyclerView mRecycleView;

        public ViewHolder2(View itemView) {
            super(itemView);
            mRecycleView = (RecyclerView) itemView.findViewById(R.id.mRecycleView);
        }
    }

    // 各种产品分类
    private class ViewHolder3 extends RecyclerView.ViewHolder {
        private ImageView iv_producttype;// 产品分类展示图片
        private RecyclerView mRecyclerView;

        public ViewHolder3(View itemView) {
            super(itemView);
            iv_producttype = (ImageView) itemView.findViewById(R.id.iv_producttype);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id
                    .mRecyclerView);
        }
    }
}

