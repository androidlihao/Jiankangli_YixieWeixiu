package utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.hs.jiankangli_example1.Push_Info.push_info_company_frist_activity;
import com.example.hs.jiankangli_example1.Push_Info.push_info_other_frist_activity;
import com.example.hs.jiankangli_example1.Push_Info.push_info_project_frist_activity;
import com.example.hs.jiankangli_example1.Push_Info.push_personal_info_frist_activity;
import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.ask.ask_frist_activity;
import com.example.hs.jiankangli_example1.ask.ask_other_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.explanation_of_nouns_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.work_or_error_frist_kactivity;
import com.example.hs.jiankangli_example1.push_knowledge_package.fault_frist_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.push_knowledge_repair_frist_activity;

import bean.Pic_bean;

/**
 * Created by 李浩 on 2016/11/3.
 */
public class askOrpush {
    private Context context;
    private Dialog dialog;
    private WindowManager m;
    private String ask;
    public askOrpush(Context context, WindowManager m, String ask){
        this.context=context;
        this.m=m;
        this.ask=ask;
    }
    public Dialog setDialog() {
        dialog = new Dialog(context, R.style.Transparent);
        dialog.setContentView(R.layout.push_layout);  //设置dialog的布局
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = (d.getHeight());   //高度设置为屏幕的高度
        p.width = (d.getWidth());    //宽度设置为屏幕的宽度
        dialog.getWindow().setAttributes(p);//设置生效
        if(ask.equals("ask")){//如果是提问
            dialog.findViewById(R.id.tv_bt2_id).setVisibility(View.GONE);
            TextView tv_cheats_id= (TextView) dialog.findViewById(R.id.tv_cheats_id);
            TextView tv_1_title_id= (TextView) dialog.findViewById(R.id.tv_1_title_id);
            tv_1_title_id.setText("故障");
            tv_cheats_id.setText("其他");
        }else if(ask.equals("push_info")){
            dialog.findViewById(R.id.tv_bt2_id).setVisibility(View.GONE);
            TextView tv_1_title_id= (TextView) dialog.findViewById(R.id.tv_1_title_id);
            TextView tv_2_title_id= (TextView) dialog.findViewById(R.id.tv_2_title_id);
            TextView tv_3_title_id= (TextView) dialog.findViewById(R.id.tv_3_title_id);
            TextView tv_cheats_id= (TextView) dialog.findViewById(R.id.tv_cheats_id);
            tv_1_title_id.setText("人才信息");
            tv_2_title_id.setText("公司信息");
            tv_3_title_id.setText("项目信息");
            tv_cheats_id.setText("其他信息");
        }else if(ask.equals("push")){
            dialog.findViewById(R.id.tv_bt1_id).setVisibility(View.GONE);
            dialog.findViewById(R.id.ll_noun_id).setVisibility(View.VISIBLE);
        }
        setOnClicklistener();
        return dialog;
    }
    private void setOnClicklistener() {
        dialog.findViewById(R.id.ll_guzhang_id).setOnClickListener(new MyOnClickListener());
        dialog.findViewById(R.id.ll_cuowu_id).setOnClickListener(new MyOnClickListener());
        dialog.findViewById(R.id.ll_gongzuo_id).setOnClickListener(new MyOnClickListener());
        dialog.findViewById(R.id.fabu_exit_id).setOnClickListener(new MyOnClickListener());
        dialog.findViewById(R.id.ll_cheats_id).setOnClickListener(new MyOnClickListener());
        dialog.findViewById(R.id.ll_noun_id).setOnClickListener(new MyOnClickListener());
    }
    private class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.ll_cuowu_id://跳转到错误编码界面
                    Intent intent3;
                    switch (ask){
                        case "ask":
                            Pic_bean.list=null;
                            intent3=new Intent(context,ask_frist_activity.class);
                            intent3.putExtra("ask_content","错误编码");
                            context.startActivity(intent3);
                            dialog.dismiss();
                            break;
                        case "push":
                            intent3=new Intent(context,work_or_error_frist_kactivity.class);//工作原理和错误编码共用
                            intent3.putExtra("cuowu","错误编码");
                            context.startActivity(intent3);
                            dialog.dismiss();
                            break;
                        case "push_info":
                            intent3=new Intent(context,push_info_project_frist_activity.class);
                            context.startActivity(intent3);
                            dialog.dismiss();
                            break;
                    }
                    break;
                case R.id.ll_gongzuo_id://跳转到工作原理界面
                    Intent intent2;
                    switch (ask){
                        case "ask":
                            Pic_bean.list=null;
                            intent2=new Intent(context,ask_frist_activity.class);
                            intent2.putExtra("ask_content","工作原理");
                            context.startActivity(intent2);
                            dialog.dismiss();
                            break;
                        case "push":
                            intent2=new Intent(context,work_or_error_frist_kactivity.class);
                            context.startActivity(intent2);
                            dialog.dismiss();
                            break;
                        case "push_info":
                            intent2=new Intent(context,push_info_company_frist_activity.class);
                            context.startActivity(intent2);
                            dialog.dismiss();
                            break;
                    }
                    break;
                case R.id.ll_guzhang_id://跳转到故障维修方案页面
                    Intent intent;
                        switch (ask){
                            case "ask":
                                Pic_bean.list=null;
                                intent=new Intent(context,ask_frist_activity.class);
                                intent.putExtra("ask_content","故障");
                                context.startActivity(intent);
                                dialog.dismiss();
                                break;
                            case "push":
                                intent=new Intent(context,fault_frist_activity.class);
                                context.startActivity(intent);
                                dialog.dismiss();
                                break;
                            case "push_info":
                                intent2=new Intent(context,push_personal_info_frist_activity.class);
                                context.startActivity(intent2);
                                dialog.dismiss();
                                break;
                        }
                    break;
                case R.id.fabu_exit_id:
                    dialog.dismiss();
                    break;
                case R.id.ll_cheats_id://跳转到维修秘籍界面
                    Intent intents;
                    switch (ask){
                        case "ask":
                            Pic_bean.list=null;
                            intent=new Intent(context,ask_other_activity.class);
                            intent.putExtra("ask_content","其他");
                            context.startActivity(intent);
                            dialog.dismiss();
                            break;
                        case "push":
                            intents=new Intent(context,push_knowledge_repair_frist_activity.class);
                            context.startActivity(intents);
                            dialog.dismiss();
                            break;
                        case "push_info":
                            intent2=new Intent(context,push_info_other_frist_activity.class);
                            context.startActivity(intent2);
                            dialog.dismiss();
                            break;
                    }
                    break;
                case R.id.ll_noun_id:
                    //跳转到名词解释界面
                    Intent intent_explanation =new Intent(context,explanation_of_nouns_activity.class);
                    context.startActivity(intent_explanation);
                    dialog.dismiss();
                    break;
            }
        }
    }
}
