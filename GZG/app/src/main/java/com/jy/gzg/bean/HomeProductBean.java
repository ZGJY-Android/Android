package com.jy.gzg.bean;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class HomeProductBean {
    private int id;
    private Object image;
    private float market_price;
    private Object memo;
    private String name;
    private float price;

    public HomeProductBean() {
    }

    public HomeProductBean(int id, Object image, float price, Object memo, String name,
                           float market_price) {
        this.id = id;
        this.image = image;
        this.memo = memo;
        this.name = name;
        this.price = price;
        this.market_price = market_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public float getMarket_price() {
        return market_price;
    }

    public void setMarket_price(float market_price) {
        this.market_price = market_price;
    }

    public Object getMemo() {
        return memo;
    }

    public void setMemo(Object memo) {
        this.memo = memo;
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
}
