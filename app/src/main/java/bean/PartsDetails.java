package bean;

import java.util.List;

/**
 * Created by 李浩 on 2017/8/18.
 */

public class PartsDetails {


    /**
     * code : 200
     * message : 配件查询成功！
     * body : {"data":{"partId":8,"createMemberId":null,"supplierId":null,"name":"rwerqwer","showImage":null,"manufacturerId":386,"productCategoriesId":602,"partNo":"234234","serialNumber":"234234","warrantyPeriod":null,"price":null,"moneyUnit":null,"inventory":23423,"sold":null,"forSale":null,"quality":0,"status":null,"flag":null,"productTime":"2017-08-03","remark":"234324","description":"234234324","readCount":0,"createTime":null,"updateTime":null,"updateMemberId":null,"auditTime":null,"auditUserId":null,"operateTime":null,"statusName":"暂无","manufacturerName":null,"productCategoriesName":null,"partWarrantyList":[{"warrantyId":14,"partId":8,"num":1,"type":1,"price":12,"typeName":"年"}],"partImList":[{"partImageId":19,"partId":10,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"imagePath":"http://192.168.1.3//images/201708/20170803135706_990.jpg"}],"reason":null,"supplierName":null,"memberName":null,"auditName":null,"sdate":null,"edate":null},"pid":null}
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
         * data : {"partId":8,"createMemberId":null,"supplierId":null,"name":"rwerqwer","showImage":null,"manufacturerId":386,"productCategoriesId":602,"partNo":"234234","serialNumber":"234234","warrantyPeriod":null,"price":null,"moneyUnit":null,"inventory":23423,"sold":null,"forSale":null,"quality":0,"status":null,"flag":null,"productTime":"2017-08-03","remark":"234324","description":"234234324","readCount":0,"createTime":null,"updateTime":null,"updateMemberId":null,"auditTime":null,"auditUserId":null,"operateTime":null,"statusName":"暂无","manufacturerName":null,"productCategoriesName":null,"partWarrantyList":[{"warrantyId":14,"partId":8,"num":1,"type":1,"price":12,"typeName":"年"}],"partImList":[{"partImageId":19,"partId":10,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"imagePath":"http://192.168.1.3//images/201708/20170803135706_990.jpg"}],"reason":null,"supplierName":null,"memberName":null,"auditName":null,"sdate":null,"edate":null}
         * pid : null
         */

        private DataBean data;
        private Object pid;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public Object getPid() {
            return pid;
        }

        public void setPid(Object pid) {
            this.pid = pid;
        }

