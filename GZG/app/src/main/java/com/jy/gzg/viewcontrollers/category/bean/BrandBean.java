package com.jy.gzg.viewcontrollers.category.bean;

import java.util.List;

/**
 * Created by YX on 2016/12/21 0021.
 */

public class BrandBean {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {

        private Object create_by;
        private Object creation_date;
        private boolean delete_flag;
        private int id;
        private String introduction;
        private String last_updated_by;
        private String last_updated_date;
        private String logo;
        private String name;
        private Object orders;
        private int type;
        private String url;

        public Object getCreate_by() {
            return create_by;
        }

        public void setCreate_by(Object create_by) {
            this.create_by = create_by;
        }

        public Object getCreation_date() {
            return creation_date;
        }

        public void setCreation_date(Object creation_date) {
            this.creation_date = creation_date;
        }

        public boolean isDelete_flag() {
            return delete_flag;
        }

        public void setDelete_flag(boolean delete_flag) {
            this.delete_flag = delete_flag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getLast_updated_by() {
            return last_updated_by;
        }

        public void setLast_updated_by(String last_updated_by) {
            this.last_updated_by = last_updated_by;
        }

        public String getLast_updated_date() {
            return last_updated_date;
        }

        public void setLast_updated_date(String last_updated_date) {
            this.last_updated_date = last_updated_date;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getOrders() {
            return orders;
        }

        public void setOrders(Object orders) {
            this.orders = orders;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
