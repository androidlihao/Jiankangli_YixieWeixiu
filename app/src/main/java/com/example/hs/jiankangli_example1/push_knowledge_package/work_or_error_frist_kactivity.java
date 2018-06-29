package com.example.hs.jiankangli_example1.push_knowledge_package;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
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

import com.example.hs.jiankangli_example1.common_activity_pacage.Select_more_activity;

import Inter.get_net_Info;
import bean.My_draft;
import bean.firm;
import bean.product;
import Inter.Globle;
import utils.CheckContentTitle;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;
import utils.give_up_push;

/**
 * Created by 李浩 on 2016/9/26.
 */
public class work_or_error_frist_kactivity extends AutoLayoutActivity implements get_net_Info {
    private EditText fabu_title_id;
    private EditText product_category_id;
    private EditText et_firm_id;
    private EditText et_jixing_id;
    private EditText et_guanjianzi_id;
    private EditText et_zixitong_id;
    private Button btn_next_id;
    private Button btn_save_id;
    private AutoLinearLayout gengduo_1_id;
    private AutoLinearLayout gengduo_2_id;
    private product.BodyBean.DataBean productbean;
    private firm.BodyBean.DataBean firmbean;
    private String cuowu;
    private TextView tv_content_id;
    private AutoLinearLayout sets_back_id;
    private int contentid;
    private String results;
    private String title;
    private String category;
    private String firm;
    private String jixing;
    private String guanjianzi;
    private String zixitong;
    private String URL;
    private Dialog mDialog;
    private String checkedTitle= Globle.TEST_URL+"/api/knowledge/checkcontenttitle";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏的颜色
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.gongzuo_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);//将当前页面添加到集合中
        Intent intent=getIntent();
        //初始化界面控件实例
        cuowu = intent.getStringExtra("cuowu");
        mDialog = give_up_push.show_Dialog(work_or_error_frist_kactivity.this,new MyOnListener());
        initView();
        //设置监听器
        setOnClickListener();
        //获取到草稿页面传过来的值
        results = getIntent().getStringExtra("my_draft");
        if(results !=null&&!results.isEmpty()){
            My_draft my_draft=com.alibaba.fastjson.JSONObject.parseObject(results,My_draft.class);
            if(my_draft.getBody()!=null){
                if(my_draft.getBody().getData()!=null){
                    fabu_title_id.setText(my_draft.getBody().getData().getTitle());
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
        gengduo_1_id.setOnClickListener(new MyOnListener());
        gengduo_2_id.setOnClickListener(new MyOnListener());
        sets_back_id.setOnClickListener(new MyOnListener());
    }
    private void initView() {
        tv_content_id = (TextView) findViewById(R.id.tv_content_id);
        if(cuowu!=null){
            tv_content_id.setText(cuowu);
        }
        sets_back_id = (AutoLinearLayout)findViewById(R.id.sets_back_id);
        fabu_title_id = (EditText) findViewById(R.id.fabu_title_id);//发布的标题，必填项
        product_category_id = (EditText) findViewById(R.id.product_category_id);//产品分类，必填项
        et_firm_id = (EditText) findViewById(R.id.et_firm_id);//厂商，必填项
        et_jixing_id = (EditText) findViewById(R.id.et_jixing_id);//机型，必填项
        et_guanjianzi_id = (EditText) findViewById(R.id.et_guanjianzi_id);//关键字必填项
        et_zixitong_id = (EditText) findViewById(R.id.et_zixitong_id);//子系统，非必选填项
        btn_next_id = (Button) findViewById(R.id.btn_next_id);//第一个为下一步
        btn_save_id = (Button) findViewById(R.id.btn_save_id);//第二个为保存草稿
        //发现界面控件实例
        gengduo_1_id = (AutoLinearLayout) findViewById(R.id.gengduo_1_id);
        gengduo_2_id = (AutoLinearLayout) findViewById(R.id.gengduo_2_id);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //此时的请求码和结果码,如果跳过去的请求码是1，返回的也是1，说明是产品分类
        if(requestCode==1&&requestCode==resultCode){
            //产品分类
            product_category_id.clearFocus();//失去焦点
            productbean = (product.BodyBean.DataBean) data.getSerializableExtra("duixiang");
            product_category_id.setText(productbean.getName());//将获取到的名称写入
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
            et_firm_id.setText(firmbean.getName());//将获取到的名称写入
        }else if(requestCode==2&&resultCode==2){
            et_firm_id.setHint("请输入");
            et_firm_id.setText("");
            et_firm_id.setFocusable(true);//获取焦点,光标闪动
            et_firm_id.setFocusableInTouchMode(true);
            et_firm_id.requestFocus();
        }
    }
    private void get_content(){
        title = fabu_title_id.getText().toString();
        category = product_category_id.getText().toString();
        firm = et_firm_id.getText().toString();
        jixing = et_jixing_id.getText().toString();
        guanjianzi = et_guanjianzi_id.getText().toString();
        zixitong = et_zixitong_id.getText().toString();
    }
    @Override
    public void getinfo(String str) {
        if(str.contains("20010")&&results==null){//并且不是从草稿箱过来的
            Toast.makeText(work_or_error_frist_kactivity.this, "标题重复！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(category.trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "产品分类为必填项，不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(firm.trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "品牌分类为必填项，不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(jixing.trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "机型为必填项，不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(guanjianzi.trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "关键字为必填项，不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        Bundle bundle=new Bundle();
        Intent intent;
        //获取所有的选项，然后开始下一步的操作，将本页的数据传输给下一步
        if(cuowu!=null){
            intent=new Intent(work_or_error_frist_kactivity.this,push_knowledge_digest_activity.class);
            bundle.putString("content_categories_id","3");
            bundle.putString("tag_type","错误编码");
            bundle.putString("content_categories_name","错误编码");
        }else{
            intent=new Intent(work_or_error_frist_kactivity.this,push_knowledge_digest_activity.class);
            bundle.putString("content_categories_name","工作原理");
            bundle.putString("tag_type","工作原理");
            bundle.putString("content_categories_id","2");
        }
        if(results!=null&&!results.isEmpty()){//如果是草稿界面过来的
            intent.putExtra("my_draft",results);
        }
        //标题
        bundle.putString("title",fabu_title_id.getText().toString());
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
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private class MyOnListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){//下一步按钮
                //获取到输入框中的信息
                case R.id.btn_next_id:
                    get_content();
                    if(title.trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "标题为必填项，不能为空！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(cuowu!=null){
                        CheckContentTitle.checked(title,"3",work_or_error_frist_kactivity.this,"work_or_error_frist_kactivity",checkedTitle);
                    }else{
                        CheckContentTitle.checked(title,"2",work_or_error_frist_kactivity.this,"work_or_error_frist_kactivity",checkedTitle);
                    }
                    break;
                case R.id.btn_save_id://保存草稿按钮
                     get_content();
                    if(title.trim().isEmpty()&&category.trim().isEmpty()&&firm.trim().isEmpty()
                            &&jixing.trim().isEmpty()&&guanjianzi.trim().isEmpty()
                            &&zixitong.trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "至少填写一项才能保存！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //开始执行保存草稿的操作
                    JSONObject jsonObject=new JSONObject();
                    if(results!=null&&!results.isEmpty()){//如果是从草稿箱过来的
                        URL=Globle.TEST_URL+"/api/knowledge/editcontent";
                        try {
                            jsonObject.put("content_id",contentid);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        URL=Globle.TEST_URL+"/api/knowledge/savecontent";
                    }
                    try {
                        if(productbean!=null){
                            jsonObject.put("product_categories_id", String.valueOf(productbean.getProductCategoriesId()));
                        }
                        if(firmbean!=null){
                            jsonObject.put("manufacturer_id", String.valueOf(firmbean.getManufacturerId()));
                        }
                        jsonObject.put("title",title);
                        jsonObject.put("manufacturer_name",firm);
                        jsonObject.put("product_categories_name",category);
                        jsonObject.put("models",jixing);
                        jsonObject.put("key_word",guanjianzi);
                        jsonObject.put("subsystem",zixitong);
                        jsonObject.put("is_draft",1);//设置结果
                        jsonObject.put("member_id",new JavaScriptObject(getApplicationContext()).getMemberid(""));//会员ID
                        if(cuowu!=null){
                            jsonObject.put("content_categories_id","3");//错误编码为3
                        }else{
                            jsonObject.put("content_categories_id","2");//工作原理为2
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestNet.requestServer(jsonObject,URL,work_or_error_frist_kactivity.this,"保存草稿");
                    break;
                case R.id.gengduo_1_id:
                    //跳转到产品分类的界面，获取返回值
                    Intent intent=new Intent(work_or_error_frist_kactivity.this, Select_more_activity.class);
                    intent.putExtra("add","产品分类");
                    startActivityForResult(intent,1);//设置Intent和请求码
                    break;
                case R.id.gengduo_2_id:
                    //跳转到厂商界面，获取选择的返回值
                    Intent intent2=new Intent(work_or_error_frist_kactivity.this,Select_more_activity.class);
                    intent2.putExtra("add","品牌");
                    startActivityForResult(intent2,2);
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
        }
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
