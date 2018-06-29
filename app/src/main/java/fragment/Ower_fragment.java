package fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.certified_company.Certified_company_status_activity;
import com.example.hs.jiankangli_example1.password.Login_activity;
import com.example.hs.jiankangli_example1.My_activity;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.Set_Activity;
import com.example.hs.jiankangli_example1.certified_company.Certified_company_frist_activity;

import AsyncTask.query_AsyncTask;
import Inter.get_net_Info;
import integral.integral_detail_activity;
import com.example.hs.jiankangli_example1.personal_Info.personal_info_activity;
import com.zhy.autolayout.AutoLinearLayout;

import net.qiujuer.genius.blur.StackBlur;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import MyView.TiltView;
import bean.info;
import bean.personal;
import de.hdodenhof.circleimageview.CircleImageView;
import Inter.Globle;
import utils.Common_utils;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.XUtilsDB;
import utils.headPicUtlis;
/**
 * Created by 李浩 on 2016/9/12.
 */
public class Ower_fragment extends Fragment  implements get_net_Info {

    private View view;
    private CircleImageView head_cv_id;
    private AlertDialog.Builder builder;
    private AlertDialog create;
    /**
     * 头像原始照片名称
     */
    private static final String PHOTO_FILE_NAME = "IMG20160802124325.jpg";
    private static final int PHOTO_REQUEST_CAMERA = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    private File tempFile;
    /**
     * 头像设置
     */
    private Bitmap bitmaps;
    private AutoLinearLayout all_jcxx_id;
    private AutoLinearLayout all_wdjf_id;
    private AutoLinearLayout all_wdsc_id;
    private AutoLinearLayout all_wdfb_id;
    private AutoLinearLayout all_wdcg_id;
    private TiltView sanjiao_id;
    private ImageView iv_shezhi_id;
    private DbManager db;
    private boolean up;
    private personal.BodyBean.DataBean dataBean;
    private TextView tv_jf_id;
    private ImageView iv_dj_id;
    private info infos;
    private String lv;
    private String jf;
    private AutoLinearLayout ll_jf_id;
    private View company_approve_id;
    private String companyStatus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.ower_fragment,container,false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //初始化界面控件实例
        initView();
        setOnClickListener();
        super.onActivityCreated(savedInstanceState);
    }
    //初始化界面控件实例
    private void initView() {
        //发现积分
        ll_jf_id = (AutoLinearLayout) view.findViewById(R.id.ll_jf_id);
        //设置头像
        head_cv_id = (CircleImageView) view.findViewById(R.id.head_cv_id);
        //基础信息的按钮，点击跳转界面
        all_jcxx_id = (AutoLinearLayout) view.findViewById(R.id.all_jcxx_id);
        //我的积分按钮，点击跳转界面
        all_wdjf_id = (AutoLinearLayout) view.findViewById(R.id.all_wdjf_id);
        //我的收藏按钮，点击跳转界面
        all_wdsc_id = (AutoLinearLayout) view.findViewById(R.id.all_wdsc_id);
        //我的发布按钮，点击跳转界面
        all_wdfb_id = (AutoLinearLayout) view.findViewById(R.id.all_wdfb_id);
        //我的草稿按钮，点击跳转界面
        all_wdcg_id = (AutoLinearLayout) view.findViewById(R.id.all_wdcg_id);
        //发现设置按钮
        iv_shezhi_id = (ImageView) view.findViewById(R.id.iv_shezhi_id);
        //发现自定义三角形控件
        sanjiao_id = (TiltView) view.findViewById(R.id.sanjiao_id);
        //发现积分显示控件
        tv_jf_id = (TextView) view.findViewById(R.id.tv_integrals_id);
        //发现等级显示控件
        iv_dj_id = (ImageView) view.findViewById(R.id.iv_dj_id);
        //发现公司认证
        company_approve_id = view.findViewById(R.id.company_approve_id);
    }
    private void setOnClickListener() {
        all_wdcg_id.setOnClickListener(new MyOnclickListener());
        iv_shezhi_id.setOnClickListener(new MyOnclickListener());//设置
        all_jcxx_id.setOnClickListener(new MyOnclickListener());// 设置点击事件，基础信息
        head_cv_id.setOnClickListener(new MyOnclickListener());//头像按钮
        all_wdjf_id.setOnClickListener(new MyOnclickListener());//设置积分详情
        all_wdsc_id.setOnClickListener(new MyOnclickListener());
        all_wdfb_id.setOnClickListener(new MyOnclickListener());
        company_approve_id.setOnClickListener(new MyOnclickListener());
    }
    @Override
    public void onResume() {
        if(!up){
            if(new Common_utils(getActivity()).isLogin()){
                ll_jf_id.setVisibility(View.VISIBLE);
                //获取数据库里面的头像的信息，然后更新头像
                requestHttp();//请求网络，展现等级和积分
                setHead();
            }else{//如果没有登录，设置没有登录的时候的照片,//默认的头像
                ll_jf_id.setVisibility(View.GONE);
                head_cv_id.setImageDrawable(getResources().getDrawable(R.mipmap.login3x));
                sanjiao_id.setImageDrawable(getResources().getDrawable(R.mipmap.moren3x));
            }
        }
        up=false;//重新变为false,为下次进入设置头像做判断
        super.onResume();
    }
    private void requestHttp(){
        String Uri=Globle.TEST_URL+"/api/member/selectMemberInfoById";
        JSONObject jsonObject=new JSONObject();
        try {
            String memberID=new JavaScriptObject(getActivity()).getMemberid(null);
            jsonObject.put("member_id",memberID);
            RequestNet.queryServer(jsonObject,Uri,Ower_fragment.this,"get_personal_info");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /**
     * 已经登录了，设置头像
     */
    private void setHead() {
        //如果用户没有修改过头像信息，设置默认的头像
        if (new JavaScriptObject(getActivity()).getHeadPath() == null || new JavaScriptObject(getActivity()).getHeadPath().isEmpty()) {
            //默认的头像
            head_cv_id.setImageDrawable(getResources().getDrawable(R.mipmap.morentouxiang3x));
            //将背景也设置为默认的
            sanjiao_id.setImageDrawable(getResources().getDrawable(R.mipmap.moren3x));
        } else {
            ImageOptions options = new ImageOptions.Builder().setFadeIn(true).setLoadingDrawableId(R.mipmap.morentouxiang3x).build(); //淡入效果
            x.image().loadDrawable(new JavaScriptObject(getActivity()).getHeadPath(),options, new Callback.CacheCallback<Drawable>() {
                @Override
                public boolean onCache(Drawable result) {//对缓存的图片进行操作
                    //缓存的图片
                    Log.i("TAG", "onCache:");
                    BitmapDrawable bd = (BitmapDrawable) result;
                    Bitmap bm = bd.getBitmap();
                    head_cv_id.setImageBitmap(bm);
                    //高斯模糊,从网上下载的图片进行高斯模糊
                    Bitmap newBitmap = StackBlur.blur(bm, 10, false);
                    sanjiao_id.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    sanjiao_id.setImageBitmap(newBitmap);
                    return false;
                }
                @Override
                public void onSuccess(Drawable result) {//没有缓存的时候，下载图片
                    BitmapDrawable bd = (BitmapDrawable) result;
                    Bitmap bm = bd.getBitmap();
                    head_cv_id.setImageBitmap(bm);
                    //高斯模糊,从网上下载的图片进行高斯模糊
                    Bitmap newBitmap = StackBlur.blur(bm, 10, false);
                    sanjiao_id.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    sanjiao_id.setImageBitmap(newBitmap);
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
        }
    }
    /**
     * 初始化popupWindow
     *
     * @param
     */
    //点击头像，弹出AlertDialog
    private void showPopwindow() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;//获取当前的SDK版本
        if (currentapiVersion >= 21) {
            builder = new AlertDialog.Builder(getActivity(), R.style.dialogActivity);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        create = builder.create();
        Window w = create.getWindow();//获取一个窗口
        WindowManager.LayoutParams lp = w.getAttributes();//窗口布局填充器
        lp.y = getActivity().getWindow().getDecorView().getHeight();
        create.show();
        View view_pop_1 = getLayoutInflater(getArguments()).inflate(R.layout.popup_window, null);//获取弹出的布局文件填充布局
        create.setContentView(view_pop_1);
        TextView camera_photo = (TextView) view_pop_1.findViewById(R.id.camera_photo);
        TextView photo_book = (TextView) view_pop_1.findViewById(R.id.photo_book);
        TextView qx = (TextView) view_pop_1.findViewById(R.id.qx_photo);
        //然后给每个不同的按钮设置不同的监听得到事件
        camera_photo.setOnClickListener(new MyOnclickListener());//拍照上传
        photo_book.setOnClickListener(new MyOnclickListener());//手机图库上传
        qx.setOnClickListener(new MyOnclickListener());//取消上传事件
    }

    @Override
    public void getinfo(String str){
        Log.i("TAG", "getinfodsd: "+str);
        try {
            JSONObject json=new JSONObject(str);
            String code=json.getString("code");
            switch (code){
                case "200":
                    infos = com.alibaba.fastjson.JSONObject.parseObject(str,info.class);
                    if(infos!=null&&infos.getBody()!=null&&infos.getBody().getData()!=null){
                        jf = infos.getBody().getData().getCurrentIntegral()+"";
                        companyStatus = infos.getBody().getData().getCompanyStatus();
                        tv_jf_id.setText(jf);
                        if(infos.getBody().getData().getLevelName()!=null&&!infos.getBody().getData().getLevelName().isEmpty()){
                            lv = (infos.getBody().getData().getLevelName()).toLowerCase();
                        }
                        if(lv==null){
                            iv_dj_id.setImageDrawable(getResources().getDrawable(R.mipmap.lv1_3x));
                        }else{
                            switch (lv){
                                case "lv1":
                                    iv_dj_id.setImageDrawable(getResources().getDrawable(R.mipmap.lv1_3x));
                                    break;
                                case "lv2":
                                    iv_dj_id.setImageDrawable(getResources().getDrawable(R.mipmap.lv2_3x));
                                    break;
                                case "lv3":
                                    iv_dj_id.setImageDrawable(getResources().getDrawable(R.mipmap.lv3_3x));
                                    break;
                                case "lv4":
                                    iv_dj_id.setImageDrawable(getResources().getDrawable(R.mipmap.lv4_3x));
                                    break;
                                case "lv5":
                                    iv_dj_id.setImageDrawable(getResources().getDrawable(R.mipmap.lv5_3x));
                                    break;
                                default:
                                    iv_dj_id.setImageDrawable(getResources().getDrawable(R.mipmap.lv5_3x));
                                    break;
                            }
                        }
                    }
                    break;
                case "20002":
                    head_cv_id.setImageDrawable(getResources().getDrawable(R.mipmap.login3x));
                    sanjiao_id.setImageDrawable(getResources().getDrawable(R.mipmap.moren3x));
                    ll_jf_id.setVisibility(View.GONE);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public class MyOnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                //判断是否登录了，如果登录了，那么给这个控件设置的点击事件为弹出弹窗，如果没有登录，跳转到登录界面
                case R.id.head_cv_id:
                    //第一次为未登陆，往SharePreferences中存入flag值，默认为false，登陆了改成true
                    //然后根据falg的值来判断是否登陆了,然后弹出什么样的弹窗
                    if(new Common_utils(getActivity()).isLogin()){
                        showPopwindow();
                        //TODO
                        db = x.getDb(XUtilsDB.getDBconfig());
                        try {
                            dataBean = db.findFirst(personal.BodyBean.DataBean.class);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }else{
                        //跳转到登录界面
                        Intent intent3=new Intent(getActivity(), Login_activity.class);
                        startActivity(intent3);
                      }
                    break;
                case R.id.camera_photo://启动照相机功能
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                         if (Common_utils.hasSdcard()) {//如果有SD卡
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(new File(Environment.getExternalStorageDirectory(),PHOTO_FILE_NAME)));//相片名字
                    }
                    startActivityForResult(intent,PHOTO_REQUEST_CAMERA);//使用隐式意图调用相册或者相机并且获取返回的值
                    break;
                case R.id.photo_book://启动相册功能
                    Intent intent1 = new Intent(Intent.ACTION_PICK);
                    intent1.setType("image/*");
                    startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);
                    break;
                case R.id.qx_photo://取消
                    create.dismiss();
                    break;
                case R.id.iv_shezhi_id://设置
                    Intent intent2=new Intent(getActivity(),Set_Activity.class);//跳转到设置界面
                    startActivity(intent2);
                    break;
                case R.id.all_jcxx_id://基础信息
                    //判断是否登录，如果登录显示个人信息界面，没有登录，弹出弹窗提示用户先登录
                    if(new Common_utils(getActivity()).isLogin()){
                        Intent intent3=new Intent(getActivity(),personal_info_activity.class);//跳转到个人界面
                        head_cv_id.setDrawingCacheEnabled(true);
                        Bitmap obmp = Bitmap.createBitmap(head_cv_id.getDrawingCache());
                        intent3.putExtra("bitmap",obmp);
                        head_cv_id.setDrawingCacheEnabled(false);
                        startActivity(intent3);
                    }else{
                        Intent intent3=new Intent(getActivity(),Login_activity.class);//跳转到登录界面
                        Toast.makeText(getActivity(), "请先登录再执行查看信息操作", Toast.LENGTH_SHORT).show();
                        startActivity(intent3);
                    }
                    break;
                case R.id.all_wdjf_id://我的积分
                    //判断是否登录，如果登录显示个人信息界面，没有登录，弹出弹窗提示用户先登录
                    if(new Common_utils(getActivity()).isLogin()){
                        Intent intent4=new Intent(getActivity(), integral_detail_activity.class);//跳转到积分
                        startActivity(intent4);
                    }else{
                        Intent intent3=new Intent(getActivity(),Login_activity.class);//跳转到登录界面
                        Toast.makeText(getActivity(), "请先登录再执行查看积分操作", Toast.LENGTH_SHORT).show();
                        startActivity(intent3);
                    }
                    break;
                case R.id.all_wdcg_id://我的草稿按钮
                    if(new Common_utils(getActivity()).isLogin()){
                        Intent intent3=new Intent(getActivity(), My_activity.class);
                        intent3.putExtra("my","caogao");
                        startActivity(intent3);
                    }else{
                        Intent intent3=new Intent(getActivity(),Login_activity.class);//跳转到登录界面
                        Toast.makeText(getActivity(), "请先登录再执行查看草稿操作", Toast.LENGTH_SHORT).show();
                        startActivity(intent3);
                    }
                    break;
                case R.id.all_wdsc_id:
                    if(new Common_utils(getActivity()).isLogin()){
                        Intent intent3=new Intent(getActivity(),My_activity.class);
                        intent3.putExtra("my","collect");
                        startActivity(intent3);
                    }else{
                        Toast.makeText(getActivity(),"请先登录再执行查看收藏操作", Toast.LENGTH_SHORT).show();
                        new Common_utils(getActivity()).Login();
                    }
                    break;
                case R.id.all_wdfb_id:
                    if(new Common_utils(getActivity()).isLogin()){
                        Intent intent3=new Intent(getActivity(), My_activity.class);
                        intent3.putExtra("my","mypublish");
                        startActivity(intent3);
                    }else{
                        Toast.makeText(getActivity(), "请先登录再执行查看发布操作", Toast.LENGTH_SHORT).show();
                        new Common_utils(getActivity()).Login();
                    }
                    break;
                case R.id.company_approve_id:
                    //跳转到公司认证页面
                    if(new Common_utils(getActivity()).isLogin()){
                        if(companyStatus!=null&&(companyStatus.equals("2")||companyStatus.equals("3")||companyStatus.equals("4"))){
                            Intent intents3=new Intent(getActivity(), Certified_company_status_activity.class);
                            intents3.putExtra("statusCode",companyStatus);
                            startActivity(intents3);
                            return;
                        }
                        Intent intent3=new Intent(getActivity(),Certified_company_frist_activity.class);
                        startActivity(intent3);
                    }else{
                        Intent intent3=new Intent(getActivity(),Login_activity.class);//跳转到登录界面
                        Toast.makeText(getActivity(), "登录以后再能执行公司认证操作哦！", Toast.LENGTH_SHORT).show();
                        startActivity(intent3);
                    }
                    break;
            }
        }
    }
    /**
     * 根据调用相机 或相册 返回值
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {//返回码
        if (requestCode == PHOTO_REQUEST_GALLERY) {//如果是相册
            if (data != null) {
                Uri uri = data.getData();//获取的Uri
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CAMERA) {//如果是相机
            if (Common_utils.hasSdcard()) {
                if (data == null) {
                    //头像文件原始文件名字
                    tempFile = new File(Environment.getExternalStorageDirectory(),PHOTO_FILE_NAME);
                    crop(Uri.fromFile(tempFile));
                }
            } else {
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {//请求码为裁剪以后的界面
            try {
                bitmaps = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(uritempFile));//获取裁剪了以后的图片
                up=true;//是否上传头像
                // 在界面中显示图片
                head_cv_id.setImageBitmap(bitmaps);//显示图片
                //进行高斯模糊以后，再显示出来
                Bitmap newBitmap = StackBlur.blur(bitmaps,10, false);
                sanjiao_id.setScaleType(ImageView.ScaleType.CENTER_CROP);
                sanjiao_id.setImageBitmap(newBitmap);
                create.dismiss();
                create = null;
                new headPicUtlis(getActivity(),dataBean,db).saveBitmap(bitmaps,"head_phone.jpg");//存储
                new headPicUtlis(getActivity(),dataBean,db).Uphead();//上传
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 裁剪照片,对在相册和拍照选择的照片进行自由裁剪
     */
    Uri uritempFile;

    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("noFaceDetection", true);
        /**
         * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
         * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */
        //uritempFile为Uri类变量，实例化uritempFile
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
}
