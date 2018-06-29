package com.example.hs.jiankangli_example1.answer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.LinkedList;
import AsyncTask.UoLoadAsyncTask;
import AsyncTask.delete_AsyncTask;
import Inter.getPath_Inter;
import bean.Pic_bean;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import Inter.Globle;
import utils.JavaScriptObject;
import utils.Pic_MultiImageSelector_util;
import utils.RequestNet;
import utils.Statubars;
import utils.ninelayout_Adapter;

/**
 * Created by 李浩 on 2016/11/10.
 */
public class answer_final_activity extends AutoLayoutActivity implements getPath_Inter {

    private EditText et_explain_id;
    private Button btn_next_id;
    private GridView gv_id;
    private LinkedList<LinkedList<String>> list;
    private ninelayout_Adapter adapter;
    private AutoLinearLayout back;
    private ArrayList<String> deleteList;
    private ArrayList<String> lastPathList;
    private final String Answer_URL= Globle.TEST_URL+"/api/qanda/createAnswer";
    private Bundle bundle;
    private TextView tv_big_titleBar_id;
    private TextView tv_small_titleBar_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.answer_final_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        bundle = getIntent().getExtras();
        deleteList=bundle.getStringArrayList("deleteList");
        initView();
        setOnClickList();
        initData();
        aboutGridView();
    }
    private void initView() {
        //发现故障描述输入的控件
        et_explain_id = (EditText) findViewById(R.id.et_explain_id);
        //发现下一步按钮
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        back = (AutoLinearLayout) findViewById(R.id.sets_back_id);
        //发现girdView
        gv_id = (GridView)findViewById(R.id.gv_id);
        tv_big_titleBar_id = (TextView) findViewById(R.id.tv_big_titleBar_id);
        tv_small_titleBar_id = (TextView) findViewById(R.id.tv_small_titleBar_id);
        switch (bundle.getString("content_category_id")){
            case "1":
                tv_big_titleBar_id.setText("故障详情");
                tv_small_titleBar_id.setText("结果");
                break;
            case "2":
                tv_big_titleBar_id.setText("工作原理");
                tv_small_titleBar_id.setText("工作原理");
                break;
            case "3":
                tv_big_titleBar_id.setText("错误编码");
                tv_small_titleBar_id.setText("解决指导");
                break;
            case "5":
                tv_big_titleBar_id.setText("其他");
                tv_small_titleBar_id.setText("说明");
                break;
        }
    }
    private void setOnClickList() {
        btn_next_id.setOnClickListener(new MyOnListener());
        back.setOnClickListener(new MyOnListener());
    }
    private void initData() {
        list=new LinkedList<>();
        LinkedList<String> linkedList=new LinkedList<>();
        linkedList.addFirst("tianjia");
        list.add(linkedList);
        adapter = new ninelayout_Adapter(this,list);
        gv_id.setAdapter(adapter);//绑定适配器
    }
    @Override
    public void getpath(LinkedList<LinkedList<String>> urlList) {
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
                    String explain=et_explain_id.getText().toString();
                    if(explain!=null&&!explain.isEmpty()){
                        JSONObject js=new JSONObject();
                        switch (bundle.getString("content_category_id")){
                            case "1":
                                if(Pic_bean.answer_final_pic==true&&Pic_bean.answer_frist_pic==true){
                                    try {
                                        js.put("question_id",bundle.getString("question_id"));
                                        js.put("member_id",new JavaScriptObject(answer_final_activity.this).getMemberid(""));
                                        js.put("result",explain);//结果
                                        js.put("steps_resolve",bundle.getString("steps_resolve"));//解决步骤
                                        JSONArray jsonArray=new JSONArray();
                                        if(Pic_bean.answer_finallist!=null&&!Pic_bean.answer_finallist.isEmpty()&&Pic_bean.answer_finallist.size()!=0){
                                            for(int i=0;i<Pic_bean.answer_finallist.size();i++){
                                                Log.i("TAG", "onClick: "+Pic_bean.answer_finallist.get(i).size());
                                                if(Pic_bean.answer_finallist.get(i).size()==3&&!Pic_bean.answer_finallist.get(i).get(0).equals("tianjia")&&!Pic_bean.answer_finallist.get(i).get(1).equals("failed")&&!Pic_bean.answer_finallist.get(i).get(2).equals("failed")){
                                                    JSONObject jsonject=new JSONObject();
                                                    jsonject.put("image_path",Pic_bean.answer_finallist.get(i).get(2));
                                                    jsonject.put("type","3");
                                                    jsonject.put("weight",i+1);
                                                    jsonArray.put(jsonject);
                                                }
                                            }
                                            js.put("pictures",jsonArray);
                                        }
                                        if(Pic_bean.answer_frist_list!=null&&!Pic_bean.answer_frist_list.isEmpty()&&Pic_bean.answer_frist_list.size()!=0){
                                            for(int i=0;i<Pic_bean.answer_frist_list.size();i++){
                                                Log.i("TAG", "onClick: "+Pic_bean.answer_frist_list.get(i).size());
                                                if(Pic_bean.answer_frist_list.get(i).size()==3&&!Pic_bean.answer_frist_list.get(i).get(0).equals("tianjia")&&!Pic_bean.answer_frist_list.get(i).get(1).equals("failed")&&!Pic_bean.answer_frist_list.get(i).get(2).equals("failed")){
                                                    JSONObject jsonject=new JSONObject();
                                                    jsonject.put("image_path",Pic_bean.answer_frist_list.get(i).get(2));
                                                    jsonject.put("type","2");
                                                    jsonject.put("weight",i+1);
                                                    jsonArray.put(jsonject);
                                                }
                                            }
                                            js.put("pictures",jsonArray);
                                        }
                                        RequestNet.requestServer(js,Answer_URL,answer_final_activity.this, "回答");
                                        //开始删除图片优化
                                        new delete_AsyncTask().execute(deleteList);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "图片正在上传中...请稍等", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case "2":
                                if(Pic_bean.answer_final_pic==true){//工作原理
                                    try {
                                        js.put("question_id",bundle.getString("question_id"));
                                        js.put("member_id",new JavaScriptObject(answer_final_activity.this).getMemberid(""));
                                        js.put("working_principle",explain);//工作原理
                                        JSONArray jsonArray=new JSONArray();
                                        if(Pic_bean.answer_finallist!=null&&!Pic_bean.answer_finallist.isEmpty()&&Pic_bean.answer_finallist.size()!=0){
                                            for(int i=0;i<Pic_bean.answer_finallist.size();i++){
                                                Log.i("TAG", "onClick: "+Pic_bean.answer_finallist.get(i).size());
                                                if(Pic_bean.answer_finallist.get(i).size()==3&&!Pic_bean.answer_finallist.get(i).get(0).equals("tianjia")&&!Pic_bean.answer_finallist.get(i).get(1).equals("failed")&&!list.get(i).get(2).equals("failed")){
                                                    JSONObject jsonject=new JSONObject();
                                                    jsonject.put("image_path",list.get(i).get(2));
                                                    jsonject.put("type","4");
                                                    jsonject.put("weight",i+1);
                                                    jsonArray.put(jsonject);
                                                }
                                            }
                                            js.put("pictures",jsonArray);
                                        }
                                        //开始删除图片优化
                                        new delete_AsyncTask().execute(deleteList);
                                        RequestNet.requestServer(js,Answer_URL,answer_final_activity.this, "回答");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "图片正在上传中...请稍等", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case "3":
                                if(Pic_bean.answer_final_pic==true&&Pic_bean.answer_frist_pic==true&&Pic_bean.answer_second_pic==true){
                                    try {
                                        js.put("question_id",bundle.getString("question_id"));
                                        js.put("member_id",new JavaScriptObject(answer_final_activity.this).getMemberid(""));
                                        js.put("solving_guide",explain);//解决指导
                                        js.put("explanation",bundle.getString("explanation"));//解释
                                        js.put("possible_causes",bundle.getString("possible_causes"));//可能的原因
                                        JSONArray jsonArray=new JSONArray();
                                        if(Pic_bean.answer_finallist!=null&&!Pic_bean.answer_finallist.isEmpty()&&Pic_bean.answer_finallist.size()!=0){
                                            for(int i=0;i<Pic_bean.answer_finallist.size();i++){
                                                Log.i("TAG", "onClick: "+Pic_bean.answer_finallist.get(i).size());
                                                if(Pic_bean.answer_finallist.get(i).size()==3&&!Pic_bean.answer_finallist.get(i).get(0).equals("tianjia")&&!Pic_bean.answer_finallist.get(i).get(1).equals("failed")&&!Pic_bean.answer_finallist.get(i).get(2).equals("failed")){
                                                    JSONObject jsonject=new JSONObject();
                                                    jsonject.put("image_path",Pic_bean.answer_finallist.get(i).get(2));
                                                    jsonject.put("type","7");
                                                    jsonject.put("weight",i+1);
                                                    jsonArray.put(jsonject);
                                                }
                                            }
                                            js.put("pictures",jsonArray);
                                        }
                                        if(Pic_bean.answer_frist_list!=null&&!Pic_bean.answer_frist_list.isEmpty()&&Pic_bean.answer_frist_list.size()!=0){
                                            for(int i=0;i<Pic_bean.answer_frist_list.size();i++){
                                                Log.i("TAG", "onClick: "+Pic_bean.answer_frist_list.get(i).size());
                                                if(Pic_bean.answer_frist_list.get(i).size()==3&&!Pic_bean.answer_frist_list.get(i).get(0).equals("tianjia")&&!Pic_bean.answer_frist_list.get(i).get(1).equals("failed")&&!Pic_bean.answer_frist_list.get(i).get(2).equals("failed")){
                                                    JSONObject jsonject=new JSONObject();
                                                    jsonject.put("image_path",Pic_bean.answer_frist_list.get(i).get(2));
                                                    jsonject.put("type","5");
                                                    jsonject.put("weight",i+1);
                                                    jsonArray.put(jsonject);
                                                }
                                            }
                                            js.put("pictures",jsonArray);
                                        }
                                        if(Pic_bean.answer_second_list!=null&&!Pic_bean.answer_second_list.isEmpty()&&Pic_bean.answer_second_list.size()!=0){
                                            for(int i=0;i<Pic_bean.answer_second_list.size();i++){
                                                Log.i("TAG", "onClick: "+Pic_bean.answer_second_list.get(i).size());
                                                if(Pic_bean.answer_second_list.get(i).size()==3&&!Pic_bean.answer_second_list.get(i).get(0).equals("tianjia")&&!Pic_bean.answer_second_list.get(i).get(1).equals("failed")&&!Pic_bean.answer_second_list.get(i).get(2).equals("failed")){
                                                    JSONObject jsonject=new JSONObject();
                                                    jsonject.put("image_path",Pic_bean.answer_second_list.get(i).get(2));
                                                    jsonject.put("type","6");
                                                    jsonject.put("weight",i+1);
                                                    jsonArray.put(jsonject);
                                                }
                                            }
                                            js.put("pictures",jsonArray);
                                        }
                                        RequestNet.requestServer(js,Answer_URL,answer_final_activity.this, "回答");
                                        new delete_AsyncTask().execute(deleteList);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "图片正在上传中...请稍等", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case "5":
                                if(Pic_bean.answer_final_pic==true){
                                    try {
                                        js.put("question_id",bundle.getString("question_id"));
                                        js.put("member_id",new JavaScriptObject(answer_final_activity.this).getMemberid(""));
                                        js.put("interpretation",explain);
                                        JSONArray jsonArray=new JSONArray();
                                        if(Pic_bean.answer_finallist!=null&&!Pic_bean.answer_finallist.isEmpty()&&Pic_bean.answer_finallist.size()!=0){
                                            for(int i=0;i<Pic_bean.answer_finallist.size();i++){
                                                Log.i("TAG", "onClick: "+Pic_bean.answer_finallist.get(i).size());
                                                if(Pic_bean.answer_finallist.get(i).size()==3&&!Pic_bean.answer_finallist.get(i).get(0).equals("tianjia")&&!Pic_bean.answer_finallist.get(i).get(1).equals("failed")&&!list.get(i).get(2).equals("failed")){
                                                    JSONObject jsonject=new JSONObject();
                                                    jsonject.put("image_path",list.get(i).get(2));
                                                    jsonject.put("type","10");
                                                    jsonject.put("weight",i+1);
                                                    jsonArray.put(jsonject);
                                                }
                                            }
                                            js.put("pictures",jsonArray);
                                        }
                                        RequestNet.requestServer(js,Answer_URL,answer_final_activity.this, "回答");
                                        //开始删除图片优化
                                        new delete_AsyncTask().execute(deleteList);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "图片正在上传中...请稍等", Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }
                        //执行回答操作
                    }else if(explain.isEmpty()||explain==null){
                        Toast.makeText(getApplicationContext(),"请输入您的解决方案以后再提交哦!",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
    private void aboutGridView() {
        gv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==gv_id.getAdapter().getCount()-1&&(list.get(i).getFirst()).equals("tianjia")){//为最后一项，并且最后一项为可以添加
                    new Pic_MultiImageSelector_util(list,answer_final_activity.this).getMultiImageSelector(2,5-(list.size()-1));
                }
            }
        });
        gv_id.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if(!(list.get(i).getFirst()).equals("tianjia")){//如果当前不为添加
                    gv_id.getChildAt(i).findViewById(R.id.iv_delte_id).setVisibility(View.VISIBLE);
                    gv_id.getChildAt(i).findViewById(R.id.iv_delte_id).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //如果当前图片上传成功了，将所有删除的记录下来,用于图片删除优化操作
                            if(list.get(i).size()==3&&!list.get(i).get(1).equals("failed")){
                                deleteList.add(list.get(i).getLast());
                            }
                            //将最后上传的这次的图片所删除的记录下来
                            lastPathList.add(list.get(i).getFirst());
                            list=new Pic_MultiImageSelector_util(null,null).deleteUrlList(list,i);//去删除数据，然后刷新适配器
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
                return false;
            }
        });
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
                //新建一个集合用于存储最后删除的图片的路径
                lastPathList = new ArrayList<>();
                list=new Pic_MultiImageSelector_util(null,answer_final_activity.this).getURLList(list,path);//根据返回来的值去得到GridView的数据源
                adapter.notifyDataSetChanged();//先显示
                Pic_bean.answer_final_pic=false;//开始上传，变为false不可上传
                new UoLoadAsyncTask(answer_final_activity.this,path,list,"answer_final").execute();//开始执行上传任务
            }
        }
    }
}
