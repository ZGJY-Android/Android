package com.jy.gzg.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.jdsjlzx.interfaces.Closeable;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnSwipeMenuItemClickListener;
import com.github.jdsjlzx.interfaces.SwipeMenuCreator;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.swipe.SwipeMenu;
import com.github.jdsjlzx.swipe.SwipeMenuItem;
import com.jy.gzg.R;
import com.jy.gzg.activity.ProductdetailsActivity;
import com.jy.gzg.bean.HomeProductBean;
import com.jy.gzg.ui.CustomGridLayoutManager;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.util.RecyclerViewUtil;
import com.jy.gzg.util.ZYWUtil;
import com.jy.gzg.viewcontrollers.cart.adapter.CartFragmentBottomAdapter;
import com.jy.gzg.viewcontrollers.cart.adapter.CartFragmentTopAdapter;
import com.jy.gzg.viewcontrollers.cart.bean.CartBean;
import com.jy.gzg.viewcontrollers.cart.bean.CartListBean;
import com.jy.gzg.widget.AppConstant;
import com.jy.gzg.widget.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Allen on 15/12/27.
 */
public class CartFragment extends Fragment {
    private Context mContext;
    private LRecyclerView mLRecyclerView;
    private RecyclerView mRecyclerView;
    private TextView tv_info,// 没有收藏时的提示信息
            tv_money,// 应付总钱数
            tv_count;// 商品总数
    private CheckBox ck_selectall;// 全选
    private Button btn_settlement;// 去结算
    private CartFragmentTopAdapter cartFragmentTopAdapter;
    private CartFragmentBottomAdapter cartFragmentBottomAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private ArrayList<CartBean> dataList;// 购物车中已经添加的数据
    private boolean isHidden = true;// 当前fragment是否可见，因为切换用户时需要刷新数据

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (isHidden) {
            //init data
            ZYWUtil.init(mContext);
            final String uid = ZYWUtil.readData(Constant.SP_FILE, Constant.SP_UID);
            if (uid != null && !uid.equals("")) {
//                // 这是看效果的测试数据
//                dataList.clear();
//                CartBean cartBean;
//                for (int i = 0; i < 10; i++) {
//                    cartBean = new CartBean("", "aaaaa", 12, 12);
//                    dataList.add(cartBean);
//                }
//                mHandler.sendEmptyMessage(1);
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstant
                        .CART_LIST, new Response
                        .Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        CartListBean cartListBean = GsonUtil.parseJsonWithGson(s, CartListBean
                                .class);
                        dataList = cartListBean.getCart();
                        mHandler.sendEmptyMessage(1);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        AppToast.getInstance().showShort("网络连接失败");
                        mHandler.sendEmptyMessage(1);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("memberId", uid);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
            isHidden = false;
        } else {
            isHidden = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.fragment_cart, container,
                false);
        initViews(rootView);
        initDatas();
        onHiddenChanged(isHidden);
        // -----------------------这是下边的推荐商品-----------------------
        // 网格布局管理器，且该recycleview纵向有2个item
        CustomGridLayoutManager gridLayoutManager = new CustomGridLayoutManager(mContext, 2);
        gridLayoutManager.setScrollEnabled(false);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        List<HomeProductBean> mDatas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            HomeProductBean homeProductBean = new HomeProductBean(12, "", 12, "sdsfdsf",
                    "asda", 14);
            mDatas.add(homeProductBean);
        }
        cartFragmentBottomAdapter = new CartFragmentBottomAdapter(mContext, mDatas);
        mRecyclerView.setAdapter(cartFragmentBottomAdapter);

