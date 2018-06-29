package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李浩 on 2016/12/23.
 */

public class question_bean implements Serializable{

    /**
     * code : 200
     * message : 问题详情查询成功！
     * body : {"data":{"questionImagesModel":[{"questionImagesId":1651,"questionId":67,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/7441f07867884a899dbad4d9dd521ca5.png","localImagePath":"images/health/knowledge/pic/7441f07867884a899dbad4d9dd521ca5.png","weight":2,"contentType":1},{"questionImagesId":1650,"questionId":67,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/38e1e84539544ad4b26b0831257b81f4.png","localImagePath":"images/health/knowledge/pic/38e1e84539544ad4b26b0831257b81f4.png","weight":1,"contentType":1}],"contentcategoriesModels":{"contentCategoriesId":1,"name":"故障","singlename":"故障","code":"1"},"manufacturerModel":{"manufacturerId":503,"name":"西门子","singlename":"西门子 3","is_select":0},"productCategoriesModel":{"productCategoriesId":603,"name":"dsdsds","singlename":"dsdsds 3","is_select":0},"memberModel":{"memberId":147,"name":"李浩","nickname":"一个独立的个体","headPath":"http://192.168.1.3/images/health/knowledge/pic/31a4485a53644ad18b8741a114c97c5f.jpg","localHeadPath":"images/health/knowledge/pic/31a4485a53644ad18b8741a114c97c5f.jpg","levelName":"lv5"},"questionId":67,"manufacturerId":503,"productCategoriesId":603,"contentCategoriesId":1,"memberId":147,"updateTime":1478589209000,"createTime":1478589193000,"flag":1,"lastUpdateUserId":1,"title":"扣女哦","status":4,"keyWord":"他无图TXT","shareNum":0,"commentNum":0,"readNum":28,"collectNum":0,"topNum":0,"answerNum":0,"errCode":"曲终人未散无所谓","subsystem":"","models":"欧诺图","faultDescription":"公明","stepsResolve":"","result":"","workingPrinciple":"","explanation":"","possibleCauses":"","solvingGuide":"","upTime":1478589193000,"auditTime":1479470873000,"summayContent":"","failReason":"true","source":1,"bountyIntegral":8,"contentTypeName":"","memberName":"李浩","manufacturerName":"西门子","productName":"","statusName":"审核失败","isCollect":0,"isUp":0,"questionOrAnswer":0,"statusNum":0},"pid":"s00002"}
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
         * data : {"questionImagesModel":[{"questionImagesId":1651,"questionId":67,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/7441f07867884a899dbad4d9dd521ca5.png","localImagePath":"images/health/knowledge/pic/7441f07867884a899dbad4d9dd521ca5.png","weight":2,"contentType":1},{"questionImagesId":1650,"questionId":67,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/38e1e84539544ad4b26b0831257b81f4.png","localImagePath":"images/health/knowledge/pic/38e1e84539544ad4b26b0831257b81f4.png","weight":1,"contentType":1}],"contentcategoriesModels":{"contentCategoriesId":1,"name":"故障","singlename":"故障","code":"1"},"manufacturerModel":{"manufacturerId":503,"name":"西门子","singlename":"西门子 3","is_select":0},"productCategoriesModel":{"productCategoriesId":603,"name":"dsdsds","singlename":"dsdsds 3","is_select":0},"memberModel":{"memberId":147,"name":"李浩","nickname":"一个独立的个体","headPath":"http://192.168.1.3/images/health/knowledge/pic/31a4485a53644ad18b8741a114c97c5f.jpg","localHeadPath":"images/health/knowledge/pic/31a4485a53644ad18b8741a114c97c5f.jpg","levelName":"lv5"},"questionId":67,"manufacturerId":503,"productCategoriesId":603,"contentCategoriesId":1,"memberId":147,"updateTime":1478589209000,"createTime":1478589193000,"flag":1,"lastUpdateUserId":1,"title":"扣女哦","status":4,"keyWord":"他无图TXT","shareNum":0,"commentNum":0,"readNum":28,"collectNum":0,"topNum":0,"answerNum":0,"errCode":"曲终人未散无所谓","subsystem":"","models":"欧诺图","faultDescription":"公明","stepsResolve":"","result":"","workingPrinciple":"","explanation":"","possibleCauses":"","solvingGuide":"","upTime":1478589193000,"auditTime":1479470873000,"summayContent":"","failReason":"true","source":1,"bountyIntegral":8,"contentTypeName":"","memberName":"李浩","manufacturerName":"西门子","productName":"","statusName":"审核失败","isCollect":0,"isUp":0,"questionOrAnswer":0,"statusNum":0}
         * pid : s00002
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
             * questionImagesModel : [{"questionImagesId":1651,"questionId":67,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/7441f07867884a899dbad4d9dd521ca5.png","localImagePath":"images/health/knowledge/pic/7441f07867884a899dbad4d9dd521ca5.png","weight":2,"contentType":1},{"questionImagesId":1650,"questionId":67,"type":1,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/38e1e84539544ad4b26b0831257b81f4.png","localImagePath":"images/health/knowledge/pic/38e1e84539544ad4b26b0831257b81f4.png","weight":1,"contentType":1}]
             * contentcategoriesModels : {"contentCategoriesId":1,"name":"故障","singlename":"故障","code":"1"}
             * manufacturerModel : {"manufacturerId":503,"name":"西门子","singlename":"西门子 3","is_select":0}
             * productCategoriesModel : {"productCategoriesId":603,"name":"dsdsds","singlename":"dsdsds 3","is_select":0}
             * memberModel : {"memberId":147,"name":"李浩","nickname":"一个独立的个体","headPath":"http://192.168.1.3/images/health/knowledge/pic/31a4485a53644ad18b8741a114c97c5f.jpg","localHeadPath":"images/health/knowledge/pic/31a4485a53644ad18b8741a114c97c5f.jpg","levelName":"lv5"}
             * questionId : 67
             * manufacturerId : 503
             * productCategoriesId : 603
             * contentCategoriesId : 1
             * memberId : 147
             * updateTime : 1478589209000
             * createTime : 1478589193000
             * flag : 1
             * lastUpdateUserId : 1
             * title : 扣女哦
             * status : 4
             * keyWord : 他无图TXT
             * shareNum : 0
             * commentNum : 0
             * readNum : 28
             * collectNum : 0
             * topNum : 0
             * answerNum : 0
             * errCode : 曲终人未散无所谓
             * subsystem :
             * models : 欧诺图
             * faultDescription : 公明
             * stepsResolve :
             * result :
             * workingPrinciple :
             * explanation :
             * possibleCauses :
             * solvingGuide :
             * upTime : 1478589193000
             * auditTime : 1479470873000
             * summayContent :
             * failReason : true
             * source : 1
             * bountyIntegral : 8
             * contentTypeName :
             * memberName : 李浩
             * manufacturerName : 西门子
             * productName :
             * statusName : 审核失败
             * isCollect : 0
             * isUp : 0
             * questionOrAnswer : 0
             * statusNum : 0
             */

