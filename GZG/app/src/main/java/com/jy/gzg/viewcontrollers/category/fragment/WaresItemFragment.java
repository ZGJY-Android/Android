package com.jy.gzg.viewcontrollers.category.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.jy.gzg.R;
import com.jy.gzg.activity.SearchResultActivity;
import com.jy.gzg.util.AppLog;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.viewcontrollers.category.adapter.MyAdapter;
import com.jy.gzg.viewcontrollers.category.bean.ReclassifyBean;
import com.jy.gzg.volley.VolleyListenerInterface;
import com.jy.gzg.volley.VolleyRequestUtil;
import com.jy.gzg.widget.AppConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YX on 2016/10/31 0031.
 */
public class WaresItemFragment extends Fragment {
    public static final String TAG = "WaresItemFragment";
    private int id;
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_category, null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_category);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        //添加之定义分割线
        //mRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));

        id = getArguments().getInt(TAG, -1);
        AppLog.i("----------w-----------", id);

        Map<String, String> wareMap = new HashMap<>();
        wareMap.put("parentId", id + "");
        //加载网络请求
        VolleyRequestUtil.RequestPost(getActivity(), AppConstant.CATEGORY_WARES, "CATEGORY_WARES",
                wareMap, new VolleyListenerInterface(context, VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        ReclassifyBean categoryWaresBean = GsonUtil.parseJsonWithGson(result,
                                ReclassifyBean.class);
                        final List<ReclassifyBean.ListBean> waresBean = categoryWaresBean.getList();

                        myAdapter = new MyAdapter(getActivity());
                        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position, String data) {
                                //商品id
                                int wareID = waresBean.get(position).getId();
                                AppToast.getInstance().showShort("分类商品" + wareID);
                                Intent intent = new Intent(context, SearchResultActivity.class);
                                intent.putExtra("ware_Id", wareID + "");
                                context.startActivity(intent);
                            }
                        });
                        mRecyclerView.setAdapter(myAdapter);
                        setHeader(mRecyclerView);
                        myAdapter.addDatas(waresBean);
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        AppToast.getInstance().showShort("网络加载错误~");
                    }
                });

        return view;
    }

    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.recy_wares_header,
                view, false);
        myAdapter.setHeaderView(header);
    }
}
