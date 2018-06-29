package bean;

import java.util.List;

/**
 * Created by 李浩 on 2016/11/10.
 */
public class title_bean {

    /**
     * title : 医械梦想家
     * url : 192.168.1.3/app
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String title;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
