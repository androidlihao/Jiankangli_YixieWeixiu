package bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by 李浩 on 2016/10/17.
 */
public class My_draft implements Serializable{

    /**
     * code : 200
     * message : 查询成功！
     * body : {"data":{"isCollect":0,"isUp":0,"otherProductCategorieName":"","otherManufacturerName":"","contentImages":[{"contentImagesId":1819,"contentId":1120,"createTime":1481001712000,"flag":1,"type":4,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/722f0871ec9a45d1aea6d24806c62cc3.jpg","localImagePath":"images/health/knowledge/pic/722f0871ec9a45d1aea6d24806c62cc3.jpg","weight":1},{"contentImagesId":1820,"contentId":1120,"createTime":1481001712000,"flag":1,"type":4,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/081a3d2547a14bf49d1e75b94899cce1.jpg","localImagePath":"images/health/knowledge/pic/081a3d2547a14bf49d1e75b94899cce1.jpg","weight":2},{"contentImagesId":1821,"contentId":1120,"createTime":1481001712000,"flag":1,"type":4,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/23576dc330da4c4b986ea600d789c3eb.jpg","localImagePath":"images/health/knowledge/pic/23576dc330da4c4b986ea600d789c3eb.jpg","weight":3}],"member":{"memberId":107,"name":"丁泽泽","nickname":"叮当","headPath":"http://192.168.1.3/images/health/knowledge/pic/9ea6030d66dd4a15bd35a49613ee01b7.jpg","localHeadPath":"images/health/knowledge/pic/9ea6030d66dd4a15bd35a49613ee01b7.jpg","levelName":"lv5"},"contentcategoriesModels":{"contentCategoriesId":2,"name":"工作原理","singlename":"","code":"2"},"contentId":1120,"manufacturerId":360,"manufacturerModel":{"manufacturerId":360,"name":"西门子222","singlename":"西门子222","is_select":0},"productCategoriesId":73,"productCategoriesModel":{"productCategoriesId":73,"name":"分类","singlename":"分类","is_select":0},"contentCategoriesId":2,"updateTime":1481001733000,"createTime":1481001712000,"flag":1,"title":"这些什么、一","status":7,"statusName":"","keyWord":"这些是我的","shareNum":0,"commentNum":0,"readNum":0,"collectNum":0,"topNum":0,"errCode":"","subsystem":"俄中r","models":"是啊我们是","faultDescription":"","stepsResolve":"","result":"","workingPrinciple":"tight","explanation":"","possibleCauses":"","solvingGuide":"","isGrantIntegral":0,"summayContent":"刚刚天大地大","failReason":"","productName":"","memberId":"107","memberName":"","isDraft":1,"abbreviation":"","singlename":"","commonly":"","contentTypeName":"","manufacturerName":""},"pid":"s00005","dateInt":0}
     */

