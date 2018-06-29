package com.example.hs.jiankangli_example1.certified_company;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import AsyncTask.UoLoadAsyncTask;
import AsyncTask.delete_AsyncTask;
import Inter.Company_Pic_Inter;
import bean.Company_bean;
import bean.getPic_path;
import bean.Pic_bean;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import Inter.Globle;
import utils.BundleUtils;
import utils.Common_utils;
import utils.JavaScriptObject;
import utils.Pic_MultiImageSelector_util;
import utils.RequestNet;
import utils.Statubars;
import utils.ninelayout_Adapter;

/**
 * Created by 李浩 on 2016/11/17.
 */
public class Certified_company_four_activity extends AutoLayoutActivity implements Company_Pic_Inter{

    private GridView gv_jinengzhengshu_id;
    private GridView gv_zhizhizhengshu_id;
    private Button button;
    private LinkedList<LinkedList<String>> jinengzhengshuList=new LinkedList<>();
    private LinkedList<LinkedList<String>> zhizhizhengshuList=new LinkedList<>();
    private ninelayout_Adapter jineng_adapter;
    private ninelayout_Adapter zhizhizhengshu_adapter;
    private LinearLayout sets_back_id;
    private ArrayList<String> deleteList=new ArrayList<>();
    private String company_renzheng_URl;
    private Bundle bundle;
    private Company_bean cb;
    private List<Company_bean.BodyBean.DataBean.PictureBean> picture;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        setContentView(R.layout.company_four_layout);
        bundle = getIntent().getExtras();
        Pic_bean.answer_second_list=null;
        Pic_bean.answer_frist_list=null;
        Pic_bean.answer_frist_pic=true;
        Pic_bean.answer_second_pic=true;
        cb = (Company_bean) getIntent().getSerializableExtra("company_info");
        //初始化界面控件实例
        initView();
        if(cb!=null){
            company_renzheng_URl=Globle.TEST_URL+"/api/info/UpdateAttestationCompany";
            picture = cb.getBody().getData().getPicture();
            for(int i=0;i<picture.size();i++){
                switch (picture.get(i).getType()){
                    case 1:
                        LinkedList<String> linkedList=new LinkedList<>();
                        linkedList.add(picture.get(i).getImagePath());
                        linkedList.add(picture.get(i).getImagePath());
                        linkedList.add(picture.get(i).getLocalImagePath());
                        zhizhizhengshuList.add(linkedList);
                        break;
                    case 2:
                        LinkedList<String> linkedLists=new LinkedList<>();
                        linkedLists.add(picture.get(i).getImagePath());
                        linkedLists.add(picture.get(i).getImagePath());
                        linkedLists.add(picture.get(i).getLocalImagePath());
                        jinengzhengshuList.add(linkedLists);
                        break;
                }
            }
                    Pic_bean.answer_frist_list=zhizhizhengshuList;
                    Pic_bean.answer_second_list=jinengzhengshuList;
        }else{
            company_renzheng_URl= Globle.TEST_URL+"/api/info/attestationCompany";
        }
        //初始化数据
        initData(gv_jinengzhengshu_id.getId());
        initData(gv_zhizhizhengshu_id.getId());
        //设置监听器
        setOnClickList();
        //关于九宫格
        aboutGridView(gv_jinengzhengshu_id,2);//技能证书
        aboutGridView(gv_zhizhizhengshu_id,1);//资质证书
    }
    private void initView() {
        gv_jinengzhengshu_id = (GridView) findViewById(R.id.gv_jinengzhengshu_id);
        gv_zhizhizhengshu_id = (GridView) findViewById(R.id.gv_zhizhizhengshu_id);
        button = (Button) findViewById(R.id.btn_next_id);
        sets_back_id = (LinearLayout) findViewById(R.id.sets_back_id);
    }

    private void initData(int id) {
        //准备数据源
        LinkedList<String> list=new LinkedList<>();
        list.addFirst("tianjia");
        switch (id){
            case R.id.gv_jinengzhengshu_id:
                if(jinengzhengshuList.size()!=5){
                    jinengzhengshuList.add(list);
                }
                //准备适配器
                jineng_adapter = new ninelayout_Adapter(this,jinengzhengshuList);
                //绑定适配器
                gv_jinengzhengshu_id.setAdapter(jineng_adapter);
                break;
            case R.id.gv_zhizhizhengshu_id:
                if(zhizhizhengshuList.size()!=5){
                    zhizhizhengshuList.add(list);
                }
                //准备适配器
                zhizhizhengshu_adapter = new ninelayout_Adapter(this,zhizhizhengshuList);
                //绑定适配器
                gv_zhizhizhengshu_id.setAdapter(zhizhizhengshu_adapter);
                break;
        }
    }
    private void setOnClickList() {
        sets_back_id.setOnClickListener(new MyOnListener());
        button.setOnClickListener(new MyOnListener());
    }
    private class MyOnListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.sets_back_id:
                    finish();
                    break;
                case R.id.btn_next_id://下一步
                    if(Pic_bean.zhengmian_boolean==false){
                        Toast.makeText(getApplicationContext(), "身份证正面图片正在上传.....请稍等！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(Pic_bean.fanmian_boolean==false){
                        Toast.makeText(getApplicationContext(), "身份证反面正在上传.....请稍等！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(Pic_bean.logo_boolean==false){
                        Toast.makeText(getApplicationContext(), "公司logo正在上传.....请稍等！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(Pic_bean.zhizhao_boolean==false){
                        Toast.makeText(getApplicationContext(), "企业营业执照正在上传.....请稍等！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(Pic_bean.answer_frist_pic!=true){
                        Toast.makeText(getApplicationContext(), "资质证书图片正在上传中...上传完成以后再进行提交!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(Pic_bean.answer_second_pic!=true){
                        Toast.makeText(getApplicationContext(), "技能证书图片正在上传中...上传完成以后再进行提交!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("memberId",new JavaScriptObject(Certified_company_four_activity.this).getMemberid(""));
                        jsonObject.put("mobile",new Common_utils(Certified_company_four_activity.this).getPhoneNumber());
                        if(cb!=null){
                            jsonObject.put("companyId",cb.getBody().getData().getCompanyId());
                        }
                        jsonObject=BundleUtils.bundleToJsonObject(bundle,jsonObject);
                        JSONArray jsonArray=new JSONArray();
                        List<getPic_path> listss= new LinkedList<>();
                        listss.add(Pic_bean.logo_path);
                        listss.add(Pic_bean.zhengmian_path);
                        listss.add(Pic_bean.back_path);
                        listss.add(Pic_bean.zhizhao_path);
                        for(int i=0;i<listss.size();i++){
                            JSONObject js = new JSONObject();
                            js.put("image_path",listss.get(i).getLocal_path());
                            js.put("type",i+4);
                            js.put("weight",i);
                            jsonArray.put(js);
                        }
                        if(Pic_bean.answer_frist_list!=null&&Pic_bean.answer_frist_list.size()!=0&&!Pic_bean.answer_frist_list.isEmpty()){
                            for(int i=0;i<Pic_bean.answer_frist_list.size();i++) {
                                if (Pic_bean.answer_frist_list.get(i).size() == 3 && !Pic_bean.answer_frist_list.get(i).get(0).equals("tianjia") && !Pic_bean.answer_frist_list.get(i).get(1).equals("failed") && !Pic_bean.answer_frist_list.get(i).get(2).equals("failed")) {
                                    JSONObject js = new JSONObject();
                                    js.put("image_path", Pic_bean.answer_frist_list.get(i).get(2));
                                    js.put("type", 1);
                                    js.put("weight", i + listss.size());
                                    jsonArray.put(js);
                                }
                            }
                        }
                        if(Pic_bean.answer_second_list!=null&&Pic_bean.answer_second_list.size()!=0&&!Pic_bean.answer_second_list.isEmpty()){
                            for(int i=0;i<Pic_bean.answer_second_list.size();i++){
                                if (Pic_bean.answer_second_list.get(i).size() == 3 && !Pic_bean.answer_second_list.get(i).get(0).equals("tianjia") && !Pic_bean.answer_second_list.get(i).get(1).equals("failed") && !Pic_bean.answer_second_list.get(i).get(2).equals("failed")) {
                                    JSONObject js=new JSONObject();
                                    js.put("image_path",Pic_bean.answer_second_list.get(i).get(2));
                                    js.put("type",2);
                                    js.put("weight",i+listss.size()+Pic_bean.answer_frist_list.size());
                                    jsonArray.put(js);
                                }
                        }
                        }
                        jsonObject.put("picture",jsonArray);
                        RequestNet.requestServer(jsonObject,company_renzheng_URl,Certified_company_four_activity.this,"提交");
                        new delete_AsyncTask().execute(deleteList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
    private void aboutGridView(final GridView gv_id, final int a) {
        gv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (a){
                    case 1:
                        if((i == (gv_id.getAdapter().getCount() - 1)) && (zhizhizhengshuList.get(i).getFirst()).equals("tianjia")){//为最后一项，并且最后一项为可以添加
                            new Pic_MultiImageSelector_util(zhizhizhengshuList,Certified_company_four_activity.this).getMultiImageSelector(a,5-(zhizhizhengshuList.size()-1));//设置请求码
                        }
                        break;
                    case 2:
                        if((i == (gv_id.getAdapter().getCount() - 1)) && (jinengzhengshuList.get(i).getFirst()).equals("tianjia")){//为最后一项，并且最后一项为可以添加
                            new Pic_MultiImageSelector_util(jinengzhengshuList,Certified_company_four_activity.this).getMultiImageSelector(a,5-(jinengzhengshuList.size()-1));//设置请求码
                        }
                        break;
                }
            }


        });
        gv_id.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                switch (a){
                    case 1:
                        if(!(zhizhizhengshuList.get(i).getFirst()).equals("tianjia")){//如果当前不为添加
                            gv_id.getChildAt(i).findViewById(R.id.iv_delte_id).setVisibility(View.VISIBLE);
                            gv_id.getChildAt(i).findViewById(R.id.iv_delte_id).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //如果当前图片上传成功了，将所有删除的记录下来,用于图片删除优化操作
                                    if(zhizhizhengshuList.get(i).size()==3&&!zhizhizhengshuList.get(i).get(1).equals("failed")){
                                        deleteList.add(zhizhizhengshuList.get(i).getLast());
                                    }
                                    zhizhizhengshuList=new Pic_MultiImageSelector_util(null,null).deleteUrlList(zhizhizhengshuList,i);//去删除数据，然后刷新适配器
                                    zhizhizhengshu_adapter.notifyDataSetChanged();
                                }
                            });
                        }
                        break;
                    case 2:
                        if(!(jinengzhengshuList.get(i).getFirst()).equals("tianjia")){//如果当前不为添加
                            gv_id.getChildAt(i).findViewById(R.id.iv_delte_id).setVisibility(View.VISIBLE);
                            gv_id.getChildAt(i).findViewById(R.id.iv_delte_id).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //如果当前图片上传成功了，将所有删除的记录下来,用于图片删除优化操作
                                    if(jinengzhengshuList.get(i).size()==3&&!jinengzhengshuList.get(i).get(1).equals("failed")){
                                        deleteList.add(jinengzhengshuList.get(i).getLast());
                                    }
                                    jinengzhengshuList=new Pic_MultiImageSelector_util(null,null).deleteUrlList(jinengzhengshuList,i);//去删除数据，然后刷新适配器
                                    jineng_adapter.notifyDataSetChanged();
                                }
                            });
                        }
                        break;
                }
                return false;
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){//资质证书
            if(resultCode == RESULT_OK){//结果码
                ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);//得到返回来的本地地址集合
                if(path.size()==6){
                    path.remove(5);
                }
                zhizhizhengshuList=new Pic_MultiImageSelector_util(null,Certified_company_four_activity.this).getURLList(zhizhizhengshuList,path);//根据返回来的值去得到GridView的数据源
                zhizhizhengshu_adapter.notifyDataSetChanged();//先显示
                Pic_bean.answer_frist_pic=false;//第一个
                new UoLoadAsyncTask(Certified_company_four_activity.this,path,zhizhizhengshuList,"frist").execute();//开始上传图片
            }
        }else if(requestCode == 2&&resultCode == RESULT_OK){
            ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);//得到返回来的本地地址集合
            if(path.size()==6){
                path.remove(5);
            }
            jinengzhengshuList=new Pic_MultiImageSelector_util(null,Certified_company_four_activity.this).getURLList(jinengzhengshuList,path);//根据返回来的值去得到GridView的数据源
            jineng_adapter.notifyDataSetChanged();//先显示
            Pic_bean.answer_second_pic=false;//第二个
            new UoLoadAsyncTask(Certified_company_four_activity.this,path,jinengzhengshuList,"second").execute();//再上传
        }
    }
    @Override
    public void getFirst(LinkedList<LinkedList<String>> urlList) {
        zhizhizhengshuList=urlList;
    }

    @Override
    public void getSeconde(LinkedList<LinkedList<String>> urlList) {
        jinengzhengshuList=urlList;
    }
}
