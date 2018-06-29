package com.example.hs.jiankangli_example1.password;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;


import java.util.HashSet;
import java.util.Set;

import Inter.get_net_Info;
import bean.personal;
import Inter.Globle;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import utils.Common_utils;
import utils.RequestNet;
import utils.Statubars;
import utils.XUtilsDB;

import static Inter.Globle.jpsh_type;

/**
 * Created by 李浩 on 2016/9/19.
 */
public class Login_activity extends AutoLayoutActivity  implements get_net_Info {
    /**
     * 登录地址
     */
    private final static String LOGIN_URL = Globle.TEST_URL+"/api/member/login";
    private AutoLinearLayout sets_back_id;
    private EditText et_number_id;
    private EditText et_pwd_id;
    private String number;
    private String pwd;
    private Button btn_login;
    private TextView tv_register_id;
    private TextView tv_forgets_id;
    private static personal p;
    private Handler MyHandler=new Handler(){

        private  DbManager db;
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    JSONObject json= null;
                    try {
                        json = new JSONObject(msg.obj.toString());
                        String code=json.getString("code");
                        if(code.equals("200")){
                            Toast.makeText(Login_activity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            p = com.alibaba.fastjson.JSONObject.parseObject(msg.obj.toString(),personal.class);//解析数据
                            if(p.getBody()!=null){
                                if(p.getBody().getData()!=null){
                                    personal.BodyBean.DataBean data= p.getBody().getData();
                                    db=x.getDb(XUtilsDB.getDBconfig());//获取数据库
                                    SharedPreferences sp=getSharedPreferences("config",0);
                                    SharedPreferences.Editor edit=sp.edit();
                                    db.save(data);//存储实体到数据库，然后主界面返回到设置界面，finish当前界面
                                    edit.putBoolean("isLogin",true);
                                    edit.commit();
                                    Set<String> set;
                                    if (p.getBody().getData().getJpushTagsArray()!=null){
                                        set=p.getBody().getData().getJpushTagsArray();
                                    }else{
                                        set=new HashSet<>();
                                    }
                                    set.add(jpsh_type);
                                    JPushInterface.setAliasAndTags(Login_activity.this,p.getBody().getData().getUuid(),set,mAliasCallback);//设置别名
                                    answer_Application.getInstance().kill();
                                }
                            }
                        }else if(code.equals("30001")){
                            Toast.makeText(Login_activity.this, "账号或者密码错误!", Toast.LENGTH_SHORT).show();
                        }else if(code.equals("20002")){
                            Toast.makeText(Login_activity.this, "您已被注销，请联系管理员!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Login_activity.this, json.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    Set<String> set;
                    Log.i("TAG", "gotResult:1 "+p);
                    if (p.getBody()!=null&&p.getBody().getData()!=null&&p.getBody().getData().getJpushTagsArray()!=null){
                        set=p.getBody().getData().getJpushTagsArray();
                    }else{
                        set=new HashSet<>();
                    }
                    set.add(jpsh_type);
                    if (p.getBody()!=null&&p.getBody().getData()!=null&&p.getBody().getData().getUuid()!=null){
                        JPushInterface.setAliasAndTags(Login_activity.this,p.getBody().getData().getUuid(),set,mAliasCallback);//设置别名
                    }
                    break;
            }
        }
    };
    public final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    Log.i("TAG", "gotResult: "+"0");
                    logs = "Set tag and alias success";//设置别名成功
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    Log.i("TAG", "gotResult: "+"6002");
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    // 延迟 60 秒来调用 Handler 设置别名
                    MyHandler.sendMessageDelayed(MyHandler.obtainMessage(5,alias), 1000 * 60);//发送消息过去
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //设置状态栏的颜色
        new Statubars().setStatubars(this, window, "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.login_activity);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        //初始化界面控件实例
        initView();
        //设置监听
        setOnClickListener();
    }
    private void login() {
        //获取输入框中的手机号码和密码，然后执行登录的过程
        getEditText();
        //判断输入的是否为空，不为空接着判断
        if(TextUtils.isEmpty(number)){
            Toast.makeText(getApplicationContext(), "手机号码不能为空!", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(pwd)){
            Toast.makeText(getApplicationContext(), "密码不能为空!", Toast.LENGTH_SHORT).show();
        }else{
            //判读是不是正确的手机号码
            if (Common_utils.isMobileNO(number)) {
                loginHttp();//开始登录操作
            }else{
                Toast.makeText(Login_activity.this, "手机号码格式不正确，请输入正确的手机号!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void loginHttp() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobile",number);
            jsonObject.put("password",pwd);
            RequestNet.queryServer(jsonObject,LOGIN_URL,Login_activity.this,"Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
      }
    private void setOnClickListener() {
        sets_back_id.setOnClickListener(new MyOnClickListener());
        btn_login.setOnClickListener(new MyOnClickListener());
        tv_register_id.setOnClickListener(new MyOnClickListener());
        tv_forgets_id.setOnClickListener(new MyOnClickListener());
    }

    private void initView() {
        sets_back_id = (AutoLinearLayout) findViewById(R.id.sets_back_id);
        et_number_id = (EditText) findViewById(R.id.et_number_id);
        et_pwd_id = (EditText) findViewById(R.id.et_pwd_id);
        et_number_id.setInputType(InputType.TYPE_CLASS_PHONE);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_register_id = (TextView) findViewById(R.id.tv_register_id);
        tv_forgets_id = (TextView) findViewById(R.id.tv_forgets_id);
    }
    public void getEditText() {
        number = et_number_id.getText().toString();//获取手机号码
        pwd = et_pwd_id.getText().toString();//获取密码
    }
    @Override
    public void getinfo(String result) {
        Log.i("TAG", "getinfo: "+result);
        Message msg=new Message();
        msg.what=200;
        msg.obj=result;
        MyHandler.sendMessage(msg);
    }
    public class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.sets_back_id:
                    onBackPressed();
                    finish();
                break;
                case R.id.btn_login:
                    //执行登录的操作
                    login();
                    break;
                case R.id.tv_register_id:
                    //跳转页面
                    Intent intents=new Intent(Login_activity.this,Register_activity.class);
                    intents.putExtra("forget","注册");
                    intents.putExtra("type","0");
                    startActivity(intents);
                    break;
                case R.id.tv_forgets_id:
                    //跳转到忘记密码页
                    Intent intent=new Intent(Login_activity.this,Register_activity.class);
                    intent.putExtra("forget","忘记密码");
                    intent.putExtra("type","1");
                    startActivity(intent);
                    break;
            }
        }
    }
 }
