package com.example.hs.jiankangli_example1.common_activity_pacage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.push_knowledge_package.fault_frist_activity;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.LinkedList;
import java.util.List;

import Inter.get_net_Info;
import bean.firm;
import bean.product;
import Inter.Globle;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/9/26.
 */
public class Select_more_activity extends AutoLayoutActivity implements get_net_Info {

    private TextView tv_add_id;
    private String biaoti;
    private ListView lv_id;
    private final static String productcategories_URL= Globle.TEST_URL+"/api/knowledge/productcategorieslist";//产品列表分类接口
    private final static String chanshang_URL=Globle.TEST_URL+"/api/knowledge/manufacturerlist";//厂商列表接口
    private Handler myHandler=new Handler(){
        private product pd;
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(biaoti.equals("产品分类")){
                pd = com.alibaba.fastjson.JSONObject.parseObject(msg.obj.toString(), product.class);//将得到的数据进行解析
                //准备数据源
                dataBeanList = pd.getBody().getData();
                list = new LinkedList<>();
                for(product.BodyBean.DataBean dataBean: dataBeanList){
                    list.add(dataBean.getSinglename());//产品分类的名称
                }
            }else if(biaoti.equals("品牌")){
                //解析厂商的数据
                fm = com.alibaba.fastjson.JSONObject.parseObject(msg.obj.toString(), firm.class);//解析
                list=new LinkedList<>();
                if(fm!=null&&fm.getBody()!=null&&fm.getBody().getData()!=null){
                    dataBeenlist = fm.getBody().getData();
                    for(firm.BodyBean.DataBean dataBean:dataBeenlist){
                        list.add(dataBean.getSinglename());
                    }
                }
            }
            list.add("添加");
            //准备适配器
            ArrayAdapter adapter=new ArrayAdapter(Select_more_activity.this,android.R.layout.simple_list_item_1,list);
            //绑定适配器
            lv_id.setAdapter(adapter);
            //设置监听器
            lv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //设置listView的item的监听,获取点击项的
                    Intent intent=new Intent(Select_more_activity.this,fault_frist_activity.class);
                    Bundle bundle=new Bundle();
                    //判断当前的页面是厂商还是产品分类
                    if(biaoti.equals("产品分类")){
                        //如果点的是存在的产品分类，那么直接返回,如果点击了最后的一项其他，那么
                        if(i==list.size()-1){
                            //点击的最后一项
                            setResult(2,intent);
                        }else{
                            bundle.putSerializable("duixiang",dataBeanList.get(i));//传递对象
                            intent.putExtras(bundle);
                            setResult(1,intent);//设置结果码和回过去的intent
                        }
                    }else{//厂商
                        //请求码为2
                        if(i==list.size()-1){
                            //点击的最后一项
                            setResult(2,intent);
                        }else{
                            bundle.putSerializable("duixiang",dataBeenlist.get(i));//传递对象
                            intent.putExtras(bundle);
                            setResult(1,intent);//设置结果码和回过去的intent
                        }
                    }
                    Select_more_activity.this.finish();//销毁当前界面
                }
            });

        }
    };
    private List<String> list;
    private List<product.BodyBean.DataBean> dataBeanList;
    private firm fm;
    private List<firm.BodyBean.DataBean> dataBeenlist;
    private AutoLinearLayout sets_back_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏的颜色
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.xuanze_layout);//填充布局
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        biaoti = getIntent().getStringExtra("add");
        //初始化界面控件实例
        initView();
        tv_add_id.setText(biaoti);//设置toolbar的标题页
        //然后根据传过来的值去请求不同的数据接口
        if(biaoti.equals("产品分类")){
            resquestHttp(productcategories_URL);
        }else if(biaoti.equals("品牌")){
            resquestHttp(chanshang_URL);
        }
    }
    private void resquestHttp( String url) {
        RequestNet.queryServer(null,url,Select_more_activity.this,"Select_more_activity");
    }
    private void initView() {
        tv_add_id = (TextView) findViewById(R.id.tv_add_id);
        lv_id = (ListView) findViewById(R.id.lv_id);
        sets_back_id = (AutoLinearLayout) findViewById(R.id.sets_back_id);
        sets_back_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void getinfo(String result) {
        Message msg=new Message();
        msg.obj=result;
        myHandler.sendMessage(msg);
    }
}
