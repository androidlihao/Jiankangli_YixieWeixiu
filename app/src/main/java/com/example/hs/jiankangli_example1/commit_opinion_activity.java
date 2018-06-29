package com.example.hs.jiankangli_example1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import Inter.Globle;
import utils.Common_utils;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/10/13.
 */
public class commit_opinion_activity extends AutoLayoutActivity{

    private AutoLinearLayout yj_back_id;
    private EditText et_yijian_id;
    private EditText et_yijianphone_id;
    private TextView tv_tijiao_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏的颜色
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.opinion_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        //初始化界面控件实例
        initView();
        setOnClickListener();
    }
    private void setOnClickListener() {
        yj_back_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initView() {
        yj_back_id = (AutoLinearLayout) findViewById(R.id.yj_back_id);
        et_yijian_id = (EditText) findViewById(R.id.et_yijian_id);
        et_yijianphone_id = (EditText) findViewById(R.id.et_yijianphone_id);
        tv_tijiao_id = (TextView) findViewById(R.id.tv_tijiao_id);
        tv_tijiao_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String opinion=et_yijian_id.getText().toString();
                String phone_number=et_yijianphone_id.getText().toString();
                if(opinion.trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "请输入您对我们的意见或者建议以后再提交！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(phone_number.trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "请输入您的手机号以便我们联系您！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Common_utils.isMobileNO(phone_number)){
                    Toast.makeText(getApplicationContext(), "请输入正确的手机号码再提交！", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestHttps();
            }
        });
    }
    private void requestHttps() {
        String Uri=Globle.TEST_URL+"/api/feedBack/insertFeedBack";
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("content",et_yijian_id.getText().toString());
            jsonObject.put("phone",et_yijianphone_id.getText().toString());
            jsonObject.put("member_id",new JavaScriptObject(commit_opinion_activity.this).getMemberid(""));//会员ID
            RequestNet.requestServer(jsonObject,Uri,commit_opinion_activity.this,"意见反馈");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
