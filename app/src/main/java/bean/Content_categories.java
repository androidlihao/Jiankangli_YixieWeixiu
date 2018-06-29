package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李浩 on 2016/11/2.
 */
public class Content_categories implements Serializable{

    /**
     * data : [{"contentCategoriesId":1,"name":"故障解决方案","singlename":"故障解决方案"},{"contentCategoriesId":2,"name":"工作原理","singlename":"工作原理"},{"contentCategoriesId":3,"name":"错误编码","singlename":"错误编码"},{"contentCategoriesId":4,"name":"维修秘籍","singlename":"维修秘籍"},{"contentCategoriesId":32,"name":"12","singlename":"12"}]
     * pid : s00002
     */

    private BodyBean body;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean implements Serializable{
        private String pid;
        /**
         * contentCategoriesId : 1
         * name : 故障解决方案
         * singlename : 故障解决方案
         */

        private List<DataBean> data;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{
            private int contentCategoriesId;
            private String name;
            private String singlename;

            public int getContentCategoriesId() {
                return contentCategoriesId;
            }

            public void setContentCategoriesId(int contentCategoriesId) {
                this.contentCategoriesId = contentCategoriesId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSinglename() {
                return singlename;
            }

            public void setSinglename(String singlename) {
                this.singlename = singlename;
            }
        }
    }
}
