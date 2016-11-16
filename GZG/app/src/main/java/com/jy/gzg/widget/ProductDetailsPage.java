package com.jy.gzg.widget;

import android.content.Context;
import android.view.View;
import android.widget.ScrollView;

import com.jy.gzg.R;
import com.jy.gzg.ui.SnapPageLayout;

public class ProductDetailsPage implements SnapPageLayout.GavinSnapPage {

    private Context context;
    private View rootView = null;
    private ScrollView mScollView;

    public ProductDetailsPage(Context context, View rootView) {
        this.context = context;
        this.rootView = rootView;
        mScollView = (ScrollView) this.rootView.findViewById(R.id.mScollView);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public boolean isAtTop() {
        int scrollY = mScollView.getScrollY();
        if (scrollY == 0) {
            // 到顶部了
            return true;
        }
        return false;
    }

    @Override
    public boolean isAtBottom() {
        return false;
    }

}
