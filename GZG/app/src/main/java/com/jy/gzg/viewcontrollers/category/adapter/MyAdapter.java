package com.jy.gzg.viewcontrollers.category.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.util.AppToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    private ArrayList<String> mDatas = new ArrayList<>();

    private View mHeaderView;

//    private OnItemClickListener mListener;
//
//    public void setOnItemClickListener(OnItemClickListener li) {
//        mListener = li;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(int position, String data);
//    }

    public MyAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void addDatas(ArrayList<String> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new Holder(mHeaderView);
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.reclassify,
                parent, false);
        return new Holder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(viewHolder);
        final String data = mDatas.get(pos);
        if (viewHolder instanceof Holder) {
            ((Holder) viewHolder).text.setText(data);
//            if (mListener == null) return;
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mListener.onItemClick(pos, data);
//                }
//            });
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView text;

        private RecyclerView mRecyclerView;
        private List<Integer> mDatas;
        private ReclassifyAdapter mAdapter;


        public Holder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) return;
            //二级分类的标题
            text = (TextView) itemView.findViewById(R.id.tv_reclassify);

            //二级分类的商品
            initData();
            initReclassifyViews(itemView);

            //设置布局管理器
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
            mRecyclerView.setLayoutManager(gridLayoutManager);

            mAdapter = new ReclassifyAdapter(mContext, mDatas);
            mAdapter.setOnItemClickListener(new ReclassifyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    AppToast.getInstance().showShort("分类商品" + position);
                }
            });
            //添加之定义分割线
//            mRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
            mRecyclerView.setAdapter(mAdapter);

        }

        private void initReclassifyViews(View itemView) {
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_reclassify);
        }

        private void initData() {
            mDatas = new ArrayList<>(Arrays.asList(R.mipmap.sy_mypic3, R.mipmap.sy_mypic6, R.mipmap.sy_naifen, R.mipmap.sy_mypic6, R.mipmap.sy_mypic3));
        }
    }
}