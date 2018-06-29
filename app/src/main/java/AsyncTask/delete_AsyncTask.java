package AsyncTask;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import Inter.Globle;

/**
 * Created by 李浩 on 2016/11/8.
 */
public class delete_AsyncTask extends AsyncTask<ArrayList<String>,Void,String>{
    private final static String del_Pic_URL= Globle.TEST_URL+"/api/pic/del";

    @Override
    protected String doInBackground(ArrayList<String>... arrayLists) {
        RequestParams params=new RequestParams(del_Pic_URL);
        try {
            JSONObject jsonObject=new JSONObject();
            JSONArray jsonArray=new JSONArray();
            if(arrayLists[0]!=null&&arrayLists[0].size()!=0&&!arrayLists[0].isEmpty()){
                for(int i=0;i<arrayLists[0].size();i++){
                    jsonArray.put(arrayLists[0].get(i));
                }
                jsonObject.put("paths",jsonArray);
            }
            String jsonString = Base64.encodeToString(jsonObject.toString().getBytes(), Base64.DEFAULT);//base64加密
            params.setBodyContent(jsonString);
            x.http().post(params, new Callback.CacheCallback<String>() {
                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    Log.i("TAG", "delete: "+result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
