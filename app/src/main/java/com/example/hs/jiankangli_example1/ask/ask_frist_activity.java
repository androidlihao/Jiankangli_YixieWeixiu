package com.example.hs.jiankangli_example1.ask;

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
import com.example.hs.jiankangli_example1.common_activity_pacage.Select_more_activity;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import Inter.Globle;
import Inter.get_net_Info;
import bean.firm;
import bean.product;
import bean.question_bean;
import utils.CheckContentTitle;
import utils.Max_integral;
import utils.Statubars;
import utils.give_up_push;

/**
 * Created by 李浩 on 2016/11/4.
 */
public class ask_frist_activity extends AutoLayoutActivity implements get_net_Info{
    private EditText fabu_title_id;
    private EditText product_category_id;
    private EditText et_firm_id;
    private EditText et_jixing_id;
    private EditText et_guanjianzi_id;
    private EditText et_cuowu_id;
    private EditText et_zixitong_id;
    private Button btn_next_id;
    private AutoLinearLayout gengduo_1_id;
    private AutoLinearLayout gengduo_2_id;
    private EditText et_integral_id;
    private product.BodyBean.DataBean productbean;
    private firm.BodyBean.DataBean firmbean;
    private String content_categories_id;
    private String ask_content;
    private String title;
    private String category;
    private String firm;
    private String jixing;
    private String guanjianzi;
    private String integral;
    private String zixitong;
    private String cuowucode;
    private Dialog mDialog;
    private question_bean questionBean;
    private String checkedTitle= Globle.TEST_URL+"/api/qanda/checkQuestionTitle";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_fault_frist_layout);
        new Statubars().setStatubars(this, getWindow(),"zhaose", getResources().getColor(R.color.statue));
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        mDialog = give_up_push.show_Dialog(ask_frist_activity.this,new ask_frist_activity.MyOnListener());
        initView();//初始化界面控件实例
        //设置回显值
        setQuestionBean();
        SetOnClickListener();//设置监听器
    }
    private void initView() {
        findViewById(R.id.sets_back_id).setOnClickListener(new MyOnListener());//设置返回键监听
        Intent intent=getIntent();
        questionBean = (question_bean) intent.getSerializableExtra("question");
        if(questionBean!=null&&questionBean.getBody().getData().getContentcategoriesModels()!=null){
            int a=questionBean.getBody().getData().getContentcategoriesModels().getContentCategoriesId();
            switch (a){
                case 1:
                    ask_content="故障";
                    break;
                case 2:
                    ask_content="工作原理";
                    break;
                case 3:
                    ask_content="错误编码";
                    break;
            }
        }else{
            ask_content = intent.getStringExtra("ask_content");
        }
        TextView content_category_id= (TextView) findViewById(R.id.content_category_id); //发现内容分类标题
        content_category_id.setText(ask_content);
        switch (ask_content){
            case "故障":
                content_categories_id="1";
                break;
            case "工作原理":
                content_categories_id="2";
                this.findViewById(R.id.ll_error_id).setVisibility(View.GONE);
                this.findViewById(R.id.error_xian_id).setVisibility(View.GONE);
                break;
            case "错误编码":
                content_categories_id="3";
                this.findViewById(R.id.ll_error_id).setVisibility(View.GONE);
                this.findViewById(R.id.error_xian_id).setVisibility(View.GONE);
                break;
        }
        fabu_title_id = (EditText) findViewById(R.id.fabu_title_id); //发布的标题，必填项
        product_category_id = (EditText) findViewById(R.id.product_category_id);//产品分类，必填项
        et_firm_id = (EditText) findViewById(R.id.et_firm_id); //厂商，必填项
        et_jixing_id = (EditText) findViewById(R.id.et_jixing_id);//机型，必填项
        et_guanjianzi_id = (EditText) findViewById(R.id.et_guanjianzi_id); //关键字必填项
        et_zixitong_id = (EditText) findViewById(R.id.et_zixitong_id);//子系统，非必选填项
        et_integral_id = (EditText) findViewById(R.id.et_integral_id);//悬赏积分
        btn_next_id = (Button) findViewById(R.id.btn_next_id); //第一个为下一步
        et_cuowu_id = (EditText) findViewById(R.id.et_cuowu_id);//错误编码
        gengduo_1_id = (AutoLinearLayout) findViewById(R.id.gengduo_1_id); //发现界面控件实例
        gengduo_2_id = (AutoLinearLayout) findViewById(R.id.gengduo_2_id);
    }
    private void setQuestionBean() {
        if(questionBean!=null&&questionBean.getBody()!=null&&questionBean.getBody().getData()!=null){
            fabu_title_id.setText(questionBean.getBody().getData().getTitle());
            if(questionBean.getBody().getData().getProductCategoriesModel()!=null){
                product_category_id.setText(questionBean.getBody().getData().getProductCategoriesModel().getName());
            }
            if (questionBean.getBody().getData().getManufacturerModel()!=null){
                et_firm_id.setText(questionBean.getBody().getData().getManufacturerModel().getName());
            }
            et_jixing_id.setText(questionBean.getBody().getData().getModels());
            et_guanjianzi_id.setText(questionBean.getBody().getData().getKeyWord());
            et_cuowu_id.setText(questionBean.getBody().getData().getErrCode());
            et_zixitong_id.setText(questionBean.getBody().getData().getSubsystem());
            et_integral_id.setText(questionBean.getBody().getData().getBountyIntegral()+"");
        }
    }
    private void SetOnClickListener() {
        btn_next_id.setOnClickListener(new MyOnListener());
        gengduo_1_id.setOnClickListener(new MyOnListener());
        gengduo_2_id.setOnClickListener(new MyOnListener());
        new Max_integral().SetTextChange(et_integral_id,getApplicationContext());
    }
    @Override
    public void getinfo(String str) {
        if(str.contains("20010")&&questionBean==null){//并且不是从草稿箱过来的
            Toast.makeText(getApplicationContext(),"标题重复！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(category.trim().isEmpty()){
            Toast.makeText(this, "产品分类不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (firm.trim().isEmpty()){
            Toast.makeText(this, "品牌不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (jixing.trim().isEmpty()){
            Toast.makeText(this, "机型不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (guanjianzi.trim().isEmpty()){
            Toast.makeText(this, "关键字不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        Bundle bundle=new Bundle();
        switch (ask_content){
            case "故障":
                cuowucode = et_cuowu_id.getText().toString();
                if(integral !=null&&!integral.isEmpty()&&Integer.parseInt(integral)>0){
                    //TODO
                    Intent intents=new Intent(ask_frist_activity.this, ask_digest_activity.class);
                    bundle.putString("sign","fault");
                    next_step(intents,bundle);
                }else{
                    Toast.makeText(getApplicationContext(), "悬赏积分必须为正整数", Toast.LENGTH_SHORT).show();
                }
                break;
            case "工作原理":
                if(integral !=null&&!integral.isEmpty()&&Integer.parseInt(integral)>0){
                    Intent intents=new Intent(ask_frist_activity.this, ask_digest_activity.class);
                    bundle.putString("sign","work");
                    next_step(intents,bundle);
                }else{
                    Toast.makeText(getApplicationContext(), "悬赏积分必须为正整数", Toast.LENGTH_SHORT).show();
                }
                break;
            case "错误编码":
                if(integral !=null&&!integral.isEmpty()&&Integer.parseInt(integral)>0){
                    Intent intents=new Intent(ask_frist_activity.this,ask_digest_activity.class);
                    bundle.putString("sign","error");
                    next_step(intents,bundle);
                }else{
                    Toast.makeText(getApplicationContext(), "悬赏积分必须为正整数", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void get_content(){
        title = fabu_title_id.getText().toString();
        category = product_category_id.getText().toString();
        firm = et_firm_id.getText().toString();
        jixing = et_jixing_id.getText().toString();
        guanjianzi = et_guanjianzi_id.getText().toString();
        integral = et_integral_id.getText().toString();
        zixitong = et_zixitong_id.getText().toString();
        cuowucode=et_cuowu_id.getText().toString();
    }
    private class MyOnListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.gengduo_1_id:
                    Intent intent=new Intent(ask_frist_activity.this,Select_more_activity.class);
                    intent.putExtra("add","产品分类");
                    startActivityForResult(intent,1);//设置Intent和请求码
                    break;
                case R.id.gengduo_2_id://跳转到厂商界面，获取选择的返回值
                    Intent intent2=new Intent(ask_frist_activity.this,Select_more_activity.class);
                    intent2.putExtra("add","品牌");
                    startActivityForResult(intent2,2);
                    break;
                case R.id.btn_next_id:
                    get_content();//获取所有的值
                    if(title.trim().isEmpty()){
                        Toast.makeText(getApplicationContext(),"标题不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    CheckContentTitle.checked(title,content_categories_id,ask_frist_activity.this,"ask_frist_activity",checkedTitle);//验证标题重复性
                    break;
                case R.id.tv_cancle_id:
                    mDialog.dismiss();
                    break;
                case R.id.tv_close_id:
                    mDialog.dismiss();
                    finish();
                    break;
                case R.id.sets_back_id:
                    close();
                    break;
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        get_content();
        if (keyCode==KeyEvent.KEYCODE_BACK&&(!title.trim().isEmpty()||!category.trim().isEmpty()
                ||!firm.trim().isEmpty()||!jixing.trim().isEmpty()||!integral.trim().isEmpty()
                ||!guanjianzi.trim().isEmpty()||!cuowucode.trim().isEmpty()||!zixitong.trim().isEmpty())){
                mDialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }
    public void close(){
        get_content();
        if(title.trim().isEmpty()&&category.trim().isEmpty()
                &&firm.trim().isEmpty()&&jixing.trim().isEmpty()&&integral.trim().isEmpty()
                &&guanjianzi.trim().isEmpty()&&cuowucode.trim().isEmpty()&&zixitong.trim().isEmpty()){
            finish();
            return;
        }
        mDialog.show();
    }
    private void next_step(Intent intents,Bundle bundle) {
        //获取所有的选项，然后开始下一步的操作，将本页的数据传输给下一步

        bundle.putString("title",title);//标题
        if(productbean!=null){//有这个分类存在
            bundle.putString("product_categories_id",String.valueOf(productbean.getProductCategoriesId()));
        }
        //产品分类名称
        bundle.putString("product_categories_name",category);
        //厂商ID
        if(firmbean!=null){
            bundle.putString("manufacturer_id",String.valueOf(firmbean.getManufacturerId()));
        }
        bundle.putString("content_categories_id",content_categories_id);
        //厂商名称
        bundle.putString("manufacturer_name",firm);
        //机型
        bundle.putString("models",jixing);
        //关键字
        bundle.putString("key_word",guanjianzi);
        //错误编码
        bundle.putString("err_code",cuowucode);
        //悬赏积分
        bundle.putString("bounty_integral",integral);
        //子系统
        bundle.putString("subsystem",zixitong);
        //标签
        bundle.putString("ask_content",ask_content);
        if (questionBean!=null){
            intents.putExtra("question",questionBean);
        }
        //设置Bundle
        intents.putExtra("ask",bundle);
        //跳转到指定的
        startActivity(intents);
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
            et_firm_id.setFocusable(true);
            et_firm_id.setFocusableInTouchMode(true);
            et_firm_id.requestFocus();
        }
    }
}
