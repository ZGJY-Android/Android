package com.jy.gzg.viewcontrollers.home.widget;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jy.gzg.R;
import com.jy.gzg.activity.CountryActivity;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.util.CustomImageLoader;
import com.jy.gzg.widget.imageCycleView.Carousel;
import com.jy.gzg.widget.imageCycleView.ImageCycleView;

import java.util.ArrayList;


/**
 * RecyclerView的HeaderView，简单的展示一个TextView
 */
public class SampleHeader extends RelativeLayout {
    Context context = null;
    private ImageCycleView imageCycleView;
    private LinearLayout layoutview1;
    private LinearLayout layoutview2;
    private LinearLayout layoutview3;
    private LinearLayout layoutview4;
    private LinearLayout layoutview5;
    private LinearLayout layoutview6;
    private LinearLayout layoutview7;
    private LinearLayout layoutview8;

    public SampleHeader(Context context) {
        super(context);
        this.context = context;
        View view = inflate(context, R.layout.sample_header, this);
        initView(view);
        //轮播图请求
        initLBT();

    }

    private void initView(View view) {
        imageCycleView = (ImageCycleView) view.findViewById(R.id.ad_view);
        layoutview1 = (LinearLayout) view.findViewById(R.id.bt_home1);
        layoutview2 = (LinearLayout) view.findViewById(R.id.bt_home2);
        layoutview3 = (LinearLayout) view.findViewById(R.id.bt_home3);
        layoutview4 = (LinearLayout) view.findViewById(R.id.bt_home4);
        layoutview5 = (LinearLayout) view.findViewById(R.id.bt_home5);
        layoutview6 = (LinearLayout) view.findViewById(R.id.bt_home6);
        layoutview7 = (LinearLayout) view.findViewById(R.id.bt_home7);
        layoutview8 = (LinearLayout) view.findViewById(R.id.bt_home8);

        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.bt_home1:
                        button1Click();
                        break;
                    case R.id.bt_home2:
                        button2Click();
                        break;
                    case R.id.bt_home3:
                        button3Click();
                        break;
                    case R.id.bt_home4:
                        button4Click();
                        break;
                    case R.id.bt_home5:
                        button5Click();
                        break;
                    case R.id.bt_home6:
                        button6Click();
                        break;
                    case R.id.bt_home7:
                        button7Click();
                        break;
                    case R.id.bt_home8:
                        button8Click();
                        break;
                }
            }
        };
        layoutview1.setOnClickListener(listener);
        layoutview2.setOnClickListener(listener);
        layoutview3.setOnClickListener(listener);
        layoutview4.setOnClickListener(listener);
        layoutview5.setOnClickListener(listener);
        layoutview6.setOnClickListener(listener);
        layoutview7.setOnClickListener(listener);
        layoutview8.setOnClickListener(listener);
    }

    private void button1Click() {
        AppToast.getInstance().showShort("1");
    }

    private void button2Click() {
        AppToast.getInstance().showShort("2");
    }

    private void button3Click() {
    }

    private void button4Click() {
    }

    private void button5Click() {
        Intent intent = new Intent(context, CountryActivity.class);
        intent.putExtra("country", 1);
        context.startActivity(intent);
    }

    private void button6Click() {
        Intent intent = new Intent(context, CountryActivity.class);
        intent.putExtra("country", 2);
        context.startActivity(intent);
    }

    private void button7Click() {
        Intent intent = new Intent(context, CountryActivity.class);
        intent.putExtra("country", 3);
        context.startActivity(intent);
    }

    private void button8Click() {
        Intent intent = new Intent(context, CountryActivity.class);
        intent.putExtra("country", 4);
        context.startActivity(intent);
    }

    private void initLBT() {
        ArrayList<Carousel> list = new ArrayList();
        Carousel carousel = new Carousel();
        carousel.setImgUrl("http://e.hiphotos.baidu" +
                ".com/image/h%3D360/sign=0a882fbc955298221a333fc5e7cb7b3b" +
                "/77094b36acaf2eddef675a92881001e939019332.jpg");
        list.add(carousel);
        carousel = new Carousel();
        carousel.setImgUrl("http://d.hiphotos.baidu" +
                ".com/image/h%3D360/sign=2f9956925cdf8db1a32e7a623922dddb" +
                "/0ff41bd5ad6eddc42223e3f13bdbb6fd536633f7.jpg");
        list.add(carousel);
        carousel = new Carousel();
        carousel.setImgUrl("http://e.hiphotos.baidu.com/image/h%3D360/sign=9a66dab99" +
                "c510fb367197191e932c893/b999a9014c086e06613eab4b00087bf40ad1cb18.jpg");
        list.add(carousel);
        carousel = new Carousel();
        carousel.setImgUrl("http://e.hiphotos.baidu" +
                ".com/image/h%3D360/sign=333dcd39cb3d70cf53faac0bc8dcd1ba" +
                "/024f78f0f736afc33532a065b119ebc4b74512f7.jpg");
        list.add(carousel);
        imageCycleView.setImageResources(list, listener);
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