    private String message;
    private BodyBean body;

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
         * data : {"isCollect":0,"isUp":0,"otherProductCategorieName":"","otherManufacturerName":"","contentImages":[{"contentImagesId":1819,"contentId":1120,"createTime":1481001712000,"flag":1,"type":4,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/722f0871ec9a45d1aea6d24806c62cc3.jpg","localImagePath":"images/health/knowledge/pic/722f0871ec9a45d1aea6d24806c62cc3.jpg","weight":1},{"contentImagesId":1820,"contentId":1120,"createTime":1481001712000,"flag":1,"type":4,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/081a3d2547a14bf49d1e75b94899cce1.jpg","localImagePath":"images/health/knowledge/pic/081a3d2547a14bf49d1e75b94899cce1.jpg","weight":2},{"contentImagesId":1821,"contentId":1120,"createTime":1481001712000,"flag":1,"type":4,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/23576dc330da4c4b986ea600d789c3eb.jpg","localImagePath":"images/health/knowledge/pic/23576dc330da4c4b986ea600d789c3eb.jpg","weight":3}],"member":{"memberId":107,"name":"丁泽泽","nickname":"叮当","headPath":"http://192.168.1.3/images/health/knowledge/pic/9ea6030d66dd4a15bd35a49613ee01b7.jpg","localHeadPath":"images/health/knowledge/pic/9ea6030d66dd4a15bd35a49613ee01b7.jpg","levelName":"lv5"},"contentcategoriesModels":{"contentCategoriesId":2,"name":"工作原理","singlename":"","code":"2"},"contentId":1120,"manufacturerId":360,"manufacturerModel":{"manufacturerId":360,"name":"西门子222","singlename":"西门子222","is_select":0},"productCategoriesId":73,"productCategoriesModel":{"productCategoriesId":73,"name":"分类","singlename":"分类","is_select":0},"contentCategoriesId":2,"updateTime":1481001733000,"createTime":1481001712000,"flag":1,"title":"这些什么、一","status":7,"statusName":"","keyWord":"这些是我的","shareNum":0,"commentNum":0,"readNum":0,"collectNum":0,"topNum":0,"errCode":"","subsystem":"俄中r","models":"是啊我们是","faultDescription":"","stepsResolve":"","result":"","workingPrinciple":"tight","explanation":"","possibleCauses":"","solvingGuide":"","isGrantIntegral":0,"summayContent":"刚刚天大地大","failReason":"","productName":"","memberId":"107","memberName":"","isDraft":1,"abbreviation":"","singlename":"","commonly":"","contentTypeName":"","manufacturerName":""}
         * pid : s00005
         * dateInt : 0
         */

        private DataBean data;
        private String pid;
        private int dateInt;

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

        public int getDateInt() {
            return dateInt;
        }

        public void setDateInt(int dateInt) {
            this.dateInt = dateInt;
        }

        public static class DataBean implements Serializable{
            /**
             * isCollect : 0
             * isUp : 0
             * otherProductCategorieName :
             * otherManufacturerName :
             * contentImages : [{"contentImagesId":1819,"contentId":1120,"createTime":1481001712000,"flag":1,"type":4,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/722f0871ec9a45d1aea6d24806c62cc3.jpg","localImagePath":"images/health/knowledge/pic/722f0871ec9a45d1aea6d24806c62cc3.jpg","weight":1},{"contentImagesId":1820,"contentId":1120,"createTime":1481001712000,"flag":1,"type":4,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/081a3d2547a14bf49d1e75b94899cce1.jpg","localImagePath":"images/health/knowledge/pic/081a3d2547a14bf49d1e75b94899cce1.jpg","weight":2},{"contentImagesId":1821,"contentId":1120,"createTime":1481001712000,"flag":1,"type":4,"imagePath":"http://192.168.1.3/images/health/knowledge/pic/23576dc330da4c4b986ea600d789c3eb.jpg","localImagePath":"images/health/knowledge/pic/23576dc330da4c4b986ea600d789c3eb.jpg","weight":3}]
             * member : {"memberId":107,"name":"丁泽泽","nickname":"叮当","headPath":"http://192.168.1.3/images/health/knowledge/pic/9ea6030d66dd4a15bd35a49613ee01b7.jpg","localHeadPath":"images/health/knowledge/pic/9ea6030d66dd4a15bd35a49613ee01b7.jpg","levelName":"lv5"}
             * contentcategoriesModels : {"contentCategoriesId":2,"name":"工作原理","singlename":"","code":"2"}
             * contentId : 1120
             * manufacturerId : 360
             * manufacturerModel : {"manufacturerId":360,"name":"西门子222","singlename":"西门子222","is_select":0}
             * productCategoriesId : 73
             * productCategoriesModel : {"productCategoriesId":73,"name":"分类","singlename":"分类","is_select":0}
             * contentCategoriesId : 2
             * updateTime : 1481001733000
             * createTime : 1481001712000
             * flag : 1
             * title : 这些什么、一
             * status : 7
             * statusName :
             * keyWord : 这些是我的
             * shareNum : 0
             * commentNum : 0
             * readNum : 0
             * collectNum : 0
             * topNum : 0
             * errCode :
             * subsystem : 俄中r
             * models : 是啊我们是
             * faultDescription :
             * stepsResolve :
             * result :
             * workingPrinciple : tight
             * explanation :
             * possibleCauses :
             * solvingGuide :
             * isGrantIntegral : 0
             * summayContent : 刚刚天大地大
             * failReason :
             * productName :
             * memberId : 107
             * memberName :
             * isDraft : 1
             * abbreviation :
             * singlename :
             * commonly :
             * contentTypeName :
             * manufacturerName :
             */

            private int isCollect;
            private int isUp;
            private String otherProductCategorieName;
            private String otherManufacturerName;
            private MemberBean member;
            private ContentcategoriesModelsBean contentcategoriesModels;
            private int contentId;
            private int manufacturerId;
            private ManufacturerModelBean manufacturerModel;
            private int productCategoriesId;
            private ProductCategoriesModelBean productCategoriesModel;
            private int contentCategoriesId;
            private long updateTime;
            private long createTime;
            private int flag;
            private String title;
            private int status;
            private String statusName;
            private String keyWord;
            private int shareNum;
            private int commentNum;
            private int readNum;
            private int collectNum;
            private int topNum;
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
            private int isGrantIntegral;
            private String summayContent;
            private String failReason;
            private String productName;
            private String memberId;
            private String memberName;
            private int isDraft;
            private String abbreviation;
            private String singlename;
            private String commonly;
            private String contentTypeName;
            private String manufacturerName;
            private List<ContentImagesBean> contentImages;

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

            public String getOtherProductCategorieName() {
                return otherProductCategorieName;
            }

            public void setOtherProductCategorieName(String otherProductCategorieName) {
                this.otherProductCategorieName = otherProductCategorieName;
            }

            public String getOtherManufacturerName() {
                return otherManufacturerName;
            }

            public void setOtherManufacturerName(String otherManufacturerName) {
                this.otherManufacturerName = otherManufacturerName;
            }

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
            }

            public ContentcategoriesModelsBean getContentcategoriesModels() {
                return contentcategoriesModels;
            }

            public void setContentcategoriesModels(ContentcategoriesModelsBean contentcategoriesModels) {
                this.contentcategoriesModels = contentcategoriesModels;
            }

            public int getContentId() {
                return contentId;
            }

            public void setContentId(int contentId) {
                this.contentId = contentId;
            }

            public int getManufacturerId() {
                return manufacturerId;
            }

            public void setManufacturerId(int manufacturerId) {
                this.manufacturerId = manufacturerId;
            }

            public ManufacturerModelBean getManufacturerModel() {
                return manufacturerModel;
            }

            public void setManufacturerModel(ManufacturerModelBean manufacturerModel) {
                this.manufacturerModel = manufacturerModel;
            }

            public int getProductCategoriesId() {
                return productCategoriesId;
            }

            public void setProductCategoriesId(int productCategoriesId) {
                this.productCategoriesId = productCategoriesId;
            }

            public ProductCategoriesModelBean getProductCategoriesModel() {
                return productCategoriesModel;
            }

            public void setProductCategoriesModel(ProductCategoriesModelBean productCategoriesModel) {
                this.productCategoriesModel = productCategoriesModel;
            }

            public int getContentCategoriesId() {
                return contentCategoriesId;
            }

            public void setContentCategoriesId(int contentCategoriesId) {
                this.contentCategoriesId = contentCategoriesId;
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

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
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

            public int getIsGrantIntegral() {
                return isGrantIntegral;
            }

            public void setIsGrantIntegral(int isGrantIntegral) {
                this.isGrantIntegral = isGrantIntegral;
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

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public String getMemberName() {
                return memberName;
            }

            public void setMemberName(String memberName) {
                this.memberName = memberName;
            }

            public int getIsDraft() {
                return isDraft;
            }

            public void setIsDraft(int isDraft) {
                this.isDraft = isDraft;
            }

            public String getAbbreviation() {
                return abbreviation;
            }

            public void setAbbreviation(String abbreviation) {
                this.abbreviation = abbreviation;
            }

            public String getSinglename() {
                return singlename;
            }

            public void setSinglename(String singlename) {
                this.singlename = singlename;
            }

            public String getCommonly() {
                return commonly;
            }

            public void setCommonly(String commonly) {
                this.commonly = commonly;
            }

            public String getContentTypeName() {
                return contentTypeName;
            }

            public void setContentTypeName(String contentTypeName) {
                this.contentTypeName = contentTypeName;
            }

            public String getManufacturerName() {
                return manufacturerName;
            }

            public void setManufacturerName(String manufacturerName) {
                this.manufacturerName = manufacturerName;
            }

            public List<ContentImagesBean> getContentImages() {
                return contentImages;
            }

            public void setContentImages(List<ContentImagesBean> contentImages) {
                this.contentImages = contentImages;
            }

            public static class MemberBean implements Serializable{
                /**
                 * memberId : 107
                 * name : 丁泽泽
                 * nickname : 叮当
                 * headPath : http://192.168.1.3/images/health/knowledge/pic/9ea6030d66dd4a15bd35a49613ee01b7.jpg
                 * localHeadPath : images/health/knowledge/pic/9ea6030d66dd4a15bd35a49613ee01b7.jpg
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

            public static class ContentcategoriesModelsBean implements Serializable{
                /**
                 * contentCategoriesId : 2
                 * name : 工作原理
                 * singlename :
                 * code : 2
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
                 * manufacturerId : 360
                 * name : 西门子222
                 * singlename : 西门子222
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
                 * productCategoriesId : 73
                 * name : 分类
                 * singlename : 分类
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

            public static class ContentImagesBean implements Serializable{
                /**
                 * contentImagesId : 1819
                 * contentId : 1120
                 * createTime : 1481001712000
                 * flag : 1
                 * type : 4
                 * imagePath : http://192.168.1.3/images/health/knowledge/pic/722f0871ec9a45d1aea6d24806c62cc3.jpg
                 * localImagePath : images/health/knowledge/pic/722f0871ec9a45d1aea6d24806c62cc3.jpg
                 * weight : 1
                 */

                private int contentImagesId;
                private int contentId;
                private long createTime;
                private int flag;
                private int type;
                private String imagePath;
                private String localImagePath;
                private int weight;

                public int getContentImagesId() {
                    return contentImagesId;
                }

                public void setContentImagesId(int contentImagesId) {
                    this.contentImagesId = contentImagesId;
                }

                public int getContentId() {
                    return contentId;
                }

                public void setContentId(int contentId) {
                    this.contentId = contentId;
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
