package com.example.hs.jiankangli_example1.seek;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import bean.Content_categories;
import bean.firm;
import bean.get_citys_bean;
import bean.product;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/11/2.
 */
public class seek_more_activity extends AutoLayoutActivity{
    private String title;
    private ArrayList<String> arrayList;
    private ListView lv_id;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.xuanze_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        intent = this.getIntent();
        title = intent.getStringExtra("title");
        initView();
    }
    private void initView() {
        findViewById(R.id.sets_back_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView tv_title= (TextView) findViewById(R.id.tv_add_id);
        //设置标题
        tv_title.setText(title);
        lv_id = (ListView) findViewById(R.id.lv_id);
        //准备数据源
        if(title.equals("添加厂商")){
            List<firm.BodyBean.DataBean> firmlist = (List<firm.BodyBean.DataBean>)intent.getSerializableExtra("firm");
            if(firmlist!=null&&firmlist.size()!=0){
                arrayList = new ArrayList<>();
                for(int i=0;i<firmlist.size();i++){
                    arrayList.add(firmlist.get(i).getName());
                }
            }
        }else if(title.equals("添加产品")){
            List<product.BodyBean.DataBean> productlist = (List<product.BodyBean.DataBean>)intent.getSerializableExtra("product");
            if(productlist!=null&&productlist.size()!=0){
                arrayList = new ArrayList<>();
                for(int i=0;i<productlist.size();i++){
                    arrayList.add(productlist.get(i).getName());
                }
            }
        }else if(title.equals("内容")){
            List<Content_categories.BodyBean.DataBean> contentlist = (List<Content_categories.BodyBean.DataBean>)intent.getSerializableExtra("content");
            if(contentlist!=null&&contentlist.size()!=0){
                arrayList = new ArrayList<>();
                for(int i=0;i<contentlist.size();i++){
                    arrayList.add(contentlist.get(i).getName());
                }
            }
        }else if(title.equals("地区")){
            List<get_citys_bean.BodyBean.DataBean> Citylist = (List<get_citys_bean.BodyBean.DataBean>)intent.getSerializableExtra("City");
            if(Citylist!=null&&Citylist.size()!=0){
                arrayList = new ArrayList<>();
                for(int i=0;i<Citylist.size();i++){
                    arrayList.add(Citylist.get(i).getName());
                }
            }
        }
        //准备适配器
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
//        //绑定适配器
        lv_id.setAdapter(adapter);
        //设置监听器
        aboutListview();
    }
    private void aboutListview() {
        lv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(seek_more_activity.this,seek_activity.class);
                intent.putExtra("biaoqian",arrayList.get(i));
                setResult(5,intent);
                finish();
            }
        });
    }

}
