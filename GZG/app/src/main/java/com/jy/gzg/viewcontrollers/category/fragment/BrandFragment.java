package com.jy.gzg.viewcontrollers.category.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.jy.gzg.R;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.viewcontrollers.category.adapter.BrandAdapter;
import com.jy.gzg.viewcontrollers.category.bean.BrandBean;
import com.jy.gzg.volley.VolleyListenerInterface;
import com.jy.gzg.volley.VolleyRequestUtil;
import com.jy.gzg.widget.AppConstant;

import static com.jy.gzg.volley.VolleyRequestUtil.context;

/**
 * Created by YX on 2016/11/1 0001.
 */
public class BrandFragment extends Fragment {
    public static final String ARGS_BRAND = "args_brand";
    private int mBrand;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private BrandAdapter brandAdapter;


    public static BrandFragment newInstance(int brand){
        Bundle bundle = new Bundle();

        bundle.putInt(ARGS_BRAND,brand);
        BrandFragment brandFragment = new BrandFragment();
        brandFragment.setArguments(bundle);
        return brandFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBrand = getArguments().getInt(ARGS_BRAND);
        Log.i("mBrand---------------",mBrand+"");
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_brand,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_brand);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        //获取分类数据
        initData();

        return view;
    }

    private void initData() {
        VolleyRequestUtil.RequestPost(getActivity(), AppConstant.CATEGORY_BRAND, "CATEGORY_BRAND",
                new VolleyListenerInterface(context,VolleyListenerInterface.mListener,VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        BrandBean brandBean = GsonUtil.parseJsonWithGson(result,BrandBean.class);

                        brandAdapter = new BrandAdapter(getActivity());
                        brandAdapter.addDatas(brandBean.getList());
                        brandAdapter.setOnItemClickListener(new BrandAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                            }
                        });
                        mRecyclerView.setAdapter(brandAdapter);


                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        AppToast.getInstance().showShort("网络加载错误~");
                    }
                });
    }
}
