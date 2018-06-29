package com.example.hs.jiankangli_example1.ask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;

import Inter.Globle;
import bean.question_bean;
import utils.BundleUtils;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;


/**
 * Created by 李浩 on 2016/11/7.
 */
public class ask_digest_activity extends AutoLayoutActivity{
    private String ASK_URL;
    private EditText et_digest_id;
    private Bundle bundle;
    private question_bean questionBean;
    private Button btn_sub_next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.ask_digest_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        bundle = getIntent().getBundleExtra("ask");
        String sing=bundle.getString("sign");
        btn_sub_next = (Button) findViewById(R.id.btn_sub_next);
        switch (sing){
            case "fault":
                btn_sub_next.setText("下一步");
                break;
        }
        questionBean = (question_bean) getIntent().getSerializableExtra("question");
        if(questionBean!=null){
            ASK_URL=Globle.TEST_URL+"/api/qanda/rePublishCreateQuestion";//审核失败再次提交接口
        }else{
            ASK_URL= Globle.TEST_URL+"/api/qanda/createQuestion";
        }
        et_digest_id = (EditText) findViewById(R.id.et_digest_id);
        if (questionBean!=null&&questionBean.getBody().getData()!=null){
            et_digest_id.setText(questionBean.getBody().getData().getSummayContent());
        }
        goBack();
    }
    public void PushAsk(View view){
        if (btn_sub_next.getText().toString().equals("下一步")){
            Intent intent=new Intent(this,ask_second_activity.class);
            bundle.putString("summay_content",et_digest_id.getText().toString());
            intent.putExtra("ask",bundle);
            startActivity(intent);
            return;
        }
        push();
    }
    private void push() {
        try {
            JSONObject js=new JSONObject();
            if (questionBean!=null&&questionBean.getBody().getData()!=null){
                js.put("question_id",questionBean.getBody().getData().getQuestionId());
            }
            js=BundleUtils.bundleToJsonObject(bundle,js);
            js.put("member_id",new JavaScriptObject(ask_digest_activity.this).getMemberid(""));
            js.put("summay_content",et_digest_id.getText().toString());
            RequestNet.requestServer(js,ASK_URL,ask_digest_activity.this,"提问");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void goBack() {
        findViewById(R.id.sets_back_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
