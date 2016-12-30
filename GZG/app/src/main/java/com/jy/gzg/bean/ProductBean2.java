package com.jy.gzg.bean;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class ProductBean2 extends Entity {
    // 商品实体类，用于使用过lRecycleView的地方
    private long product_id;// 商品编号
    private String image;// 展示图片
    private String memo;// 备注
    private String name;// 名称
    private double price;// 销售价
    private double market_price;// 市场价

    public ProductBean2() {
    }

    public ProductBean2(long product_id, String image, String memo, String name, double price,
                        double market_price) {
        this.product_id = product_id;
        this.image = image;
        this.memo = memo;
        this.name = name;
        this.price = price;
        this.market_price = market_price;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }
}
