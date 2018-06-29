package com.example.hs.jiankangli_example1.Push_Info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;

import bean.push_company_bean;
import bean.push_personal_bean;
import Inter.Globle;
import bean.push_project_bean;
import utils.BundleUtils;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/11/15.
 */
public class push_info_no_pictures_activity extends AutoLayoutActivity{

    private EditText et_company_profile_id;
    private Button btn_next_id;
    private Bundle bundle;
    private String tag;
    private TextView tv_title_push_comany_info_id;
    private String type;
    private String URL= Globle.TEST_URL+"/api/info/infoProjectInsert";
    private push_personal_bean personal_bean;
    private push_company_bean company_bean;
    private push_project_bean project_bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        setContentView(R.layout.company_second_layout);
        answer_Application.getInstance().addActivity(this);
        bundle = getIntent().getExtras();
        tag = bundle.getString("tag");
        personal_bean = (push_personal_bean) bundle.getSerializable("push_personal_bean");
        company_bean = (push_company_bean) bundle.getSerializable("push_company_bean");
        project_bean = (push_project_bean) bundle.getSerializable("push_project_bean");
        type = bundle.getString("push");
        initView();
    }
    private void initView() {
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        tv_title_push_comany_info_id = (TextView) findViewById(R.id.tv_title_push_comany_info_id);
        tv_title_push_comany_info_id.setText(tag);
        findViewById(R.id.sets_back_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        et_company_profile_id = (EditText) findViewById(R.id.et_company_profile_id);
        et_company_profile_id.setHint("请输入"+tag+"，限制不得超过2000个字符");
        if(personal_bean!=null&&personal_bean.getBody()!=null&&personal_bean.getBody().getData()!=null){
            et_company_profile_id.setText(personal_bean.getBody().getData().getDescription());
        }
        switch (tag){
            case "公司简介":
                if(company_bean!=null&&company_bean.getBody()!=null&&company_bean.getBody().getData()!=null){
                    et_company_profile_id.setText(company_bean.getBody().getData().getIntroduction());
                }
                break;
            case "服务说明":
                if(company_bean!=null&&company_bean.getBody()!=null&&company_bean.getBody().getData()!=null){
                    et_company_profile_id.setText(company_bean.getBody().getData().getDescription());
                }
                break;
            case "项目说明":
                if(project_bean!=null&&project_bean.getBody()!=null&&project_bean.getBody().getData()!=null){
                    et_company_profile_id.setText(project_bean.getBody().getData().getDescription());
                }
                break;
            case "项目要求":
                if(project_bean!=null&&project_bean.getBody()!=null&&project_bean.getBody().getData()!=null){
                    et_company_profile_id.setText(project_bean.getBody().getData().getProjectRequirement());
                }
                btn_next_id.setText("提交");
                break;
        }
        btn_next_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (tag){
                    case "公司简介":
                        if(et_company_profile_id.getText().toString()!=null&&!et_company_profile_id.getText().toString().isEmpty()){
                            Intent intent=new Intent(push_info_no_pictures_activity.this,push_info_no_pictures_activity.class);
                            bundle.putString("introduction",et_company_profile_id.getText().toString());
                            bundle.putString("tag","服务说明");
                            intent.putExtras(bundle);
                            bundle.putSerializable("push_company_bean",company_bean);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "请输入公司简介再进行下一步哦！", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "服务说明":
                        if(et_company_profile_id.getText().toString()!=null&&!et_company_profile_id.getText().toString().isEmpty()){
                            bundle.putString("desc",et_company_profile_id.getText().toString());
                            Intent intent=new Intent(push_info_no_pictures_activity.this,push_zhengshu_activity.class);
                            if(type!=null&&type.equals("push_personal_info")){//个人
                                bundle.putString("tag",type);
                            }else{
                                bundle.putString("tag","push_company_info");//公司
                                bundle.putSerializable("push_company_bean",company_bean);
                            }
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "请输入服务说明再进行下一步哦！", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "项目说明":
                        if(et_company_profile_id.getText().toString()!=null&&!et_company_profile_id.getText().toString().isEmpty()){
                            bundle.putString("desc",et_company_profile_id.getText().toString());
                            Intent intent=new Intent(push_info_no_pictures_activity.this,push_info_no_pictures_activity.class);
                            bundle.putString("tag","项目要求");
                            bundle.putSerializable("push_project_bean",project_bean);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "请输入项目说明再进行下一步哦！", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "项目要求":
                        if(et_company_profile_id.getText().toString()!=null&&!et_company_profile_id.getText().toString().isEmpty()){
                            bundle.putString("projectRequirement",et_company_profile_id.getText().toString());
                            org.json.JSONObject jsonObject= new org.json.JSONObject();
                            try {
                                if(project_bean!=null&&project_bean.getBody()!=null&&project_bean.getBody().getData()!=null){
                                    URL=Globle.TEST_URL+"/api/info/infoProjectUpdate";
                                    jsonObject.put("informationId",project_bean.getBody().getData().getInformationId());
                                }
                                jsonObject.put("memberId",new JavaScriptObject(getApplicationContext()).getMemberid(""));
                                jsonObject=BundleUtils.bundleToJsonObject(bundle,jsonObject);
                                RequestNet.requestServer(jsonObject,URL,push_info_no_pictures_activity.this,"push_project");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "请输入项目要求再进行下一步哦！", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
    }
}
