package com.example.hs.jiankangli_example1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import Inter.Globle;
import utils.Common_utils;
import utils.JavaScriptObject;
import utils.Statubars;

public class consultActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_content_id;
    private EditText et_companyName_id;
    private EditText et_contacts_id;
    private EditText et_address_id;
    private EditText et_phoneNumber_id;
    private Button btn_submit_id;
    private String partId;
    private PopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.activity_consult);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_id);
        this.setSupportActionBar(toolbar);
        partId = getIntent().getStringExtra("partId");
        Log.i("TAG", "onCreate: "+partId);
    }

    @Override
    protected void onStart() {
        initView();
        super.onStart();
    }

    private void initView() {
        View view=findViewById(R.id.sets_back_id);
        view.setOnClickListener(this);
        et_content_id = (EditText) findViewById(R.id.et_content_id);
        et_companyName_id = (EditText)findViewById(R.id.et_companyName_id);
        et_contacts_id = (EditText)findViewById(R.id.et_contacts_id);
        et_phoneNumber_id = (EditText)findViewById(R.id.et_phoneNumber_id);
        et_address_id = (EditText)findViewById(R.id.et_address_id);
        btn_submit_id = (Button) findViewById(R.id.btn_submit_id);
        btn_submit_id.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        popupWindow = new PopupWindow();
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setContentView(LayoutInflater.from(getApplication()).inflate(R.layout.popwinds, null));
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sets_back_id:
                finish();
                break;
            case R.id.btn_submit_id:
                String content=et_content_id.getText().toString().trim();
                String phone=et_phoneNumber_id.getText().toString().trim();
                String address=et_address_id.getText().toString().trim();
                String contacts=et_contacts_id.getText().toString().trim();
                String companyName=et_companyName_id.getText().toString().trim();
                if (content.isEmpty()||content==null){
                    Toast.makeText(this, "请输入您的咨询详情再提交哦！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (companyName.isEmpty()||companyName==null){
                    Toast.makeText(this, "请输入您的公司名称再提交哦！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (address.isEmpty()||address==null){
                    Toast.makeText(this, "请输入您的地址再提交哦！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (contacts.isEmpty()||contacts==null){
                    Toast.makeText(this, "请输入您的联系人姓名再提交哦！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.isEmpty()||phone==null){
                    Toast.makeText(this, "请输入您的联系电话，方便我们及时给您反馈！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Common_utils.isMobileNO(phone)){
                    Toast.makeText(this, "手机号码格式有误，请仔细检查！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //开始提交任务
                try {
                    final JSONObject jsonObject=new JSONObject();
                    jsonObject.put("partId",partId);
                    jsonObject.put("content",content);
                    jsonObject.put("companyName",companyName);
                    jsonObject.put("phone",phone);
                    jsonObject.put("address",address);
                    jsonObject.put("contacts",contacts);
                    jsonObject.put("memberId",new JavaScriptObject(this).getMemberid(""));
                    Log.i("TAG", "onClick: "+jsonObject);
                    RequestParams params=new RequestParams(Globle.TEST_URL+"/api/partConsult/addConsult");
                    if (jsonObject!=null){
                        String jsonString = Base64.encodeToString(jsonObject.toString().getBytes(),Base64.NO_WRAP);//base64加密
                        params.setBodyContent(jsonString);
                    }
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject jsonObject1=new JSONObject(result);
                                switch (jsonObject1.getString("code")){
                                    case "200":
                                        popupWindow.showAtLocation(consultActivity.this.getWindow().getDecorView(), Gravity.CENTER,0,0);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                popupWindow.dismiss();
                                                finish();
                                            }
                                        },2000);
                                        break;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

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
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }
}
