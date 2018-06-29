package utils;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import AsyncTask.push_AsyncTask;
import AsyncTask.query_AsyncTask;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * Created by 李浩 on 2017/1/13.
 */

public class RequestNet {


    public static void requestServer(JSONObject js, String uri, Activity activity, String tag){
            RequestParams params = new RequestParams(uri);
            String jsonString = Base64.encodeToString(js.toString().getBytes(), Base64.NO_WRAP);//base64加密
            JSONObject json=RequestNet.getDeviceInfo(activity);
            String HeaderjsonString = Base64.encodeToString(json.toString().getBytes(), Base64.NO_WRAP);//base64加密
            params.setHeader("header",HeaderjsonString);
            params.setBodyContent(jsonString);
            new push_AsyncTask(activity,tag).execute(params);
    }
    public static void queryServer(JSONObject js, String uri,Object object,String tag){
        RequestParams params=new RequestParams(uri);
        if (js!=null){
            String jsonString = Base64.encodeToString(js.toString().getBytes(),Base64.NO_WRAP);//base64加密
            params.setBodyContent(jsonString);
        }
        try {
            Activity activity= (Activity) object;
            JSONObject json=RequestNet.getDeviceInfo(activity);
            String HeaderjsonString = Base64.encodeToString(json.toString().getBytes(), Base64.NO_WRAP);//base64加密
            params.setHeader("header",HeaderjsonString.trim());
        }catch (Exception e){
            Activity activity= ((Fragment)object).getActivity();
            JSONObject json=RequestNet.getDeviceInfo(activity);
            String HeaderjsonString = Base64.encodeToString(json.toString().getBytes(), Base64.NO_WRAP);//base64加密
            params.setHeader("header",HeaderjsonString.trim());
        }
        Log.i("TAG", "queryServer: "+js);
        new query_AsyncTask(params,object,tag).execute();
    }
    public static JSONObject getDeviceInfo(Context context){
        JSONObject j = null;
        try {
            TelephonyManager tm = (TelephonyManager)context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String macAdress = info.getMacAddress(); //获取mac地址
            DisplayMetrics metric = new DisplayMetrics();
            j = new JSONObject();
            JSONObject AppJs=new JSONObject();
            AppJs.put("packageName",packInfo.packageName);
            AppJs.put("appName",context.getResources().getString(packInfo.applicationInfo.labelRes));
            AppJs.put("version",packInfo.versionName);
            AppJs.put("mobileType","android");
            AppJs.put("channel",getChannelName(context));
            j.put("APP",AppJs);
            JSONObject DeviceJs=new JSONObject();
            DeviceJs.put("platform",android.os.Build.VERSION.RELEASE);
            DeviceJs.put("model", android.os.Build.MODEL);
            DeviceJs.put("factory",android.os.Build.BRAND);
            DeviceJs.put("denstiy", metric.density);
            DeviceJs.put("imei",tm.getDeviceId());
            DeviceJs.put("mac",macAdress);
            if (Common_utils.getDB()!=null){
                DeviceJs.put("clientId",Common_utils.getDB().getMemberId());
            }
            j.put("device",DeviceJs);
            JSONObject UserJs=new JSONObject();
            if (Common_utils.getDB()!=null){
                UserJs.put("token",Common_utils.getDB().getUuid());
            }
            j.put("user",UserJs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
     }
    /**
     * 获取渠道名
     *
     * @param ctx 此处习惯性的设置为activity，实际上context就可以
     * @return 如果没有获取成功，那么返回值为空
     */
    public static String getChannelName(Context ctx) {
        if (ctx == null) {
            return null;
        }
        String channelName = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = applicationInfo.metaData.getString("UMENG_CHANNEL");
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelName;
    }
}
