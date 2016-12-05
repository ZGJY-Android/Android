package com.jy.gzg.bean;

import java.util.List;

/**
 * Created by YX on 2016/11/18 0018
 */
public class test {
    private PageBean page;
    private List<TagsBean> tags;
    public PageBean getPage() {
        return page;
    }
    public void setPage(PageBean page) {
        this.page = page;
    }
    public List<TagsBean> getTags() {
        return tags;
    }
    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }
    public static class PageBean {

        private List<ListBean> list;
        public List<ListBean> getList() {
            return list;
        }
        public void setList(List<ListBean> list) {
            this.list = list;
        }
        public static class ListBean {
            @Override
            public String toString() {
                return "ListBean{" +
                        "image='" + image + '\'' +
                        '}';
            }

            private String image;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
    public static class TagsBean {
        private Object icon;
        private int id;
        private String name;
        private int type;

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
