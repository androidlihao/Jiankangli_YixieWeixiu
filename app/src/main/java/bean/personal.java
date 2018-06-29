package bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by 李浩 on 2016/9/21.
 */
public class personal implements Serializable{

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
        private DataBean data;
        private String token;
        private String pid;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }
        @Table(name="databean",onCreated = "")//设置表名
        public static class DataBean {
            @Column(name = "id",isId = true,autoGen = false)
            private int memberId;
            @Column(name="pwd")
            private String pwd;
            @Column(name="sex")
            private int sex;
            @Column(name="name")
            private String name;
            @Column(name="createTime")
            private long createTime;
            @Column(name="flag")
            private int flag;
            @Column(name="integral")
            private int integral;
            @Column(name="nickname")
            private String nickname;
            @Column(name="phone")
            private String phone;
            @Column(name="attentionSkillsId")
            private String attentionSkillsId;
            @Column(name="knowledgeNum")
            private int knowledgeNum;
            @Column(name="registerSourceType")
            private int registerSourceType;
            @Column(name="loginTime")
            private long loginTime;
            @Column(name="token")
            private String token;
            @Column(name="snstype")
            private int snstype;
            @Column(name="commentNum")
            private int commentNum;
            @Column(name="readNum")
            private int readNum;
            @Column(name="collectNum")
            private int collectNum;
            @Column(name="topNum")
            private int topNum;
            @Column(name="shareNum")
            private int shareNum;
            @Column(name="headPath")
            private String headPath;
            @Column(name="status")
            private int status;
            @Column(name="currentIntegral")
            private int currentIntegral;
            //"fcode": "xRFzBr6m435"
            @Column(name="fcode")
            private String  fcode;
            @Column(name="uuid")
            private String uuid;
            @Column(name="levelName")
            private String levelName;
            private String jpushTags;
            private Set<String> jpushTagsArray;

            public String getJpushTags() {
                return jpushTags;
            }

            public void setJpushTags(String jpushTags) {
                this.jpushTags = jpushTags;
            }

            public Set<String> getJpushTagsArray() {
                return jpushTagsArray;
            }

            public void setJpushTagsArray(Set<String> jpushTagsArray) {
                this.jpushTagsArray = jpushTagsArray;
            }

            public String getLevelName() {
                return levelName;
            }

            public void setLevelName(String levelName) {
                this.levelName = levelName;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getFcode() {
                return fcode;
            }
            public void setFcode(String fcode) {
                this.fcode = fcode;
            }

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
        }
    }
}

