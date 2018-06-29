package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李浩 on 2016/10/11.
 */
public class info implements Serializable{


    /**
     * code : 200
     * message : 基础信息查询成功！
     * body : {"data":{"memberId":147,"pwd":"e10adc3949ba59abbe56e057f20f883e","sex":1,"name":"李浩","updateTime":1477659204000,"createTime":1477659204000,"flag":1,"integral":50,"nickname":"一个独立的个体","phone":"13755794643","attentionSkillsId":"78,73,77,601,76,75","productModel":[{"productCategoriesId":78,"updateTime":1477481126000,"createTime":1477481126000,"flag":1,"name":"133","singlename":"133","status":2,"upContentNum":0,"contentNum":0,"weight":0},{"productCategoriesId":73,"updateTime":1477381831000,"createTime":1476610132000,"flag":1,"lastUpdateUserId":1,"name":"分类","singlename":"分类","status":2,"upContentNum":0,"contentNum":0,"weight":0},{"productCategoriesId":77,"updateTime":1477452062000,"createTime":1477452062000,"flag":1,"name":"Ty","singlename":"Ty","status":2,"upContentNum":0,"contentNum":0,"weight":0},{"productCategoriesId":601,"updateTime":1475128939000,"createTime":1475128939000,"flag":1,"createUserId":1,"name":"家属","singlename":"家属 1","status":5,"upContentNum":0,"contentNum":1,"failReason":"","weight":224},{"productCategoriesId":76,"updateTime":1477383098000,"createTime":1477382615000,"flag":1,"createUserId":1,"lastUpdateUserId":1,"name":"123213213213","singlename":"12321321","status":3,"icon":"/images/201610/20161025161129_440.jpg","upContentNum":0,"contentNum":0,"failReason":"","weight":123123},{"productCategoriesId":75,"updateTime":1477382571000,"createTime":1477381483000,"flag":1,"createUserId":1,"lastUpdateUserId":1,"name":"daf","singlename":"daf","status":3,"icon":"/images/201610/20161025154443_201.jpg","upContentNum":0,"contentNum":0,"failReason":"","weight":23}],"knowledgeNum":0,"registerSourceType":2,"loginTime":1479539367000,"token":"5a87285c926b4cd9bc4f3fa0efc856c3","snstype":1,"commentNum":-1,"readNum":0,"collectNum":0,"topNum":0,"shareNum":0,"headPath":"http://192.168.1.3/images/health/knowledge/pic/01af5a1ab35548f5832f084540964bf9.jpg","status":1,"currentIntegral":1255,"upContentNum":0,"levelName":"lv5","birthday":945014400000,"fCode":"CpEsGd1f732","companyStatus":"2"},"pid":"s00011"}
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
         * data : {"memberId":147,"pwd":"e10adc3949ba59abbe56e057f20f883e","sex":1,"name":"李浩","updateTime":1477659204000,"createTime":1477659204000,"flag":1,"integral":50,"nickname":"一个独立的个体","phone":"13755794643","attentionSkillsId":"78,73,77,601,76,75","productModel":[{"productCategoriesId":78,"updateTime":1477481126000,"createTime":1477481126000,"flag":1,"name":"133","singlename":"133","status":2,"upContentNum":0,"contentNum":0,"weight":0,"lastUpdateUserId":1,"createUserId":1,"failReason":"","icon":"/images/201610/20161025161129_440.jpg"},{"productCategoriesId":73,"updateTime":1477381831000,"createTime":1476610132000,"flag":1,"lastUpdateUserId":1,"name":"分类","singlename":"分类","status":2,"upContentNum":0,"contentNum":0,"weight":0},{"productCategoriesId":77,"updateTime":1477452062000,"createTime":1477452062000,"flag":1,"name":"Ty","singlename":"Ty","status":2,"upContentNum":0,"contentNum":0,"weight":0},{"productCategoriesId":601,"updateTime":1475128939000,"createTime":1475128939000,"flag":1,"createUserId":1,"name":"家属","singlename":"家属 1","status":5,"upContentNum":0,"contentNum":1,"failReason":"","weight":224},{"productCategoriesId":76,"updateTime":1477383098000,"createTime":1477382615000,"flag":1,"createUserId":1,"lastUpdateUserId":1,"name":"123213213213","singlename":"12321321","status":3,"icon":"/images/201610/20161025161129_440.jpg","upContentNum":0,"contentNum":0,"failReason":"","weight":123123},{"productCategoriesId":75,"updateTime":1477382571000,"createTime":1477381483000,"flag":1,"createUserId":1,"lastUpdateUserId":1,"name":"daf","singlename":"daf","status":3,"icon":"/images/201610/20161025154443_201.jpg","upContentNum":0,"contentNum":0,"failReason":"","weight":23}],"knowledgeNum":0,"registerSourceType":2,"loginTime":1479539367000,"token":"5a87285c926b4cd9bc4f3fa0efc856c3","snstype":1,"commentNum":-1,"readNum":0,"collectNum":0,"topNum":0,"shareNum":0,"headPath":"http://192.168.1.3/images/health/knowledge/pic/01af5a1ab35548f5832f084540964bf9.jpg","status":1,"currentIntegral":1255,"upContentNum":0,"levelName":"lv5","birthday":945014400000,"fCode":"CpEsGd1f732","companyStatus":"2"}
         * pid : s00011
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

        public static class DataBean {
            /**
             * memberId : 147
             * pwd : e10adc3949ba59abbe56e057f20f883e
             * sex : 1
             * name : 李浩
             * updateTime : 1477659204000
             * createTime : 1477659204000
             * flag : 1
             * integral : 50
             * nickname : 一个独立的个体
             * phone : 13755794643
             * attentionSkillsId : 78,73,77,601,76,75
             * productModel : [{"productCategoriesId":78,"updateTime":1477481126000,"createTime":1477481126000,"flag":1,"name":"133","singlename":"133","status":2,"upContentNum":0,"contentNum":0,"weight":0},{"productCategoriesId":73,"updateTime":1477381831000,"createTime":1476610132000,"flag":1,"lastUpdateUserId":1,"name":"分类","singlename":"分类","status":2,"upContentNum":0,"contentNum":0,"weight":0},{"productCategoriesId":77,"updateTime":1477452062000,"createTime":1477452062000,"flag":1,"name":"Ty","singlename":"Ty","status":2,"upContentNum":0,"contentNum":0,"weight":0},{"productCategoriesId":601,"updateTime":1475128939000,"createTime":1475128939000,"flag":1,"createUserId":1,"name":"家属","singlename":"家属 1","status":5,"upContentNum":0,"contentNum":1,"failReason":"","weight":224},{"productCategoriesId":76,"updateTime":1477383098000,"createTime":1477382615000,"flag":1,"createUserId":1,"lastUpdateUserId":1,"name":"123213213213","singlename":"12321321","status":3,"icon":"/images/201610/20161025161129_440.jpg","upContentNum":0,"contentNum":0,"failReason":"","weight":123123},{"productCategoriesId":75,"updateTime":1477382571000,"createTime":1477381483000,"flag":1,"createUserId":1,"lastUpdateUserId":1,"name":"daf","singlename":"daf","status":3,"icon":"/images/201610/20161025154443_201.jpg","upContentNum":0,"contentNum":0,"failReason":"","weight":23}]
             * knowledgeNum : 0
             * registerSourceType : 2
             * loginTime : 1479539367000
             * token : 5a87285c926b4cd9bc4f3fa0efc856c3
             * snstype : 1
             * commentNum : -1
             * readNum : 0
             * collectNum : 0
             * topNum : 0
             * shareNum : 0
             * headPath : http://192.168.1.3/images/health/knowledge/pic/01af5a1ab35548f5832f084540964bf9.jpg
             * status : 1
             * currentIntegral : 1255
             * upContentNum : 0
             * levelName : lv5
             * birthday : 945014400000
             * fCode : CpEsGd1f732
             * companyStatus : 2
             */

