package com.example.hs.jiankangli_example1.push_knowledge_package;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
public class fault_frist_activity extends AutoLayoutActivity implements get_net_Info {
    private EditText fabu_title_id;
    private EditText product_category_id;
    private EditText et_firm_id;
    private EditText et_jixing_id;
    private EditText et_guanjianzi_id;
    private EditText et_cuowu_id;
    private EditText et_zixitong_id;
    private Button btn_next_id;
    private Button btn_save_id;
    private AutoLinearLayout gengduo_1_id;
    private AutoLinearLayout gengduo_2_id;
    private product.BodyBean.DataBean productbean;
    private firm.BodyBean.DataBean firmbean;
    private String results;
    private int contentid;
    private String title;
    private String category;
    private String firm;
    private String jixing;
    private String guanjianzi;
    private String cuowucode;
    private String zixitong;
    private Dialog mDialog;
    private String URL;
    private String checkedTitle= Globle.TEST_URL+"/api/knowledge/checkcontenttitle";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(),"zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.guzhang_activity);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);//将当前页面添加到集合中
        mDialog = give_up_push.show_Dialog(fault_frist_activity.this,new MyOnListener());
        //初始化界面控件实例
        initView();
        //获取到草稿页面传过来的值
        results = getIntent().getStringExtra("my_draft");
        if(results !=null&&!results.isEmpty()){
            My_draft my_draft=com.alibaba.fastjson.JSONObject.parseObject(results,My_draft.class);
            if(my_draft.getBody()!=null){
                if(my_draft.getBody().getData()!=null){
                    fabu_title_id.setText(my_draft.getBody().getData().getTitle());
                    et_jixing_id.setText(my_draft.getBody().getData().getModels());
                    et_guanjianzi_id.setText(my_draft.getBody().getData().getKeyWord());
                    et_cuowu_id.setText(my_draft.getBody().getData().getErrCode());
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
        //设置监听器
        setOnClickListener();
    }
    private void setOnClickListener() {
        btn_next_id.setOnClickListener(new MyOnListener());
        btn_save_id.setOnClickListener(new MyOnListener());
        product_category_id.setOnClickListener(new MyOnListener());
        et_firm_id.setOnClickListener(new MyOnListener());
        gengduo_1_id.setOnClickListener(new MyOnListener());
        gengduo_2_id.setOnClickListener(new MyOnListener());
    }
    private void get_content(){
        title = fabu_title_id.getText().toString();
        category = product_category_id.getText().toString();
        firm = et_firm_id.getText().toString();
        jixing = et_jixing_id.getText().toString();
        guanjianzi = et_guanjianzi_id.getText().toString();
        cuowucode = et_cuowu_id.getText().toString();
        zixitong = et_zixitong_id.getText().toString();
    }
    private void initView() {
        findViewById(R.id.sets_back_id).setOnClickListener(new MyOnListener());
        fabu_title_id = (EditText) findViewById(R.id.fabu_title_id);//发布的标题，必填项
        product_category_id = (EditText) findViewById(R.id.product_category_id); //产品分类，必填项
        et_firm_id = (EditText) findViewById(R.id.et_firm_id);//厂商，必填项
        et_jixing_id = (EditText) findViewById(R.id.et_jixing_id); //机型，必填项
        et_guanjianzi_id = (EditText) findViewById(R.id.et_guanjianzi_id);//关键字必填项
        et_cuowu_id = (EditText) findViewById(R.id.et_cuowu_id);//错误编码必填项
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
    @Override
    public void getinfo(String str) {
        if(str.contains("20010")&&results==null){//并且不是从草稿箱过来的
            Toast.makeText(fault_frist_activity.this, "标题重复！", Toast.LENGTH_SHORT).show();
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
        Intent intent=new Intent(fault_frist_activity.this,push_knowledge_digest_activity.class);
        if(results!=null&&!results.isEmpty()){//如果是草稿界面过来的
            intent.putExtra("my_draft",results);
        }
        Bundle bundle=new Bundle();
        bundle.putString("title",fabu_title_id.getText().toString());//标题
        if(productbean!=null){//有这个分类存在
            bundle.putString("product_categories_id", String.valueOf(productbean.getProductCategoriesId()));
        }
        bundle.putString("content_categories_name","故障描述");
        bundle.putString("product_categories_name",category);
        if(firmbean!=null){
            bundle.putString("manufacturer_id", String.valueOf(firmbean.getManufacturerId()));
        }
        bundle.putString("content_categories_id","1");//当前页面为故障描述，那么当前页面的内容ID为1
        bundle.putString("manufacturer_name",firm);
        bundle.putString("models",jixing);
        bundle.putString("key_word",guanjianzi);
        bundle.putString("err_code",cuowucode);
        bundle.putString("subsystem",zixitong);
        bundle.putString("tag_type","故障维修");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public class MyOnListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_next_id:
                    get_content();
                    if(title.trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "标题为必填项，不能为空！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    CheckContentTitle.checked(title,"1",fault_frist_activity.this,"fault_frist_activity",checkedTitle);
                    break;
                case R.id.btn_save_id://保存草稿按钮
                    get_content();
                    if(title.trim().isEmpty()&&category.trim().isEmpty()
                            &&firm.trim().isEmpty()&&jixing.trim().isEmpty()
                            &&guanjianzi.trim().isEmpty()&&cuowucode.trim().isEmpty()&&zixitong.trim().isEmpty()){
                        Toast.makeText(fault_frist_activity.this, "至少填写一项才能保存！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    JSONObject jsonObject=new JSONObject();
                    if(results!=null&&!results.isEmpty()){//如果是从草稿箱过来的
                        URL=Globle.TEST_URL+"/api/knowledge/editcontent";//编辑草稿
                        try {
                            jsonObject.put("content_id",contentid);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        URL=Globle.TEST_URL+"/api/knowledge/savecontent";//保存草稿
                    }
                    try {
                        if(productbean!=null){
                            jsonObject.put("product_categories_id", String.valueOf(productbean.getProductCategoriesId()));
                        }
                        if(firmbean!=null){
                            jsonObject.put("manufacturer_id",String.valueOf(firmbean.getManufacturerId()));
                        }
                        jsonObject.put("title",title);
                        jsonObject.put("manufacturer_name",firm);
                        jsonObject.put("product_categories_name",category);
                        jsonObject.put("models",jixing);
                        jsonObject.put("key_word",guanjianzi);
                        jsonObject.put("err_code",cuowucode);
                        jsonObject.put("is_draft",1);
                        jsonObject.put("subsystem",zixitong);
                        jsonObject.put("member_id",new JavaScriptObject(getApplicationContext()).getMemberid(""));//会员ID
                        jsonObject.put("content_categories_id","1");
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                    RequestNet.requestServer(jsonObject,URL,fault_frist_activity.this,"保存草稿");
                    break;
                case R.id.gengduo_1_id:
                    //跳转到产品分类的界面，获取返回值
                    Intent product_intent=new Intent(fault_frist_activity.this, Select_more_activity.class);
                    product_intent.putExtra("add","产品分类");
                    startActivityForResult(product_intent,1);//设置Intent和请求码
                    break;
                case R.id.gengduo_2_id:
                    //跳转到厂商界面，获取选择的返回值
                    Intent intent2=new Intent(fault_frist_activity.this,Select_more_activity.class);
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        get_content();
        if (keyCode==KeyEvent.KEYCODE_BACK&&(!title.trim().isEmpty()||!category.trim().isEmpty()
                ||!firm.trim().isEmpty()||!jixing.trim().isEmpty()
                ||!guanjianzi.trim().isEmpty()||!cuowucode.trim().isEmpty()||!zixitong.trim().isEmpty())){
                mDialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }
    public void close(){
        get_content();
        if(title.trim().isEmpty()&&category.trim().isEmpty()
                &&firm.trim().isEmpty()&&jixing.trim().isEmpty()
                &&guanjianzi.trim().isEmpty()&&cuowucode.trim().isEmpty()&&zixitong.trim().isEmpty()){
            finish();
            return;
        }
        mDialog.show();
    }
}
