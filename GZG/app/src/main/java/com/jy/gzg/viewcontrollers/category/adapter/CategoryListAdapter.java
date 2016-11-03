package com.jy.gzg.viewcontrollers.category.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.category.fragment.PageFragment;

/**
 * Created by YX on 2016/10/31 0031.
 */
public class CategoryListAdapter extends BaseAdapter {
    private Context context;
    private String[] strings;
    public static int mPosition;

    public CategoryListAdapter(Context context, String[] strings) {
        this.context = context;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int i) {
        return strings[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.listview_item_category,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.tv_item_category);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        mPosition = i;
        viewHolder.textView.setText(strings[i]);
        if (i == PageFragment.mPosition){
            view.setBackgroundResource(R.color.categoryBg);
        }else {
            view.setBackgroundResource(R.color.white);
        }
        return view;
    }

    static class ViewHolder{
        TextView textView;
    }
}