            private ContentcategoriesModelsBean contentcategoriesModels;
            private ManufacturerModelBean manufacturerModel;
            private ProductCategoriesModelBean productCategoriesModel;
            private MemberModelBean memberModel;
            private int questionId;
            private int manufacturerId;
            private int productCategoriesId;
            private int contentCategoriesId;
            private int memberId;
            private long updateTime;
            private long createTime;
            private int flag;
            private int lastUpdateUserId;
            private String title;
            private int status;
            private String keyWord;
            private int shareNum;
            private int commentNum;
            private int readNum;
            private int collectNum;
            private int topNum;
            private int answerNum;
            private String errCode;
            private String subsystem;
            private String models;
            private String faultDescription;
            private String stepsResolve;
            private String result;
            private String workingPrinciple;
            private String explanation;
            private String possibleCauses;
            private String solvingGuide;
            private long upTime;
            private long auditTime;
            private String summayContent;
            private String failReason;
            private int source;
            private int bountyIntegral;
            private String contentTypeName;
            private String memberName;
            private String manufacturerName;
            private String productName;
            private String statusName;
            private int isCollect;
            private int isUp;
            private int questionOrAnswer;
            private int statusNum;
            private List<QuestionImagesModelBean> questionImagesModel;

            public ContentcategoriesModelsBean getContentcategoriesModels() {
                return contentcategoriesModels;
            }

