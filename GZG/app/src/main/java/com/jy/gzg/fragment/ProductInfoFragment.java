package com.jy.gzg.fragment;

import android.content.Context;
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
import android.widget.RelativeLayout;

import com.jy.gzg.R;
import com.jy.gzg.ui.SnapPageLayout;
import com.jy.gzg.util.CustomImageLoader;
import com.jy.gzg.widget.ProductDetailsPage;
import com.jy.gzg.widget.ProductInfoPage;
import com.jy.gzg.widget.ProductInfoPopupWindow;

/**
 * Created by Administrator on 2016/10/31 0029.
 */
public class ProductInfoFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private Context mContext;
    private SnapPageLayout mSnapPageLayout;
    private ProductInfoPage topPage = null;
    private ProductDetailsPage bottomPage = null;
    private ViewPager vp_viewPager;
    private ViewGroup line_viewGroup;
    private RelativeLayout relat_price;// 用其点击事件触发弹框效果

    String[] imgUrls = new String[]{"http://img2.3lian.com/2014/f4/102/d/91.jpg", "http://www" +
            ".bz55.com/uploads/allimg/150720/139-150H0110925.jpg", "http://www.bz55" +
            ".com/uploads/allimg/150719/139-150G9151225.jpg", "http://www.bz55" +
            ".com/uploads/allimg/150409/140-150409145242.jpg"};
    private ImageView[] tips;//装小圆点的ImageView数组
    private ImageView[] mImageViews;//装ImageView数组

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
        mSnapPageLayout = (SnapPageLayout) view.findViewById(R.id.mSnapPageLayout);
        topPage = new ProductInfoPage(mContext, getLayoutInflater(savedInstanceState)
                .inflate(R.layout.view_product_info, null));
        bottomPage = new ProductDetailsPage(mContext,
                getLayoutInflater(savedInstanceState).inflate(
                        R.layout.fragment_productdetails, null));
        mSnapPageLayout.setSnapPages(topPage, bottomPage);
        initViews(view);
        setViewsListen();
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
            mImageViews[i] = imageView;
            CustomImageLoader.share().reqImageLoader(getContext(), imageView, imgUrls[i]);
        }

        //设置Adapter
        vp_viewPager.setAdapter(new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return imgUrls.length;
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
        });
        //设置监听，主要是设置点点的背景
        vp_viewPager.setOnPageChangeListener(this);
        return view;
    }

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
        vp_viewPager = (ViewPager) view.findViewById(R.id.vp_viewPager);
        line_viewGroup = (ViewGroup) view.findViewById(R.id.line_viewGroup);
        relat_price = (RelativeLayout) view.findViewById(R.id.relat_price);
    }

    /**
     * 对某些控件实行监听事件
     */
    public void setViewsListen() {
        relat_price.setOnClickListener(new View.OnClickListener() {
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
                        .mSnapPageLayout), Gravity
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
