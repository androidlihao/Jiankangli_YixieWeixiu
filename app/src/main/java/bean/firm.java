package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李浩 on 2016/9/26.
 */
public class firm implements Serializable{

    /**
     * data : [{"manufacturerId":28,"updateTime":null,"createTime":null,"flag":null,"createUserId":null,"lastUpdateUserId":null,"name":"GE","singlename":"GE","status":null,"upContentNum":null,"contentNum":null},{"manufacturerId":32,"updateTime":null,"createTime":null,"flag":null,"createUserId":null,"lastUpdateUserId":null,"name":"飞利浦","singlename":"飞利浦","status":null,"upContentNum":null,"contentNum":null},{"manufacturerId":33,"updateTime":null,"createTime":null,"flag":null,"createUserId":null,"lastUpdateUserId":null,"name":"西门子","singlename":"西门子","status":null,"upContentNum":null,"contentNum":null},{"manufacturerId":34,"updateTime":null,"createTime":null,"flag":null,"createUserId":null,"lastUpdateUserId":null,"name":"东芝","singlename":"东芝","status":null,"upContentNum":null,"contentNum":null},{"manufacturerId":35,"updateTime":null,"createTime":null,"flag":null,"createUserId":null,"lastUpdateUserId":null,"name":"日立","singlename":"日立","status":null,"upContentNum":null,"contentNum":null}]
     * pid : s00001
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
         * manufacturerId : 28
         * updateTime : null
         * createTime : null
         * flag : null
         * createUserId : null
         * lastUpdateUserId : null
         * name : GE
         * singlename : GE
         * status : null
         * upContentNum : null
         * contentNum : null
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
            private int manufacturerId;
            private Object updateTime;
            private Object createTime;
            private Object flag;
            private Object createUserId;
            private Object lastUpdateUserId;
            private String name;
            private String singlename;
            private Object status;
            private Object upContentNum;
            private Object contentNum;

            public int getManufacturerId() {
                return manufacturerId;
            }

            public void setManufacturerId(int manufacturerId) {
                this.manufacturerId = manufacturerId;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getFlag() {
                return flag;
            }

            public void setFlag(Object flag) {
                this.flag = flag;
            }

            public Object getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(Object createUserId) {
                this.createUserId = createUserId;
            }

            public Object getLastUpdateUserId() {
                return lastUpdateUserId;
            }

            public void setLastUpdateUserId(Object lastUpdateUserId) {
                this.lastUpdateUserId = lastUpdateUserId;
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

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getUpContentNum() {
                return upContentNum;
            }

            public void setUpContentNum(Object upContentNum) {
                this.upContentNum = upContentNum;
            }

            public Object getContentNum() {
                return contentNum;
            }

            public void setContentNum(Object contentNum) {
                this.contentNum = contentNum;
            }
        }
    }
}
