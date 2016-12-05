package com.jy.gzg.bean;


import java.io.Serializable;

/**
 * 实体类
 */
public abstract class Entity implements Serializable {
    public long id;// 流水号

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
