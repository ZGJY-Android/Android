package com.jy.gzg.viewcontrollers.home.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jy.gzg.R;
import com.jy.gzg.activity.CountryActivity;
import com.jy.gzg.activity.HuolipintuanActivity;
import com.jy.gzg.activity.MuyinzhuanchangActivity;
import com.jy.gzg.activity.XianshitemaiActivity;
import com.jy.gzg.bean.LunbotuBean;
import com.jy.gzg.ui.FixedSpeedScroller;
import com.jy.gzg.util.GsonUtil;
import com.jy.gzg.viewcontrollers.home.bean.HeaderModelBean;
import com.jy.gzg.viewcontrollers.home.bean.ListBean;
import com.jy.gzg.widget.AppConstant;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的HeaderView，简单的展示一个TextView
 */
public class SampleHeader extends RelativeLayout implements ViewPager.OnPageChangeListener {
    private Context context;
    private ImageHandler handler;
    private ViewPager main_viewpager;
    private ViewGroup line_viewGroup;
    private ImageView[] tips;// 装点点的ImageView数组
    private ImageView[] mImageViews;// 装ImageView数组
    private ArrayList<String> imgUrlList;// 图片资源
    private ImageLoader imageLoader;

    private LinearLayout layoutview1,// 限时特卖
            layoutview2,// 火力拼团
            layoutview3,// 母婴专场
            layoutview4,// 洗护用品
            layoutview5,
            layoutview6,
            layoutview7,
            layoutview8;
    private ImageView iv_img1, iv_img2, iv_img3, iv_img4, iv_img5, iv_img6, iv_img7, iv_img8;
    private TextView tv_text1, tv_text2, tv_text3, tv_text4, tv_text5, tv_text6, tv_text7, tv_text8;

