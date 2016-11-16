package com.jy.gzg.viewcontrollers.home.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jy.gzg.R;
import com.jy.gzg.util.CustomImageLoader;
import com.jy.gzg.widget.imageCycleView.Carousel;
import com.jy.gzg.volley.CateCallCenter;
import com.jy.gzg.widget.imageCycleView.ImageCycleView;

import java.util.ArrayList;


/**
 * RecyclerView的HeaderView，简单的展示一个TextView
 */
public class SampleHeader extends RelativeLayout {
    Context context = null;
    private ImageCycleView imageCycleView;

    public SampleHeader(Context context) {
        super(context);
        this.context = context;
        View view = inflate(context, R.layout.sample_header, this);
        imageCycleView = (ImageCycleView) view.findViewById(R.id.ad_view);
        initLBT("4028815e513e7ead01513f256fba0006");
    }

    private void initLBT(String idUser) {
        CateCallCenter.shared().reqCarousel(getContext(), idUser, new CateCallCenter
                .ICarouselDelegate() {
            @Override
            public void reqCarouselComplete(ArrayList<Carousel> carouselList) {
                imageCycleView.setImageResources(carouselList, listener);
            }

            @Override
            public void reqCarouselFailed(int connectionError, String errorMsg) {

            }
        });
    }

    //viewpager的监听
    private ImageCycleView.ImageCycleViewListener listener = new ImageCycleView
            .ImageCycleViewListener() {
        //显示图片
        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            CustomImageLoader.share().reqImageLoader(getContext(), imageView, imageURL);
        }

        //点击图片
        @Override
        public void onImageClick(Carousel info, int postion, View imageView) {

        }
    };
}