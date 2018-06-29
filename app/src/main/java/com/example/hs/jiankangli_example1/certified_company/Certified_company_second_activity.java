package com.example.hs.jiankangli_example1.certified_company;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;

import bean.Company_bean;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/11/15.
 */
public class Certified_company_second_activity extends AutoLayoutActivity{

    private EditText et_company_profile_id;
    private Button btn_next_id;
    private Bundle bundle;
    private Company_bean cb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_second_layout);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        bundle = getIntent().getExtras();
        cb = (Company_bean) getIntent().getSerializableExtra("company_info");
        initView();
    }
    private void initView() {
        findViewById(R.id.sets_back_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        et_company_profile_id = (EditText) findViewById(R.id.et_company_profile_id);
        if(cb!=null&&cb.getBody()!=null&&cb.getBody().getData()!=null){
            et_company_profile_id.setText(cb.getBody().getData().getIntroduction());
        }
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        btn_next_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_company_profile_id.getText().toString()!=null&&!et_company_profile_id.getText().toString().isEmpty()){
                    bundle.putString("introduction",et_company_profile_id.getText().toString());
                    Intent intent=new Intent(Certified_company_second_activity.this,Certified_company_three_activity.class);
                    intent.putExtra("tags","sfz");
                    if(cb!=null){
                        intent.putExtra("company_info",cb);
                    }
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(Certified_company_second_activity.this, "请输入公司简介再进行下一步哦！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
