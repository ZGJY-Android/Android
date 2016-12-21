package com.jy.gzg.viewcontrollers.category.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.jy.gzg.R;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.viewcontrollers.category.adapter.CategoryListAdapter;
import com.jy.gzg.viewcontrollers.category.bean.CategoryListBean;
import com.jy.gzg.volley.VolleyListenerInterface;
import com.jy.gzg.volley.VolleyRequestUtil;
import com.jy.gzg.widget.AppConstant;

import java.util.List;

import static com.jy.gzg.volley.VolleyRequestUtil.context;

public class PageFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;

    private List<CategoryListBean.ListBean> listBean;
    private ListView listView;
    private CategoryListAdapter listAdapter;
    private WaresItemFragment waresItemFragment;
    public static int mPosition;


    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
        Log.i("page---------------", mPage + "");


    }

    private void initData() {
        VolleyRequestUtil.RequestPost(getActivity(), AppConstant.CATEGORY_LIST, "CATEGORY_LIST",
                new VolleyListenerInterface(context,VolleyListenerInterface.mListener,VolleyListenerInterface.mErrorListener) {
                    @Override
            public void onMySuccess(String result) {
                CategoryListBean categoryListBean = GsonUtil.parseJsonWithGson(result,CategoryListBean.class);
                        listBean = categoryListBean.getList();

                listAdapter = new CategoryListAdapter(getActivity());
                        listAdapter.setListData(listBean);
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //拿到当前位置
                        mPosition = i;
                        //即时刷新adapter
                        listAdapter.notifyDataSetChanged();
                            waresItemFragment = new WaresItemFragment();
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_category, waresItemFragment);
                            Bundle waresBundle = new Bundle();
                            waresBundle.putInt(WaresItemFragment.TAG, listBean.get(i).getId());
                            waresItemFragment.setArguments(waresBundle);
                            fragmentTransaction.commit();
                    }
                });
                        //创建WaresItemFragment对象
                        waresItemFragment = new WaresItemFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_category, waresItemFragment);
                        // 给右边Fragment传数据，点击那个item就显示对应的信息
                        Bundle waresBundle = new Bundle();
                        waresBundle.putInt(WaresItemFragment.TAG, listBean.get(mPosition).getId());
                        waresItemFragment.setArguments(waresBundle);
                        fragmentTransaction.commit();
            }
            @Override
            public void onMyError(VolleyError error) {
                AppToast.getInstance().showShort("网络加载错误~");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.viewpager_category, container, false);
        listView =  (ListView) view.findViewById(R.id.lv_category);
        //获取分类数据
        initData();
        return view;
    }
}