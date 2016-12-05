package com.jy.gzg.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class HuolipintuanBean {
    private PageBean page;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean {
        private ArrayList<ProductBean> list;

        public ArrayList<ProductBean> getList() {
            return list;
        }

        public void setList(ArrayList<ProductBean> list) {
            this.list = list;
        }
    }
}
