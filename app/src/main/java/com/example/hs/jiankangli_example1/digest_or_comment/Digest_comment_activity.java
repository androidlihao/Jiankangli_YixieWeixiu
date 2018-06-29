package com.example.hs.jiankangli_example1.digest_or_comment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import Inter.Globle;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/10/20.
 */
public class Digest_comment_activity extends AutoLayoutActivity{
    private EditText et_write_zy_id;
    private TextView tv_xiezhaiyao_id;
    private String contentid;
    private AutoLinearLayout sets_back_id;
    private TextView tv_show_id;
    private TextView tv_titles_id;
    private String info;
    private String URL;
    private String tag;
    private String comment;
    private JSONObject jsonObjects;
    private String toast;
    private String digest_comment;
    @SuppressLint({ "JavascriptInterface","SetJavaScriptEnabled" })
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.write_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        Intent intent=getIntent();
        tag = intent.getStringExtra("tag");
        initview();
        switch (tag){
            case "write":
                digest_comment="请先输入摘要,然后再提交!";
                URL=Globle.TEST_URL+"/api/details/insertContentSummayById";
                contentid = intent.getStringExtra("id");
                tv_titles_id.setText("写摘要");
                tv_xiezhaiyao_id.setText("提交");
                break;
            case "comment":
                digest_comment="评论内容不能为空！";
                comment = intent.getStringExtra("comment");
                try {
                    jsonObjects = new JSONObject(comment);
                    if(comment.contains("nickname")){
                        et_write_zy_id.setHint("回复:"+jsonObjects.getString("nickname"));
                    }else{
                        et_write_zy_id.setHint("回复:");
                    }
                    switch (jsonObjects.getString("content_type")){
                        case "1":
                            URL=Globle.TEST_URL+"/api/contentComment/insertContentComment";
                            break;
                        case "2":
                            URL=Globle.TEST_URL+"/api/qanda/insertAnswerComment";
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tv_titles_id.setText("评论");
                tv_xiezhaiyao_id.setText("发送");
                break;
        }
        setOnClickListener();
    }
    private void setOnClickListener() {
        sets_back_id.setOnClickListener(new MyOnClickListener());
        tv_xiezhaiyao_id.setOnClickListener(new MyOnClickListener());
    }
    int num = 200;//限制的最大字数　　
    private void initview() {
       sets_back_id = (AutoLinearLayout) findViewById(R.id.sets_back_id);
        et_write_zy_id = (EditText) findViewById(R.id.et_write_zy_id);
        et_write_zy_id.setCursorVisible(true);
        tv_show_id = (TextView) findViewById(R.id.tv_show_id);
        tv_titles_id = (TextView) findViewById(R.id.tv_titles_id);
        //弹出弹窗
        tv_xiezhaiyao_id = (TextView) findViewById(R.id.tv_xiezhaiyao_id);
        et_write_zy_id.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }
            public void afterTextChanged(Editable s) {
                int number = num - s.length();
                tv_show_id.setText("您还可以输入" + number+"字");
                selectionStart = et_write_zy_id.getSelectionStart();
                selectionEnd = et_write_zy_id.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    tv_show_id.setText(s);
                    et_write_zy_id.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }
    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.sets_back_id:
                    finish();
                    break;
                case R.id.tv_xiezhaiyao_id:
                    if(et_write_zy_id.getText().toString().trim()!=null&&!et_write_zy_id.getText().toString().trim().isEmpty()){
                        info = et_write_zy_id.getText().toString().trim();
                        JSONObject jsonObject=new JSONObject();
                        switch (tag){
                            case "write":
                                try {
                                    toast="提交";
                                    jsonObject.put("contentId",contentid);
                                    jsonObject.put("summayContent",info);
                                    jsonObject.put("memberId",new JavaScriptObject(Digest_comment_activity.this).getMemberid(""));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "comment":
                                toast="评论";
                                try {
                                    switch (jsonObjects.getString("content_type")){//评论知识库,回复知识库
                                        case "1":
                                            jsonObject.put("content_id",jsonObjects.getString("content_id"));
                                            jsonObject.put("comment_member_id",new JavaScriptObject(getApplicationContext()).getMemberid(""));
                                            jsonObject.put("content",info);
                                            if(comment.contains("replyMemberId")){
                                                jsonObject.put("reply_member_id",jsonObjects.getString("replyMemberId"));
                                            }
                                            if(comment.contains("replycommmentid")){
                                                jsonObject.put("reply_commment_id",jsonObjects.getString("replycommmentid"));
                                            }
                                            break;
                                        case "2":
                                            Log.i("TAG", "onClick: "+jsonObjects);
                                            jsonObject.put("answer_id",jsonObjects.getString("answer_id"));
                                            jsonObject.put("comment_member_id",new JavaScriptObject(getApplicationContext()).getMemberid(""));
                                            jsonObject.put("content",info);
                                            if(comment.contains("replyMemberId")){
                                                jsonObject.put("reply_member_id",jsonObjects.getString("replyMemberId"));
                                            }
                                            if(comment.contains("replycommmentid")){
                                                jsonObject.put("reply_commment_id",jsonObjects.getString("replycommmentid"));
                                            }
                                            break;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                        RequestNet.requestServer(jsonObject,URL,Digest_comment_activity.this,toast);
                    }else{
                        Toast.makeText(getApplicationContext(),digest_comment, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
