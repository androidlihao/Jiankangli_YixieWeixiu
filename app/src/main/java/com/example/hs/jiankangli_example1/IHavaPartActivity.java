package com.example.hs.jiankangli_example1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.example.hs.jiankangli_example1.common_activity_pacage.Select_more_activity;
import com.example.hs.jiankangli_example1.common_activity_pacage.province_and_city_activity;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

import AsyncTask.UoLoadAsyncTask;
import AsyncTask.delete_AsyncTask;
import Inter.Globle;
import Inter.getPath_Inter;
import MyView.MyGridView;
import bean.Pic_bean;
import bean.firm;
import bean.product;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import utils.Common_utils;
import utils.JavaScriptObject;
import utils.PicPathToJson;
import utils.Pic_MultiImageSelector_util;
import utils.RequestNet;
import utils.Statubars;
import utils.ninelayout_Adapter;

public class IHavaPartActivity extends AppCompatActivity implements View.OnClickListener , getPath_Inter {

    private TextView tv_location_id;
    private EditText et_companyName_id;
    private EditText et_contactsName_id;
    private EditText et_phoneNumber_id;
    private EditText et_emailAddress_id;
    private EditText et_address_id;
    private EditText et_partDetalis_id;
    private MyGridView gv_id;
    private View sets_back_id;
    private Button btn_submit_id;
    private LinkedList list;
    private ninelayout_Adapter adapter;
    private View location_id;
    private int cityCode;
    private EditText et_partsName_id;
    private EditText et_manufacturerName_id;
    private EditText et_productcategory_id;
    private EditText et_partsNO_id;
    private EditText et_serialNo_id;
    private EditText et_productModel_id;
    private String manufacturerId;
    private String productCategoriesId;
    private View ll_productcategory_id;
    private View ll_manufacturerName_id;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.activity_ihava_part);
        answer_Application.getInstance().addActivity(this);
        type = getIntent().getStringExtra("type");
        initView();
        initData();
    }

    private void initView() {
        TextView tv_title_id= (TextView) findViewById(R.id.tv_title_id);
        switch (type){
            case "2":
                tv_title_id.setText("我有配件");
                break;
            case "3":
                tv_title_id.setText("我要配件");
                break;
        }
        et_companyName_id = (EditText) findViewById(R.id.et_companyName_id);
        et_contactsName_id = (EditText) findViewById(R.id.et_contactsName_id);
        et_phoneNumber_id = (EditText) findViewById(R.id.et_phoneNumber_id);
        et_emailAddress_id = (EditText) findViewById(R.id.et_emailAddress_id);
        tv_location_id = (TextView) findViewById(R.id.tv_location_id);
        location_id = findViewById(R.id.location_id);
        et_address_id = (EditText) findViewById(R.id.et_address_id);
        et_partDetalis_id = (EditText) findViewById(R.id.et_PartDetalis_id);
        gv_id = (MyGridView) findViewById(R.id.gv_id);
        sets_back_id = findViewById(R.id.sets_back_id);
        btn_submit_id = (Button) findViewById(R.id.btn_submit_id);
        et_partsName_id = (EditText) findViewById(R.id.et_PartsName_id);
        et_manufacturerName_id = (EditText) findViewById(R.id.et_ManufacturerName_id);
        et_productcategory_id = (EditText) findViewById(R.id.et_productcategory_id);
        ll_productcategory_id = findViewById(R.id.ll_productcategory_id);
        ll_manufacturerName_id = findViewById(R.id.ll_ManufacturerName_id);
        et_partsNO_id = (EditText) findViewById(R.id.et_PartsNO_id);
        et_serialNo_id = (EditText) findViewById(R.id.et_SerialNo_id);
        et_productModel_id = (EditText) findViewById(R.id.et_productModel_id);
        et_manufacturerName_id.setFocusable(false);
    }

    @Override
    protected void onStart() {
        btn_submit_id.setOnClickListener(this);
        sets_back_id.setOnClickListener(this);
        location_id.setOnClickListener(this);
        ll_manufacturerName_id.setOnClickListener(this);
        ll_productcategory_id.setOnClickListener(this);
        super.onStart();
    }
    private ArrayList<String> deleteList=new ArrayList<>();
    private void initData() {
        list = new LinkedList<>();
        if(list.size()<5){
            LinkedList  <String> linkedList=new LinkedList<>();
            linkedList.addFirst("tianjia");
            list.add(linkedList);
        }
        adapter = new ninelayout_Adapter(this,list);
        gv_id.setAdapter(adapter);//绑定适配    器

        PicPathToJson picpath=new PicPathToJson(this,list,deleteList);
        picpath.GridViewOnItem(gv_id, adapter);
        picpath.GridViewDelete(gv_id, adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public  boolean falg=true;//默认可以上传的
    private String  PushUrl= Globle.TEST_URL+"/api/partHaveWant/addHaveOrWant";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_productcategory_id:
                    Intent intent=new Intent(this, Select_more_activity.class);
                    intent.putExtra("add","产品分类");
                    startActivityForResult(intent,1);//设置Intent和请求码
                break;
            case R.id.ll_ManufacturerName_id:
                    Intent intent2=new Intent(    this,Select_more_activity.class);
                    intent2.putExtra("add","品牌");
                    startActivityForResult(intent2,2);
                break;
            case R.id.sets_back_id:
                finish();
                break;
            case R.id.btn_submit_id:
                if (falg==false){

                }else{
                    Log.i("TAG", "onClick: "+list);
                }
                String companyName=et_companyName_id.getText().toString().trim();
                String contacts=et_contactsName_id.getText().toString().trim();
                String phone=et_phoneNumber_id.getText().toString().trim();
                String email=et_emailAddress_id.getText().toString().trim();
                String location=tv_location_id.getText().toString().trim();//所在地
                String describe=et_partDetalis_id.getText().toString().trim();
                String address=et_address_id.getText().toString().trim();
                String partName=et_partsName_id.getText().toString().trim();
                String model=et_productModel_id.getText().toString().trim();
                String partNo=et_partsNO_id.getText().toString().trim();
                String serialNumber=et_serialNo_id.getText().toString().trim();
                try {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("memberId",new JavaScriptObject(this).getMemberid(""));
                    if (companyName.trim().isEmpty()){
                        Toast.makeText(this, "请输入公司名称！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    jsonObject.put("companyName", companyName);
                    if (contacts.trim().isEmpty()){
                        Toast.makeText(this, "请输入联系人名称！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    jsonObject.put("contacts",contacts);
                    if (phone.trim().isEmpty()){
                        Toast.makeText(this, "请输入联系人电话！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!Common_utils.isMobileNO(phone)){
                        Toast.makeText(this, "电话号码格式不正确！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!email.trim().isEmpty()&&!Common_utils.isEmail(email)){
                        Toast.makeText(this, "邮箱格式不正确！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    jsonObject.put("phone",phone);
                    jsonObject.put("email",email);
                    jsonObject.put("describe",describe);
                    jsonObject.put("type",type);
                    jsonObject.put("address",address);
                    if (partName.trim().isEmpty()){
                        Toast.makeText(this, "请输入配件名称！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    jsonObject.put("partName",partName);
                    jsonObject.put("manufacturerId", manufacturerId);
                    jsonObject.put("productCategoriesId", productCategoriesId);
                    jsonObject.put("model",model);
                    jsonObject.put("cityid",cityCode);
                    jsonObject.put("partNo",partNo);
                    jsonObject.put("serialNumber",serialNumber);
                    jsonObject=PicPathToJson.getHaveWant(Integer.parseInt(type),jsonObject,list);
                    Log.i("TAG", "Submit: "+jsonObject);
                    RequestNet.requestServer(jsonObject,PushUrl,this,"提交");
                    new delete_AsyncTask().execute(deleteList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.location_id:
                //所在地
                Intent intent3=new Intent(this,province_and_city_activity.class);
                intent3.putExtra("selectID",100000);
                startActivityForResult(intent3,3);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2&&resultCode == RESULT_OK){
            ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);//得到返回来的本地地址集合
            if(path.size()==6){
                path.remove(5);
            }
            new Pic_MultiImageSelector_util(list,this).getURLList(list,path);//根据返回来的值去得到GridView的数据源
            adapter.notifyDataSetChanged();
            falg=false;
            //开始上传数据
            new UoLoadAsyncTask(this,path,list,"IHavaPartActivity").execute();
        }else if(requestCode==3&&resultCode==2){
            tv_location_id.setText(data.getStringExtra("cityName"));
            cityCode = data.getIntExtra("cityCode",0);
        }
        //此时的请求码和结果码,如果跳过去的请求码是1，返回的也是1，说明是产品分类
       else if(requestCode==1&&requestCode==resultCode){
            //产品分类
            product.BodyBean.DataBean productbean = (product.BodyBean.DataBean) data.getSerializableExtra("duixiang");
            productCategoriesId=productbean.getProductCategoriesId()+"";
            et_productcategory_id.setText(productbean.getName());//将获取到的名称写 入
            et_productcategory_id.setFocusable(false);
            et_productcategory_id.setFocusableInTouchMode(false);
        }else if(requestCode==1&&resultCode==2){
            et_productcategory_id.setHint("请输入");
            et_productcategory_id.setText("");
            et_productcategory_id.setFocusable(true);//获取焦点,光标闪动
            et_productcategory_id.setFocusableInTouchMode(true);
            et_productcategory_id.requestFocus();
        }else if(requestCode==2&&resultCode==1){
            //厂商分类
            et_manufacturerName_id.setFocusable(false);
            et_productcategory_id.setFocusableInTouchMode(false);
            firm.BodyBean.DataBean firmbean = (firm.BodyBean.DataBean) data.getSerializableExtra("duixiang");
            manufacturerId=firmbean.getManufacturerId()+"";
            et_manufacturerName_id.setText(firmbean.getName());//将获取到的名称写入
        }else if(requestCode==2&&resultCode==2){
            et_manufacturerName_id.setHint("请输入");
            et_manufacturerName_id.setText("");
            et_manufacturerName_id.setFocusable(true);//获取焦点,光标闪动
            et_manufacturerName_id.setFocusableInTouchMode(true);
            et_manufacturerName_id.requestFocus();
        }
    }

    @Override
    public void getpath(LinkedList<LinkedList<String>> urlList) {
        falg=true;
        list=urlList;
    }
}
