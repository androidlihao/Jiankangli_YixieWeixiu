package com.example.hs.jiankangli_example1.push_knowledge_package;

import android.content.Intent;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Base64;
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
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import AsyncTask.push_AsyncTask;
import bean.My_draft;
import bean.personal;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import utils.BitmapCompressor;
import Inter.Globle;
import utils.Common_utils;
import utils.PreView;
import utils.RequestNet;
import utils.Statubars;
import utils.XUtilsDB;
import utils.addAdapter;
import utils.headPicUtlis;

/**
 * Created by 李浩 on 2016/9/27.
 */
public class jieguo extends AutoLayoutActivity {

    private GridView gv_id;
    private MultiImageSelector multiImageSelector;
    //设置数据源
    private LinkedList<String> urlList=new LinkedList<>();
    private addAdapter adapter;
    private final static String UpPIC_URL= Globle.TEST_URL+"/api/pic/upload";
    private Button btn_next_id;
    private EditText et_jieguo_id;
    private Intent intent;
    private Bundle bundle;
    private ArrayList<String> arrayList=new ArrayList<>();
    private JSONArray jsonArray;
    private String zhidao;
    private TextView queren_id;
    private Button btn_save_id;
    private List<My_draft.BodyBean.DataBean.ContentImagesBean> contentImages;
    private int contentid;
    private String results;
    private ArrayList<String> priviewList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏的颜色
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.jieguo_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);//将当前页面添加到集合中
        intent = getIntent();
        bundle = intent.getBundleExtra("jiejue");
        zhidao = intent.getStringExtra("zhidao");//获取指导
        //初始化界面控件实例
        initView();
        //关于GridView
        aboutGridView();
        results = intent.getStringExtra("my_draft");
        if(results !=null&&!results.isEmpty()){//如果是从我的草稿页跳转过来的
            My_draft my_draft=com.alibaba.fastjson.JSONObject.parseObject(results, My_draft.class);
            if(my_draft.getBody()!=null){
                if (my_draft.getBody().getData()!=null){
                    if(zhidao!=null){
                        et_jieguo_id.setText(my_draft.getBody().getData().getSolvingGuide());//得到解决指导
                    }else{
                        et_jieguo_id.setText(my_draft.getBody().getData().getResult());//得到结果
                    }
                    if(my_draft.getBody().getData().getContentImages()!=null){
                        //获取图片的集合
                        contentImages = my_draft.getBody().getData().getContentImages();
                        contentid = my_draft.getBody().getData().getContentId();
                        for(My_draft.BodyBean.DataBean.ContentImagesBean Images: contentImages){
                            if(Images.getType()==3||Images.getType()==7){//如果是结果的图片
                                Log.i("TAg", "onCreate:s "+Images.getImagePath());//然后填充数据源
                                urlList.add(Images.getImagePath());
                                arrayList.add(Images.getLocalImagePath());//将草稿箱中已经选择好的放入arraylist中
                                priviewList.add(Images.getImagePath());
                            }
                        }
                    }
                }
            }
            if(urlList.size()<5){
                urlList.add("tianjia");
            }
            adapter.notifyDataSetChanged();
        }else{//如果不是从我的草稿业跳转过来的
            urlList.add("tianjia");
        }
    }
    private void aboutGridView() {
        adapter = new addAdapter(this,urlList);//将数据源传入适配器的geiView
        //绑定适配器
        gv_id.setAdapter(adapter);

        gv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //如果点击是最后一项的话，那么直接跳转
                if(i==gv_id.getAdapter().getCount()-1&&urlList.get(i).equals("tianjia")){//为最后一项，并且最后一项为可以添加
                    //开始选择图片的操作
                    multiImageSelector = MultiImageSelector.create(jieguo.this);
                    multiImageSelector.showCamera(true); // 是否显示相机. 默认为显示
                    int j=urlList.size()-1;//此时有的图片数量
                    multiImageSelector.count(5-j); // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                    multiImageSelector .multi(); // 多选模式, 默认模式;
                    multiImageSelector.start(jieguo.this,2);//设置请求码,图片选择器启动
                }
            }
        });
        gv_id.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if(!urlList.get(i).equals("tianjia")){//如果最后一项不为添加
                    gv_id.getChildAt(i).findViewById(R.id.iv_delte_id).setVisibility(View.VISIBLE);
                    gv_id.getChildAt(i).findViewById(R.id.iv_delte_id).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (urlList.getLast().equals("tianjia")&&i!=urlList.size()-1) {
                                urlList.remove(i);//删除掉当前的
                                arrayList.remove(i);//将返回的数据对应的删除
                                priviewList.remove(i);
                                //如果当前的urlList长度为5,而且结尾部位不是tianjia
                            } else if (urlList.size() == 5 && !urlList.getLast().equals("tianjia")) {
                                urlList.remove(i);
                                arrayList.remove(i);
                                priviewList.remove(i);
                                urlList.addLast("tianjia");//在结尾加一个tianjia
                            }
                            //再次做判断
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
                // 获取返回的图片列表
                ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);//得到返回来的文件的路径
                adapter.notifyDataSetChanged();//刷新适配器
                //填充数据源
                if(path.size()==6){//如果返回的是6个
                    path.remove(0);//去除掉最后的一个
                    urlList.remove("tianjia");//将最后面的一个添加去掉
                    for(String s:path){
                        urlList.add(s);
                    }
                }else if(path.size()==5){//返回的是五个
                    urlList.remove("tianjia");
                    for(String s:path){
                        urlList.add(s);
                    }
                }else if(path.size()>=0&&path.size()<5){
                    urlList.removeLast();//去掉最后一个
                    for(String s:path){
                        urlList.add(s);
                    }
                    if(urlList.size()!=5){
                        urlList.addLast("tianjia");
                    }
                }
                uploadImage(path);
                adapter.notifyDataSetChanged();//刷新适配器
            }
        }
    }
    private void initView(){
        //开启预览界面
        TextView tv_priview_id = (TextView) findViewById(R.id.tv_priview_id);//界面
        tv_priview_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(zhidao!=null&&!zhidao.isEmpty()){
                    bundle.putString("solvingGuide",et_jieguo_id.getText().toString());//结果
                    bundle.putString("tag","cuowu");
                }else{
                    bundle.putString("result",et_jieguo_id.getText().toString());//结果
                    bundle.putString("tag","guzhang");
                }
                bundle.putStringArrayList("PriviewList3",priviewList);
                new PreView(jieguo.this,bundle).SendPreview();
            }
        });
        AutoLinearLayout sets_back_id= (AutoLinearLayout) findViewById(R.id.sets_back_id);
        sets_back_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        queren_id = (TextView) findViewById(R.id.queren_id);
        if(zhidao!=null){
            queren_id.setText(zhidao);
        }
        btn_save_id = (Button) findViewById(R.id.btn_save_id);

        btn_save_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL;
                JSONObject jsonObject=new JSONObject();
                if(results!=null&&!results.isEmpty()){//如果是从草稿箱过来的
                    Log.i("TAG", "onClick: "+"编辑草稿");
                    URL=Globle.TEST_URL+"/api/knowledge/editcontent";
                    try {
                        jsonObject.put("content_id",contentid);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    URL=Globle.TEST_URL+"/api/knowledge/savecontent";
                }
                    try {
                        jsonObject.put("manufacturer_id",bundle.getString("manufacturer_id"));
                        jsonObject.put("manufacturer_name",bundle.getString("manufacturer_name"));
                        jsonObject.put("content_categories_id",bundle.getString("content_categories_id"));
                        Log.i("TAG", "member_id: "+x.getDb(XUtilsDB.getDBconfig()).findFirst(personal.BodyBean.DataBean.class).getMemberId());
                        jsonObject.put("member_id",x.getDb(XUtilsDB.getDBconfig()).findFirst(personal.BodyBean.DataBean.class).getMemberId());//会员ID
                        jsonObject.put("product_categories_id",bundle.getString("product_categories_id"));
                        jsonObject.put("product_categories_name",bundle.getString("product_categories_name"));
                        jsonObject.put("models",bundle.getString("models"));
                        jsonObject.put("key_word",bundle.getString("key_word"));
                        jsonObject.put("summay_content",bundle.getString("summay_content"));
                        jsonObject.put("subsystem",bundle.getString("subsystem"));
                        if(zhidao!=null){
                            jsonObject.put("possible_causes",bundle.getString("possible_causes"));//原因
                            jsonObject.put("explanation",bundle.getString("explanation"));//设置结果
                            jsonObject.put("solving_guide",et_jieguo_id.getText().toString());//设置结果

                        }else{
                            jsonObject.put("err_code",bundle.getString("err_code"));
                            jsonObject.put("fault_description",bundle.getString("fault_description"));
                            jsonObject.put("steps_resolve",bundle.getString("steps_resolve"));
                            jsonObject.put("result",et_jieguo_id.getText().toString());//设置结果

                        }
                        jsonObject.put("title",bundle.getString("title"));//设置结果
                        jsonObject.put("is_draft",1);//设置结果
                        jsonArray = new JSONArray();
                        ArrayList miaosuList=bundle.getStringArrayList("Picguzhangmiaosu");
                        for(int i=0;i<miaosuList.size();i++){
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1.put("image_path",miaosuList.get(i));
                            Log.i("TAG", "onClick: "+miaosuList.get(i));
                            if(zhidao!=null){
                                jsonObject1.put("type",6);//如果当前页面为解释，那么第一个就是可能的原因
                            }else{
                                jsonObject1.put("type",1);//如果当前页面为解决步骤，那么第一个就是描述
                            }
                            jsonObject1.put("weight",i);
                            jsonArray.put(jsonObject1);
                        }
                        final ArrayList jiejuezhou=bundle.getStringArrayList("Picjiejuebuzhou");
                        for(int i=0;i<jiejuezhou.size();i++){
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1.put("image_path",jiejuezhou.get(i));
                            if(zhidao!=null){
                                jsonObject1.put("type",5);//如果当前页面为解释，那么第二个就是解释
                            }else{
                                jsonObject1.put("type",2);//如果当前页面为解决步骤，那么第二个就是解决步骤
                            }
                            jsonObject1.put("weight",i);
                            jsonArray.put(jsonObject1);
                        }
                        //结果的图片集
                        for(int i=0;i<arrayList.size();i++){
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1.put("image_path",arrayList.get(i));
                            if(zhidao!=null){
                                jsonObject1.put("type",7);//解决指导
                            }else{
                                jsonObject1.put("type",3);//结果
                            }
                            jsonObject1.put("weight",i);
                            jsonArray.put(jsonObject1);
                        }
                        jsonObject.put("pictures",jsonArray);
                        RequestNet.requestServer(jsonObject,URL,jieguo.this,"保存草稿");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (DbException e) {
                        e.printStackTrace();
                    }


            }
        });
        gv_id = (GridView)findViewById(R.id.gv_id);//发现girdView
        //发现下一步按钮
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        et_jieguo_id = (EditText) findViewById(R.id.et_jieguo_id);
        btn_next_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String URL;
                    JSONObject jsonObject=new JSONObject();
                    if(results!=null&&!results.isEmpty()){//如果是从草稿箱过来的
                        URL=Globle.TEST_URL+"/api/knowledge/editcontent";
                        try {
                            jsonObject.put("content_id",contentid);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        URL=Globle.TEST_URL+"/api/knowledge/savecontent";
                    }
                    try {
                        jsonObject.put("manufacturer_id",bundle.getString("manufacturer_id"));
                        jsonObject.put("manufacturer_name",bundle.getString("manufacturer_name"));
                        jsonObject.put("content_categories_id",bundle.getString("content_categories_id"));
                        Log.i("TAG", "member_id: "+x.getDb(XUtilsDB.getDBconfig()).findFirst(personal.BodyBean.DataBean.class).getMemberId());
                        jsonObject.put("member_id",x.getDb(XUtilsDB.getDBconfig()).findFirst(personal.BodyBean.DataBean.class).getMemberId());//会员ID
                        jsonObject.put("product_categories_id",bundle.getString("product_categories_id"));
                        jsonObject.put("product_categories_name",bundle.getString("product_categories_name"));
                        jsonObject.put("models",bundle.getString("models"));
                        jsonObject.put("key_word",bundle.getString("key_word"));
                        jsonObject.put("summay_content",bundle.getString("summay_content"));
                        jsonObject.put("subsystem",bundle.getString("subsystem"));
                        if(zhidao!=null){
                            jsonObject.put("possible_causes",bundle.getString("possible_causes"));//设置结果
                            jsonObject.put("explanation",bundle.getString("explanation"));//设置结果
                            jsonObject.put("solving_guide",et_jieguo_id.getText().toString());//设置结果

                        }else{
                            jsonObject.put("err_code",bundle.getString("err_code"));
                            jsonObject.put("fault_description",bundle.getString("fault_description"));
                            jsonObject.put("steps_resolve",bundle.getString("steps_resolve"));
                            jsonObject.put("result",et_jieguo_id.getText().toString());//设置结果

                        }
                        Log.i("TAG", "onClick: "+bundle.getString("fault_description"));
                        jsonObject.put("title",bundle.getString("title"));//设置结果
                        jsonObject.put("is_draft",0);//设置结果
                        jsonArray = new JSONArray();
                        ArrayList miaosuList=bundle.getStringArrayList("Picguzhangmiaosu");
                        for(int i=0;i<miaosuList.size();i++){
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1.put("image_path",miaosuList.get(i));
                            Log.i("TAG", "onClick: "+miaosuList.get(i));
                            if(zhidao!=null){
                                jsonObject1.put("type",6);//如果当前页面为解释，那么第一个就是可能的原因
                            }else{
                                jsonObject1.put("type",1);//如果当前页面为解决步骤，那么第一个就是描述
                            }
                            jsonObject1.put("weight",i);
                            jsonArray.put(jsonObject1);
                        }
                        final ArrayList jiejuezhou=bundle.getStringArrayList("Picjiejuebuzhou");
                        for(int i=0;i<jiejuezhou.size();i++){
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1.put("image_path",jiejuezhou.get(i));
                            if(zhidao!=null){
                                jsonObject1.put("type",5);//如果当前页面为解释，那么第一个就是可能的原因
                            }else{
                                jsonObject1.put("type",2);//如果当前页面为解决步骤，那么第一个就是描述
                            }
                            jsonObject1.put("weight",i);
                            jsonArray.put(jsonObject1);
                        }
                        //结果的图片集
                        for(int i=0;i<arrayList.size();i++){
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1.put("image_path",arrayList.get(i));
                            if(zhidao!=null){
                                jsonObject1.put("type",7);//如果当前页面为解释，那么第一个就是可能的原因
                            }else{
                                jsonObject1.put("type",3);//如果当前页面为解决步骤，那么第一个就是描述
                            }
                            jsonObject1.put("weight",i);
                            jsonArray.put(jsonObject1);
                        }
                        jsonObject.put("pictures",jsonArray);
                        RequestNet.requestServer(jsonObject,URL,jieguo.this,"知识点发布");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
            }
        });
    }
    //根据返回来的图片的个数进行上传
    private void uploadImage(final ArrayList<String> urlList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //上传到服务器
                if(urlList.size()>0){
                    for(int i=0;i<urlList.size();i++){
                        RequestParams params = new RequestParams(UpPIC_URL);//设置params
                        params.setMultipart(true);
                        //根据路径得到bitmap图片
                        Bitmap b= new BitmapCompressor().getSmallBitmap(urlList.get(i));
                        final String fileName = System.currentTimeMillis()+"tupian_pic.png" ;
                        new headPicUtlis(jieguo.this,null,null).saveBitmap(b,fileName);
                        params.addBodyParameter("file",new File(Environment.getExternalStorageDirectory() + "/",fileName));//将所选择文件,文件过大上传不了
                        final int finalI = i+1;
                        x.http().post(params, new Callback.CacheCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Log.i("TAG", "onSuccess: "+result);
                                try {
                                    JSONObject jsonObject=new JSONObject(result);
                                    String code= (String) jsonObject.get("code");
                                    if(code.equals("200")){
                                        Toast.makeText(getApplicationContext(), "选择的第"+ finalI +"张"+"图片"+(CharSequence) jsonObject.get("message"), Toast.LENGTH_SHORT).show();
                                        JSONObject jsonObject1=jsonObject.getJSONObject("body");
                                        arrayList.add(jsonObject1.getString("local_path"));
                                        priviewList.add(jsonObject1.getString("url"));
                                        Common_utils.deletePic(fileName);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                arrayList.add("");//当上传没有成功
                                priviewList.add("");
                                Toast.makeText(getApplicationContext(),"服务器或网络异常！,选择的第"+finalI +"张"+"图片"+"上传失败!"+ex,Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }

                            @Override
                            public boolean onCache(String result) {
                                return false;
                            }
                        });

                    }
                }
            }

        }).start();

    }
}
