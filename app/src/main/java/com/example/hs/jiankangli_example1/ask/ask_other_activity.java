package com.example.hs.jiankangli_example1.ask;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import Inter.Globle;
import Inter.get_net_Info;
import bean.question_bean;
import utils.CheckContentTitle;
import utils.JavaScriptObject;
import utils.Max_integral;
import utils.RequestNet;
import utils.Statubars;
import utils.give_up_push;

/**
 * Created by 李浩 on 2016/11/8.
 */
public class ask_other_activity extends AutoLayoutActivity implements get_net_Info {
    private String ASK_URL;
    private EditText fabu_title_id;
    private EditText et_keyword_id;
    private EditText et_integral_id;
    private EditText et_digest_id;
    private String title;
    private String keyword;
    private String integral;
    private Dialog mDialog;
    private String digest;
    private question_bean questionBean;
    private String checkedTitle= Globle.TEST_URL+"/api/qanda/checkQuestionTitle";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.ask_other_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        mDialog = give_up_push.show_Dialog(ask_other_activity.this,new MyOnListener());
        initView();
        setQuestionBean();
    }
    private void initView() {
        findViewById(R.id.sets_back_id).setOnClickListener(new MyOnListener());
        questionBean = (question_bean) getIntent().getSerializableExtra("question");
        if(questionBean!=null){
            ASK_URL=Globle.TEST_URL+"/api/qanda/rePublishCreateQuestion";//审核失败再次提交接口
        }else{
            ASK_URL= Globle.TEST_URL+"/api/qanda/createQuestion";
        }
        fabu_title_id = (EditText) findViewById(R.id.fabu_title_id);
        et_keyword_id = (EditText) findViewById(R.id.et_keyword_id);
        et_integral_id = (EditText) findViewById(R.id.et_integral_id);
        et_digest_id = (EditText) findViewById(R.id.et_digest_id);
        new Max_integral().SetTextChange(et_integral_id,getApplicationContext());
    }
    private void setQuestionBean() {
        if(questionBean!=null&&questionBean.getBody()!=null&&questionBean.getBody().getData()!=null){
            fabu_title_id.setText(questionBean.getBody().getData().getTitle());
            et_keyword_id.setText(questionBean.getBody().getData().getKeyWord());
            et_integral_id.setText(questionBean.getBody().getData().getBountyIntegral()+"");
            et_digest_id.setText(questionBean.getBody().getData().getSummayContent());
        }
    }
    public void push(View view){
        get_content();
        if(title.trim().isEmpty()){
            Toast.makeText(ask_other_activity.this, "标题不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        CheckContentTitle.checked(title,"5",ask_other_activity.this,"ask_other_activity",checkedTitle);
    }
    private void get_content(){
        title = fabu_title_id.getText().toString();
        keyword = et_keyword_id.getText().toString();
        integral = et_integral_id.getText().toString();
        digest = et_digest_id.getText().toString();
    }
    @Override
    public void getinfo(String str) {
        if(str.contains("20010")&&questionBean==null){//并且不是从草稿箱过来的
            Toast.makeText(getApplicationContext(), "标题重复！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(keyword.trim().isEmpty()){
            Toast.makeText(ask_other_activity.this, "关键字不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(integral.trim().isEmpty()){
            Toast.makeText(ask_other_activity.this, "悬赏积分不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Integer.parseInt(integral)<=0){
            Toast.makeText(ask_other_activity.this, "悬赏积分必须为正整数!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            JSONObject jsonObject=new JSONObject();
            if (questionBean!=null&&questionBean.getBody().getData()!=null){
                jsonObject.put("question_id",questionBean.getBody().getData().getQuestionId());
            }
            jsonObject.put("content_categories_id","5");
            jsonObject.put("title",title);
            jsonObject.put("member_id",new JavaScriptObject(ask_other_activity.this).getMemberid(""));
            jsonObject.put("key_word",keyword);
            jsonObject.put("bounty_integral",integral);
            jsonObject.put("summay_content",et_digest_id.getText().toString().trim());
            RequestNet.requestServer(jsonObject,ASK_URL,ask_other_activity.this,"提问");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public class MyOnListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_cancle_id:
                    mDialog.dismiss();
                    break;
                case R.id.tv_close_id:
                    mDialog.dismiss();
                    finish();
                    break;
                case R.id.sets_back_id:
                    close();
                    break;
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        get_content();
        if (keyCode==KeyEvent.KEYCODE_BACK&&(!title.trim().isEmpty()||!keyword.trim().isEmpty()||
                !integral.trim().isEmpty()||!digest.trim().isEmpty())){
            mDialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }
    public void close(){
        get_content();
        if(title.trim().isEmpty()&&keyword.trim().isEmpty()&&
                integral.trim().isEmpty()&&digest.trim().isEmpty()){
            finish();
            return;
        }
        mDialog.show();
    }
}
