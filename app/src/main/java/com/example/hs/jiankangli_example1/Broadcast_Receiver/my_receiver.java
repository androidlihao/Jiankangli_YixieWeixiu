package com.example.hs.jiankangli_example1.Broadcast_Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.hs.jiankangli_example1.My_activity;

import org.json.JSONException;
import org.json.JSONObject;

import Inter.Globle;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by 李浩 on 2016/11/28.
 */
public class my_receiver extends BroadcastReceiver {
    private static final String TAG = "test" ;

    public my_receiver() {

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String title = bundle.getString(JPushInterface.EXTRA_TITLE);//标题
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);//内容
        String json = bundle.getString(JPushInterface.EXTRA_EXTRA);//附加信息的json
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {

        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            System.out.println("收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            System.out.println("收到了通知"+json);
            // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            System.out.println("用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为
            Intent i = new Intent(context,My_activity.class); //自定义打开的界面
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                JSONObject jsonObject=new JSONObject(json);
                i.putExtra("notificationMessage",Globle.TEST_URL+"/"+jsonObject.getString("pageURL"));
                i.putExtra("my","notofication_message");
                context.startActivity(i);
            } catch (JSONException e){
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
        //根据传过来的消息处理
        Log.i(TAG,"MyReceiver接收到的消息内容： title: "+ title + " message: " + message);
    }
}