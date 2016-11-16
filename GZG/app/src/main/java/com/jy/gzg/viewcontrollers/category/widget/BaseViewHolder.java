package com.jy.gzg.viewcontrollers.category.widget;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by YX on 2016/11/7 0007.
 * RecyclerView通用ViewHolder
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    public BaseViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }
    //通过viewId获取控件
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = itemView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }
    /**
     * 设置TextView的值
     */
    public BaseViewHolder setText(int viewId,String text){
        TextView tv= getView(viewId);
        tv.setText(text);
        return this;
    }
    public BaseViewHolder setImageResource(int viewId,int resId){
        ImageView view= getView(viewId);
        view.setImageResource(resId);
        return this;
    }
    public BaseViewHolder setImageBitamp(int viewId,Bitmap bitmap){
        ImageView view= getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }
    public BaseViewHolder setImageURI(int viewId,String uri){
        ImageView view= getView(viewId);
//        Imageloader.getInstance().loadImg(view,uri);
        return this;
    }

}
