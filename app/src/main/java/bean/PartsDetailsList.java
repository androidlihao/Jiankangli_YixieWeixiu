package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李浩 on 2017/8/18.
 */

public class PartsDetailsList {

    /**
     * code : 200
     * message : 配件列表查询成功！
     * body : {"pid":"30001","data":[{"partId":11,"createMemberId":null,"supplierId":null,"name":"打发苏打粉","showImage":"http://192.168.1.3/","manufacturerId":356,"productCategoriesId":null,"partNo":null,"serialNumber":null,"warrantyPeriod":null,"price":null,"moneyUnit":"¥","inventory":0,"sold":null,"forSale":null,"quality":null,"status":null,"flag":null,"productTime":null,"remark":null,"description":null,"readCount":null,"createTime":null,"updateTime":null,"updateMemberId":null,"auditTime":null,"auditUserId":null,"operateTime":null,"statusName":"暂无","manufacturerName":"测试1","productCategoriesName":null,"partWarrantyList":null,"partImList":null,"reason":null,"supplierName":null,"memberName":null,"auditName":null,"sdate":null,"edate":null}]}
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
         * pid : 30001
         * data : [{"partId":11,"createMemberId":null,"supplierId":null,"name":"打发苏打粉","showImage":"http://192.168.1.3/","manufacturerId":356,"productCategoriesId":null,"partNo":null,"serialNumber":null,"warrantyPeriod":null,"price":null,"moneyUnit":"¥","inventory":0,"sold":null,"forSale":null,"quality":null,"status":null,"flag":null,"productTime":null,"remark":null,"description":null,"readCount":null,"createTime":null,"updateTime":null,"updateMemberId":null,"auditTime":null,"auditUserId":null,"operateTime":null,"statusName":"暂无","manufacturerName":"测试1","productCategoriesName":null,"partWarrantyList":null,"partImList":null,"reason":null,"supplierName":null,"memberName":null,"auditName":null,"sdate":null,"edate":null}]
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
             * partId : 11
             * createMemberId : null
             * supplierId : null
             * name : 打发苏打粉
             * showImage : http://192.168.1.3/
             * manufacturerId : 356
             * productCategoriesId : null
             * partNo : null
             * serialNumber : null
             * warrantyPeriod : null
             * price : null
             * moneyUnit : ¥
             * inventory : 0
             * sold : null
             * forSale : null
             * quality : null
             * status : null
             * flag : null
             * productTime : null
             * remark : null
             * description : null
             * readCount : null
             * createTime : null
             * updateTime : null
             * updateMemberId : null
             * auditTime : null
             * auditUserId : null
             * operateTime : null
             * statusName : 暂无
             * manufacturerName : 测试1
             * productCategoriesName : null
             * partWarrantyList : null
             * partImList : null
             * reason : null
             * supplierName : null
             * memberName : null
             * auditName : null
             * sdate : null
             * edate : null
             */

            private int partId;
            private Object createMemberId;
            private Object supplierId;
            private String name;
            private String showImage;
            private int manufacturerId;
            private Object productCategoriesId;
            private Object partNo;
            private Object serialNumber;
            private Object warrantyPeriod;
            private Object price;
            private String moneyUnit;
            private int inventory;
            private Object sold;
            private Object forSale;
            private Object quality;
            private Object status;
            private Object flag;
            private Object productTime;
            private Object remark;
            private Object description;
            private Object readCount;
            private Object createTime;
            private Object updateTime;
            private Object updateMemberId;
            private Object auditTime;
            private Object auditUserId;
            private Object operateTime;
            private String statusName;
            private String manufacturerName;
            private Object productCategoriesName;
            private Object partWarrantyList;
            private Object partImList;
            private Object reason;
            private Object supplierName;
            private Object memberName;
            private Object auditName;
            private Object sdate;
            private Object edate;

            public int getPartId() {
                return partId;
            }

            public void setPartId(int partId) {
                this.partId = partId;
            }

            public Object getCreateMemberId() {
                return createMemberId;
            }

