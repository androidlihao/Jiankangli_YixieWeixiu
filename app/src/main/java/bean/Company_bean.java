package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李浩 on 2016/11/19.
 */
public class Company_bean implements Serializable{

    /**
     * code : 200
     * message : 操作成功
     * body : {"data":{"companyId":79,"memberId":147,"userName":"哄哄","email":"fjfhfbb@163.com","mobile":"13755794643","qq":"867848","cityId":"140581","introduction":"头LOL呀","companyName":"毫无意义","createTime":1479459952000,"updateTime":1479459970000,"flag":1,"status":2,"failReason":null,"lastUserId":null,"legalPerson":"给你","address":"哄哄","startTime":null,"endTime":null,"cityName":"高平市","cityParentId":null,"companyImages":null,"picture":[{"companyImagesId":69,"companyId":79,"updateTime":null,"createTime":1479460831000,"flag":1,"createUserId":1822,"lastUpdateUserId":null,"type":4,"imagePath":"http://192.168.1.3//images/201610/20161024114836_0.jpg","localImagePath":"/images/201610/20161024114836_0.jpg","weight":1}],"answerImages":null},"pid":"s00022"}
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

    public static class BodyBean implements Serializable{
        /**
         * data : {"companyId":79,"memberId":147,"userName":"哄哄","email":"fjfhfbb@163.com","mobile":"13755794643","qq":"867848","cityId":"140581","introduction":"头LOL呀","companyName":"毫无意义","createTime":1479459952000,"updateTime":1479459970000,"flag":1,"status":2,"failReason":null,"lastUserId":null,"legalPerson":"给你","address":"哄哄","startTime":null,"endTime":null,"cityName":"高平市","cityParentId":null,"companyImages":null,"picture":[{"companyImagesId":69,"companyId":79,"updateTime":null,"createTime":1479460831000,"flag":1,"createUserId":1822,"lastUpdateUserId":null,"type":4,"imagePath":"http://192.168.1.3//images/201610/20161024114836_0.jpg","localImagePath":"/images/201610/20161024114836_0.jpg","weight":1}],"answerImages":null}
         * pid : s00022
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
             * companyId : 79
             * memberId : 147
             * userName : 哄哄
             * email : fjfhfbb@163.com
             * mobile : 13755794643
             * qq : 867848
             * cityId : 140581
             * introduction : 头LOL呀
             * companyName : 毫无意义
             * createTime : 1479459952000
             * updateTime : 1479459970000
             * flag : 1
             * status : 2
             * failReason : null
             * lastUserId : null
             * legalPerson : 给你
             * address : 哄哄
             * startTime : null
             * endTime : null
             * cityName : 高平市
             * cityParentId : null
             * companyImages : null
             * picture : [{"companyImagesId":69,"companyId":79,"updateTime":null,"createTime":1479460831000,"flag":1,"createUserId":1822,"lastUpdateUserId":null,"type":4,"imagePath":"http://192.168.1.3//images/201610/20161024114836_0.jpg","localImagePath":"/images/201610/20161024114836_0.jpg","weight":1}]
             * answerImages : null
             */

            private int companyId;
            private int memberId;
            private String userName;
            private String email;
            private String mobile;
            private String qq;
            private String cityId;
            private String introduction;
            private String companyName;
            private long createTime;
            private long updateTime;
            private int flag;
            private int status;
            private Object failReason;
            private Object lastUserId;
            private String legalPerson;
            private String address;
            private Object startTime;
            private Object endTime;
            private String cityName;
            private Object cityParentId;
            private Object companyImages;
            private Object answerImages;
            private List<PictureBean> picture;

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
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

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
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

            public Object getFailReason() {
                return failReason;
            }

            public void setFailReason(Object failReason) {
                this.failReason = failReason;
            }

            public Object getLastUserId() {
                return lastUserId;
            }

            public void setLastUserId(Object lastUserId) {
                this.lastUserId = lastUserId;
            }

            public String getLegalPerson() {
                return legalPerson;
            }

            public void setLegalPerson(String legalPerson) {
                this.legalPerson = legalPerson;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public Object getCityParentId() {
                return cityParentId;
            }

            public void setCityParentId(Object cityParentId) {
                this.cityParentId = cityParentId;
            }

            public Object getCompanyImages() {
                return companyImages;
            }

            public void setCompanyImages(Object companyImages) {
                this.companyImages = companyImages;
            }

            public Object getAnswerImages() {
                return answerImages;
            }

            public void setAnswerImages(Object answerImages) {
                this.answerImages = answerImages;
            }

            public List<PictureBean> getPicture() {
                return picture;
            }

            public void setPicture(List<PictureBean> picture) {
                this.picture = picture;
            }

            public static class PictureBean implements Serializable{
                /**
                 * companyImagesId : 69
                 * companyId : 79
                 * updateTime : null
                 * createTime : 1479460831000
                 * flag : 1
                 * createUserId : 1822
                 * lastUpdateUserId : null
                 * type : 4
                 * imagePath : http://192.168.1.3//images/201610/20161024114836_0.jpg
                 * localImagePath : /images/201610/20161024114836_0.jpg
                 * weight : 1
                 */

                private int companyImagesId;
                private int companyId;
                private Object updateTime;
                private long createTime;
                private int flag;
                private int createUserId;
                private Object lastUpdateUserId;
                private int type;
                private String imagePath;
                private String localImagePath;
                private int weight;

                public int getCompanyImagesId() {
                    return companyImagesId;
                }

                public void setCompanyImagesId(int companyImagesId) {
                    this.companyImagesId = companyImagesId;
                }

                public int getCompanyId() {
                    return companyId;
                }

                public void setCompanyId(int companyId) {
                    this.companyId = companyId;
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

                public int getFlag() {
                    return flag;
                }

                public void setFlag(int flag) {
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

                public String getLocalImagePath() {
                    return localImagePath;
                }

                public void setLocalImagePath(String localImagePath) {
                    this.localImagePath = localImagePath;
                }

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
                    this.weight = weight;
                }
            }
        }
    }
}
