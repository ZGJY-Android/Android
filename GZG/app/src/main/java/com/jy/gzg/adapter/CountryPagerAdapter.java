package com.jy.gzg.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jy.gzg.fragment.CountryFragment;

/**
 * Created by YX on 2016/11/21 0021.
 */
public class CountryPagerAdapter extends FragmentPagerAdapter {
    public final int COUNT = 8;
    private String[] titles = new String[]{"母婴用品", "洗护用品", "洗护用品", "洗护用品", "洗护用品", "洗护用品",
            "洗护用品", "洗护用品"};
    private Context context;
    //区分首页八个按钮的ID
    private String tagId;

    public CountryPagerAdapter(FragmentManager fm, Context context,String tagId) {
        super(fm);
        this.context = context;
        this.tagId = tagId;
    }

    @Override
    public Fragment getItem(int position) {
        return CountryFragment.newInstance(position,tagId);
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
