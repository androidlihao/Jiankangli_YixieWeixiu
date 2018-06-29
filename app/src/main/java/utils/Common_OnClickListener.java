package utils;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.hs.jiankangli_example1.R;
import com.example.hs.jiankangli_example1.common_activity_pacage.Select_more_activity;

/**
 * Created by 李浩 on 2017/1/17.
 */

public class Common_OnClickListener implements View.OnClickListener {
    private Activity context;
    public Common_OnClickListener(Activity context){
        this.context=context;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gengduo_1_id:
                //跳转到产品分类的界面获取返回值
                Intent intent=new Intent(context,Select_more_activity.class);
                intent.putExtra("add","产品分类");
                context.startActivityForResult(intent,1);//设置Intent和请求码
                break;
            case R.id.gengduo_2_id:
                //跳转到厂商界面，获取选择的返回值
                Intent intent2=new Intent(context,Select_more_activity.class);
                intent2.putExtra("add","品牌");
                context.startActivityForResult(intent2,2);
                break;
        }
    }

}
