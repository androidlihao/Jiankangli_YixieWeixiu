package com.example.hs.jiankangli_example1.Push_Info;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import bean.push_project_bean;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/11/18.
 */
public class push_info_project_frist_activity extends AutoLayoutActivity implements get_net_Info {

    private TextView tv_area_id;
    private View ll_diqu_id;
    private View sets_back_id;
    private Button btn_next_id;
    private int cityCode;

    private EditText et_project_name_id;
    private EditText et_project_cycle_id;
    private EditText et_project_budget_id;
    private String get_project_info_URL= Globle.TEST_URL+"/api/info/infoProjectDetail";
    private push_project_bean push_project_bean;
    private Dialog mDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.push_info_project_frist_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        String informationId = getIntent().getStringExtra("informationId");
        //初始化界面控件实例
        initView();
        if(informationId!=null&&!informationId.isEmpty()){
            requestHttp(informationId);
        }
        //设置监听器
        SetOnClickListener();
    }

    private void requestHttp(String informationId) {
        mDialog = new Dialog(push_info_project_frist_activity.this, R.style.myDialogTheme2);
        mDialog.setContentView(R.layout.getting);
        mDialog.setCanceledOnTouchOutside(false);
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("memberId",new JavaScriptObject(push_info_project_frist_activity.this).getMemberid(""));
            jsonObject.put("informationId",informationId);
            RequestNet.queryServer(jsonObject,get_project_info_URL,push_info_project_frist_activity.this,"push_project");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void getinfo(String str) {
        mDialog.dismiss();
        push_project_bean = com.alibaba.fastjson.JSONObject.parseObject(str, push_project_bean.class);
        if(push_project_bean!=null&&push_project_bean.getBody()!=null&&push_project_bean.getBody().getData()!=null){
            et_project_name_id.setText(push_project_bean.getBody().getData().getProjectName());
            et_project_cycle_id.setText(push_project_bean.getBody().getData().getProjectCycle());
            et_project_budget_id.setText(push_project_bean.getBody().getData().getProjectBudget());
            tv_area_id.setText(push_project_bean.getBody().getData().getCityName());
            cityCode= Integer.parseInt(push_project_bean.getBody().getData().getCityId());
        }
    }
    private void initView() {
        //发现地区显示按钮
        tv_area_id = (TextView) findViewById(R.id.tv_area_id);
        //发现地区按钮
        ll_diqu_id =  findViewById(R.id.ll_diqu_id);
        sets_back_id = findViewById(R.id.sets_back_id);
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        et_project_name_id = (EditText) findViewById(R.id.et_project_name_id);//项目名称
        et_project_cycle_id = (EditText) findViewById(R.id.et_project_cycle_id);//项目周期
        et_project_budget_id = (EditText) findViewById(R.id.et_project_budget_id);//项目预算
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
                    Intent intent=new Intent(push_info_project_frist_activity.this,province_and_city_activity.class);
                    intent.putExtra("selectID",100000);
                    startActivityForResult(intent,3);
                    break;
                case R.id.sets_back_id:
                    finish();
                    break;
                case R.id.btn_next_id:
                    if(et_project_name_id.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "项目名称不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(et_project_cycle_id.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "项目周期不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(et_project_budget_id.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "项目预算不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tv_area_id.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "所属地区不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent1=new Intent(push_info_project_frist_activity.this,push_info_no_pictures_activity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("projectName",et_project_name_id.getText().toString());
                    bundle.putString("cityId",cityCode+"");
                    bundle.putString("projectCycle",et_project_cycle_id.getText().toString());
                    bundle.putString("projectBudget",et_project_budget_id.getText().toString());
                    if(push_project_bean!=null){
                        bundle.putSerializable("push_project_bean",push_project_bean);
                    }
                    bundle.putString("tag","项目说明");
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
