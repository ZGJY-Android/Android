package com.zgjy.gzg.volley;

/**
 * 请求路径和常量
 */
public class RequestConstant {
    //    http://123.57.167.177:8080/gnw/rest/userApp/v1/register?requestJson=
    public static final String TEST_IP = "112.124.22.238:8081";//测试IP
    public static final String IP = "";//正式IP
    public static final String PATH = "http://" + TEST_IP + "/course_api";
    // 发送请求错误，客户端自定义
    public static final int REQ_OK = 0;
    public static final int REQ_ERROR_PARAM = -1;// 参数错误

   // http://123.57.167.177:8080/gnw/rest/cateApp/v1/sendOrder

    //post方法到轮播图
    public static final String GET_VIEWPAPER = PATH + "/banner/query?type=1";

    // post方法获取门店
    public static  final String GET_STORE = PATH + "/getStoreInfoList";

    // post方法获取门店信息
    public static  final String GET_STORE_NEWS = PATH + "/getStoreInfo";

    //post方法获取评论 getCommList
    public  static  final String GET_OBTAIN_COMMENT =PATH + "/getCommList";

    //post方法获取预约下单接口
    public  static  final String GET_PLACE_AN_ORDER =PATH + "/sendOrder";





}

