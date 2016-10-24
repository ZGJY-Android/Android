package com.jy.gzg.util;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.jy.gzg.R;
import com.jy.gzg.volley.BitmapCache;


public class CustomImageLoader {

    public static CustomImageLoader sInstance = null;
    private RequestQueue mQueue;

    public static CustomImageLoader share() {
        if (sInstance == null) {
            sInstance = new CustomImageLoader();
        }
        return sInstance;
    }

    public void reqImageLoader(Context context, ImageView imageView, String imgUrl) {

        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(context);
        }
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.mipmap
                .icon_stub, R.mipmap.icon_error);

        imageLoader.get(imgUrl, listener);
    }

}
