package AsyncTask;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.IHavaPartActivity;
import com.example.hs.jiankangli_example1.Push_Info.push_personal_Or_other_second_info_activity;
import com.example.hs.jiankangli_example1.Push_Info.push_zhengshu_activity;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.answer.answer_final_activity;
import com.example.hs.jiankangli_example1.answer.answer_frist_activity;
import com.example.hs.jiankangli_example1.answer.answer_second_activity;
import com.example.hs.jiankangli_example1.ask.ask_second_activity;
import com.example.hs.jiankangli_example1.certified_company.Certified_company_four_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.push_knowledge_finish_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.push_knowledge_repair_second_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.push_knowledge_repair_three_activity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import bean.Pic_bean;
import utils.BitmapCompressor;
import Inter.Globle;
import utils.Common_utils;


/**
 * Created by 李浩 on 2016/10/31.
 */
public class UoLoadAsyncTask extends AsyncTask<ArrayList<String>,Integer,LinkedList<LinkedList<String>>> {
    private final static String UpPIC_URL= Globle.TEST_URL+"/api/pic/upload";
    private Context contexts;
    private  LinkedList<LinkedList<String>> urlList;
    private String tag;
    private final ProgressBar mProgress;
    private final TextView tv_jd_id;
    private final AlertDialog alertDialog2;

