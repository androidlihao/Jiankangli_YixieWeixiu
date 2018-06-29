package com.example.hs.jiankangli_example1.ask;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import AsyncTask.UoLoadAsyncTask;
import AsyncTask.delete_AsyncTask;
import Inter.Globle;
import Inter.getPath_Inter;
import bean.Pic_bean;
import bean.question_bean;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import utils.BundleUtils;
import utils.JavaScriptObject;
import utils.PicPathToJson;
import utils.Pic_MultiImageSelector_util;
import utils.RequestNet;
import utils.Statubars;
import utils.ninelayout_Adapter;


/**
 * Created by 李浩 on 2016/9/27.
 */
public class ask_second_activity extends AutoLayoutActivity implements getPath_Inter {
    private GridView gv_id;
    private Button btn_next_id;
    private EditText et_guzhangshuru_id;
    private LinkedList<LinkedList<String>> list;
    private ninelayout_Adapter adapter;
    private AutoLinearLayout back;
    private ArrayList<String> deleteList=new ArrayList<>();
    private Bundle bundle;
    private question_bean questionBean;
    private List<question_bean.BodyBean.DataBean.QuestionImagesModelBean> questionImagesModel;
    private String ASK_URL;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.ask_fault_second_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        questionBean = (question_bean)getIntent().getSerializableExtra("question");
        if(questionBean!=null){
            ASK_URL= Globle.TEST_URL+"/api/qanda/rePublishCreateQuestion";//审核失败再次提交接口
        }else{
            ASK_URL= Globle.TEST_URL+"/api/qanda/createQuestion";
        }
        initView();
        bundle = getIntent().getBundleExtra("ask");
        setOnClickList();
        Pic_bean.UoLoadPic=true;
        Pic_bean.list=null;
        list=new LinkedList<>();
        if(questionBean!=null&&questionBean.getBody().getData().getQuestionImagesModel()!=null){
            questionImagesModel = questionBean.getBody().getData().getQuestionImagesModel();//图片集合
            for(int i=0;i<questionImagesModel.size();i++){
                switch (questionImagesModel.get(i).getType()){
                    case 1:
                        LinkedList<String> linkedList=new LinkedList<>();
                        linkedList.add(questionImagesModel.get(i).getImagePath());
                        linkedList.add(questionImagesModel.get(i).getImagePath());
                        linkedList.add(questionImagesModel.get(i).getLocalImagePath());
                        list.add(linkedList);
                        break;
                }
            }
            Pic_bean.list=list;
        }
        initData();
        PicPathToJson picpath=new PicPathToJson(this,list,deleteList);
        picpath.GridViewOnItem(gv_id,adapter);
        picpath.GridViewDelete(gv_id,adapter);
    }
    private void setOnClickList() {
        btn_next_id.setOnClickListener(new MyOnListener());
        back.setOnClickListener(new MyOnListener());
    }
    private void initView() {
        et_guzhangshuru_id = (EditText) findViewById(R.id.et_guzhangshuru_id);//发现故障描述输入的控件
        btn_next_id = (Button) findViewById(R.id.btn_next_id); //发现下一步按钮
        back = (AutoLinearLayout) findViewById(R.id.sets_back_id);
        gv_id =(GridView)findViewById(R.id.gv_id);//发现girdView
        if (questionBean!=null&&questionBean.getBody().getData()!=null){
            et_guzhangshuru_id.setText(questionBean.getBody().getData().getFaultDescription());
        }
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
            if(resultCode == RESULT_OK){//结果码
                ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);//得到返回来的本地地址集合
                if(path.size()==6){
                    path.remove(5);
                }
                list=new Pic_MultiImageSelector_util(null,ask_second_activity.this).getURLList(list,path);//根据返回来的值去得到GridView的数据源
                adapter.notifyDataSetChanged();//先显示
                Pic_bean.UoLoadPic=false;
                new UoLoadAsyncTask(ask_second_activity.this,path,list,"ask").execute();//再上传
            }
        }
    }
    @Override
    public void getpath(LinkedList<LinkedList<String>> urlList){
        list=urlList;
    }
    private class MyOnListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.sets_back_id:
                    finish();
                    break;
                case R.id.btn_next_id:
                    String fault=et_guzhangshuru_id.getText().toString();
                    bundle=getIntent().getBundleExtra("ask");
                    bundle.putString("fault_description",fault);
                    bundle.putInt("type",1);
                    if(Pic_bean.UoLoadPic==true){
                        push();
                        new delete_AsyncTask().execute(deleteList); //开始删除图片优化
                    }else{
                        Toast.makeText(getApplicationContext(), "图片正在上传中...请稍后再提问哦!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
    private void push() {
        try {
            JSONObject js=new JSONObject();
            if (questionBean!=null&&questionBean.getBody().getData()!=null){
                js.put("question_id",questionBean.getBody().getData().getQuestionId());
            }
            bundle.remove("sign");
            js= BundleUtils.bundleToJsonObject(bundle,js);
            js.put("member_id",new JavaScriptObject(this).getMemberid(""));
            JSONArray jsonArray=new JSONArray();
            if(Pic_bean.list!=null&&Pic_bean.list.size()!=0&&!Pic_bean.list.isEmpty()){
                for(int i=0;i<Pic_bean.list.size();i++){
                    if (Pic_bean.list.get(i).size() == 3 &&!Pic_bean.list.get(i).get(0).equals("tianjia") &&!Pic_bean.list.get(i).get(1).equals("failed") && !Pic_bean.list.get(i).get(2).equals("failed")) {
                        JSONObject jsonject=new JSONObject();
                        jsonject.put("image_path",Pic_bean.list.get(i).get(2));
                        jsonject.put("type",bundle.getInt("type"));
                        jsonject.put("weight",i);
                        jsonArray.put(jsonject);
                    }
                }
            }
            js.put("pictures",jsonArray);
            RequestNet.requestServer(js,ASK_URL,this,"提问");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
