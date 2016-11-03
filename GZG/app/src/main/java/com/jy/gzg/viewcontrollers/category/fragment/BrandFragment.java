package com.jy.gzg.viewcontrollers.category.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.gzg.R;

/**
 * Created by YX on 2016/11/1 0001.
 */
public class BrandFragment extends Fragment {
    public static final String ARGS_BRAND = "args_brand";
    private int mBrand;

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

        return view;
    }
}
