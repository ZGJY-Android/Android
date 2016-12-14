package com.jy.gzg.viewcontrollers.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
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

    private List<HomeProductBean> xstmBeanList, hlptBeanList, koreaBeanList, japanBeanList,
            aussieBeanList, europeBeanList;

    // 建立枚举5个item类型
    public enum ITEM_TYPE {
        TYPE_1,// 限时特卖
        TYPE_2,// 火力拼团
        TYPE_3,// 母婴专区
        TYPE_4// 国家馆
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

    public void setKoreaBeanList(List<HomeProductBean> koreaBeanList) {
        this.koreaBeanList = koreaBeanList;
    }

    public void setJapanBeanList(List<HomeProductBean> japanBeanList) {
        this.japanBeanList = japanBeanList;
    }

    public void setAussieBeanList(List<HomeProductBean> aussieBeanList) {
        this.aussieBeanList = aussieBeanList;
    }

    public void setEuropeBeanList(List<HomeProductBean> europeBeanList) {
        this.europeBeanList = europeBeanList;
    }

    @Override
    public int getItemViewType(int position) {
        // 每次都会调用此方法，获得当前所需要的view样式
        // Log.i(Constant.TAG, "getItemViewType执行了" + position);
        if (position < 1) {
            return ITEM_TYPE.TYPE_1.ordinal();
        } else if (position < 2) {
            return ITEM_TYPE.TYPE_2.ordinal();
        } else if (position < 3) {
            return ITEM_TYPE.TYPE_3.ordinal();
        } else {
            return ITEM_TYPE.TYPE_4.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 加载Item View的时候根据不同TYPE加载不同的布局
        // Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.TYPE_1.ordinal()代表0
        if (viewType == ITEM_TYPE.TYPE_1.ordinal()) {
            return new ViewHolder1(mLayoutInflater.inflate(R.layout.view_home_xianshitemai,
                    parent, false));
        } else if (viewType == ITEM_TYPE.TYPE_2.ordinal()) {
            return new ViewHolder2(mLayoutInflater.inflate(R.layout.view_home_huolipintuan,
                    parent, false));
        } else if (viewType == ITEM_TYPE.TYPE_3.ordinal()) {
            return new ViewHolder3(mLayoutInflater.inflate(R.layout
                    .view_home_muyingzhuanqu, parent, false));
        } else {
            return new ViewHolder4(mLayoutInflater.inflate(R.layout.view_home_guojiaguan,
                    parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder1) {
            // 网格布局管理器，且该recycleview纵向有2个item
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
            gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
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
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
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
        } else if (holder instanceof ViewHolder3) {
        } else {
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((ViewHolder4) holder).mRecyclerView.setLayoutManager(manager);
            if (position == 3) {
                // 韩国馆
                ((ViewHolder4) holder).iv_country.setImageResource(R.mipmap.nav_hgg);
                ((ViewHolder4) holder).iv_countryshow.setImageResource(R.mipmap.hgg);
                mWaresItemAdapter = new WaresItemAdapter(mContext, koreaBeanList);
                mWaresItemAdapter.setOnItemClickLitener(new WaresItemAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mContext,
                                ProductdetailsActivity
                                        .class);
                        intent.putExtra("product_id", koreaBeanList.get(position).getId() + "");
                        mContext.startActivity(intent);
                    }
                });
            } else if (position == 4) {
                // 日本馆
                ((ViewHolder4) holder).iv_country.setImageResource(R.mipmap.nav_rbg);
                ((ViewHolder4) holder).iv_countryshow.setImageResource(R.mipmap.rbg);
                mWaresItemAdapter = new WaresItemAdapter(mContext, japanBeanList);
                mWaresItemAdapter.setOnItemClickLitener(new WaresItemAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mContext,
                                ProductdetailsActivity
                                        .class);
                        intent.putExtra("product_id", japanBeanList.get(position).getId() + "");
                        mContext.startActivity(intent);
                    }
                });
            } else if (position == 5) {
                // 澳洲馆
                ((ViewHolder4) holder).iv_country.setImageResource(R.mipmap.nav_adly);
                ((ViewHolder4) holder).iv_countryshow.setImageResource(R.mipmap.adlyg);
                mWaresItemAdapter = new WaresItemAdapter(mContext, aussieBeanList);
                mWaresItemAdapter.setOnItemClickLitener(new WaresItemAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mContext,
                                ProductdetailsActivity
                                        .class);
                        intent.putExtra("product_id", aussieBeanList.get(position).getId() + "");
                        mContext.startActivity(intent);
                    }
                });
            } else if (position == 6) {
                // 欧洲馆
                ((ViewHolder4) holder).iv_country.setImageResource(R.mipmap.nav_ozg);
                ((ViewHolder4) holder).iv_countryshow.setImageResource(R.mipmap.ozg);
                mWaresItemAdapter = new WaresItemAdapter(mContext, europeBeanList);
                mWaresItemAdapter.setOnItemClickLitener(new WaresItemAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mContext,
                                ProductdetailsActivity
                                        .class);
                        intent.putExtra("product_id", europeBeanList.get(position).getId() + "");
                        mContext.startActivity(intent);
                    }
                });
            }
            ((ViewHolder4) holder).mRecyclerView.setAdapter(mWaresItemAdapter);
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

    private class ViewHolder3 extends RecyclerView.ViewHolder {
        public ViewHolder3(View itemView) {
            super(itemView);
        }
    }

    private class ViewHolder4 extends RecyclerView.ViewHolder {
        private ImageView iv_country,// 国家馆图片
                iv_countryshow;// 国家馆展示图片
        private RecyclerView mRecyclerView;

        public ViewHolder4(View itemView) {
            super(itemView);
            iv_country = (ImageView) itemView.findViewById(R.id
                    .iv_country);
            iv_countryshow = (ImageView) itemView.findViewById(R.id
                    .iv_countryshow);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id
                    .mLRecyclerView_guojiaguan);
        }
    }
}

