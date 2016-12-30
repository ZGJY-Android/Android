package com.jy.gzg.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.util.RecyclerViewUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.jy.gzg.R;
import com.jy.gzg.activity.ProductdetailsActivity;
import com.jy.gzg.adapter.ProductCommentAdapter;
import com.jy.gzg.bean.ProductCommentBean;
import com.jy.gzg.bean.ProductBean;
import com.jy.gzg.widget.Constant;
import com.jy.gzg.widget.ProductCommentHeader;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31 0031.
 */
public class ProductCommentFragment extends Fragment {
    private Context mContext;
    private ProductBean xstmBean;

    // 服务器端一共多少条数据
    private static final int TOTAL_COUNTER = 20;
    // 每一页展示多少条数据
    private static final int REQUEST_COUNT = 10;
    // 已经获取到多少条数据了
    private static int mCurrentCounter = 0;
    private ProductCommentAdapter mProductCommentAdapter = null;
    private PreviewHandler mHandler = null;
    private LRecyclerView mLRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private boolean isRefresh = false;

    public void setXstmBean(ProductBean xstmBean) {
        this.xstmBean = xstmBean;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productcomment, container, false);
        initViews(view);
        ArrayList<ProductCommentBean> dataList = new ArrayList<>();
        mHandler = new PreviewHandler(getActivity());
        mProductCommentAdapter = new ProductCommentAdapter(getActivity());
        mProductCommentAdapter.addAll(dataList);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(getActivity(),
                mProductCommentAdapter);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mLRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        RecyclerViewUtils.setHeaderView(mLRecyclerView, new ProductCommentHeader(mContext));
        setViewListen();
        mLRecyclerView.setRefreshing(true);
        return view;
    }

    /**
     * 初始化各种控件
     */
    public void initViews(View view) {
        mLRecyclerView = (LRecyclerView) view.findViewById(R.id.mLRecyclerView);
    }


    private void notifyDataSetChanged() {
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(ArrayList<ProductCommentBean> list) {
        mProductCommentAdapter.addAll(list);
        mCurrentCounter += list.size();

    }

    private class PreviewHandler extends Handler {
        private WeakReference<Context> ref;

        PreviewHandler(Context context) {
            ref = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            final ProductdetailsActivity activity = (ProductdetailsActivity) ref.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            switch (msg.what) {
                case -1:
                    if (isRefresh) {
                        mProductCommentAdapter.clear();
                        mCurrentCounter = 0;
                    }
                    int currentSize = mProductCommentAdapter.getItemCount();
                    //模拟组装几个数据
                    ArrayList<ProductCommentBean> newList = new ArrayList<>();
                    for (int i = 0; i < REQUEST_COUNT; i++) {
                        if (newList.size() + currentSize >= TOTAL_COUNTER) {
                            break;
                        }
                        ProductCommentBean item = new ProductCommentBean();
                        item.id = currentSize + i;
                        newList.add(item);
                    }
                    addItems(newList);
                    if (isRefresh) {
                        isRefresh = false;
                        mLRecyclerView.refreshComplete();
                        notifyDataSetChanged();
                    } else {
                        RecyclerViewStateUtils.setFooterViewState(mLRecyclerView, LoadingFooter
                                .State.Normal);
                    }
                    break;
                case -2:
                    notifyDataSetChanged();
                    break;
                case -3:
                    if (isRefresh) {
                        isRefresh = false;
                        mLRecyclerView.refreshComplete();
                        notifyDataSetChanged();
                    } else {
                        RecyclerViewStateUtils.setFooterViewState(activity, mLRecyclerView,
                                REQUEST_COUNT, LoadingFooter.State.NetWorkError, new View
                                        .OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        RecyclerViewStateUtils.setFooterViewState
                                                (getActivity(),
                                                        mLRecyclerView,
                                                        REQUEST_COUNT, LoadingFooter.State
                                                                .Loading, null);
                                        requestData();
                                    }
                                });
                    }
                    break;
                case -4:
                    int index = mProductCommentAdapter.getDataList().size();
                    mProductCommentAdapter.getDataList().remove(0);
                    mProductCommentAdapter.getDataList().remove(1);
                    mProductCommentAdapter.notifyItemRangeRemoved(0, 2);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 模拟请求网络
     */
    private void requestData() {
        Log.d(Constant.TAG, "requestData");
        new Thread() {

            @Override
            public void run() {
                super.run();
                // 模拟一下网络请求失败的情况
                //if (NetworkUtils.isNetAvailable(context)) {
                mHandler.sendEmptyMessage(-1);
//                } else {
//                    mHandler.sendEmptyMessage(-3);
//                }
            }
        }.start();
    }

    /**
     * 设置各种监听事件
     */
    private void setViewListen() {
        /**
         * 这是mRecyclerView的滑动监听
         */
        mLRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                RecyclerViewStateUtils.setFooterViewState(mLRecyclerView, LoadingFooter.State
                        .Normal);
                mProductCommentAdapter.clear();
                mCurrentCounter = 0;
                isRefresh = true;
                requestData();
            }

            @Override
            public void onScrollUp() {
            }

            @Override
            public void onScrollDown() {
            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState
                        (mLRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    Log.d(Constant.TAG, "the state is Loading, just wait..");
                    return;
                }
                if (mCurrentCounter < TOTAL_COUNTER) {
                    // loading more
                    RecyclerViewStateUtils.setFooterViewState(getActivity(),
                            mLRecyclerView,
                            REQUEST_COUNT, LoadingFooter.State.Loading, null);
                    requestData();
                } else {
                    //the end
                    RecyclerViewStateUtils.setFooterViewState(getActivity(),
                            mLRecyclerView,
                            REQUEST_COUNT, LoadingFooter.State.TheEnd, null);
                }
            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {
            }
        });

        /**
         * 设置mLRecyclerViewAdapter的item的点击事件
         */
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }
}
