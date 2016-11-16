package com.jy.gzg.widget;


import android.content.Context;
import android.view.View;

import com.jy.gzg.R;
import com.jy.gzg.ui.MyScrollView;
import com.jy.gzg.ui.SnapPageLayout;

public class ProductInfoPage implements
        SnapPageLayout.GavinSnapPage {

    private Context context;

    private View rootView = null;
    private MyScrollView mScrollView = null;

    public ProductInfoPage(Context context, View rootView) {
        this.context = context;
        this.rootView = rootView;
        mScrollView = (MyScrollView) this.rootView
                .findViewById(R.id.product_scrollview);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public boolean isAtTop() {
        return true;
    }

    @Override
    public boolean isAtBottom() {
        int scrollY = mScrollView.getScrollY();
        int height = mScrollView.getHeight();
        int scrollViewMeasuredHeight = mScrollView.getChildAt(0)
                .getMeasuredHeight();

        if ((scrollY + height) >= scrollViewMeasuredHeight) {
            return true;
        }
        return false;
    }

}
