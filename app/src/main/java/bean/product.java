package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李浩 on 2016/9/26.
 */
public class product implements Serializable{

    /**
     * code : 200
     * message : 查询成功！
     * body : {"data":[{"productCategoriesId":76,"name":"123213213213","singlename":"12321321","contentNum":0,"upContentNum":0,"icon":"http://192.168.1.3//images/201610/20161025161129_440.jpg","localPath":"/images/201610/20161025161129_440.jpg","is_select":0},{"productCategoriesId":601,"name":"家属","singlename":"家属 1","contentNum":1,"upContentNum":0,"is_select":0},{"productCategoriesId":75,"name":"daf","singlename":"daf","contentNum":0,"upContentNum":0,"icon":"http://192.168.1.3//images/201610/20161025154443_201.jpg","localPath":"/images/201610/20161025154443_201.jpg","is_select":0},{"productCategoriesId":73,"name":"分类","singlename":"分类","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":74,"name":"2","singlename":"2","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":77,"name":"Ty","singlename":"Ty","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":78,"name":"133","singlename":"133","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":79,"name":"21","singlename":"21","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":80,"name":"31","singlename":"31","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":81,"name":"dsdsds","singlename":"dsdsds","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":82,"name":"12","singlename":"12","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":83,"name":"添加产品分类","singlename":"添加产品分类","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":84,"name":"55","singlename":"55","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":85,"name":"家属","singlename":"家属","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":602,"name":"123","singlename":"123 2","contentNum":17,"upContentNum":1,"is_select":0},{"productCategoriesId":603,"name":"dsdsds","singlename":"dsdsds 3","contentNum":7,"upContentNum":0,"is_select":0},{"productCategoriesId":604,"name":"添加产品分类","singlename":"添加产品分类 产品分类 4","contentNum":11,"upContentNum":1,"is_select":0},{"productCategoriesId":605,"name":"55","singlename":"55 5","contentNum":5,"upContentNum":1,"is_select":0},{"productCategoriesId":606,"name":"testc","singlename":"testc 6","contentNum":3,"upContentNum":0,"is_select":0}],"pid":"s00016"}
     */

    private BodyBean body;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean implements Serializable{
        /**
         * data : [{"productCategoriesId":76,"name":"123213213213","singlename":"12321321","contentNum":0,"upContentNum":0,"icon":"http://192.168.1.3//images/201610/20161025161129_440.jpg","localPath":"/images/201610/20161025161129_440.jpg","is_select":0},{"productCategoriesId":601,"name":"家属","singlename":"家属 1","contentNum":1,"upContentNum":0,"is_select":0},{"productCategoriesId":75,"name":"daf","singlename":"daf","contentNum":0,"upContentNum":0,"icon":"http://192.168.1.3//images/201610/20161025154443_201.jpg","localPath":"/images/201610/20161025154443_201.jpg","is_select":0},{"productCategoriesId":73,"name":"分类","singlename":"分类","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":74,"name":"2","singlename":"2","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":77,"name":"Ty","singlename":"Ty","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":78,"name":"133","singlename":"133","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":79,"name":"21","singlename":"21","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":80,"name":"31","singlename":"31","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":81,"name":"dsdsds","singlename":"dsdsds","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":82,"name":"12","singlename":"12","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":83,"name":"添加产品分类","singlename":"添加产品分类","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":84,"name":"55","singlename":"55","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":85,"name":"家属","singlename":"家属","contentNum":0,"upContentNum":0,"is_select":0},{"productCategoriesId":602,"name":"123","singlename":"123 2","contentNum":17,"upContentNum":1,"is_select":0},{"productCategoriesId":603,"name":"dsdsds","singlename":"dsdsds 3","contentNum":7,"upContentNum":0,"is_select":0},{"productCategoriesId":604,"name":"添加产品分类","singlename":"添加产品分类 产品分类 4","contentNum":11,"upContentNum":1,"is_select":0},{"productCategoriesId":605,"name":"55","singlename":"55 5","contentNum":5,"upContentNum":1,"is_select":0},{"productCategoriesId":606,"name":"testc","singlename":"testc 6","contentNum":3,"upContentNum":0,"is_select":0}]
         * pid : s00016
         */

        private String pid;
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
            /**
             * productCategoriesId : 76
             * name : 123213213213
             * singlename : 12321321
             * contentNum : 0
             * upContentNum : 0
             * icon : http://192.168.1.3//images/201610/20161025161129_440.jpg
             * localPath : /images/201610/20161025161129_440.jpg
             * is_select : 0
             */

            private int productCategoriesId;
            private String name;
            private String singlename;
            private int contentNum;
            private int upContentNum;
            private String icon;
            private String localPath;
            private int is_select;

            public int getProductCategoriesId() {
                return productCategoriesId;
            }

            public void setProductCategoriesId(int productCategoriesId) {
                this.productCategoriesId = productCategoriesId;
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

            public int getContentNum() {
                return contentNum;
            }

            public void setContentNum(int contentNum) {
                this.contentNum = contentNum;
            }

            public int getUpContentNum() {
                return upContentNum;
            }

            public void setUpContentNum(int upContentNum) {
                this.upContentNum = upContentNum;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getLocalPath() {
                return localPath;
            }

            public void setLocalPath(String localPath) {
                this.localPath = localPath;
            }

            public int getIs_select() {
                return is_select;
            }

            public void setIs_select(int is_select) {
                this.is_select = is_select;
            }
        }
    }
}
