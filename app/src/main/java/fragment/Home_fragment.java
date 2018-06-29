package fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.hs.jiankangli_example1.password.Login_activity;
import com.example.hs.jiankangli_example1.R;
import com.zhy.autolayout.AutoLinearLayout;
import org.json.JSONException;
import org.xutils.http.RequestParams;

import java.util.List;

import AsyncTask.query_AsyncTask;
import Inter.get_net_Info;
import bean.Pic_bean;
import bean.title_bean;
import Inter.Globle;
import utils.Common_utils;
import utils.JavaScriptObject;
import utils.askOrpush;

import static android.content.ContentValues.TAG;
import static com.example.hs.jiankangli_example1.R.layout.home_fragment;

/**
 * Created by lihao on 2016/9/6.
 */
public class Home_fragment extends Fragment implements get_net_Info {

    private View view;
    private WebView wv_id;
    private WebSettings ws;
    private TextView tv_ask_id;
    private TextView tv_title_id;
    private AutoLinearLayout al_seek_id;
    private AutoLinearLayout sets_back_id;
    private WindowManager m;
    private TextView tv_push_id;
    private TextView tv_push_info_id;
    private RadioGroup navigation_btn;
    private View tv_share_id;
    private Dialog mDialog;
    private View toolbar_id;
    private ImageView iv_bell_id;

