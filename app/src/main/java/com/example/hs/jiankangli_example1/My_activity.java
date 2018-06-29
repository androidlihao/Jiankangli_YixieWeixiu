package com.example.hs.jiankangli_example1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.password.Login_activity;
import com.umeng.socialize.UMShareAPI;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import Inter.Globle;
import bean.Pic_bean;
import bean.title_bean;
import utils.Common_utils;
import utils.JavaScriptObject;
import utils.Statubars;
import utils.askOrpush;
import utils.filter_popwindow;

/**
 * Created by 李浩 on 2016/10/17.
 */
public class My_activity extends AutoLayoutActivity {
    private WebView mWebview_id;
    private AutoLinearLayout sets_back_id;
    private String URl;
    private TextView tv_title_id;
    private String Load_URL;
    private TextView tv_filter_id;
    private filter_popwindow popWinMenu;
    private String province_id;
    private String operationId;
    private String manufacturer_id;
    private String product_categories_id;
    private String content_categories_id;
    private String key_word;
    private Dialog mDialog;
    private View tv_share_id;
    private View tv_tiwen_id;
    private View tv_fabuzhishidian_id;
    private View tv_push_info_id;
    private String repairManualId;

    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.mycaogao_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        Pic_bean.operationId="0";
        URl= getIntent().getStringExtra("my");
        popWinMenu = new filter_popwindow(My_activity.this,new MyOnClickListener());
        popWinMenu.setFocusable(true);
        popWinMenu.setBackgroundDrawable(new BitmapDrawable());
        initView();
    }
    @Override
    protected void onResume() {
        mWebview_id.reload();
        if (Pic_bean.up_ing){
            mWebview_id.goBack();
            Pic_bean.up_ing=false;//goback以后从新变为false
        }
        super.onResume();
    }
    private WebSettings ws;
    private WindowManager m;
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }
    private void initView() {
        m =getWindowManager();
        mWebview_id = (WebView) findViewById(R.id.mWebview_id);
        ws = mWebview_id.getSettings();
        mDialog = new Dialog(My_activity.this, R.style.myDialogTheme2);
        mDialog.setContentView(R.layout.getting);
        mDialog.setCanceledOnTouchOutside(false);
        tv_title_id = (TextView) findViewById(R.id.tv_title_id);
        tv_share_id = findViewById(R.id.tv_share_id);//分享
        tv_tiwen_id = findViewById(R.id.tv_tiwen_id);//提问
        tv_fabuzhishidian_id = findViewById(R.id.tv_fabuzhishidian_id);//发布知识点
        tv_push_info_id = findViewById(R.id.tv_push_info_id);//我要发布
        tv_tiwen_id.setOnClickListener(new MyOnClickListener());
        tv_fabuzhishidian_id.setOnClickListener(new MyOnClickListener());
        tv_push_info_id.setOnClickListener(new MyOnClickListener());
        tv_share_id.setOnClickListener(new MyOnClickListener());
        tv_filter_id = (TextView) findViewById(R.id.tv_filter_id);
        tv_filter_id.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    popWinMenu.showAsDropDown(tv_filter_id,-15,0);
                }
                return false;
            }
        });
        sets_back_id = (AutoLinearLayout) findViewById(R.id.sets_back_id);
        sets_back_id.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mWebview_id.canGoBack()){
                    mWebview_id.goBack();
                }else{
                    if(popWinMenu.isShowing()){
                       popWinMenu.dismiss();
                    }
                    if (mDialog.isShowing()){
                        mDialog.dismiss();
                    }
                    finish();
                }
            }
        });
        ws.setJavaScriptEnabled(true);
        mWebview_id.requestFocusFromTouch();
        ws.setDefaultTextEncodingName("utf-8");
        ws.setAppCacheEnabled(false);// 设置启动缓存
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        if(Build.VERSION.SDK_INT >= 19) {
            ws.setLoadsImagesAutomatically(true);
        } else {
            ws.setLoadsImagesAutomatically(false);
        }
        ws.setBuiltInZoomControls(false);
        //设置本地调用对象及其接口
        mWebview_id.addJavascriptInterface(new JavaScriptObject(My_activity.this),"androidInfo");
        mWebview_id.setWebChromeClient(new WebChromeClient());
        switch (URl){
            case "fixHelper":
                Load_URL=Globle.TEST_URL+"/app/engineer/PDFTest/pdfjs/web/viewer.html";
                repairManualId = getIntent().getStringExtra("repairManualId");
                Log.i("TAGs", "onPageFfsdfinished: "+repairManualId);
                tv_title_id.setText("工程师助手");
                break;
            case "about_me":
                Load_URL=Globle.TEST_URL+"/app/aboutMe/index.html";
                tv_title_id.setText("关于我们");
                break;
            case "caogao":
                Load_URL=Globle.TEST_URL+"/app/my/my-caogao.html";
                tv_title_id.setText("我的草稿");
                break;
            case "mypublish":
                Load_URL=Globle.TEST_URL+"/app/infoMy/index.html";
                tv_title_id.setText("我的发布");
                break;
            case "collect":
                Load_URL=Globle.TEST_URL+"/app/my/my-collect.html";
                Pic_bean.collects=new String();
                tv_title_id.setText("我的收藏");
                break;
            case "notofication_message"://推送消息
                Load_URL=getIntent().getStringExtra("notificationMessage");
                break;
            case "preview":
                Load_URL=Globle.TEST_URL+"/app/"+URl+".html";
                tv_title_id.setText("发布预览");
                break;
            case "integral_rule":
                Load_URL=Globle.TEST_URL+"/app/score/index.html";
                tv_title_id.setText("积分规则");
                break;
            case "seek_result":
                String seekjson=getIntent().getStringExtra("seekJson");
                try {
                    JSONObject js=new JSONObject(seekjson);
                    if(seekjson.contains("province_id")){
                        province_id = js.getString("province_id");
                    }else{
                        province_id="";
                    }
                    if(seekjson.contains("operationId")){
                        operationId = js.getString("operationId");
                    }else{
                        operationId="";
                    }
                    if(seekjson.contains("manufacturer_id")){
                        manufacturer_id = js.getString("manufacturer_id");
                    }else{
                        manufacturer_id="";
                    }
                    if(seekjson.contains("product_categories_id")){
                        product_categories_id = js.getString("product_categories_id");
                    }else{
                        product_categories_id="";
                    }
                    if(seekjson.contains("content_categories_id")){
                        content_categories_id = js.getString("content_categories_id");
                    }else{
                        product_categories_id="";
                    }
                    if(seekjson.contains("key_word")){
                        key_word = js.getString("key_word");
                    }else{
                        key_word="";
                    }
                    Load_URL=Globle.TEST_URL+"/app/"+"searchResult.html?operationId="+operationId+"&manufacturer_id="
                            +manufacturer_id+"&product_categories_id="+product_categories_id+
                            "&content_categories_id="+content_categories_id+
                            "&key_word="+key_word+"&infoType="+content_categories_id+"&province_id="+province_id;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tv_title_id.setText("搜索结果");
                break;
        }
        //加载需要显示的网页
        mWebview_id.loadUrl(Load_URL);
        //设置Web视图
        mWebview_id.setWebViewClient(new MyWebViewClient());
        //设置webview的网页监听
        mWebview_id.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebview_id.canGoBack()&&event.getAction()==KeyEvent.ACTION_UP) {//如果webview可以返回,而且点击的是返回键
                    mWebview_id.goBack();
                    return true;
                }else if((keyCode == KeyEvent.KEYCODE_BACK)&&!mWebview_id.canGoBack()){
                    if(popWinMenu.isShowing()){
                        popWinMenu.dismiss();
                        finish();
                    }
                }
                return false;
            }
        });
        if(URl.equals("preview")){
            String msg=getIntent().getStringExtra("yulan");
            new JavaScriptObject(My_activity.this).savePreview(msg,mWebview_id);
        }
    }
    class MyWebViewClient extends  WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mDialog.show();
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            if (url.equals(Globle.TEST_URL+"/app/engineer/PDFTest/pdfjs/web/viewer.html")){
                mWebview_id.loadUrl("javascript:dataAndroidRepairManualId('"+repairManualId+"')");
            }
            if(url.equals(Globle.TEST_URL+"/app/infoMy/index.html")){//如果是我的发布
                mWebview_id.loadUrl("javascript:operationIdFromJava('" + Pic_bean.operationId + "')");
            }
            mDialog.dismiss();
            String jsTitle = JavaScriptObject.JsTitle;//json格式的数据
            if(jsTitle!=null&&!jsTitle.isEmpty()){
                title_bean title_bean = com.alibaba.fastjson.JSONObject.parseObject(jsTitle, title_bean.class);
                List<bean.title_bean.DataBean> Data=title_bean.getData();
                if(url.contains("?")){
                    for(int i=0;i<Data.size();i++){
                        if((Globle.TEST_URL+Data.get(i).getUrl()).equals(url.substring(0,url.lastIndexOf("?")))){
                            if(url.contains("details")||url.contains("newInfo")){
                                tv_share_id.setVisibility(View.VISIBLE);
                            }else{
                                tv_share_id.setVisibility(View.GONE);
                            }
                            tv_tiwen_id.setVisibility(View.GONE);
                            sets_back_id.setVisibility(View.VISIBLE);
                            tv_title_id.setText(Data.get(i).getTitle());
                            tv_fabuzhishidian_id.setVisibility(View.GONE);
                            tv_push_info_id.setVisibility(View.GONE);
                        }
                    }
                   }else{
                    for(int i=0;i<Data.size();i++){
                        if(url.equals(Globle.TEST_URL+"/app/technology.html")&&url.equals(Globle.TEST_URL+(Data.get(i).getUrl()))){
                            tv_tiwen_id.setVisibility(View.VISIBLE);
                            sets_back_id.setVisibility(View.VISIBLE);
                            tv_title_id.setText(Data.get(i).getTitle());
                            tv_fabuzhishidian_id.setVisibility(View.GONE);
                            tv_push_info_id.setVisibility(View.GONE);
                            tv_share_id.setVisibility(View.GONE);
                            break;
                        }else if(url.equals(Globle.TEST_URL+"/app/knowledge.html")&&url.equals(Globle.TEST_URL+(Data.get(i).getUrl()))){
                            tv_tiwen_id.setVisibility(View.GONE);
                            sets_back_id.setVisibility(View.VISIBLE);
                            tv_title_id.setText(Data.get(i).getTitle());
                            tv_fabuzhishidian_id.setVisibility(View.VISIBLE);
                            tv_push_info_id.setVisibility(View.GONE);
                            tv_share_id.setVisibility(View.GONE);
                            break;
                        }else if(url.equals(Globle.TEST_URL+"/app/info/info-list.html")&&url.equals(Globle.TEST_URL+(Data.get(i).getUrl()))){
                            tv_title_id.setText(Data.get(i).getTitle());
                            sets_back_id.setVisibility(View.VISIBLE);
                            tv_tiwen_id.setVisibility(View.GONE);
                            tv_push_info_id.setVisibility(View.VISIBLE);
                            tv_fabuzhishidian_id.setVisibility(View.GONE);
                            tv_share_id.setVisibility(View.GONE);
                            break;
                        }else if(url.equals(Globle.TEST_URL+"/app/searchResult.html")&&url.equals(Globle.TEST_URL+(Data.get(i).getUrl()))){
                            gonggong(Data.get(i).getTitle());
                            break;
                        }else if(url.equals(Globle.TEST_URL+"/app/my/my-caogao.html")){//我的草稿
                            gonggong("我的草稿");
                            break;
                        }else if(url.equals(Globle.TEST_URL+"/app/my/my-collect.html")){//我的收藏
                            gonggong("我的收藏");
                            mWebview_id.loadUrl("javascript:contentTypeFromJava('" + Pic_bean.collects + "')");
                            break;
                        }else if(url.equals(Globle.TEST_URL+"/app/infoMy/index.html")){//我的发布
                            gonggong("我的发布");
                            break;
                        }
                    }
                }
            }
            if(url.equals(Globle.TEST_URL+"/app/my/my-collect.html")){
                tv_filter_id.setVisibility(View.VISIBLE);
            }else{
                tv_filter_id.setVisibility(View.GONE);
            }
            super.onPageFinished(view, url);
        }

    }
    private void gonggong(String title){
        tv_title_id.setText(title);
        sets_back_id.setVisibility(View.VISIBLE);
        tv_tiwen_id.setVisibility(View.GONE);
        tv_push_info_id.setVisibility(View.GONE);
        tv_fabuzhishidian_id.setVisibility(View.GONE);
        tv_share_id.setVisibility(View.GONE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
//                case  R.id.tv_push_id:
//                    Pic_bean.collects="4";
//                    mWebview_id.loadUrl("javascript:contentTypeFromJava('" + 4 + "')");
//                    popWinMenu.dismiss();
//                    break;
                case  R.id.tv_news_id:
                    Pic_bean.collects="1";
                    popWinMenu.dismiss();
                    mWebview_id.loadUrl("javascript:contentTypeFromJava('" + 1 + "')");
                    break;
                case  R.id.tv_knowledg_id:
                    popWinMenu.dismiss();
                    Pic_bean.collects="2";
                    mWebview_id.loadUrl("javascript:contentTypeFromJava('" + 2 + "')");
                    break;
                case  R.id.tv_ask_id:
                    popWinMenu.dismiss();
                    Pic_bean.collects="3";
                    mWebview_id.loadUrl("javascript:contentTypeFromJava('" + 3 + "')");
                    break;
                case R.id.tv_all_kind_id:
                    popWinMenu.dismiss();
                    Pic_bean.collects="";
                    mWebview_id.loadUrl("javascript:contentTypeFromJava('" +""+ "')");
                    break;
                case R.id.tv_tiwen_id:
                    Pic_bean.list=null;
                    if(new Common_utils(getApplicationContext()).isLogin()){
                        new askOrpush(My_activity.this,m,"ask").setDialog().show();
                    }else{
                        Intent intent=new Intent(My_activity.this,Login_activity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.tv_fabuzhishidian_id:
                    if(new Common_utils(getApplicationContext()).isLogin()){
                        new askOrpush(My_activity.this,m,"push").setDialog().show();
                    }else{
                        Intent intent=new Intent(My_activity.this, Login_activity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.tv_push_info_id:
                    if (new Common_utils(getApplicationContext()).isLogin()){
                        new askOrpush(My_activity.this,m,"push_info").setDialog().show();
                    }else{
                        Intent intent=new Intent(My_activity.this,Login_activity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.tv_share_id:
                    if(new Common_utils(getApplicationContext()).isLogin()){
                        mWebview_id.loadUrl("javascript:AndroidShare()");
                        new JavaScriptObject(My_activity.this).Share_board_show();
                    }else{
                        Toast.makeText(My_activity.this, "登录以后才能执行分享操作！",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(My_activity.this,Login_activity.class);
                        startActivity(intent);
                    }
                    break;
            }
        }
    }
}
