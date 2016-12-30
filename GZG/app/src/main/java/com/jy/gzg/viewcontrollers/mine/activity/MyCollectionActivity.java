package com.jy.gzg.viewcontrollers.mine.activity;

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
import com.jy.gzg.viewcontrollers.mine.adapter.MyCollectionAdapter;
import com.jy.gzg.viewcontrollers.mine.bean.MyCollectionBean;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionActivity extends AppCompatActivity {
    private List<MyCollectionBean> collections;
    private LRecyclerView mLRecyclerView;
    private MyCollectionAdapter myCollectionAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private ImageView iv_return;
    private TextView tv_info;// 没有收藏时的提示信息


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);
        initViews();
        //init data
        collections = new ArrayList<>();
        MyCollectionBean collectionBean;
        for (int i = 0; i < 10; i++) {
            collectionBean = new MyCollectionBean();
            collections.add(collectionBean);
        }

        myCollectionAdapter = new MyCollectionAdapter();
        myCollectionAdapter.setDataList(collections);

        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        mLRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        mLRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(MyCollectionActivity.this));//
        // 布局管理器。
        mLRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mLRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。

        mLRecyclerViewAdapter = new LRecyclerViewAdapter(MyCollectionActivity.this,
                myCollectionAdapter);
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
            }
            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(MyCollectionActivity.this)
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
                // 右边的删除按钮
                myCollectionAdapter.remove(adapterPosition);
                mLRecyclerViewAdapter.notifyDataSetChanged();
                if (myCollectionAdapter.getItemCount() == 0) {
                    tv_info.setVisibility(View.VISIBLE);
                }
            } else if (direction == LRecyclerView.LEFT_DIRECTION) {
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
