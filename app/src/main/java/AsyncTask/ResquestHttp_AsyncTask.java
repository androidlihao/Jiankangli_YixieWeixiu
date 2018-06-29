package AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.example.hs.jiankangli_example1.seek.seek_activity;

import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.LinkedList;

import bean.Content_categories;
import bean.firm;
import bean.get_citys_bean;
import bean.product;

/**
 * Created by 李浩 on 2016/11/2.
 */
public class ResquestHttp_AsyncTask extends AsyncTask<LinkedList<String>,Void,String>{
    private LinkedList<String> url;
    private seek_activity context;
    private String operationId;
    public ResquestHttp_AsyncTask(LinkedList<String> url, Context context, String operationId){
        this.context= (seek_activity) context;
        this.url=url;
        this.operationId=operationId;
    }
    @Override
    protected String doInBackground(LinkedList<String>... strings) {
        for(int i=0;i<url.size();i++){
            RequestParams params = new RequestParams(url.get(i));
            if(i==2){
                org.json.JSONObject jsonObject=new org.json.JSONObject();
                try {
                    jsonObject.put("operationId",operationId);
                    String jsonString = Base64.encodeToString(jsonObject.toString().getBytes(),Base64.DEFAULT);//base64加密
                    params.setBodyContent(jsonString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (i==3){
                org.json.JSONObject jsonObject=new org.json.JSONObject();
                try {
                    jsonObject.put("levelType",2);
                    String jsonString = Base64.encodeToString(jsonObject.toString().getBytes(),Base64.DEFAULT);//base64加密
                    params.setBodyContent(jsonString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            final int finalI = i;
            x.http().post(params, new Callback.CacheCallback<String>() {
                @Override
                public boolean onCache(String result) {//缓存的信息
                    return false;
                }
                @Override
                public void onSuccess(String result) {
                    switch (finalI){
                        case 0:
                            context.sendproduct(JSONObject.parseObject(result,product.class));
                            break;
                        case 1:
                            context.sendfirm(JSONObject.parseObject(result,firm.class));
                            break;
                        case 2:
                           context.sendcontent(JSONObject.parseObject(result,Content_categories.class));
                            break;
                        case 3:
                            context.sendCityInfo(JSONObject.parseObject(result,get_citys_bean.class));
                            break;

                    }
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
        }
        return  null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
