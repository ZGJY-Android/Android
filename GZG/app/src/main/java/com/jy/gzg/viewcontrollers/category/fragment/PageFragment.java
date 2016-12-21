package com.jy.gzg.viewcontrollers.category.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.category.adapter.CategoryListAdapter;

public class PageFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;

    private String[] strs = {"常用分类", "服饰内衣", "鞋靴", "手机", "家用电器", "数码", "电脑办公",
            "个护化妆", "图书", "洗护用品", "锅碗瓢盆", "法师的法师的法师", "油盐酱醋", "健身器材"};
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_category, container, false);
        ListView listView = (ListView) view.findViewById(R.id.lv_category);
        listAdapter = new CategoryListAdapter(getActivity(), strs);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 拿到当前位置
                mPosition = i;
                // 即时刷新adapter
                listAdapter.notifyDataSetChanged();
                for (int x = 0; x < strs.length; x++) {
                    waresItemFragment = new WaresItemFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager()
                            .beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_category, waresItemFragment);
                    Bundle waresBundle = new Bundle();
                    waresBundle.putString(WaresItemFragment.TAG, strs[i]);
                    waresItemFragment.setArguments(waresBundle);
                    fragmentTransaction.commit();
                }
            }
        });

        //创建WaresItemFragment对象
        waresItemFragment = new WaresItemFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_category, waresItemFragment);
        Bundle waresBundle = new Bundle();
        waresBundle.putString(WaresItemFragment.TAG, strs[mPosition]);
        waresItemFragment.setArguments(waresBundle);
        fragmentTransaction.commit();

        return view;
    }
}