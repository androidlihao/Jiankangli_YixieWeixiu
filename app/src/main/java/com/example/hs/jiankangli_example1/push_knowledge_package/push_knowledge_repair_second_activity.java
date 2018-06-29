package com.example.hs.jiankangli_example1.push_knowledge_package;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import AsyncTask.UoLoadAsyncTask;
import Inter.getPath_Inter;
import bean.My_draft;
import bean.Pic_bean;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import Inter.Globle;
import utils.BundleUtils;
import utils.Common_utils;
import utils.PicPathToJson;
import utils.Pic_MultiImageSelector_util;
import utils.RequestNet;
import utils.Statubars;
import utils.ninelayout_Adapter;

/**
 * Created by 李浩 on 2016/9/27.
 */
public class push_knowledge_repair_second_activity extends AutoLayoutActivity implements View.OnClickListener , getPath_Inter {

    private GridView gv_id;
    private Button btn_next_id;
    private EditText et_guzhangshuru_id;
    private Bundle bundle;
    private Button btn_save_id;
    private String results;
    private int contentid;
    private List<My_draft.BodyBean.DataBean.ContentImagesBean> contentImages;
    private View sets_back_id;
    //TODO
    private LinkedList<LinkedList<String>> list=new LinkedList<>();//图片集合
    private ninelayout_Adapter adapter;
    private ArrayList<String> deleteList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.guzhang_miaosu);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);//将当前页面添加到集合中
        bundle = getIntent().getExtras();
        results = getIntent().getStringExtra("my_draft");
        Pic_bean.answer_frist_list=null;
        Pic_bean.answer_frist_pic=true;//可以传递
        initView();
        setOnClickListener();
        if(!TextUtils.isEmpty(results)) {//如果是从我的草稿页跳转过来的
            My_draft my_draft = com.alibaba.fastjson.JSONObject.parseObject(results, My_draft.class);
            if (my_draft != null && my_draft.getBody() != null && my_draft.getBody().getData() != null) {
                et_guzhangshuru_id.setText(my_draft.getBody().getData().getFaultDescription());//得到故障描述
                contentid = my_draft.getBody().getData().getContentId();
                if (my_draft.getBody().getData().getContentImages() != null) {//如果是图片集合存在，那么
                    contentImages = my_draft.getBody().getData().getContentImages();
                    for (int i = 0; i < contentImages.size(); i++) {
                        My_draft.BodyBean.DataBean.ContentImagesBean Images = contentImages.get(i);
                        if (Images.getType() == 8) {//如果是故障维修的图片
                            LinkedList<String> linkedList = new LinkedList<>();
                            linkedList.add(contentImages.get(i).getImagePath());
                            linkedList.add(contentImages.get(i).getImagePath());
                            linkedList.add(contentImages.get(i).getLocalImagePath());
                            list.add(linkedList);
                        }
                    }
                    Pic_bean.answer_frist_list=list;
                }
            }
        }
        initData();
        PicPathToJson picpath=new PicPathToJson(this,list,deleteList);
        picpath.GridViewOnItem(gv_id,adapter);
        picpath.GridViewDelete(gv_id,adapter);
    }
    private void initData() {
        if(list.size()<5){
            LinkedList<String> linkedList=new LinkedList<>();
            linkedList.addFirst("tianjia");
            list.add(linkedList);
        }
        adapter = new ninelayout_Adapter(this,list);
        gv_id.setAdapter(adapter);//绑定适配器
    }
    private void initView() {
        sets_back_id = findViewById(R.id.sets_back_id);
        btn_save_id = (Button) findViewById(R.id.btn_save_id);
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        et_guzhangshuru_id = (EditText) findViewById(R.id.et_guzhangshuru_id);
        gv_id = (GridView)findViewById(R.id.gv_id);
    }
    private void setOnClickListener() {
        btn_save_id.setOnClickListener(this);
        sets_back_id.setOnClickListener(this);
        btn_next_id.setOnClickListener(this);
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
                list=new Pic_MultiImageSelector_util(null,push_knowledge_repair_second_activity.this).getURLList(list,path);//根据返回来的值去得到GridView的数据源
                adapter.notifyDataSetChanged();//先显示
                Pic_bean.answer_frist_pic=false;
                new UoLoadAsyncTask(push_knowledge_repair_second_activity.this,path,list,"push_knowledge_repair_second_activity").execute();//再上传
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_id:
                try {
                    String URL;
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("member_id",new Common_utils(getApplicationContext()).getMemberid());//会员ID
                    jsonObject.put("fault_description",et_guzhangshuru_id.getText().toString());//故障描述
                    jsonObject.put("is_draft",1);//保存草稿
                    if(!TextUtils.isEmpty(results)){//如果是从草稿箱过来的
                        URL=Globle.TEST_URL+"/api/knowledge/editcontent";
                        jsonObject.put("content_id",contentid);
                    }else{
                        URL=Globle.TEST_URL+"/api/knowledge/savecontent";
                    }
                    bundle.remove("my_draft");
                    jsonObject=BundleUtils.bundleToJsonObject(bundle,jsonObject);
                    jsonObject=PicPathToJson.getPicPath(8,jsonObject,Pic_bean.answer_frist_list);
                    RequestNet.requestServer(jsonObject,URL,push_knowledge_repair_second_activity.this,"保存草稿");
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.sets_back_id:
                finish();
                break;
            case R.id.btn_next_id:
                Intent intent=new Intent(push_knowledge_repair_second_activity.this,push_knowledge_repair_three_activity.class);
                bundle.putString("fault_description",et_guzhangshuru_id.getText().toString().trim());
                bundle.putStringArrayList("deleteArray",deleteList);
                if(TextUtils.isEmpty(results)){
                    intent.putExtra("my_draft",results);
                }
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void getpath(LinkedList<LinkedList<String>> urlList) {
        list=urlList;
    }
}