    public SampleHeader(final Context context) {
        super(context);
        this.context = context;
        View view = inflate(context, R.layout.sample_header, this);
        initView(view);// 初始化控件
        imageLoader = ImageLoader.getInstance();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST,
                AppConstant.HEAD_MODEL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                setViewListen();// 数据请求成功后才设置监听
                HeaderModelBean headerModelBean = GsonUtil.parseJsonWithGson(jsonObject
                                .toString(),
                        HeaderModelBean.class);
                List<ListBean> list = headerModelBean.getList();
                imageLoader.displayImage(list.get(0).getIcon()
                        , iv_img1);
                imageLoader.displayImage(list.get(1).getIcon()
                        , iv_img2);
                imageLoader.displayImage(list.get(2).getIcon()
                        , iv_img3);
                imageLoader.displayImage(list.get(3).getIcon()
                        , iv_img4);
                imageLoader.displayImage(list.get(4).getIcon()
                        , iv_img5);
                imageLoader.displayImage(list.get(5).getIcon()
                        , iv_img6);
                imageLoader.displayImage(list.get(6).getIcon()
                        , iv_img7);
                imageLoader.displayImage(list.get(7).getIcon()
                        , iv_img8);
                tv_text1.setText(list.get(0).getName());
                tv_text2.setText(list.get(1).getName());
                tv_text3.setText(list.get(2).getName());
                tv_text4.setText(list.get(3).getName());
                tv_text5.setText(list.get(4).getName());
                tv_text6.setText(list.get(5).getName());
                tv_text7.setText(list.get(6).getName());
                tv_text8.setText(list.get(7).getName());
                layoutview1.setTag(list.get(0).getId() + "");
                layoutview2.setTag(list.get(1).getId() + "");
                layoutview3.setTag(list.get(2).getId() + "");
                layoutview4.setTag(list.get(3).getId() + "");
                layoutview5.setTag(list.get(4).getId() + "");
                layoutview6.setTag(list.get(5).getId() + "");
                layoutview7.setTag(list.get(6).getId() + "");
                layoutview8.setTag(list.get(7).getId() + "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                // 此处应当做缓存
            }
        });
        requestQueue.add(jsonObjectRequest1);
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST,
                AppConstant.LUNBOTU_IMGS, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                LunbotuBean lunbotuBean = GsonUtil.parseJsonWithGson(jsonObject
                        .toString(), LunbotuBean.class);
                if (lunbotuBean != null) {
                    setLunBoTu(lunbotuBean);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        requestQueue.add(jsonObjectRequest2);
    }

    public void setLunBoTu(LunbotuBean lunbotuBean) {
        ArrayList<LunbotuBean.ListBean> lunBoTuList = lunbotuBean.getList();
        //载入图片资源ID
        imgUrlList = new ArrayList<>();
        LunbotuBean.ListBean listBean;
        for (int i = 0; i < lunBoTuList.size(); i++) {
            listBean = lunBoTuList.get(i);
            imgUrlList.add(listBean.getPath());
        }
        // 当只有3张图片或者2张图片的时候，滑动存在BUG
        if (imgUrlList.size() == 2) {
            // 如果只有2张图片的话就人为的添加为4张
            tips = new ImageView[2];
            imgUrlList.add(imgUrlList.get(0));
            imgUrlList.add(imgUrlList.get(1));
        } else if (imgUrlList.size() == 3) {
            // 如果只有3张图片的话就人为的添加为6张
            tips = new ImageView[3];
            imgUrlList.add(imgUrlList.get(0));
            imgUrlList.add(imgUrlList.get(1));
            imgUrlList.add(imgUrlList.get(2));
        } else {
            tips = new ImageView[imgUrlList.size()];
        }

        //将点点加入到ViewGroup中
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.mipmap.landian);
            } else {
                tips[i].setBackgroundResource(R.mipmap.huidian);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup
                    .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            line_viewGroup.addView(imageView, layoutParams);
        }

        //将图片装载到数组中
        mImageViews = new ImageView[imgUrlList.size()];
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView imageView = new ImageView(context);
            mImageViews[i] = imageView;
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.getInstance().displayImage(imgUrlList.get(i), imageView);
        }
        handler = new ImageHandler(context);
        //设置Adapter
        main_viewpager.setAdapter(new MyAdapter());
        // 一张图片没必要写滑动监听，还浪费资源
        if (imgUrlList.size() != 1) {
            //设置监听，主要是设置点点的背景
            main_viewpager.addOnPageChangeListener(this);
            //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
            main_viewpager.setCurrentItem((mImageViews.length) * 100);
        }
    }

    /**
     * 初始化各种控件
     *
     * @param view
     */
    private void initView(View view) {
        main_viewpager = (ViewPager) view.findViewById(R.id.main_viewpager);
        line_viewGroup = (ViewGroup) view.findViewById(R.id.line_viewGroup);
        layoutview1 = (LinearLayout) view.findViewById(R.id.line_home1);
        layoutview2 = (LinearLayout) view.findViewById(R.id.line_home2);
        layoutview3 = (LinearLayout) view.findViewById(R.id.line_home3);
        layoutview4 = (LinearLayout) view.findViewById(R.id.line_home4);
        layoutview5 = (LinearLayout) view.findViewById(R.id.line_home5);
        layoutview6 = (LinearLayout) view.findViewById(R.id.line_home6);
        layoutview7 = (LinearLayout) view.findViewById(R.id.line_home7);
        layoutview8 = (LinearLayout) view.findViewById(R.id.line_home8);
        iv_img1 = (ImageView) view.findViewById(R.id.iv_img1);
        iv_img2 = (ImageView) view.findViewById(R.id.iv_img2);
        iv_img3 = (ImageView) view.findViewById(R.id.iv_img3);
        iv_img4 = (ImageView) view.findViewById(R.id.iv_img4);
        iv_img5 = (ImageView) view.findViewById(R.id.iv_img5);
        iv_img6 = (ImageView) view.findViewById(R.id.iv_img6);
        iv_img7 = (ImageView) view.findViewById(R.id.iv_img7);
        iv_img8 = (ImageView) view.findViewById(R.id.iv_img8);
        tv_text1 = (TextView) view.findViewById(R.id.tv_text1);
        tv_text2 = (TextView) view.findViewById(R.id.tv_text2);
        tv_text3 = (TextView) view.findViewById(R.id.tv_text3);
        tv_text4 = (TextView) view.findViewById(R.id.tv_text4);
        tv_text5 = (TextView) view.findViewById(R.id.tv_text5);
        tv_text6 = (TextView) view.findViewById(R.id.tv_text6);
        tv_text7 = (TextView) view.findViewById(R.id.tv_text7);
        tv_text8 = (TextView) view.findViewById(R.id.tv_text8);
    }

    /**
     * 设置各种监听事件
     */
    private void setViewListen() {
        layoutview1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MuyinzhuanchangActivity.class);
                intent.putExtra("headermodel_id", layoutview1.getTag() + "");
                context.startActivity(intent);
            }
        });
        layoutview2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        layoutview3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, XianshitemaiActivity.class);
                intent.putExtra("headermodel_id", layoutview3.getTag() + "");
                context.startActivity(intent);
            }
        });
        layoutview4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HuolipintuanActivity.class);
                intent.putExtra("headermodel_id", layoutview4.getTag() + "");
                context.startActivity(intent);
            }
        });
        layoutview5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountryActivity.class);
                intent.putExtra("country", 1);
                intent.putExtra("headermodel_id", layoutview5.getTag() + "");
                context.startActivity(intent);
            }
        });
        layoutview6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountryActivity.class);
                intent.putExtra("country", 2);
                intent.putExtra("headermodel_id", layoutview6.getTag() + "");
                context.startActivity(intent);
            }
        });
        layoutview7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountryActivity.class);
                intent.putExtra("country", 3);
                intent.putExtra("headermodel_id", layoutview7.getTag() + "");
                context.startActivity(intent);
            }
        });
        layoutview8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountryActivity.class);
                intent.putExtra("country", 4);
                intent.putExtra("headermodel_id", layoutview8.getTag() + "");
                context.startActivity(intent);
            }
        });


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, position,
                0));
        if (position == preItem) {
            //不作处理
            Log.i("zyw", "没有移动");
            return;
        }
        if (position > preItem) {
            //从左向右滑

            preItem = position;
            isLeftToRight = true;
        }
        if (position < preItem) {
            //从右向左滑

            preItem = position;
            isLeftToRight = false;
        }
        setPointBackground();
    }

    FixedSpeedScroller mScroller;

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING://手指滑动状态

                break;
            case ViewPager.SCROLL_STATE_IDLE://停止状态

                break;
            case ViewPager.SCROLL_STATE_SETTLING://自动滑动状态
