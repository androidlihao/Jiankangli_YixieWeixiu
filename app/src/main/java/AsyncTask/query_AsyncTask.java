package AsyncTask;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.hs.jiankangli_example1.PartsDetailsActivity;
import com.example.hs.jiankangli_example1.Push_Info.push_info_company_frist_activity;
import com.example.hs.jiankangli_example1.Push_Info.push_info_other_frist_activity;
import com.example.hs.jiankangli_example1.Push_Info.push_info_project_frist_activity;
import com.example.hs.jiankangli_example1.Push_Info.push_personal_info_frist_activity;
import com.example.hs.jiankangli_example1.ask.ask_frist_activity;
import com.example.hs.jiankangli_example1.ask.ask_other_activity;
import com.example.hs.jiankangli_example1.certified_company.Certified_company_frist_activity;
import com.example.hs.jiankangli_example1.certified_company.Certified_company_status_activity;
import com.example.hs.jiankangli_example1.common_activity_pacage.Select_more_activity;
import com.example.hs.jiankangli_example1.common_activity_pacage.brand_or_skill_activity;
import com.example.hs.jiankangli_example1.common_activity_pacage.province_and_city_activity;
import com.example.hs.jiankangli_example1.fix_helper_Activity;
import com.example.hs.jiankangli_example1.password.Change_password_activity;
import com.example.hs.jiankangli_example1.password.Login_activity;
import com.example.hs.jiankangli_example1.password.Register_activity;
import com.example.hs.jiankangli_example1.password.Register_finish;
import com.example.hs.jiankangli_example1.personal_Info.Edit_Personal_Info_activity;
import com.example.hs.jiankangli_example1.personal_Info.personal_Skill_activity;
import com.example.hs.jiankangli_example1.personal_Info.personal_info_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.explanation_of_nouns_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.fault_frist_activity;
import com.example.hs.jiankangli_example1.push_knowledge_package.work_or_error_frist_kactivity;
import com.example.hs.jiankangli_example1.push_knowledge_package.push_knowledge_repair_frist_activity;
import com.example.hs.jiankangli_example1.seek.seek_activity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashSet;
import java.util.Set;

import bean.personal;
import cn.jpush.android.api.JPushInterface;
import fragment.Home_fragment;
import fragment.Ower_fragment;
import integral.integral_detail_activity;
import utils.JavaScriptObject;
import utils.Max_integral;
import utils.UpdateManager;
import utils.XUtilsDB;


/**
 * Created by 李浩 on 2016/11/8.
 */
