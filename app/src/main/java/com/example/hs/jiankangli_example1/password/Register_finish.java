package com.example.hs.jiankangli_example1.password;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.personal_Info.Edit_Personal_Info_activity;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;

import Inter.get_net_Info;
import integral.terms_activity;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import bean.personal;
import Inter.Globle;
import utils.RequestNet;
import utils.Statubars;
import utils.XUtilsDB;

/**
 * Created by 李浩 on 2016/9/22.
 */
public class Register_finish extends AutoLayoutActivity implements get_net_Info {
    private EditText f_pwd_id;
    private EditText s_pwd_id;
    private final static String registerpwd_URL= Globle.TEST_URL+"/api/member/register";
    private final static String forgetpwd_URL=Globle.TEST_URL+"/api/member/setpwd";//忘记密码从新设置和修改密码的接口
    private String mobile;
    private String biaoti;
    private TextView tv_title_id;
    private Button btn_pwd_id;
    private SharedPreferences.Editor edit;
    private ImageView btn;
    private boolean istongyi;
    private TextView tv_sm_id;
    private EditText et_ivtation_code_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.register_finish);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        biaoti = getIntent().getStringExtra("biaoti");
        mobile = getIntent().getStringExtra("mobile");//获取手机号码
        SharedPreferences sp=getSharedPreferences("config",0);
        edit = sp.edit();
        initView();
        tv_title_id.setText(biaoti);
        if(biaoti.equals("忘记密码")){
            btn_pwd_id.setText("完成");
        }
    }

    private void initView() {
        AutoLinearLayout sets_back_id= (AutoLinearLayout) findViewById(R.id.sets_back_id);
        sets_back_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //发现邀请码控件
        et_ivtation_code_id = (EditText) findViewById(R.id.et_ivtation_code_id);
        f_pwd_id = (EditText) findViewById(R.id.f_pwd_id);
        s_pwd_id = (EditText) findViewById(R.id.s_pwd_id);
        tv_title_id = (TextView) findViewById(R.id.tv_title_id);
        btn_pwd_id = (Button) findViewById(R.id.btn_pwd_id);
        tv_sm_id = (TextView) findViewById(R.id.tv_sm_id);
        tv_sm_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到免责声明页
                Intent intent=new Intent(Register_finish.this, terms_activity.class);
                startActivity(intent);
            }
        });
        btn = (ImageView) findViewById(R.id.btn);
        //默认为true
        istongyi =false;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!istongyi){//代表可以被选中
                    btn.setImageDrawable(getResources().getDrawable(R.mipmap.duihao3x));
                    istongyi=true;//从新变为true，表示已经被选中了
                }else{
                    btn.setImageDrawable(getResources().getDrawable(R.mipmap.fangkuai3x));
                    istongyi =false;//从新变为false，表示已经没有被选中
                }
            }
        });
    }
    private personal p;
    private  DbManager db;
    
    public void register(View view){
        if(TextUtils.isEmpty(f_pwd_id.getText().toString())||TextUtils.isEmpty(s_pwd_id.getText().toString())){
            Toast.makeText(Register_finish.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!f_pwd_id.getText().toString().equals(s_pwd_id.getText().toString())){
            Toast.makeText(Register_finish.this,"两次输入密码不同!",Toast.LENGTH_LONG).show();
            return;
        }
        if (!istongyi){
            Toast.makeText(Register_finish.this, "请阅读声明以后，勾选免责声明完成注册!", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (biaoti){
            case "忘记密码":
                try {
                    JSONObject js=new JSONObject();
                    js.put("type","1");
                    js.put("old_pwd","");
                    js.put("new_pwd",f_pwd_id.getText().toString());
                    js.put("mobile",mobile);
                    RequestNet.queryServer(js,forgetpwd_URL,Register_finish.this,"Register_finish");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("password",s_pwd_id.getText().toString());
                    jsonObject.put("mobile",mobile);
                    jsonObject.put("rec_member_fcode",et_ivtation_code_id.getText().toString());
                    RequestNet.queryServer(jsonObject,registerpwd_URL,Register_finish.this,"Register_finish");
                } catch (JSONException e){
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void getinfo(String result) {
        try {
            JSONObject json=new JSONObject(result);
            if(json.getString("code").equals("200")){
                if(tv_title_id.getText().toString().equals("注册")){
                    Toast.makeText(Register_finish.this,"注册成功",Toast.LENGTH_SHORT).show();
                    p = com.alibaba.fastjson.JSONObject.parseObject(result, personal.class);//解析数据
                    personal.BodyBean.DataBean data= p.getBody().getData();
                    db=x.getDb(XUtilsDB.getDBconfig());//获取数据库
                    SharedPreferences sp=getSharedPreferences("config",0);
                    SharedPreferences.Editor edit=sp.edit();
                    try {
                        db.save(data);//存储实体到数据库，然后主界面返回到设置界面，finish当前界面
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                    edit.putBoolean("isLogin",true);
                    edit.commit();
                    Intent intent=new Intent(Register_finish.this,Edit_Personal_Info_activity.class);
                    intent.putExtra("name",mobile);
                    intent.putExtra("nickname",mobile);
                    intent.putExtra("sex","男");
                    intent.putExtra("birth","1994-01-01");
                    intent.putStringArrayListExtra("jineng",null);
                    intent.putStringArrayListExtra("jnid",null);
                    startActivity(intent);
                    answer_Application.getInstance().kill();
                }else{
                    //确认退出
                    edit.clear();
                    edit.commit();//清除用户登录信息
                    //删除保存在数据库中的信息
                    DbManager db=x.getDb(XUtilsDB.getDBconfig());
                    try {
                        db.dropTable(personal.BodyBean.DataBean.class);//将存储的表格也删除
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Register_finish.this,"密码修改成功，请重新登录",Toast.LENGTH_LONG).show();
                    //跳转到登录界面
                    Intent intent=new Intent(Register_finish.this, Login_activity.class);
                    startActivity(intent);
                    answer_Application.getInstance().kill();
                }
            }else if(json.getString("code").equals("20100")){
                Toast.makeText(Register_finish.this,"手机号已注册",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Register_finish.this,json.getString("message"),Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