            public void setCreateMemberId(Object createMemberId) {
                this.createMemberId = createMemberId;
            }

            public Object getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(Object supplierId) {
                this.supplierId = supplierId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShowImage() {
                return showImage;
            }

            public void setShowImage(String showImage) {
                this.showImage = showImage;
            }

            public int getManufacturerId() {
                return manufacturerId;
            }

            public void setManufacturerId(int manufacturerId) {
                this.manufacturerId = manufacturerId;
            }

            public Object getProductCategoriesId() {
                return productCategoriesId;
            }

            public void setProductCategoriesId(Object productCategoriesId) {
                this.productCategoriesId = productCategoriesId;
            }

            public Object getPartNo() {
                return partNo;
            }

            public void setPartNo(Object partNo) {
                this.partNo = partNo;
            }

            public Object getSerialNumber() {
                return serialNumber;
            }

            public void setSerialNumber(Object serialNumber) {
                this.serialNumber = serialNumber;
            }

            public Object getWarrantyPeriod() {
                return warrantyPeriod;
            }

            public void setWarrantyPeriod(Object warrantyPeriod) {
                this.warrantyPeriod = warrantyPeriod;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public String getMoneyUnit() {
                return moneyUnit;
            }

            public void setMoneyUnit(String moneyUnit) {
                this.moneyUnit = moneyUnit;
            }

            public int getInventory() {
                return inventory;
            }

            public void setInventory(int inventory) {
                this.inventory = inventory;
            }

            public Object getSold() {
                return sold;
            }

            public void setSold(Object sold) {
                this.sold = sold;
            }

            public Object getForSale() {
                return forSale;
            }

            public void setForSale(Object forSale) {
                this.forSale = forSale;
            }

            public Object getQuality() {
                return quality;
            }

            public void setQuality(Object quality) {
                this.quality = quality;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getFlag() {
                return flag;
            }

            public void setFlag(Object flag) {
                this.flag = flag;
            }

            public Object getProductTime() {
                return productTime;
            }

            public void setProductTime(Object productTime) {
                this.productTime = productTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public Object getReadCount() {
                return readCount;
            }

            public void setReadCount(Object readCount) {
                this.readCount = readCount;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getUpdateMemberId() {
                return updateMemberId;
            }

            public void setUpdateMemberId(Object updateMemberId) {
                this.updateMemberId = updateMemberId;
            }

            public Object getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(Object auditTime) {
                this.auditTime = auditTime;
            }

            public Object getAuditUserId() {
                return auditUserId;
            }

            public void setAuditUserId(Object auditUserId) {
                this.auditUserId = auditUserId;
            }

            public Object getOperateTime() {
                return operateTime;
            }

            public void setOperateTime(Object operateTime) {
                this.operateTime = operateTime;
            }

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public String getManufacturerName() {
                return manufacturerName;
            }

            public void setManufacturerName(String manufacturerName) {
                this.manufacturerName = manufacturerName;
            }

            public Object getProductCategoriesName() {
                return productCategoriesName;
            }

            public void setProductCategoriesName(Object productCategoriesName) {
                this.productCategoriesName = productCategoriesName;
            }

            public Object getPartWarrantyList() {
                return partWarrantyList;
            }

            public void setPartWarrantyList(Object partWarrantyList) {
                this.partWarrantyList = partWarrantyList;
            }

            public Object getPartImList() {
                return partImList;
            }

            public void setPartImList(Object partImList) {
                this.partImList = partImList;
            }

            public Object getReason() {
                return reason;
            }

            public void setReason(Object reason) {
                this.reason = reason;
            }

            public Object getSupplierName() {
                return supplierName;
            }

            public void setSupplierName(Object supplierName) {
                this.supplierName = supplierName;
            }

            public Object getMemberName() {
                return memberName;
            }

            public void setMemberName(Object memberName) {
                this.memberName = memberName;
            }

            public Object getAuditName() {
                return auditName;
            }

            public void setAuditName(Object auditName) {
                this.auditName = auditName;
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
