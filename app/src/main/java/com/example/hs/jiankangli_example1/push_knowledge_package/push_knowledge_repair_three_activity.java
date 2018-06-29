package com.example.hs.jiankangli_example1.push_knowledge_package;

import android.content.Intent;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import AsyncTask.UoLoadAsyncTask;
import AsyncTask.delete_AsyncTask;
import Inter.getPath_Inter;
import bean.My_draft;
import bean.Pic_bean;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import Inter.Globle;
import utils.BundleUtils;
import utils.Common_utils;
import utils.PicPathToJson;
import utils.Pic_MultiImageSelector_util;
import utils.PreView;
import utils.RequestNet;
import utils.Statubars;
import utils.ninelayout_Adapter;

/**
 * Created by 李浩 on 2016/9/27.
 */
public class push_knowledge_repair_three_activity extends AutoLayoutActivity implements View.OnClickListener, getPath_Inter {

    private GridView gv_id;
    private Button btn_next_id;
    private EditText et_jiejuebuzou_id;
    private Bundle bundle;
    private Button btn_save_id;
    private TextView queren_id;
    private int contentid;
    private String results;
    private LinkedList<LinkedList<String>> list=new LinkedList<>();//图片集合
    private ninelayout_Adapter adapter;
    private  ArrayList<String> deleteList;
    private View sets_back_id;
    private View tv_priview_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.jieguo_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);//将当前页面添加到集合中
        bundle=getIntent().getExtras();
        deleteList=bundle.getStringArrayList("deleteArray");
        results = getIntent().getStringExtra("my_draft");
        initView();
        Pic_bean.answer_second_list=null;
        Pic_bean.answer_second_pic=true;//可以传递
        setOnClickListener();
        if(!TextUtils.isEmpty(results)){//如果是从我的草稿页跳转过来的
            My_draft my_draft=com.alibaba.fastjson.JSONObject.parseObject(results, My_draft.class);
            if(my_draft.getBody()!=null&&my_draft.getBody().getData()!=null){
                    et_jiejuebuzou_id.setText(my_draft.getBody().getData().getStepsResolve());//得到故障描述
                    contentid = my_draft.getBody().getData().getContentId();
                    if(my_draft.getBody().getData().getContentImages()!=null){//如果是图片集合存在，那么
                        List<My_draft.BodyBean.DataBean.ContentImagesBean> contentImages = my_draft.getBody().getData().getContentImages();
                        for(int i=0;i<contentImages.size();i++){
                            My_draft.BodyBean.DataBean.ContentImagesBean Images=contentImages.get(i);
                            if(Images.getType()==9){//如果是可能的原因
                                LinkedList<String> linkedList = new LinkedList<>();
                                linkedList.add(contentImages.get(i).getImagePath());
                                linkedList.add(contentImages.get(i).getImagePath());
                                linkedList.add(contentImages.get(i).getLocalImagePath());
                                list.add(linkedList);
                            }
                        }
                        Pic_bean.answer_second_list=list;
                    }
             }
         }
        initData();
        PicPathToJson picpath=new PicPathToJson(this,list,deleteList);
        picpath.GridViewOnItem(gv_id,adapter);
        picpath.GridViewDelete(gv_id,adapter);
    }
    private void initData() {
        if(list.size()!=5){
            LinkedList<String> linkedList=new LinkedList<>();
            linkedList.addFirst("tianjia");
            list.add(linkedList);
        }
        adapter = new ninelayout_Adapter(this,list);
        gv_id.setAdapter(adapter);//绑定适配器
    }
    private void setOnClickListener() {
        sets_back_id.setOnClickListener(this);
        tv_priview_id.setOnClickListener(this);
        btn_next_id.setOnClickListener(this);
        btn_save_id.setOnClickListener(this);
        tv_priview_id.setOnClickListener(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
            if(resultCode == RESULT_OK){//结果码
                ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);//得到返回来的本地地址集合
                if(path.size()==6){
                    path.remove(5);
                }
                list=new Pic_MultiImageSelector_util(null,push_knowledge_repair_three_activity.this).getURLList(list,path);//根据返回来的值去得到GridView的数据源
                adapter.notifyDataSetChanged();//先显示
                Pic_bean.answer_second_pic=false;
                new UoLoadAsyncTask(push_knowledge_repair_three_activity.this,path,list,"push_knowledge_repair_three_activity").execute();//再上传
            }
        }
    }
    private void initView() {
        queren_id = (TextView)findViewById(R.id.queren_id);
        et_jiejuebuzou_id = (EditText) findViewById(R.id.et_jieguo_id);
        queren_id.setText("解决方法");
        gv_id = (GridView)findViewById(R.id.gv_id);
        sets_back_id = findViewById(R.id.sets_back_id);
        tv_priview_id = findViewById(R.id.tv_priview_id);//界面
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        btn_save_id = (Button) findViewById(R.id.btn_save_id);
    }
    @Override
    public void getpath(LinkedList<LinkedList<String>> urlList) {
        list=urlList;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sets_back_id:
                finish();
                break;
            case R.id.btn_next_id:
                push(0,"知识点发布");
                break;
            case R.id.btn_save_id:
                push(1,"保存草稿");
                break;
            case R.id.tv_priview_id:
                bundle.putString("steps_resolve",et_jiejuebuzou_id.getText().toString());
                bundle.putStringArrayList("PriviewList1",PicPathToJson.getPreViewPath(Pic_bean.answer_frist_list));
                bundle.putStringArrayList("PriviewList2",PicPathToJson.getPreViewPath(Pic_bean.answer_second_list));
                bundle.putString("tag","miji");
                new PreView(this,bundle).SendPreview();
                break;
        }
    }
    private void push(int is_draft, String tag){
        try{
            if(!Pic_bean.answer_frist_pic||!Pic_bean.answer_second_pic){
                Toast.makeText(getApplicationContext(), "图片正在上传中....请稍后", Toast.LENGTH_SHORT).show();
                return;
            }
            String URL;
            JSONObject jsonObject=new JSONObject();
            if(!TextUtils.isEmpty(results)){
                URL=Globle.TEST_URL+"/api/knowledge/editcontent";
                jsonObject.put("content_id",contentid);
            }else{
                URL=Globle.TEST_URL+"/api/knowledge/savecontent";
            }
            jsonObject.put("member_id",new Common_utils(getApplicationContext()).getMemberid());
            jsonObject.put("steps_resolve",et_jiejuebuzou_id.getText().toString());
            jsonObject.put("is_draft",is_draft);
            bundle.remove("my_draft");
            jsonObject=BundleUtils.bundleToJsonObject(bundle,jsonObject);
            jsonObject=PicPathToJson.getPicPath(8,jsonObject,Pic_bean.answer_frist_list);
            jsonObject=PicPathToJson.getPicPath(9,jsonObject,Pic_bean.answer_second_list);
            new delete_AsyncTask().execute(bundle.getStringArrayList("deleteArray"));
            bundle.remove("deleteArray");
            RequestNet.requestServer(jsonObject,URL,push_knowledge_repair_three_activity.this,tag);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
