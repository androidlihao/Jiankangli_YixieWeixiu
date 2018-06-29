package bean;

import java.io.Serializable;

/**
 * Created by 李浩 on 2016/11/28.
 */
public class push_project_bean implements Serializable{

    /**
     * code : 200
     * message : 信息发布-项目详情查询成功！
     * body : {"data":{"informationId":227,"cityId":"110100","description":"头目","projectName":"提一提","projectCycle":"头LOL呀","projectBudget":"送一下","projectRequirement":"诺咯哈","createTime":1480313767000,"updateTime":1480313767000,"biddingNum":0,"manufacturerName":"","productCategoriesName":"","shortName":"北京","mergerName":"中国,北京,北京市","tbCompanyName":"or你明明哦","tbUserName":"普通朋友苏芮","tbCollectId":"","levelName":"","memberName":"","statusName":"","companyStatus":""},"pid":"s00002"}
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
         * data : {"informationId":227,"cityId":"110100","description":"头目","projectName":"提一提","projectCycle":"头LOL呀","projectBudget":"送一下","projectRequirement":"诺咯哈","createTime":1480313767000,"updateTime":1480313767000,"biddingNum":0,"manufacturerName":"","productCategoriesName":"","shortName":"北京","mergerName":"中国,北京,北京市","tbCompanyName":"or你明明哦","tbUserName":"普通朋友苏芮","tbCollectId":"","levelName":"","memberName":"","statusName":"","companyStatus":""}
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
             * informationId : 227
             * cityId : 110100
             * description : 头目
             * projectName : 提一提
             * projectCycle : 头LOL呀
             * projectBudget : 送一下
             * projectRequirement : 诺咯哈
             * createTime : 1480313767000
             * updateTime : 1480313767000
             * biddingNum : 0
             * manufacturerName :
             * productCategoriesName :
             * shortName : 北京
             * mergerName : 中国,北京,北京市
             * tbCompanyName : or你明明哦
             * tbUserName : 普通朋友苏芮
             * tbCollectId :
             * levelName :
             * memberName :
             * statusName :
             * companyStatus :
             */

            private int informationId;
            private String cityId;
            private String description;
            private String projectName;
            private String projectCycle;
            private String projectBudget;
            private String projectRequirement;
            private long createTime;
            private long updateTime;
            private int biddingNum;
            private String manufacturerName;
            private String productCategoriesName;
            private String cityName;
            private String mergerName;
            private String tbCompanyName;
            private String tbUserName;
            private String tbCollectId;
            private String levelName;
            private String memberName;
            private String statusName;
            private String companyStatus;

            public int getInformationId() {
                return informationId;
            }

            public void setInformationId(int informationId) {
                this.informationId = informationId;
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

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getProjectCycle() {
                return projectCycle;
            }

            public void setProjectCycle(String projectCycle) {
                this.projectCycle = projectCycle;
            }

            public String getProjectBudget() {
                return projectBudget;
            }

            public void setProjectBudget(String projectBudget) {
                this.projectBudget = projectBudget;
            }

            public String getProjectRequirement() {
                return projectRequirement;
            }

            public void setProjectRequirement(String projectRequirement) {
                this.projectRequirement = projectRequirement;
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

            public int getBiddingNum() {
                return biddingNum;
            }

            public void setBiddingNum(int biddingNum) {
                this.biddingNum = biddingNum;
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

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getMergerName() {
                return mergerName;
            }

            public void setMergerName(String mergerName) {
                this.mergerName = mergerName;
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
        }
    }
}
