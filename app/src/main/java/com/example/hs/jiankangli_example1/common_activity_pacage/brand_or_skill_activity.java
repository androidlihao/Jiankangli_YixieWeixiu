package com.example.hs.jiankangli_example1.common_activity_pacage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.personal_Info.Edit_Personal_Info_activity;
import com.zhy.autolayout.AutoLayoutActivity;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import Adapter_package.skill_or_brand_adapter;
import Inter.get_net_Info;
import bean.firm;
import bean.product;
import bean.send_LinkedList_bean;
import Inter.Globle;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/11/21.
 */
public class brand_or_skill_activity extends AutoLayoutActivity implements get_net_Info {

    private ListView lv_skill_brand_id;
    private String tag;
    private final static String Skill_URL= Globle.TEST_URL+"/api/knowledge/productcategorieslist";//技能
    private final static String Brand_URL=Globle.TEST_URL+"/api/knowledge/manufacturerlist";//厂商列表接口
    private skill_or_brand_adapter myAdapter;
    private View tv_finish_id;
    private LinkedList<LinkedList<String>> lists=new LinkedList<>();
    private LinkedList<LinkedList<String>> skill_or_brand_List=new LinkedList<>();
    private firm firm;
    private int a;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.brand_or_skill_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        tag = getIntent().getStringExtra("tag");
        if(getIntent().getSerializableExtra("skill_brand")!=null){
            send_LinkedList_bean slb= (send_LinkedList_bean) getIntent().getSerializableExtra("skill_brand");
            skill_or_brand_List=slb.getSends();
        }
        //初始化界面控件实例
        initView();
        //获取数据源
        switch (tag){
            case "skill":
                a=6;
                requestHttp_info(Skill_URL);
                break;
            case "brand":
                a=7;
                requestHttp_info(Brand_URL);
                break;
        }
    }

    private void requestHttp_info(String URL) {
        RequestNet.queryServer(null,URL,brand_or_skill_activity.this,"brand_or_skill");
    }
    private HashMap<Integer, Boolean> isSelected;//是否被选择了的
    private void initView() {
        findViewById(R.id.sets_back_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lv_skill_brand_id = (ListView) findViewById(R.id.lv_skill_brand_id);
        tv_finish_id = findViewById(R.id.tv_finish_id);
        tv_finish_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将数据返回去
                LinkedList<LinkedList<String>> finishList=new LinkedList<>();
                //完成按钮，结束当前界面，返回上一界面
                Intent intent=new Intent(brand_or_skill_activity.this,Edit_Personal_Info_activity.class);
                //往intent里面存入被选中的数据
                isSelected = skill_or_brand_adapter.getIsSelected();
                Iterator iter = isSelected.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    int key = (int)entry.getKey();//得到key
                    boolean val = (boolean) entry.getValue();//将Hashmap遍历，然后得到对应位置的布尔值
                    if(val==true){//如果val为true
                        LinkedList<String> linkedList=new LinkedList<String>();
                        linkedList.addFirst(lists.get(key).getFirst());//名字
                        linkedList.addLast(lists.get(key).getLast());//id
                        finishList.add(linkedList);
                    }
                }
                send_LinkedList_bean slb=new send_LinkedList_bean();
                slb.setSends(finishList);
                intent.putExtra("result",slb);
                setResult(a,intent);
                finish();//结束当前页面
            }
        });
    }
    @Override
    public void getinfo(String str) {
        LinkedList<String> linkedList=new LinkedList<>();
        if(str!=null&&!str.isEmpty()){
            switch (tag){
                case "skill":
                    product pd= JSONObject.parseObject(str,product.class);
                    if(pd!=null&&pd.getBody()!=null&&pd.getBody().getData()!=null){
                        for(int i=0;i<pd.getBody().getData().size();i++){
                            linkedList.add(pd.getBody().getData().get(i).getName());
                            LinkedList<String> stringLinkedList=new LinkedList<>();
                            stringLinkedList.addFirst(pd.getBody().getData().get(i).getName());
                            stringLinkedList.addLast(pd.getBody().getData().get(i).getProductCategoriesId()+"");
                            lists.add(stringLinkedList);
                        }
                    }
                    break;
                case "brand":
                    firm = JSONObject.parseObject(str, firm.class);
                    if(firm!=null&&firm.getBody()!=null&&firm.getBody().getData()!=null){
                        for(int i=0;i<firm.getBody().getData().size();i++){
                            linkedList.add(firm.getBody().getData().get(i).getName());
                            LinkedList<String> stringLinkedList=new LinkedList<>();
                            stringLinkedList.addFirst(firm.getBody().getData().get(i).getName());
                            stringLinkedList.addLast(firm.getBody().getData().get(i).getManufacturerId()+"");
                            lists.add(stringLinkedList);
                        }
                    }
                    break;
            }
        }
        //准备适配器
        myAdapter = new skill_or_brand_adapter(linkedList,brand_or_skill_activity.this,skill_or_brand_List);
        //绑定适配器
        lv_skill_brand_id.setAdapter(myAdapter);
        //设置监听器
        lv_skill_brand_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                skill_or_brand_adapter.ViewHolder holder = (skill_or_brand_adapter.ViewHolder) arg1.getTag();
                // 改变CheckBox的状态
                holder.cb.toggle();
                //将CheckBox的选中状况记录下来
                skill_or_brand_adapter.getIsSelected().put(arg2,holder.cb.isChecked());//这个数据的arg2值
            }
        });
    }
}
