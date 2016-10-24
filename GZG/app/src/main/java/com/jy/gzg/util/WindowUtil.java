package com.jy.gzg.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.jy.gzg.cachemanager.SystemBarTintManager;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class WindowUtil {
    /**
     * 设置一体化状态栏颜色
     *
     * @param activity
     * @param color    状态栏颜色
     *                 4.4及以上版本开启
     */
    public static void setStatusBarTint(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarAlpha(0);
        tintManager.setStatusBarTintEnabled(true);// 开启状态栏
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setStatusBarTintColor(color);// 导航栏染色
    }

    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
