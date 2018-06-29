package com.example.hs.jiankangli_example1.personal_Info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.alibaba.fastjson.JSONObject;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.zhy.autolayout.AutoLayoutActivity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Inter.get_net_Info;
import bean.product;
import Inter.Globle;
import utils.MyAdapter;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/10/11.
 */
public class personal_Skill_activity extends AutoLayoutActivity implements get_net_Info,View.OnClickListener {

    private View bt_selectall;
    private View bt_cancel;
    private View bt_deselectall;
    private product pd;
    private ListView lv_sk_id;
    private MyAdapter myAdapter;
    private List<product.BodyBean.DataBean> dataBeanList;
    private ArrayList<String> list;
    private View tv_finish_id;
    private HashMap<Integer, Boolean> isSelected;
    private ArrayList jinengList;
    private ArrayList<String> skillList;
    private View set_back_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.skill_layout);//
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        skillList = getIntent().getStringArrayListExtra("sklist");//得到了已经选择好技能
        initView();
        String URL= Globle.TEST_URL+"/api/knowledge/productcategorieslist";//产品列表分类接口
        RequestNet.queryServer(null,URL,personal_Skill_activity.this,"personal_Skill_activity");
        setOnClickListener();
    }
    private void setOnClickListener() {
        tv_finish_id.setOnClickListener(this);
        bt_selectall.setOnClickListener(this);
        bt_deselectall.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
        set_back_id.setOnClickListener(this);
        lv_sk_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                MyAdapter.ViewHolder holder = (MyAdapter.ViewHolder) arg1.getTag();
                // 改变CheckBox的状态
                holder.cb.toggle();
                //将CheckBox的选中状况记录下来
                MyAdapter.getIsSelected().put(arg2, holder.cb.isChecked());//这个数据的arg2值
            }
        });
    }
    private void dataChanged() {
        myAdapter.notifyDataSetChanged();
    };
    private void initView() {
        tv_finish_id = findViewById(R.id.tv_finish_id);
        lv_sk_id = (ListView) findViewById(R.id.lv_sk_id);
        bt_selectall =findViewById(R.id.btn_selectall);
        bt_cancel =  findViewById(R.id.btn_cancleselectall);
        bt_deselectall =findViewById(R.id.btn_deselectall);
        set_back_id =findViewById(R.id.set_back_id);
    }
    @Override
    public void getinfo(String result) {
        //网络请求成功，将数据返回去
        pd = JSONObject.parseObject(result, product.class);//解析出来对象
        dataBeanList = pd.getBody().getData();
        list = new ArrayList<>();
        jinengList = new ArrayList<>();
        for(product.BodyBean.DataBean dataBean: dataBeanList){
            list.add(dataBean.getName());//产品分类的名称
            jinengList.add(dataBean.getProductCategoriesId());
        }
        //准备适配器
        myAdapter = new MyAdapter(list, personal_Skill_activity.this,skillList);
        //设置适配器
        lv_sk_id.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_finish_id:
                //完成按钮，结束当前界面，返回上一界面
                Intent intent=new Intent(personal_Skill_activity.this, Edit_Personal_Info_activity.class);
                //往intent里面存入被选中的数据
                isSelected = MyAdapter.getIsSelected();
                ArrayList<String> lists=new ArrayList<>();
                ArrayList<String> listjineng=new ArrayList<>();
                Iterator iter = isSelected.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    int key = (int)entry.getKey();//得到key
                    boolean val = (boolean) entry.getValue();//得到val
                    if(val==true){//如果val为true
                        lists.add(list.get(key));
                        //根据Key得到技能的Id
                        listjineng.add(jinengList.get(key)+"");
                    }
                }
                intent.putStringArrayListExtra("skill",lists);
                intent.putStringArrayListExtra("jinengid",listjineng);
                setResult(6,intent);
                personal_Skill_activity.this.finish();//结束当前页面
                break;
            case R.id.btn_selectall:
                // 遍历list的长度，将MyAdapter中的map值全部设为true
                for (int i = 0; i < list.size(); i++) {
                    MyAdapter.getIsSelected().put(i, true);
                }
                dataChanged();// 刷新listview和TextView的显示
                break;
            case R.id.btn_deselectall:
                // 遍历list的长度，将已选的按钮设为未选
                for (int i = 0; i < list.size(); i++) {
                    if (MyAdapter.getIsSelected().get(i)) {
                        MyAdapter.getIsSelected().put(i, false);
                    }
                }
                // 刷新listview和TextView的显示
                dataChanged();
                break;
            case R.id.btn_cancleselectall:
                // 遍历list的长度，将已选的设为未选，未选的设为已选
                for (int i = 0; i < list.size(); i++) {
                    if (MyAdapter.getIsSelected().get(i)) {
                        MyAdapter.getIsSelected().put(i, false);
                    } else {
                        MyAdapter.getIsSelected().put(i, true);
                    }
                }
                dataChanged();
                break;
            case R.id.set_back_id:
                finish();
                break;
        }
    }
}
