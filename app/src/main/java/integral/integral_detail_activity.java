package integral;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.hs.jiankangli_example1.My_activity;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Inter.get_net_Info;
import bean.jifeng;
import utils.Date_format;
import Inter.Globle;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/10/13.
 */
public class integral_detail_activity extends AutoLayoutActivity implements get_net_Info,View.OnClickListener{
    private ListView lv_jifeng_id;
    private jifeng jifeng;
    private List<Map<String, Object>> mList;
    private List<jifeng.BodyBean.DataBean> data;
    private SimpleAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.integral_detail_layout);
        SysApplication.getInstance().addActivity(this);
        findViewById(R.id.jf_back_id).setOnClickListener(this);
        findViewById(R.id.tv_jifengrule_id).setOnClickListener(this);
        aboutListView();
    }
    private void aboutListView() {
        try {
            lv_jifeng_id = (ListView) findViewById(R.id.lv_jifeng_id);
            mList = new ArrayList<>();
            String integral_Uri= Globle.TEST_URL+"/api/details/integralRecord";
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("member_id",new JavaScriptObject(integral_detail_activity.this).getMemberid(""));//会员ID
            RequestNet.queryServer(jsonObject,integral_Uri,integral_detail_activity.this,"integeral_detatil"); //准备数据源
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new SimpleAdapter(integral_detail_activity.this,mList, R.layout.jf_item,new String[]{"content","date","integral"},
                new int[]{R.id.tv_item_content_id,R.id.tv_item_date_id,R.id.tv_item_jifeng_id}); //准备适配器
        lv_jifeng_id.setAdapter(adapter);//绑定适配器
    }
    @Override
    public void getinfo(String str) {
        try {
            JSONObject jsonObject1=new JSONObject(str);
            if(jsonObject1.getString("code").equals("200")){//查询成功
                jifeng = com.alibaba.fastjson.JSONObject.parseObject(str, jifeng.class);
                data = jifeng.getBody().getData();
                for(jifeng.BodyBean.DataBean db:data){
                    Map<String, Object> map = new HashMap<>();
                    map.put("content",db.getContent());
                    map.put("date", Date_format.timeStamp2Date(db.getCreateTime()+"","yyyy-MM-dd HH:mm:ss"));
                    map.put("integral",db.getIntegral());
                    mList.add(map);
                }
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jf_back_id:
                finish();
            break;
            case R.id.tv_jifengrule_id://跳转到积分规则界面
                Intent intent=new Intent(integral_detail_activity.this, My_activity.class);
                intent.putExtra("my","integral_rule");
                startActivity(intent);
                break;
        }
    }
}
