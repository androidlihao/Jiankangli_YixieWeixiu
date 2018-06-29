package com.example.hs.jiankangli_example1;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.umeng.socialize.UMShareAPI;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;
import java.util.Timer;
import java.util.TimerTask;

import com.example.hs.jiankangli_example1.seek.seek_activity;
import bean.Pic_bean;
import fragment.Home_fragment;
import fragment.Ower_fragment;
import Inter.Globle;
import utils.Common_utils;
import utils.Statubars;
import utils.UpdateManager;

public class MainActivity extends AutoLayoutActivity {
    private AutoLinearLayout al_seek_id;
    private RadioButton btn_home_id;
    private RadioButton btn_ower_id;
    private Home_fragment home_fragment;
    private Ower_fragment ower_fragment;
    private Fragment mFragment;//当前显示的Fragment
    private RadioGroup navigationBar;
    private RelativeLayout toolbar_id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose", ContextCompat.getColor(this, R.color.statue));
        setContentView(R.layout.activity_main);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        UpdateManager mUpdateManager = new UpdateManager(this);
        mUpdateManager.showNoticeDialog();
        //初始化界面控件实例
        initView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_fragment,home_fragment).commit();
        mFragment = home_fragment;
    }
    //初始化界面控件实例
    private void initView(){
        navigationBar = (RadioGroup) findViewById(R.id.navigation_btn);
        //发现搜索按钮
        al_seek_id = (AutoLinearLayout) findViewById(R.id.al_seek_id);
        //给这些按钮设置监听事件
        btn_home_id = (RadioButton) findViewById(R.id.btn_home_id);
        btn_ower_id = (RadioButton) findViewById(R.id.btn_ower_id);
        toolbar_id = (RelativeLayout) findViewById(R.id.toolbar_id);
        home_fragment = new Home_fragment();
        ower_fragment = new Ower_fragment();
        al_seek_id.setVisibility(View.VISIBLE);
        navigationBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int checkedId) {
                switch (checkedId){
                    case R.id.btn_home_id:
                        btn_home_id.setChecked(true);
                        btn_ower_id.setChecked(false);
                        toolbar_id.setVisibility(View.VISIBLE);
                        switchFragment(home_fragment);
                        break;
                    case R.id.btn_ower_id:
                        btn_home_id.setChecked(false);
                        btn_ower_id.setChecked(true);
                        toolbar_id.setVisibility(View.GONE);
                        switchFragment(ower_fragment);
                        break;
                }
            }
        });
        setOnClickListener();
    }
    private void setOnClickListener() {
        al_seek_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传值过去
                Intent intent=new Intent(MainActivity.this, seek_activity.class);
                intent.putExtra("tags","1");
                startActivity(intent);

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!Common_utils.isNetworkAvailable(this)){
            Toast.makeText(this, "当前没有可用网络！", Toast.LENGTH_LONG).show();
        }
    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub\
        if(keyCode == KeyEvent.KEYCODE_BACK&&Pic_bean.URL.equals(Globle.TEST_URL+"/app/")&&mFragment==home_fragment)
        {
            exitBy2Click(); //调用双击退出函数
        }else if(keyCode == KeyEvent.KEYCODE_BACK&&mFragment==ower_fragment){
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;
    private void exitBy2Click(){
        Timer tExit;
        if (!isExit) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次返回键退出医械梦想家", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run(){
                    isExit = false; //取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            SysApplication.getInstance().exit();//退出app，直接退出
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    private void switchFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //判断当前显示的Fragment是不是切换的Fragment
        if(mFragment!=fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                fragmentTransaction .hide(mFragment)
                        .add(R.id.main_fragment,fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                fragmentTransaction.hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }
}
