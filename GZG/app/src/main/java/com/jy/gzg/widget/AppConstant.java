package com.jy.gzg.widget;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class AppConstant {
    public static final String IP = "http://192.168.0.110:8080";//正式IP
    public static final String LUNBOTU_IMGS = IP + "/app/FindList";// 轮播图
    public static final String HEAD_MODEL = IP + "/app/Index ";// 首页八个小块
    public static final String HOME_XSTM = IP + "/app/LimitsIndex";// 主页限时特卖的相关数据
    public static final String HOME_HLPT = IP + "/app/SpellGrouipIndex";// 主页火力拼团的相关数据
    public static final String HOME_KOREA = IP + "/app/Korea";// 主页韩国馆的相关数据
    public static final String HOME_JAPAN = IP + "/app/JapanIndex";// 主页日本馆的相关数据
    public static final String HOME_AUSSIE = IP + "/app/AussieIndex";// 主页澳洲馆的相关数据
    public static final String HOME_EUROPE = IP + "/app/EuropeIndex";// 主页欧洲馆的相关数据

    public static final String COUNTRY_DETAILS = IP + "/appTopic/App?";//国家馆通用接口
    public static final String HEADERMODEL_DETAILS =
            IP + "/appTopic/App?productCategoryId=0&&tagIds=";// 通用八个按钮的详情接口(跟参数-->&&tagIds=3)
    public static final String PRODUCT_DETAILS = IP +
            "/appTopic/App?tagIds=6&&productCategoryId=999999&&id=";// 商品详情(跟参数-->&&id=48)

    public static final String CATEGORY_LIST = IP +
            "/app/AppProductCategory2";// 二级分类列表
    public static final String CATEGORY_WARES = IP +
            "/app/AppProductCategory3?";// 三级分类列表
    public static final String CATEGORY_BRAND = IP +
            "/app/AppfindBrand";// 品牌列表
    public static final String CATEGORY_BRAND_WARES = IP +
            "/app/AppProductCategory3?";// 品牌商品

    public static final String USER_LOGIN = IP +
            "/appLogin/submit?";// 用户登录(跟参数-->username=1234567&&password=1234567)

}
