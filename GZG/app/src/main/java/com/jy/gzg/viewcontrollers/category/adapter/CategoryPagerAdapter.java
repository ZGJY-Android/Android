package com.jy.gzg.viewcontrollers.category.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jy.gzg.viewcontrollers.category.fragment.BrandFragment;
import com.jy.gzg.viewcontrollers.category.fragment.PageFragment;

/**
 * Created by YX on 2016/10/25 0025.
 */
public class CategoryPagerAdapter extends FragmentPagerAdapter {
    public final int COUNT = 2;
    private String[] titles = new String[]{"商品", "品牌"};
    private Context context;

    public CategoryPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return PageFragment.newInstance(position);
        } else {
            return BrandFragment.newInstance(position);
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
