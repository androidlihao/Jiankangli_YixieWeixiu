package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李浩 on 2016/11/25.
 */
public class get_citys_bean implements Serializable{

    /**
     * code : 200
     * message : 查询成功！
     * body : {"data":[{"cityId":513200,"name":"阿坝藏族羌族自治州","parentId":"510000","shortName":"阿坝","levelType":"2","cityCode":"0837","zipCode":"624000","mergerName":"中国,四川省,阿坝藏族羌族自治州","lng":"102.221374","lat":"31.899792","pinyin":"Aba"}]}
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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{
            /**
             * cityId : 513200
             * name : 阿坝藏族羌族自治州
             * parentId : 510000
             * shortName : 阿坝
             * levelType : 2
             * cityCode : 0837
             * zipCode : 624000
             * mergerName : 中国,四川省,阿坝藏族羌族自治州
             * lng : 102.221374
             * lat : 31.899792
             * pinyin : Aba
             */

            private int cityId;
            private String name;
            private String parentId;
            private String shortName;
            private String levelType;
            private String cityCode;
            private String zipCode;
            private String mergerName;
            private String lng;
            private String lat;
            private String pinyin;

            public int getCityId() {
                return cityId;
            }

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getShortName() {
                return shortName;
            }

            public void setShortName(String shortName) {
                this.shortName = shortName;
            }

            public String getLevelType() {
                return levelType;
            }

            public void setLevelType(String levelType) {
                this.levelType = levelType;
            }

            public String getCityCode() {
                return cityCode;
            }

            public void setCityCode(String cityCode) {
                this.cityCode = cityCode;
            }

            public String getZipCode() {
                return zipCode;
            }

            public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
            }

            public String getMergerName() {
                return mergerName;
            }

            public void setMergerName(String mergerName) {
                this.mergerName = mergerName;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }
        }
    }
}