            private int memberId;
            private String pwd;
            private int sex;
            private String name;
            private long updateTime;
            private long createTime;
            private int flag;
            private int integral;
            private String nickname;
            private String phone;
            private String attentionSkillsId;
            private int knowledgeNum;
            private int registerSourceType;
            private long loginTime;
            private String token;
            private int snstype;
            private int commentNum;
            private int readNum;
            private int collectNum;
            private int topNum;
            private int shareNum;
            private String headPath;
            private int status;
            private int currentIntegral;
            private int upContentNum;
            private String levelName;
            private long birthday;
            private String fCode;
            private String companyStatus;
            private List<ProductModelBean> productModel;

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public String getPwd() {
                return pwd;
            }

            public void setPwd(String pwd) {
                this.pwd = pwd;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
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

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAttentionSkillsId() {
                return attentionSkillsId;
            }

            public void setAttentionSkillsId(String attentionSkillsId) {
                this.attentionSkillsId = attentionSkillsId;
            }

            public int getKnowledgeNum() {
                return knowledgeNum;
            }

            public void setKnowledgeNum(int knowledgeNum) {
                this.knowledgeNum = knowledgeNum;
            }

            public int getRegisterSourceType() {
                return registerSourceType;
            }

            public void setRegisterSourceType(int registerSourceType) {
                this.registerSourceType = registerSourceType;
            }

            public long getLoginTime() {
                return loginTime;
            }

            public void setLoginTime(long loginTime) {
                this.loginTime = loginTime;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getSnstype() {
                return snstype;
            }

            public void setSnstype(int snstype) {
                this.snstype = snstype;
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

            public int getShareNum() {
                return shareNum;
            }

            public void setShareNum(int shareNum) {
                this.shareNum = shareNum;
            }

            public String getHeadPath() {
                return headPath;
            }

            public void setHeadPath(String headPath) {
                this.headPath = headPath;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCurrentIntegral() {
                return currentIntegral;
            }

            public void setCurrentIntegral(int currentIntegral) {
                this.currentIntegral = currentIntegral;
            }

            public int getUpContentNum() {
                return upContentNum;
            }

            public void setUpContentNum(int upContentNum) {
                this.upContentNum = upContentNum;
            }

            public String getLevelName() {
                return levelName;
            }

            public void setLevelName(String levelName) {
                this.levelName = levelName;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public String getFCode() {
                return fCode;
            }

            public void setFCode(String fCode) {
                this.fCode = fCode;
            }

            public String getCompanyStatus() {
                return companyStatus;
            }

            public void setCompanyStatus(String companyStatus) {
                this.companyStatus = companyStatus;
            }

            public List<ProductModelBean> getProductModel() {
                return productModel;
            }

            public void setProductModel(List<ProductModelBean> productModel) {
                this.productModel = productModel;
            }

            public static class ProductModelBean {
                /**
                 * productCategoriesId : 78
                 * updateTime : 1477481126000
                 * createTime : 1477481126000
                 * flag : 1
                 * name : 133
                 * singlename : 133
                 * status : 2
                 * upContentNum : 0
                 * contentNum : 0
                 * weight : 0
                 * lastUpdateUserId : 1
                 * createUserId : 1
                 * failReason :
                 * icon : /images/201610/20161025161129_440.jpg
                 */

                private int productCategoriesId;
                private long updateTime;
                private long createTime;
                private int flag;
                private String name;
                private String singlename;
                private int status;
                private int upContentNum;
                private int contentNum;
                private int weight;
                private int lastUpdateUserId;
                private int createUserId;
                private String failReason;
                private String icon;

                public int getProductCategoriesId() {
                    return productCategoriesId;
                }

                public void setProductCategoriesId(int productCategoriesId) {
                    this.productCategoriesId = productCategoriesId;
                }

                public long getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(long updateTime) {
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

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getUpContentNum() {
                    return upContentNum;
                }

                public void setUpContentNum(int upContentNum) {
                    this.upContentNum = upContentNum;
                }

                public int getContentNum() {
                    return contentNum;
                }

                public void setContentNum(int contentNum) {
                    this.contentNum = contentNum;
                }

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
                    this.weight = weight;
                }

                public int getLastUpdateUserId() {
                    return lastUpdateUserId;
                }

                public void setLastUpdateUserId(int lastUpdateUserId) {
                    this.lastUpdateUserId = lastUpdateUserId;
                }

                public int getCreateUserId() {
                    return createUserId;
                }

                public void setCreateUserId(int createUserId) {
                    this.createUserId = createUserId;
                }

                public String getFailReason() {
                    return failReason;
                }

                public void setFailReason(String failReason) {
                    this.failReason = failReason;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }
        }
    }
}
