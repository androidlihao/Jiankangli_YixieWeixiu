package utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by 李浩 on 2016/12/21.
 */

public class CheckContentTitle {
    public static void checked(String title,String content_categories_id,Context context,String tag,String URL){
        //开始验证标题重复性
        JSONObject jsonObjects=new JSONObject();
        try {
            jsonObjects.put("title",title);
            jsonObjects.put("contentCategoriesId",content_categories_id);
            RequestNet.queryServer(jsonObjects,URL,context,tag);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
