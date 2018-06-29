package utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.password.Login_activity;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import bean.personal;

/**
 * Created by 李浩 on 2016/12/26.  公共工具类
 */

public class Common_utils {
    private static personal.BodyBean.DataBean person;
    private Context mContxt;

    public Common_utils(Context mContxt) {
        this.mContxt = mContxt;
    }
    /**
     * 检查当前网络是否可用
     *
     * @return
     */

    public static boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //判断是否为手机号码
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][34578]\\d{9}";
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    //判断是否为邮箱
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    //判断是否有内存卡
    public static boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    private String Nickname;
    public String getNickName(){
        if(isLogin()){
            getDB();
            Nickname = person.getNickname();
        }else{
            Login();
        }
        return Nickname;
    }
    //是否登录
    public boolean isLogin() {
        //获取SharePreferences
        SharedPreferences sp = mContxt.getApplicationContext().getSharedPreferences("config", 0);
        boolean isLogin = sp.getBoolean("isLogin", false);//如果没有isLogin的时候直接返回false
        return isLogin;
    }
    public void  Login(){
        Intent intent=new Intent(mContxt, Login_activity.class);
        mContxt.startActivity(intent);
    }
    //获取数据库表
    public static personal.BodyBean.DataBean getDB(){
        try {
            person = x.getDb(XUtilsDB.getDBconfig()).findFirst(personal.BodyBean.DataBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return person;
    }
    private String name;
    public String getName(){
        if(isLogin()){
            getDB();
            name = person.getName();
        }else{
            Login();
        }
        return name;
    }
    private int sex;
    //得到性别
    public int getSex(){
        if(isLogin()){
            getDB();
            sex = person.getSex();
        }else{
            Login();
        }
        return sex;
    }
    private String code;
    public String getCode(){
        if(isLogin()){
            getDB();
            code = person.getFcode();
        }else{
            Login();
        }
        return code;
    }
    private String phone;
    public String getPhoneNumber(){
        if(isLogin()){
            getDB();
            phone = person.getPhone();
        }else{
            Login();
        }
        return phone;
    }
    private String lvName;
    public String getlv_name(){
        if(isLogin()){
            getDB();
            lvName = person.getLevelName();
        }else{
            Login();
        }
        return  lvName;
    }
    private String Memberid;
    public String getMemberid(){//获取会员ID
        if(isLogin()){
            getDB();
            Memberid = person.getMemberId()+"";
        }else{
            Memberid="";
        }
        return Memberid;
    }
    public void showToast(Toast toast,String string) {
        if (toast == null) {
            toast = Toast.makeText(mContxt,string,Toast.LENGTH_SHORT);
        } else {
            toast.cancel();//关闭吐司显示
            toast = Toast.makeText(mContxt,string ,Toast.LENGTH_SHORT);
        }
        toast.show();//重新显示吐司
    }
    public static void deletePic(String fileName){
        //上传完成以后将缩略图删除
        File file = new File(Environment.getExternalStorageDirectory() + "/",fileName);
        file.delete();
    }
    //裁剪图片
    public void crop(Uri uri, int PHOTO_REQUEST_CUT) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");//系统自带的裁剪页面
            intent.setDataAndType(uri,"image/*");//输入数据的路径和数据类型
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("outputFormat", "JPEG");
            intent.putExtra("noFaceDetection", true);
            intent.putExtra("return-data", true);
            Activity actxivity= (Activity) mContxt;
            actxivity.startActivityForResult(intent,PHOTO_REQUEST_CUT);//跳转到裁剪页面，然后返回裁剪以后的照片
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
