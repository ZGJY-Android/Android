package com.jy.gzg.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class ProductListBean {
    // 用于带有recycleview的json解析
    private ArrayList<ProductBean> list;

    public ArrayList<ProductBean> getList() {
        return list;
    }

    public void setList(ArrayList<ProductBean> list) {
        this.list = list;
    }
}
