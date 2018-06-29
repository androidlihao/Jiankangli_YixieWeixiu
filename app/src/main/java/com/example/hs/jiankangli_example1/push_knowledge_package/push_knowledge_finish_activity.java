package com.example.hs.jiankangli_example1.push_knowledge_package;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import Inter.Globle;
import Inter.getPath_Inter;
import bean.My_draft;
import bean.Pic_bean;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import utils.BundleUtils;
import utils.JavaScriptObject;
import utils.PicPathToJson;
import utils.Pic_MultiImageSelector_util;
import utils.PreView;
import utils.RequestNet;
import utils.Statubars;
import utils.ninelayout_Adapter;

/**
 * Created by 李浩 on 2016/12/1.
 */
public class push_knowledge_finish_activity extends AutoLayoutActivity implements getPath_Inter {
    private Bundle bundle;
    private EditText et_text_id;
    private GridView gv_id;
    private LinkedList<LinkedList<String>> list=new LinkedList<>();
    private ninelayout_Adapter adapter;
    private View sets_back_id;
    private ArrayList<String> deleteList=new ArrayList<>();
    private Button btn_next_id;
    private Button btn_save_id;
    private View tv_priview_id;
    private My_draft myDraft;
    private List<My_draft.BodyBean.DataBean.ContentImagesBean> contentImages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.push_knowledge_finish_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        bundle = getIntent().getExtras();
        myDraft = (My_draft) bundle.getSerializable("my_draft");
        Pic_bean.answer_frist_list=null;
        Pic_bean.answer_frist_pic=true;//可以传递
        //初始化界面控件实例
        initView();
        if(myDraft!=null&&myDraft.getBody()!=null&&myDraft.getBody().getData()!=null){
            contentImages = myDraft.getBody().getData().getContentImages();
            for(int i=0;i<contentImages.size();i++){
                switch (contentImages.get(i).getType()){
                    case 5:
                        LinkedList<String> linkedList=new LinkedList<>();
                        linkedList.add(contentImages.get(i).getImagePath());
                        linkedList.add(contentImages.get(i).getImagePath());
                        linkedList.add(contentImages.get(i).getLocalImagePath());
                        list.add(linkedList);
                        break;
                }
            }
            Pic_bean.answer_frist_list=list;
        }
        //初始化界面数据
        initData();
        //界面控件设置监听器
        SetOnClickListener();
        PicPathToJson picpath=new PicPathToJson(this,list,deleteList);
        picpath.GridViewOnItem(gv_id,adapter);
        picpath.GridViewDelete(gv_id,adapter);
    }
    private void initView() {
        TextView push_knowledeg_title= (TextView) findViewById(R.id.push_knowledeg_title);
        push_knowledeg_title.setText(bundle.getString("push_knowledge_title"));
        et_text_id = (EditText) findViewById(R.id.et_text_id);
        if(myDraft!=null&&myDraft.getBody()!=null&&myDraft.getBody().getData()!=null){
            et_text_id.setText(myDraft.getBody().getData().getExplanation());
        }
        gv_id = (GridView) findViewById(R.id.gv_id);
        sets_back_id = findViewById(R.id.sets_back_id);
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        btn_save_id = (Button) findViewById(R.id.btn_save_id);
        tv_priview_id = findViewById(R.id.tv_priview_id);
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
    private void SetOnClickListener() {
        sets_back_id.setOnClickListener(new MyOnClickListener());
        btn_next_id.setOnClickListener(new MyOnClickListener());
        btn_save_id.setOnClickListener(new MyOnClickListener());
        tv_priview_id.setOnClickListener(new MyOnClickListener());
    }

    @Override
    public void getpath(LinkedList<LinkedList<String>> urlList) {
        list=urlList;
    }

    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String URL;
            if(myDraft!=null){
                URL=Globle.TEST_URL+"/api/knowledge/editcontent";
            }else{
                URL= Globle.TEST_URL+"/api/knowledge/savecontent";//保存草稿
            }
            switch (view.getId()){
                case R.id.sets_back_id:
                    finish();
                    break;
                case R.id.btn_save_id:
                    push(URL,"1","1","保存草稿");
                    break;
                case R.id.btn_next_id:
                    if(et_text_id.getText().toString().trim().isEmpty()){
                        Toast.makeText(getApplicationContext(), "解释为必填项，不能为空！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    push(URL,"0","1","知识点发布");
                    break;
                case R.id.tv_priview_id:
                    //预览界面
                    bundle.putString("explanation",et_text_id.getText().toString());
                    bundle.putString("tag","nonu");
                    new PreView(push_knowledge_finish_activity.this,bundle).SendPreview();
                    break;
            }
        }
    }
    private void push(String pushUrl, String is_draft,String is_noun,String tag) {
        try {
            if(!Pic_bean.answer_frist_pic){
                Toast.makeText(getApplicationContext(), "图片正在上传中....请稍后", Toast.LENGTH_SHORT).show();
                return;
            }
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("member_id",new JavaScriptObject(getApplicationContext()).getMemberid(""));
            jsonObject.put("is_draft",is_draft);
            jsonObject.put("is_noun",is_noun);
            jsonObject.put("explanation",et_text_id.getText().toString());
            if(myDraft!=null){
                jsonObject.put("content_id",myDraft.getBody().getData().getContentId());
            }
            jsonObject=BundleUtils.bundleToJsonObject(bundle,jsonObject);
            jsonObject=PicPathToJson.getPicPath(5,jsonObject,Pic_bean.answer_frist_list);
            RequestNet.requestServer(jsonObject,pushUrl,push_knowledge_finish_activity.this,tag);
            new delete_AsyncTask().execute(deleteList);
        }catch (Exception e){
            e.printStackTrace();
        }
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
                list=new Pic_MultiImageSelector_util(null,push_knowledge_finish_activity.this).getURLList(list,path);//根据返回来的值去得到GridView的数据源
                adapter.notifyDataSetChanged();//先显示
                Pic_bean.answer_frist_pic=false;
                new UoLoadAsyncTask(push_knowledge_finish_activity.this,path,list,"push_knowledge_finish_activity").execute();//再上传
            }
        }
    }
}
