package utils;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedList;

import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by 李浩 on 2016/11/7.
 */
public class Pic_MultiImageSelector_util {
    private LinkedList<LinkedList<String>> urlList;
    private Context context;

    public Pic_MultiImageSelector_util(LinkedList<LinkedList<String>> urlList, Context context) {
        this.urlList = urlList;
        this.context = context;
    }

    public MultiImageSelector getMultiImageSelector(int a,int b) {
        MultiImageSelector multiImageSelector = MultiImageSelector.create(context);
        multiImageSelector.showCamera(true); // 是否显示相机.默认为显示
        multiImageSelector.count(b); // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
        ArrayList<String> arrayList=new ArrayList<>();
        for(int i=0;i<urlList.size()-1;i++){
            arrayList.add(urlList.get(i).getFirst());
        }
        multiImageSelector.multi(); // 多选模式, 默认模式;
        multiImageSelector.start((Activity) context,a);//设置请求码,图片选择器启动
        return multiImageSelector;
    }

    public LinkedList<LinkedList<String>> getURLList(LinkedList<LinkedList<String>> list, ArrayList<String> path) {
        if(list.getLast().getFirst().equals("tianjia")){//去除掉最后一个
            list.removeLast();
        }
        if (path.size() == 6) {//如果返回的是6个
            path.remove(5);//去除掉最后的一个
            for (String s :path) {
                LinkedList<String> linkedList = new LinkedList<>();
                linkedList.addFirst(s);
                list.add(linkedList);
            }
        } else if (path.size() == 5) {//返回的是五个
            for (String s : path) {
                LinkedList<String> linkedList = new LinkedList<>();
                linkedList.addFirst(s);
                list.add(linkedList);
            }
        } else if (path.size() >=0 && path.size() < 5) {
            for (String s :path) {
                LinkedList<String> linkedList = new LinkedList<>();
                linkedList.addFirst(s);
                list.add(linkedList);
            }
            if(list.size()<5){
                LinkedList<String> linkedList = new LinkedList<>();
                linkedList.addFirst("tianjia");
                list.addLast(linkedList);
            }
        }
        return list;
    }

    public LinkedList<LinkedList<String>> deleteUrlList(LinkedList<LinkedList<String>> list,int i){
        if ((list.getLast().getFirst()).equals("tianjia")&&i!=list.size()-1) {
            list.remove(i);
        } else if (list.size()==5 && !(list.getLast().getFirst()).equals("tianjia")){
            list.remove(i);
            LinkedList<String> linkedlist=new LinkedList<>();
            linkedlist.addLast("tianjia");//在结尾加一个tianjia
            list.addLast(linkedlist);
        }
        return list;
    }
}

