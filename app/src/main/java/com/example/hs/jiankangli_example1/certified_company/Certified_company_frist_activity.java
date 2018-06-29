package com.example.hs.jiankangli_example1.certified_company;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.example.hs.jiankangli_example1.common_activity_pacage.province_and_city_activity;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;

import Inter.get_net_Info;
import bean.Company_bean;
import bean.Pic_bean;
import Inter.Globle;
import utils.Common_utils;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/11/14.
 */
public class Certified_company_frist_activity extends AutoLayoutActivity implements get_net_Info {

    private EditText et_company_name;
    private EditText legal_person_name_id;
    private LinearLayout ll_area_id;
    private LinearLayout sets_back_id;
    private Button btn_next_id;
    private EditText et_address_id;
    private EditText et_contacts_id;
    private EditText et_phoneNumber_id;
    private EditText et_qq_id;
    private EditText et_emailAddress_id;
    private TextView tv_area_id;
    private String checkCompanyName_URl= Globle.TEST_URL+"/api/info/checkCompanyName";
    private Company_bean cb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_frist_layout);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        cb = (Company_bean) getIntent().getSerializableExtra("company_info");
        //初始化界面控件实例
        initView();
        //设置监听器
        setOnClickListener();
    }
    private void initView() {
        et_company_name = (EditText) findViewById(R.id.et_company_name);//公司名字
        legal_person_name_id = (EditText) findViewById(R.id.legal_person_name_id);//法人名字
        ll_area_id = (LinearLayout) findViewById(R.id.ll_area_id);//省份
        et_address_id = (EditText) findViewById(R.id.et_address_id);//详细地址
        sets_back_id = (LinearLayout) findViewById(R.id.sets_back_id);//返回
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        et_contacts_id = (EditText) findViewById(R.id.et_contacts_id);//联系人
        et_phoneNumber_id = (EditText) findViewById(R.id.et_phoneNumber_id);//手机号码
        et_qq_id = (EditText) findViewById(R.id.et_QQ_id);//QQ
        et_emailAddress_id = (EditText) findViewById(R.id.et_emailAddress_id);//邮箱
        tv_area_id = (TextView) findViewById(R.id.tv_area_id);
        if(cb!=null&&cb.getBody()!=null&&cb.getBody().getData()!=null){
            et_company_name.setText(cb.getBody().getData().getCompanyName());
             legal_person_name_id.setText(cb.getBody().getData().getLegalPerson());
            et_address_id.setText(cb.getBody().getData().getAddress());
            et_contacts_id.setText(cb.getBody().getData().getUserName());
            et_phoneNumber_id.setText(cb.getBody().getData().getMobile());
            et_qq_id.setText(cb.getBody().getData().getQq());
            et_emailAddress_id.setText(cb.getBody().getData().getEmail());
            tv_area_id.setText(cb.getBody().getData().getCityName());
        }
    }
    private void setOnClickListener() {
        ll_area_id.setOnClickListener(new MyOnClickListener());
        sets_back_id.setOnClickListener(new MyOnClickListener());
        btn_next_id.setOnClickListener(new MyOnClickListener());
    }

    @Override
    public void getinfo(String str){
        try {
            JSONObject js=new JSONObject(str);
            String checkCode=js.getString("code");
            if(cb==null&&!checkCode.equals("200")){//有重复的情况出现
                Toast.makeText(this,js.getString("message"), Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(legal_person_name_id.getText().toString())){
                Toast.makeText(getApplicationContext(), "法人名称不能为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(tv_area_id.getText().toString())){
                Toast.makeText(this, "所属地区不能为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(et_address_id.getText().toString())){
                Toast.makeText(this, "详细地址不能为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(et_contacts_id.getText().toString())){
                Toast.makeText(this, "联系人不能为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(et_phoneNumber_id.getText().toString())){
                Toast.makeText(this, "手机号码不能为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(et_qq_id.getText().toString())){
                Toast.makeText(this, "QQ号码不能为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(et_emailAddress_id.getText().toString())){
                Toast.makeText(this, "邮箱不能为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            //开始执行下一步
            if(!Common_utils.isMobileNO(et_phoneNumber_id.getText().toString())){
                Toast.makeText(getApplicationContext(), "手机号码格式不正确!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!Common_utils.isEmail(et_emailAddress_id.getText().toString())){
                Toast.makeText(getApplicationContext(), "邮箱格式不正确!", Toast.LENGTH_SHORT).show();
                return;
            }
            Pic_bean.logo_path=null;
            Pic_bean.back_path=null;
            Pic_bean.zhengmian_path=null;
            Pic_bean.zhizhao_path=null;
            Intent nextIntent=new Intent(Certified_company_frist_activity.this,Certified_company_second_activity.class);
            Bundle bundle=new Bundle();
            bundle.putString("mobile",et_phoneNumber_id.getText().toString());
            bundle.putString("companyName",et_company_name.getText().toString());
            bundle.putString("legalPerson",legal_person_name_id.getText().toString());
            bundle.putString("userName",et_contacts_id.getText().toString());
            bundle.putString("email",et_emailAddress_id.getText().toString());
            bundle.putString("qq",et_qq_id.getText().toString());
            if(data!=null){//如果有选择省份，那么取选择的省份
                bundle.putInt("cityId",data.getIntExtra("cityCode",0));
            }else if(cb!=null&&data==null){
                bundle.putInt("cityId", Integer.parseInt(cb.getBody().getData().getCityId()));
            }
            bundle.putString("address",et_address_id.getText().toString());
            if(cb!=null){
                nextIntent.putExtra("company_info",cb);
            }
            nextIntent.putExtras(bundle);
            startActivity(nextIntent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
               case  R.id.ll_area_id:
                    //跳转到省份界面
                   Intent intent=new Intent(Certified_company_frist_activity.this,province_and_city_activity.class);
                   intent.putExtra("selectID",100000);
                   startActivityForResult(intent,3);
                   break;
                case R.id.sets_back_id:
                    finish();
                    break;
                case R.id.btn_next_id:
                    if(et_company_name.getText().toString()!=null&&!et_company_name.getText().toString().isEmpty()){

                        JSONObject jsonObject=new JSONObject();
                        try {
                            jsonObject.put("memberId",new JavaScriptObject(Certified_company_frist_activity.this).getMemberid(""));
                            jsonObject.put("companyName",et_company_name.getText().toString());
                            RequestNet.queryServer(jsonObject,checkCompanyName_URl,Certified_company_frist_activity.this,"checked");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "公司名称不能为空!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
    private Intent data;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==3&&resultCode==2){
            this.data=data;
            tv_area_id.setText(data.getStringExtra("cityName"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
