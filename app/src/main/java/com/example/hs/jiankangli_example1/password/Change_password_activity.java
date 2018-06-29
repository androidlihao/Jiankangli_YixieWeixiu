package com.example.hs.jiankangli_example1.password;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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

import Inter.get_net_Info;
import bean.personal;
import Inter.Globle;
import utils.Common_utils;
import utils.RequestNet;
import utils.Statubars;
import utils.XUtilsDB;

/**
 * Created by 李浩 on 2016/9/25.
 */
public class Change_password_activity extends AutoLayoutActivity implements get_net_Info {

    private EditText old_pwd_id;
    private EditText input_id;
    private EditText input_again_id;
    private TextView tv_submit_id;
    private String forgetpwd_URL= Globle.TEST_URL+"/api/member/setpwd";//忘记密码从新设置和修改密码的接口
    private SharedPreferences.Editor edit;
    private AutoLinearLayout set_back_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp=getSharedPreferences("config",0);
        edit = sp.edit();
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.change_pwd_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        //初始化界面控件实例
        initView();
        //执行修改密码操作
        changed();
    }
    private void changed() {
        tv_submit_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击的时候去获取值
                String old_pwd = old_pwd_id.getText().toString().trim();
                String new_frist_pwd = input_id.getText().toString().trim();
                String new_second_pwd = input_again_id.getText().toString().trim();
                if(old_pwd.isEmpty()||new_frist_pwd.isEmpty()||new_second_pwd.isEmpty()){
                    Toast.makeText(getApplicationContext(), "请填完密码以后提交进行修改", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!new_frist_pwd.equals(new_second_pwd)){
                    Toast.makeText(Change_password_activity.this, "两次输入的密码不一致！", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    JSONObject js=new JSONObject();
                    js.put("type","0");
                    js.put("old_pwd",old_pwd);
                    js.put("new_pwd",new_frist_pwd);
                    js.put("mobile",new Common_utils(Change_password_activity.this).getPhoneNumber());
                    RequestNet.queryServer(js,forgetpwd_URL,Change_password_activity.this,"Change_password_activity");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void initView() {
        old_pwd_id = (EditText) findViewById(R.id.old_pwd_id);//旧密码输入控件
        input_id = (EditText) findViewById(R.id.input_id);
        input_again_id = (EditText) findViewById(R.id.input_again_id);
        tv_submit_id = (TextView) findViewById(R.id.tv_submit_id);//提交按钮
        set_back_id = (AutoLinearLayout) findViewById(R.id.back_id);
        set_back_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void getinfo(String str) {
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(str);
            if(jsonObject.getString("code").equals("200")){
                Toast.makeText(Change_password_activity.this,"密码修改成功", Toast.LENGTH_SHORT).show();
                //清除掉所有的当前数据
                edit.clear();
                edit.commit();//清除用户登录信息
                //删除保存在数据库中的信息
                DbManager db=x.getDb(XUtilsDB.getDBconfig());
                try {
                    db.dropTable(personal.BodyBean.DataBean.class);//将存储的表格也删除
                } catch (DbException e) {
                    e.printStackTrace();
                }
                answer_Application.getInstance().kill();
            }else{
                Toast.makeText(Change_password_activity.this,jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
