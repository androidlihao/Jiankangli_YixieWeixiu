package com.example.hs.jiankangli_example1.push_knowledge_package;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.example.hs.jiankangli_example1.common_activity_pacage.Select_more_activity;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONObject;
import org.xutils.http.RequestParams;

import AsyncTask.push_AsyncTask;
import Inter.Globle;
import Inter.get_net_Info;
import bean.My_draft;
import bean.product;
import utils.CheckContentTitle;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;
import utils.give_up_push;

/**
 * Created by 李浩 on 2016/12/1.
 */
public class explanation_of_nouns_activity extends AutoLayoutActivity implements get_net_Info {

    private EditText product_category_id;
    private View more_id;
    private product.BodyBean.DataBean productbean;
    private View view;
    private Button btn_next_id;
    private Button btn_save_id;
    private EditText et_zymingci_id;
    private EditText et_abbreviation_id;
    private EditText et_short_id;
    private EditText et_adage_id;
    private My_draft myDraft;
    private String draft_URl;
    private Dialog mDialog;
    private String title;
    private String product_category;
    private String singlename;
    private String commonly;
    private String abbreviation;
    private String checkedTitle= Globle.TEST_URL+"/api/knowledge/checkcontenttitle";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.explanation_of_nouns_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        myDraft = (My_draft) getIntent().getSerializableExtra("my_draft");
        mDialog = give_up_push.show_Dialog(explanation_of_nouns_activity.this,new MyOnListener());
        initView();
        setOnClickListener();
    }
    private void setOnClickListener() {
        more_id.setOnClickListener(new MyOnListener());
        view.setOnClickListener(new MyOnListener());
        btn_next_id.setOnClickListener(new MyOnListener());
        btn_save_id.setOnClickListener(new MyOnListener());
    }
    private void initView() {
        //产品分类，必填项
        product_category_id = (EditText) findViewById(R.id.product_category_id);
        more_id = findViewById(R.id.gengduo_1_id);
        view = findViewById(R.id.sets_back_id);
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        btn_save_id = (Button) findViewById(R.id.btn_save_id);
        et_zymingci_id = (EditText) findViewById(R.id.et_zymingci_id);
        et_abbreviation_id = (EditText) findViewById(R.id.et_abbreviation_id);
        et_short_id = (EditText) findViewById(R.id.et_short_id);
        et_adage_id = (EditText) findViewById(R.id.et_adage_id);
        if(myDraft!=null&&myDraft.getBody()!=null&&myDraft.getBody().getData()!=null){
            et_zymingci_id.setText(myDraft.getBody().getData().getTitle());
            if(myDraft.getBody().getData().getProductCategoriesModel()!=null){
                product_category_id.setText(myDraft.getBody().getData().getProductCategoriesModel().getName());
            }
            et_abbreviation_id.setText(myDraft.getBody().getData().getAbbreviation());
            et_adage_id.setText(myDraft.getBody().getData().getCommonly());
            et_short_id.setText(myDraft.getBody().getData().getSinglename());
            draft_URl=Globle.TEST_URL+"/api/knowledge/editcontent";
        }else{
            draft_URl=Globle.TEST_URL+"/api/knowledge/savecontent";
        }
    }
    private void get_content(){
        title = et_zymingci_id.getText().toString();
        product_category = product_category_id.getText().toString();
        singlename = et_short_id.getText().toString();
        commonly = et_adage_id.getText().toString();
        abbreviation = et_abbreviation_id.getText().toString();
    }
    @Override
    public void getinfo(String str) {
        if(str.contains("20011")&&myDraft==null){//并且不是从草稿箱过来的
            Toast.makeText(getApplicationContext(), "专有名词重复！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(product_category.trim().isEmpty()){
            Toast.makeText(explanation_of_nouns_activity.this, "产品分类不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        Bundle bundle=new Bundle();
        if(productbean!=null){
            bundle.putString("product_categories_id", String.valueOf(productbean.getProductCategoriesId()));
        }
        bundle.putString("title",title);
        bundle.putString("product_categories_name", product_category);
        bundle.putString("singlename",singlename);
        bundle.putString("commonly",commonly);
        bundle.putString("content_categories_id","6");
        bundle.putString("content_categories_name","名词解释");
        bundle.putInt("is_noun",1);
        bundle.putString("title",title);
        bundle.putString("abbreviation",abbreviation);
        bundle.putString("push_knowledge_title","解释");
        bundle.putSerializable("my_draft",myDraft);
        bundle.putString("tag_type","名词解释");
        Intent intents=new Intent(explanation_of_nouns_activity.this,push_knowledge_finish_activity.class);
        intents.putExtras(bundle);
        startActivity(intents);
    }
    public class MyOnListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.gengduo_1_id:
                    //跳转到产品分类的界面，获取返回值
                    Intent intent=new Intent(explanation_of_nouns_activity.this, Select_more_activity.class);
                    intent.putExtra("add","产品分类");
                    startActivityForResult(intent,1);//设置Intent和请求码
                    break;
                case R.id.sets_back_id:
                    close();
                    break;
                case R.id.btn_next_id:
                    get_content();
                    if(title.trim().isEmpty()){
                        Toast.makeText(explanation_of_nouns_activity.this, "专用名词不能为空！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    CheckContentTitle.checked(title,"6",explanation_of_nouns_activity.this,"explanation_of_nouns_activity",checkedTitle);
                    break;
                case R.id.btn_save_id:
                    get_content();
                    if(title.trim().isEmpty()&&product_category.trim().isEmpty()
                            &&abbreviation.trim().isEmpty()&&singlename.trim().isEmpty()&&commonly.trim().isEmpty()){
                        Toast.makeText(explanation_of_nouns_activity.this, "至少填写一项才能保存！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        JSONObject jsonObject=new JSONObject();
                        if(productbean!=null){
                            jsonObject.put("product_categories_id",String.valueOf(productbean.getProductCategoriesId()));
                        }
                        if(myDraft!=null){
                            jsonObject.put("content_id",myDraft.getBody().getData().getContentId());
                        }
                        jsonObject.put("product_categories_name",product_category);
                        jsonObject.put("singlename",singlename);
                        jsonObject.put("commonly",commonly);
                        jsonObject.put("abbreviation",abbreviation);
                        jsonObject.put("is_draft",1);
                        jsonObject.put("is_noun",1);
                        jsonObject.put("title",title);
                        jsonObject.put("member_id",new JavaScriptObject(getApplicationContext()).getMemberid(""));
                        jsonObject.put("content_categories_id","6");
                        RequestNet.requestServer(jsonObject,draft_URl,explanation_of_nouns_activity.this,"保存草稿");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.tv_cancle_id:
                    mDialog.dismiss();
                    break;
                case R.id.tv_close_id:
                    mDialog.dismiss();
                    finish();
                    break;
            }
        }
    }
    public void close(){
        get_content();
        if(title.trim().isEmpty()&&product_category.trim().isEmpty()
                &&abbreviation.trim().isEmpty()&&singlename.trim().isEmpty()&&commonly.trim().isEmpty()){
            finish();
            return;
        }
        mDialog.show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        get_content();
        if (keyCode==KeyEvent.KEYCODE_BACK&&(!title.trim().isEmpty()||!product_category.trim().isEmpty()
                ||!abbreviation.trim().isEmpty()||!singlename.trim().isEmpty()||!commonly.trim().isEmpty())){
            mDialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //此时的请求码和结果码,如果跳过去的请求码是1，返回的也是1，说明是产品分类
        if(requestCode==1&&requestCode==resultCode){
            //产品分类
            Log.i("TAG", "onActivityResult: ");
            product_category_id.clearFocus();//失去焦点
            productbean = (product.BodyBean.DataBean) data.getSerializableExtra("duixiang");
            product_category_id.setText(productbean.getName());//将获取到的名称写入
        }else if(requestCode==1&&resultCode==2){
            Log.i("TAG", "onActivityResult:产品的其他");
            product_category_id.setHint("请输入");
            product_category_id.setText("");
            product_category_id.setFocusable(true);//获取焦点,光标闪动
            product_category_id.setFocusableInTouchMode(true);
            product_category_id.requestFocus();
        }
    }
}
