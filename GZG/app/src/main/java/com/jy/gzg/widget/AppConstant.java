package com.jy.gzg.widget;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class AppConstant {
    public static final String IP = "http://www.maizanmao.com";// 正式IP
    //public static final String IP = "http://192.168.0.110:8080";// 本地服务器数据

    // ----------------------------------主页-------------------------------------
    public static final String LUNBOTU_IMGS = IP + "/app/FindList";// 轮播图
    public static final String HEAD_MODEL = IP + "/app/Index ";// 首页八个小块
    public static final String HOME_XSTM = IP + "/app/LimitsIndex";// 主页限时特卖的相关数据
    public static final String HOME_HLPT = IP + "/app/SpellGrouipIndex";// 主页火力拼团的相关数据
    public static final String HOME_MYZX = IP + "/app/BabyIndex";// 主页母婴专享的相关数据
    public static final String HOME_HFMZ = IP + "/app/ToiletriesIndex";// 主页护肤美妆的相关数据
    public static final String HOME_JKBJ = IP + "/app/HealthyHome";// 主页健康保健的相关数据
    public static final String HOME_JJRY = IP + "/app/HomeDay";// 主页居家日用的相关数据
    public static final String COUNTRY_DETAILS = IP + "/appTopic/App?";//国家馆通用接口
    public static final String HEADERMODEL_DETAILS =
            IP + "/appTopic/App?tagIds=";// 通用八个按钮的详情接口(跟参数-->&&tagIds=3)

    public static final String PRODUCT_DETAILS = IP +
            "/app/AppFindProduct?id=";// 商品详情新接口(跟参数-->&&id=48)

    // ----------------------------------品牌分类-------------------------------------
    public static final String CATEGORY_LIST = IP +
            "/app/AppProductCategory2";// 二级分类列表
    public static final String CATEGORY_WARES = IP +
            "/app/AppProductCategory3?";// 三级分类列表
    public static final String CATEGORY_BRAND = IP +
            "/app/AppfindBrand";// 品牌列表
    public static final String CATEGORY_BRAND_WARES = IP +
            "/app/AppProductCategory3?";// 品牌商品

    public static final String CATEGORY_BRAND_DETAILS = "http://192.168.0" +
            ".110:8080/app/AppProduct?";// 查看分类商品(跟参数-->productCategoryId=266)


    // ----------------------------------登录注册-------------------------------------
    public static final String USER_LOGIN = IP +
            "/appLogin/submit?";// 用户登录(跟参数-->username=1234567&&password=1234567)
    public static final String ISEXISTED_ACCOUNT = IP +
            "/appRegister/checkUsername";// 检查用户名是否被禁用或已存在(跟参数-->username=123)
    public static final String ISEXISTED_EMAIL = IP + "/appRegister/checkEmail";// 检查E-mail是否存在
    // (跟参数-->email=1111@qq.com)
    public static final String REGISTER_SUBMIT = IP +
            "/appRegister/submit?";// 注册提交(跟参数-->username=123&&email=1111@qq.com&&password=123)

    // ----------------------------------购物车-------------------------------------
    public static final String CART_ADD = IP +
            "/appCart/add?";// 添加商品到购物车(跟参数-->id=id&&quantity=3)
    public static final String CART_LIST = IP +
            "/appCart/list?";// 购物车中的商品列表(跟参数会员id-->memberId=83)
}
