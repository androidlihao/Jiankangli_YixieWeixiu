package com.example.hs.jiankangli_example1;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import com.example.hs.jiankangli_example1.password.Change_password_activity;
import com.example.hs.jiankangli_example1.password.Login_activity;

import java.util.HashSet;
import java.util.Set;

import bean.personal;
import cn.jpush.android.api.JPushInterface;
import utils.Common_utils;
import utils.DataCleanManager;
import utils.Statubars;
import utils.XUtilsDB;

/**
 * Created by 李浩 on 2016/9/18.
 */
public class Set_Activity extends AutoLayoutActivity{
    private AutoLinearLayout set_back_id;
    private RelativeLayout rl_cleanCache_id;
    private String totalCacheSize;
    private TextView tv_cache_id;
    private RelativeLayout rl_aboutWe_id;
    private TextView tv_exit_id;
    private AlertDialog.Builder builder;
    private AlertDialog create;
    private TextView yes_exit;
    private TextView qx_exit;
    private SharedPreferences.Editor edit;
    private TextView yes_clear;
    private TextView qx_cache;
    private RelativeLayout change_pwd_id;
    private RelativeLayout rl_yijian_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏
        SharedPreferences sp=getSharedPreferences("config",0);
        edit = sp.edit();
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.set_activity);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        initView();
        setOnClickListtener();
    }
    //当界面获取焦点的时候
    @Override
    protected void onResume() {
        //获取全部的缓存信息
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(getApplicationContext());
            tv_cache_id.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!new Common_utils(getApplicationContext()).isLogin()){
            tv_exit_id.setVisibility(View.GONE);//让退出按钮隐藏
        }
        super.onResume();
    }
    private void setOnClickListtener() {
        set_back_id.setOnClickListener(new MyOnClickListener());
        rl_cleanCache_id.setOnClickListener(new MyOnClickListener());
        rl_aboutWe_id.setOnClickListener(new MyOnClickListener());
        tv_exit_id.setOnClickListener(new MyOnClickListener());
        change_pwd_id.setOnClickListener(new MyOnClickListener());
        rl_yijian_id.setOnClickListener(new MyOnClickListener());
    }

    private void initView() {
        //初始化界面控件实例
        set_back_id = (AutoLinearLayout) findViewById(R.id.set_back_id);
        //发现清除缓存的控件按钮
        rl_cleanCache_id = (RelativeLayout)findViewById(R.id.rl_cleanCache_id);
        //发现缓存展现控件
        tv_cache_id = (TextView) findViewById(R.id.tv_cache_id);
        //发现界面控件实例
        rl_aboutWe_id = (RelativeLayout) findViewById(R.id.rl_aboutWe_id);
        //发现退出按钮
        tv_exit_id = (TextView) findViewById(R.id.tv_exit_id);
        //发现修改密码按钮
        change_pwd_id = (RelativeLayout) findViewById(R.id.change_pwd_id);
        //发现意见反馈按钮
        rl_yijian_id = (RelativeLayout) findViewById(R.id.rl_yijian_id);
    }
    public class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.set_back_id://当用户点击的返回按钮，返回上一界面
                    finish();//结束当前界面
                    break;
                case R.id.rl_cleanCache_id:
                    //弹出弹窗
                    showCache();
                    break;
                case R.id.rl_aboutWe_id:
                     Intent intent=new Intent(Set_Activity.this,My_activity.class);
                     intent.putExtra("my","about_me");
                     startActivity(intent);
                    break;
                    //TODO
                case R.id.tv_exit_id:
                    //弹出弹窗
                    showPopwindow();
                    break;
                case R.id.qx_exit:
                    create.dismiss();
                    break;
                case R.id.yes_exit:
                    //确认退出
                    Set<String> strings=new HashSet<>();
                    JPushInterface.setAliasAndTags(Set_Activity.this, "", strings,new Login_activity().mAliasCallback);
                    edit.clear();
                    edit.commit();//清除用户登录信息
                    //删除保存在数据库中的信息
                    DbManager db=x.getDb(XUtilsDB.getDBconfig());
                    try {
                        db.dropTable(personal.BodyBean.DataBean.class);//将存储的表格也删除
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                    create.dismiss();
                    Toast.makeText(Set_Activity.this,"注销成功",Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case R.id.qx_cache:
                    create.dismiss();
                    break;
                case R.id.yes_clear:
                    DataCleanManager.clearAllCache(getApplicationContext());
                    //清除掉缓存
                    Toast.makeText(Set_Activity.this, "缓存清理成功",Toast.LENGTH_SHORT).show();
                    tv_cache_id.setText("0K");
                    create.dismiss();
                    break;
                case R.id.change_pwd_id:
                    //跳转到修改密码页
                    boolean islogin=new Common_utils(getApplicationContext()).isLogin();
                    Intent intent1;
                    if(islogin){
                        intent1=new Intent(Set_Activity.this, Change_password_activity.class);
                    }else{
                        intent1=new Intent(Set_Activity.this, Login_activity.class);
                        Toast.makeText(Set_Activity.this, "未登陆无法修改密码!", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(intent1);
                    break;
                case R.id.rl_yijian_id:
                    //跳转到意见反馈页
                    Intent intents=new Intent(Set_Activity.this,commit_opinion_activity.class);
                    startActivity(intents);
                    break;
            }
        }
    }
    private void showCache() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;//获取当前的SDK版本
        if (currentapiVersion >= 21) {
            builder = new AlertDialog.Builder(Set_Activity.this, R.style.dialogActivity);
        } else {
            builder = new AlertDialog.Builder(Set_Activity.this);
        }
        //TODO
        create = builder.create();
        Window w = create.getWindow();//获取一个窗口

        WindowManager.LayoutParams lp = w.getAttributes();//窗口布局填充器
        lp.y = Set_Activity.this.getWindow().getDecorView().getHeight();
        create.show();
        View view_pop_1 = getLayoutInflater().inflate(R.layout.clearcache_layout, null);//获取弹出的布局文件填充布局
        create.setContentView(view_pop_1);
        yes_clear = (TextView) view_pop_1.findViewById(R.id.yes_clear);
        qx_cache = (TextView) view_pop_1.findViewById(R.id.qx_cache);
        yes_clear.setOnClickListener(new MyOnClickListener());
        qx_cache.setOnClickListener(new MyOnClickListener());
    }

    //点击头像，弹出AlertDialog
    private void showPopwindow() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;//获取当前的SDK版本
        if (currentapiVersion >= 21) {
            builder = new AlertDialog.Builder(Set_Activity.this, R.style.dialogActivity);
        } else {
            builder = new AlertDialog.Builder(Set_Activity.this);
        }
        //TODO
        create = builder.create();
        Window w = create.getWindow();//获取一个窗口

        WindowManager.LayoutParams lp = w.getAttributes();//窗口布局填充器
        lp.y = Set_Activity.this.getWindow().getDecorView().getHeight();
        create.show();
        View view_pop_1 = getLayoutInflater().inflate(R.layout.exit_layout, null);//获取弹出的布局文件填充布局
        create.setContentView(view_pop_1);
        //发现控件
        yes_exit = (TextView) view_pop_1.findViewById(R.id.yes_exit);
        qx_exit = (TextView) view_pop_1.findViewById(R.id.qx_exit);
        yes_exit.setOnClickListener(new MyOnClickListener());
        qx_exit.setOnClickListener(new MyOnClickListener());
    }
}
