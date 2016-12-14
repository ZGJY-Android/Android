package com.jy.gzg.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public class LunbotuBean {
    private ArrayList<ListBean> list;

    public ArrayList<ListBean> getList() {
        return list;
    }

    public void setList(ArrayList<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int ad_position_id;
        private int id;
        private String path;
        private String title;

        public int getAd_position_id() {
            return ad_position_id;
        }

        public void setAd_position_id(int ad_position_id) {
            this.ad_position_id = ad_position_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