        public static class DataBean {
            /**
             * partId : 8
             * createMemberId : null
             * supplierId : null
             * name : rwerqwer
             * showImage : null
             * manufacturerId : 386
             * productCategoriesId : 602
             * partNo : 234234
             * serialNumber : 234234
             * warrantyPeriod : null
             * price : null
             * moneyUnit : null
             * inventory : 23423
             * sold : null
             * forSale : null
             * quality : 0
             * status : null
             * flag : null
             * productTime : 2017-08-03
             * remark : 234324
             * description : 234234324
             * readCount : 0
             * createTime : null
             * updateTime : null
             * updateMemberId : null
             * auditTime : null
             * auditUserId : null
             * operateTime : null
             * statusName : 暂无
             * manufacturerName : null
             * productCategoriesName : null
             * partWarrantyList : [{"warrantyId":14,"partId":8,"num":1,"type":1,"price":12,"typeName":"年"}]
             * partImList : [{"partImageId":19,"partId":10,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"imagePath":"http://192.168.1.3//images/201708/20170803135706_990.jpg"}]
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
            private Object showImage;
            private int manufacturerId;
            private int productCategoriesId;
            private String partNo;
            private String serialNumber;
            private Object warrantyPeriod;
            private String price;
            private Object moneyUnit;
            private int inventory;
            private Object sold;
            private Object forSale;
            private int quality;
            private Object status;
            private Object flag;
            private String productTime;
            private String remark;
            private String description;
            private int readCount;
            private Object createTime;
            private Object updateTime;
            private Object updateMemberId;
            private Object auditTime;
            private Object auditUserId;
            private Object operateTime;
            private String statusName;
            private Object manufacturerName;
            private Object productCategoriesName;
            private Object reason;
            private Object supplierName;
            private Object memberName;
            private Object auditName;
            private Object sdate;
            private Object edate;
            private List<PartWarrantyListBean> partWarrantyList;
            private List<PartImListBean> partImList;

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

            public Object getShowImage() {
                return showImage;
            }

            public void setShowImage(Object showImage) {
                this.showImage = showImage;
            }

            public int getManufacturerId() {
                return manufacturerId;
            }

            public void setManufacturerId(int manufacturerId) {
                this.manufacturerId = manufacturerId;
            }

            public int getProductCategoriesId() {
                return productCategoriesId;
            }

            public void setProductCategoriesId(int productCategoriesId) {
                this.productCategoriesId = productCategoriesId;
            }

            public String getPartNo() {
                return partNo;
            }

            public void setPartNo(String partNo) {
                this.partNo = partNo;
            }

            public String getSerialNumber() {
                return serialNumber;
            }

            public void setSerialNumber(String serialNumber) {
                this.serialNumber = serialNumber;
            }

            public Object getWarrantyPeriod() {
                return warrantyPeriod;
            }

            public void setWarrantyPeriod(Object warrantyPeriod) {
                this.warrantyPeriod = warrantyPeriod;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public Object getMoneyUnit() {
                return moneyUnit;
            }

            public void setMoneyUnit(Object moneyUnit) {
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

            public int getQuality() {
                return quality;
            }

            public void setQuality(int quality) {
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

            public String getProductTime() {
                return productTime;
            }

            public void setProductTime(String productTime) {
                this.productTime = productTime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getReadCount() {
                return readCount;
            }

            public void setReadCount(int readCount) {
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

            public Object getManufacturerName() {
                return manufacturerName;
            }

            public void setManufacturerName(Object manufacturerName) {
                this.manufacturerName = manufacturerName;
            }

            public Object getProductCategoriesName() {
                return productCategoriesName;
            }

            public void setProductCategoriesName(Object productCategoriesName) {
                this.productCategoriesName = productCategoriesName;
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

            public List<PartWarrantyListBean> getPartWarrantyList() {
                return partWarrantyList;
            }

            public void setPartWarrantyList(List<PartWarrantyListBean> partWarrantyList) {
                this.partWarrantyList = partWarrantyList;
            }

            public List<PartImListBean> getPartImList() {
                return partImList;
            }

            public void setPartImList(List<PartImListBean> partImList) {
                this.partImList = partImList;
            }

            public static class PartWarrantyListBean {
                /**
                 * warrantyId : 14
                 * partId : 8
                 * num : 1
                 * type : 1
                 * price : 12
                 * typeName : 年
                 */

                private int warrantyId;
                private int partId;
                private int num;
                private int type;
                private int price;
                private String typeName;

                public int getWarrantyId() {
                    return warrantyId;
                }

                public void setWarrantyId(int warrantyId) {
                    this.warrantyId = warrantyId;
                }

                public int getPartId() {
                    return partId;
                }

                public void setPartId(int partId) {
                    this.partId = partId;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public String getTypeName() {
                    return typeName;
                }

                public void setTypeName(String typeName) {
                    this.typeName = typeName;
                }
            }

            public static class PartImListBean {
                /**
                 * partImageId : 19
                 * partId : 10
                 * createTime : null
                 * createUserId : null
                 * updateTime : null
                 * updateUserId : null
                 * imagePath : http://192.168.1.3//images/201708/20170803135706_990.jpg
                 */

                private int partImageId;
                private int partId;
                private Object createTime;
                private Object createUserId;
                private Object updateTime;
                private Object updateUserId;
                private String imagePath;

                public int getPartImageId() {
                    return partImageId;
                }

                public void setPartImageId(int partImageId) {
                    this.partImageId = partImageId;
                }

                public int getPartId() {
                    return partId;
                }

                public void setPartId(int partId) {
                    this.partId = partId;
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

                public String getImagePath() {
                    return imagePath;
                }

                public void setImagePath(String imagePath) {
                    this.imagePath = imagePath;
                }
            }
        }
    }
}
