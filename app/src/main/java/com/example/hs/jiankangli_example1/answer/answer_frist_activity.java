package com.example.hs.jiankangli_example1.answer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import AsyncTask.UoLoadAsyncTask;
import Inter.getPath_Inter;
import bean.Pic_bean;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import utils.Pic_MultiImageSelector_util;
import utils.Statubars;
import utils.ninelayout_Adapter;

/**
 * Created by 李浩 on 2016/11/10.
 */
public class answer_frist_activity extends AutoLayoutActivity implements getPath_Inter {

    private EditText et_explain_id;
    private Button btn_next_id;
    private GridView gv_id;
    private LinkedList<LinkedList<String>> list;
    private ninelayout_Adapter adapter;
    private AutoLinearLayout back;
    private ArrayList<String> deleteList;
    private Bundle bundle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.answer_first_layout);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        bundle = getIntent().getExtras();
        deleteList=bundle.getStringArrayList("deleteList");
        initView();
        setOnClickList();
        initData();
        aboutGridView();
    }
    private void initView() {
        //发现故障描述输入的控件
        et_explain_id = (EditText) findViewById(R.id.et_explain_id);
        //发现下一步按钮
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        back = (AutoLinearLayout) findViewById(R.id.sets_back_id);
        //发现girdView
        gv_id = (GridView)findViewById(R.id.gv_id);
        TextView tv_big_titleBar_id = (TextView) findViewById(R.id.tv_big_titleBar_id);
        TextView tv_small_titleBar_id = (TextView) findViewById(R.id.tv_small_titleBar_id);
        switch (bundle.getString("content_category_id")){
            case "1":
                tv_big_titleBar_id.setText("故障详情");
                tv_small_titleBar_id.setText("解决步骤");
                break;
            case "3":
                tv_big_titleBar_id.setText("错误编码");
                tv_small_titleBar_id.setText("解释");
                break;
        }
    }
    private void setOnClickList() {
        btn_next_id.setOnClickListener(new MyOnListener());
        back.setOnClickListener(new MyOnListener());
    }
    private void initData() {
        list=new LinkedList<>();
        LinkedList<String> linkedList=new LinkedList<>();
        linkedList.addFirst("tianjia");
        list.add(linkedList);
        adapter = new ninelayout_Adapter(this,list);
        gv_id.setAdapter(adapter);//绑定适配器
    }

    @Override
    public void getpath(LinkedList<LinkedList<String>> urlList) {
        list=urlList;
    }
    private class MyOnListener implements View.OnClickListener{
        private Intent intent;
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.sets_back_id:
                    finish();
                    break;
                case R.id.btn_next_id:
                    switch (bundle.getString("content_category_id")){
                        case "1":
                            intent = new Intent(answer_frist_activity.this,answer_final_activity.class);
                            bundle.putString("steps_resolve",et_explain_id.getText().toString());//解决步骤
                            break;
                        case "3":
                            intent = new Intent(answer_frist_activity.this,answer_second_activity.class);
                            bundle.putString("explanation",et_explain_id.getText().toString());//解释
                            break;
                    }
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
            }
        }
}
    private void aboutGridView() {
        gv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==gv_id.getAdapter().getCount()-1&&(list.get(i).getFirst()).equals("tianjia")){//为最后一项，并且最后一项为可以添加
                    new Pic_MultiImageSelector_util(list,answer_frist_activity.this).getMultiImageSelector(2,5-(list.size()-1));
                }
            }
        });
        gv_id.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if(!(list.get(i).getFirst()).equals("tianjia")){//如果当前不为添加
                    gv_id.getChildAt(i).findViewById(R.id.iv_delte_id).setVisibility(View.VISIBLE);
                    gv_id.getChildAt(i).findViewById(R.id.iv_delte_id).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //如果当前图片上传成功了，将所有删除的记录下来,用于图片删除优化操作
                            if(list.get(i).size()==3&&!list.get(i).get(1).equals("failed")){
                                deleteList.add(list.get(i).getLast());
                            }
                            list=new Pic_MultiImageSelector_util(null,null).deleteUrlList(list,i);//去删除数据，然后刷新适配器
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
                ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);//得到返回来的本地地址集合
                if(path.size()==6){
                    path.remove(5);
                }
                list=new Pic_MultiImageSelector_util(null,answer_frist_activity.this).getURLList(list,path);//根据返回来的值去得到GridView的数据源
                adapter.notifyDataSetChanged();//先显示
                Pic_bean.answer_frist_pic=false;//开始上传，变为false不可上传
                new UoLoadAsyncTask(answer_frist_activity.this,path,list,"answer_frist").execute();//开始执行上传任务
            }
        }
    }
}
