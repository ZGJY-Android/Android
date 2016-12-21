package com.jy.gzg.viewcontrollers.category.bean;

import java.util.List;

/**
 * Created by YX on 2016/12/20 0020.
 */

public class CategoryListBean {


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
        private int grade;
        private int id;
        private String last_updated_by;
        private String last_updated_date;
        private String name;
        private int orders;
        private int parent_id;
        private String seo_description;
        private String seo_keywords;
        private String seo_title;
        private Object tag_id;
        private String tree_path;

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

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrders() {
            return orders;
        }

        public void setOrders(int orders) {
            this.orders = orders;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public String getSeo_description() {
            return seo_description;
        }

        public void setSeo_description(String seo_description) {
            this.seo_description = seo_description;
        }

        public String getSeo_keywords() {
            return seo_keywords;
        }

        public void setSeo_keywords(String seo_keywords) {
            this.seo_keywords = seo_keywords;
        }

        public String getSeo_title() {
            return seo_title;
        }

        public void setSeo_title(String seo_title) {
            this.seo_title = seo_title;
        }

        public Object getTag_id() {
            return tag_id;
        }

        public void setTag_id(Object tag_id) {
            this.tag_id = tag_id;
        }

        public String getTree_path() {
            return tree_path;
        }

        public void setTree_path(String tree_path) {
            this.tree_path = tree_path;
        }
    }
}