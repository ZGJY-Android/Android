package com.jy.gzg.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jy.gzg.R;
import com.jy.gzg.fragment.CountryFragment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by YX on 2016/11/21 0021.
 */
public class CountryPagerAdapter extends FragmentPagerAdapter {
    public final int COUNT = 8;
    private String[] titles = new String[]{"母婴用品", "洗护用品", "洗护用品", "洗护用品", "洗护用品", "洗护用品", "洗护用品", "洗护用品"};
    private Context context;
    private ArrayList mDatas;

    public CountryPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                mDatas = new ArrayList<>(Arrays.asList(R.mipmap.sy_hlpic2,
                        R.mipmap.sy_hlpic3, R.mipmap.sy_hlpic1, R.mipmap.sy_hlpic2, R.mipmap.sy_hlpic3,
                        R.mipmap.sy_hlpic1, R.mipmap.sy_hlpic2, R.mipmap.sy_hlpic3, R.mipmap.sy_xspic2,
                        R.mipmap.sy_xspic3, R.mipmap.sy_xspic4, R.mipmap.sy_xspic2, R.mipmap.sy_xspic3, R.mipmap.sy_xspic4));
                break;
            case 1:
                mDatas = new ArrayList<>(Arrays.asList(R.mipmap.sy_hlpic1, R.mipmap.sy_hlpic2));
                break;
            case 2:
                mDatas = new ArrayList<>(Arrays.asList(R.mipmap.sy_hlpic1));
                break;
            default:
                mDatas = new ArrayList<>(Arrays.asList(R.mipmap.sy_hlpic2,
                        R.mipmap.sy_hlpic3, R.mipmap.sy_hlpic1, R.mipmap.sy_hlpic2));
        }
        return CountryFragment.newInstance(position, mDatas);
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