    @SuppressLint({ "JavascriptInterface","SetJavaScriptEnabled" })
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void setOnClickListener() {
        sets_back_id.setOnClickListener(new MyOnClickListener());
        tv_ask_id.setOnClickListener(new MyOnClickListener());
        tv_push_id.setOnClickListener(new MyOnClickListener());
        tv_push_info_id.setOnClickListener(new MyOnClickListener());
        tv_share_id.setOnClickListener(new MyOnClickListener());
        iv_bell_id.setOnClickListener(new MyOnClickListener());
    }
    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.sets_back_id:
                    wv_id.goBack();
                    break;
                case R.id.tv_ask_id:
                    Pic_bean.list=null;
                    if(new Common_utils(getActivity()).isLogin()){
                        new askOrpush(getActivity(),m,"ask").setDialog().show();
                    }else{
                        Intent intent=new Intent(getActivity(),Login_activity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.tv_push_id:
                    if(new Common_utils(getActivity()).isLogin()){
                        new askOrpush(getActivity(),m,"push").setDialog().show();
                    }else{
                        Intent intent=new Intent(getActivity(), Login_activity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.tv_push_info_id:
                    if (new Common_utils(getActivity()).isLogin()){
                        new askOrpush(getActivity(),m,"push_info").setDialog().show();
                    }else{
                        Intent intent=new Intent(getActivity(),Login_activity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.tv_share_id:
                    if(new Common_utils(getActivity()).isLogin()){
                        wv_id.loadUrl("javascript:AndroidShare()");
                        new JavaScriptObject(getActivity()).Share_board_show();
                    }else{
                        Toast.makeText(getActivity(), "登录以后才能执行分享操作！",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getActivity(),Login_activity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.iv_bell_id:
                    if(new Common_utils(getActivity()).isLogin()){
                        wv_id.loadUrl(Globle.TEST_URL+"/app/noticeList/index.html");
                    }else{
                        Toast.makeText(getActivity(),"登录以后才能查看系统消息哦！", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getActivity(),Login_activity.class);
                        startActivity(intent);
                    }
                    break;
            }
        }
    }
    private void initView() {
        tv_ask_id = (TextView) getActivity().findViewById(R.id.tv_ask_id);//提问
        tv_title_id = (TextView) getActivity().findViewById(R.id.tv_title_id);
        al_seek_id = (AutoLinearLayout) getActivity().findViewById(R.id.al_seek_id);
        sets_back_id = (AutoLinearLayout) getActivity().findViewById(R.id.sets_back_id);
        tv_push_id = (TextView) getActivity().findViewById(R.id.tv_push_id);
        tv_push_info_id = (TextView) getActivity().findViewById(R.id.tv_push_info_id);
        navigation_btn = (RadioGroup) getActivity().findViewById(R.id.navigation_btn);
        tv_share_id = getActivity().findViewById(R.id.tv_share_id);
        toolbar_id = getActivity().findViewById(R.id.toolbar_id);
        iv_bell_id = (ImageView) getActivity().findViewById(R.id.iv_bell_id);
        wv_id = (WebView) view.findViewById(R.id.wv_id);//发现webview
        ws = wv_id.getSettings();
        mDialog = new Dialog(getActivity(), R.style.myDialogTheme2);
        mDialog.setContentView(R.layout.getting);
        mDialog.setCanceledOnTouchOutside(false);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(home_fragment,container,false);
        }
        ViewGroup viewGroup= (ViewGroup) view.getParent();
        if(viewGroup!=null){
            viewGroup.removeView(view);
        }
        initView();
        setOnClickListener();
        m =getActivity().getWindowManager();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //设置WebView属性，能够执行Javascript脚本
        ws.setJavaScriptEnabled(true);
        wv_id.requestFocusFromTouch();
        ws.setDefaultTextEncodingName("utf-8");
        wv_id.requestFocusFromTouch();
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        wv_id.setWebChromeClient(new WebChromeClient());
        ws.setAppCacheEnabled(false);// 设置启动缓存
        if(Build.VERSION.SDK_INT >= 19) {
            ws.setLoadsImagesAutomatically(true);
        } else {
            ws.setLoadsImagesAutomatically(false);
        }
        ws.setBuiltInZoomControls(false);
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置本地调用对象及其接口
        wv_id.addJavascriptInterface(new JavaScriptObject(getActivity()),"androidInfo");
        //加载需要显示的网页
        wv_id.loadUrl(Globle.TEST_URL+"/app/");
        //设置Web视图
        wv_id.setWebViewClient(new MyWebViewClient ());
        wv_id.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && wv_id.canGoBack()&&event.getAction()==KeyEvent.ACTION_UP) {
                    wv_id.goBack();
                    return true;
                }
                return false;
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onResume() {
        wv_id.reload();//刷新界面
        super.onResume();
    }
    class MyWebViewClient extends  WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            int a=toolbar_id.getVisibility();
            if(a!=0){
                return;
            }
            mDialog.show();
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public void onPageFinished(WebView view, String url){
            Log.i(TAG, "onPageFinished: "+url);
            mDialog.dismiss();
            tv_title_id.setVisibility(View.VISIBLE);
            if(url!=null&&!url.isEmpty()){
                Pic_bean.URL=url;
            }
            String jsTitle = JavaScriptObject.JsTitle;//json格式的数据
            Log.i(TAG, "jsTitle: "+jsTitle);
            if(jsTitle!=null&&!jsTitle.isEmpty()){
                title_bean  title_bean = JSONObject.parseObject(jsTitle, title_bean.class);
                List<bean.title_bean.DataBean> Data=title_bean.getData();
                if(url.contains("?")){
                    for(int i=0;i<Data.size();i++){
                        if((Globle.TEST_URL+Data.get(i).getUrl()).equals(url.substring(0,url.lastIndexOf("?")))){
                            if(url.contains("details")||url.contains("newInfo")){
                                tv_share_id.setVisibility(View.VISIBLE);
                            }else{
                                tv_share_id.setVisibility(View.GONE);
                            }
                            tv_ask_id.setVisibility(View.GONE);
                            iv_bell_id.setVisibility(View.GONE);
                            al_seek_id.setVisibility(View.GONE);
                            sets_back_id.setVisibility(View.VISIBLE);
                            tv_title_id.setText(Data.get(i).getTitle());
                            tv_push_id.setVisibility(View.GONE);
                            tv_push_info_id.setVisibility(View.GONE);
                            navigation_btn.setVisibility(View.GONE);
                        }
                    }
                }else{
                    for(int i=0;i<Data.size();i++){
                        if(url.equals(Globle.TEST_URL+"/app/technology.html")&&url.equals(Globle.TEST_URL+(Data.get(i).getUrl()))){
                            tv_ask_id.setVisibility(View.VISIBLE);
                            al_seek_id.setVisibility(View.GONE);
                            iv_bell_id.setVisibility(View.GONE);
                            sets_back_id.setVisibility(View.VISIBLE);
                            tv_title_id.setText(Data.get(i).getTitle());
                            tv_push_id.setVisibility(View.GONE);
                            tv_push_info_id.setVisibility(View.GONE);
                            navigation_btn.setVisibility(View.GONE);
                            tv_share_id.setVisibility(View.GONE);
                        }else if(url.equals(Globle.TEST_URL+"/app/")&&url.equals(Globle.TEST_URL+(Data.get(i).getUrl()))){//首页
                            iv_bell_id.setVisibility(View.VISIBLE);
                            tv_ask_id.setVisibility(View.GONE);
                            al_seek_id.setVisibility(View.VISIBLE);
                            sets_back_id.setVisibility(View.GONE);
                            tv_title_id.setText(Data.get(i).getTitle());
                            tv_push_id.setVisibility(View.GONE);
                            tv_push_info_id.setVisibility(View.GONE);
                            navigation_btn.setVisibility(View.VISIBLE);
                            tv_share_id.setVisibility(View.GONE);
                            //当前HTML页面为首页的时候，请求网络，是否有未读消息
                            RequestParams params=new RequestParams(Globle.TEST_URL+"/api/notice /noticeCount ");
                            org.json.JSONObject jsonObject=new org.json.JSONObject();
                            try {
                                jsonObject.put("memberId",new JavaScriptObject(getActivity()).getMemberid(""));
                                String jsonString = Base64.encodeToString(jsonObject.toString().getBytes(),Base64.DEFAULT);//base64加密
                                params.setBodyContent(jsonString);
                                      new query_AsyncTask(params,Home_fragment.this,"notices").execute();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else if(url.equals(Globle.TEST_URL+"/app/knowledge.html")&&url.equals(Globle.TEST_URL+(Data.get(i).getUrl()))){
                            al_seek_id.setVisibility(View.GONE);
                            tv_ask_id.setVisibility(View.GONE);
                            iv_bell_id.setVisibility(View.GONE);
                            sets_back_id.setVisibility(View.VISIBLE);
                            tv_title_id.setText(Data.get(i).getTitle());
                            tv_push_id.setVisibility(View.VISIBLE);
                            tv_push_info_id.setVisibility(View.GONE);
                            navigation_btn.setVisibility(View.GONE);
                            tv_share_id.setVisibility(View.GONE);
                        }else if(url.equals(Globle.TEST_URL+"/app/info/info-list.html")&&url.equals(Globle.TEST_URL+(Data.get(i).getUrl()))){
                            tv_title_id.setText(Data.get(i).getTitle());
                            sets_back_id.setVisibility(View.VISIBLE);
                            al_seek_id.setVisibility(View.GONE);
                            iv_bell_id.setVisibility(View.GONE);
                            tv_ask_id.setVisibility(View.GONE);
                            tv_push_info_id.setVisibility(View.VISIBLE);
                            tv_push_id.setVisibility(View.GONE);
                            navigation_btn.setVisibility(View.GONE);
                            tv_share_id.setVisibility(View.GONE);
                        }else if(url.equals(Globle.TEST_URL+"/app/searchResult.html")&&url.equals(Globle.TEST_URL+(Data.get(i).getUrl()))){
                            tv_title_id.setText(Data.get(i).getTitle());
                            sets_back_id.setVisibility(View.VISIBLE);
                            iv_bell_id.setVisibility(View.GONE);
                            al_seek_id.setVisibility(View.GONE);
                            tv_ask_id.setVisibility(View.GONE);
                            tv_push_info_id.setVisibility(View.GONE);
                            tv_push_id.setVisibility(View.GONE);
                            navigation_btn.setVisibility(View.GONE);
                            tv_share_id.setVisibility(View.GONE);
                        }else if(url.equals(Globle.TEST_URL+"/app/noticeList/index.html")){
                            tv_title_id.setText("系统消息");
                            sets_back_id.setVisibility(View.VISIBLE);
                            iv_bell_id.setVisibility(View.GONE);
                            al_seek_id.setVisibility(View.GONE);
                            tv_ask_id.setVisibility(View.GONE);
                            tv_push_info_id.setVisibility(View.GONE);
                            tv_push_id.setVisibility(View.GONE);
                            navigation_btn.setVisibility(View.GONE);
                            tv_share_id.setVisibility(View.GONE);
                        }
                    }
                }
            }
            super.onPageFinished(view, url);
        }
    }
    @Override
    public void getinfo(String str) {
        try {
            org.json.JSONObject js=new org.json.JSONObject(str);
            String code=js.getString("code");
            switch (code){
                case "200":
                    String count=js.getJSONObject("body").getJSONObject("data").getString("count");
                    if(Integer.parseInt(count)>0){
                        iv_bell_id.setImageDrawable(getResources().getDrawable(R.mipmap.show_bell));
                    }else{
                        iv_bell_id.setImageDrawable(getResources().getDrawable(R.mipmap.hide_bell));
                    }
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
