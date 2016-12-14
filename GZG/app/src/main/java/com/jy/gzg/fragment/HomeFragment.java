package com.jy.gzg.fragment;


import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.util.RecyclerViewUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.jy.gzg.R;
import com.jy.gzg.activity.MainActivity;
import com.jy.gzg.activity.SearchActivity;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.bean.HuolipintuanBean;
import com.jy.gzg.bean.ProductBean;
import com.jy.gzg.bean.XianshitemaiBean;
import com.jy.gzg.util.Constant;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.viewcontrollers.home.adapter.DataAdapter;
import com.jy.gzg.viewcontrollers.home.bean.ItemModel;
import com.jy.gzg.viewcontrollers.home.widget.HomeConstant;
import com.jy.gzg.viewcontrollers.home.widget.SampleHeader;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用带HeaderView的分页加载LinearLayout RecyclerView
 */

public class HomeFragment extends Fragment {
    private Context mContext;
    // 服务器端一共多少条数据
    private static final int TOTAL_COUNTER = 8;
    // 每一页展示多少条数据
    private static final int REQUEST_COUNT = 8;
    // 已经获取到多少条数据了
    private static int mCurrentCounter = 0;
    private DataAdapter mDataAdapter = null;
    private PreviewHandler mHandler = null;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private boolean isRefresh = false;
    private List<ProductBean> xstmBeanList,// 限时特卖的相关数据
            hlptBeanList;// 火力拼团的相关数据

    // 申明控件对象
    private ImageView title_search;// 搜索
    private LRecyclerView mRecyclerView = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.fragment_home, container,
                false);
        initView(rootView);// 初始化控件
        //init data
        ArrayList<ItemModel> dataList = new ArrayList<>();
        mHandler = new PreviewHandler(mContext);
        mDataAdapter = new DataAdapter(mContext);
        mDataAdapter.addAll(dataList);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mDataAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        RecyclerViewUtils.setHeaderView(mRecyclerView, new SampleHeader(mContext));
        setViewListen();// 设置监听
        mRecyclerView.setRefreshing(true);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void notifyDataSetChanged() {
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(ArrayList<ItemModel> list) {
        mDataAdapter.addAll(list);
        mCurrentCounter += list.size();

    }

    private class PreviewHandler extends Handler {

        private WeakReference<Context> ref;

        PreviewHandler(Context context) {
            ref = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            final MainActivity activity = (MainActivity) ref.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            switch (msg.what) {
                case -1:
                    if (isRefresh) {
                        mDataAdapter.clear();
                        mCurrentCounter = 0;
                    }
                    int currentSize = mDataAdapter.getItemCount();
                    //模拟组装几个数据
                    ArrayList<ItemModel> newList = new ArrayList<>();
                    for (int i = 0; i < REQUEST_COUNT; i++) {
                        if (newList.size() + currentSize >= TOTAL_COUNTER) {
                            break;
                        }
                        ItemModel item = new ItemModel();
                        item.id = currentSize + i;
                        item.title = "item" + (item.id);
                        newList.add(item);
                    }
                    addItems(newList);
                    if (isRefresh) {
                        isRefresh = false;
                        mRecyclerView.refreshComplete();
                        notifyDataSetChanged();
                    } else {
                        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter
                                .State.Normal);
                    }
                    break;
                case -2:
                    notifyDataSetChanged();
                    break;
                case -3:
                    if (isRefresh) {
                        isRefresh = false;
                        mRecyclerView.refreshComplete();
                        notifyDataSetChanged();
                    } else {
                        RecyclerViewStateUtils.setFooterViewState(activity, mRecyclerView,
                                REQUEST_COUNT, LoadingFooter.State.NetWorkError, new View
                                        .OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        RecyclerViewStateUtils.setFooterViewState(getActivity(),
                                                mRecyclerView,
                                                REQUEST_COUNT, LoadingFooter.State.Loading, null);
                                        requestData();
                                    }
                                });
                    }
                    break;
                case -4:
                    int index = mDataAdapter.getDataList().size();
                    mDataAdapter.getDataList().remove(0);
                    mDataAdapter.getDataList().remove(1);
                    mDataAdapter.notifyItemRangeRemoved(0, 2);
                    break;
                default:
                    break;
            }
        }
    }

    int num = 0;

    /**
     * 模拟请求网络
     */
    private void requestData() {
        Log.d(Constant.TAG, "requestData()执行了");
        new Thread() {
            @Override
            public void run() {
                super.run();
                num = 0;// 此处必须清零
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                // 限时特卖
                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST,
                        HomeConstant.HOME_XSTM, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        XianshitemaiBean xianshitemaiBean = GsonUtil.parseJsonWithGson(jsonObject
                                        .toString(),
                                XianshitemaiBean.class);
                        xstmBeanList = xianshitemaiBean.getPage().getList();
                        mDataAdapter.setXstmBeanList(xstmBeanList);
                        num++;
                        if (num == 2) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        xstmBeanList = new ArrayList<>();
                        mDataAdapter.setXstmBeanList(xstmBeanList);
                        num++;
                        if (num == 2) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                });
                // 火力拼团
                JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST,
                        HomeConstant.HOME_HLPT, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        HuolipintuanBean huolipintuanBean = GsonUtil.parseJsonWithGson(jsonObject
                                        .toString(),
                                HuolipintuanBean.class);
                        hlptBeanList = huolipintuanBean.getPage().getList();
                        mDataAdapter.setHlptBeanList(hlptBeanList);
                        num++;
                        if (num == 2) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        hlptBeanList = new ArrayList<>();
                        mDataAdapter.setHlptBeanList(hlptBeanList);
                        num++;
                        if (num == 2) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                });
                requestQueue.add(jsonObjectRequest1);
                requestQueue.add(jsonObjectRequest2);
            }
        }.start();
    }

    /**
     * 初始化各种控件
     *
     * @param view
     */
    private void initView(View view) {
        title_search = (ImageView) view.findViewById(R.id.title_home_left);
        mRecyclerView = (LRecyclerView) view.findViewById(R.id.mLRecyclerView);
    }

    /**
     * 设置各种监听事件
     */
    private void setViewListen() {
        /**
         * 这是mRecyclerView的滑动监听
         */
        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State
                        .Normal);
                mDataAdapter.clear();
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
                        (mRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    Log.d(Constant.TAG, "the state is Loading, just wait..");
                    return;
                }
                if (mCurrentCounter < TOTAL_COUNTER) {
                    // loading more
                    RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView,
                            REQUEST_COUNT, LoadingFooter.State.Loading, null);
                    requestData();
                } else {
                    //the end
                    RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView,
                            REQUEST_COUNT, LoadingFooter.State.TheEnd, null);
                }
            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {
            }
        });

        /**
         * 设置mLRecyclerViewAdapter的item点击事件
         */
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ItemModel item = mDataAdapter.getDataList().get(position);
                AppToast.getInstance().showShort(item.title);

            }

            @Override
            public void onItemLongClick(View view, int position) {
                ItemModel item = mDataAdapter.getDataList().get(position);

                AppToast.getInstance().showShort("onItemLongClick - " + item.title);

            }
        });

        title_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
