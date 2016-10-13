package com.zgjy.gzg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Allen on 2016/2/2.
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = createView(inflater, container, savedInstanceState);

        init();
        return view;
    }

    public abstract void init();



    public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}
