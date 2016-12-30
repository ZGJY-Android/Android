package com.jy.gzg.viewcontrollers.cart.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.swipe.SwipeMenuAdapter;
import com.jy.gzg.R;
import com.jy.gzg.viewcontrollers.cart.bean.CartBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class CartFragmentTopAdapter extends SwipeMenuAdapter<CartFragmentTopAdapter
        .DefaultViewHolder> {
    private ImageLoader imageLoader;
    protected List<CartBean> mDataList = new ArrayList<>();
    protected List<Integer> checkPosition = new ArrayList<>();// 记录当前选中的checkbox
    protected double money;// 总钱数
    protected int count;// 商品总数量
    private CallBack callBack;// 回调接口

    public int getCount() {
        return count;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getMoney() {
        return money;
    }

    public List<Integer> getCheckPosition() {
        return checkPosition;
    }

    public void setCheckPosition(List<Integer> checkPosition) {
        this.checkPosition = checkPosition;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<CartBean> getDataList() {
        return mDataList;
    }

    public void setDataList(Collection<CartBean> list) {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(Collection<CartBean> list) {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    public void remove(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
        if (position != mDataList.size()) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, mDataList.size() - position);
        }
    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cartfragment,
                parent, false);
        imageLoader = ImageLoader.getInstance();
        return view;
    }

    @Override
    public CartFragmentTopAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView,
                                                                             int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(CartFragmentTopAdapter.DefaultViewHolder holder, int position) {
        if (position == 0) {
            money = 0;
            count = 0;
        }
        final DefaultViewHolder viewHolder = holder;
        final CartBean cartBean = mDataList.get(position);
        viewHolder.cb_select.setTag(new Integer(position));
        final int num = Integer.parseInt(viewHolder.tv_count.getText().toString());
        final double sum = num * cartBean.getPrice();
        if (checkPosition.contains(new Integer(position))) {
            viewHolder.cb_select.setChecked(true);
        } else {
            viewHolder.cb_select.setChecked(false);
            // 计算商品数量和总钱数
            money += sum;
            count += num;
        }
        imageLoader.displayImage(cartBean.getImage(), viewHolder.iv_img);
        viewHolder.tv_name.setText(cartBean.getName());
        viewHolder.tv_price.setText("￥" + cartBean.getPrice());
        viewHolder.cb_select.setOnCheckedChangeListener(new CompoundButton
                .OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkPosition.add((Integer) buttonView.getTag());
                    money += sum;
                    count += num;
                } else {
                    checkPosition.remove(buttonView.getTag());
                    money -= sum;
                    count -= num;
                }
                callBack.onMoneyChage();
            }
        });

    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_select;
        private ImageView iv_img;
        private TextView tv_name, tv_price, tv_reduce, tv_count, tv_add;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            cb_select = (CheckBox) itemView.findViewById(R.id.cb_select);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_reduce = (TextView) itemView.findViewById(R.id.tv_reduce);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            tv_add = (TextView) itemView.findViewById(R.id.tv_add);
            // 添加数量
            tv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = Integer.parseInt(tv_count.getText().toString());
                    num++;
                    tv_count.setText(num + "");
                }
            });

            // 减少数量
            tv_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = Integer.parseInt(tv_count.getText().toString());
                    if (num > 1) {
                        num--;
                    }
                    tv_count.setText(num + "");
                }
            });
        }
    }

    public void setOnCheckChangeListener(CartFragmentTopAdapter.CallBack callBack) {
        this.callBack = callBack;
    }

    public static interface CallBack {
        public void onMoneyChage();
    }
}
