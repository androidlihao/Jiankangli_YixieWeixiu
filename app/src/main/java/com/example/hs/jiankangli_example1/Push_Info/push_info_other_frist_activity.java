package com.example.hs.jiankangli_example1.Push_Info;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.example.hs.jiankangli_example1.common_activity_pacage.province_and_city_activity;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;

import Inter.Globle;
import Inter.get_net_Info;
import bean.push_other_bean;
import utils.Common_utils;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/11/18.
 */
public class push_info_other_frist_activity extends AutoLayoutActivity implements get_net_Info {

    private TextView tv_area_id;
    private View ll_diqu_id;
    private View sets_back_id;
    private EditText et_other_title;
    private Button btn_next_id;
    private EditText et_contacts_id;
    private EditText et_emailAddress_id;
    private EditText et_qq_id;
    private EditText et_phoneNumber_id;
    private int cityCode;
    private Dialog mDialog;
    private push_other_bean other_bean;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.push_info_other_frist_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        String informationId=getIntent().getStringExtra("informationId");
        //初始化界面控件实例
        initView();
        if(informationId!=null&&!informationId.isEmpty()){
            requestHttp(informationId);
        }
        //设置监听器
        SetOnClickListener();
    }
    private String get_other_info_URL= Globle.TEST_URL+"/api/info/getInformationOther";
    private void requestHttp(String informationId) {
        mDialog = new Dialog(push_info_other_frist_activity.this, R.style.myDialogTheme2);
        mDialog.setContentView(R.layout.getting);
        mDialog.setCanceledOnTouchOutside(false);
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("memberId",new JavaScriptObject(push_info_other_frist_activity.this).getMemberid(""));
            jsonObject.put("informationId",informationId);
            RequestNet.queryServer(jsonObject,get_other_info_URL,push_info_other_frist_activity.this,"push_other");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void getinfo(String str) {
        mDialog.dismiss();
        other_bean = com.alibaba.fastjson.JSONObject.parseObject(str, push_other_bean.class);
        if(other_bean!=null&&other_bean.getBody()!=null&&other_bean.getBody().getData()!=null){
            et_other_title.setText(other_bean.getBody().getData().getTitle());
            et_contacts_id.setText(other_bean.getBody().getData().getUserName());
            et_emailAddress_id.setText(other_bean.getBody().getData().getEmail());
            et_phoneNumber_id.setText(other_bean.getBody().getData().getMobile());
            et_qq_id.setText(other_bean.getBody().getData().getQq());
            tv_area_id.setText(other_bean.getBody().getData().getCityName());
            cityCode= Integer.parseInt(other_bean.getBody().getData().getCityId());
        }
    }
    private void initView() {
        //发现地区显示按钮
        tv_area_id = (TextView) findViewById(R.id.tv_area_id);
        //发现地区按钮
        ll_diqu_id =  findViewById(R.id.ll_diqu_id);
        sets_back_id = findViewById(R.id.sets_back_id);
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        et_other_title = (EditText) findViewById(R.id.et_other_title);
        et_contacts_id = (EditText) findViewById(R.id.et_contacts_id);
        et_emailAddress_id = (EditText) findViewById(R.id.et_emailAddress_id);
        et_qq_id = (EditText) findViewById(R.id.et_QQ_id);
        et_phoneNumber_id = (EditText) findViewById(R.id.et_phoneNumber_id);
    }
    private void SetOnClickListener() {
        ll_diqu_id.setOnClickListener(new MyOnClickListener());
        sets_back_id.setOnClickListener(new MyOnClickListener());
        btn_next_id.setOnClickListener(new MyOnClickListener());
    }

    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.ll_diqu_id:
                    Intent intent=new Intent(push_info_other_frist_activity.this,province_and_city_activity.class);
                    intent.putExtra("selectID",100000);
                    startActivityForResult(intent,3);
                    break;
                case R.id.sets_back_id:
                    finish();
                    break;
                case R.id.btn_next_id:
                    if (et_other_title.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "标题不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(et_contacts_id.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "联系人不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(et_emailAddress_id.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "邮箱不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!Common_utils.isEmail(et_emailAddress_id.getText().toString())){
                        Toast.makeText(getApplicationContext(), "邮箱格式不正确!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(et_phoneNumber_id.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "手机号不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!Common_utils.isMobileNO(et_phoneNumber_id.getText().toString())){
                        Toast.makeText(getApplicationContext(), "手机号码格式不正确!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(et_qq_id.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "QQ号不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tv_area_id.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "所属地区不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent1=new Intent(push_info_other_frist_activity.this,push_personal_Or_other_second_info_activity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("title",et_other_title.getText().toString().trim());
                    bundle.putString("userName",et_contacts_id.getText().toString().trim());
                    bundle.putString("mobile",et_phoneNumber_id.getText().toString().trim());
                    bundle.putString("email",et_emailAddress_id.getText().toString().trim());
                    bundle.putString("qq",et_qq_id.getText().toString().trim());
                    bundle.putString("cityId",cityCode+"");
                    bundle.putSerializable("push_other_bean",other_bean);
                    bundle.putString("tag","说明");
                    intent1.putExtras(bundle);
                    startActivity(intent1);
                    break;
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==3&&resultCode==2){
            tv_area_id.setText(data.getStringExtra("cityName"));
            cityCode= data.getIntExtra("cityCode",0);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
