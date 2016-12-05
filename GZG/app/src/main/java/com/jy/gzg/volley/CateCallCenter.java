package com.jy.gzg.volley;

import android.content.Context;

import com.jy.gzg.util.AppConfiguration;
import com.jy.gzg.widget.imageCycleView.Carousel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CateCallCenter implements AsyncRequest.AsyncRequestCallBack {

    private  static CateCallCenter  sInstance =null;

    private final int K_ASYNC_GET_LBT_HASHCODE = 0x20150100;

    private int mSequenceID = 1;

    private HashMap<Integer, RequestUnit> mRequestMap = new HashMap<Integer, RequestUnit>();

    private int allocHandleID() {
        return mSequenceID++;
    }

    private class RequestUnit {
        public AsyncRequest mRequest = null;
        public Object mDelegate = null;
        public Object mAdditionalData = null;

    }

    public static CateCallCenter shared() {
        if (sInstance == null) {
            sInstance = new CateCallCenter();
        }
        return sInstance;
    }




    //轮播图回调接口
    public interface ICarouselDelegate{
        void reqCarouselComplete(ArrayList<Carousel> carouselList);

        void reqCarouselFailed(int connectionError, String errorMsg);
    }

    //轮播图请求
   public  int reqCarousel(Context context, ICarouselDelegate delegate){
       int retHandle = allocHandleID();

       if (delegate == null ){
            return RequestConstant.REQ_ERROR_PARAM;
       }

       String requestURL;

       //根据环境配置来分配正式地址和测试地址
       if(AppConfiguration.__env_use_release_server_urls){
           requestURL = RequestConstant.GET_VIEWPAPER ;//有正式环境地址时替换
       } else {
           requestURL =RequestConstant.GET_VIEWPAPER ;
       }
       //此数据结构用来分装请求参数
       AsyncRequest.AsyncRequestStruct  req =new AsyncRequest.AsyncRequestStruct();
       req.url =requestURL;
       req.reqHashCode =K_ASYNC_GET_LBT_HASHCODE;

       Map<String,String> reqMap = new HashMap<String,String>();
       req.reqJsonObject = new JSONObject(reqMap);
       req.reqMethod =req.METHOD_POST;
       req.reqTag =retHandle;


       PostCarouselRequest  postCarouselRequest =new PostCarouselRequest(this);
       postCarouselRequest.doRequset(req,this,context);

       RequestUnit unit = new RequestUnit();
       unit.mDelegate = delegate;
       unit.mRequest = postCarouselRequest;
       mRequestMap.put(retHandle, unit);

       return  retHandle;
   }



    //取消请求，若有发生网络请求，在每个活动生命周期结束时必须调用该函数。
    public void cancleRequest(int handle) {
        RequestUnit unit = mRequestMap.get(handle);
        mRequestMap.remove(handle);
        if (unit != null) {
            unit.mRequest.cancelRequest(handle);
            unit.mDelegate = null;
        }
    }


   //请求成功
    @Override
    public void onRequestComplete(AsyncRequest.AsyncRequestStruct result) {
        int handle = (Integer) result.reqTag;
        RequestUnit unit = null;
        unit = mRequestMap.remove(handle);

        if (unit == null)
            return;

        if (unit.mDelegate == null) {
            return;
        }

        //通过reqHashCode来对应请求
        switch (result.reqHashCode){
            case K_ASYNC_GET_LBT_HASHCODE:
                if (unit.mDelegate != null && unit.mRequest != null){
                    //通过iCarouselDelegate回调通知CateFragment
                    ICarouselDelegate iCarouselDelegate = (ICarouselDelegate) unit.mDelegate;
                    iCarouselDelegate.reqCarouselComplete((ArrayList<Carousel>) result.reqResultObj);
                }
              break;


        }
    }

    //请求失败
    @Override
    public void onReqeustFailed(AsyncRequest.AsyncRequestStruct result) {
        int handle = (Integer) result.reqTag;
        RequestUnit unit = null;

        unit = mRequestMap.remove(handle);

        if (unit == null)
            return;

        if (unit.mDelegate == null) {
            return;
        }

        //通过reqHashCode来对应请求
        switch (result.reqHashCode){
            case K_ASYNC_GET_LBT_HASHCODE:
                if (unit.mDelegate != null && unit.mRequest != null){
                    //通过iCarouselDelegate回调通知CateFragment
                    ICarouselDelegate iCarouselDelegate = (ICarouselDelegate) unit.mDelegate;
                    iCarouselDelegate.reqCarouselFailed(result.userDefErrorCode, result.userDefErrorMsg);
                    iCarouselDelegate =null;
                }
                break;


        }

    }
}
