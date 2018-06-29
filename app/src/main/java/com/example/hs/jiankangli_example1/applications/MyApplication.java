package com.example.hs.jiankangli_example1.applications;

import android.app.Application;
import android.widget.Toast;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import org.xutils.x;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by 李浩 on 2016/9/18.
 */
public class MyApplication extends Application{
    public static Toast toast;
    //各个平台的配置，建议放在全局Application或者程序入口
    {   PlatformConfig.setSinaWeibo("55045656","f65f232c49ffe56eb0f2d624bea4ce93");//新浪微博
        PlatformConfig.setQQZone("1105713577","WRwvTSy4dkxaSvqH");//QQ
        PlatformConfig.setWeixin("wxf882c6f54edb9595", "9432d8aca38bd616884774c1dec7bd58");//微信
    }
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);//设置xutils3初始化
        JPushInterface.setDebugMode(false); //设置为调试模式，具体发布的时候可以直接设置为false
        JPushInterface.init(this);
        UMShareAPI.get(this);//友盟初始化
        //设置回调
        Config.REDIRECT_URL="http://sns.whalecloud.com/sina2/callback";
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = false;
        Config.isJumptoAppStore = true;//没有微信跳转到对应的下载页
    }
}
