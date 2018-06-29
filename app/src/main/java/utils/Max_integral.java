package utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import AsyncTask.query_AsyncTask;
import Inter.Globle;
import Inter.get_net_Info;
import bean.info;

/**
 * Created by 李浩 on 2016/11/8.
 */
public class Max_integral implements get_net_Info {
    private  int MIN_MARK=0 ;
    private static int MAX_MARK ;
    private Toast toast;//在类前面声明吐司，确保在这个页面只有一个吐司
    private final static String MemberInfo_URL= Globle.TEST_URL+"/api/member/selectMemberInfoById";
    private static Context context;
    public void SetTextChange(EditText editText,  Context context){
        RequestParams params=new RequestParams(MemberInfo_URL);
        this.context=context;
        //查询积分详情
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("member_id",new JavaScriptObject(context).getMemberid(""));
            String jsonString = Base64.encodeToString(jsonObject.toString().getBytes(),Base64.DEFAULT);//base64加密
            params.setBodyContent(jsonString);
            new query_AsyncTask(params,new Max_integral(),"integral").execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s!= null &&!s.equals(""))
                {
                    if (MIN_MARK != -1 && MAX_MARK != -1)
                    {
                        int markVal = 0;
                        try
                        {
                            markVal = Integer.parseInt(s.toString());
                        }catch (NumberFormatException e)
                        {
                            markVal = 0;
                        }
                        if (markVal > MAX_MARK)
                        {
                            showToast();
                        }
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void getinfo(String str) {
        info infos= com.alibaba.fastjson.JSONObject.parseObject(str,info.class);
        if(infos.getBody()!=null&&infos.getBody().getData()!=null&&infos.getBody().getData().getCurrentIntegral()!=0){
           MAX_MARK=infos.getBody().getData().getCurrentIntegral();
        }
    }
    public void showToast() {
        if (toast == null) {
            toast = Toast.makeText(context, "您当前的积分数为"+MAX_MARK+",您目前的积分无法满足您的悬赏哦!", Toast.LENGTH_SHORT);
        } else {
            toast.cancel();//关闭吐司显示
            toast = Toast.makeText(context, "您当前的积分数为"+MAX_MARK+",您目前的积分无法满足您的悬赏哦!", Toast.LENGTH_SHORT);
        }
        toast.show();//重新显示吐司
    }
}
