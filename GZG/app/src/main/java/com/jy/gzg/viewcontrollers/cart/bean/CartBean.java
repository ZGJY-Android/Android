package com.jy.gzg.viewcontrollers.cart.bean;

import com.jy.gzg.bean.Entity;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class CartBean extends Entity {
    private String image;
    private String name;
    private float price;
    private int quantity;

    public CartBean() {
    }

    public CartBean(String image, String name, int price, int quantity) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
