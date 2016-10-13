package com.zgjy.gzg.volley;

import android.util.Log;

import com.zgjy.gzg.viewcontrollers.home.bean.Carousel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图
 */
public class PostCarouselRequest extends AsyncRequest{
    /**
     * 构造方法
     *
     * @param callback 请求回调
     */
    public PostCarouselRequest(AsyncRequestCallBack callback) {
        super(callback);
    }

    @Override
    public List<Carousel> inThreadParseResponseData(String responseData) {
        //此处返回数据，在这边做解析，返回你想要的数据结构到ui层。
        Log.e("yx", "responseData:" + responseData);
        List<Carousel> carouselList =new ArrayList<Carousel>();
        if (responseData == null){
            return  null;
        }

        JSONObject jsonObject =null;

        try {
//            jsonObject =new JSONObject(responseData);
//
//            int code =jsonObject.getInt("status");
//            String msg = jsonObject.getString("message");
//            if (code != 1000){
//                this.mRequestData.userDefErrorCode =code;
//                this.mRequestData.userDefErrorMsg =msg;
//            }
             Carousel carousel =null;

            JSONArray jsonArray =new JSONArray(responseData);
            for (int i =0;i<jsonArray.length();i++){
                carousel = new Carousel();
                JSONObject data =jsonArray.getJSONObject(i);
                carousel.setId(data.getInt("id"));
                carousel.setName(data.getString("name"));
                carousel.setDescription(data.get("description"));
                carousel.setImgUrl(data.getString("imgUrl"));
                carousel.setType(data.getInt("type"));
                carouselList.add(carousel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return carouselList;
    }
}
