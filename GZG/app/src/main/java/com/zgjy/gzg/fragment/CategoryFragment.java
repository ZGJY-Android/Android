package com.zgjy.gzg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zgjy.gzg.R;


/**
 * Created by Allen on 15/12/27.
 */
public class CategoryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_category,
                container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.title_left);

/*        //设置背景
//        imageView.setBackgroundResource(R.mipmap.b);

        //类似设置src
        imageView.setImageResource(R.mipmap.b);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        TextView textView = (TextView) view.findViewById(R.id.title_text);
        textView.setText("分类");

        ImageView imageView1 = (ImageView) view.findViewById(R.id.title_right);

        imageView1.setBackgroundResource(R.mipmap.g);*/

        return view;
    }
}
