package com.jy.gzg.viewcontrollers.home.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.gzg.R;
import com.jy.gzg.adapter.ListBaseAdapter;
import com.jy.gzg.util.T;
import com.jy.gzg.viewcontrollers.home.bean.ItemModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/10/12 0012.
 */
public class DataAdapter extends ListBaseAdapter<ItemModel> {

    // 建立枚举5个item类型
    public enum ITEM_TYPE {
        TYPE_1,// 限时特卖
        TYPE_2,// 火力拼团
        TYPE_3,// 全球精选
        TYPE_4,// 跨境直邮
        TYPE_5,// 国家馆
    }

    private LayoutInflater mLayoutInflater;

    public DataAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        // 每次都会调用此方法，获得当前所需要的view样式
//        Log.i(Constant.TAG, "getItemViewType执行了" + position);
        if (position < 1) {
            return ITEM_TYPE.TYPE_1.ordinal();
        } else if (position < 2) {
            return ITEM_TYPE.TYPE_2.ordinal();
        } else if (position < 3) {
            return ITEM_TYPE.TYPE_3.ordinal();
        } else if (position < 4) {
            return ITEM_TYPE.TYPE_4.ordinal();
        } else {
            return ITEM_TYPE.TYPE_5.ordinal();
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
                    .view_home_quanqiujingxuan, parent, false));
        } else if (viewType == ITEM_TYPE.TYPE_4.ordinal()) {
            return new ViewHolder4(mLayoutInflater.inflate(R.layout.view_home_kuajingzhiyou,
                    parent, false));
        } else {
            return new ViewHolder5(mLayoutInflater.inflate(R.layout.view_home_guojiaguan,
                    parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // ItemModel item = mDataList.get(position);
        if (holder instanceof ViewHolder1) {
            //((ViewHolder1) holder).textView.setText("同志们1好" + position);
        } else if (holder instanceof ViewHolder2) {
        } else if (holder instanceof ViewHolder3) {
        } else if (holder instanceof ViewHolder4) {
        } else {
        }

    }

    // 限时特卖
    private class ViewHolder1 extends RecyclerView.ViewHolder {
        private RecyclerView mRecycleView;

        public ViewHolder1(View itemView) {
            super(itemView);
            mRecycleView = (RecyclerView) itemView.findViewById(R.id.mRecycleView);
            // 网格布局管理器，且该recycleview纵向有2个item
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
            gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecycleView.setLayoutManager(gridLayoutManager);
            ArrayList<String> commodityList = new ArrayList<String>();
            for (int i = 0; i < 11; i++) {
                commodityList.add("haha_" + i);
            }
            XSTMAdapter xstmAdapter = new XSTMAdapter(commodityList, mContext);
            mRecycleView.setAdapter(xstmAdapter);

        }
    }

    private class ViewHolder2 extends RecyclerView.ViewHolder {

        public ViewHolder2(View itemView) {
            super(itemView);
        }
    }

    private class ViewHolder3 extends RecyclerView.ViewHolder {

        public ViewHolder3(View itemView) {
            super(itemView);
        }
    }

    private class ViewHolder4 extends RecyclerView.ViewHolder {

        public ViewHolder4(View itemView) {
            super(itemView);
        }
    }

    private class ViewHolder5 extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private WaresItemAdapter mWaresItemAdapter;
        private List<Integer> mDatas;

        public ViewHolder5(View itemView) {
            super(itemView);

            initData();

            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.mLRecyclerView_guojiaguan);
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(manager);

            mWaresItemAdapter = new WaresItemAdapter(mContext,mDatas);
            mWaresItemAdapter.setOnItemClickLitener(new WaresItemAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    T.getInstance().showShort(position+"");
                }
            });
            mRecyclerView.setAdapter(mWaresItemAdapter);

        }

        private void initData() {
            mDatas = new ArrayList<>(Arrays.asList(R.mipmap.sy_hlpic1,R.mipmap.sy_hlpic2,R.mipmap.sy_hlpic3,R.mipmap.sy_xspic2,R.mipmap.sy_xspic3,R.mipmap.sy_xspic4));
        }

    }


}