//                try {
//                    Field mField = ViewPager.class.getDeclaredField("mScroller");
//                    mField.setAccessible(true);
//                    mScroller = new FixedSpeedScroller(main_viewpager
//                            .getContext(), new
//                            AccelerateInterpolator
//                            ());
//                    mScroller.setmDuration(500);
//                    mField.set(main_viewpager, mScroller);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                break;
            default:
                break;
        }
    }

    public class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            if (imgUrlList.size() == 1) {
                return 1;
            }
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁.
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (imgUrlList.size() != 1) {
                ((ViewPager) container).removeView(mImageViews[position % mImageViews.length]);
            }
        }

        // 载入图片进去，用当前的position 除以图片数组长度取余数是关键
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            try {
                ((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mImageViews[position % mImageViews.length];
        }
    }

    /**
     * 设置选中的tip的背景
     *
     * @param selectItems
     */
    int preItem = 0;
    int index = -1;// 记录小点
    boolean isLeftToRight = true;// 是否从左往右滑动

    private void setPointBackground() {
        if (isLeftToRight) {
            // 从左往右滑动
            index++;
        } else {
            // 从右向左滑动
            index--;
        }
        if (index >= tips.length) {
            index = 0;
        } else if (index < 0) {
            index = tips.length - 1;
        }
        for (int i = 0; i < tips.length; i++) {
            if (index == i) {
                tips[i].setBackgroundResource(R.mipmap.landian);
            } else {
                tips[i].setBackgroundResource(R.mipmap.huidian);
            }
        }
    }

    private class ImageHandler extends Handler {
        /**
         * 请求更新显示的View。
         */
        protected static final int MSG_UPDATE_IMAGE = 1;
        /**
         * 请求暂停轮播。
         */
        protected static final int MSG_KEEP_SILENT = 2;
        /**
         * 请求恢复轮播。
         */
        protected static final int MSG_BREAK_SILENT = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected static final int MSG_PAGE_CHANGED = 4;

        //轮播间隔时间
        protected static final long MSG_DELAY = 3000;

        //使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
        private Context weakReference;
        private int currentItem = 0;

        protected ImageHandler(Context wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (context == null) {
                //Activity已经回收，无需再处理UI了
                return;
            }
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (handler.hasMessages(MSG_UPDATE_IMAGE)) {
                handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    main_viewpager.setCurrentItem(currentItem);
                    //准备下次播放
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentItem = msg.arg1;
                    handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE,
                            ImageHandler.MSG_DELAY);
                    break;
                default:
                    break;
            }
        }
    }
}