package AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import bean.getPic_path;
import bean.Pic_bean;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import Inter.Globle;

/**
 * Created by 李浩 on 2016/11/15.
 */
public class Upload_Photo extends AsyncTask<File,Void,String> {
    private Context context;
    private int a;
    private String str;
    private final String UP_PIC= Globle.TEST_URL+"/api/pic/upload";

    public Upload_Photo(Context context, int a, String str){
        this.context=context;
        this.a=a;
        this.str=str;
    }
    @Override
    protected String doInBackground(File... files) {
        up(files[0]);
        return null;
    }
    private void up(File file) {
        Luban.get(context)
                .load(file)                     //传人要压缩的图片
                .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
                .setCompressListener(new OnCompressListener() { //设置回调
                    private RequestParams params;
                    @Override
                    public void onStart() {
                        //  压缩开始前调用，可以在方法内启动 loading UI
                        params = new RequestParams(UP_PIC);
                        params.setMultipart(true);
                    }
                    @Override
                    public void onSuccess(File file) {
                        //  压缩成功后调用，返回压缩后的图片文件
                        params.setConnectTimeout(40000);
                        params.addBodyParameter("file",file);//将所选择文件,文件过大上传不了
                        x.http().post(params, new Callback.CacheCallback<String>() {
                            @Override
                            public boolean onCache(String result) {
                                return false;
                            }
                            @Override
                            public void onSuccess(String result) {//请求服务器成功
                                Log.i("Tag", "pic: "+result);
                                try {
                                    JSONObject js=new JSONObject(result);
                                    Toast.makeText(context, js.getString("message"), Toast.LENGTH_SHORT).show();
                                    getPic_path paths=new getPic_path();
                                    if(js.getString("code").equals("200")){
                                        paths.setPic_type(a);
                                        JSONObject json=js.getJSONObject("body");
                                        paths.setLocal_path(json.getString("local_path"));
                                        switch (a){
                                            case 4:
                                                Pic_bean.logo_path=paths;
                                                Pic_bean.logo_boolean=true;
                                                break;
                                            case 5:
                                                Pic_bean.zhengmian_path=paths;
                                                Pic_bean.zhengmian_boolean=true;
                                                break;
                                            case 6:
                                                Pic_bean.back_path=paths;
                                                Pic_bean.fanmian_boolean=true;
                                                break;
                                            case 7:
                                                Pic_bean.zhizhao_path=paths;
                                                Pic_bean.zhizhao_boolean=true;
                                                break;
                                        }
                                    }
                                } catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                Toast.makeText(context, "图片上传失败！", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });
                    }
                    @Override
                    public void onError(Throwable e){
                        //  当压缩过去出现问题时调用
                        Log.i("TAG", "onError: "+e);
                    }
                }).launch();//启动压缩
    }
}
