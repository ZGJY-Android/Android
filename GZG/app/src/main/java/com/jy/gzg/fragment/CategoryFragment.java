package com.jy.gzg.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.category.adapter.CategoryPagerAdapter;


/**
 * Created by Allen on 15/12/27.
 */
public class CategoryFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    public Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_category,
                container, false);

        mViewPager = (ViewPager) rootView.findViewById(R.id.vp_category);
        CategoryPagerAdapter categoryPagerAdapter = new CategoryPagerAdapter(getFragmentManager(),context);
        mViewPager.setAdapter(categoryPagerAdapter);

        mTabLayout = (TabLayout) rootView.findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mViewPager);

        return rootView;
    }


}
