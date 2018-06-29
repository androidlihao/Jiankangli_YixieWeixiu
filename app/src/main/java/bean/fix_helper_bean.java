package bean;

import java.util.List;

/**
 * Created by 李浩 on 2017/6/29.
 */

public class fix_helper_bean {

    /**
     * code : 200
     * message : 维修手册查询成功！
     * body : {"pid":"s20001","data":[{"repairManualId":2,"title":"dsafsd","manufacturerId":null,"productCategoriesId":null,"contentCategoriesId":null,"keyWord":null,"readNum":0,"fileName":null,"fileUrl":null,"weight":null,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"flag":null,"lastUpdateUserId":null,"status":null,"auditUserId":null,"auditTime":null,"manufacturerName":"测试1","productCategoriesName":"123 2","contentCategoriesName":null,"sdate":null,"edate":null},{"repairManualId":3,"title":"dsafsd","manufacturerId":null,"productCategoriesId":null,"contentCategoriesId":null,"keyWord":null,"readNum":10,"fileName":null,"fileUrl":null,"weight":null,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"flag":null,"lastUpdateUserId":null,"status":null,"auditUserId":null,"auditTime":null,"manufacturerName":"GE 1","productCategoriesName":"123 2","contentCategoriesName":null,"sdate":null,"edate":null},{"repairManualId":4,"title":"dfasdf","manufacturerId":null,"productCategoriesId":null,"contentCategoriesId":null,"keyWord":null,"readNum":10,"fileName":null,"fileUrl":null,"weight":null,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"flag":null,"lastUpdateUserId":null,"status":null,"auditUserId":null,"auditTime":null,"manufacturerName":"123","productCategoriesName":"123 2","contentCategoriesName":null,"sdate":null,"edate":null},{"repairManualId":8,"title":"第一条测试","manufacturerId":null,"productCategoriesId":null,"contentCategoriesId":null,"keyWord":null,"readNum":0,"fileName":null,"fileUrl":null,"weight":null,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"flag":null,"lastUpdateUserId":null,"status":null,"auditUserId":null,"auditTime":null,"manufacturerName":"ceshi22","productCategoriesName":"ceshi","contentCategoriesName":null,"sdate":null,"edate":null}]}
     */

    private String code;
    private String message;
    private BodyBean body;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * pid : s20001
         * data : [{"repairManualId":2,"title":"dsafsd","manufacturerId":null,"productCategoriesId":null,"contentCategoriesId":null,"keyWord":null,"readNum":0,"fileName":null,"fileUrl":null,"weight":null,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"flag":null,"lastUpdateUserId":null,"status":null,"auditUserId":null,"auditTime":null,"manufacturerName":"测试1","productCategoriesName":"123 2","contentCategoriesName":null,"sdate":null,"edate":null},{"repairManualId":3,"title":"dsafsd","manufacturerId":null,"productCategoriesId":null,"contentCategoriesId":null,"keyWord":null,"readNum":10,"fileName":null,"fileUrl":null,"weight":null,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"flag":null,"lastUpdateUserId":null,"status":null,"auditUserId":null,"auditTime":null,"manufacturerName":"GE 1","productCategoriesName":"123 2","contentCategoriesName":null,"sdate":null,"edate":null},{"repairManualId":4,"title":"dfasdf","manufacturerId":null,"productCategoriesId":null,"contentCategoriesId":null,"keyWord":null,"readNum":10,"fileName":null,"fileUrl":null,"weight":null,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"flag":null,"lastUpdateUserId":null,"status":null,"auditUserId":null,"auditTime":null,"manufacturerName":"123","productCategoriesName":"123 2","contentCategoriesName":null,"sdate":null,"edate":null},{"repairManualId":8,"title":"第一条测试","manufacturerId":null,"productCategoriesId":null,"contentCategoriesId":null,"keyWord":null,"readNum":0,"fileName":null,"fileUrl":null,"weight":null,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"flag":null,"lastUpdateUserId":null,"status":null,"auditUserId":null,"auditTime":null,"manufacturerName":"ceshi22","productCategoriesName":"ceshi","contentCategoriesName":null,"sdate":null,"edate":null}]
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

