package utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import AsyncTask.UploadHead;
import Inter.Globle;
import bean.personal;

/**
 * Created by 李浩 on 2016/10/27.
 */
public class headPicUtlis {
    private Context context;
    private personal.BodyBean.DataBean dataBean;
    private DbManager db;
    public headPicUtlis(Context context,personal.BodyBean.DataBean dataBean, DbManager db) {
        this.context=context;
        this.dataBean=dataBean;
        this.db=db;
    }
    /**
     * 保存头像图片到本地
     */
    public void saveBitmap(Bitmap bm, String fileName) {
        File f = new File(Environment.getExternalStorageDirectory() + "/", fileName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG,90, out);//将图片压缩到out中
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private final static String UpPIC_URL= Globle.TEST_URL+"/api/pic/upload";//上传图片接口

    public void Uphead(){
        RequestParams params = new RequestParams(UpPIC_URL);//设置params
        params.setMultipart(true);
        params.addBodyParameter("file",new File(Environment.getExternalStorageDirectory() + "/","head_phone.jpg"));//存储
        new UploadHead(context,params,dataBean,db,"图片").execute();//上传
    }
}
