package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李浩 on 2016/11/25.
 */
public class push_personal_bean implements Serializable{

    /**
     * code : 200
     * message : 操作成功
     * body : {"data":{"informationId":179,"memberId":147,"userName":"明体力","manufacturerIds":"357","productCategoriesIds":"601","email":"jdhdbdbx@163.com","mobile":"13755794643","qq":"648487","cityId":"110100","introduction":"哦揉眼睛","description":"破嘴","createTime":1479980164000,"updateTime":1479980183000,"flag":1,"status":2,"shareNum":0,"commentNum":0,"readNum":4,"collectNum":0,"topNum":0,"contentCategoriesId":915,"biddingNum":0,"headPath":"http://192.168.1.3/images/health/knowledge/pic/6c587d59749a4c8c8e20095eaf41fac0.jpg","logoPath":"http://192.168.1.3/images/health/knowledge/pic/a3fb60b953d343da972dc50e71ca5b39.jpg","cityName":"北京市","manufacturerName":"","productCategoriesName":"","tbCompanyName":"","tbUserName":"","tbCollectId":"","levelName":"","memberName":"","statusName":"审核中","informationImages":[{"informationImagesId":238,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":3,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/0991c14ac7ed47aaaf6de0b0a4e61741.png","weight":0,"localImagePath":"images/health/knowledge/pic/0991c14ac7ed47aaaf6de0b0a4e61741.png"},{"informationImagesId":239,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/adc77dd8f04840d1ab72211eb8d66194.png","weight":1,"localImagePath":"images/health/knowledge/pic/adc77dd8f04840d1ab72211eb8d66194.png"},{"informationImagesId":240,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/650a33092b734628a4b85f763af50d6e.png","weight":2,"localImagePath":"images/health/knowledge/pic/650a33092b734628a4b85f763af50d6e.png"},{"informationImagesId":241,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":2,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/2f92b63e737444ac8f1f3dbde49fa0e8.png","weight":3,"localImagePath":"images/health/knowledge/pic/2f92b63e737444ac8f1f3dbde49fa0e8.png"},{"informationImagesId":242,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":2,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/fadc7fb677c0402aa489c6b2c1aeb00d.png","weight":4,"localImagePath":"images/health/knowledge/pic/fadc7fb677c0402aa489c6b2c1aeb00d.png"}],"productModel":[{"productCategoriesId":601,"name":"家属","singlename":"家属 1"}],"manufacturerList":[{"manufacturerId":357,"name":"测试2","singlename":"测试2","is_select":0}]},"pid":"s000016"}
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
         * data : {"informationId":179,"memberId":147,"userName":"明体力","manufacturerIds":"357","productCategoriesIds":"601","email":"jdhdbdbx@163.com","mobile":"13755794643","qq":"648487","cityId":"110100","introduction":"哦揉眼睛","description":"破嘴","createTime":1479980164000,"updateTime":1479980183000,"flag":1,"status":2,"shareNum":0,"commentNum":0,"readNum":4,"collectNum":0,"topNum":0,"contentCategoriesId":915,"biddingNum":0,"headPath":"http://192.168.1.3/images/health/knowledge/pic/6c587d59749a4c8c8e20095eaf41fac0.jpg","logoPath":"http://192.168.1.3/images/health/knowledge/pic/a3fb60b953d343da972dc50e71ca5b39.jpg","cityName":"北京市","manufacturerName":"","productCategoriesName":"","tbCompanyName":"","tbUserName":"","tbCollectId":"","levelName":"","memberName":"","statusName":"审核中","informationImages":[{"informationImagesId":238,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":3,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/0991c14ac7ed47aaaf6de0b0a4e61741.png","weight":0,"localImagePath":"images/health/knowledge/pic/0991c14ac7ed47aaaf6de0b0a4e61741.png"},{"informationImagesId":239,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/adc77dd8f04840d1ab72211eb8d66194.png","weight":1,"localImagePath":"images/health/knowledge/pic/adc77dd8f04840d1ab72211eb8d66194.png"},{"informationImagesId":240,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/650a33092b734628a4b85f763af50d6e.png","weight":2,"localImagePath":"images/health/knowledge/pic/650a33092b734628a4b85f763af50d6e.png"},{"informationImagesId":241,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":2,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/2f92b63e737444ac8f1f3dbde49fa0e8.png","weight":3,"localImagePath":"images/health/knowledge/pic/2f92b63e737444ac8f1f3dbde49fa0e8.png"},{"informationImagesId":242,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":2,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/fadc7fb677c0402aa489c6b2c1aeb00d.png","weight":4,"localImagePath":"images/health/knowledge/pic/fadc7fb677c0402aa489c6b2c1aeb00d.png"}],"productModel":[{"productCategoriesId":601,"name":"家属","singlename":"家属 1"}],"manufacturerList":[{"manufacturerId":357,"name":"测试2","singlename":"测试2","is_select":0}]}
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
             * informationId : 179
             * memberId : 147
             * userName : 明体力
             * manufacturerIds : 357
             * productCategoriesIds : 601
             * email : jdhdbdbx@163.com
             * mobile : 13755794643
             * qq : 648487
             * cityId : 110100
             * introduction : 哦揉眼睛
             * description : 破嘴
             * createTime : 1479980164000
             * updateTime : 1479980183000
             * flag : 1
             * status : 2
             * shareNum : 0
             * commentNum : 0
             * readNum : 4
             * collectNum : 0
             * topNum : 0
             * contentCategoriesId : 915
             * biddingNum : 0
             * headPath : http://192.168.1.3/images/health/knowledge/pic/6c587d59749a4c8c8e20095eaf41fac0.jpg
             * logoPath : http://192.168.1.3/images/health/knowledge/pic/a3fb60b953d343da972dc50e71ca5b39.jpg
             * cityName : 北京市
             * manufacturerName :
             * productCategoriesName :
             * tbCompanyName :
             * tbUserName :
             * tbCollectId :
             * levelName :
             * memberName :
             * statusName : 审核中
             * informationImages : [{"informationImagesId":238,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":3,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/0991c14ac7ed47aaaf6de0b0a4e61741.png","weight":0,"localImagePath":"images/health/knowledge/pic/0991c14ac7ed47aaaf6de0b0a4e61741.png"},{"informationImagesId":239,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/adc77dd8f04840d1ab72211eb8d66194.png","weight":1,"localImagePath":"images/health/knowledge/pic/adc77dd8f04840d1ab72211eb8d66194.png"},{"informationImagesId":240,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/650a33092b734628a4b85f763af50d6e.png","weight":2,"localImagePath":"images/health/knowledge/pic/650a33092b734628a4b85f763af50d6e.png"},{"informationImagesId":241,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":2,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/2f92b63e737444ac8f1f3dbde49fa0e8.png","weight":3,"localImagePath":"images/health/knowledge/pic/2f92b63e737444ac8f1f3dbde49fa0e8.png"},{"informationImagesId":242,"informationId":179,"updateTime":null,"createTime":1479980164000,"flag":null,"createUserId":147,"lastUpdateUserId":null,"type":2,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/fadc7fb677c0402aa489c6b2c1aeb00d.png","weight":4,"localImagePath":"images/health/knowledge/pic/fadc7fb677c0402aa489c6b2c1aeb00d.png"}]
             * productModel : [{"productCategoriesId":601,"name":"家属","singlename":"家属 1"}]
             * manufacturerList : [{"manufacturerId":357,"name":"测试2","singlename":"测试2","is_select":0}]
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
                 * informationImagesId : 238
                 * informationId : 179
                 * updateTime : null
                 * createTime : 1479980164000
                 * flag : null
                 * createUserId : 147
                 * lastUpdateUserId : null
                 * type : 3
                 * imagePath : http://192.168.1.3/images/health/knowledge/pic/0991c14ac7ed47aaaf6de0b0a4e61741.png
                 * weight : 0
                 * localImagePath : images/health/knowledge/pic/0991c14ac7ed47aaaf6de0b0a4e61741.png
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

            public static class ProductModelBean implements Serializable{
                /**
                 * productCategoriesId : 601
                 * name : 家属
                 * singlename : 家属 1
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
