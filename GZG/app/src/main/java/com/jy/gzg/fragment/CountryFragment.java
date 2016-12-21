package com.jy.gzg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.jy.gzg.R;
import com.jy.gzg.activity.ProductdetailsActivity;
import com.jy.gzg.adapter.CountryVerticalAdapter;
import com.jy.gzg.bean.CountryMYBean;
import com.jy.gzg.bean.ProductBean;
import com.jy.gzg.util.AppLog;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.volley.VolleyListenerInterface;
import com.jy.gzg.volley.VolleyRequestUtil;
import com.jy.gzg.widget.AppConstant;

import java.util.ArrayList;

import static com.jy.gzg.volley.VolleyRequestUtil.context;


/**
 * Created by YX on 2016/11/21 0021.
 */
public class CountryFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    public static final String ARGS_TAGID = "args_tagid";
    private int mPage;
    private RecyclerView vRecyclerView;
    private ArrayList<ProductBean> mDatas;
    private String MYURL = null;
    private CountryVerticalAdapter countryVerticalAdapter;
    //区分首页八个按钮的ID
    private String tagId;

    public static CountryFragment newInstance(int page, String tagId) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        args.putString(ARGS_TAGID, tagId);
        CountryFragment fragment = new CountryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
        tagId = getArguments().getString(ARGS_TAGID);
        AppLog.i("zzzzzzzzzzzzz",tagId);
    }

    private void initData() {
        MYURL = AppConstant.COUNTRY_DETAILS + "tagIds=" + tagId +
                "&&productCategoryId="
                + mPage;
        AppLog.i("YYYYYYYYYY",MYURL);
        VolleyRequestUtil.RequestPost(getActivity(), MYURL, mPage + "", new
                VolleyListenerInterface(context, VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        CountryMYBean countryMYBean = GsonUtil.parseJsonWithGson(result,
                                CountryMYBean
                                        .class);
                        mDatas = countryMYBean.getPage().getList();
                        //设置适配器
                        countryVerticalAdapter = new CountryVerticalAdapter(getActivity());
                        countryVerticalAdapter.setmData(mDatas);
                        countryVerticalAdapter.setOnItemClickListener(new CountryVerticalAdapter
                                .OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getActivity(),
                                        ProductdetailsActivity
                                                .class);
                                intent.putExtra("product_id", mDatas.get(position).getId() + "");
                                startActivity(intent);

                            }
                        });
                        vRecyclerView.setAdapter(countryVerticalAdapter);
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        AppLog.i("CountryFragment", error);
                        AppToast.getInstance().showShort("网络加载错误~");
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //设置商品数据
        initData();
        //初始化view
        final View view = inflater.inflate(R.layout.viewpager_country, container, false);
        vRecyclerView = (RecyclerView) view.findViewById(R.id.rv_country_vertical);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        vRecyclerView.setLayoutManager(gridLayoutManager);
        return view;
    }
}
