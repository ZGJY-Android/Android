package com.jy.gzg.fragment;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.gzg.R;
import com.jy.gzg.bean.ProductBean;
import com.jy.gzg.ui.CustomViewPager;
import com.jy.gzg.widget.ProductInfoPopupWindow;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2016/10/31 0029.
 */
public class ProductInfoFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private Context mContext;
    private ProductBean xstmBean;
    private CustomViewPager vp_viewPager;
    private ViewGroup line_viewGroup;
    private LinearLayout line_qilicount;// 用其点击事件触发弹框效果

    private TextView tv_fullname,// 名称
            tv_price,// 价格
            tv_marketprice,// 市场价
            tv_qsum,// 起批量
            tv_point,// 积分
            tv_count,// 数量
            tv_id,// 编号
            tv_reduce,// 减
            tv_add;// 加

    String[] imgUrls;
    private ImageView[] tips;//装小圆点的ImageView数组
    private ImageView[] mImageViews;//装ImageView数组

    public void setXstmBean(ProductBean xstmBean) {
        this.xstmBean = xstmBean;
    }

    // 自定义的弹出框类
    ProductInfoPopupWindow productInfoPopupWindow;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productinfo, container, false);
        initViews(view);
        setViewsListen();
        if (xstmBean == null) {
            mImageViews = new ImageView[1];
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.getInstance().displayImage("", imageView);
            mImageViews[0] = imageView;
            vp_viewPager.setAdapter(pagerAdapter);
            return view;
        } else {
            imgUrls = new String[]{xstmBean.getImage()};
            tv_fullname.setText(xstmBean.getFull_name());
            tv_price.setText("￥" + xstmBean.getPrice());
            tv_marketprice.setText("￥" + xstmBean.getMarket_price());
            tv_marketprice.getPaint().setFlags(Paint
                    .STRIKE_THRU_TEXT_FLAG);// 添加中划线
            if (xstmBean.getQsum() == null || xstmBean.getQsum().equals("")) {
                tv_qsum.setText(1 + "");
            } else {
                String[] qipiCount = xstmBean.getQsum().trim().split("|");
                if (qipiCount[1].equals("")) {
                    tv_qsum.setText(1 + "");
                } else
                    tv_qsum.setText(qipiCount[1]);
            }
            tv_point.setText(xstmBean.getPoint() + "");
            tv_id.setText(xstmBean.getSn());
        }

        //将小圆点加入到ViewGroup中
        tips = new ImageView[imgUrls.length];
        ImageView imageView = null;
        for (int i = 0; i < tips.length; i++) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.mipmap.landian);
            } else {
                tips[i].setBackgroundResource(R.mipmap.huidian);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup
                    .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            line_viewGroup.addView(imageView, layoutParams);
        }

        //将图片装载到数组中
        mImageViews = new ImageView[imgUrls.length];
        for (int i = 0; i < mImageViews.length; i++) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            if (xstmBean.getImage() == null || xstmBean.getImage().equals("")) {
                ImageLoader.getInstance().displayImage("", imageView);
            } else {
                ImageLoader.getInstance().displayImage(imgUrls[i], imageView);
            }
            mImageViews[i] = imageView;
        }

        //设置Adapter
        vp_viewPager.setAdapter(pagerAdapter);
        //设置监听，主要是设置点点的背景
        vp_viewPager.setOnPageChangeListener(this);
        return view;
    }

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return mImageViews.length;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(mImageViews[position]);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViews[position]);
            return mImageViews[position];
        }
    };

    /**
     * 设置选中的tip的背景
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.mipmap.landian);
            } else {
                tips[i].setBackgroundResource(R.mipmap.huidian);
            }
        }
    }

    /**
     * 初始化控件信息
     */
    public void initViews(View view) {
        vp_viewPager = (CustomViewPager) view.findViewById(R.id.vp_viewPager);
        line_viewGroup = (ViewGroup) view.findViewById(R.id.line_viewGroup);
        line_qilicount = (LinearLayout) view.findViewById(R.id.line_qilicount);

        tv_fullname = (TextView) view.findViewById(R.id.tv_fullname);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_marketprice = (TextView) view.findViewById(R.id.tv_marketprice);
        tv_qsum = (TextView) view.findViewById(R.id.tv_qsum);
        tv_point = (TextView) view.findViewById(R.id.tv_point);
        tv_count = (TextView) view.findViewById(R.id.tv_count);
        tv_id = (TextView) view.findViewById(R.id.tv_id);
        tv_reduce = (TextView) view.findViewById(R.id.tv_reduce);
        tv_add = (TextView) view.findViewById(R.id.tv_add);
    }

    /**
     * 对某些控件实行监听事件
     */
    public void setViewsListen() {
        line_qilicount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实例化ProductInfoPopupWindow
                productInfoPopupWindow = new ProductInfoPopupWindow(mContext, new View
                        .OnClickListener() {

                    public void onClick(View v) {
                        productInfoPopupWindow.dismiss();
                        switch (v.getId()) {
                            case R.id.iv_productimg:
                                break;
                            default:
                                break;
                        }
                    }

                });
                //显示窗口
                productInfoPopupWindow.showAtLocation(getActivity().findViewById(R.id
                        .mScollView), Gravity
                        .BOTTOM, 0, 0); //设置layout在PopupWindow中显示的位置
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