        setViewsListen();
        return rootView;
    }

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            // 得到指定postition的item
            // int size = mLRecyclerView.getLayoutManager().getChildAt(0).getHeight();
            int size = getResources().getDimensionPixelSize(R.dimen.addressmanage_item_height);
            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            {
            }
            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem defaultItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_huise)
                        //.setImage(R.mipmap.ic_launcher)
                        .setText("收藏") // 文字，还可以设置文字颜色，大小等。
                        .setTextColor(Color.WHITE)
                        .setTextSize(15)
                        .setWidth(size)
                        .setHeight(size);
                swipeRightMenu.addMenuItem(defaultItem);// 添加‘设为默认’按钮到右侧侧菜单。

                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        //.setImage(R.mipmap.ic_launcher)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setTextSize(15)
                        .setWidth(size)
                        .setHeight(size);
                swipeRightMenu.addMenuItem(deleteItem);// 添加‘删除’按钮到右侧侧菜单。
            }
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener
            () {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int
                direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
            if (direction == LRecyclerView.RIGHT_DIRECTION) {
                if (menuPosition == 1) {
                    // 右边的删除按钮
                    cartFragmentTopAdapter.remove(adapterPosition);
                    mLRecyclerViewAdapter.notifyDataSetChanged();
                    // 下边需要动态设置recycleview的高度
                    RecyclerViewUtil.setRecycleViewHeightBasedOnChildren(mLRecyclerView);
                    if (cartFragmentTopAdapter.getItemCount() == 0) {
                        tv_info.setVisibility(View.VISIBLE);
                    }
                }
            } else if (direction == LRecyclerView.LEFT_DIRECTION) {
            }
        }
    };

    /**
     * 初始化控件对象
     */
    private void initViews(View view) {
        mLRecyclerView = (LRecyclerView) view.findViewById(R.id.mLRecyclerView);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        tv_info = (TextView) view.findViewById(R.id.tv_info);
        tv_money = (TextView) view.findViewById(R.id.tv_money);
        tv_count = (TextView) view.findViewById(R.id.tv_count);
        ck_selectall = (CheckBox) view.findViewById(R.id.ck_selectall);
        btn_settlement = (Button) view.findViewById(R.id.btn_settlement);
    }

    /**
     * 初始化相关变量
     */
    private void initDatas() {
        cartFragmentTopAdapter = new CartFragmentTopAdapter();
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext,
                cartFragmentTopAdapter);
        dataList = new ArrayList<>();
    }

    /**
     * 设置控件的监听事件
     */
    private void setViewsListen() {
        /**
         * LRecyclerViewAdapter的item点击事件
         */
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext,
                        ProductdetailsActivity
                                .class);
                intent.putExtra("product_id", dataList.get(position).getId() + "");
                mContext.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        cartFragmentTopAdapter.setOnCheckChangeListener(new CartFragmentTopAdapter.CallBack() {
            @Override
            public void onMoneyChage() {
                tv_money.setText("￥" + cartFragmentTopAdapter.getMoney());
                tv_count.setText(cartFragmentTopAdapter.getCount() + "");
            }
        });

        /**
         * 是否全选
         */
        ck_selectall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<Integer> checkPosition = cartFragmentTopAdapter.getCheckPosition();
                if (isChecked) {
                    // 全选
                    // 遍历dataList的长度，将MyAdapter中的map值全部设为true
                    checkPosition.clear();
                    for (int i = 0; i < dataList.size(); i++) {
                        checkPosition.add(i);
                    }
                    cartFragmentTopAdapter.setCheckPosition(checkPosition);
                } else {
                    // 全不选
                    checkPosition.clear();
                    cartFragmentTopAdapter.setCheckPosition(checkPosition);
                }
                mLRecyclerViewAdapter.notifyDataSetChanged();
                tv_money.setText("￥" + cartFragmentTopAdapter.getMoney());
                tv_count.setText(cartFragmentTopAdapter.getCount() + "");
            }
        });

        /**
         * 去结算
         */
        btn_settlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    cartFragmentTopAdapter.setDataList(dataList);
                    if (cartFragmentTopAdapter.getItemCount() == 0) {
                        tv_info.setVisibility(View.VISIBLE);
                    } else
                        tv_info.setVisibility(View.GONE);
                    // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
                    // 设置菜单创建器。
                    mLRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
                    // 设置菜单Item点击监听。
                    mLRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
                    mLRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));// 布局管理器。
                    mLRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
                    mLRecyclerView.setItemAnimator(new DefaultItemAnimator());//
                    // 设置Item默认动画，加也行，不加也行。
                    mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
                    mLRecyclerView.setPullRefreshEnabled(false);// 是否下拉刷新
                    break;
            }
            return true;
        }
    });

}
