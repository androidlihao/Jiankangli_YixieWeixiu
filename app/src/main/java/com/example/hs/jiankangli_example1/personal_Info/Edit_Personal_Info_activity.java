package com.example.hs.jiankangli_example1.personal_Info;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.applications.SysApplication;
import com.example.hs.jiankangli_example1.applications.answer_Application;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;

import Inter.get_net_Info;
import bean.personal;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import de.hdodenhof.circleimageview.CircleImageView;
import Inter.Globle;
import utils.Common_utils;
import utils.RequestNet;
import utils.Statubars;
import utils.XUtilsDB;
import utils.headPicUtlis;

/**
 * Created by 李浩 on 2016/10/11.
 */
public class Edit_Personal_Info_activity extends AutoLayoutActivity implements get_net_Info {
    private TextView et_sex_id;
    private AutoRelativeLayout rl_sex_id;
    private LinearLayout set_back_id;
    private OptionPicker Sexpicker;
    private TextView et_birth_id;
    private AutoRelativeLayout rl_brith_id;
    private DatePicker datepicker;
    private AutoRelativeLayout rl_touxiang_id;
    private CircleImageView iv_touxiang_id;
    private AlertDialog.Builder builder;
    private AlertDialog create;
    private static final String PHOTO_FILE_NAME = "bianjihead.jpg";
    private static final int PHOTO_REQUEST_CAMERA = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    private File tempFile;
    private Bitmap bitmaps;
    private AutoRelativeLayout rl_skill_id;
    private TagFlowLayout et_skill_id;
    private ArrayList<String> skillList=new ArrayList<>();
    private TagAdapter mAdapter;
    private TextView tv_baocun_id;
    private EditText et_nickNime_id;
    private EditText et_nime_id;
    private ArrayList<String> idlist=new ArrayList<>();
    private personal.BodyBean.DataBean dataBean;
    private Intent intent;
    private ArrayList<String> jineng;
    private ArrayList<String> jnidlist;
    private DbManager db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.edit_info);
        SysApplication.getInstance().addActivity(this);//把当前的activity添加到集合中
        answer_Application.getInstance().addActivity(this);
        intent = getIntent();
        //初始化界面控件实例
        initView();
        aboutTagLayout();
        et_nime_id.setText(intent.getStringExtra("name"));
        et_nickNime_id.setText(intent.getStringExtra("nickname"));
        et_sex_id.setText(intent.getStringExtra("sex"));
        et_birth_id.setText(intent.getStringExtra("birth"));
        Bitmap bitmap= (Bitmap) getIntent().getExtras().get("bitmap");
        if(bitmap!=null){
            iv_touxiang_id.setImageBitmap(bitmap);
        }
        //设置监听器
        setOnClickListener();
    }

    private void aboutTagLayout() {
        //填充数据源
        //获取传过来的技能集合
        jineng = intent.getStringArrayListExtra("jineng");
        if(jineng!=null&&!jineng.isEmpty()){
            skillList.addAll(jineng);
        }
        //获取原本就有的技能的Id
        //获取传过来的技能集合
        jnidlist = intent.getStringArrayListExtra("jnid");
        if(jnidlist!=null&&!jnidlist.isEmpty()){//传过来的东西不为空
            idlist.addAll(jnidlist);
        }
        mAdapter = new TagAdapter(skillList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                View convertView = LayoutInflater.from(Edit_Personal_Info_activity.this).inflate(R.layout.tag_view, null);
                Button button = (Button) convertView.findViewById(R.id.tv_tagview_id);
                button.setText(o.toString());
                return convertView;
            }
        };//准备适配器
        et_skill_id.setAdapter(mAdapter);//绑定适配器
    }
    private void setOnClickListener() {
        set_back_id.setOnClickListener(new MyOnClickListener());
        rl_sex_id.setOnClickListener(new MyOnClickListener());
        rl_brith_id.setOnClickListener(new MyOnClickListener());
        rl_touxiang_id.setOnClickListener(new MyOnClickListener());
        et_skill_id.setOnClickListener(new MyOnClickListener());
        rl_skill_id.setOnClickListener(new MyOnClickListener());
        tv_baocun_id.setOnClickListener(new MyOnClickListener());
    }

    private void initView() {
        db = x.getDb(XUtilsDB.getDBconfig());
        try {
            dataBean = db.findFirst(personal.BodyBean.DataBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        //初始化性别选择器
        aboutSexPicker();
        //初始化日期选择器
        aboutDatePicker();
        //发现返回按钮
        set_back_id = (LinearLayout)findViewById(R.id.set_back_id);
        //发现性别显示控件
        et_sex_id = (TextView) findViewById(R.id.et_Sex_id);
        //发现性别控件
        rl_sex_id = (AutoRelativeLayout) findViewById(R.id.rl_sex_id);
        //发现出生日期显示控件
        et_birth_id = (TextView) findViewById(R.id.et_birth_id);
        //发现出生日期控件
        rl_brith_id = (AutoRelativeLayout) findViewById(R.id.rl_brith_id);
        //发现头像控件
        rl_touxiang_id = (AutoRelativeLayout) findViewById(R.id.rl_touxiang_id);
        //发现头像显示控件
        iv_touxiang_id = (CircleImageView) findViewById(R.id.iv_touxiang_id);
        //发现我的技能按钮
        rl_skill_id = (AutoRelativeLayout) findViewById(R.id.rl_skill_id);
        //发现技能展示控件
        et_skill_id = (TagFlowLayout) findViewById(R.id.et_Skill_id);
        //发现保存按钮
        tv_baocun_id = (TextView) findViewById(R.id.tv_baocun_id);
        //发现昵称按钮
        et_nickNime_id = (EditText) findViewById(R.id.et_nickNime_id);
        //发现姓名按钮
        et_nime_id = (EditText) findViewById(R.id.et_Name_id);
    }
    private void aboutDatePicker() {
        datepicker = new DatePicker(this, DatePicker.YEAR_MONTH_DAY);
        datepicker.setRange(1965, 2016);//年份范围
        String bt=intent.getStringExtra("birth");
        datepicker.setSelectedItem(Integer.parseInt(bt.substring(0,4)),Integer.parseInt(bt.substring(5,7)),Integer.parseInt(bt.substring(8)));
        datepicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                et_birth_id.setText(year + "-" + month + "-" + day);
            }
        });
    }
    private void aboutSexPicker() {
        Sexpicker = new OptionPicker(this, new String[]{
                "男","女"
        });
        Sexpicker.setTextSize(18);
        Sexpicker.setSelectedItem(intent.getStringExtra("sex"));//设置当前选项为传过来的性别
        Sexpicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(String option) {
                et_sex_id.setText(option);
            }
        });
    }

    @Override
    public void getinfo(String str) {
        JSONObject json= null;
        try {
            json = new JSONObject(str);
            if(json.getString("code").equals("200")){
                Toast.makeText(this, "修改个人信息成功！", Toast.LENGTH_SHORT).show();
                dataBean.setName(et_nime_id.getText().toString());
                dataBean.setNickname(et_nickNime_id.getText().toString());
                if(et_sex_id.getText().toString().equals("男")){
                    dataBean.setSex(1);
                }else{
                    dataBean.setSex(0);
                }
                try {
                    db.update(dataBean,"name");//修改数据库里面的头像数据
                    db.update(dataBean,"sex");//修改数据库里面的头像数据
                    db.update(dataBean,"nickname");//修改数据库里面的头像数据
                } catch (DbException e) {
                    e.printStackTrace();
                }
                answer_Application.getInstance().kill();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.set_back_id:
                    finish();
                    break;
                case R.id.rl_sex_id:
                    //弹出性别选择框
                    Sexpicker.show();
                    break;
                case R.id.rl_brith_id:
                    //弹出出生日期选择器
                    datepicker.show();
                    break;
                case R.id.rl_touxiang_id:
                    //弹出选择框
                    showPopWindow();
                    break;
                case R.id.camera_photo://拍照上传
                    takePhoto();
                    break;
                case R.id.photo_book://相册
                    selectPhoto();
                    break;
                case R.id.qx_photo://取消
                    create.dismiss();
                    break;
                case R.id.rl_skill_id:
                    //跳转到技能展现界面
                   addSkill();
                    break;
                case R.id.tv_baocun_id:
                    //开始执行保存操作
                    upInfo();
                    break;
            }
        }
    }
    //开始上传任务
    private void upInfo() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("member_id",new Common_utils(getApplicationContext()).getMemberid());//会员ID
            jsonObject.put("nickname",et_nickNime_id.getText().toString());
            jsonObject.put("name",et_nime_id.getText().toString());
            if(et_sex_id.getText().toString().equals("男")){
                jsonObject.put("sex",1);
            }else{
                jsonObject.put("sex",0);
            }
            jsonObject.put("birthday",et_birth_id.getText().toString());
            if(idlist.isEmpty()){//如果数据源为空,那么没有任何的技能
                jsonObject.put("attention_skills_id","");
            }else{
                StringBuffer stringBuffer=new StringBuffer();
                for(String s:idlist){
                    stringBuffer.append(s+",");
                }
                stringBuffer.deleteCharAt(stringBuffer.length()-1);
                jsonObject.put("attention_skills_id",stringBuffer);
            }
            String Uri=Globle.TEST_URL+"/api/member/updateMemberInfo";
            RequestNet.queryServer(jsonObject,Uri,Edit_Personal_Info_activity.this,"Edit_Personal_Info_activity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //添加技能
    private void addSkill(){
        Intent intent=new Intent(Edit_Personal_Info_activity.this, personal_Skill_activity.class);
        //将现在就已经选择的数据传过去
        intent.putStringArrayListExtra("sklist",skillList);
        startActivityForResult(intent,200);
    }
    //拍照
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Common_utils.hasSdcard()) {//如果有SD卡
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment.getExternalStorageDirectory(),PHOTO_FILE_NAME)));//相片名字
        }
        startActivityForResult(intent,PHOTO_REQUEST_CAMERA);//使用隐式意图调用相册或者相机并且获取返回的值
    }
    //选择图片
    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent,PHOTO_REQUEST_GALLERY);
    }
    //选择弹框
    private void showPopWindow() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;//获取当前的SDK版本
        if (currentapiVersion >= 21) {
            builder = new AlertDialog.Builder(this, R.style.dialogActivity);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        //TODO
        create = builder.create();
        Window w = create.getWindow();//获取一个窗口
        WindowManager.LayoutParams lp = w.getAttributes();//窗口布局填充器
        lp.y =getWindow().getDecorView().getHeight();
        create.show();
        View view_pop_1 = getLayoutInflater().inflate(R.layout.popup_window, null);//获取弹出的布局文件填充布局
        create.setContentView(view_pop_1);
        TextView camera_photo = (TextView) view_pop_1.findViewById(R.id.camera_photo);
        TextView photo_book = (TextView) view_pop_1.findViewById(R.id.photo_book);
        TextView qx = (TextView) view_pop_1.findViewById(R.id.qx_photo);
        //然后给每个不同的按钮设置不同的监听得到事件
        camera_photo.setOnClickListener(new MyOnClickListener());//拍照上传
        photo_book.setOnClickListener(new MyOnClickListener());//手机图库上传
        qx.setOnClickListener(new MyOnClickListener());//取消上传事件
    }
    /**
     * 根据调用相机 或相册 返回值
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {//返回码
        if (requestCode == PHOTO_REQUEST_GALLERY) {//如果是相册
            if (data != null) {
                Uri uri = data.getData();//获取的Uri
                new Common_utils(Edit_Personal_Info_activity.this).crop(uri,PHOTO_REQUEST_CUT);
            }
        } else if (requestCode == PHOTO_REQUEST_CAMERA) {//如果是相机
            if (Common_utils.hasSdcard()) {
                if (data == null) {
                    //头像文件原始文件名字
                    tempFile = new File(Environment.getExternalStorageDirectory(),PHOTO_FILE_NAME);
                    new Common_utils(Edit_Personal_Info_activity.this).crop(Uri.fromFile(tempFile),PHOTO_REQUEST_CUT);
                }
            } else {
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {//请求码为裁剪以后的界面
            try {
                //获取裁剪了以后的图片
                bitmaps = data.getParcelableExtra("data");
                //将裁剪以后的图片显示出来
                iv_touxiang_id.setImageBitmap(bitmaps);
                create.dismiss();
                //开始上传头像
                new headPicUtlis(Edit_Personal_Info_activity.this,dataBean,db).saveBitmap(bitmaps,"head_phone.jpg");
                new headPicUtlis(Edit_Personal_Info_activity.this,dataBean,db).Uphead();//上传
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(requestCode==200&&resultCode==6){//顺便将id也取出来
                skillList.clear();//先将原数据源清空
                idlist.clear();//将原来的id集合清空
                //有一个bug,就是没有选择的时候，返回刷新无效,既然没有数据，直接让et_skill_id隐藏好了，此时idlist肯定也为空，上传到服务器，没有数据
                if(data.getStringArrayListExtra("skill").isEmpty()){
                    et_skill_id.setVisibility(View.INVISIBLE);
                }else{
                    et_skill_id.setVisibility(View.VISIBLE);
                }
                skillList.addAll(data.getStringArrayListExtra("skill"));
                idlist.addAll(data.getStringArrayListExtra("jinengid"));
                et_skill_id.setAdapter(new TagAdapter(skillList) {
                    @Override
                    public View getView(FlowLayout parent, int position, Object o) {
                        View convertView = LayoutInflater.from(Edit_Personal_Info_activity.this).inflate(R.layout.tag_view, null);
                        Button button = (Button) convertView.findViewById(R.id.tv_tagview_id);
                        button.setText(o.toString());
                        return convertView;
                    }
                });
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
