package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李浩 on 2016/11/28.
 */
public class push_other_bean implements Serializable{

    /**
     * code : 200
     * message : 查询成功！
     * body : {"data":{"informationId":254,"memberId":147,"userName":"唐敏","email":"djdjdhdh@163.com","mobile":"13755794643","qq":"245468787","cityId":"120100","description":"送手机","createTime":1480320733000,"updateTime":1480320733000,"flag":1,"status":2,"shareNum":0,"commentNum":0,"readNum":0,"collectNum":0,"topNum":0,"contentCategoriesId":5,"biddingNum":0,"headPath":"http://192.168.1.3/images/health/knowledge/pic/6a880fb2ebe24734b039b20932cae801.jpg","logoPath":"http://192.168.1.3/images/health/knowledge/pic/91e0c3222f8249d0bd08612d084774e3.png","cityName":"天津市","manufacturerName":"","productCategoriesName":"","tbCompanyName":"","tbUserName":"","tbCollectId":"","levelName":"","memberName":"","statusName":"审核中","informationImages":[{"informationImagesId":627,"informationId":254,"updateTime":null,"createTime":1480320714000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":3,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/c6c75e4e081b43f2af729be9c0c990b0.png","weight":0,"localImagePath":"images/health/knowledge/pic/c6c75e4e081b43f2af729be9c0c990b0.png"}],"productModel":[],"manufacturerList":[],"companyStatus":"2"}}
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
         * data : {"informationId":254,"memberId":147,"userName":"唐敏","email":"djdjdhdh@163.com","mobile":"13755794643","qq":"245468787","cityId":"120100","description":"送手机","createTime":1480320733000,"updateTime":1480320733000,"flag":1,"status":2,"shareNum":0,"commentNum":0,"readNum":0,"collectNum":0,"topNum":0,"contentCategoriesId":5,"biddingNum":0,"headPath":"http://192.168.1.3/images/health/knowledge/pic/6a880fb2ebe24734b039b20932cae801.jpg","logoPath":"http://192.168.1.3/images/health/knowledge/pic/91e0c3222f8249d0bd08612d084774e3.png","cityName":"天津市","manufacturerName":"","productCategoriesName":"","tbCompanyName":"","tbUserName":"","tbCollectId":"","levelName":"","memberName":"","statusName":"审核中","informationImages":[{"informationImagesId":627,"informationId":254,"updateTime":null,"createTime":1480320714000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":3,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/c6c75e4e081b43f2af729be9c0c990b0.png","weight":0,"localImagePath":"images/health/knowledge/pic/c6c75e4e081b43f2af729be9c0c990b0.png"}],"productModel":[],"manufacturerList":[],"companyStatus":"2"}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{
            /**
             * informationId : 254
             * memberId : 147
             * userName : 唐敏
             * email : djdjdhdh@163.com
             * mobile : 13755794643
             * qq : 245468787
             * cityId : 120100
             * description : 送手机
             * createTime : 1480320733000
             * updateTime : 1480320733000
             * flag : 1
             * status : 2
             * shareNum : 0
             * commentNum : 0
             * readNum : 0
             * collectNum : 0
             * topNum : 0
             * contentCategoriesId : 5
             * biddingNum : 0
             * headPath : http://192.168.1.3/images/health/knowledge/pic/6a880fb2ebe24734b039b20932cae801.jpg
             * logoPath : http://192.168.1.3/images/health/knowledge/pic/91e0c3222f8249d0bd08612d084774e3.png
             * cityName : 天津市
             * manufacturerName :
             * productCategoriesName :
             * tbCompanyName :
             * tbUserName :
             * tbCollectId :
             * levelName :
             * memberName :
             * statusName : 审核中
             * informationImages : [{"informationImagesId":627,"informationId":254,"updateTime":null,"createTime":1480320714000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":3,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/c6c75e4e081b43f2af729be9c0c990b0.png","weight":0,"localImagePath":"images/health/knowledge/pic/c6c75e4e081b43f2af729be9c0c990b0.png"}]
             * productModel : []
             * manufacturerList : []
             * companyStatus : 2
             */
            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            private int informationId;
            private int memberId;
            private String userName;
            private String email;
            private String mobile;
            private String qq;
            private String cityId;
            private String description;
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
            private List<?> productModel;
            private List<?> manufacturerList;

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public List<?> getProductModel() {
                return productModel;
            }

            public void setProductModel(List<?> productModel) {
                this.productModel = productModel;
            }

            public List<?> getManufacturerList() {
                return manufacturerList;
            }

            public void setManufacturerList(List<?> manufacturerList) {
                this.manufacturerList = manufacturerList;
            }

            public static class InformationImagesBean implements Serializable{
                /**
                 * informationImagesId : 627
                 * informationId : 254
                 * updateTime : null
                 * createTime : 1480320714000
                 * flag : null
                 * createUserId : 147
                 * lastUpdateUserId : null
                 * type : 3
                 * imagePath : http://192.168.1.3/images/health/knowledge/pic/c6c75e4e081b43f2af729be9c0c990b0.png
                 * weight : 0
                 * localImagePath : images/health/knowledge/pic/c6c75e4e081b43f2af729be9c0c990b0.png
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
                private int weight;
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

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
                    this.weight = weight;
                }

                public String getLocalImagePath() {
                    return localImagePath;
                }

                public void setLocalImagePath(String localImagePath) {
                    this.localImagePath = localImagePath;
                }
            }
        }
    }
}
