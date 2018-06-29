package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李浩 on 2016/11/25.
 */
public class push_company_bean implements Serializable{

    /**
     * code : 200
     * message : 操作成功
     * body : {"data":{"informationId":180,"memberId":147,"userName":"inn呼呼呼","manufacturerIds":"357","productCategoriesIds":"76","email":"fjndbdjdn@163.com","mobile":"13755794643","qq":"6457588","cityId":"120100","introduction":"全心全意","description":"SMS我","companyName":"or您曲终人未散呀始终如一去","createTime":1479980752000,"updateTime":1480065276000,"flag":1,"status":2,"shareNum":0,"commentNum":0,"readNum":10,"collectNum":0,"topNum":0,"contentCategoriesId":916,"biddingNum":0,"headPath":"http://192.168.1.3/images/health/knowledge/pic/6c587d59749a4c8c8e20095eaf41fac0.jpg","logoPath":"http://192.168.1.3/images/health/knowledge/pic/a3fb60b953d343da972dc50e71ca5b39.jpg","cityName":"天津市","manufacturerName":"","productCategoriesName":"","tbCompanyName":"","tbUserName":"","tbCollectId":"","levelName":"","memberName":"","statusName":"审核中","informationImages":[{"informationImagesId":382,"informationId":180,"updateTime":null,"createTime":1480065276000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/b1fc7c71682f4c3c89ca75ce67dba74a.png","weight":null,"localImagePath":"images/health/knowledge/pic/b1fc7c71682f4c3c89ca75ce67dba74a.png"},{"informationImagesId":383,"informationId":180,"updateTime":null,"createTime":1480065276000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":2,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/a439db6416e64cfa83eebac10ca984bd.png","weight":null,"localImagePath":"images/health/knowledge/pic/a439db6416e64cfa83eebac10ca984bd.png"}],"productModel":[{"productCategoriesId":76,"name":"123213213213","singlename":"12321321"}],"manufacturerList":[{"manufacturerId":357,"name":"测试2","singlename":"测试2","is_select":0}],"companyStatus":"2"},"pid":"s000016"}
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
         * data : {"informationId":180,"memberId":147,"userName":"inn呼呼呼","manufacturerIds":"357","productCategoriesIds":"76","email":"fjndbdjdn@163.com","mobile":"13755794643","qq":"6457588","cityId":"120100","introduction":"全心全意","description":"SMS我","companyName":"or您曲终人未散呀始终如一去","createTime":1479980752000,"updateTime":1480065276000,"flag":1,"status":2,"shareNum":0,"commentNum":0,"readNum":10,"collectNum":0,"topNum":0,"contentCategoriesId":916,"biddingNum":0,"headPath":"http://192.168.1.3/images/health/knowledge/pic/6c587d59749a4c8c8e20095eaf41fac0.jpg","logoPath":"http://192.168.1.3/images/health/knowledge/pic/a3fb60b953d343da972dc50e71ca5b39.jpg","cityName":"天津市","manufacturerName":"","productCategoriesName":"","tbCompanyName":"","tbUserName":"","tbCollectId":"","levelName":"","memberName":"","statusName":"审核中","informationImages":[{"informationImagesId":382,"informationId":180,"updateTime":null,"createTime":1480065276000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/b1fc7c71682f4c3c89ca75ce67dba74a.png","weight":null,"localImagePath":"images/health/knowledge/pic/b1fc7c71682f4c3c89ca75ce67dba74a.png"},{"informationImagesId":383,"informationId":180,"updateTime":null,"createTime":1480065276000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":2,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/a439db6416e64cfa83eebac10ca984bd.png","weight":null,"localImagePath":"images/health/knowledge/pic/a439db6416e64cfa83eebac10ca984bd.png"}],"productModel":[{"productCategoriesId":76,"name":"123213213213","singlename":"12321321"}],"manufacturerList":[{"manufacturerId":357,"name":"测试2","singlename":"测试2","is_select":0}],"companyStatus":"2"}
         * pid : s000016
         */