        public static class DataBean {
            /**
             * repairManualId : 2
             * title : dsafsd
             * manufacturerId : null
             * productCategoriesId : null
             * contentCategoriesId : null
             * keyWord : null
             * readNum : 0
             * fileName : null
             * fileUrl : null
             * weight : null
             * createTime : null
             * createUserId : null
             * updateTime : null
             * updateUserId : null
             * flag : null
             * lastUpdateUserId : null
             * status : null
             * auditUserId : null
             * auditTime : null
             * manufacturerName : 测试1
             * productCategoriesName : 123 2
             * contentCategoriesName : null
             * sdate : null
             * edate : null
             */

            private int repairManualId;
            private String title;
            private Object manufacturerId;
            private Object productCategoriesId;
            private Object contentCategoriesId;
            private Object keyWord;
            private int readNum;
            private Object fileName;
            private Object fileUrl;
            private Object weight;
            private Object createTime;
            private Object createUserId;
            private Object updateTime;
            private Object updateUserId;
            private Object flag;
            private Object lastUpdateUserId;
            private Object status;
            private Object auditUserId;
            private Object auditTime;
            private String manufacturerName;
            private String productCategoriesName;
            private Object contentCategoriesName;
            private Object sdate;
            private Object edate;

            public int getRepairManualId() {
                return repairManualId;
            }

            public void setRepairManualId(int repairManualId) {
                this.repairManualId = repairManualId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getManufacturerId() {
                return manufacturerId;
            }

            public void setManufacturerId(Object manufacturerId) {
                this.manufacturerId = manufacturerId;
            }

            public Object getProductCategoriesId() {
                return productCategoriesId;
            }

            public void setProductCategoriesId(Object productCategoriesId) {
                this.productCategoriesId = productCategoriesId;
            }

            public Object getContentCategoriesId() {
                return contentCategoriesId;
            }

            public void setContentCategoriesId(Object contentCategoriesId) {
                this.contentCategoriesId = contentCategoriesId;
            }

            public Object getKeyWord() {
                return keyWord;
            }

            public void setKeyWord(Object keyWord) {
                this.keyWord = keyWord;
            }

            public int getReadNum() {
                return readNum;
            }

            public void setReadNum(int readNum) {
                this.readNum = readNum;
            }

            public Object getFileName() {
                return fileName;
            }

            public void setFileName(Object fileName) {
                this.fileName = fileName;
            }

            public Object getFileUrl() {
                return fileUrl;
            }

            public void setFileUrl(Object fileUrl) {
                this.fileUrl = fileUrl;
            }

            public Object getWeight() {
                return weight;
            }

            public void setWeight(Object weight) {
                this.weight = weight;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(Object createUserId) {
                this.createUserId = createUserId;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getUpdateUserId() {
                return updateUserId;
            }

            public void setUpdateUserId(Object updateUserId) {
                this.updateUserId = updateUserId;
            }

            public Object getFlag() {
                return flag;
            }

            public void setFlag(Object flag) {
                this.flag = flag;
            }

            public Object getLastUpdateUserId() {
                return lastUpdateUserId;
            }

            public void setLastUpdateUserId(Object lastUpdateUserId) {
                this.lastUpdateUserId = lastUpdateUserId;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getAuditUserId() {
                return auditUserId;
            }

            public void setAuditUserId(Object auditUserId) {
                this.auditUserId = auditUserId;
            }

            public Object getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(Object auditTime) {
                this.auditTime = auditTime;
            }

            public String getManufacturerName() {
                return manufacturerName;
            }

            public void setManufacturerName(String manufacturerName) {
                this.manufacturerName = manufacturerName;
            }

            public String getProductCategoriesName() {
                return productCategoriesName;
            }

            public void setProductCategoriesName(String productCategoriesName) {
                this.productCategoriesName = productCategoriesName;
            }

            public Object getContentCategoriesName() {
                return contentCategoriesName;
            }

            public void setContentCategoriesName(Object contentCategoriesName) {
                this.contentCategoriesName = contentCategoriesName;
            }

            public Object getSdate() {
                return sdate;
            }

            public void setSdate(Object sdate) {
                this.sdate = sdate;
            }

            public Object getEdate() {
                return edate;
            }

            public void setEdate(Object edate) {
                this.edate = edate;
            }
        }
    }
}
