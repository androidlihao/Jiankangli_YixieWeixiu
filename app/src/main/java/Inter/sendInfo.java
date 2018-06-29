package Inter;



import bean.Content_categories;
import bean.firm;
import bean.get_citys_bean;
import bean.product;

/**
 * Created by 李浩 on 2016/11/2.
 */
public interface sendInfo {
    void sendproduct(product pd);
    void sendfirm(firm fm);
    void sendcontent(Content_categories cc);
    void sendCityInfo(get_citys_bean citys_bean);
}
