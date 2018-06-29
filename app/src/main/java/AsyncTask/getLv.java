package AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import bean.info;

/**
 * Created by 李浩 on 2016/10/27.
 */
public class getLv extends AsyncTask<RequestParams,Void,String>{
    private TextView tv_jf_id;
    private ImageView iv_dj_id;
    private RequestParams params;
    private Context context;
    private String lv;
    public  static String results;
    public  getLv(TextView tv_jf_id, ImageView iv_dj_id, RequestParams params, Context context) {
        this.tv_jf_id=tv_jf_id;
        this.iv_dj_id=iv_dj_id;
        this.params=params;
        this.context=context;
    }
    @Override
    protected String doInBackground(RequestParams... requestParamses) {
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject js=new JSONObject(result);
                    if(js.getString("code").equals("200")){
                         results=result;
                    }else{
                        Toast.makeText(context,js.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
        return results;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i("TAG", "onPostExecute: "+result);
        if(result!=null&&!result.isEmpty()){
             info infos = com.alibaba.fastjson.JSONObject.parseObject(result, info.class);
            if(infos!=null){
                if(infos.getBody()!=null){
                    if(infos.getBody().getData()!=null){
                        String jf = infos.getBody().getData().getIntegral()+"";
                        tv_jf_id.setText(jf);
                        lv = (infos.getBody().getData().getLevelName()).toLowerCase();
                    }
                }
            }
        }
        switch (lv+""){
            case "lv1":
                iv_dj_id.setImageDrawable(context.getResources().getDrawable(R.mipmap.lv1_3x));
                break;
            case "lv2":
                iv_dj_id.setImageDrawable(context.getResources().getDrawable(R.mipmap.lv2_3x));
                break;
            case "lv3":
                iv_dj_id.setImageDrawable(context.getResources().getDrawable(R.mipmap.lv3_3x));
                break;
            case "lv4":
                iv_dj_id.setImageDrawable(context.getResources().getDrawable(R.mipmap.lv4_3x));
                break;
            case "lv5":
                iv_dj_id.setImageDrawable(context.getResources().getDrawable(R.mipmap.lv5_3x));
                break;
            default:
                iv_dj_id.setImageDrawable(context.getResources().getDrawable(R.mipmap.lv5_3x));
                break;
        }
        super.onPostExecute(result);
    }
}
