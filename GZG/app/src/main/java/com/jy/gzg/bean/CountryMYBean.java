package com.jy.gzg.bean;

import java.util.ArrayList;

/**
 * Created by YX on 2016/12/7 0007.
 */

public class CountryMYBean {
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