public class query_AsyncTask extends AsyncTask<RequestParams,Void,String>{
    private RequestParams params;
    private Object object;
    private String tag;
    public query_AsyncTask(RequestParams params,Object object, String tag){
        this.params=params;
        this.object=object;
        this.tag=tag;
    }
    @Override
    protected String doInBackground(RequestParams... requestParamses) {
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                Log.i("sfsf", "onSuccess: "+result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String code=jsonObject.getString("code");
                    switch (tag){
                        case "city":
                            province_and_city_activity city_activity= (province_and_city_activity) object;
                            city_activity.getinfo(result);
                            clearInfo(city_activity,code);
                            break;
                        case "integral":
                            Max_integral max_integral= (Max_integral)object;
                            max_integral.getinfo(result);
                            break;
                        case "checked":
                            Certified_company_frist_activity frist_activity= (Certified_company_frist_activity) object;
                            frist_activity.getinfo(result);
                            clearInfo(frist_activity,code);
                            break;
                        case "approve":
                            Certified_company_status_activity status_activity= (Certified_company_status_activity) object;
                            status_activity.getinfo(result);
                            clearInfo(status_activity,code);
                            break;
                        case "brand_or_skill":
                            brand_or_skill_activity brandorskill_activity= (brand_or_skill_activity) object;
                            brandorskill_activity.getinfo(result);
                            clearInfo(brandorskill_activity,code);
                            break;
                        case "push_personal":
                            push_personal_info_frist_activity personalInfoFristActivity= (push_personal_info_frist_activity) object;
                            personalInfoFristActivity.getinfo(result);
                            clearInfo(personalInfoFristActivity,code);
                            break;
                        case "push_company":
                            push_info_company_frist_activity  pushInfoCompanyFristActivity= (push_info_company_frist_activity) object;
                            pushInfoCompanyFristActivity.getinfo(result);
                            clearInfo(pushInfoCompanyFristActivity,code);
                            break;
                        case "push_project":
                            push_info_project_frist_activity pushInfoProjectFristActivity= (push_info_project_frist_activity) object;
                            pushInfoProjectFristActivity.getinfo(result);
                            clearInfo(pushInfoProjectFristActivity,code);
                            break;
                        case "push_other":
                            push_info_other_frist_activity pushInfoOtherFristActivity= (push_info_other_frist_activity) object;
                            pushInfoOtherFristActivity.getinfo(result);
                            clearInfo(pushInfoOtherFristActivity,code);
                            break;
//                        case "getCitys_Seek":
//                            seek_activity seek_activitys= (seek_activity) object;
//                            seek_activitys.getinfo(result);
//                            clearInfo(seek_activitys,code);
//                            break;
                        case "fault_frist_activity":
                            fault_frist_activity fault_frist= (fault_frist_activity) object;
                            fault_frist.getinfo(result);
                            clearInfo(fault_frist,code);
                            break;
                        case "work_or_error_frist_kactivity":
                            work_or_error_frist_kactivity gongzuoactivity= (work_or_error_frist_kactivity) object;
                            gongzuoactivity.getinfo(result);
                            clearInfo(gongzuoactivity,code);
                            break;
                        case "push_knowledge_repair_frist_activity":
                            push_knowledge_repair_frist_activity weixiu0= (push_knowledge_repair_frist_activity) object;
                            weixiu0.getinfo(result);
                            clearInfo(weixiu0,code);
                            break;
                        case "explanation_of_nouns_activity":
                            explanation_of_nouns_activity explanationOfNounsActivity= (explanation_of_nouns_activity) object;
                            explanationOfNounsActivity.getinfo(result);
                            clearInfo(explanationOfNounsActivity,code);
                            break;
                        case "Change_password_activity":
                            Change_password_activity changePasswordActivity= (Change_password_activity) object;
                            changePasswordActivity.getinfo(result);
                            clearInfo(changePasswordActivity,code);
                            break;
                        case "get_personal_info":
                            Ower_fragment owerFragment= (Ower_fragment) object;
                            owerFragment.getinfo(result);
                            clearInfo(owerFragment.getActivity(),code);
                            break;
                        case "notices":
                            Home_fragment home_fragment= (Home_fragment) object;
                            home_fragment.getinfo(result);
                            clearInfo(home_fragment.getActivity(),code);
                            break;
                        case "updata":
                            UpdateManager updateManager= (UpdateManager) object;
                            updateManager.getinfo(result);
                            break;
                        case "ask_frist_activity":
                            ask_frist_activity askFristActivity= (ask_frist_activity) object;
                            askFristActivity.getinfo(result);
                            clearInfo(askFristActivity,code);
                            break;
                        case "ask_other_activity":
                            ask_other_activity askOtherActivity= (ask_other_activity) object;
                            askOtherActivity.getinfo(result);
                            clearInfo(askOtherActivity,code);
                            break;
                        case "question":
                            JavaScriptObject javaScriptObject= (JavaScriptObject) object;
                            javaScriptObject.getinfo(result);
                            break;
                        case "integeral_detatil":
                            integral_detail_activity integral_detail_activity= (integral.integral_detail_activity) object;
                            integral_detail_activity.getinfo(result);
                            clearInfo(integral_detail_activity,code);
                            break;
                        case "personal_info_activity":
                            personal_info_activity personal_info_activity= (com.example.hs.jiankangli_example1.personal_Info.personal_info_activity) object;
                            personal_info_activity.getinfo(result);
                            clearInfo(personal_info_activity,code);
                            break;
                        case "Edit_Personal_Info_activity":
                            Edit_Personal_Info_activity edit_personal_info_activity= (Edit_Personal_Info_activity) object;
                            edit_personal_info_activity.getinfo(result);
                            clearInfo(edit_personal_info_activity,code);
                            break;
                        case "personal_Skill_activity":
                            personal_Skill_activity personal_Skill_activitys= (personal_Skill_activity) object;
                            personal_Skill_activitys.getinfo(result);
                            clearInfo(personal_Skill_activitys,code);
                            break;
                        case "PartsDetailsActivity":
                            PartsDetailsActivity partsDetailsActivity= (PartsDetailsActivity) object;
                            partsDetailsActivity.getinfo(result);
                            clearInfo(partsDetailsActivity,code);
                            break;
                        case "Login":
                            Login_activity logins= (Login_activity) object;
                            logins.getinfo(result);
                            clearInfo(logins,code);
                            break;
                        case "register":
                            Register_activity registers= (Register_activity) object;
                            registers.getinfo(result);
                            clearInfo(registers,code);
                            break;
                        case "Register_finish":
                            Register_finish Register_finishs= (Register_finish) object;
                            Register_finishs.getinfo(result);
                            clearInfo(Register_finishs,code);
                            break;
                        case "Select_more_activity":
                            Select_more_activity select_more_activity= (Select_more_activity) object;
                            select_more_activity.getinfo(result);
                            clearInfo(select_more_activity,code);
                            break;

                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("TAG", "onError: "+ex);
                try {
                    Toast.makeText((Activity)object, "网络或服务器异常！", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {
                //然后将result返回去

            }
        });
        return null;
    }
    public void clearInfo(Activity activity,String code){
        switch (code){
            case "200":

                break;
            case "20002":
                Toast.makeText(activity,"您已被注销，请联系管理员!", Toast.LENGTH_SHORT).show();
                //强制退出
                Set<String> strings=new HashSet<>();
                JPushInterface.setAliasAndTags(activity, "", strings,new Login_activity().mAliasCallback);
                SharedPreferences sp=activity.getSharedPreferences("config",0);
                SharedPreferences.Editor edit = sp.edit();
                edit.clear();
                edit.commit();//清除用户登录信息
                //删除保存在数据库中的信息
                DbManager db=x.getDb(XUtilsDB.getDBconfig());
                try {
                    db.dropTable(personal.BodyBean.DataBean.class);//将存储的表格也删除
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case "20003":
                Toast.makeText(activity,"您已被禁言，请联系管理员", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
