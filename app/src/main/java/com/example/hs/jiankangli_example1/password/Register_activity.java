package com.example.hs.jiankangli_example1.password;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import Inter.Globle;
import Inter.get_net_Info;
import utils.Common_utils;
import utils.RequestNet;
import utils.Statubars;

/**
 * Created by 李浩 on 2016/9/22.
 */
public class Register_activity extends AutoLayoutActivity implements get_net_Info {

    private Button btn_register;
    private EditText reg_num_id;
    private TextView tv_getnum_id;
    private TimeCount time;
    private final static String REGISTER_URL= Globle.TEST_URL+"/api/msgCode/sendMsgCode";
    private final static String XIAOZHENF_URL=Globle.TEST_URL+"/api/msgCode/checkMsgCode";
    private Handler MyHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==200){
                //解析返回来的数据
                JSONObject js = null;
                try {
                    js = new JSONObject(msg.obj.toString());
                    String code = (String) js.get("code");
                    if (code.equals("200")) {
                        Toast.makeText(getApplicationContext(), "获取验证码成功", Toast.LENGTH_SHORT).show();
                        //然后实现验证注册
                    }else if(code.equals("20600")){
                        Toast.makeText(getApplicationContext(), "验证码获取频繁", Toast.LENGTH_SHORT).show();
                    }else if(code.equals("20700")){
                        Toast.makeText(getApplicationContext(), "手机号已注册", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), js.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if(msg.what==201){
                JSONObject js = null;
                try {
                    js = new JSONObject(msg.obj.toString());
                    String code = js.getString("code");
                    String message=js.getString("message");
                    if (code.equals("200")) {
                        Toast.makeText(getApplicationContext(), "验证码输入正确", Toast.LENGTH_SHORT).show();
                        //跳转到密码设置页面
                        Intent intent=new Intent(Register_activity.this,Register_finish.class);
                        intent.putExtra("mobile",reg_num_id.getText().toString());
                        intent.putExtra("biaoti",toolbars);
                        intent.putExtra("type",type);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private EditText et_yanzhengma_id;
    private String toolbars;
    private TextView tv_biaoti_id;
    private String type;
    private AutoLinearLayout sets_back_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏的颜色
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        setContentView(R.layout.register_activity);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        toolbars = getIntent().getStringExtra("forget");
        type = getIntent().getStringExtra("type");
        //初始化界面控件实例
        initView();
        tv_biaoti_id.setText(toolbars);
        if(toolbars.equals("忘记密码")){
            btn_register.setText("下一步");
        }
        //设置监听
        setOnClickListener();
    }
    private void setOnClickListener() {
        //设置监听事件
         tv_getnum_id.setOnClickListener(new MyOnClickListener());
         btn_register.setOnClickListener(new MyOnClickListener());
         sets_back_id.setOnClickListener(new MyOnClickListener());
    }

    private void initView() {
        btn_register = (Button) findViewById(R.id.btn_register);//发现注册按钮
        reg_num_id = (EditText) findViewById(R.id.reg_num_id);//手机号码输入框
        tv_getnum_id = (TextView) findViewById(R.id.tv_getnum_id);//获取验证码的按钮
        et_yanzhengma_id = (EditText)findViewById(R.id.et_yanzhengma_id);
        //发现标题按钮
        tv_biaoti_id = (TextView) findViewById(R.id.tv_biaoti_id);
        //发现返回键
        sets_back_id = (AutoLinearLayout) findViewById(R.id.sets_back_id);
    }

    @Override
    public void getinfo(String result) {
        Message msg=new Message();
        msg.what=201;
        msg.obj=result;
        MyHandler.sendMessage(msg);
    }

    private class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_getnum_id:
                    //获取验证码
                    getNum();
                    break;
                case R.id.btn_register:
                    //实现注册操作
                    register();
                    break;
                case R.id.sets_back_id:
                    finish();
                    break;
            }
        }
    }

    private void register() {
         if (TextUtils.isEmpty(reg_num_id.getText().toString())){
             Toast.makeText(this, "手机号码不能为空!", Toast.LENGTH_SHORT).show();
             return;
         }
         if (TextUtils.isEmpty(et_yanzhengma_id.getText().toString())){
             Toast.makeText(getApplicationContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
             return;
         }
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobileCode",reg_num_id.getText().toString());
            jsonObject.put("code",et_yanzhengma_id.getText().toString());
            RequestNet.queryServer(jsonObject,XIAOZHENF_URL,Register_activity.this,"register");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void getNum() {
        if (TextUtils.isEmpty(reg_num_id.getText().toString())) {//手机输入按钮
            Toast.makeText(getApplicationContext(), "请输入11位手机号", Toast.LENGTH_SHORT).show();
        } else {
            if (isMobileNO(reg_num_id.getText().toString())) {
                time = new TimeCount(60000, 1000);
                time.start();
                //开启子线程，网络请求任务
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //  在这里获取验证码
                        RequestParams params = new RequestParams(REGISTER_URL);
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("mobileCode",reg_num_id.getText().toString());
                            //设置调用的平台的类型
                            jsonObject.put("type",type);
                            String jsonString = Base64.encodeToString(jsonObject.toString().getBytes(), Base64.NO_WRAP);
                            params.setBodyContent(jsonString);
                            x.http().post(params, new Callback.CacheCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    //获取返回值
                                    //将获取的值利用handler返回给主线程，然后主线程再次进行网络请求
                                    Message msg=new Message();
                                    msg.what=200;
                                    msg.obj=result;
                                    MyHandler.sendMessage(msg);
                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {

                                }

                                @Override
                                public void onCancelled(CancelledException cex) {

                                }

                                @Override
                                public void onFinished() {

                                }

                                @Override
                                public boolean onCache(String result) {
                                    return false;
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            } else {
                Toast.makeText(getApplicationContext(), "请输入正确手机号", Toast.LENGTH_SHORT).show();
            }

        }

    }
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            tv_getnum_id.setText("重新获取");
            tv_getnum_id.setTextColor(getResources().getColor(R.color.gray));
            tv_getnum_id.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_getnum_id.setClickable(false);
            tv_getnum_id.setTextColor(getResources().getColor(R.color.lightgray));
            tv_getnum_id.setText(millisUntilFinished / 1000 + "秒后重试");
        }
    }

    /**
     * 判断是不是手机号码
     *
     * @param mobiles
     *            手机号码
     * @return
     */
    public boolean isMobileNO(String mobiles) {
        String telRegex = "[1][34578]\\d{9}";
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

}
