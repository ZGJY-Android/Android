package com.jy.gzg.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.jy.gzg.R;
import com.jy.gzg.adapter.SearchResultAdapter;
import com.jy.gzg.bean.SearchResultBean;
import com.jy.gzg.util.Constant;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {
    // 服务器端一共多少条数据
    private static final int TOTAL_COUNTER = 20;
    // 每一页展示多少条数据
    private static final int REQUEST_COUNT = 10;
    // 已经获取到多少条数据了
    private static int mCurrentCounter = 0;
    private SearchResultAdapter mSearchResultAdapter = null;
    private PreviewHandler mHandler = null;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private boolean isRefresh = false;
    // 申明控件对象
    private LRecyclerView mLRecyclerView = null;
    private ImageView iv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        initView();
        ArrayList<SearchResultBean> dataList = new ArrayList<>();
        mHandler = new PreviewHandler(SearchResultActivity.this);
        mSearchResultAdapter = new SearchResultAdapter(SearchResultActivity.this);
        mSearchResultAdapter.addAll(dataList);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(SearchResultActivity.this,
                mSearchResultAdapter);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));
        mLRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mLRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        setViewListen();
        mLRecyclerView.setRefreshing(true);

    }

    private void notifyDataSetChanged() {
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(ArrayList<SearchResultBean> list) {
        mSearchResultAdapter.addAll(list);
        mCurrentCounter += list.size();

    }

    private class PreviewHandler extends Handler {
        private WeakReference<Context> ref;

        PreviewHandler(Context context) {
            ref = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            final SearchResultActivity activity = (SearchResultActivity) ref.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            switch (msg.what) {
                case -1:
                    if (isRefresh) {
                        mSearchResultAdapter.clear();
                        mCurrentCounter = 0;
                    }
                    int currentSize = mSearchResultAdapter.getItemCount();
                    //模拟组装几个数据
                    ArrayList<SearchResultBean> newList = new ArrayList<>();
                    for (int i = 0; i < REQUEST_COUNT; i++) {
                        if (newList.size() + currentSize >= TOTAL_COUNTER) {
                            break;
                        }
                        SearchResultBean item = new SearchResultBean();
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
                                                (SearchResultActivity.this,
                                                        mLRecyclerView,
                                                        REQUEST_COUNT, LoadingFooter.State
                                                                .Loading, null);
                                        requestData();
                                    }
                                });
                    }
                    break;
                case -4:
                    int index = mSearchResultAdapter.getDataList().size();
                    mSearchResultAdapter.getDataList().remove(0);
                    mSearchResultAdapter.getDataList().remove(1);
                    mSearchResultAdapter.notifyItemRangeRemoved(0, 2);
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
     * 初始化各种控件
     */
    private void initView() {
        mLRecyclerView = (LRecyclerView) findViewById(R.id.mLRecyclerView);
        iv_return = (ImageView) findViewById(R.id.iv_return);
    }

    /**
     * 设置各种监听事件
     */
    private void setViewListen() {
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 这是mRecyclerView的滑动监听
         */
        mLRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                RecyclerViewStateUtils.setFooterViewState(mLRecyclerView, LoadingFooter.State
                        .Normal);
                mSearchResultAdapter.clear();
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
                    RecyclerViewStateUtils.setFooterViewState(SearchResultActivity.this,
                            mLRecyclerView,
                            REQUEST_COUNT, LoadingFooter.State.Loading, null);
                    requestData();
                } else {
                    //the end
                    RecyclerViewStateUtils.setFooterViewState(SearchResultActivity.this,
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
                Intent intent = new Intent(SearchResultActivity.this, ProductdetailsActivity
                        .class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }
}
