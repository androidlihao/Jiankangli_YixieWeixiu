package bean;

import java.util.List;

/**
 * Created by 李浩 on 2016/11/15.
 */
public class Citys {

    /**
     * code : 200
     * message : 查询成功！
     * body : {"data":[{"cityId":110000,"name":"北京","parentId":"100000","shortName":"北京","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,北京","lng":"116.405285","lat":"39.904989","pinyin":"Beijing"},{"cityId":120000,"name":"天津","parentId":"100000","shortName":"天津","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,天津","lng":"117.190182","lat":"39.125596","pinyin":"Tianjin"},{"cityId":130000,"name":"河北省","parentId":"100000","shortName":"河北","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,河北省","lng":"114.502461","lat":"38.045474","pinyin":"Hebei"},{"cityId":140000,"name":"山西省","parentId":"100000","shortName":"山西","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,山西省","lng":"112.549248","lat":"37.857014","pinyin":"Shanxi"},{"cityId":150000,"name":"内蒙古自治区","parentId":"100000","shortName":"内蒙古","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,内蒙古自治区","lng":"111.670801","lat":"40.818311","pinyin":"Inner Mongolia"},{"cityId":210000,"name":"辽宁省","parentId":"100000","shortName":"辽宁","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,辽宁省","lng":"123.429096","lat":"41.796767","pinyin":"Liaoning"},{"cityId":220000,"name":"吉林省","parentId":"100000","shortName":"吉林","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,吉林省","lng":"125.3245","lat":"43.886841","pinyin":"Jilin"},{"cityId":230000,"name":"黑龙江省","parentId":"100000","shortName":"黑龙江","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,黑龙江省","lng":"126.642464","lat":"45.756967","pinyin":"Heilongjiang"},{"cityId":310000,"name":"上海","parentId":"100000","shortName":"上海","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,上海","lng":"121.472644","lat":"31.231706","pinyin":"Shanghai"},{"cityId":320000,"name":"江苏省","parentId":"100000","shortName":"江苏","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,江苏省","lng":"118.767413","lat":"32.041544","pinyin":"Jiangsu"},{"cityId":330000,"name":"浙江省","parentId":"100000","shortName":"浙江","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,浙江省","lng":"120.153576","lat":"30.287459","pinyin":"Zhejiang"},{"cityId":340000,"name":"安徽省","parentId":"100000","shortName":"安徽","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,安徽省","lng":"117.283042","lat":"31.86119","pinyin":"Anhui"},{"cityId":350000,"name":"福建省","parentId":"100000","shortName":"福建","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,福建省","lng":"119.306239","lat":"26.075302","pinyin":"Fujian"},{"cityId":360000,"name":"江西省","parentId":"100000","shortName":"江西","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,江西省","lng":"115.892151","lat":"28.676493","pinyin":"Jiangxi"},{"cityId":370000,"name":"山东省","parentId":"100000","shortName":"山东","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,山东省","lng":"117.000923","lat":"36.675807","pinyin":"Shandong"},{"cityId":410000,"name":"河南省","parentId":"100000","shortName":"河南","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,河南省","lng":"113.665412","lat":"34.757975","pinyin":"Henan"},{"cityId":420000,"name":"湖北省","parentId":"100000","shortName":"湖北","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,湖北省","lng":"114.298572","lat":"30.584355","pinyin":"Hubei"},{"cityId":430000,"name":"湖南省","parentId":"100000","shortName":"湖南","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,湖南省","lng":"112.982279","lat":"28.19409","pinyin":"Hunan"},{"cityId":440000,"name":"广东省","parentId":"100000","shortName":"广东","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,广东省","lng":"113.280637","lat":"23.125178","pinyin":"Guangdong"},{"cityId":450000,"name":"广西壮族自治区","parentId":"100000","shortName":"广西","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,广西壮族自治区","lng":"108.320004","lat":"22.82402","pinyin":"Guangxi"},{"cityId":460000,"name":"海南省","parentId":"100000","shortName":"海南","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,海南省","lng":"110.33119","lat":"20.031971","pinyin":"Hainan"},{"cityId":500000,"name":"重庆","parentId":"100000","shortName":"重庆","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,重庆","lng":"106.504962","lat":"29.533155","pinyin":"Chongqing"},{"cityId":510000,"name":"四川省","parentId":"100000","shortName":"四川","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,四川省","lng":"104.065735","lat":"30.659462","pinyin":"Sichuan"},{"cityId":520000,"name":"贵州省","parentId":"100000","shortName":"贵州","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,贵州省","lng":"106.713478","lat":"26.578343","pinyin":"Guizhou"},{"cityId":530000,"name":"云南省","parentId":"100000","shortName":"云南","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,云南省","lng":"102.712251","lat":"25.040609","pinyin":"Yunnan"},{"cityId":540000,"name":"西藏自治区","parentId":"100000","shortName":"西藏","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,西藏自治区","lng":"91.132212","lat":"29.660361","pinyin":"Tibet"},{"cityId":610000,"name":"陕西省","parentId":"100000","shortName":"陕西","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,陕西省","lng":"108.948024","lat":"34.263161","pinyin":"Shaanxi"},{"cityId":620000,"name":"甘肃省","parentId":"100000","shortName":"甘肃","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,甘肃省","lng":"103.823557","lat":"36.058039","pinyin":"Gansu"},{"cityId":630000,"name":"青海省","parentId":"100000","shortName":"青海","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,青海省","lng":"101.778916","lat":"36.623178","pinyin":"Qinghai"},{"cityId":640000,"name":"宁夏回族自治区","parentId":"100000","shortName":"宁夏","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,宁夏回族自治区","lng":"106.278179","lat":"38.46637","pinyin":"Ningxia"},{"cityId":650000,"name":"新疆维吾尔自治区","parentId":"100000","shortName":"新疆","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,新疆维吾尔自治区","lng":"87.617733","lat":"43.792818","pinyin":"Xinjiang"},{"cityId":710000,"name":"台湾","parentId":"100000","shortName":"台湾","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,台湾","lng":"121.509062","lat":"25.044332","pinyin":"Taiwan"},{"cityId":810000,"name":"香港特别行政区","parentId":"100000","shortName":"香港","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,香港特别行政区","lng":"114.173355","lat":"22.320048","pinyin":"Hong Kong"},{"cityId":820000,"name":"澳门特别行政区","parentId":"100000","shortName":"澳门","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,澳门特别行政区","lng":"113.54909","lat":"22.198951","pinyin":"Macau"},{"cityId":900000,"name":"钓鱼岛","parentId":"100000","shortName":"钓鱼岛","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,钓鱼岛","lng":"123.478088","lat":"25.742385","pinyin":"DiaoyuDao"}],"pid":"s00002"}
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
         * data : [{"cityId":110000,"name":"北京","parentId":"100000","shortName":"北京","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,北京","lng":"116.405285","lat":"39.904989","pinyin":"Beijing"},{"cityId":120000,"name":"天津","parentId":"100000","shortName":"天津","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,天津","lng":"117.190182","lat":"39.125596","pinyin":"Tianjin"},{"cityId":130000,"name":"河北省","parentId":"100000","shortName":"河北","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,河北省","lng":"114.502461","lat":"38.045474","pinyin":"Hebei"},{"cityId":140000,"name":"山西省","parentId":"100000","shortName":"山西","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,山西省","lng":"112.549248","lat":"37.857014","pinyin":"Shanxi"},{"cityId":150000,"name":"内蒙古自治区","parentId":"100000","shortName":"内蒙古","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,内蒙古自治区","lng":"111.670801","lat":"40.818311","pinyin":"Inner Mongolia"},{"cityId":210000,"name":"辽宁省","parentId":"100000","shortName":"辽宁","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,辽宁省","lng":"123.429096","lat":"41.796767","pinyin":"Liaoning"},{"cityId":220000,"name":"吉林省","parentId":"100000","shortName":"吉林","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,吉林省","lng":"125.3245","lat":"43.886841","pinyin":"Jilin"},{"cityId":230000,"name":"黑龙江省","parentId":"100000","shortName":"黑龙江","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,黑龙江省","lng":"126.642464","lat":"45.756967","pinyin":"Heilongjiang"},{"cityId":310000,"name":"上海","parentId":"100000","shortName":"上海","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,上海","lng":"121.472644","lat":"31.231706","pinyin":"Shanghai"},{"cityId":320000,"name":"江苏省","parentId":"100000","shortName":"江苏","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,江苏省","lng":"118.767413","lat":"32.041544","pinyin":"Jiangsu"},{"cityId":330000,"name":"浙江省","parentId":"100000","shortName":"浙江","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,浙江省","lng":"120.153576","lat":"30.287459","pinyin":"Zhejiang"},{"cityId":340000,"name":"安徽省","parentId":"100000","shortName":"安徽","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,安徽省","lng":"117.283042","lat":"31.86119","pinyin":"Anhui"},{"cityId":350000,"name":"福建省","parentId":"100000","shortName":"福建","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,福建省","lng":"119.306239","lat":"26.075302","pinyin":"Fujian"},{"cityId":360000,"name":"江西省","parentId":"100000","shortName":"江西","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,江西省","lng":"115.892151","lat":"28.676493","pinyin":"Jiangxi"},{"cityId":370000,"name":"山东省","parentId":"100000","shortName":"山东","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,山东省","lng":"117.000923","lat":"36.675807","pinyin":"Shandong"},{"cityId":410000,"name":"河南省","parentId":"100000","shortName":"河南","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,河南省","lng":"113.665412","lat":"34.757975","pinyin":"Henan"},{"cityId":420000,"name":"湖北省","parentId":"100000","shortName":"湖北","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,湖北省","lng":"114.298572","lat":"30.584355","pinyin":"Hubei"},{"cityId":430000,"name":"湖南省","parentId":"100000","shortName":"湖南","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,湖南省","lng":"112.982279","lat":"28.19409","pinyin":"Hunan"},{"cityId":440000,"name":"广东省","parentId":"100000","shortName":"广东","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,广东省","lng":"113.280637","lat":"23.125178","pinyin":"Guangdong"},{"cityId":450000,"name":"广西壮族自治区","parentId":"100000","shortName":"广西","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,广西壮族自治区","lng":"108.320004","lat":"22.82402","pinyin":"Guangxi"},{"cityId":460000,"name":"海南省","parentId":"100000","shortName":"海南","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,海南省","lng":"110.33119","lat":"20.031971","pinyin":"Hainan"},{"cityId":500000,"name":"重庆","parentId":"100000","shortName":"重庆","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,重庆","lng":"106.504962","lat":"29.533155","pinyin":"Chongqing"},{"cityId":510000,"name":"四川省","parentId":"100000","shortName":"四川","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,四川省","lng":"104.065735","lat":"30.659462","pinyin":"Sichuan"},{"cityId":520000,"name":"贵州省","parentId":"100000","shortName":"贵州","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,贵州省","lng":"106.713478","lat":"26.578343","pinyin":"Guizhou"},{"cityId":530000,"name":"云南省","parentId":"100000","shortName":"云南","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,云南省","lng":"102.712251","lat":"25.040609","pinyin":"Yunnan"},{"cityId":540000,"name":"西藏自治区","parentId":"100000","shortName":"西藏","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,西藏自治区","lng":"91.132212","lat":"29.660361","pinyin":"Tibet"},{"cityId":610000,"name":"陕西省","parentId":"100000","shortName":"陕西","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,陕西省","lng":"108.948024","lat":"34.263161","pinyin":"Shaanxi"},{"cityId":620000,"name":"甘肃省","parentId":"100000","shortName":"甘肃","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,甘肃省","lng":"103.823557","lat":"36.058039","pinyin":"Gansu"},{"cityId":630000,"name":"青海省","parentId":"100000","shortName":"青海","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,青海省","lng":"101.778916","lat":"36.623178","pinyin":"Qinghai"},{"cityId":640000,"name":"宁夏回族自治区","parentId":"100000","shortName":"宁夏","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,宁夏回族自治区","lng":"106.278179","lat":"38.46637","pinyin":"Ningxia"},{"cityId":650000,"name":"新疆维吾尔自治区","parentId":"100000","shortName":"新疆","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,新疆维吾尔自治区","lng":"87.617733","lat":"43.792818","pinyin":"Xinjiang"},{"cityId":710000,"name":"台湾","parentId":"100000","shortName":"台湾","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,台湾","lng":"121.509062","lat":"25.044332","pinyin":"Taiwan"},{"cityId":810000,"name":"香港特别行政区","parentId":"100000","shortName":"香港","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,香港特别行政区","lng":"114.173355","lat":"22.320048","pinyin":"Hong Kong"},{"cityId":820000,"name":"澳门特别行政区","parentId":"100000","shortName":"澳门","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,澳门特别行政区","lng":"113.54909","lat":"22.198951","pinyin":"Macau"},{"cityId":900000,"name":"钓鱼岛","parentId":"100000","shortName":"钓鱼岛","levelType":"1","cityCode":"","zipCode":"","mergerName":"中国,钓鱼岛","lng":"123.478088","lat":"25.742385","pinyin":"DiaoyuDao"}]
         * pid : s00002
         */

        private String pid;
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
            /**
             * cityId : 110000
             * name : 北京
             * parentId : 100000
             * shortName : 北京
             * levelType : 1
             * cityCode :
             * zipCode :
             * mergerName : 中国,北京
             * lng : 116.405285
             * lat : 39.904989
             * pinyin : Beijing
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
