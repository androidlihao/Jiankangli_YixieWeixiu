package com.example.hs.jiankangli_example1.Push_Info;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import AsyncTask.UoLoadAsyncTask;
import AsyncTask.delete_AsyncTask;
import Inter.getPath_Inter;
import bean.Pic_bean;
import bean.push_other_bean;
import bean.push_personal_bean;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import Inter.Globle;
import utils.BundleUtils;
import utils.JavaScriptObject;
import utils.Pic_MultiImageSelector_util;
import utils.RequestNet;
import utils.Statubars;
import utils.ninelayout_Adapter;

/**
 * Created by 李浩 on 2016/9/27.
 */
public class push_personal_Or_other_second_info_activity extends AutoLayoutActivity implements getPath_Inter {
    private GridView gv_id;
    private Button btn_next_id;
    private EditText et_guzhangshuru_id;
    private LinkedList<LinkedList<String>> list=new LinkedList<>();;
    private ninelayout_Adapter adapter;
    private AutoLinearLayout back;
    private ArrayList<String> deleteList=new ArrayList<>();
    private Bundle bundle;
    private String tag;
    private String push_other_url= Globle.TEST_URL+"/api/info/releaseOtherInformation";
    private push_personal_bean personal_bean;
    private List<push_personal_bean.BodyBean.DataBean.InformationImagesBean> informationImages;
    private push_other_bean other_bean;
    private List<push_other_bean.BodyBean.DataBean.InformationImagesBean> informationImages1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.ask_fault_second_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        bundle = getIntent().getExtras();
        tag = bundle.getString("tag");
        personal_bean = (push_personal_bean) bundle.getSerializable("push_personal_bean");
        other_bean = (push_other_bean) bundle.getSerializable("push_other_bean");
        Pic_bean.list=null;
        Pic_bean.UoLoadPic=true;
        initView();
        if(personal_bean!=null&&personal_bean.getBody()!=null&&personal_bean.getBody().getData()!=null){
            informationImages = personal_bean.getBody().getData().getInformationImages();
            for(int i=0;i<informationImages.size();i++){
                switch (informationImages.get(i).getType()){
                    case 3:
                        LinkedList<String> linkedList=new LinkedList<>();
                        linkedList.add(informationImages.get(i).getImagePath());
                        linkedList.add(informationImages.get(i).getImagePath());
                        linkedList.add(informationImages.get(i).getLocalImagePath());
                        list.add(linkedList);
                        break;
                }
            }
            Pic_bean.list=list;
        }
        if(other_bean!=null&&other_bean.getBody()!=null&&other_bean.getBody().getData()!=null){
            informationImages1 = other_bean.getBody().getData().getInformationImages();
            for(int i=0;i<informationImages1.size();i++){
                switch (informationImages1.get(i).getType()){
                    case 3:
                        LinkedList<String> linkedList=new LinkedList<>();
                        linkedList.add(informationImages1.get(i).getImagePath());
                        linkedList.add(informationImages1.get(i).getImagePath());
                        linkedList.add(informationImages1.get(i).getLocalImagePath());
                        list.add(linkedList);
                        break;
                }
            }
            Pic_bean.list=list;
        }
        initData();
        setOnClickList();
        aboutGridView();
    }
    private void setOnClickList() {
        btn_next_id.setOnClickListener(new MyOnListener());
        back.setOnClickListener(new MyOnListener());
    }
    private void initView() {
        et_guzhangshuru_id = (EditText) findViewById(R.id.et_guzhangshuru_id);//发现个人介绍
        et_guzhangshuru_id.setHint("请输入"+tag+"，限制不得超过2000个字符");
        btn_next_id = (Button) findViewById(R.id.btn_next_id); //发现下一步按钮
        back = (AutoLinearLayout) findViewById(R.id.sets_back_id);
        gv_id =(GridView)findViewById(R.id.gv_id);//发现girdView
        TextView small_title= (TextView) findViewById(R.id.small_title);
        switch (tag){
            case "个人介绍":
                small_title.setText(tag);
                btn_next_id.setText("下一步");
                break;
            case "说明":
                small_title.setText(tag);
                btn_next_id.setText("提交");
                break;
        }
        if(personal_bean!=null&&personal_bean.getBody()!=null&&personal_bean.getBody().getData()!=null){
            et_guzhangshuru_id.setText(personal_bean.getBody().getData().getIntroduction());
        }
        if(other_bean!=null&&other_bean.getBody()!=null&&other_bean.getBody().getData()!=null){
            et_guzhangshuru_id.setText(other_bean.getBody().getData().getDescription());
        }
    }
    private void initData() {
        LinkedList<String> linkedList=new LinkedList<>();
        linkedList.addFirst("tianjia");
        if(list.size()!=5){
            list.add(linkedList);
        }
        adapter = new ninelayout_Adapter(this,list);
        gv_id.setAdapter(adapter);//绑定适配器
    }
    private void aboutGridView() {
        gv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==gv_id.getAdapter().getCount()-1&&(list.get(i).getFirst()).equals("tianjia")){//为最后一项，并且最后一项为可以添加
                    new Pic_MultiImageSelector_util(list,push_personal_Or_other_second_info_activity.this).getMultiImageSelector(2,5-(list.size()-1));
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
                list=new Pic_MultiImageSelector_util(null,push_personal_Or_other_second_info_activity.this).getURLList(list,path);//根据返回来的值去得到GridView的数据源
                adapter.notifyDataSetChanged();//先显示
                Pic_bean.UoLoadPic=false;
                new UoLoadAsyncTask(push_personal_Or_other_second_info_activity.this,path,list,"push_personal_picture").execute();//再上传
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
                    switch (tag){
                        case "说明":
                            if(et_guzhangshuru_id.getText().toString().trim().isEmpty()){
                                Toast.makeText(getApplicationContext(), "请输入您的说明以后再提交！", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(Pic_bean.UoLoadPic==false){
                                Toast.makeText(getApplicationContext(), "图片正在上传中...请稍后提交！", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(other_bean!=null){
                                push_other_url=Globle.TEST_URL+"/api/info/editOtherInformation";
                            }
                            JSONObject jsonObject=new JSONObject();
                            try {
                                jsonObject.put("memberId",new JavaScriptObject(getApplicationContext()).getMemberid(""));
                                if(other_bean!=null){
                                    jsonObject.put("informationId",other_bean.getBody().getData().getInformationId());
                                }
                                jsonObject.put("desc",et_guzhangshuru_id.getText().toString().trim());
                                jsonObject=BundleUtils.bundleToJsonObject(bundle,jsonObject);
                                JSONArray jsonArray=new JSONArray();
                                if(Pic_bean.list!=null){
                                    for(int i=0;i<Pic_bean.list.size();i++){
                                        if (Pic_bean.list.get(i).size() == 3 && !Pic_bean.list.get(i).get(0).equals("tianjia") && !Pic_bean.list.get(i).get(1).equals("failed") && !Pic_bean.list.get(i).get(2).equals("failed")) {
                                            JSONObject js=new JSONObject();
                                            js.put("image_path",Pic_bean.list.get(i).get(2));
                                            js.put("type",3);
                                            js.put("weight",i);
                                            jsonArray.put(js);
                                        }
                                    }
                                }
                                jsonObject.put("picture",jsonArray);
                                RequestNet.requestServer(jsonObject,push_other_url,push_personal_Or_other_second_info_activity.this,"信息发布");
                                new delete_AsyncTask().execute(deleteList);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "个人介绍":
                            String guzhangshuru=et_guzhangshuru_id.getText().toString();
                            if(guzhangshuru!=null&&!guzhangshuru.isEmpty()){
                                Intent intent=new Intent(push_personal_Or_other_second_info_activity.this,push_info_no_pictures_activity.class);
                                bundle.putString("introduction",et_guzhangshuru_id.getText().toString());
                                bundle.putString("tag","服务说明");
                                bundle.putStringArrayList("deleteList",deleteList);
                                bundle.putSerializable("push_personal_bean",personal_bean);
                                bundle.putString("push","push_personal_info");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }else if(guzhangshuru.isEmpty()){
                                Toast.makeText(push_personal_Or_other_second_info_activity.this,"请先输入您的个人介绍再进行下一步",Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                    break;
            }
        }
    }
}
