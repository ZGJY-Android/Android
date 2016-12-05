package com.jy.gzg.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class CustomViewPager extends ViewPager {

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childSize = getChildCount();
        int maxHeight = 0;
        int maxWidth = 0;
        // 遍历viewpager的每个childView,找出高度最大的那个childView的高度，并把这个高度设置为viewpager的高度。
        for (int i = 0; i < childSize; i++) {
            View child = getChildAt(i);
//            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec
//                    .UNSPECIFIED));
            child.measure(widthMeasureSpec, widthMeasureSpec);
            if (child.getMeasuredHeight() > maxHeight) {
                maxHeight = child.getMeasuredHeight();
            }
            if (child.getMeasuredWidth() > maxWidth) {
                maxWidth = child.getMeasuredWidth();
            }
        }
        if (maxWidth > 0) {
            setMeasuredDimension(maxWidth, maxWidth);
        }
    }
}
