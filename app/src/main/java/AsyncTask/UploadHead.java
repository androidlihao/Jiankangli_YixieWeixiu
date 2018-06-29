package AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;
import bean.personal;
import Inter.Globle;
/**
 * Created by 李浩 on 2016/10/27.
 */
public class UploadHead extends AsyncTask<RequestParams,Void,String>{
    private Context context;
    private RequestParams params;
    private personal.BodyBean.DataBean dataBean;
    private DbManager db;
    private final static String Uphead_URL= Globle.TEST_URL+"/api/member/updateHeadPath";//上传头像
    private String s;
    public  static String results;
    public UploadHead(Context context,RequestParams params, personal.BodyBean.DataBean dataBean, DbManager db,String s) {
        this.params=params;
        this.context=context;
        this.dataBean=dataBean;
        this.db=db;
        this.s=s;
    }
    @Override
    protected String doInBackground(RequestParams... requestParamses) {
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }
            @Override
            //将图片上传到服务器成功
            public void onSuccess(String result) {
                results=result;
                JSONObject jsons= null;
                try {
                    jsons = new JSONObject(result);
                    String code=jsons.getString("code");
                    if(s.equals("图片")&&code.equals("200")){
                                JSONObject js=jsons.getJSONObject("body");
                                String url=js.getString("url");//本地使用的,修改更新本地数据库里面的头像地址
                                dataBean.setHeadPath(url);
                                try {
                                    db.update(dataBean,"headPath");//修改数据库里面的头像数据
                                } catch (DbException e) {
                                    e.printStackTrace();
                                }
                                String local_path = js.getString("local_path");
                                RequestParams params=new RequestParams(Uphead_URL);
                                JSONObject jsonObject=new JSONObject();
                                jsonObject.put("member_id", dataBean.getMemberId());
                                jsonObject.put("head_path",local_path);
                                String jsonString = Base64.encodeToString(jsonObject.toString().getBytes(), Base64.DEFAULT);//base64加密
                                params.setBodyContent(jsonString);
                                new UploadHead(context,params,dataBean,db,"头像").execute();//再次启动异步任务
                    }else if(s.equals("头像")&&code.equals("200")){
                        Toast.makeText(context, "上传头像成功!", Toast.LENGTH_SHORT).show();
                    }else if(s.equals("头像")&&!code.equals("200")){
                        Toast.makeText(context, "上传头像失败!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context, "上传头像失败，服务器或网络异常！", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        return results;
    }

    @Override
    protected void onPostExecute(String result1) {

        super.onPostExecute(result1);
    }
}
