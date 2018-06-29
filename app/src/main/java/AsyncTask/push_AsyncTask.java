package AsyncTask;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.example.hs.jiankangli_example1.password.Login_activity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashSet;
import java.util.Set;

import bean.Pic_bean;
import bean.personal;
import cn.jpush.android.api.JPushInterface;
import utils.LoadingDialog;
import utils.XUtilsDB;

import static android.R.id.edit;

/**
 * Created by 李浩 on 2016/11/10.
 */
public class push_AsyncTask extends AsyncTask<RequestParams,Void,String>{
    private Context applicationContext;
    private LoadingDialog mDialog;
    private String tag;
    @Override
    protected void onPreExecute() {
        mDialog = new LoadingDialog(applicationContext);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {//当加载框出现时，屏蔽返回键
                return true;
            }
        });
        super.onPreExecute();
    }

    public push_AsyncTask(Context applicationContext, String tag) {
        this.tag=tag;
        this.applicationContext=applicationContext;
    }
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==1){
                Toast.makeText(applicationContext,tag+"成功！",Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
                if (tag.equals("知识点发布")||tag.equals("提问")){//如果是发布知识点和提问的话
                    Pic_bean.up_ing=true;
                }
                answer_Application.getInstance().kill();
            }
        };
    };
    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(RequestParams... requestParamses) {
        x.http().post(requestParamses[0], new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }
            @Override
            public void onSuccess(String result) {
                Log.i("TAG", "push: "+result);
                try {
                    JSONObject js=new JSONObject(result);
                    switch (js.getString("code")){
                        case "200":
                            new Thread(new Runnable(){
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                        if(Pic_bean.list!=null||Pic_bean.answer_frist_list!=null||Pic_bean.answer_second_list!=null||Pic_bean.answer_finallist!=null){
                                            Pic_bean.answer_frist_list=null;
                                            Pic_bean.answer_second_list=null;
                                            Pic_bean.answer_finallist=null;
                                            Pic_bean.list=null;
                                        }
                                        Pic_bean.zhengmian_path=null;
                                        Pic_bean.back_path=null;
                                        Pic_bean.logo_path=null;
                                        Pic_bean.zhizhao_path=null;
                                        mHandler.sendEmptyMessage(1);
                                    } catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                            break;
                        case "20010":
                            Toast.makeText(applicationContext,"标题重复!", Toast.LENGTH_SHORT).show();
                            SystemClock.sleep(1000);
                            mDialog.dismiss();
                            break;
                        case "20002":
                            Toast.makeText(applicationContext,"您已被注销，请联系管理员!", Toast.LENGTH_SHORT).show();
                            //强制退出
                            Set<String> strings=new HashSet<>();
                            JPushInterface.setAliasAndTags(applicationContext, "", strings,new Login_activity().mAliasCallback);
                            SharedPreferences sp=applicationContext.getSharedPreferences("config",0);
                            SharedPreferences.Editor edit = sp.edit();
                            edit.clear();
                            edit.commit();//清除用户登录信息
                            //删除保存在数据库中的信息
                            DbManager db=x.getDb(XUtilsDB.getDBconfig());
                            try {
                                db.dropTable(personal.BodyBean.DataBean.class);//将存储的表格也删除
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                            SystemClock.sleep(1000);
                            mDialog.dismiss();
                            break;
                        case "20003":
                            Toast.makeText(applicationContext,"您已被禁言，请联系管理员", Toast.LENGTH_SHORT).show();
                            SystemClock.sleep(1000);
                            mDialog.dismiss();
                            break;
                        default:
                            Toast.makeText(applicationContext,js.getString("message"), Toast.LENGTH_SHORT).show();
                            SystemClock.sleep(1000);
                            mDialog.dismiss();
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(applicationContext,"服务器或网络异常！", Toast.LENGTH_SHORT).show();
                SystemClock.sleep(1000);
                mDialog.dismiss();
                Log.i("ATG", "onError: "+ex);
            }
            @Override
            public void onCancelled(CancelledException cex) {
                //取消

            }

            @Override
            public void onFinished() {

            }
        });
        return null;
    }

    public void clearInfo(Activity activity, String code){
        switch (code){
            case "200":

                break;
            case "20002":
                Toast.makeText(activity,"您已被注销，请联系管理员!", Toast.LENGTH_SHORT).show();
                //强制退出
                Set<String> strings=new HashSet<>();
                JPushInterface.setAliasAndTags(activity, "", strings,new Login_activity().mAliasCallback);
                SharedPreferences sp=activity.getSharedPreferences("config",0);
                SharedPreferences.Editor edit = sp.edit();
                edit.clear();
                edit.commit();//清除用户登录信息
                //删除保存在数据库中的信息
                DbManager db=x.getDb(XUtilsDB.getDBconfig());
                try {
                    db.dropTable(personal.BodyBean.DataBean.class);//将存储的表格也删除
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case "20003":
                Toast.makeText(activity,"您已被禁言，请联系管理员", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