        private DataBean data;
        private String pid;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public static class DataBean implements Serializable{
            /**
             * informationId : 180
             * memberId : 147
             * userName : inn呼呼呼
             * manufacturerIds : 357
             * productCategoriesIds : 76
             * email : fjndbdjdn@163.com
             * mobile : 13755794643
             * qq : 6457588
             * cityId : 120100
             * introduction : 全心全意
             * description : SMS我
             * companyName : or您曲终人未散呀始终如一去
             * createTime : 1479980752000
             * updateTime : 1480065276000
             * flag : 1
             * status : 2
             * shareNum : 0
             * commentNum : 0
             * readNum : 10
             * collectNum : 0
             * topNum : 0
             * contentCategoriesId : 916
             * biddingNum : 0
             * headPath : http://192.168.1.3/images/health/knowledge/pic/6c587d59749a4c8c8e20095eaf41fac0.jpg
             * logoPath : http://192.168.1.3/images/health/knowledge/pic/a3fb60b953d343da972dc50e71ca5b39.jpg
             * cityName : 天津市
             * manufacturerName :
             * productCategoriesName :
             * tbCompanyName :
             * tbUserName :
             * tbCollectId :
             * levelName :
             * memberName :
             * statusName : 审核中
             * informationImages : [{"informationImagesId":382,"informationId":180,"updateTime":null,"createTime":1480065276000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/b1fc7c71682f4c3c89ca75ce67dba74a.png","weight":null,"localImagePath":"images/health/knowledge/pic/b1fc7c71682f4c3c89ca75ce67dba74a.png"},{"informationImagesId":383,"informationId":180,"updateTime":null,"createTime":1480065276000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":2,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/a439db6416e64cfa83eebac10ca984bd.png","weight":null,"localImagePath":"images/health/knowledge/pic/a439db6416e64cfa83eebac10ca984bd.png"}]
             * productModel : [{"productCategoriesId":76,"name":"123213213213","singlename":"12321321"}]
             * manufacturerList : [{"manufacturerId":357,"name":"测试2","singlename":"测试2","is_select":0}]
             * companyStatus : 2
             */

            private int informationId;
            private int memberId;
            private String userName;
            private String manufacturerIds;
            private String productCategoriesIds;
            private String email;
            private String mobile;
            private String qq;
            private String cityId;
            private String introduction;
            private String description;
            private String companyName;
            private long createTime;
            private long updateTime;
            private int flag;
            private int status;
            private int shareNum;
            private int commentNum;
            private int readNum;
            private int collectNum;
            private int topNum;
            private int contentCategoriesId;
            private int biddingNum;
            private String headPath;
            private String logoPath;
            private String cityName;
            private String manufacturerName;
            private String productCategoriesName;
            private String tbCompanyName;
            private String tbUserName;
            private String tbCollectId;
            private String levelName;
            private String memberName;
            private String statusName;
            private String companyStatus;
            private List<InformationImagesBean> informationImages;
            private List<ProductModelBean> productModel;
            private List<ManufacturerListBean> manufacturerList;

            public int getInformationId() {
                return informationId;
            }

            public void setInformationId(int informationId) {
                this.informationId = informationId;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getManufacturerIds() {
                return manufacturerIds;
            }

            public void setManufacturerIds(String manufacturerIds) {
                this.manufacturerIds = manufacturerIds;
            }

            public String getProductCategoriesIds() {
                return productCategoriesIds;
            }

            public void setProductCategoriesIds(String productCategoriesIds) {
                this.productCategoriesIds = productCategoriesIds;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getShareNum() {
                return shareNum;
            }

            public void setShareNum(int shareNum) {
                this.shareNum = shareNum;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }

            public int getReadNum() {
                return readNum;
            }

            public void setReadNum(int readNum) {
                this.readNum = readNum;
            }

            public int getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(int collectNum) {
                this.collectNum = collectNum;
            }

            public int getTopNum() {
                return topNum;
            }

            public void setTopNum(int topNum) {
                this.topNum = topNum;
            }

            public int getContentCategoriesId() {
                return contentCategoriesId;
            }

            public void setContentCategoriesId(int contentCategoriesId) {
                this.contentCategoriesId = contentCategoriesId;
            }

            public int getBiddingNum() {
                return biddingNum;
            }

            public void setBiddingNum(int biddingNum) {
                this.biddingNum = biddingNum;
            }

            public String getHeadPath() {
                return headPath;
            }

            public void setHeadPath(String headPath) {
                this.headPath = headPath;
            }

            public String getLogoPath() {
                return logoPath;
            }

            public void setLogoPath(String logoPath) {
                this.logoPath = logoPath;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
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

            public String getTbCompanyName() {
                return tbCompanyName;
            }

            public void setTbCompanyName(String tbCompanyName) {
                this.tbCompanyName = tbCompanyName;
            }

            public String getTbUserName() {
                return tbUserName;
            }

            public void setTbUserName(String tbUserName) {
                this.tbUserName = tbUserName;
            }

            public String getTbCollectId() {
                return tbCollectId;
            }

            public void setTbCollectId(String tbCollectId) {
                this.tbCollectId = tbCollectId;
            }

            public String getLevelName() {
                return levelName;
            }

            public void setLevelName(String levelName) {
                this.levelName = levelName;
            }

            public String getMemberName() {
                return memberName;
            }

            public void setMemberName(String memberName) {
                this.memberName = memberName;
            }

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public String getCompanyStatus() {
                return companyStatus;
            }

            public void setCompanyStatus(String companyStatus) {
                this.companyStatus = companyStatus;
            }

            public List<InformationImagesBean> getInformationImages() {
                return informationImages;
            }

            public void setInformationImages(List<InformationImagesBean> informationImages) {
                this.informationImages = informationImages;
            }

            public List<ProductModelBean> getProductModel() {
                return productModel;
            }

            public void setProductModel(List<ProductModelBean> productModel) {
                this.productModel = productModel;
            }

            public List<ManufacturerListBean> getManufacturerList() {
                return manufacturerList;
            }

            public void setManufacturerList(List<ManufacturerListBean> manufacturerList) {
                this.manufacturerList = manufacturerList;
            }

            public static class InformationImagesBean implements Serializable{
                /**
                 * informationImagesId : 382
                 * informationId : 180
                 * updateTime : null
                 * createTime : 1480065276000
                 * flag : null
                 * createUserId : 147
                 * lastUpdateUserId : null
                 * type : 1
                 * imagePath : http://192.168.1.3/images/health/knowledge/pic/b1fc7c71682f4c3c89ca75ce67dba74a.png
                 * weight : null
                 * localImagePath : images/health/knowledge/pic/b1fc7c71682f4c3c89ca75ce67dba74a.png
                 */

                private int informationImagesId;
                private int informationId;
                private Object updateTime;
                private long createTime;
                private Object flag;
                private int createUserId;
                private Object lastUpdateUserId;
                private int type;
                private String imagePath;
                private Object weight;
                private String localImagePath;

                public int getInformationImagesId() {
                    return informationImagesId;
                }

                public void setInformationImagesId(int informationImagesId) {
                    this.informationImagesId = informationImagesId;
                }

                public int getInformationId() {
                    return informationId;
                }

                public void setInformationId(int informationId) {
                    this.informationId = informationId;
                }

                public Object getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(Object updateTime) {
                    this.updateTime = updateTime;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public Object getFlag() {
                    return flag;
                }

                public void setFlag(Object flag) {
                    this.flag = flag;
                }

                public int getCreateUserId() {
                    return createUserId;
                }

                public void setCreateUserId(int createUserId) {
                    this.createUserId = createUserId;
                }

                public Object getLastUpdateUserId() {
                    return lastUpdateUserId;
                }

                public void setLastUpdateUserId(Object lastUpdateUserId) {
                    this.lastUpdateUserId = lastUpdateUserId;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getImagePath() {
                    return imagePath;
                }

                public void setImagePath(String imagePath) {
                    this.imagePath = imagePath;
                }

                public Object getWeight() {
                    return weight;
                }

                public void setWeight(Object weight) {
                    this.weight = weight;
                }

                public String getLocalImagePath() {
                    return localImagePath;
                }

                public void setLocalImagePath(String localImagePath) {
                    this.localImagePath = localImagePath;
                }
            }

            public static class ProductModelBean implements Serializable{
                /**
                 * productCategoriesId : 76
                 * name : 123213213213
                 * singlename : 12321321
                 */

                private int productCategoriesId;
                private String name;
                private String singlename;

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
            }

            public static class ManufacturerListBean implements Serializable{
                /**
                 * manufacturerId : 357
                 * name : 测试2
                 * singlename : 测试2
                 * is_select : 0
                 */

                private int manufacturerId;
                private String name;
                private String singlename;
                private int is_select;

                public int getManufacturerId() {
                    return manufacturerId;
                }

                public void setManufacturerId(int manufacturerId) {
                    this.manufacturerId = manufacturerId;
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

                public int getIs_select() {
                    return is_select;
                }

                public void setIs_select(int is_select) {
                    this.is_select = is_select;
                }
            }
        }
    }
}
