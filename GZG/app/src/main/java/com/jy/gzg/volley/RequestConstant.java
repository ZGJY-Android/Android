package com.jy.gzg.volley;

/**
 * 请求路径和常量
 */
public class RequestConstant {
    public static final String IP = "";//正式IP

    //测试轮播图请求地址   "http://112.124.22.238:8081/course_api/banner/query?type=1";
    public static final String TEST_IP1 = "112.124.22.238:8081";//测试IP
    public static final String PATH1 = "http://" + TEST_IP1 + "/course_api";


    public static final String TEST_IP = "192.168.0.110:8080";//测试IP
    public static final String PATH = "http://" + TEST_IP + "/appTopic";
    // 发送请求错误，客户端自定义
    public static final int REQ_OK = 0;
    public static final int REQ_ERROR_PARAM = -1;// 参数错误

    //post方法到轮播图
    public static final String GET_VIEWPAPER = PATH1 + "/banner/query?type=1";

    //post方法到轮播图
    public static final String GET_KOREA = PATH + "Korea?tagIds=8&&productCategoryId=";
    ;

}

