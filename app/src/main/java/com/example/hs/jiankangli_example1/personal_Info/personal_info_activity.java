package com.example.hs.jiankangli_example1.personal_Info;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Inter.get_net_Info;
import bean.info;
import de.hdodenhof.circleimageview.CircleImageView;
import utils.Common_utils;
import utils.Date_format;
import Inter.Globle;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/9/19.
 */
public class personal_info_activity extends AutoLayoutActivity implements get_net_Info {

    private AutoLinearLayout set_back_id;
    private TextView tv_bianji_id;
    private CircleImageView iv_civ_id;
    private TextView tv_name_id;
    private TextView tv_sex_id;
    private TextView tv_nickName_id;
    private TextView tv_birth_id;
    private info info;
    private TagFlowLayout et_skill_id;
    private ArrayList<String> skillList = new ArrayList<>();
    private List<info.BodyBean.DataBean.ProductModelBean> productModel;
    private ArrayList<String> idlist=new ArrayList<>();
    private TextView tv_ivtation_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.jcxx_activity);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        initView(); //初始化界面控件实例
        setOnClickListener();//设置监听器
        tv_name_id.setText(new Common_utils(personal_info_activity.this).getName());
        tv_nickName_id.setText(new Common_utils(personal_info_activity.this).getNickName());
        int i=new Common_utils(personal_info_activity.this).getSex();
        if(i==1){
            tv_sex_id.setText("男");
        }else{
            tv_sex_id.setText("女");
        }
        Bitmap bitmap= (Bitmap) getIntent().getExtras().get("bitmap");
        iv_civ_id.setImageBitmap(bitmap);
        tv_ivtation_id.setText(new Common_utils(personal_info_activity.this).getCode());
        requestHttp();
    }
    private void requestHttp() {
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("member_id",new Common_utils(getApplication()).getMemberid());
            RequestNet.queryServer(jsonObject,Globle.TEST_URL+"/api/member/selectMemberInfoById",personal_info_activity.this,"personal_info_activity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void setOnClickListener() {
        set_back_id.setOnClickListener(new MyOnClickListener());
        tv_bianji_id.setOnClickListener(new MyOnClickListener());
    }
    private void initView() {
        set_back_id = (AutoLinearLayout) findViewById(R.id.set_back_id);
        tv_bianji_id = (TextView) findViewById(R.id.tv_bianji_id);
        iv_civ_id = (CircleImageView) findViewById(R.id.iv_civ_id);//头像
        tv_name_id = (TextView) findViewById(R.id.tv_Name_id);
        tv_sex_id = (TextView) findViewById(R.id.tv_Sex_id);
        tv_nickName_id = (TextView) findViewById(R.id.tv_nickName_id);
        tv_birth_id = (TextView) findViewById(R.id.tv_birth_id);
        et_skill_id = (TagFlowLayout) findViewById(R.id.liushibuju_id);
        tv_ivtation_id = (TextView) findViewById(R.id.tv_ivtation_id);
    }
    @Override
    public void getinfo(String result) {
        info = com.alibaba.fastjson.JSONObject.parseObject(result,info.class);
        //填充出生年月和我的技能
        productModel = info.getBody().getData().getProductModel();
        tv_birth_id.setText(Date_format.timeStamp2Date(info.getBody().getData().getBirthday()+"","yyyy-MM-dd"));
        for(int i=0;i<productModel.size();i++){
            skillList.add(productModel.get(i).getName());
            idlist.add(productModel.get(i).getProductCategoriesId()+"");
        }
        et_skill_id.setAdapter(new TagAdapter(skillList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                View convertView = LayoutInflater.from(personal_info_activity.this).inflate(R.layout.tag_view, null);
                Button button = (Button) convertView.findViewById(R.id.tv_tagview_id);
                button.setText(o.toString());
                return convertView;
            }
        });
    }

    public class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.set_back_id:
                    finish();
                    break;
                case R.id.tv_bianji_id:
                    //跳转到编辑页面
                    Intent intent=new Intent(personal_info_activity.this,Edit_Personal_Info_activity.class);
                    intent.putExtra("name",tv_name_id.getText().toString());
                    intent.putExtra("nickname",tv_nickName_id.getText().toString());
                    intent.putExtra("sex",tv_sex_id.getText().toString());
                    intent.putExtra("birth",tv_birth_id.getText().toString());
                    intent.putStringArrayListExtra("jineng",skillList);
                    intent.putStringArrayListExtra("jnid",idlist);
                    iv_civ_id.setDrawingCacheEnabled(true);
                    Bitmap obmp = Bitmap.createBitmap(iv_civ_id.getDrawingCache());
                    intent.putExtra("bitmap",obmp);
                    iv_civ_id.setDrawingCacheEnabled(false);
                    startActivity(intent);
                    break;
            }
        }
    }
}
