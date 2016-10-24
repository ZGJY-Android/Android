package com.zgjy.gzg.viewcontrollers.home.bean;

import com.jy.gzg.bean.Entity;

/**
 * Created by YX on 2016/10/8 0008.
 */
public class WaresItemModel extends Entity {
    public long id;
    public String name;
    public int imgRes ;
    public String price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
