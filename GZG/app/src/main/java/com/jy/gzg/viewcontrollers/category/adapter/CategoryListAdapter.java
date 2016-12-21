package com.jy.gzg.viewcontrollers.category.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.category.bean.CategoryListBean;
import com.jy.gzg.viewcontrollers.category.fragment.PageFragment;

import java.util.List;

/**
 * Created by YX on 2016/10/31 0031.
 */
public class CategoryListAdapter extends BaseAdapter {
    private Context context;
    private List<CategoryListBean.ListBean> listBeen = null;
    public static int mPosition;

    public CategoryListAdapter(Context context) {
        this.context = context;
    }

    public void setListData (List<CategoryListBean.ListBean> listBeen){
        this.listBeen = listBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public Object getItem(int i) {
        return listBeen.get(i);
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
        viewHolder.textView.setText(listBeen.get(i).getName());
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