    public UoLoadAsyncTask(Context context, ArrayList<String> localpaths, LinkedList<LinkedList<String>> urlList, String tag){
        this.urlList=urlList;
        contexts= context;
        this.tag=tag;
        if (urlList.getLast().getFirst().equals("tianjia")){
            a=urlList.size()-1;
        }else{
            a=urlList.size();
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(contexts);
        dialog.setTitle("正在上传中");
        LayoutInflater inflater = LayoutInflater.from(contexts);
        View v = inflater.inflate(R.layout.uppicture, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        tv_jd_id = (TextView) v.findViewById(R.id.tv_jd_id);
        dialog.setView(v);
        alertDialog2 = dialog.create();
        alertDialog2.setCancelable(false);
        if (!alertDialog2.isShowing()&&tag.equals("IHavaPartActivity")){
            alertDialog2.show();
        }
    }
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
    @Override
    protected LinkedList<LinkedList<String>> doInBackground(ArrayList<String>... arrayLists) {
        uploadPic();
        return urlList;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(LinkedList<LinkedList<String>> linkedLists) {
        super.onPostExecute(linkedLists);
    }
    int a ;
    private void uploadPic(){
        for(int i = 0;i<urlList.size();i++){
            RequestParams params = new RequestParams(UpPIC_URL);//设置params
            params.setMultipart(true);
            if (urlList.get(i).getFirst().equals("tianjia")){//如果是添加也不会上传
                continue;
            }
            if (urlList.get(i).size()==3&&!urlList.get(i).getLast().equals("failed")){//上传成功的不会再次上传
                continue;
            }
            if(urlList.get(i).size()==3&&urlList.get(i).getLast().equals("failed")){//如果是上传失败的，清除失败从新上传
                urlList.get(i).remove(2);
                urlList.get(i).remove(1);
            }
            Bitmap b= new BitmapCompressor().getSmallBitmap(urlList.get(i).getFirst());//根据路径得到bitmap图片
            final String fileName = System.currentTimeMillis()+"tupian_pic.png" ;
            saveBitmap(b,fileName);
            params.addBodyParameter("file",new File(Environment.getExternalStorageDirectory() + "/",fileName));//将所选择文件,文件过大上传不了
            params.setConnectTimeout(40000);
            final int finalI = i+1;
            x.http().post(params,new Callback.CacheCallback<String>() {
                @Override
                public boolean onCache(String result) {
                    return false;
                }
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject jsonObject=new JSONObject(result);
                        String code= (String) jsonObject.get("code");
                        if(code.equals("200")){
                            if (tag.equals("IHavaPartActivity")){
                                pop(finalI,"成功",a);
                            }else{
                                Toast.makeText(contexts, "选择的第"+ finalI +"张"+"图片"+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                            JSONObject js=jsonObject.getJSONObject("body");
                            urlList.get(finalI-1).add(js.getString("url"));
                            urlList.get(finalI-1).addLast(js.getString("local_path"));
                            //上传完成以后将缩略图删除
                            Common_utils.deletePic(fileName);
                        }else if(!code.equals("200")){
                            urlList.get(finalI-1).add("failed");
                            urlList.get(finalI-1).addLast("failed");
                        }
                        Log.i("TAG", "continue3: "+urlList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.i("TAG", "onError: "+ex);
                    urlList.get(finalI-1).add("failed");
                    urlList.get(finalI-1).addLast("failed");
                    if (tag.equals("IHavaPartActivity")){
                        pop(finalI,"失败,服务器或网络异常！",a);
                    }else{
                        Toast.makeText(contexts, "选择的第"+ finalI +"张"+"图片上传失败,服务器或网络异常！", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(CancelledException cex) {

                }
                @Override
                public void onFinished() {
                        //判断urllist

                        switch (tag){
                            case "ask":
                                ask_second_activity context= (ask_second_activity) contexts;//强制转形
                                context.getpath(urlList);
                                if(finalI==a){//循环到了最后一张
                                    Pic_bean.UoLoadPic=true;//上传完以后从新变为true
                                    Pic_bean.list=urlList;//存储到静态变量中
                                }
                                break;
                            case "answer_final"://如果是最后一个回答界面
                                answer_final_activity answer_final= (answer_final_activity) contexts;
                                answer_final.getpath(urlList);
                                if(finalI==a){
                                    Pic_bean.answer_finallist=urlList;
                                    Pic_bean.answer_final_pic=true;//说明最后一组图片上传成功，上传完以后从新变为true
                                }
                                break;
                            case "answer_frist"://如果是第一个回答界面
                                answer_frist_activity answer_frist= (answer_frist_activity) contexts;
                                answer_frist.getpath(urlList);
                                if(finalI==a){
                                    Pic_bean.answer_frist_list=urlList;
                                    Pic_bean.answer_frist_pic=true;//说明最后一组图片上传成功，上传完以后从新变为true
                                }
                                break;
                            case "answer_second"://如果是第二个回答界面
                                answer_second_activity answer_second= (answer_second_activity) contexts;
                                answer_second.getpath(urlList);
                                if(finalI==a){
                                    Pic_bean.answer_second_list=urlList;
                                    Pic_bean.answer_second_pic=true;//说明最后一组图片上传成功，上传完以后从新变为true
                                }
                                break;
                            case "frist":
                                Certified_company_four_activity company_frist= (Certified_company_four_activity) contexts;
                                company_frist.getFirst(urlList);
                                if(finalI==a){
                                    Pic_bean.answer_frist_list=urlList;
                                    Pic_bean.answer_frist_pic=true;//说明第一组上传成功，上传完以后从新变为true
                                }
                                break;
                            case "second":
                                Certified_company_four_activity company_second= (Certified_company_four_activity) contexts;
                                company_second.getSeconde(urlList);
                                if(finalI==a){
                                    Pic_bean.answer_second_list=urlList;
                                    Pic_bean.answer_second_pic=true;//说明第二组图片上传成功，上传完以后从新变为true
                                }
                                break;
                            case "push_frist":
                                push_zhengshu_activity zhengshu_activity_frist= (push_zhengshu_activity) contexts;
                                zhengshu_activity_frist.getFirst(urlList);
                                if(finalI==a){
                                    Pic_bean.answer_frist_list=urlList;
                                    Pic_bean.answer_frist_pic=true;//说明第一组上传成功，上传完以后从新变为true
                                }
                                break;
                            case "push_second":
                                push_zhengshu_activity zhengshu_activity_second= (push_zhengshu_activity) contexts;
                                zhengshu_activity_second.getSeconde(urlList);
                                if(finalI==a){
                                    Pic_bean.answer_second_list=urlList;
                                    Pic_bean.answer_second_pic=true;//说明第二组图片上传成功，上传完以后从新变为true
                                }
                                break;
                            case "push_personal_picture":
                                push_personal_Or_other_second_info_activity personal_second_activity=new push_personal_Or_other_second_info_activity();
                                personal_second_activity.getpath(urlList);
                                if(finalI==a){
                                    Pic_bean.UoLoadPic=true;//上传完以后从新变为true
                                    Pic_bean.list=urlList;//存储到静态变量中
                                }
                                break;
                            case "push_knowledge_finish_activity":
                                push_knowledge_finish_activity pushKnowledgeFinishActivity=new push_knowledge_finish_activity();
                                pushKnowledgeFinishActivity.getpath(urlList);
                                Pic_bean.answer_frist_list=urlList;//存储到静态变量中
                                if(finalI==a){
                                    Pic_bean.answer_frist_pic=true;//上传完以后从新变为true
                                }
                                break;
                            case "push_knowledge_repair_second_activity":
                                push_knowledge_repair_second_activity pushKnowledgeRepairSecondActivity=new push_knowledge_repair_second_activity();
                                pushKnowledgeRepairSecondActivity.getpath(urlList);
                                Pic_bean.answer_frist_list=urlList;//存储到静态变量中
                                if(finalI==a){
                                    Pic_bean.answer_frist_pic=true;//上传完以后从新变为true
                                }
                                break;
                            case "push_knowledge_repair_three_activity":
                                push_knowledge_repair_three_activity pushKnowledgeRepairThreeActivity= (push_knowledge_repair_three_activity) contexts;
                                pushKnowledgeRepairThreeActivity.getpath(urlList);
                                Pic_bean.answer_second_list=urlList;
                                if(finalI==a){
                                    Pic_bean.answer_second_pic=true;//说明第二组图片上传成功，上传完以后从新变为true
                                }
                                break;
                            case "IHavaPartActivity":
                                IHavaPartActivity  iHavaPartActivity= (IHavaPartActivity) contexts;
                                if (finalI==a){
                                    iHavaPartActivity.getpath(urlList);
                                }
                                break;
                        }
                }
            });

        }
    }
    public void pop(int fianl,String str,int a){
        Log.i("TAG", "pop: "+fianl);
        Log.i("TAG", "pop: "+a);

        mProgress.setProgress((int)(((float)fianl /a) * 100));
        tv_jd_id.setText("第"+fianl+"张上传"+str);
        if (fianl==a){
           new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    tv_jd_id.setText("上传完成！");
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertDialog2.dismiss();
                        }
                    },1000);
                }
            },1000);
        }
    }
}
