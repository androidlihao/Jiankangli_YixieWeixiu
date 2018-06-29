package com.example.hs.jiankangli_example1.certified_company;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;

import Inter.get_net_Info;
import bean.Company_bean;
import Inter.Globle;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/11/19.
 */
public class Certified_company_status_activity extends AutoLayoutActivity implements get_net_Info {

    private TextView tv_approve_id;
    private TextView tv_status_id;
    private String statusCode;
    private Company_bean cb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        setContentView(R.layout.company_approve_status_layout);
        statusCode = getIntent().getStringExtra("statusCode");
        try {
            RequestHttp();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initView();
    }
    private String Company_Apporve_URL= Globle.TEST_URL+"/api/info/getAttestationCompanyInfo";
    private void RequestHttp() throws JSONException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("memberId",new JavaScriptObject(getApplicationContext()).getMemberid(""));
        RequestNet.queryServer(jsonObject,Company_Apporve_URL,Certified_company_status_activity.this,"approve");
    }

    private void initView() {
        tv_approve_id = (TextView) findViewById(R.id.tv_approve_id);
        tv_status_id = (TextView) findViewById(R.id.tv_status_id);
        findViewById(R.id.sets_back_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        switch (statusCode){
            case "2":
                tv_status_id.setText("公司认证审核中");
                break;
            case "3":
                tv_status_id.setText("公司认证审核通过");
                break;
            case "4":
                tv_status_id.setText("公司认证审核失败");
                break;
        }
        tv_approve_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb!=null){
                    Intent intent=new Intent(Certified_company_status_activity.this,Certified_company_frist_activity.class);
                    intent.putExtra("company_info",cb);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void getinfo(String str) {
        Log.i("TAG", "getinfo: "+str);
        cb = com.alibaba.fastjson.JSONObject.parseObject(str,Company_bean.class);
    }
}
