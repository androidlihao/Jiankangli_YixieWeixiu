package com.example.hs.jiankangli_example1.certified_company;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLayoutActivity;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import AsyncTask.Upload_Photo;
import bean.Company_bean;
import bean.Pic_bean;
import bean.getPic_path;
import utils.Statubars;
import utils.get_absolute_UrL;
import utils.showPopWindows;

/**
 * Created by 李浩 on 2016/11/15.
 */
public class Certified_company_three_activity extends AutoLayoutActivity {

    private View sets_back_id;
    private static final int PHOTO_REQUEST_CAMERA = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private Button btn_next_id;
    private Bundle bundle;
    private ImageView iv_identity_front_id;
    private ImageView iv_identity_back_id;
    private TextView tv_titlt1_id;
    private TextView tv_titlt2_id;
    private String tag;
    private ImageView iv_logo_id;
    private ImageView iv_license_id;
    private boolean b=false;
    private boolean c=false;
    private boolean d=false;
    private boolean e=false;
    private File tempFile;
    private Company_bean cb;
    private List<Company_bean.BodyBean.DataBean.PictureBean> picture;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this, getWindow(), "zhaose", getResources().getColor(R.color.statue));
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        setContentView(R.layout.company_three_layout);
        answer_Application.getInstance().addActivity(this);
        tag = getIntent().getStringExtra("tags");
        bundle=getIntent().getExtras();
        cb = (Company_bean) getIntent().getSerializableExtra("company_info");//进入界面,将回显参数传递到当前界面
        if(cb!=null){
            picture=cb.getBody().getData().getPicture();
        }
        initView();
        setOnClickListener();
        initData();
    }
    private void setOnClickListener() {
        iv_identity_front_id.setOnClickListener(new MyOnClickListener());
        iv_identity_back_id.setOnClickListener(new MyOnClickListener());
        iv_logo_id.setOnClickListener(new MyOnClickListener());
        iv_license_id.setOnClickListener(new MyOnClickListener());
        sets_back_id.setOnClickListener(new MyOnClickListener());
        btn_next_id.setOnClickListener(new MyOnClickListener());
    }
    private void initView() {
        sets_back_id = findViewById(R.id.sets_back_id);
        btn_next_id = (Button) findViewById(R.id.btn_next_id);
        iv_identity_front_id = (ImageView) findViewById(R.id.iv_identity_front_id);
        iv_identity_back_id = (ImageView) findViewById(R.id.iv_identity_back_id);
        iv_logo_id = (ImageView) findViewById(R.id.iv_logo_id);
        iv_license_id = (ImageView) findViewById(R.id.iv_license_id);
        tv_titlt1_id = (TextView) findViewById(R.id.tv_titlt1_id);
        tv_titlt2_id = (TextView) findViewById(R.id.tv_titlt2_id);
    }
    //进入身份证界面，开始得到
    private void initData() {
        switch (tag){
            case "sfz":
                tv_titlt1_id.setText("法人身份证正面");
                tv_titlt2_id.setText("法人身份证反面");
                iv_logo_id.setVisibility(View.GONE);
                iv_license_id.setVisibility(View.GONE);
                Pic_bean.zhengmian_boolean=false;
                Pic_bean.fanmian_boolean=false;//默认没有上传，如果回显说明已经上传成功
                if(cb!=null){//回显界面为身份证界面
                    for(int i=0;i<picture.size();i++){
                        switch (picture.get(i).getType()){
                            case 5:
                                Picasso.with(this).load(picture.get(i).getImagePath()).into(iv_identity_front_id);
                                getPic_path huixian_zhengmianpath=new getPic_path();
                                huixian_zhengmianpath.setPic_type(5);
                                huixian_zhengmianpath.setLocal_path(picture.get(i).getLocalImagePath());
                                Pic_bean.zhengmian_path=huixian_zhengmianpath;
                                break;
                            case 6:
                                Picasso.with(this).load(picture.get(i).getImagePath()).into(iv_identity_back_id);
                                getPic_path huixian_fanmianpath=new getPic_path();
                                huixian_fanmianpath.setPic_type(6);
                                huixian_fanmianpath.setLocal_path(picture.get(i).getLocalImagePath());
                                Pic_bean.back_path=huixian_fanmianpath;
                                break;
                        }
                    }
                    b=true;
                    c=true;//默认可以进行下一步
                    Pic_bean.zhengmian_boolean=true;
                    Pic_bean.fanmian_boolean=true;//默认已经上传成功了
                }
                break;
            case "zz":
                tv_titlt1_id.setText("公司logo");
                tv_titlt2_id.setText("企业营业执照");
                iv_identity_front_id.setVisibility(View.GONE);
                iv_identity_back_id.setVisibility(View.GONE);
                Pic_bean.logo_boolean=false;
                Pic_bean.zhizhao_boolean=false;//默认全部上传成功了
                if(cb!=null){
                    for(int i=0;i<picture.size();i++){
                        switch (picture.get(i).getType()){
                            case 4:
                                Picasso.with(this).load(picture.get(i).getImagePath()).into(iv_logo_id);
                                getPic_path huixian_logopath=new getPic_path();
                                huixian_logopath.setPic_type(4);
                                huixian_logopath.setLocal_path(picture.get(i).getLocalImagePath());
                                Pic_bean.logo_path=huixian_logopath;
                                break;
                            case 7:
                                Picasso.with(this).load(picture.get(i).getImagePath()).into(iv_license_id);
                                getPic_path huixian_zhizhaopath=new getPic_path();
                                huixian_zhizhaopath.setPic_type(7);
                                huixian_zhizhaopath.setLocal_path(picture.get(i).getLocalImagePath());
                                Pic_bean.zhizhao_path=huixian_zhizhaopath;
                                break;
                        }
                    }
                    d=true;
                    e=true;
                    Pic_bean.logo_boolean=true;
                    Pic_bean.zhizhao_boolean=true;//默认全部上传成功了
                }
                break;
        }
    }
    private  int a;
    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.sets_back_id:
                    finish();
                    break;
                case R.id.iv_identity_back_id://身份证反面
                    a = 6;
                    new showPopWindows(Certified_company_three_activity.this).showPopwindow();
                    break;
                case R.id.iv_identity_front_id://身份证正面
                    a = 5;
                    new showPopWindows(Certified_company_three_activity.this).showPopwindow();
                    break;
                case R.id.iv_logo_id://身份证正面
                    a = 4;
                    new showPopWindows(Certified_company_three_activity.this).showPopwindow();
                    break;
                case R.id.iv_license_id://身份证正面
                    a = 7;
                    new showPopWindows(Certified_company_three_activity.this).showPopwindow();
                    break;
                case R.id.btn_next_id:
                    if(tag.equals("sfz")){
                        if(b==false||c==false){
                            Toast.makeText(getApplicationContext(),"请先上传您的身份证照片再进行下一步验证!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent=new Intent(Certified_company_three_activity.this,Certified_company_three_activity.class);
                        intent.putExtras(bundle);
                        intent.putExtra("company_info",cb);//将回显数据传递给下一个界面显示
                        intent.putExtra("tags","zz");
                        startActivity(intent);
                    }else if(tag.equals("zz")){//如果是执照
                        if(d==false||e==false){
                            Toast.makeText(getApplicationContext(), "请先上传您公司的logo和营业执照以后再进行下一步验证！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent=new Intent(Certified_company_three_activity.this,Certified_company_four_activity.class);
                        intent.putExtra("company_info",cb);//将回显数据传递给下一个界面显示
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    break;
            }
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {//返回码
        if (requestCode == PHOTO_REQUEST_GALLERY) {//如果是相册
            if (data!= null) {
                Uri uri = data.getData();//获取的Uri
                File file=new File(get_absolute_UrL.getImageAbsolutePath(Certified_company_three_activity.this,uri));
                if(a==5){//此时为身份证正面
                    b=true;
                    Pic_bean.zhengmian_boolean=false;
                    x.image().bind(iv_identity_front_id,Uri.fromFile(file).getPath());
                }else if(a==6){
                    c=true;
                    Pic_bean.fanmian_boolean=false;
                    x.image().bind(iv_identity_back_id,Uri.fromFile(file).getPath());
                }else if(a==4){
                    d=true;
                    Pic_bean.logo_boolean=false;
                    x.image().bind(iv_logo_id,Uri.fromFile(file).getPath());
                }else if(a==7){
                    e=true;
                    Pic_bean.zhizhao_boolean=false;
                    x.image().bind(iv_license_id,Uri.fromFile(file).getPath());
                }
                new Upload_Photo(Certified_company_three_activity.this,a,"company_three_acitivity").execute(file);
            }
        } else if (requestCode == PHOTO_REQUEST_CAMERA) {//如果是相机
            Uri uri = data.getData();
            if(uri == null){
                //use bundle to get data
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Bitmap  photo = (Bitmap) bundle.get("data"); //get bitmap
                    //spath :生成图片取个名字和路径包含类型
                        String fileName = System.currentTimeMillis()+"tupian_pic.png" ;
                        saveBitmap( photo, fileName );
                        tempFile = new File(Environment.getExternalStorageDirectory() + "/",fileName);
                } else {
                    Toast.makeText(getApplicationContext(), "找不到图片", Toast.LENGTH_LONG).show();
                    return;
                }
            }else{
                tempFile=new File(get_absolute_UrL.getImageAbsolutePath(Certified_company_three_activity.this,uri));
            }
            //头像文件原始文件名字
            if(a==5){
                b=true;
                Pic_bean.zhengmian_boolean=false;
                x.image().bind(iv_identity_front_id,Uri.fromFile(tempFile).getPath());
            }else if(a==6){
                c=true;
                Pic_bean.fanmian_boolean=false;
                x.image().bind(iv_identity_back_id,Uri.fromFile(tempFile).getPath());
            }else if(a==4){
                d=true;
                Pic_bean.logo_boolean=false;
                x.image().bind(iv_logo_id,Uri.fromFile(tempFile).getPath());
            }else if(a==7){
                e=true;
                Pic_bean.zhizhao_boolean=false;
                x.image().bind(iv_license_id,Uri.fromFile(tempFile).getPath());
            }
            new Upload_Photo(Certified_company_three_activity.this,a,"company_three_acitivity").execute(tempFile);
        }
             super.onActivityResult(requestCode, resultCode, data);
    }
    public void saveBitmap(Bitmap bm, String fileName) {
        File f = new File(Environment.getExternalStorageDirectory() + "/",fileName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG,100, out);//将图片压缩到out中
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
