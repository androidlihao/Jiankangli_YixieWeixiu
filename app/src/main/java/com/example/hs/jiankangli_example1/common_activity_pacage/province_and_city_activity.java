package com.example.hs.jiankangli_example1.common_activity_pacage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import java.util.List;

import AsyncTask.query_AsyncTask;
import com.example.hs.jiankangli_example1.certified_company.Certified_company_frist_activity;
import Inter.get_net_Info;
import bean.Citys;
import Inter.Globle;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/11/14.
 */
public class province_and_city_activity extends AutoLayoutActivity implements get_net_Info {
    private final String City_URL= Globle.TEST_URL+"/api/base/getCityInfo";
    private int selectID;
    private ListView lv_province_and_city_id;
    private Citys citys;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.province_and_city_layout);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        selectID = getIntent().getIntExtra("selectID",10000);
        //初始化界面控件实例
        initView();
    }

    private void initView() {
        lv_province_and_city_id = (ListView) findViewById(R.id.lv_province_and_city_id);
        findViewById(R.id.sets_back_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        try {
            //准备数据源
            JSONObject js=new JSONObject();
            js.put("selectId",selectID);
            RequestNet.queryServer(js,City_URL,province_and_city_activity.this,"city");
            lv_province_and_city_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(citys.getBody().getData().get(i).getLevelType().equals("2")){
                        Intent intent=new Intent(province_and_city_activity.this,province_and_city_activity.class);
                        intent.putExtra("cityCode",citys.getBody().getData().get(i).getCityId());
                        intent.putExtra("cityName",citys.getBody().getData().get(i).getName());
                        setResult(2,intent);
                        finish();
                        return;
                    }
                    Intent intent=new Intent(province_and_city_activity.this,province_and_city_activity.class);
                    intent.putExtra("selectID",citys.getBody().getData().get(i).getCityId());
                    startActivityForResult(intent,2);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==resultCode&&requestCode==2&&citys.getBody().getData().get(0).getLevelType().equals("1")){
            Intent intent=new Intent(province_and_city_activity.this, Certified_company_frist_activity.class);
            intent.putExtra("cityCode",data.getIntExtra("cityCode",0));
            intent.putExtra("cityName",data.getStringExtra("cityName"));
            setResult(2,intent);
            finish();
        }else if(requestCode==resultCode&&requestCode==2&&!citys.getBody().getData().get(0).getLevelType().equals("1")){
            Intent intent=new Intent(province_and_city_activity.this,province_and_city_activity.class);
            intent.putExtra("cityCode",data.getIntExtra("cityCode",0));
            intent.putExtra("cityName",data.getStringExtra("cityName"));
            setResult(2,intent);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void getinfo(String str) {
        if(str!=null){
            citys = com.alibaba.fastjson.JSONObject.parseObject(str,Citys.class);
        }
        //准备适配器
        if(citys.getBody()!=null&& citys.getBody().getData()!=null){
            MyAdapter adapter=new MyAdapter(citys.getBody().getData());
            lv_province_and_city_id.setAdapter(adapter);
        }
    }
    class MyAdapter extends BaseAdapter{
        private List<Citys.BodyBean.DataBean> data;
        public MyAdapter(List<Citys.BodyBean.DataBean> data){
            this.data=data;
        }
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView , ViewGroup parent) {
            ViewHolder myholder;
            if(convertView==null){
                convertView=getLayoutInflater().inflate(android.R.layout.simple_list_item_1,null);
                myholder=new ViewHolder();
                myholder.tv_city= (TextView) convertView.findViewById(android.R.id.text1);
                convertView.setTag(myholder);
            } else{
                myholder=(ViewHolder)convertView.getTag();
            }
                myholder.tv_city.setText(data.get(i).getName().toString());
            return convertView;
        }
    }
    public class ViewHolder
    {
        public TextView tv_city;
    }
}
