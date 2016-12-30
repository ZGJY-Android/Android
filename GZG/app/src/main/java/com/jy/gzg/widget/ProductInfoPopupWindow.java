package com.jy.gzg.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jy.gzg.R;
import com.jy.gzg.activity.LoginActivity;
import com.jy.gzg.activity.ProductdetailsActivity;
import com.jy.gzg.bean.ProductBean;
import com.jy.gzg.util.AppToast;
import com.jy.gzg.util.ZYWUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.Map;

import static com.jy.gzg.activity.ProductdetailsActivity.productCount;


/**
 * Created by Administrator on 2016/11/10 0010.
 */
public class ProductInfoPopupWindow extends PopupWindow {
    private Context context;
    private View mMenuView;
    private ImageView iv_productimg;// 需要设置其点击事件避免关闭弹框
    private RelativeLayout relat_pop;// 设置其点击事件避免关闭弹框
    private TextView tv_name,// 名称
            tv_price,// 价格
            tv_count,// 数量
            tv_reduce,// 减
            tv_add;// 加
    private Button btn_yes;// 确定按钮
    private CallBack callBack;// 接口回调
    private ProductBean productBean;

    public ProductInfoPopupWindow() {
    }

    public ProductInfoPopupWindow(Context context, OnClickListener itemsOnClick) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.view_productinfo_popup, null);
        initViews(mMenuView);
        productBean = ProductdetailsActivity.productBean;
        ImageLoader.getInstance().displayImage(productBean.getImage(), iv_productimg);
        tv_name.setText(productBean.getName());
        tv_price.setText("￥" + productBean.getPrice());
        tv_count.setText(productCount + "");
        //设置按钮监听
        iv_productimg.setOnClickListener(itemsOnClick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.BottomDialog);
        //实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        setViewsListen();
    }

    /**
     * 初始化控件信息
     */
    public void initViews(View view) {
        iv_productimg = (ImageView) mMenuView.findViewById(R.id.iv_productimg);
        relat_pop = (RelativeLayout) mMenuView.findViewById(R.id.relat_pop);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_count = (TextView) view.findViewById(R.id.tv_count);
        tv_reduce = (TextView) view.findViewById(R.id.tv_reduce);
        tv_add = (TextView) view.findViewById(R.id.tv_add);
        btn_yes = (Button) view.findViewById(R.id.btn_yes);
    }

    /**
     * 对某些控件实行监听事件
     */
    public void setViewsListen() {
        iv_productimg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 此处设置监听的目的是不让其关闭弹框
            }
        });
        relat_pop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 此处设置监听的目的是不让其关闭弹框
            }
        });

        // 添加数量
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productCount++;
                tv_count.setText(productCount + "");
            }
        });

        // 减少数量
        tv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productCount > 1) {
                    productCount--;
                }
                tv_count.setText(productCount + "");
            }
        });

        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int[] location = new int[2];
                // 计算该视图在全局坐标系中的x，y值(注意这个值是要从屏幕顶端算起，也就是索包括了通知栏的高度),获取在当前屏幕内的绝对坐标
                mMenuView.findViewById(R.id.relat_pop).getLocationOnScreen(location);
                int x = location[0];// 距离屏幕左边的距离
                int y = location[1];// 距离屏幕上边的距离
                int height = (int) event.getY();// 点击位置的纵坐标
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y > height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

        // 点击确认
        btn_yes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                productCount = Integer.parseInt(tv_count.getText()
                        .toString());
                dismiss();
                callBack.isYes("回调成功:" + productCount);
                // ---------------将商品添加到购物车---------------
                // 1、首先判断是否登录
                ZYWUtil.init(context);// 必须初始化
                String username = ZYWUtil.readData(Constant.SP_FILE, Constant.SP_UNAME);
                if (username == null || username.equals("")) {
                    AppToast.getInstance().showShort("您还未登录");
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                } else {
                    Log.i("zyw", "vvvvvvvvv__________" + ZYWUtil.readData(Constant.SP_FILE,
                            Constant.SP_UID));
                    addToCart(productBean.getId(), ProductdetailsActivity.productCount);
                }
            }
        });
    }

    /**
     * 添加到购物车操作(存在bug，有待修改)
     *
     * @param id
     * @param quantity
     */
    private void addToCart(final long id, final int quantity) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        final String url = AppConstant.CART_ADD + "id=" + id + "&&quantity=" + quantity;
        Log.i("zyw", "ppppppppppp___________" + url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstant
                .CART_ADD, new Response
                .Listener<String>() {
            @Override
            public void onResponse(String s) {
                AppToast.getInstance().showShort("添加购物车成功");
                Log.i("zyw", "zzzzzzzzzzz___________" + s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                AppToast.getInstance().showShort("网络连接失败");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map params = new HashMap<>();
                params.put("id", id + "");
                params.put("quantity", quantity + "");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void setOnClickListener(ProductInfoPopupWindow.CallBack callBack) {
        this.callBack = callBack;
    }

    public static interface CallBack {
        public void isYes(String result);
    }
}
