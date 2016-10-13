package com.zgjy.gzg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zgjy.gzg.R;


/**
 * Created by Allen on 15/12/27.
 */
public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("allen", "MineFragment------onCreateView");

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_mine,container,false);
        Log.d("allen","MineFragment------rootView==null");

        return view;
    }
}
