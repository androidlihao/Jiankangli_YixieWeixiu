package utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.certified_company.Certified_company_three_activity;


/**
 * Created by 李浩 on 2016/11/15.
 */
public class showPopWindows {
    private Certified_company_three_activity contexts;
    private Context context;
    private AlertDialog.Builder builder;
    private AlertDialog create;
    private static final int PHOTO_REQUEST_CAMERA = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    public showPopWindows(Context context){
        contexts= (Certified_company_three_activity) context;
        this.context=context;
    }
    public void showPopwindow() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;//获取当前的SDK版本
        if (currentapiVersion >= 21) {
            builder = new AlertDialog.Builder(contexts,R.style.dialogActivity);
        } else {
            builder = new AlertDialog.Builder(contexts);
        }
        create = builder.create();
        Window w = create.getWindow();//获取一个窗口
        WindowManager.LayoutParams lp = w.getAttributes();//窗口布局填充器
        lp.y =contexts.getWindow().getDecorView().getHeight();
        create.show();
        View view_pop_1 = LayoutInflater.from(context).inflate(R.layout.popup_window, null);//获取弹出的布局文件填充布局
        create.setContentView(view_pop_1);
        TextView camera_photo = (TextView) view_pop_1.findViewById(R.id.camera_photo);
        TextView photo_book = (TextView) view_pop_1.findViewById(R.id.photo_book);
        TextView qx = (TextView) view_pop_1.findViewById(R.id.qx_photo);
//      //然后给每个不同的按钮设置不同的监听得到事件
        camera_photo.setOnClickListener(new MyOnclickListener());//拍照上传
        photo_book.setOnClickListener(new MyOnclickListener());//手机图库上传
        qx.setOnClickListener(new MyOnclickListener());//取消上传事件

    }
    public class MyOnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.camera_photo://启动照相机功能
                    create.dismiss();
                    if (Common_utils.hasSdcard()) {//如果有SD卡//
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        contexts.startActivityForResult(intent,PHOTO_REQUEST_CAMERA);
                    }else{
                        Toast.makeText(contexts, "sdcard不可用", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.photo_book://启动相册功能
                    create.dismiss();
                    Intent intent1 = new Intent(Intent.ACTION_PICK);
                    intent1.setType("image/*");
                    contexts.startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);
                    break;
                case R.id.qx_photo://取消
                    create.dismiss();
                    break;
            }
        }
    }

}
