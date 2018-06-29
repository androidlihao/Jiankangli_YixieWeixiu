package bean;

import java.io.Serializable;
import java.util.LinkedList;

import Inter.Globle;

/**
 * Created by 李浩 on 2016/11/8.
 */
public class Pic_bean implements Serializable{
    public static LinkedList<LinkedList<String>> list;
    public static boolean UoLoadPic=true;
    public static String URL=Globle.TEST_URL+"/app/";
    public static LinkedList<LinkedList<String>> answer_finallist;
    public static boolean answer_final_pic=true;//默认为true，是可以上传的

    public static LinkedList<LinkedList<String>> answer_frist_list;
    public static boolean answer_frist_pic=true;//默认为true，是可以上传的

    public static LinkedList<LinkedList<String>> answer_second_list;
    public static boolean answer_second_pic=true;//默认为true，是可以上传的

    public static String time;

    public static getPic_path zhengmian_path;

    public static boolean zhengmian_boolean=false;
    public static boolean fanmian_boolean=false;
    public static boolean logo_boolean=false;
    public static boolean zhizhao_boolean=false;

    public static getPic_path back_path;
    public static getPic_path logo_path;
    public static getPic_path zhizhao_path;

    public static boolean is_Share=false;
    public static boolean up_ing;//是否上传成功了
    public static String operationId="0";

    public static String collects;
}
