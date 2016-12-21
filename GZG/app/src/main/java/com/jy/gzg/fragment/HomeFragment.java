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
import com.jy.gzg.bean.HomeBean;
import com.jy.gzg.bean.HomeProductBean;
import com.jy.gzg.widget.Constant;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.viewcontrollers.home.adapter.DataAdapter;
import com.jy.gzg.viewcontrollers.home.bean.ItemModelBean;
import com.jy.gzg.widget.AppConstant;
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
    private static final int TOTAL_COUNTER = 6;
    // 每一页展示多少条数据
    private static final int REQUEST_COUNT = 6;
    // 已经获取到多少条数据了
    private static int mCurrentCounter = 0;
    private DataAdapter mDataAdapter = null;
    private PreviewHandler mHandler = null;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private boolean isRefresh = false;
    private List<HomeProductBean> xstmBeanList,// 限时特卖的相关数据
            hlptBeanList,// 火力拼团的相关数据
            myzxBeanList,// 母婴专场的相关数据
            hfmzBeanList,// 护肤美妆的相关数据
            jkbjBeanList,// 健康保健的相关数据
            jjryBeanList;// 居家日用的相关数据

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
        ArrayList<ItemModelBean> dataList = new ArrayList<>();
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

    private void addItems(ArrayList<ItemModelBean> list) {
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
                    ArrayList<ItemModelBean> newList = new ArrayList<>();
                    for (int i = 0; i < REQUEST_COUNT; i++) {
                        if (newList.size() + currentSize >= TOTAL_COUNTER) {
                            break;
                        }
                        ItemModelBean item = new ItemModelBean();
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

    private int num = 0, count = 6;

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
                        AppConstant.HOME_XSTM, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        HomeBean xianshitemaiBean = GsonUtil.parseJsonWithGson(jsonObject
                                        .toString(),
                                HomeBean.class);
                        xstmBeanList = xianshitemaiBean.getList();
                        mDataAdapter.setXstmBeanList(xstmBeanList);
                        num++;
                        if (num == count) {
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
                        if (num == count) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                });
                // 火力拼团
                JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST,
                        AppConstant.HOME_HLPT, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        HomeBean huolipintuanBean = GsonUtil.parseJsonWithGson(jsonObject
                                        .toString(),
                                HomeBean.class);
                        hlptBeanList = huolipintuanBean.getList();
                        mDataAdapter.setHlptBeanList(hlptBeanList);
                        num++;
                        if (num == count) {
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
                        if (num == count) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                });
                // 母婴专场
                JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest(Request.Method.POST,
                        AppConstant.HOME_MYZX, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        HomeBean koreaBean = GsonUtil.parseJsonWithGson(jsonObject
                                        .toString(),
                                HomeBean.class);
                        myzxBeanList = koreaBean.getList();
                        mDataAdapter.setMyzcBeanList(myzxBeanList);
                        num++;
                        if (num == count) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        myzxBeanList = new ArrayList<>();
                        mDataAdapter.setMyzcBeanList(myzxBeanList);
                        num++;
                        if (num == count) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                });
                // 护肤美妆
                JsonObjectRequest jsonObjectRequest5 = new JsonObjectRequest(Request.Method.POST,
                        AppConstant.HOME_HFMZ, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        HomeBean japanBean = GsonUtil.parseJsonWithGson(jsonObject
                                        .toString(),
                                HomeBean.class);
                        hfmzBeanList = japanBean.getList();
                        mDataAdapter.setHfmzBeanList(hfmzBeanList);
                        num++;
                        if (num == count) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        hfmzBeanList = new ArrayList<>();
                        mDataAdapter.setHfmzBeanList(hfmzBeanList);
                        num++;
                        if (num == count) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                });
                // 健康保健
                JsonObjectRequest jsonObjectRequest6 = new JsonObjectRequest(Request.Method.POST,
                        AppConstant.HOME_JKBJ, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        HomeBean aussieBean = GsonUtil.parseJsonWithGson(jsonObject
                                        .toString(),
                                HomeBean.class);
                        jkbjBeanList = aussieBean.getList();
                        mDataAdapter.setJkbjBeanList(jkbjBeanList);
                        num++;
                        if (num == count) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        jkbjBeanList = new ArrayList<>();
                        mDataAdapter.setJkbjBeanList(jkbjBeanList);
                        num++;
                        if (num == count) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                });
                // 居家日用
                JsonObjectRequest jsonObjectRequest7 = new JsonObjectRequest(Request.Method.POST,
                        AppConstant.HOME_JJRY, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        HomeBean europeBean = GsonUtil.parseJsonWithGson(jsonObject
                                        .toString(),
                                HomeBean.class);
                        jjryBeanList = europeBean.getList();
                        mDataAdapter.setJjryBeanList(jjryBeanList);
                        num++;
                        if (num == count) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        jjryBeanList = new ArrayList<>();
                        mDataAdapter.setJjryBeanList(jjryBeanList);
                        num++;
                        if (num == count) {
                            mHandler.sendEmptyMessage(-1);
                        } else {
                            mHandler.sendEmptyMessage(-3);
                        }
                    }
                });
                requestQueue.add(jsonObjectRequest1);
                requestQueue.add(jsonObjectRequest2);
                // ....................
                requestQueue.add(jsonObjectRequest4);
                requestQueue.add(jsonObjectRequest5);
                requestQueue.add(jsonObjectRequest6);
                requestQueue.add(jsonObjectRequest7);
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
                ItemModelBean item = mDataAdapter.getDataList().get(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ItemModelBean item = mDataAdapter.getDataList().get(position);
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
