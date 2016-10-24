package com.jy.gzg.viewcontrollers.home.bean;

/**
 * Created by YX on 2016/9/7 0007.
 */
public class Carousel {
    /**
     * id : 1
     * name : 音箱狂欢
     * description : null
     * imgUrl : http://7mno4h.com2.z0.glb.qiniucdn.com/5608f3b5Nc8d90151.jpg
     * type : 1
     */

    private int id;
    private String name;
    private Object description;
    private String imgUrl;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Carousel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", imgUrl='" + imgUrl + '\'' +
                ", type=" + type +
                '}';
    }
}
