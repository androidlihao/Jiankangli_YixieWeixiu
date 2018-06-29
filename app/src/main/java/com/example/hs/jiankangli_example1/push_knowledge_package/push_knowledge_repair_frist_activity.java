package com.example.hs.jiankangli_example1.push_knowledge_package;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONObject;

import Inter.get_net_Info;
import bean.My_draft;
import bean.firm;
import bean.product;
import Inter.Globle;
import utils.CheckContentTitle;
import utils.Common_OnClickListener;
import utils.Common_utils;
import utils.RequestNet;
import utils.Statubars;
import utils.give_up_push;

/**
 * Created by 李浩 on 2016/9/26.
 */
public class push_knowledge_repair_frist_activity extends AutoLayoutActivity implements get_net_Info {
    private EditText et_title_id;
    private EditText product_category_id;
    private EditText et_firm_id;
    private EditText et_jixing_id;
    private EditText et_guanjianzi_id;
    private EditText et_zixitong_id;
    private Button btn_next_id;
    private Button btn_save_id;
    private View gengduo_1_id;
    private View gengduo_2_id;
    private product.BodyBean.DataBean productbean;
    private firm.BodyBean.DataBean firmbean;
    private View sets_back_id;
    private int contentid;
    private String results;
    private String title;
    private String category;
    private String firm;
    private String jixing;
    private String guanjianzi;
    private String zixitong;
    private Dialog mDialog;
    private String checkedTitle= Globle.TEST_URL+"/api/knowledge/checkcontenttitle";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.weixiu0_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);//将当前页面添加到集合中
        mDialog = give_up_push.show_Dialog(push_knowledge_repair_frist_activity.this,new MyOnListener());
        initView();//初始化界面控件实例
        setOnClickListener();//设置监听器
        results = getIntent().getStringExtra("my_draft");
        if(!TextUtils.isEmpty(results)){
            My_draft my_draft=com.alibaba.fastjson.JSONObject.parseObject(results,My_draft.class);
            if(my_draft.getBody()!=null){
                if(my_draft.getBody().getData()!=null){
                    et_title_id.setText(my_draft.getBody().getData().getTitle());
                    et_jixing_id.setText(my_draft.getBody().getData().getModels());
                    et_guanjianzi_id.setText(my_draft.getBody().getData().getKeyWord());
                    et_zixitong_id.setText(my_draft.getBody().getData().getSubsystem());
                    contentid = my_draft.getBody().getData().getContentId();
                    if(my_draft.getBody().getData().getProductCategoriesModel()!=null){
                        product_category_id.setText(my_draft.getBody().getData().getProductCategoriesModel().getName());
                    }
                    if(my_draft.getBody().getData().getManufacturerModel()!=null){
                        et_firm_id.setText(my_draft.getBody().getData().getManufacturerModel().getName());
                    }
                }
            }
        }
    }
    private void setOnClickListener() {
        btn_next_id.setOnClickListener(new MyOnListener());
        btn_save_id.setOnClickListener(new MyOnListener());
        product_category_id.setOnClickListener(new MyOnListener());
        et_firm_id.setOnClickListener(new MyOnListener());
        sets_back_id.setOnClickListener(new MyOnListener());
        Common_OnClickListener common_onClickListener=new Common_OnClickListener(this);
        gengduo_1_id.setOnClickListener(common_onClickListener);
        gengduo_2_id.setOnClickListener(common_onClickListener);
    }
    private void initView() {
        et_title_id = (EditText) findViewById(R.id.et_title_id);
        product_category_id = (EditText) findViewById(R.id.product_category_id);
        et_firm_id = (EditText) findViewById(R.id.et_firm_id);
        et_jixing_id = (EditText) findViewById(R.id.et_jixing_id);
        et_guanjianzi_id = (EditText) findViewById(R.id.et_guanjianzi_id);
        et_zixitong_id = (EditText) findViewById(R.id.et_zixitong_id);
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        btn_save_id = (Button) findViewById(R.id.btn_save_id);
        gengduo_1_id =  findViewById(R.id.gengduo_1_id);
        gengduo_2_id = findViewById(R.id.gengduo_2_id);
        sets_back_id = findViewById(R.id.sets_back_id);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //此时的请求码和结果码,如果跳过去的请求码是1，返回的也是1，说明是产品分类
        if(requestCode==1&&requestCode==resultCode){
            //产品分类
            product_category_id.clearFocus();//失去焦点
            productbean = (product.BodyBean.DataBean) data.getSerializableExtra("duixiang");
            product_category_id.setText(productbean.getSinglename());//将获取到的名称写入
        }else if(requestCode==1&&resultCode==2){
            product_category_id.setHint("请输入");
            product_category_id.setText("");
            product_category_id.setFocusable(true);//获取焦点,光标闪动
            product_category_id.setFocusableInTouchMode(true);
            product_category_id.requestFocus();
        }else if(requestCode==2&&resultCode==1){
            //厂商分类
            et_firm_id.clearFocus();
            firmbean = (firm.BodyBean.DataBean) data.getSerializableExtra("duixiang");
            et_firm_id.setText(firmbean.getSinglename());//将获取到的名称写入
        }else if(requestCode==2&&resultCode==2){
            et_firm_id.setHint("请输入");
            et_firm_id.setText("");
            et_firm_id.setFocusable(true);//获取焦点,光标闪动
            et_firm_id.setFocusableInTouchMode(true);
            et_firm_id.requestFocus();
        }
    }
    @Override
    public void getinfo(String str) {
        if(str.contains("20010")&&results==null){//并且不是从草稿箱过来的
            Toast.makeText(push_knowledge_repair_frist_activity.this, "标题重复！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(category)){
            Toast.makeText(getApplicationContext(), "产品分类为必填项,不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(firm)){
            Toast.makeText(getApplicationContext(), "品牌分类为必填项，不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(jixing)){
            Toast.makeText(getApplicationContext(), "机型为必填项，不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(guanjianzi)){
            Toast.makeText(getApplicationContext(), "关键字为必填项，不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        Bundle bundle=new Bundle();
        Intent intent=new Intent(push_knowledge_repair_frist_activity.this,push_knowledge_digest_activity.class);//跳转到摘要页
        //设置内容Id
        bundle.putString("content_categories_id","4");
        //标题
        bundle.putString("title",title);
        if(!TextUtils.isEmpty(results)){//如果是草稿界面过来的
            intent.putExtra("my_draft",results);
        }
        bundle.putString("content_categories_name","维修秘籍");
        if(productbean!=null){
            bundle.putString("product_categories_id", String.valueOf(productbean.getProductCategoriesId()));
        }
        //产品分类名称
        bundle.putString("product_categories_name",category);
        //厂商ID
        if(firmbean!=null){
            bundle.putString("manufacturer_id", String.valueOf(firmbean.getManufacturerId()));
        }
        //厂商名称
        bundle.putString("manufacturer_name",firm);
        //机型
        bundle.putString("models",jixing);
        //关键字
        bundle.putString("key_word",guanjianzi);
        //子系统
        bundle.putString("subsystem",zixitong);
        bundle.putString("tag_type","维修秘籍");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private class MyOnListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            try {
                switch (view.getId()){//下一步按钮
                    case R.id.btn_next_id:
                        get_content();
                        if(title.trim().isEmpty()){
                            Toast.makeText(getApplicationContext(), "标题为必填项，不能为空！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        CheckContentTitle.checked(title,"4",push_knowledge_repair_frist_activity.this,"push_knowledge_repair_frist_activity",checkedTitle);
                        break;
                    case R.id.btn_save_id://保存草稿按钮
                        get_content();
                        if(title.trim().isEmpty()&&category.trim().isEmpty()&&firm.trim().isEmpty()
                                &&jixing.trim().isEmpty()&&guanjianzi.trim().isEmpty()
                                &&zixitong.trim().isEmpty()){
                            Toast.makeText(getApplicationContext(),"至少填写一项才能保存！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String URL;
                        JSONObject jsonObject=new JSONObject();
                        if (!TextUtils.isEmpty(results)){
                            URL=Globle.TEST_URL+"/api/knowledge/editcontent";
                            jsonObject.put("content_id",contentid);
                        }else{
                            URL=Globle.TEST_URL+"/api/knowledge/savecontent";
                        }
                        jsonObject.put("title",title);
                        if(productbean!=null){
                            jsonObject.put("product_categories_id", String.valueOf(productbean.getProductCategoriesId()));
                        }
                        if(firmbean!=null){
                            jsonObject.put("manufacturer_id", String.valueOf(firmbean.getManufacturerId()));
                        }
                        jsonObject.put("manufacturer_name",firm);
                        jsonObject.put("product_categories_name",category);
                        jsonObject.put("models",jixing);
                        jsonObject.put("key_word",guanjianzi);
                        jsonObject.put("subsystem",zixitong);
                        jsonObject.put("is_draft",1);//设置是否为草稿
                        jsonObject.put("member_id",new Common_utils(getApplicationContext()).getMemberid());//会员ID
                        jsonObject.put("content_categories_id","4");
                        RequestNet.requestServer(jsonObject,URL,push_knowledge_repair_frist_activity.this,"保存草稿");
                        break;
                    case R.id.sets_back_id:
                        close();
                        break;
                    case R.id.tv_cancle_id:
                        mDialog.dismiss();
                        break;
                    case R.id.tv_close_id:
                        mDialog.dismiss();
                        finish();
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void get_content(){
        title = et_title_id.getText().toString();
        category = product_category_id.getText().toString();
        firm = et_firm_id.getText().toString();
        jixing = et_jixing_id.getText().toString();
        guanjianzi = et_guanjianzi_id.getText().toString();
        zixitong = et_zixitong_id.getText().toString();
    }
    public void close(){
        get_content();
        if(title.trim().isEmpty()&&category.trim().isEmpty()
                &&firm.trim().isEmpty()&&jixing.trim().isEmpty()
                &&guanjianzi.trim().isEmpty()&&zixitong.trim().isEmpty()){
            finish();
            return;
        }
        mDialog.show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        get_content();
        if (keyCode==KeyEvent.KEYCODE_BACK&&(!title.trim().isEmpty()||!category.trim().isEmpty()
                ||!firm.trim().isEmpty()||!jixing.trim().isEmpty()
                ||!guanjianzi.trim().isEmpty()||!zixitong.trim().isEmpty())){
            mDialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
