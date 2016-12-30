package com.jy.gzg.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class RecyclerViewUtil {
    /**
     * 动态设置recycleview的高度
     *
     * @param recyclerView
     */
    public static void setRecycleViewHeightBasedOnChildren(RecyclerView recyclerView) {
        if (recyclerView == null) return;
        int childSize = recyclerView.getChildCount() - 1;// 减去1的目的是因为我再删除后才调用的它
        int maxHeight = 0;
        int maxWidth = 0;
        // 遍历RecyclerView的每个childView,找出高度最大的那个childView的高度，并把这个高度设置为RecyclerView的高度。
        for (int i = 0; i < childSize; i++) {
            View child = recyclerView.getChildAt(i);
            maxHeight = maxHeight + child.getMeasuredHeight();
            maxWidth = child.getWidth();
        }
        ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
        params.height = maxHeight;
        params.width = maxWidth;
        recyclerView.setLayoutParams(params);
    }
}
