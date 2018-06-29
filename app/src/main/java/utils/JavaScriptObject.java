package utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.MainActivity;
import com.example.hs.jiankangli_example1.PartsActivity;
import com.example.hs.jiankangli_example1.Push_Info.push_info_company_frist_activity;
import com.example.hs.jiankangli_example1.Push_Info.push_info_other_frist_activity;
import com.example.hs.jiankangli_example1.Push_Info.push_info_project_frist_activity;
import com.example.hs.jiankangli_example1.Push_Info.push_personal_info_frist_activity;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.ask.ask_frist_activity;
import com.example.hs.jiankangli_example1.ask.ask_other_activity;
import com.example.hs.jiankangli_example1.fix_helper_Activity;
import com.example.hs.jiankangli_example1.password.Login_activity;
import com.example.hs.jiankangli_example1.digest_or_comment.Digest_comment_activity;
import com.example.hs.jiankangli_example1.answer.answer_final_activity;
import com.example.hs.jiankangli_example1.answer.answer_frist_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.explanation_of_nouns_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.push_knowledge_repair_frist_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.work_or_error_frist_kactivity;
import com.example.hs.jiankangli_example1.seek.seek_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.fault_frist_activity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import AsyncTask.query_AsyncTask;
import Inter.Globle;
import Inter.get_net_Info;
import bean.My_draft;
import bean.Pic_bean;
import bean.personal;
import bean.question_bean;
import integral.terms_activity;

import static android.content.ContentValues.TAG;

public class JavaScriptObject implements get_net_Info {
    Context mContxt;
    private String headPath;
    private JSONObject json;

