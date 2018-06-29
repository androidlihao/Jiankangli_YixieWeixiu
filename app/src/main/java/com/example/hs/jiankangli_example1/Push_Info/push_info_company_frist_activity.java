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
import com.example.hs.jiankangli_example1.common_activity_pacage.brand_or_skill_activity;
import com.example.hs.jiankangli_example1.common_activity_pacage.province_and_city_activity;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import Inter.get_net_Info;
import bean.push_company_bean;
import bean.send_LinkedList_bean;
import Inter.Globle;
import utils.Common_utils;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/11/18.
 */
public class push_info_company_frist_activity extends AutoLayoutActivity implements get_net_Info {

    private View ll_service_brand_id;
    private View ll_service_skills_id;
    private TextView tv_area_id;
    private View ll_diqu_id;
    private View sets_back_id;
    private EditText et_company_name;
    private Button btn_next_id;
    private send_LinkedList_bean slb;
    private TextView tv_service_skills_id;
    private send_LinkedList_bean slbs;
    private TextView tv_service_brand_id;
    private EditText et_contacts_id;
    private EditText et_emailAddress_id;
    private EditText et_qq_id;
    private EditText et_phoneNumber_id;
    private int cityCode;
    private StringBuffer skill_idBuffer;
    private StringBuffer brand_idBuffer;
    private Dialog mDialog;
    private String getCompanyInfo_URl= Globle.TEST_URL+"/api/info/getCompanyInfo";
    private push_company_bean push_company_bean;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.push_info_company_frist_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        //初始化界面控件实例
        initView();
        try {
            getPeopleInfo();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //设置监听器
        SetOnClickListener();
    }
    private void initView() {
        //服务品牌
        ll_service_brand_id = findViewById(R.id.ll_service_brand_id);
        //服务技能
        ll_service_skills_id = findViewById(R.id.ll_Service_skills_id);
        //发现地区显示按钮
        tv_area_id = (TextView) findViewById(R.id.tv_area_id);
        //发现地区按钮
        ll_diqu_id =  findViewById(R.id.ll_diqu_id);
        sets_back_id = findViewById(R.id.sets_back_id);
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        et_company_name = (EditText) findViewById(R.id.et_company_name);
        tv_service_skills_id = (TextView) findViewById(R.id.tv_Service_skills_id);
        tv_service_brand_id = (TextView) findViewById(R.id.tv_service_brand_id);
        et_contacts_id = (EditText) findViewById(R.id.et_contacts_id);
        et_emailAddress_id = (EditText) findViewById(R.id.et_emailAddress_id);
        et_qq_id = (EditText) findViewById(R.id.et_QQ_id);
        et_phoneNumber_id = (EditText) findViewById(R.id.et_phoneNumber_id);
    }
    private void SetOnClickListener() {
        ll_service_brand_id.setOnClickListener(new MyOnClickListener());
        ll_service_skills_id.setOnClickListener(new MyOnClickListener());
        ll_diqu_id.setOnClickListener(new MyOnClickListener());
        sets_back_id.setOnClickListener(new MyOnClickListener());
        btn_next_id.setOnClickListener(new MyOnClickListener());
    }
    @Override
    public void getinfo(String str) {
        Log.i("TAG", "getinfo: "+str);
        try {
           JSONObject js = new JSONObject(str);
            if(js.getString("code").equals("200")) {//如果code码为200
                push_company_bean = com.alibaba.fastjson.JSONObject.parseObject(str, push_company_bean.class);
                if(push_company_bean!=null&&push_company_bean.getBody()!=null&&push_company_bean.getBody().getData()!=null){
                    LinkedList<LinkedList<String>> shillList=new LinkedList<>();
                    LinkedList<LinkedList<String>> brandList=new LinkedList<>();
                    //获取厂商
                    List<push_company_bean.BodyBean.DataBean.ManufacturerListBean> firmList = push_company_bean.getBody().getData().getManufacturerList();
                    //获取产品
                    List<bean.push_company_bean.BodyBean.DataBean.ProductModelBean> productModelList =
                            push_company_bean.getBody().getData().getProductModel();
                    skill_idBuffer=new StringBuffer();
                    StringBuffer skillBuffer=new StringBuffer();
                    for(int i=0;i<productModelList.size();i++){
                        skill_idBuffer.append(productModelList.get(i).getProductCategoriesId()+",");//产品
                        skillBuffer.append(productModelList.get(i).getName()+" ");
                        LinkedList<String> linkedList=new LinkedList<String>();
                        linkedList.addFirst(productModelList.get(i).getName());//名字
                        linkedList.addLast(String.valueOf(productModelList.get(i).getProductCategoriesId()));//id
                        shillList.add(linkedList);
                    }
                    slb=new send_LinkedList_bean();
                    slb.setSends(shillList);
                    if(skill_idBuffer!=null&&skill_idBuffer.length()!=0){
                        skill_idBuffer.deleteCharAt(skill_idBuffer.length()-1);//技能
                    }
                    brand_idBuffer=new StringBuffer();
                    StringBuffer brandBuffer=new StringBuffer();
                    for(int i=0;i<firmList.size();i++){
                        brand_idBuffer.append(firmList.get(i).getManufacturerId()+",");
                        brandBuffer.append(firmList.get(i).getName()+" ");
                        LinkedList<String> linkedList=new LinkedList<>();
                        linkedList.addFirst(firmList.get(i).getName());//名字
                        linkedList.addLast(String.valueOf(firmList.get(i).getManufacturerId()));//id
                        brandList.add(linkedList);
                    }
                    slbs=new send_LinkedList_bean();
                    slbs.setSends(brandList);
                    if(brand_idBuffer!=null&&brand_idBuffer.length()!=0){
                        brand_idBuffer.deleteCharAt(brand_idBuffer.length()-1);
                    }
                    cityCode=Integer.parseInt(push_company_bean.getBody().getData().getCityId());
                    et_company_name.setText(push_company_bean.getBody().getData().getCompanyName());
                    tv_service_brand_id.setText(brandBuffer);
                    tv_service_skills_id.setText(skillBuffer);
                    et_contacts_id.setText(push_company_bean.getBody().getData().getUserName());
                    et_emailAddress_id.setText(push_company_bean.getBody().getData().getEmail());
                    et_phoneNumber_id.setText(push_company_bean.getBody().getData().getMobile());
                    et_qq_id.setText(push_company_bean.getBody().getData().getQq());
                    tv_area_id.setText(push_company_bean.getBody().getData().getCityName());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mDialog.dismiss();
    }

    public String getPeopleInfo() throws JSONException {
        mDialog = new Dialog(push_info_company_frist_activity.this, R.style.myDialogTheme2);
        mDialog.setContentView(R.layout.getting);
        mDialog.setCanceledOnTouchOutside(false);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("memberId",new JavaScriptObject(this).getMemberid(""));
        RequestNet.queryServer(jsonObject,getCompanyInfo_URl,push_info_company_frist_activity.this,"push_company");
        mDialog.show();
        return null;
    }
    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.ll_service_brand_id:
                    Intent brand_intent=new Intent(push_info_company_frist_activity.this, brand_or_skill_activity.class);
                    //将现在就已经选择的数据传过去
                    brand_intent.putExtra("tag","brand");
                    //将已经选择好的传递过去
                    brand_intent.putExtra("skill_brand",slbs);
                    startActivityForResult(brand_intent,200);
                    break;
                case R.id.ll_Service_skills_id:
                    Intent skill_intent=new Intent(push_info_company_frist_activity.this, brand_or_skill_activity.class);
                    //将现在就已经选择的数据传过去
                    skill_intent.putExtra("tag","skill");
                    //将已经选择好的传递过去
                    skill_intent.putExtra("skill_brand",slb);
                    startActivityForResult(skill_intent,200);
                    break;
                case R.id.ll_diqu_id:
                    Intent intent=new Intent(push_info_company_frist_activity.this,province_and_city_activity.class);
                    intent.putExtra("selectID",100000);
                    startActivityForResult(intent,3);
                    break;
                case R.id.sets_back_id:
                    finish();
                    break;
                case R.id.btn_next_id:
                    if (et_company_name.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "公司名称不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tv_service_brand_id.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "服务品牌不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tv_service_skills_id.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "服务技能不能为空!", Toast.LENGTH_SHORT).show();
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
                    Intent intent1=new Intent(push_info_company_frist_activity.this,push_info_no_pictures_activity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("companyName",et_company_name.getText().toString().trim());
                    bundle.putString("userName",et_contacts_id.getText().toString().trim());
                    bundle.putString("mobile",et_phoneNumber_id.getText().toString().trim());
                    bundle.putString("email",et_emailAddress_id.getText().toString().trim());
                    bundle.putString("qq",et_qq_id.getText().toString().trim());
                    bundle.putString("cityId",cityCode+"");
                    bundle.putString("productCategoriesIds",skill_idBuffer.toString());
                    bundle.putString("manufacturerIds",brand_idBuffer.toString());
                    bundle.putString("tag","公司简介");
                    bundle.putSerializable("push_company_bean",push_company_bean);
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
            Log.i("TAG", "onActivityResult: "+cityCode);
        }else if(requestCode==200&&resultCode==6){
            //得到服务技能数据
            slb = (send_LinkedList_bean) data.getSerializableExtra("result");
            StringBuffer stringBuffer=new StringBuffer();
            skill_idBuffer = new StringBuffer();
            for(int i=0;i<slb.getSends().size();i++){
                stringBuffer.append(slb.getSends().get(i).getFirst()+" ");
                skill_idBuffer.append(slb.getSends().get(i).getLast()+",");
            }
            if(skill_idBuffer!=null&&skill_idBuffer.length()!=0){
                skill_idBuffer.deleteCharAt(skill_idBuffer.length()-1);//技能
            }
            if(slb!=null){
                tv_service_skills_id.setText(stringBuffer);
            }
        }else if(requestCode==200&&resultCode==7){
            slbs = (send_LinkedList_bean) data.getSerializableExtra("result");
            StringBuffer stringBuffer=new StringBuffer();
            brand_idBuffer = new StringBuffer();
            for(int i=0;i<slbs.getSends().size();i++){
                stringBuffer.append(slbs.getSends().get(i).getFirst()+" ");
                brand_idBuffer.append(slbs.getSends().get(i).getLast()+",");
            }
            if(brand_idBuffer!=null&&brand_idBuffer.length()!=0){
                brand_idBuffer.deleteCharAt(brand_idBuffer.length()-1);
            }
            if(slbs!=null){
                tv_service_brand_id.setText(stringBuffer);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
