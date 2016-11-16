package com.jy.gzg.viewcontrollers.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.mine.bean.MyCollectionBean;
import com.jy.gzg.viewcontrollers.mine.minterface.OnMSwipItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class MyCollectionAdapter extends RecyclerView.Adapter<MyCollectionAdapter.ViewHolder> {
    private Context mContext;
    private List<MyCollectionBean> mItems = new ArrayList<>();
    private OnMSwipItemClickListener listener;

    public MyCollectionAdapter(Context mContext, List<MyCollectionBean> users,
                               OnMSwipItemClickListener mlistner) {
        this.mContext = mContext;
        this.mItems = users;
        this.listener = mlistner;
    }

    @Override
    public MyCollectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_mycollection, parent, false);
        ViewHolder vh = new ViewHolder(v, this.listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ViewHolder mholder = holder;
        MyCollectionBean bean = mItems.get(position);
        mholder.relat_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了第" + position + "个ItemView", Toast
                        .LENGTH_SHORT).show();
            }
        });
        mholder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItems.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnMSwipItemClickListener mListener;
        private RelativeLayout relat_line;
        private Button btn_delete;

        public ViewHolder(View itemView, OnMSwipItemClickListener Listener) {
            super(itemView);
            relat_line = (RelativeLayout) itemView.findViewById(R.id.relat_line);
            btn_delete = (Button) itemView.findViewById(R.id.btn_delete);
            this.mListener = Listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
