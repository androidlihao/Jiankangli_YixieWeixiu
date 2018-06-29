package bean;

import java.util.List;

/**
 * Created by 李浩 on 2016/10/13.
 */
public class jifeng {

    /**
     * data : [{"integralRecordId":56,"integralRulesId":null,"memberId":83,"content":null,"updateTime":1476261100000,"createTime":1476261100000,"flag":1,"createUserId":null,"lastUpdateUserId":null,"integral":50,"name":null,"nickname":null,"integralRulesCode":"register"},{"integralRecordId":57,"integralRulesId":null,"memberId":83,"content":null,"updateTime":1476261835000,"createTime":1476261835000,"flag":1,"createUserId":null,"lastUpdateUserId":null,"integral":50,"name":null,"nickname":null,"integralRulesCode":"register"},{"integralRecordId":58,"integralRulesId":null,"memberId":83,"content":null,"updateTime":1476269460000,"createTime":1476269460000,"flag":1,"createUserId":null,"lastUpdateUserId":null,"integral":50,"name":null,"nickname":null,"integralRulesCode":"register"},{"integralRecordId":59,"integralRulesId":null,"memberId":83,"content":"5555555","updateTime":1476277016000,"createTime":1477155656000,"flag":1,"createUserId":null,"lastUpdateUserId":null,"integral":50,"name":null,"nickname":null,"integralRulesCode":"register"},{"integralRecordId":60,"integralRulesId":null,"memberId":83,"content":null,"updateTime":1476339021000,"createTime":1476339021000,"flag":1,"createUserId":null,"lastUpdateUserId":null,"integral":2,"name":null,"nickname":null,"integralRulesCode":"browse_knowledge"}]
     * pid : s00001
     */

    private BodyBean body;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        private String pid;
        /**
         * integralRecordId : 56
         * integralRulesId : null
         * memberId : 83
         * content : null
         * updateTime : 1476261100000
         * createTime : 1476261100000
         * flag : 1
         * createUserId : null
         * lastUpdateUserId : null
         * integral : 50
         * name : null
         * nickname : null
         * integralRulesCode : register
         */

        private List<DataBean> data;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private int integralRecordId;
            private Object integralRulesId;
            private int memberId;
            private Object content;
            private long updateTime;
            private long createTime;
            private int flag;
            private Object createUserId;
            private Object lastUpdateUserId;
            private int integral;
            private Object name;
            private Object nickname;
            private String integralRulesCode;

            public int getIntegralRecordId() {
                return integralRecordId;
            }

            public void setIntegralRecordId(int integralRecordId) {
                this.integralRecordId = integralRecordId;
            }

            public Object getIntegralRulesId() {
                return integralRulesId;
            }

            public void setIntegralRulesId(Object integralRulesId) {
                this.integralRulesId = integralRulesId;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
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

            public Object getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(Object createUserId) {
                this.createUserId = createUserId;
            }

            public Object getLastUpdateUserId() {
                return lastUpdateUserId;
            }

            public void setLastUpdateUserId(Object lastUpdateUserId) {
                this.lastUpdateUserId = lastUpdateUserId;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public String getIntegralRulesCode() {
                return integralRulesCode;
            }

            public void setIntegralRulesCode(String integralRulesCode) {
                this.integralRulesCode = integralRulesCode;
            }
        }
    }
}
