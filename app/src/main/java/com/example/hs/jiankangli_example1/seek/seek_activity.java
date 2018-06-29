package com.example.hs.jiankangli_example1.seek;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.My_activity;
import com.example.hs.jiankangli_example1.PartsActivity;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import AsyncTask.query_AsyncTask;
import Inter.get_net_Info;
import Inter.sendInfo;
import bean.Content_categories;
import bean.firm;
import bean.get_citys_bean;
import bean.product;
import Inter.Globle;
import AsyncTask.ResquestHttp_AsyncTask;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/11/1.
 */
public class seek_activity extends AutoLayoutActivity implements sendInfo{

    private RadioGroup rg_firm1_id;
    private RadioGroup rg_firm2_id;
    private final static String productcategories_URL= Globle.TEST_URL+"/api/knowledge/productcategorieslist";//产品列表分类接口
    private final static String firm_URL=Globle.TEST_URL+"/api/knowledge/manufacturerlist";//产品列表分类接口
    private final static String Content_URL=Globle.TEST_URL+"/api/knowledge/categorieslist";
    private final static String getCityInfoByLeveType_URl=Globle.TEST_URL+"/api/base/getCityInfoByLeveType";
    private List<firm.BodyBean.DataBean> datas;
    private RadioGroup rg_product1_id;
    private RadioGroup rg_product2_id;
    private List<product.BodyBean.DataBean> data;
    private RadioGroup rg_content1_id;
    private RadioGroup rg_content2_id;
    private List<Content_categories.BodyBean.DataBean> datacontent;
    private Button btn_seek_id;
    private String tag;
    private EditText et_keyword_id;
    private RadioGroup rg_zwx_id;
    private LinkedList<String> linkedList= new LinkedList<>();
    private View ll_city_id;
    private RadioGroup rg_city1_id;
    private RadioGroup rg_city2_id;
    private List<get_citys_bean.BodyBean.DataBean> data1;
    private RadioButton btn_p_id;
    private View ll_content_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.seek_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        tag = getIntent().getStringExtra("tags");
        linkedList.add(productcategories_URL);
        linkedList.add(firm_URL);
        linkedList.add(Content_URL);
        initView();
    }
    private void initView() {
        ll_city_id = findViewById(R.id.ll_city_id);
        ll_content_id = findViewById(R.id.ll_neirong_id);
        rg_zwx_id = (RadioGroup)findViewById(R.id.rg_zwx_id);
        rg_firm1_id = (RadioGroup) findViewById(R.id.rg_firm1_id);
        rg_firm2_id = (RadioGroup) findViewById(R.id.rg_firm2_id);
        rg_product1_id = (RadioGroup) findViewById(R.id.rg_product1_id);
        rg_product2_id = (RadioGroup) findViewById(R.id.rg_product2_id);
        rg_content1_id = (RadioGroup) findViewById(R.id.rg_content1_id);
        rg_content2_id = (RadioGroup) findViewById(R.id.rg_content2_id);
        rg_city1_id = (RadioGroup) findViewById(R.id.rg_city1_id);
        rg_city2_id = (RadioGroup) findViewById(R.id.rg_city2_id);
        btn_p_id = (RadioButton) findViewById(R.id.btn_p_id);
        rg_zwx_id.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int checkedId) {
                switch (checkedId){
                   case  R.id.btn_z_id:
                       btn_p_id.setTextColor(getResources().getColor(R.color.black));
                       clear();
                       ll_city_id.setVisibility(View.GONE);
                       ll_content_id.setVisibility(View.VISIBLE);
                       if (linkedList.contains(getCityInfoByLeveType_URl)){
                           linkedList.removeLast();
                       }
                       new ResquestHttp_AsyncTask(linkedList,seek_activity.this,"1").execute();
                       break;
                    case  R.id.btn_w_id:
                        btn_p_id.setTextColor(getResources().getColor(R.color.black));
                        clear();
                        if (linkedList.contains(getCityInfoByLeveType_URl)){
                            linkedList.removeLast();
                        }
                        ll_city_id.setVisibility(View.GONE);
                        ll_content_id.setVisibility(View.VISIBLE);
                        new ResquestHttp_AsyncTask(linkedList,seek_activity.this,"2").execute();
                        break;
                    case  R.id.btn_x_id:
                        btn_p_id.setTextColor(getResources().getColor(R.color.black));
                        clear();
                        ll_city_id.setVisibility(View.VISIBLE);
                        ll_content_id.setVisibility(View.VISIBLE);
                        if (!linkedList.contains(getCityInfoByLeveType_URl)){
                            linkedList.addLast(getCityInfoByLeveType_URl);
                        }
                        new ResquestHttp_AsyncTask(linkedList,seek_activity.this,"4").execute();
                        rg_content1_id.check(rg_content1_id.getChildAt(0).getId());
                        break;
                    case R.id.btn_p_id:
                        btn_p_id.setTextColor(getResources().getColor(R.color.white));
                        //如果选择了配件
                        ll_city_id.setVisibility(View.GONE);
                        ll_content_id.setVisibility(View.GONE);
                        if (!linkedList.contains(getCityInfoByLeveType_URl)){
                            linkedList.addLast(getCityInfoByLeveType_URl);
                        }
                        new ResquestHttp_AsyncTask(linkedList,seek_activity.this,"3").execute();
                        break;
                }
            }
        });
        if(tag.equals("1")){
            rg_zwx_id.check(R.id.btn_z_id);//知
        }if(tag.equals("2")){
            rg_zwx_id.check(R.id.btn_w_id);//问
        }if(tag.equals("4")){
            rg_zwx_id.check(R.id.btn_x_id);//信
        }else if (tag.equals("3")){
            rg_zwx_id.check(R.id.btn_p_id);//配件
        }
        et_keyword_id = (EditText) findViewById(R.id.et_keyword_id);
        btn_seek_id = (Button) findViewById(R.id.btn_seek_id);
        TextView tv_goback_id= (TextView) findViewById(R.id.tv_goback_id);
        tv_goback_id.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //开始执行搜索操作
        btn_seek_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取数据，然后将数据提交给js界面
                JSONObject js=new JSONObject();
                JSONObject jsonObject=new JSONObject();
                try {
                    //根据选择的按钮的Id
                    switch (rg_zwx_id.getCheckedRadioButtonId()){
                        case R.id.btn_z_id:
                            jsonObject.put("operationId","1");
                            break;
                        case R.id.btn_w_id:
                            jsonObject.put("operationId","2");
                            break;
                        case R.id.btn_x_id:
                            jsonObject.put("operationId","4");
                            break;
                    }
                    js.put("data",jsonObject);
                    if(rg_firm1_id.getCheckedRadioButtonId()!=-1){//选择了
                        RadioButton radioButton= (RadioButton) rg_firm1_id.findViewById(rg_firm1_id.getCheckedRadioButtonId());
                        for(firm.BodyBean.DataBean fm:datas){
                            if(fm.getName().equals(radioButton.getText().toString())){
                                jsonObject.put("manufacturer_id",fm.getManufacturerId());
                            }
                        }
                    }else if(rg_firm2_id.getCheckedRadioButtonId()!=-1){
                        RadioButton radioButton= (RadioButton) rg_firm2_id.findViewById(rg_firm2_id.getCheckedRadioButtonId());
                        for(firm.BodyBean.DataBean fm:datas){
                            if(fm.getName().equals(radioButton.getText().toString())){
                                jsonObject.put("manufacturer_id",fm.getManufacturerId());
                            }
                        }
                    }
                    if(rg_product1_id.getCheckedRadioButtonId()!=-1){
                        RadioButton radioButton= (RadioButton) rg_product1_id.findViewById(rg_product1_id.getCheckedRadioButtonId());
                        for(product.BodyBean.DataBean fm:data){
                            if(fm.getName().equals(radioButton.getText().toString())){
                                jsonObject.put("product_categories_id",fm.getProductCategoriesId());
                            }
                        }
                    }else if(rg_product2_id.getCheckedRadioButtonId()!=-1){
                        RadioButton radioButton= (RadioButton) rg_product2_id.findViewById(rg_product2_id.getCheckedRadioButtonId());
                        for(product.BodyBean.DataBean fm:data){
                            if(fm.getName().equals(radioButton.getText().toString())){
                                jsonObject.put("product_categories_id",fm.getProductCategoriesId());
                            }
                        }
                    }
                    if(rg_content1_id.getCheckedRadioButtonId()!=-1){
                        RadioButton radioButton= (RadioButton) rg_content1_id.findViewById(rg_content1_id.getCheckedRadioButtonId());
                        for(Content_categories.BodyBean.DataBean fm:datacontent){
                            if(fm.getName().equals(radioButton.getText().toString())){
                                jsonObject.put("content_categories_id",fm.getContentCategoriesId());
                            }
                        }
                    }else if(rg_content2_id.getCheckedRadioButtonId()!=-1){
                        RadioButton radioButton= (RadioButton) rg_content2_id.findViewById(rg_content2_id.getCheckedRadioButtonId());
                        for(Content_categories.BodyBean.DataBean fm:datacontent){
                            if(fm.getName().equals(radioButton.getText().toString())){
                                jsonObject.put("content_categories_id",fm.getContentCategoriesId());
                            }
                        }
                    }
                    if(ll_city_id.getVisibility()==View.VISIBLE){
                        if(rg_city1_id.getCheckedRadioButtonId()!=-1){
                            RadioButton radioButton= (RadioButton) rg_city1_id.findViewById(rg_city1_id.getCheckedRadioButtonId());
                            for(get_citys_bean.BodyBean.DataBean fm:data1){
                                if(fm.getName().equals(radioButton.getText().toString())){
                                    jsonObject.put("province_id",fm.getCityId());
                                }
                            }
                        }else if(rg_city2_id.getCheckedRadioButtonId()!=-1){
                            RadioButton radioButton= (RadioButton) rg_city2_id.findViewById(rg_city2_id.getCheckedRadioButtonId());
                            for(get_citys_bean.BodyBean.DataBean fm:data1){
                                if(fm.getName().equals(radioButton.getText().toString())){
                                    jsonObject.put("province_id",fm.getCityId());
                                }
                            }
                        }
                    }
                    if(et_keyword_id.getText().toString()!=null&&!et_keyword_id.getText().toString().isEmpty()){
                        jsonObject.put("key_word",et_keyword_id.getText().toString());
                    }else{
                        jsonObject.put("key_word","");
                    }
                    if (rg_zwx_id.getCheckedRadioButtonId()==R.id.btn_p_id){
                        Intent intents=new Intent(seek_activity.this,PartsActivity.class);
                         if (jsonObject.toString().contains("manufacturer_id")){
                             Log.i("TAG", "jsonObjdasect: "+jsonObject.getString("manufacturer_id"));
                             intents.putExtra("manufacturer_id",jsonObject.getString("manufacturer_id"));
                         }
                         if (jsonObject.toString().contains("product_categories_id")){
                             intents.putExtra("product_categories_id",jsonObject.getString("product_categories_id"));
                         }
                        intents.putExtra("name",jsonObject.getString("key_word"));
                        startActivity(intents);
                        return;
                    }else{
                        Intent intent=new Intent(seek_activity.this, My_activity.class);
                        intent.putExtra("seekJson",jsonObject.toString());
                        intent.putExtra("my","seek_result");
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void clear(){
        rg_firm2_id.clearCheck();
        rg_firm1_id.clearCheck();
        rg_city1_id.clearCheck();
        rg_city2_id.clearCheck();
        rg_content1_id.clearCheck();
        rg_content2_id.clearCheck();
        rg_product1_id.clearCheck();
        rg_product2_id.clearCheck();
    }
    private void clearcheck(final RadioGroup r1, final RadioGroup r2, final String string) {
        for(int i=0;i<r1.getChildCount();i++){
            RadioButton button= (RadioButton) r1.getChildAt(i);
            button.setEnabled(true);
            if(button.getText().toString()!=null&&!button.getText().toString().trim().isEmpty()){
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RadioButton radioButton= (RadioButton) view;
                        if(r2.getCheckedRadioButtonId()!=-1&&radioButton.getText().toString()!=null&&!radioButton.getText().toString().isEmpty()){
                            r2.clearCheck();
                        }
                    }
                });
            }else{
                button.setEnabled(false);
            }
         }
        for(int i=0;i<r2.getChildCount();i++){
            Button button= (Button) r2.getChildAt(i);
            button.setEnabled(true);
            if(button.getText().toString()!=null&&!button.getText().toString().trim().isEmpty()){//当按钮有值的时候
                final int finalI = i;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button radioButton= (Button) view;
                        if(r1.getCheckedRadioButtonId()!=-1&& finalI!=2){//如果r1有选中的
                            r1.clearCheck();
                        }else if(finalI==2&&radioButton.getText().toString().equals("其他")){
                            deleteData(string,r1,r2);
                        }
                    }
                });
            }else{
                button.setEnabled(false);
            }
        }
    }
    private void deleteData(String string, RadioGroup r1, RadioGroup r2) {
            Intent intent=new Intent(seek_activity.this,seek_more_activity.class);
            Bundle bundle=new Bundle();
            LinkedList<String> list=new LinkedList<>();
            for(int j=0;j<r1.getChildCount();j++){
                RadioButton rb= (RadioButton) r1.getChildAt(j);
                list.add(rb.getText().toString());
            }
            for(int j=0;j<r2.getChildCount()-1;j++){
                RadioButton rb= (RadioButton) r2.getChildAt(j);
                list.add(rb.getText().toString());
            }
            if(string.equals("firm")){
                List<firm.BodyBean.DataBean> da=new LinkedList<>();
                for(int i=0;i<datas.size();i++){
                    if(!list.contains(datas.get(i).getName())){
                        da.add(datas.get(i));
                    }
                }
                bundle.putSerializable("firm",(Serializable)da);
                intent.putExtras(bundle);
                intent.putExtra("title","添加厂商");
                startActivityForResult(intent,5);

            }else if(string.equals("product")){
                List<product.BodyBean.DataBean> da=new LinkedList<>();
                for(int i=0;i<data.size();i++){
                    if(!list.contains(data.get(i).getName())){
                        da.add(data.get(i));
                    }
                }
                bundle.putSerializable("product",(Serializable)da);
                intent.putExtras(bundle);
                intent.putExtra("title","添加产品");
                startActivityForResult(intent,4);
            }else if(string.equals("content")){
                List<Content_categories.BodyBean.DataBean> da=new LinkedList<>();
                for(int i=0;i<datacontent.size();i++){
                    if(!list.contains(datacontent.get(i).getName())){
                        da.add(datacontent.get(i));
                    }
                }
                bundle.putSerializable("content",(Serializable)da);
                intent.putExtras(bundle);
                intent.putExtra("title","内容");
                startActivityForResult(intent,3);
            }else if(string.equals("city")){
                List<get_citys_bean.BodyBean.DataBean> citysData=new LinkedList<>();
                for(int i=0;i<data1.size();i++){
                    if(!list.contains(data1.get(i).getName())){
                        citysData.add(data1.get(i));
                    }
                }
                bundle.putSerializable("City",(Serializable)citysData);
                intent.putExtras(bundle);
                intent.putExtra("title","地区");
                startActivityForResult(intent,2);
            }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==resultCode&&requestCode==5){
            String biaoqian=data.getStringExtra("biaoqian");
            RadioButton radioButton= (RadioButton) rg_firm2_id.getChildAt(1);
            radioButton.setText(biaoqian);
            if(rg_firm1_id.getCheckedRadioButtonId()!=-1){//其他没有清楚功能
                rg_firm1_id.clearCheck();
            }
            rg_firm2_id.check(rg_firm2_id.getChildAt(1).getId());
        }else if(requestCode==4&&resultCode==5){
            String biaoqian=data.getStringExtra("biaoqian");
            RadioButton radioButton= (RadioButton) rg_product2_id.getChildAt(1);
            radioButton.setText(biaoqian);
            if(rg_product1_id.getCheckedRadioButtonId()!=-1){
                rg_product1_id.clearCheck();
            }
            rg_product2_id.check(rg_product2_id.getChildAt(1).getId());
        }else if(requestCode==3&&resultCode==5){
            String biaoqian=data.getStringExtra("biaoqian");
            RadioButton radioButton= (RadioButton) rg_content2_id.getChildAt(1);
            radioButton.setText(biaoqian);
            if(rg_content1_id.getCheckedRadioButtonId()!=-1){
                rg_content1_id.clearCheck();
            }
            rg_content2_id.check(rg_content2_id.getChildAt(1).getId());
        }else if(requestCode==2&&resultCode==5){
            String biaoqian=data.getStringExtra("biaoqian");
            RadioButton radioButton= (RadioButton) rg_city2_id.getChildAt(1);
            radioButton.setText(biaoqian);
            if(rg_city1_id.getCheckedRadioButtonId()!=-1){
                rg_city1_id.clearCheck();
            }
            rg_city2_id.check(rg_city2_id.getChildAt(1).getId());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void sendproduct(product pd) {//产品
         data = pd.getBody().getData();
        setDatas(rg_product1_id,rg_product2_id,data,"product");
        clearcheck(rg_product1_id,rg_product2_id,"product");//TODO
    }
    @Override//获取传过来的数据
    public void sendfirm(firm fm) {//厂商对象
        //fm的值变化了
        datas = fm.getBody().getData();
        setDatas(rg_firm1_id,rg_firm2_id,datas,"firm");
        clearcheck(rg_firm1_id,rg_firm2_id,"firm");//TODO
    }
    @Override
    public void sendcontent(Content_categories cc) {//分类
        datacontent = cc.getBody().getData();
        setDatas(rg_content1_id,rg_content2_id,datacontent,"content");
        clearcheck(rg_content1_id,rg_content2_id,"content");//TODO
    }

    @Override
    public void sendCityInfo(get_citys_bean citys_bean) {
        data1 = citys_bean.getBody().getData();
        setDatas(rg_city1_id,rg_city2_id,data1,"city");
        clearcheck(rg_city1_id,rg_city2_id,"city");//TODO
    }


    private void setDatas(RadioGroup r1, RadioGroup r2,List datas,String str) {
        if(str.equals("firm")){
            List<firm.BodyBean.DataBean> httpdata=datas;
            for(int i=0;i<3;i++){
                Button rb1= (Button)r1.getChildAt(i);
                Button rb2= (Button)r2.getChildAt(i);
                rb1.setText("");
                rb2.setText("");
            }
            if(httpdata.size()>5){//如果当前的分类大于5
                for(int i=0;i<3;i++){
                    RadioButton rb= (RadioButton)r1.getChildAt(i);
                    rb.setText(httpdata.get(i).getName());
                }
                for(int i=0;i<3;i++){
                    Button rb= (Button) r2.getChildAt(i);
                    if(i==2){
                        rb.setText("其他");
                    }else{
                        rb.setText(httpdata.get(i+3).getName());
                    }
                }
            }
            if(httpdata.size()==4||httpdata.size()==5){
                for(int i=0;i<3;i++){
                    RadioButton rb= (RadioButton)r1.getChildAt(i);
                    rb.setText(httpdata.get(i).getName());
                }
                if(httpdata.size()==5){
                    for(int i=0;i<2;i++){
                        Button rb= (Button) r2.getChildAt(i);
                        rb.setText(httpdata.get(i+3).getName());
                    }
                }else if(httpdata.size()==4){
                    Button rb= (Button) r2.getChildAt(0);
                    rb.setText(httpdata.get(3).getName());
                }
            }
            if(httpdata.size()<4){
                switch (httpdata.size()){
                    case 1:
                        Button rb= (Button) r1.getChildAt(0);
                        rb.setText(httpdata.get(0).getName());
                        break;
                    case 2:
                        for(int i=0;i<2;i++){
                            Button rb1= (Button) r1.getChildAt(i);
                            rb1.setText(httpdata.get(i).getName());
                        }
                        break;
                    case 3:
                        for(int i=0;i<3;i++){
                            Button rb1= (Button) r1.getChildAt(i);
                            rb1.setText(httpdata.get(i).getName());
                        }
                        break;
                }
            }
        }else if(str.equals("product")){
            List<product.BodyBean.DataBean> httpdata=datas;
            for(int i=0;i<3;i++){
                Button rb1= (Button)r1.getChildAt(i);
                Button rb2= (Button)r2.getChildAt(i);
                rb1.setText("");
                rb2.setText("");
            }
            if(httpdata.size()>5){//如果当前的分类大于5
                for(int i=0;i<3;i++){
                    RadioButton rb= (RadioButton)r1.getChildAt(i);
                    rb.setText(httpdata.get(i).getName());
                }
                for(int i=0;i<3;i++){
                    Button rb= (Button) r2.getChildAt(i);
                    if(i==2){
                        rb.setText("其他");
                    }else{
                        rb.setText(httpdata.get(i+3).getName());
                    }
                }
            }
            if(httpdata.size()==4||httpdata.size()==5){
                for(int i=0;i<3;i++){
                    RadioButton rb= (RadioButton)r1.getChildAt(i);
                    rb.setText(httpdata.get(i).getName());
                }
                if(httpdata.size()==5){
                    for(int i=0;i<2;i++){
                        Button rb= (Button) r2.getChildAt(i);
                        rb.setText(httpdata.get(i+3).getName());
                    }
                }else if(httpdata.size()==4){
                    Button rb= (Button) r2.getChildAt(0);
                    rb.setText(httpdata.get(3).getName());
                }
            }
            if(httpdata.size()<4){
                switch (httpdata.size()){
                    case 1:
                        Button rb= (Button) r1.getChildAt(0);
                        rb.setText(httpdata.get(0).getName());
                        break;
                    case 2:
                        for(int i=0;i<2;i++){
                            Button rb1= (Button) r1.getChildAt(i);
                            rb1.setText(httpdata.get(i).getName());
                        }
                        break;
                    case 3:
                        for(int i=0;i<3;i++){
                            Button rb1= (Button) r1.getChildAt(i);
                            rb1.setText(httpdata.get(i).getName());
                        }
                        break;
                }
            }
        }else if(str.equals("content")){
            List<Content_categories.BodyBean.DataBean> httpdata=datas;
            for(int i=0;i<3;i++){
                Button rb1= (Button)r1.getChildAt(i);
                Button rb2= (Button)r2.getChildAt(i);
                rb1.setText("");
                rb2.setText("");
            }
            if(httpdata.size()>5){//如果当前的分类大于5
                for(int i=0;i<3;i++){
                    RadioButton rb= (RadioButton)r1.getChildAt(i);
                    rb.setText(httpdata.get(i).getName());
                }
                for(int i=0;i<3;i++){
                    Button rb= (Button) r2.getChildAt(i);
                    if(i==2){
                        rb.setText("其他");
                    }else{
                        rb.setText(httpdata.get(i+3).getName());
                    }
                }
            }
            if(httpdata.size()==4||httpdata.size()==5){
                for(int i=0;i<3;i++){
                    RadioButton rb= (RadioButton)r1.getChildAt(i);
                    rb.setText(httpdata.get(i).getName());
                }
                if(httpdata.size()==5){
                    for(int i=0;i<2;i++){
                        Button rb= (Button) r2.getChildAt(i);
                        rb.setText(httpdata.get(i+3).getName());
                    }
                }else if(httpdata.size()==4){
                    Button rb= (Button) r2.getChildAt(0);
                    rb.setText(httpdata.get(3).getName());
                }
            }
            if(httpdata.size()<4){
               switch (httpdata.size()){
                   case 1:
                       Button rb= (Button) r1.getChildAt(0);
                       rb.setText(httpdata.get(0).getName());
                       break;
                   case 2:
                       for(int i=0;i<2;i++){
                           Button rb1= (Button) r1.getChildAt(i);
                           rb1.setText(httpdata.get(i).getName());
                       }
                       break;
                   case 3:
                       for(int i=0;i<3;i++){
                           Button rb1= (Button) r1.getChildAt(i);
                           rb1.setText(httpdata.get(i).getName());
                       }
                       break;
               }
            }
        }else if(str.equals("city")){
            List<get_citys_bean.BodyBean.DataBean> httpdata=datas;
            Log.i("TAG", "setDatas: "+httpdata.size());
            for(int i=0;i<3;i++){
                Button rb1= (Button)r1.getChildAt(i);
                Button rb2= (Button)r2.getChildAt(i);
                rb1.setText("");
                rb2.setText("");
            }
            if(httpdata.size()>5){//如果当前的分类大于5
                for(int i=0;i<3;i++){
                    RadioButton rb= (RadioButton)r1.getChildAt(i);
                    rb.setText(httpdata.get(i).getName());
                }
                for(int i=0;i<3;i++){
                    Button rb= (Button) r2.getChildAt(i);
                    if(i==2){
                        rb.setText("其他");
                    }else{
                        rb.setText(httpdata.get(i+3).getName());
                    }
                }
            }
            if(httpdata.size()==4||httpdata.size()==5){
                for(int i=0;i<3;i++){
                    RadioButton rb= (RadioButton)r1.getChildAt(i);
                    rb.setText(httpdata.get(i).getName());
                }
                if(httpdata.size()==5){
                    for(int i=0;i<2;i++){
                        Button rb= (Button) r2.getChildAt(i);
                        rb.setText(httpdata.get(i+3).getName());
                    }
                }else if(httpdata.size()==4){
                    Button rb= (Button) r2.getChildAt(0);
                    rb.setText(httpdata.get(3).getName());
                }
            }
            if(httpdata.size()<4){
                switch (httpdata.size()){
                    case 1:
                        Button rb= (Button) r1.getChildAt(0);
                        rb.setText(httpdata.get(0).getName());
                        break;
                    case 2:
                        for(int i=0;i<2;i++){
                            Button rb1= (Button) r1.getChildAt(i);
                            rb1.setText(httpdata.get(i).getName());
                        }
                        break;
                    case 3:
                        for(int i=0;i<3;i++){
                            Button rb1= (Button) r1.getChildAt(i);
                            rb1.setText(httpdata.get(i).getName());
                        }
                        break;
                }
            }
        }
    }
}
