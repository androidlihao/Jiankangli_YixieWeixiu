package com.example.hs.jiankangli_example1.push_knowledge_package;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import bean.My_draft;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import utils.BitmapCompressor;
import Inter.Globle;
import utils.BundleUtils;
import utils.Common_utils;
import utils.RequestNet;
import utils.Statubars;
import utils.addAdapter;
import utils.headPicUtlis;

/**
 * Created by 李浩 on 2016/9/27.
 */
public class guzhang_miaosu_activity extends AutoLayoutActivity implements View.OnClickListener{
    private GridView gv_id;
    private MultiImageSelector multiImageSelector;
    //设置数据源
    private addAdapter adapter;
    private final static String UpPIC_URL= Globle.TEST_URL+"/api/pic/upload";
    private Button btn_next_id;
    private EditText et_guzhangshuru_id;
    private Intent faIntent;
    private Bundle bundle;
    private Button btn_save_id;
    private String results;
    private List<My_draft.BodyBean.DataBean.ContentImagesBean> contentImages;
    private int contentid;
    private LinkedList<String> urlList=new LinkedList<>();
    private ArrayList<String> arrayList=new ArrayList<>();
    private ArrayList<String> priviewList=new ArrayList<>();
    private View sets_back_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏的颜色
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.guzhang_miaosu);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);//将当前页面添加到集合中
        //获取从故障界面传过来的bundle内容
        faIntent = getIntent();
        bundle = faIntent.getBundleExtra("fangan");
        initView();
        SetOnClickListener();
        aboutGridView();
        results = faIntent.getStringExtra("my_draft");
        if(results !=null&&!results.isEmpty()){//如果是从我的草稿页跳转过来的
            My_draft my_draft=com.alibaba.fastjson.JSONObject.parseObject(results, My_draft.class);
            if(my_draft.getBody()!=null){
                if (my_draft.getBody().getData()!=null){
                    et_guzhangshuru_id.setText(my_draft.getBody().getData().getFaultDescription());//得到故障描述
                    contentid = my_draft.getBody().getData().getContentId();
                    if(my_draft.getBody().getData().getContentImages()!=null){//如果是图片集合存在，那么
                        contentImages = my_draft.getBody().getData().getContentImages();
                        for(int i=0;i<contentImages.size();i++){
                            My_draft.BodyBean.DataBean.ContentImagesBean Images=contentImages.get(i);
                            if(Images.getType()==1){//如果是故障维修的图片
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

    private void SetOnClickListener() {
        btn_save_id.setOnClickListener(this);
        sets_back_id.setOnClickListener(this);
        btn_next_id.setOnClickListener(this);
    }

    private void aboutGridView() {
        gv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //如果点击是最后一项的话，那么直接跳转
                if(i==gv_id.getAdapter().getCount()-1&&urlList.get(i).equals("tianjia")){//为最后一项，并且最后一项为可以添加
                    //开始选择图片的操作
                    multiImageSelector = MultiImageSelector.create(guzhang_miaosu_activity.this);
                    multiImageSelector.showCamera(true); // 是否显示相机.默认为显示
                    int j=urlList.size()-1;//此时有的图片数量
                    multiImageSelector.count(5-j); // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                    multiImageSelector .multi(); // 多选模式, 默认模式;
                    multiImageSelector.start(guzhang_miaosu_activity.this,2);//设置请求码,图片选择器启动
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
                            } else if (urlList.size() == 5 && !urlList.getLast().equals("tianjia")){
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
                        new headPicUtlis(guzhang_miaosu_activity.this,null,null).saveBitmap(b,fileName);
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
                                        Toast.makeText(getApplicationContext(), "选择的第"+ finalI +"张"+"图片"+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getApplicationContext(),"服务器或网络异常！,选择的第"+ finalI +"张"+"图片"+"上传失败!"+ex,Toast.LENGTH_SHORT).show();
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
    private void initView() {
        btn_save_id = (Button)findViewById(R.id.btn_save_id);
        gv_id = (GridView)findViewById(R.id.gv_id);//发现girdView
        adapter = new addAdapter(this,urlList);
        gv_id.setAdapter(adapter);//绑定适配器
        et_guzhangshuru_id = (EditText) findViewById(R.id.et_guzhangshuru_id);//发现故障描述输入的控件
        sets_back_id = findViewById(R.id.sets_back_id);
        btn_next_id = (Button) findViewById(R.id.btn_next_id); //发现下一步按钮
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_id:
                String URL;
                try {
                    JSONObject jsonObject=new JSONObject();
                    if(results!=null&&!results.isEmpty()){//如果是从草稿箱过来的
                        URL=Globle.TEST_URL+"/api/knowledge/editcontent";
                        jsonObject.put("content_id",contentid);
                    }else{
                        URL=Globle.TEST_URL+"/api/knowledge/savecontent";
                    }
                    jsonObject.put("member_id",new Common_utils(getApplicationContext()).getMemberid());//会员ID
                    jsonObject.put("fault_description",et_guzhangshuru_id.getText().toString());//故障描述
                    jsonObject.put("is_draft",1);//保存草稿
                    jsonObject=BundleUtils.bundleToJsonObject(bundle,jsonObject);
                    JSONArray jsonArray = new JSONArray();
                    for(int i=0;i<arrayList.size();i++){
                        JSONObject jsonObject1=new JSONObject();
                        jsonObject1.put("image_path",arrayList.get(i));
                        jsonObject1.put("type",1);
                        jsonObject1.put("weight",i);
                        jsonArray.put(jsonObject1);
                    }
                    jsonObject.put("pictures",jsonArray);
                    RequestNet.requestServer(jsonObject,URL,this,"保存草稿");
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.sets_back_id:
                finish();
                break;
            case R.id.btn_next_id:
                Intent intent=new Intent(this,jiejue_buzhou_activity.class);
                bundle.putString("fault_description",et_guzhangshuru_id.getText().toString());
                bundle.putStringArrayList("Picguzhangmiaosu",arrayList);//返回来的故障描述图片图片的地址集合
                bundle.putStringArrayList("PriviewList1",priviewList);
                if(!TextUtils.isEmpty(results)){
                    intent.putExtra("my_draft",results);
                }
                intent.putExtra("miaosu",bundle);
                startActivity(intent);
                break;
        }
    }
}
