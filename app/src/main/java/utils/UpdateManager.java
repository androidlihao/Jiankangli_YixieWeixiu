package utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import AsyncTask.query_AsyncTask;
import Inter.Globle;
import Inter.get_net_Info;

import static android.content.ContentValues.TAG;
import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * Created by 李浩 on 2016/12/16.
 */

public class UpdateManager implements get_net_Info {
    private Context mContext; //上下文
    private String apkUrl ; //apk下载地址
    private static final String savePath = "/sdcard/updateAPK/"; //apk保存到SD卡的路径
    private static final String saveFileName = savePath + "apkName.apk"; //完整路径名
    private ProgressBar mProgress; //下载进度条控件
    private static final int DOWNLOADING = 1; //表示正在下载
    private static final int DOWNLOADED = 2; //下载完毕
    private static final int DOWNLOAD_FAILED = 3; //下载失败
    private int progress; //下载进度
    private boolean cancelFlag = false; //取消下载标志位
    private String serverVersion; //从服务器获取的版本号
    private boolean isUpdata;
    private String updateDescription = ""; //更新内容描述信息
    private boolean forceUpdate ; //是否强制更新
    private AlertDialog alertDialog1, alertDialog2; //表示提示对话框、进度条对话框
    private TextView tv_jd_id;

    /** 构造函数 */
    public UpdateManager(Context context) {
        this.mContext = context;
    }
    public void getdataManager(){
        try {
            PackageInfo packageinfo=getVersionName();
            //请求服务器
            RequestParams params=new RequestParams(Globle.TEST_URL+"/api/version/versionUpgrade");
            JSONObject js=new JSONObject();
            js.put("version_num",packageinfo.versionCode+"");
            js.put("type","2");
            String jsonString = Base64.encodeToString(js.toString().getBytes(),Base64.DEFAULT);//base64加密
            params.setBodyContent(jsonString);
            new query_AsyncTask(params,new UpdateManager(mContext),"updata").execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void getinfo(String str) {
        Log.i(TAG, "getinfo: "+str);
        try {
            JSONObject json=new JSONObject(str);
            switch (json.getString("code")){
                case "40000":
                    isUpdata=false;
                    break;
                case "40200"://有新版本,请选择更新
                    forceUpdate=false;
                    isUpdata=true;
                    apkUrl=json.getJSONObject("body").getJSONObject("data").getString("downpath");//下载地址
                    serverVersion=json.getJSONObject("body").getJSONObject("data").getString("showVersionNum");//外部显示的版本
                    break;
                case "40100"://有新版本,请强制更新
                    forceUpdate=true;
                    isUpdata=true;
                    apkUrl=json.getJSONObject("body").getJSONObject("data").getString("downpath");//下载地址
                    serverVersion=json.getJSONObject("body").getJSONObject("data").getString("showVersionNum");//外部显示的版本
                    break;
            }
            Log.i(TAG, "getinfo: "+isUpdata);
            if (isUpdata==false){
                //如果版本最新，则不需要更新
                return;
            }
            AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
            dialog.setTitle("发现新版本 ：" + serverVersion);
            dialog.setMessage(updateDescription);
            dialog.setPositiveButton("现在更新", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    arg0.dismiss();
                    showDownloadDialog();
                }
            });
            //是否强制更新
            if (forceUpdate == false){
                dialog.setNegativeButton("待会更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        arg0.dismiss();
                    }
                });
            }
            alertDialog1  = dialog.create();
            alertDialog1.setCancelable(false);
            alertDialog1.show();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    /*
2. * 获取当前程序的版本号
3. */
    private PackageInfo getVersionName() throws Exception{
          //获取packagemanager的实例
            PackageManager packageManager = mContext.getPackageManager();
           //getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packInfo;
        }
    /** 显示更新对话框 */
    public void showNoticeDialog() {
        getdataManager();
        Log.i(TAG, "showNoticeDialog: "+isUpdata);
    }

    /** 显示进度条对话框 */
    public void showDownloadDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle("正在更新");
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.softupdate_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        tv_jd_id = (TextView) v.findViewById(R.id.tv_jd_id);
        dialog.setView(v);
        //如果是强制更新，则不显示取消按钮
        if (forceUpdate == false) {
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    arg0.dismiss();
                    cancelFlag = true;
                }
            });
        }
        alertDialog2  = dialog.create();
        alertDialog2.setCancelable(false);
        alertDialog2.show();
        //下载apk
        downloadAPK();
    }

    /** 下载apk的线程 */
    public void downloadAPK() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    URL url = new URL(apkUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();

                    File file = new File(savePath);
                    if(!file.exists()){
                        file.mkdir();
                    }
                    String apkFile = saveFileName;
                    File ApkFile = new File(apkFile);
                    FileOutputStream fos = new FileOutputStream(ApkFile);

                    int count = 0;
                    byte buf[] = new byte[1024];

                    do{
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int)(((float)count / length) * 100);
                        //更新进度
                        mHandler.sendEmptyMessage(DOWNLOADING);
                        if(numread <= 0){
                            //下载完成通知安装
                            mHandler.sendEmptyMessage(DOWNLOADED);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    }while(!cancelFlag); //点击取消就停止下载.

                    fos.close();
                    is.close();
                } catch(Exception e) {
                    mHandler.sendEmptyMessage(DOWNLOAD_FAILED);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /** 更新UI的handler */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case DOWNLOADING:
                    mProgress.setProgress(progress);
                    tv_jd_id.setText(progress+"%");
                    break;
                case DOWNLOADED:
                    if (alertDialog2 != null)
                        alertDialog2.dismiss();
                    installAPK();
                    break;
                case DOWNLOAD_FAILED:
                    Toast.makeText(mContext, "网络断开，请稍候再试", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };

    /** 下载完成后自动安装apk */
    public void installAPK() {
        File apkFile = new File(saveFileName);
        if (!apkFile.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + apkFile.toString()), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

}