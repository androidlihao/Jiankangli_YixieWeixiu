package bean;

import java.io.Serializable;

/**
 * Created by 李浩 on 2016/11/15.
 */
public class getPic_path implements Serializable{
    private int Pic_type;
    private String local_path;

    public int getPic_type() {
        return Pic_type;
    }

    public void setPic_type(int pic_type) {
        Pic_type = pic_type;
    }

    public String getLocal_path() {
        return local_path;
    }

    public void setLocal_path(String local_path) {
        this.local_path = local_path;
    }
}