    public JavaScriptObject(Context mContxt) {
        this.mContxt = mContxt;
    }
    @JavascriptInterface//获取memberid
    public String getMemberid(String s){
        String Memberid=new Common_utils(mContxt).getMemberid();
        return Memberid;
    }
    @JavascriptInterface
    public void Login(){//跳转到登录页
        new Common_utils(mContxt).Login();
    }
    @JavascriptInterface
    public void fixHelper(){//跳转到登录页
        Intent intent=new Intent(mContxt,fix_helper_Activity.class);
        mContxt.startActivity(intent);
    }
    @JavascriptInterface
    public void Article(){
        Intent intent=new Intent(mContxt, terms_activity.class);
        mContxt.startActivity(intent);
    }
    public static String JsTitle;
    @JavascriptInterface
    public void setJsTitle(String JsTitle){
        this.JsTitle=JsTitle;
    }
    private static String js=null;
    @JavascriptInterface
    public void get_Share_Content(String JSON){//分享数据
        js=JSON;
        Log.i(TAG, "get_Share_Content: "+js);
    }
    @JavascriptInterface//每次都往这个里面传入值
    public void Save_OperationId(String OperationId){
        Pic_bean.operationId=OperationId;
    }
    @JavascriptInterface
    public void Share_board_show(){
        try {
            Thread.sleep(50);
            Log.i(TAG, "Share_board_show: "+js);
            if(js!=null&&!js.isEmpty()){
                json = new JSONObject(js);
                String text= json.getString("summay");
                String title= json.getString("title");
                if(text.trim().isEmpty()){
                    text=" ";
                }
                if(title.trim().isEmpty()){
                    title=" ";
                }
                String URl=Globle.share_Url+ json.getString("type")+"/"+ json.getString("content_id")+"/"+getMemberid("");
                Log.i("TAG", "Share_board_show: "+URl);
                ShareBoardConfig shareBoardConfig = new ShareBoardConfig().setTitleVisibility(false);
                shareBoardConfig.setCancelButtonText("取消");
                shareBoardConfig.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR); // 圆角背景
                shareBoardConfig.setIndicatorColor(android.R.color.white,android.R.color.white);
                new ShareAction((Activity)mContxt).withText(text).withTargetUrl(URl).
                        withTitle(title) .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA)
                        .setCallback(new ShareListener()).open(shareBoardConfig);//当前界面
            }else{
                Toast.makeText(mContxt,"当前界面内容无法分享！", Toast.LENGTH_SHORT).  show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    @JavascriptInterface
    public void showOrhide(final String tag) {
        Log.i(TAG, "showOrhide: "+tag);
        final Activity activity= (Activity) mContxt;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View view=activity.findViewById(R.id.toolbar_id);
                Window w=activity.getWindow();
                switch (tag){
                    case "1":
                        view.setVisibility(View.GONE);//让控件消失
                        WindowManager.LayoutParams lp = w.getAttributes();
                        lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                        w.setAttributes(lp);
                        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                        break;
                    case "0":
                        if (view.getVisibility() == View.GONE) {
                            TranslateAnimation enterAnimation = new TranslateAnimation(0, 0,-view.getMeasuredHeight(), 0);
                            enterAnimation.setDuration(400);
                            view.setAnimation(enterAnimation);
                            view.setVisibility(View.VISIBLE);
                        }
                        WindowManager.LayoutParams attr = w.getAttributes();
                        attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
                        w.setAttributes(attr);
                        w.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                        break;
                }
            }
        });
    }
    public class ShareListener implements UMShareListener {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Pic_bean.is_Share=true;//说明是分享
            String toast = null;
            String share_type=null;
            if(platform==SHARE_MEDIA.WEIXIN){
                toast="微信";
                share_type="2";
            }else if(platform==SHARE_MEDIA.QQ){
                toast="QQ";
                share_type="3";
            }else if(platform==SHARE_MEDIA.SINA){
                toast="新浪微博";
                share_type="4";
            }else if(platform==SHARE_MEDIA.WEIXIN_CIRCLE){
                toast="朋友圈";
                share_type="5";
            }
            try {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("member_id",getMemberid(""));
                jsonObject.put("content_id",json.get("content_id"));
                jsonObject.put("share_type",share_type);
                jsonObject.put("content_type",json.get("content_type"));
                RequestNet.queryServer(jsonObject,Globle.TEST_URL+"/api/knowledge/saveshareinfo",mContxt,"share");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(mContxt, toast + "分享成功啦", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
           boolean a= UMShareAPI.get(mContxt).isInstall((Activity) mContxt,platform);//判断当前平台是否安装
            String toast = null;
            if(platform==SHARE_MEDIA.WEIXIN){
                toast="微信";
            }else if(platform==SHARE_MEDIA.QQ){
                toast="QQ";
            }else if(platform==SHARE_MEDIA.SINA){
                toast="新浪微博";
            }else if(platform==SHARE_MEDIA.WEIXIN_CIRCLE){
                toast="朋友圈";
            }
            if(a==false){
                if(platform==SHARE_MEDIA.WEIXIN_CIRCLE){
                    toast="微信";
                }
                Toast.makeText(mContxt,"您未安装"+toast+"客户端"+",无法进行分享！", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mContxt,toast +"分享失败啦", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Log.i("TAG", "onCancel: ");
            String toast = null;
            if(platform==SHARE_MEDIA.WEIXIN){
                toast="微信";
            }else if(platform==SHARE_MEDIA.QQ){
                toast="QQ";
            }else if(platform==SHARE_MEDIA.SINA){
                toast="新浪微博";
            }else if(platform==SHARE_MEDIA.WEIXIN_CIRCLE){
                toast="朋友圈";
            }
            Toast.makeText(mContxt,toast + "分享取消了", Toast.LENGTH_SHORT).show();
        }
    }
    @JavascriptInterface
    public String getHeadPath(){
        if(new Common_utils(mContxt).isLogin()){//如果已经登录
            try {
                headPath = x.getDb(XUtilsDB.getDBconfig()).findFirst(personal.BodyBean.DataBean.class).getHeadPath();
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        return headPath;
    }
    @JavascriptInterface
    public void writezhaiyao(String contentid){
        //获取Js界面传过来的contentID
        Intent intent=new Intent(mContxt,Digest_comment_activity.class);
        intent.putExtra("tag","write");
        intent.putExtra("id",contentid);
        mContxt.startActivity(intent);
    }
    @JavascriptInterface
    public void draft(String contentID){
        //获取Js界面传过来的contentID
        resquestHttp(contentID);
    }
    public static String priview=null;
    @JavascriptInterface
    public void savePreview(String string, WebView mWebview_id){
         priview=string;
         mWebview_id.loadUrl("javascript:getAndroidPreview()");
    }
    @JavascriptInterface
    public String getPreview(){
          return priview;
    }

    @JavascriptInterface
    public void seekActivity(String tag){
        Intent intent=new Intent(mContxt,seek_activity.class);
        intent.putExtra("tags",tag);
        mContxt.startActivity(intent);
    }
    @JavascriptInterface
    public void ToPartsActivity(){
        Intent intent=new Intent(mContxt,PartsActivity.class);
        mContxt.startActivity(intent);
    }
    //回显信息发布
    @JavascriptInterface
    public void get_push_info_id(String informationId,String contentCategorieCode){
        Log.i("TAG", "get_push_info_id: "+contentCategorieCode);
        switch (contentCategorieCode){
            case "project":
                Intent intent2=new Intent(mContxt,push_info_project_frist_activity.class);
                intent2.putExtra("informationId",informationId);
                mContxt.startActivity(intent2);
                break;
            case "other":
                Intent intent=new Intent(mContxt,push_info_other_frist_activity.class);
                intent.putExtra("informationId",informationId);
                mContxt.startActivity(intent);
                break;
            case "people":
                Intent intents=new Intent(mContxt,push_personal_info_frist_activity.class);
                mContxt.startActivity(intents);
                break;
            case "company":
                Intent intent5=new Intent(mContxt,push_info_company_frist_activity.class);
                mContxt.startActivity(intent5);
                break;
        }
    }
    private String content_URL= Globle.TEST_URL+"/api/details/contentDetailsById";
    private void resquestHttp(String contentID){
        String contentId=contentID;
        final RequestParams params=new RequestParams(content_URL);
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("content_id",contentId);
            String member_id=getMemberid("");
            jsonObject.put("member_id",member_id);
            String jsonString = Base64.encodeToString(jsonObject.toString().getBytes(),Base64.DEFAULT);//base64加密
            params.setBodyContent(jsonString);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    x.http().post(params, new Callback.CacheCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.i("TAG", "onSuccess: "+result);
                            try {
                                JSONObject js=new JSONObject(result);
                                if(js.getString("code").equals("200")){
                                    //成功获取了草稿的所有的数据，然后将数据丢过去，然后开始处理
                                    My_draft my_draft = com.alibaba.fastjson.JSONObject.parseObject(result,My_draft.class);
                                    //根据草稿中的内容分类Id去跳转界面
                                    int ContentCategoriesId=my_draft.getBody().getData().getContentCategoriesId();
                                    switch (ContentCategoriesId){
                                        case 1://当前草稿为故障维修
                                            Intent intent1=new Intent(mContxt,fault_frist_activity.class);
                                            intent1.putExtra("my_draft",result);
                                            mContxt.startActivity(intent1);
                                            break;
                                        case 2://当前草稿为工作原理
                                            Intent intent2=new Intent(mContxt,work_or_error_frist_kactivity.class);
                                            intent2.putExtra("my_draft",result);
                                            mContxt.startActivity(intent2);
                                            break;
                                        case 3://当前草稿为错误编码
                                            Intent intent3=new Intent(mContxt,work_or_error_frist_kactivity.class);//工作原理和错误编码共用
                                            intent3.putExtra("cuowu","错误编码");
                                            intent3.putExtra("my_draft",result);
                                            mContxt.startActivity(intent3);
                                            break;
                                        case 4://当前草稿为维修秘籍
                                            Intent intent4=new Intent(mContxt,push_knowledge_repair_frist_activity.class);//工作原理和错误编码共用
                                            intent4.putExtra("my_draft",result);
                                            mContxt.startActivity(intent4);
                                            break;
                                        case 6:
                                            Intent intent5=new Intent(mContxt,explanation_of_nouns_activity.class);//工作原理和错误编码共用
                                            intent5.putExtra("my_draft",my_draft);
                                            mContxt.startActivity(intent5);
                                            break;
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            Toast.makeText(mContxt, "网络或服务器异常!", Toast.LENGTH_SHORT).show();
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
                }
            }).start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @JavascriptInterface
    public void draftQuestion(String id){
        try {
            RequestParams params=new RequestParams(Globle.TEST_URL+"/api/qanda/questionDetail");
            JSONObject js=new JSONObject();
            js.put("question_id",id);
            js.put("member_id",getMemberid(""));
            String jsonString = Base64.encodeToString(js.toString().getBytes(),Base64.DEFAULT);//base64加密
            params.setBodyContent(jsonString);
            new query_AsyncTask(params,new JavaScriptObject(mContxt),"question").execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void getinfo(String str) {
        //其他和再次编辑接口没写
        Intent intent5 = null;
        question_bean questionBean= com.alibaba.fastjson.JSONObject.parseObject(str,question_bean.class);//得到这个值
        if (questionBean!=null&&questionBean.getBody().getData()!=null){
            if(questionBean.getBody().getData().getContentCategoriesId()==5){
                intent5=new Intent(mContxt,ask_other_activity.class);//工作原理和错误编码共用
            }else {
                intent5=new Intent(mContxt,ask_frist_activity.class);//工作原理和错误编码共用
            }
            intent5.putExtra("question",questionBean);
            mContxt.startActivity(intent5);
        }
    }
    //跳转到搜索页
    @JavascriptInterface
    public void refreshto(){
        Intent intent=new Intent(mContxt,seek_activity.class);
        mContxt.startActivity(intent);
    }
    //评论
    @JavascriptInterface
    public void commenet(String json) {
        if(new Common_utils(mContxt).isLogin()){
            Intent intent=new Intent(mContxt,Digest_comment_activity.class);
            intent.putExtra("tag","comment");
            intent.putExtra("comment",json);
            mContxt.startActivity(intent);
        }else{
            Intent intents=new Intent(mContxt,Login_activity.class);
            mContxt.startActivity(intents);
            Toast.makeText(mContxt, "登录以后才可以评论哦！", Toast.LENGTH_SHORT).show();
        }
    }
    //回显回答
    @JavascriptInterface
    public void answer(String content_category_id,String question_id){
        if(new Common_utils(mContxt).isLogin()){
            ArrayList<String> deleteList=new ArrayList<>();
            Pic_bean.answer_frist_list=null;
            Pic_bean.answer_second_list=null;
            Pic_bean.answer_finallist=null;
            switch (content_category_id){
                case "1"://回答故障维修方案
                    Intent fault_intent=new Intent(mContxt,answer_frist_activity.class);
                    Bundle fault_bundle=new Bundle();
                    fault_bundle.putString("question_id",question_id);
                    fault_bundle.putStringArrayList("deleteList",deleteList);
                    fault_bundle.putString("content_category_id",content_category_id);
                    fault_intent.putExtras(fault_bundle);
                    mContxt.startActivity(fault_intent);
                    break;
                case "2"://工作原理
                    Intent work_intent=new Intent(mContxt,answer_final_activity.class);
                    Bundle work_bundle=new Bundle();
                    work_bundle.putString("question_id",question_id);
                    work_bundle.putStringArrayList("deleteList",deleteList);
                    work_bundle.putString("content_category_id",content_category_id);
                    work_intent.putExtras(work_bundle);
                    mContxt.startActivity(work_intent);
                    break;
                case "3"://错误编码
                    Intent error_intent=new Intent(mContxt,answer_frist_activity.class);
                    Bundle error_bundle=new Bundle();
                    error_bundle.putString("question_id",question_id);
                    error_bundle.putStringArrayList("deleteList",deleteList);
                    error_bundle.putString("content_category_id",content_category_id);
                    error_intent.putExtras(error_bundle);
                    mContxt.startActivity(error_intent);
                    break;
                case "5"://回答其他
                    Intent intent=new Intent(mContxt,answer_final_activity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("question_id",question_id);
                    bundle.putStringArrayList("deleteList",deleteList);
                    bundle.putString("content_category_id",content_category_id);
                    intent.putExtras(bundle);
                    mContxt.startActivity(intent);
                    break;
            }
        }else{
            Intent intent=new Intent(mContxt,Login_activity.class);
            mContxt.startActivity(intent);
        }
    }
}