            public void setContentcategoriesModels(ContentcategoriesModelsBean contentcategoriesModels) {
                this.contentcategoriesModels = contentcategoriesModels;
            }

            public ManufacturerModelBean getManufacturerModel() {
                return manufacturerModel;
            }

            public void setManufacturerModel(ManufacturerModelBean manufacturerModel) {
                this.manufacturerModel = manufacturerModel;
            }

            public ProductCategoriesModelBean getProductCategoriesModel() {
                return productCategoriesModel;
            }

            public void setProductCategoriesModel(ProductCategoriesModelBean productCategoriesModel) {
                this.productCategoriesModel = productCategoriesModel;
            }

            public MemberModelBean getMemberModel() {
                return memberModel;
            }

            public void setMemberModel(MemberModelBean memberModel) {
                this.memberModel = memberModel;
            }

            public int getQuestionId() {
                return questionId;
            }

            public void setQuestionId(int questionId) {
                this.questionId = questionId;
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

            public int getContentCategoriesId() {
                return contentCategoriesId;
            }

            public void setContentCategoriesId(int contentCategoriesId) {
                this.contentCategoriesId = contentCategoriesId;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
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

            public int getLastUpdateUserId() {
                return lastUpdateUserId;
            }

            public void setLastUpdateUserId(int lastUpdateUserId) {
                this.lastUpdateUserId = lastUpdateUserId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getKeyWord() {
                return keyWord;
            }

            public void setKeyWord(String keyWord) {
                this.keyWord = keyWord;
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

            public int getAnswerNum() {
                return answerNum;
            }

            public void setAnswerNum(int answerNum) {
                this.answerNum = answerNum;
            }

            public String getErrCode() {
                return errCode;
            }

            public void setErrCode(String errCode) {
                this.errCode = errCode;
            }

            public String getSubsystem() {
                return subsystem;
            }

            public void setSubsystem(String subsystem) {
                this.subsystem = subsystem;
            }

            public String getModels() {
                return models;
            }

            public void setModels(String models) {
                this.models = models;
            }

            public String getFaultDescription() {
                return faultDescription;
            }

            public void setFaultDescription(String faultDescription) {
                this.faultDescription = faultDescription;
            }

            public String getStepsResolve() {
                return stepsResolve;
            }

            public void setStepsResolve(String stepsResolve) {
                this.stepsResolve = stepsResolve;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

            public String getWorkingPrinciple() {
                return workingPrinciple;
            }

            public void setWorkingPrinciple(String workingPrinciple) {
                this.workingPrinciple = workingPrinciple;
            }

            public String getExplanation() {
                return explanation;
            }

            public void setExplanation(String explanation) {
                this.explanation = explanation;
            }

            public String getPossibleCauses() {
                return possibleCauses;
            }

            public void setPossibleCauses(String possibleCauses) {
                this.possibleCauses = possibleCauses;
            }

            public String getSolvingGuide() {
                return solvingGuide;
            }

            public void setSolvingGuide(String solvingGuide) {
                this.solvingGuide = solvingGuide;
            }

            public long getUpTime() {
                return upTime;
            }

            public void setUpTime(long upTime) {
                this.upTime = upTime;
            }

            public long getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(long auditTime) {
                this.auditTime = auditTime;
            }

            public String getSummayContent() {
                return summayContent;
            }

            public void setSummayContent(String summayContent) {
                this.summayContent = summayContent;
            }

            public String getFailReason() {
                return failReason;
            }

            public void setFailReason(String failReason) {
                this.failReason = failReason;
            }

            public int getSource() {
                return source;
            }

            public void setSource(int source) {
                this.source = source;
            }

            public int getBountyIntegral() {
                return bountyIntegral;
            }

            public void setBountyIntegral(int bountyIntegral) {
                this.bountyIntegral = bountyIntegral;
            }

            public String getContentTypeName() {
                return contentTypeName;
            }

            public void setContentTypeName(String contentTypeName) {
                this.contentTypeName = contentTypeName;
            }

            public String getMemberName() {
                return memberName;
            }

            public void setMemberName(String memberName) {
                this.memberName = memberName;
            }

            public String getManufacturerName() {
                return manufacturerName;
            }

            public void setManufacturerName(String manufacturerName) {
                this.manufacturerName = manufacturerName;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public int getIsCollect() {
                return isCollect;
            }

            public void setIsCollect(int isCollect) {
                this.isCollect = isCollect;
            }

            public int getIsUp() {
                return isUp;
            }

            public void setIsUp(int isUp) {
                this.isUp = isUp;
            }

            public int getQuestionOrAnswer() {
                return questionOrAnswer;
            }

            public void setQuestionOrAnswer(int questionOrAnswer) {
                this.questionOrAnswer = questionOrAnswer;
            }

            public int getStatusNum() {
                return statusNum;
            }

            public void setStatusNum(int statusNum) {
                this.statusNum = statusNum;
            }

            public List<QuestionImagesModelBean> getQuestionImagesModel() {
                return questionImagesModel;
            }

            public void setQuestionImagesModel(List<QuestionImagesModelBean> questionImagesModel) {
                this.questionImagesModel = questionImagesModel;
            }

            public static class ContentcategoriesModelsBean implements Serializable{
                /**
                 * contentCategoriesId : 1
                 * name : 故障
                 * singlename : 故障
                 * code : 1
                 */

                private int contentCategoriesId;
                private String name;
                private String singlename;
                private String code;

                public int getContentCategoriesId() {
                    return contentCategoriesId;
                }

                public void setContentCategoriesId(int contentCategoriesId) {
                    this.contentCategoriesId = contentCategoriesId;
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

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }
            }

            public static class ManufacturerModelBean implements Serializable{
                /**
                 * manufacturerId : 503
                 * name : 西门子
                 * singlename : 西门子 3
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

            public static class ProductCategoriesModelBean implements Serializable{
                /**
                 * productCategoriesId : 603
                 * name : dsdsds
                 * singlename : dsdsds 3
                 * is_select : 0
                 */

                private int productCategoriesId;
                private String name;
                private String singlename;
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

                public int getIs_select() {
                    return is_select;
                }

                public void setIs_select(int is_select) {
                    this.is_select = is_select;
                }
            }

            public static class MemberModelBean implements Serializable{
                /**
                 * memberId : 147
                 * name : 李浩
                 * nickname : 一个独立的个体
                 * headPath : http://192.168.1.3/images/health/knowledge/pic/31a4485a53644ad18b8741a114c97c5f.jpg
                 * localHeadPath : images/health/knowledge/pic/31a4485a53644ad18b8741a114c97c5f.jpg
                 * levelName : lv5
                 */

                private int memberId;
                private String name;
                private String nickname;
                private String headPath;
                private String localHeadPath;
                private String levelName;

                public int getMemberId() {
                    return memberId;
                }

                public void setMemberId(int memberId) {
                    this.memberId = memberId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getHeadPath() {
                    return headPath;
                }

                public void setHeadPath(String headPath) {
                    this.headPath = headPath;
                }

                public String getLocalHeadPath() {
                    return localHeadPath;
                }

                public void setLocalHeadPath(String localHeadPath) {
                    this.localHeadPath = localHeadPath;
                }

                public String getLevelName() {
                    return levelName;
                }

                public void setLevelName(String levelName) {
                    this.levelName = levelName;
                }
            }

            public static class QuestionImagesModelBean implements Serializable{
                /**
                 * questionImagesId : 1651
                 * questionId : 67
                 * type : 1
                 * imagePath : http://192.168.1.3/images/health/knowledge/pic/7441f07867884a899dbad4d9dd521ca5.png
                 * localImagePath : images/health/knowledge/pic/7441f07867884a899dbad4d9dd521ca5.png
                 * weight : 2
                 * contentType : 1
                 */

                private int questionImagesId;
                private int questionId;
                private int type;
                private String imagePath;
                private String localImagePath;
                private int weight;
                private int contentType;

                public int getQuestionImagesId() {
                    return questionImagesId;
                }

                public void setQuestionImagesId(int questionImagesId) {
                    this.questionImagesId = questionImagesId;
                }

                public int getQuestionId() {
                    return questionId;
                }

                public void setQuestionId(int questionId) {
                    this.questionId = questionId;
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

                public int getContentType() {
                    return contentType;
                }

                public void setContentType(int contentType) {
                    this.contentType = contentType;
                }
            }
        }
    }
}
