package bean;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by 李浩 on 2016/11/21.
 */
public class send_LinkedList_bean implements Serializable{
    public LinkedList<LinkedList<String>> sends;

    public LinkedList<LinkedList<String>> getSends() {
        return sends;
    }

    public void setSends(LinkedList<LinkedList<String>> sends) {
        this.sends = sends;
    }
}
