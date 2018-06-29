package com.example.hs.jiankangli_example1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.zhy.autolayout.AutoLayoutActivity;


/**
 * Created by 李浩 on 2016/10/26.
 */
public class welcome_activity extends AutoLayoutActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        Handler handler = new Handler();
        //当计时结束,跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(welcome_activity.this, MainActivity.class);
                startActivity(intent);
                welcome_activity.this.finish();//结束当前页面
            }
        }, 3000);
    }

}
