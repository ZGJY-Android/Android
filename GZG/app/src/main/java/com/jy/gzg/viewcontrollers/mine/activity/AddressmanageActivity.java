package com.jy.gzg.viewcontrollers.mine.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.Closeable;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnSwipeMenuItemClickListener;
import com.github.jdsjlzx.interfaces.SwipeMenuCreator;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.swipe.SwipeMenu;
import com.github.jdsjlzx.swipe.SwipeMenuItem;
import com.jy.gzg.R;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.viewcontrollers.home.bean.ItemModelBean;
import com.jy.gzg.viewcontrollers.mine.adapter.AddressmanageAdapter;

import java.util.ArrayList;

public class AddressmanageActivity extends AppCompatActivity {
    private Activity mContext;
    private LRecyclerView mLRecyclerView;
    private AddressmanageAdapter addressmanageAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private ArrayList<ItemModelBean> dataList;

    private ImageView iv_return;
    private TextView tv_info;// 没有收藏时的提示信息

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressmanage);
        mContext = this;
        initViews();
        //init data
        dataList = new ArrayList<>();
        ItemModelBean itemModel;
        for (int i = 0; i < 5; i++) {
            itemModel = new ItemModelBean();
            itemModel.title = "item" + i;
            dataList.add(itemModel);
        }

        addressmanageAdapter = new AddressmanageAdapter();
        addressmanageAdapter.setDataList(dataList);

        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        mLRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        mLRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        mLRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mLRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        // 添加分割线。
        // mLRecyclerView.addItemDecoration(new ListViewDecoration(AddressmanageActivity.this));

        mLRecyclerViewAdapter = new LRecyclerViewAdapter(this, addressmanageAdapter);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);// 是否下拉刷新
        setViewsListen();
    }

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            // 得到指定postition的item
            // int size = mLRecyclerView.getLayoutManager().getChildAt(0).getHeight();
            int size = getResources().getDimensionPixelSize(R.dimen.addressmanage_item_height);
            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            {
//                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
//                        .setImage(R.mipmap.ic_launcher) // 图标。
//                        .setWidth(size) // 宽度。
//                        .setHeight(size); // 高度。
//                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。
            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem defaultItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_huise)
                        //.setImage(R.mipmap.ic_launcher)
                        .setText("设为默认") // 文字，还可以设置文字颜色，大小等。
                        .setTextColor(Color.BLACK)
                        .setTextSize(15)
                        .setWidth(size)
                        .setHeight(size);
                swipeRightMenu.addMenuItem(defaultItem);// 添加‘设为默认’按钮到右侧侧菜单。

                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        //.setImage(R.mipmap.ic_launcher)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setTextSize(15)
                        .setWidth(size)
                        .setHeight(size);
                swipeRightMenu.addMenuItem(deleteItem);// 添加‘删除’按钮到右侧侧菜单。
            }
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener
            () {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition.
         *                        这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction
         * 如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView
         * #RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int
                direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == LRecyclerView.RIGHT_DIRECTION) {
//                Toast.makeText(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition,
//                        Toast.LENGTH_SHORT).show();
                if (menuPosition == 1) {
                    // 右边的删除按钮
                    addressmanageAdapter.remove(adapterPosition);
                    mLRecyclerViewAdapter.notifyDataSetChanged();
                    if (addressmanageAdapter.getItemCount() == 0) {
                        tv_info.setVisibility(View.VISIBLE);
                    }
                }
            } else if (direction == LRecyclerView.LEFT_DIRECTION) {
//                Toast.makeText(mContext, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition,
//                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    /**
     * 初始化控件对象
     */
    private void initViews() {
        mLRecyclerView = (LRecyclerView) findViewById(R.id.mLRecyclerView);
        iv_return = (ImageView) findViewById(R.id.iv_return);
        tv_info = (TextView) findViewById(R.id.tv_info);
    }

    /**
     * 设置控件的监听事件
     */
    private void setViewsListen() {
        /**
         * LRecyclerViewAdapter的item点击事件
         */
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String text = addressmanageAdapter.getDataList().get(position).title;
                AppToast.getInstance().showShort(text);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
