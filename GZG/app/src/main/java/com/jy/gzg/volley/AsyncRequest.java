package com.jy.gzg.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jy.gzg.util.L;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 异步请求
 */
public abstract class AsyncRequest {

    private RequestQueue mQueue;
    private AsyncRequestCallBack mRequestCallback = null;
    public AsyncRequestStruct mRequestData = null;

    // Request 的子类，只需要从载解析接口
    public abstract Object inThreadParseResponseData(String responseData);

    public static class AsyncRequestStruct {
        // 可以自行定义一个hash值， 用来快速定位交互的是哪个请求。
        public int reqHashCode = 0;
        public int reqMethod; //请求方式
        public int METHOD_GET = 1;//get请求
        public int METHOD_POST = 2;//post请求

        // 可以自行定义一个tag值， 用来结果处理时附带请求参数。
        public Object reqTag = null;

        // ===> 上行传参部分
        public String url = null;
        public JSONObject reqJsonObject; // 上传data数据的json格式

        // <=== 下行结果返回部分
        public Object reqResultObj = null; // Request解析返回的结 果解析数据对象
        public int userDefErrorCode = 0; // 可以自己定义解析层的错误code
        public String userDefErrorMsg = null; // 可以自己定义解析层的错误消息
    }

    private AsyncRequest() {
    }

    /**
     * 构造方法
     *
     * @param callback 请求回调
     */
    public AsyncRequest(AsyncRequestCallBack callback) {
        assert (callback != null);
        mRequestCallback = callback;
    }

    /**
     * 访问回调 接口
     */
    public interface AsyncRequestCallBack {

        public void onRequestComplete(AsyncRequestStruct result);

        public void onReqeustFailed(AsyncRequestStruct result);
    }

    public boolean doRequset(AsyncRequestStruct data, AsyncRequestCallBack callback, Context context) {
        if (data == null || data.url == null || data.url.length() <= 7 || data.reqTag == null) {
            return false;
        }
        mRequestData = data;
        mRequestCallback = callback;
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(context);
        }
        if (data.reqMethod == data.METHOD_GET) {
            RequestJSONObjectGet(data.url, data.reqJsonObject, (int) data.reqTag);
        } else {
            RequestJSONObjectPost(data.url, data.reqJsonObject, (int) data.reqTag);
        }
        return true;
    }

    public void cancelRequest(int tag) {
        if (mQueue != null) {
            mQueue.cancelAll(tag);
        }
    }

    //post请求
    public int RequestJSONObjectPost(String url, final JSONObject dataObject, int tag) {
        if (mRequestCallback == null) {
            return -1;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                JSONObject jsonObject = null;
//                try {
                L.i("333333333333333333333333", s);
//                    jsonObject = new JSONObject(s);
//                    int code = jsonObject.optInt("status");
                Object obj = inThreadParseResponseData(s);
                L.i("55555555555555555555555555555", obj);
                mRequestData.reqResultObj = obj;

                mRequestCallback.onRequestComplete(mRequestData);
//                    if(code == 1000){
//                        mRequestCallback.onRequestComplete(mRequestData);
//                    }else{
//                        mRequestCallback.onReqeustFailed(mRequestData);
//                    }
//                } catch (JSONException e) {
//                e.printStackTrace();
//            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Object obj = inThreadParseResponseData(volleyError.getMessage());
                if (obj == null) {
                    mRequestData.userDefErrorMsg = volleyError.getMessage();
                    L.i("222222222222222222222222", volleyError.getMessage());
                } else {
                    L.i("11111111111111111111111111", volleyError.getMessage());
                    mRequestData.userDefErrorMsg = obj.toString();
                }
                mRequestCallback.onReqeustFailed(mRequestData);
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
//                String appId = "123";
//                String timestamp = String.valueOf(System.currentTimeMillis());
//                String secret = "assdda213ghjutewr4yfgb786nbvn54";
//                map.put("appId", appId);
//                map.put("timestamp", timestamp);
//                String msgDigestCal = new String(Base64.encode((SHA1Util.SHA1(appId + MD5Util.getMD5Str(timestamp)) + SHA1Util.SHA1(secret)).getBytes(), Base64.NO_WRAP));
//                map.put("msgDigest", msgDigestCal);
                if (dataObject != null) {
                    map.put("data", dataObject.toString());
                }
                return map;
            }
        };
        mQueue.add(stringRequest);
        return 0;
    }

    //get请求
    public int RequestJSONObjectGet(String url, JSONObject dataObject, int tag) {
        if (mRequestCallback == null) {
            return -1;
        }
//        //加入加密信息
//        Map<String, Object> map = new HashMap<String, Object>();
//        String appId = "123";
//        String timestamp = String.valueOf(System.currentTimeMillis());
//        String secret = "assdda213ghjutewr4yfgb786nbvn54";
//        map.put("appId", appId);
//        map.put("timestamp", timestamp);
//        String msgDigestCal = new String(Base64.encode((SHA1Util.SHA1(appId + MD5Util.getMD5Str(timestamp)) + SHA1Util.SHA1(secret)).getBytes(), Base64.NO_WRAP));
//        map.put("msgDigest", msgDigestCal);
//        map.put("data", dataObject);
//        JSONObject jsonObject = new JSONObject(map);
//        String jsonData = jsonObject.toString();
//        url = url + jsonData;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("lx", "jsonObject:" + jsonObject.toString());
                int code = jsonObject.optInt("status");
                Object obj = inThreadParseResponseData(jsonObject.toString());
                mRequestData.reqResultObj = obj;
                if (code == 1000) {
                    mRequestCallback.onRequestComplete(mRequestData);
                } else {
                    mRequestCallback.onReqeustFailed(mRequestData);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mRequestData.userDefErrorMsg = volleyError.getMessage();
                mRequestCallback.onReqeustFailed(mRequestData);
                inThreadParseResponseData(volleyError.getMessage());
            }
        });
        jsonObjectRequest.setTag(tag);
        mQueue.add(jsonObjectRequest);
        return 0;
    }

}
