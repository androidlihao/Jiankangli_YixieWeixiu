package com.example.hs.jiankangli_example1.push_knowledge_package;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;

import Inter.Globle;
import bean.My_draft;
import utils.BundleUtils;
import utils.Common_utils;
import utils.RequestNet;
import utils.Statubars;


/**
 * Created by 李浩 on 2016/12/9.
 */

public class push_knowledge_digest_activity extends AutoLayoutActivity implements View.OnClickListener{

    private View back_id;
    private Button btn_next_id;
    private Button btn_save_id;
    private Bundle bundle;
    private EditText et_digest_id;
    private My_draft myDraft;
    private String URL;
    private int is_noun=0;
    private String dratf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.push_knowledge_digest_layout);
        SysApplication.getInstance().addActivity(this);
        answer_Application.getInstance().addActivity(this);
        bundle = getIntent().getExtras();
        initView();
        switch (bundle.getString("tag_type")){
            case "名词解释":
                is_noun=1;
                myDraft = (My_draft) bundle.getSerializable("my_draft");
                if(myDraft!=null&&myDraft.getBody().getData()!=null){
                    et_digest_id.setText(myDraft.getBody().getData().getSummayContent());
                }
                break;
            default:
                dratf = getIntent().getStringExtra("my_draft");
                Log.i("TAG", "onCreate: "+dratf);
                if(dratf !=null){
                    myDraft= com.alibaba.fastjson.JSONObject.parseObject(dratf,My_draft.class);
                    if(myDraft!=null&&myDraft.getBody().getData()!=null){
                        et_digest_id.setText(myDraft.getBody().getData().getSummayContent());
                    }
                }
                break;
        }
        bundle.putInt("is_noun",is_noun);
        setOnClickListener();
    }
    private void initView() {
        back_id = findViewById(R.id.sets_back_id);
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        btn_save_id = (Button) findViewById(R.id.btn_save_id);
        et_digest_id = (EditText) findViewById(R.id.et_digest_id);
    }
    private void setOnClickListener() {
        back_id.setOnClickListener(this);
        btn_next_id.setOnClickListener(this);
        btn_save_id.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sets_back_id:
                finish();
                break;
            case R.id.btn_next_id:
                Intent intent = null;
                bundle.putString("summay_content",et_digest_id.getText().toString().trim());
                switch (bundle.getString("tag_type")){
                    case "故障维修":
                        intent=new Intent(this,guzhang_miaosu_activity.class);
                        intent.putExtra("fangan",bundle);//设置Bundle为方案
                        break;
                    case "工作原理":
                        intent=new Intent(this,work_push_activity.class);
                        intent.putExtra("gongzuo",bundle);//设置Bundle为方案
                        break;
                    case "错误编码":
                        intent=new Intent(this,possible_activity.class);
                        intent.putExtra("gongzuo",bundle);//设置Bundle为方案
                        break;
                    case "维修秘籍":
                        intent=new Intent(this,push_knowledge_repair_second_activity.class);
                        intent.putExtras(bundle);
                        break;
                    case "名词解释":
                        intent=new Intent(this,push_knowledge_finish_activity.class);
                        intent.putExtras(bundle);
                        break;
                }
                switch (bundle.getString("tag_type")){
                    case "名词解释":
                        break;
                    default:
                        if(!TextUtils.isEmpty(dratf)){
                            intent.putExtra("my_draft",dratf);
                        }
                        break;
                }
                startActivity(intent);
                break;
            case R.id.btn_save_id:
                try {
                    org.json.JSONObject jsonObject=new org.json.JSONObject();
                    if(myDraft!=null){
                        URL= Globle.TEST_URL+"/api/knowledge/editcontent";//编辑草稿
                        if(myDraft.getBody().getData()!=null){
                            jsonObject.put("content_id",myDraft.getBody().getData().getContentId());
                        }
                    }else{
                        URL=Globle.TEST_URL+"/api/knowledge/savecontent";//保存草稿
                    }
                    jsonObject.put("member_id",new Common_utils(getApplicationContext()).getMemberid());//会员ID
                    jsonObject.put("summay_content",et_digest_id.getText().toString().trim());
                    jsonObject.put("is_draft",1);//是否为草稿
                    bundle.remove("my_draft");
                    jsonObject=BundleUtils.bundleToJsonObject(bundle,jsonObject);
                    Log.i("TAG", "onClick: "+jsonObject);
                    RequestNet.requestServer(jsonObject,URL,push_knowledge_digest_activity.this,"保存草稿");
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }
}
