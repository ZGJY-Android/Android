package com.jy.gzg.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/25 0025.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private ArrayList<Fragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] titles, ArrayList<Fragment>
            fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
